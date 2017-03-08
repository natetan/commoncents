package edu.washington.ischool.commoncents.commoncents.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by iguest on 3/5/17.
 */

public class Event {
    private String name;
    private Date date;
    private String description;
    private List<User> usersInvolved;
    private List<LineItem> lineItems;

    public Event(String eventName, Date eventDate) {
        this(eventName, eventDate, "", new ArrayList<User>(), new ArrayList<LineItem>());
    }

    public Event(String eventName, Date eventDate, String eventDescription, List<User> usersInvolved, List<LineItem> lineItems) {
        this.name = eventName;
        this.date = eventDate;
        this.description = eventDescription;
        this.usersInvolved = usersInvolved;
        this.lineItems = lineItems;
    }


    //----------------------------------------------------------------------------------------------
    // Getters
    //----------------------------------------------------------------------------------------------

    //Name of the event
    public String getName() {
        return name;
    }

    //Date of the event
    public Date getDate() {
        return date;
    }

    //Description for the event
    public String getDescription() {
        return description;
    }

    //List of friends involved with the event
    public List<User> getUsersInvolved() {
        return usersInvolved;
    }

    //List of items involved with this event
    public List<LineItem> getLineItems() {
        return lineItems;
    }
}
