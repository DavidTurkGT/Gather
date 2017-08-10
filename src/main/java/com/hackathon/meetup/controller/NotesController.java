package com.hackathon.meetup.controller;

/**
 * Created by David Turk on 8/10/17.
 */
public class NotesController {
    public static String createNote(int userId){
        return "Creating note about " + userId;
    }

    public static String getNoteById(int noteId){
        return "Getting note " + noteId;
    }

    public static String updateNote(int noteId){
        return "Updating note " + noteId;
    }

    public static String deleteNote(int noteId){
        return "Deleting note " + noteId;
    }

    public static String getAllNotes(int userId){
        return "Getting all notes about user " + userId;
    }
}
