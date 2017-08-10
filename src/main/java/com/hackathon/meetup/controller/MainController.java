package com.hackathon.meetup.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.meetup.domain.Event;
import com.hackathon.meetup.domain.Status;
import com.hackathon.meetup.domain.User;
import com.hackathon.meetup.repository.EventRepo;
import com.hackathon.meetup.repository.UserRepo;
import com.hackathon.meetup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Date;


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

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index(){
        return "You made it!";
    }

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/api/register")
    public User register(@RequestBody String json) throws IOException{
        User user = objectMapper.readValue( json, User.class );
        return userService.addUser( user );
    }

}

