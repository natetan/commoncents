package edu.washington.ischool.commoncents.commoncents.Models;

import java.util.Date;
import java.util.List;

/**
 * Created by iguest on 3/5/17.
 */

public class Event {
    private String name;
    private Date date;
    private String description;
    private List<Friend> friendsInvolved;
    private List<LineItem> lineItems;

    public Event(String eventName, Date eventDate, String eventDescription, List<Friend> friendsInvolved, List<LineItem> lineItems) {
        this.name = eventName;
        this.date = eventDate;
        this.description = eventDescription;
        this.friendsInvolved = friendsInvolved;
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
    public List<Friend> getFriendsInvolved() {
        return friendsInvolved;
    }

    //List of items involved with this event
    public List<LineItem> getLineItems() {
        return lineItems;
    }
}
