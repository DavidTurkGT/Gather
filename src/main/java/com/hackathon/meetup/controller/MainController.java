package com.hackathon.meetup.controller;

<<<<<<< HEAD
import com.fasterxml.jackson.databind.ObjectMapper;
=======
>>>>>>> origin/master
import com.hackathon.meetup.domain.Event;
import com.hackathon.meetup.domain.Status;
import com.hackathon.meetup.domain.User;
import com.hackathon.meetup.repository.EventRepo;
import com.hackathon.meetup.repository.UserRepo;
<<<<<<< HEAD
import com.hackathon.meetup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Date;

=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
>>>>>>> origin/master

@RestController
public class MainController {

    @Autowired
    UserRepo users;

    @Autowired
    EventRepo events;

    @PostConstruct
    public void init(){
        if(users.count() == 0){
            User testUser = new User("David","Turk","dtgt","pass","test@test.org","8675309",true);
            users.save(testUser);
        }
        User testUser = users.findAll().get(0);
        System.out.println("User: " + testUser);
        if(events.count() == 0){
            Event newEvent = new Event(testUser, "Test Event", "Moon","Creating things", new Date(), Status.NEW);
            events.save(newEvent);
        }
        Event testEvent = events.findAll().get(0);
        System.out.println("Event: " + testEvent);
    }

<<<<<<< HEAD

    @Autowired
    UserService userService;

=======
   
>>>>>>> origin/master
    @GetMapping("/")
    public String index(){
        return "You made it!";
    }
<<<<<<< HEAD

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/api/register")
    public User register(@RequestBody String json) throws IOException{
        User user = objectMapper.readValue( json, User.class );
        return userService.addUser( user );
    }



}


=======
}

>>>>>>> origin/master
