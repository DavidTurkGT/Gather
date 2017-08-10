package com.hackathon.meetup.controller;

import com.hackathon.meetup.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    UserRepo userRepo;

   
    @GetMapping("/")
    public String index(){
        return "You made it!";
    }
}

