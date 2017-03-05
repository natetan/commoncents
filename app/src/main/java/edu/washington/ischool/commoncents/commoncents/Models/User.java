package edu.washington.ischool.commoncents.commoncents.Models;

import java.util.List;

/**
 * Created by iguest on 3/5/17.
 */

public class User {

    private String userName;
    private List<Event> eventList;
    private String email;
    private String phoneNumber;

    public User(String userName, List<Event> eventList, String email, String phoneNumber) {
        this.userName = userName;
        this.eventList = eventList;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    //----------------------------------------------------------------------------------------------
    // Getters
    //----------------------------------------------------------------------------------------------

    //User's name
    public String getUserName() {
        return userName;
    }

    //User's list of events
    public List<Event> getEventList() {
        return eventList;
    }

    //User's email address
    public String getEmail() {
        return email;
    }

    //User's phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
