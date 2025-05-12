package spring_boot.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import spring_boot.demo.api.response.WeatherResponse;
import spring_boot.demo.service.UserEntryService;
import spring_boot.demo.entry.UserEntry;
import spring_boot.demo.repository.UserEntryRepo;
import spring_boot.demo.service.WeatherApiService;

@RestController
@RequestMapping("/user")
public class UsersMongoEntryController {

    @Autowired
    private UserEntryService userEntryService;

    @Autowired
    private UserEntryRepo userEntryRepo;

    @Autowired
    private WeatherApiService weatherApiService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    @PutMapping
//    public ResponseEntity<?> updateUser(@RequestBody UserEntry userEntry) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userName = authentication.getName();
//        UserEntry userInDb = userEntryService.findByUserName(userName);
//            userInDb.setUserName(userEntry.getUserName());
//            userInDb.setPassword(userEntry.getPassword());
//            userEntryService.saveNewEntry(userInDb);
//            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
//    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserEntry userEntry){
        Authentication authentication = new SecurityContextHolder().getContext().getAuthentication();
        String name = authentication.getName();
        UserEntry userInDb = userEntryService.findByUserName(name);
        if(userInDb != null){
            userInDb.setUserName(userEntry.getUserName());
            userInDb.setPassword(passwordEncoder.encode(userEntry.getPassword()));
            userEntryService.saveNewEntry(userInDb);
            return new ResponseEntity<>("User updated", HttpStatus.OK);
        }

        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        userEntryRepo.deleteByUserName(name);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weather = weatherApiService.getWeather("Mumbai");
        String greeting = "";
        if(weather != null){
         greeting = ", Weather feels like " + weather.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>(" hi "+ authentication.getName() + greeting, HttpStatus.OK);
    }
}
