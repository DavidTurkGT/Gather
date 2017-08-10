package com.hackathon.meetup.controller;

/**
 * Created by David Turk on 8/10/17.
 */
public class UserController {

    public static String getUserById(int userId){
        return "Getting user: " + userId;
    }

    public static String blockUser(int userId){
        return "Blocking user: " + userId;
    }
}
