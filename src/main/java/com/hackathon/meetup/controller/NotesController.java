package com.hackathon.meetup.controller;

import com.hackathon.meetup.domain.User;
import com.hackathon.meetup.exceptions.ContentNotFoundException;
import com.hackathon.meetup.repository.UserRepo;
import com.hackathon.meetup.service.UserService;
import com.hackathon.meetup.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * Created by David Turk on 8/10/17.
 */
@Controller
public class NotesController {

    private UserRepo users;

    public NotesController(UserRepo users) {
        this.users = users;
    }

    public  String createNote(int userId){
        System.out.println("Users: " + users);
        return "Done!";
    }

    public  String getNoteById(int noteId){
        return "Getting note " + noteId;
    }

    public  String updateNote(int noteId){
        return "Updating note " + noteId;
    }

    public  String deleteNote(int noteId){
        return "Deleting note " + noteId;
    }

    public  String getAllNotes(int userId){
        return "Getting all notes about user " + userId;
    }
}
