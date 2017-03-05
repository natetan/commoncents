package edu.washington.ischool.commoncents.commoncents.Models;

import java.util.Date;
import java.util.List;

/**
 * Created by iguest on 3/5/17.
 */

public class Event {
    private String eventName;
    private Date eventDate;
    private String eventDescription;
    private List<Friend> friendsInvolved;
    private int owe;
    private List<LineItem> lineItems;

    public Event(String eventName, Date eventDate, String eventDescription, List<Friend> friendsInvolved, int owe, List<LineItem> lineItems) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventDescription = eventDescription;
        this.friendsInvolved = friendsInvolved;
        this.owe = owe;
        this.lineItems = lineItems;
    }


    //----------------------------------------------------------------------------------------------
    // Getters
    //----------------------------------------------------------------------------------------------

    //Name of the event
    public String getEventName() {
        return eventName;
    }

    //Date of the event
    public Date getEventDate() {
        return eventDate;
    }

    //Description for the event
    public String getEventDescription() {
        return eventDescription;
    }

    //List of friends involved with the event
    public List<Friend> getFriendsInvolved() {
        return friendsInvolved;
    }

    //How much you owe or are owed for the event
    public int getOwe() {
        return owe;
    }

    //List of items involved with this event
    public List<LineItem> getLineItems() {
        return lineItems;
    }
}
