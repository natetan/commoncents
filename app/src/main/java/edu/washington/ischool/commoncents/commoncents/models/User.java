package edu.washington.ischool.commoncents.commoncents.models;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iguest on 3/5/17.
 */

public class User extends Indexable {

    @NonNull private String name;
    @NonNull private List<Event> eventList;
    @NonNull private String email;
    @NonNull private String phoneNumber;

    public User() {
        // Default constructor required for Firebase calls to DataSnapshot.getValue(User.class)
    }

    public User(@NonNull String name) {
        this(name, new ArrayList<Event>(), "", "");
    }

    public User(@NonNull String userName, @NonNull List<Event> eventList, @NonNull String email, @NonNull String phoneNumber) {
        this.name = userName;
        this.eventList = eventList;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    //----------------------------------------------------------------------------------------------
    // Getters
    //----------------------------------------------------------------------------------------------

    //User's name
    public String getName() {
        return name;
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

    //Set user's email
    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    //Set user's phone number
    public void setPhoneNumber(@NonNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        User other = (User) o;
        if (other == null) {
            if (this == null) {
                return true;
            } else {
                return false;
            }
        }
        if (this.getName().equals(other.getName())) {
            return true;
        } else {
            return false;
        }

//        if (this.getName().equals(other.getName()) && this.phoneNumber.equals(other.getPhoneNumber()) && this.getEmail().equals(other.getEmail())) {
//
//        }
    }
}
