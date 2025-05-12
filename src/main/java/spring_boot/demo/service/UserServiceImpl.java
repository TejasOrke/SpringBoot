package spring_boot.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spring_boot.demo.entry.UserEntry;
import spring_boot.demo.repository.UserEntryRepo;


@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    UserEntryRepo userEntryRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntry user = userEntryRepo.findByUserName(username);

        if(user != null){
            return User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles()
                            .toArray(new String[0]))
                    .build();
        }
        throw new UsernameNotFoundException("User not found with username : " + username);
    }
}
