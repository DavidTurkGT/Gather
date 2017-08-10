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

    /**
     * Events endpoints
     */
    @GetMapping("/api/events")
    public String getAllEvents(){
        return EventsController.getAllEvents();
    }

    @PostMapping("/api/events")
    public String createNewEvent(){
        return EventsController.createNewEvent();
    }

    @GetMapping("/api/events/{eventId}")
    public String getEventById(@PathVariable int eventId){
        return EventsController.getEventById(eventId);
    }

    @PutMapping("/api/events/{eventId}")
    public String modifyEvent(@PathVariable int eventId){
        return EventsController.modifyEvent(eventId);
    }

    @DeleteMapping("/api/events/{eventId}")
    public String deleteEvent(@PathVariable int eventId){
        return EventsController.deleteEvent(eventId);
    }

    @PostMapping("/api/events/{eventId}/start")
    public String startEvent(@PathVariable int eventId){
        return EventsController.startEvent(eventId);
    }

    @PostMapping("/api/events/{eventId}/stop")
    public String stopEvent(@PathVariable int eventId){
        return EventsController.stopEvent(eventId);
    }

    @PostMapping("/api/events/{eventId}/rsvp")
    public String rsvp(@PathVariable int eventId){
        return EventsController.rsvp(eventId);
    }

    /**
     * Users endpoints
     */
    @GetMapping("/api/users/{userId}")
    public String getUserById(@PathVariable int userId){
        return UserController.getUserById(userId);
    }

    @PostMapping("/api/users/block/{userId}")
    public String blockUser(@PathVariable int userId){
        return UserController.blockUser(userId);
    }

    /**
     * Acquaintances endpoints
     */
    @GetMapping("/api/acquaintances/{userId}")
    public String getAcquaintances(@PathVariable int userId){
        return AcquaintanceController.getAcquaintances(userId);
    }

    @DeleteMapping("/api/acquaintances/{acqId}")
    public String deleteAcquaintance(@PathVariable int acqId){
        return AcquaintanceController.deleteAcquaintances(acqId);
    }

    @PostMapping("/api/acquaintances/request/{userId}")
    public String requestAcquaintance(@PathVariable int userId){
        return AcquaintanceController.requestAcquaintance(userId);
    }

    @GetMapping("/api/acquaintances/request/{userId}/incoming")
    public String getIncomingRequests(@PathVariable int userId){
        return AcquaintanceController.getIncomingRequests(userId);
    }

    @GetMapping("/api/acquaintances/request/{userId}/outgoing")
    public String getOutgoingRequests(@PathVariable int userId){
        return AcquaintanceController.getOutgoingRequests(userId);
    }

    @PostMapping("/api/acquaintances/request/accept/{reqId}")
    public String acceptRequest(@PathVariable int reqId){
        return AcquaintanceController.acceptRequest(reqId);
    }

    @DeleteMapping("/api/acquaintances/request/{reqId}")
    public String deleteRequest(@PathVariable int reqId){
        return AcquaintanceController.deleteAcquaintances(reqId);
    }

    /**
     * Notes endpoints
     */
    @PostMapping("/api/notes/{userId}")
    public String createNote(@PathVariable int userId){
        return NotesController.createNote(userId);
    }

    @GetMapping("/api/notes/{noteId}")
    public String getNoteById(@PathVariable int noteId){
        return NotesController.getNoteById(noteId);
    }

    @PutMapping("/api/notes/{noteId}")
    public String updateNote(@PathVariable int noteId){
        return NotesController.updateNote(noteId);
    }

    @DeleteMapping("/api/notes/{noteId}")
    public String deleteNote(@PathVariable int noteId){
        return NotesController.deleteNote(noteId);
    }

    @GetMapping("/api/notes/all/{userId}")
    public String getAllNotes(@PathVariable int userId){
        return NotesController.getAllNotes(userId);
    }

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/api/register")
    public User register(@RequestBody String json) throws IOException{
        User user = objectMapper.readValue( json, User.class );
        return userService.addUser( user );
    }

}

