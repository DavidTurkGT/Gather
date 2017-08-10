package com.hackathon.meetup.controller;

/**
 * Created by David Turk on 8/10/17.
 */
public class AcquaintanceController {
    public static String getAcquaintances(int userId){
        return "Getting acquaintances for user " + userId;
    }

    public static String deleteAcquaintances(int acqId){
        return "Deleting acquaintance " + acqId;
    }

    public static String requestAcquaintance(int userId){
        return "Requesting user: " + userId;
    }

    public static String getIncomingRequests(int userId){
        return "Getting incoming requests for " + userId;
    }

    public static String getOutgoingRequests(int userId){
        return "Getting outgoing requests for " + userId;
    }

    public static String acceptRequest(int reqId){
        return "Accepting request " + reqId;
    }

    public static String deleteRequest(int reqId){
        return "Deleting request " + reqId;
    }
}
