package spring_boot.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import spring_boot.demo.entry.UserEntry;
import spring_boot.demo.repository.UserEntryRepo;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@Disabled
public class UserServiceImplTest {


    @InjectMocks
    private UserServiceImpl userDetailsService;

    @Mock
    private UserEntryRepo userEntryRepo;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Disabled
    @Test
    void loadUserByUsernameTest(){
        when(userEntryRepo.findByUserName(ArgumentMatchers.anyString()))
                .thenReturn(UserEntry
                        .builder().
                        userName("demo").
                        password("demo").roles(new ArrayList<>())
                        .build());
        UserDetails tejas = userDetailsService.loadUserByUsername("tejas");
    }
}
