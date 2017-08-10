package com.hackathon.meetup.controller;

import com.hackathon.meetup.repository.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by David Turk on 8/10/17.
 */
public class EventsController {
    @Autowired
    EventRepo events;

    public static String getAllEvents(){
        return "Getting all events!";
    }

    public static String createNewEvent(){
        return "Creating an event";
    }

    public static String getEventById(int eventId){
        return "Getting event " + eventId;
    }

    public static String modifyEvent(int eventId){
        return "Modify an event " + eventId;
    }

    public static String deleteEvent(int eventId){
        return "Deleting event " + eventId;
    }

    public static String startEvent(int eventId){
        return "Starting event " + eventId;
    }

    public static String stopEvent(int eventId){
        return "Stopping event " + eventId;

    }

    public static String rsvp(int eventId){
        return "RSVP for: " + eventId;
    }

}
