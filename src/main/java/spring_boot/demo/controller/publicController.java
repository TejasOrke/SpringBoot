package spring_boot.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring_boot.demo.entry.UserEntry;
import spring_boot.demo.service.UserEntryService;

@RestController
@RequestMapping("/public")
public class publicController {
    @Autowired
    private UserEntryService userEntryService;



    @PostMapping("/create")
    public void createUser(@RequestBody UserEntry userEntry) {
        userEntryService.saveNewEntry(userEntry);
    }

}
