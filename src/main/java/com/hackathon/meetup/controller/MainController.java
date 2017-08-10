package com.hackathon.meetup.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.meetup.containers.NewEvent;
import com.hackathon.meetup.domain.Event;
import com.hackathon.meetup.domain.Response;
import com.hackathon.meetup.domain.Status;
import com.hackathon.meetup.domain.User;
import com.hackathon.meetup.exceptions.BadRequestException;
import com.hackathon.meetup.exceptions.ContentNotFoundException;
import com.hackathon.meetup.exceptions.InternalServerErrorException;
import com.hackathon.meetup.exceptions.UnauthorizedException;
import com.hackathon.meetup.repository.EventRepo;
import com.hackathon.meetup.repository.UserRepo;
import com.hackathon.meetup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@RestController
public class MainController {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    UserRepo users;

    @Autowired
    EventRepo events;

    @PostConstruct
    public void init(){
        if(users.count() == 0){
            User testAdmin = new User("David","Turk","dtgt","pass","test@test.org","8675309",true);
            users.save(testAdmin);
            User testUser = new User("Cornbread", "Cat", "corn", "cat", "meow@cat.com", "8675309", false);
            users.save(testUser);
        }
        User testAdmin = users.findAll().get(0);
        System.out.println("Admin: " + testAdmin);
        User testUser = users.findAll().get(1);
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
        try {
            List<Event> allEvents = events.findAll();
            Response res = new Response<List>(allEvents);
            return objectMapper.writeValueAsString(res);
        } catch (JsonProcessingException e) {
            throw new InternalServerErrorException("Error in processing to JSON");
        }
    }

    @PostMapping("/api/events")
    public String createNewEvent(@RequestBody NewEvent eventContainer,
                                 HttpSession session){
        //TODO: Remove this when a session can be created
        session.setAttribute("userId", 6);
//        if(date == null){ throw new BadRequestException("Event date cannot be null"); }
        User user = users.findOne((int) session.getAttribute("userId"));
        if(!user.isAdmin()){ throw new UnauthorizedException("User does not have admin privileges"); }
        //TODO: Test with front-end form to get Data object working
        Event newEvent = new Event();
        newEvent.setAdmin(user);
        if(eventContainer.getName() == null){ throw new BadRequestException("Name cannot be null"); }
        newEvent.setName(eventContainer.getName());
        if(eventContainer.getLocation() == null){ throw new BadRequestException("Location cannot be null"); }
        newEvent.setLocation(eventContainer.getLocation());
        newEvent.setDescription(eventContainer.getDescription());
        newEvent.setDate(new Date()); //TODO: work with the front-end to get a real date
        newEvent.setStatus(Status.NEW);
        newEvent = events.save(newEvent);
        Response res = new Response<Event>(newEvent);
        try {
            return objectMapper.writeValueAsString(res);
        } catch (JsonProcessingException e) {
            throw new InternalServerErrorException("Error in processing response as a JSON");
        }
    }

    @GetMapping("/api/events/{eventId}")
    public String getEventById(@PathVariable int eventId){
        Event event = events.findOne(eventId);
        if(event == null){
            throw new ContentNotFoundException("No event matching ID");
        }
        try{
            Response res = new Response<Event>(event);
            return objectMapper.writeValueAsString(res);
        } catch( JsonProcessingException e){
            throw new InternalServerErrorException("Error in processing to JSON");
        }
    }

    @PutMapping("/api/events/{eventId}")
    public String modifyEvent(@PathVariable int eventId,
                              @RequestParam(value = "name") String name,
                              @RequestParam(value = "location") String location,
                              @RequestParam(value = "description") String description,
                              HttpSession session){
        //TODO: Remove this when session is working
        session.setAttribute("userId", 4);
        Event event = events.findOne(eventId);
        if(event == null){ throw new ContentNotFoundException("No event matching ID"); }
        if(event.getAdmin().getUserid() != (int) session.getAttribute("userId")){
            throw new UnauthorizedException("Only the creator of an event may modify the event");
        }
        if(name == null){ throw new BadRequestException("Name cannot be null"); }
        if(location == null){ throw new BadRequestException("Location cannot be null"); }
        Event modifiedEvent = new Event(event.getAdmin(), name, location, description, new Date(), event.getStatus());
        modifiedEvent.setEventId(event.getEventId());
        modifiedEvent = events.save(modifiedEvent);
        try{
            Response res = new Response<Event>(modifiedEvent);
            return objectMapper.writeValueAsString(res);
        } catch (JsonProcessingException e){
            throw new InternalServerErrorException("Error processing response to JSON");
        }
    }

    @DeleteMapping("/api/events/{eventId}")
    public String deleteEvent(@PathVariable int eventId){
        return null;
    }

    @PostMapping("/api/events/{eventId}/start")
    public String startEvent(@PathVariable int eventId){
        return null;
    }

    @PostMapping("/api/events/{eventId}/stop")
    public String stopEvent(@PathVariable int eventId){
        return null;
    }

    @PostMapping("/api/events/{eventId}/rsvp")
    public String rsvp(@PathVariable int eventId){
        return null;
    }

    /**
     * Users endpoints
     */
    @GetMapping("/api/users/{userId}")
    public String getUserById(@PathVariable int userId){
        return null;
    }

    @PostMapping("/api/users/block/{userId}")
    public String blockUser(@PathVariable int userId){
        return null;
    }

    /**
     * Acquaintances endpoints
     */
    @GetMapping("/api/acquaintances/{userId}")
    public String getAcquaintances(@PathVariable int userId){
        return null;
    }

    @DeleteMapping("/api/acquaintances/{acqId}")
    public String deleteAcquaintance(@PathVariable int acqId){
        return null;
    }

    @PostMapping("/api/acquaintances/request/{userId}")
    public String requestAcquaintance(@PathVariable int userId){
        return null;
    }

    @GetMapping("/api/acquaintances/request/{userId}/incoming")
    public String getIncomingRequests(@PathVariable int userId){
        return null;
    }

    @GetMapping("/api/acquaintances/request/{userId}/outgoing")
    public String getOutgoingRequests(@PathVariable int userId){
        return null;
    }

    @PostMapping("/api/acquaintances/request/accept/{reqId}")
    public String acceptRequest(@PathVariable int reqId){
        return null;
    }

    @DeleteMapping("/api/acquaintances/request/{reqId}")
    public String deleteRequest(@PathVariable int reqId){
        return null;
    }

    /**
     * Notes endpoints
     */
    @PostMapping("/api/notes/{userId}")
    public String createNote(@PathVariable int userId){
        return null;
    }

    @GetMapping("/api/notes/{noteId}")
    public String getNoteById(@PathVariable int noteId){
        return null;
    }

    @PutMapping("/api/notes/{noteId}")
    public String updateNote(@PathVariable int noteId){
        return null;
    }

    @DeleteMapping("/api/notes/{noteId}")
    public String deleteNote(@PathVariable int noteId){
        return null;
    }

    @GetMapping("/api/notes/all/{userId}")
    public String getAllNotes(@PathVariable int userId){
        return null;
    }



    @PostMapping("/api/register")
    public User register(@RequestBody String json){
        try{
            User user = objectMapper.readValue( json, User.class );
            return userService.addUser( user );
        } catch (IOException e){
            throw new BadRequestException("JSON did not match data for a user");
        }
    }

}

