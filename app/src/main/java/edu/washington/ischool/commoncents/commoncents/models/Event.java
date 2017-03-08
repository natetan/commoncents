package edu.washington.ischool.commoncents.commoncents.models;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by iguest on 3/5/17.
 */

public class Event implements Indexable {

    private String name;
    private Date date;
    private String description;
    private List<User> usersInvolved;
    private List<LineItem> lineItems;

    public Event() {
        // Default constructor required for Firebase calls to DataSnapshot.getValue(Event.class)
    }

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
    // Indexable Implementation
    //----------------------------------------------------------------------------------------------

    @Exclude
    public String getKey() {
        return name + " - " + date.toString();
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
    public List<User> getFriendsInvolved() {
        return usersInvolved;
    }

    public List<User> getUsersInvolved() {
        return usersInvolved;
    }

    //List of items involved with this event
    public List<LineItem> getLineItems() {
            return lineItems;
    }

    // Gets the amount of money owed to the current user from this event
    public int getAmountOwed(User user) {
        // TODO implement
        return (int)((Math.random() - 0.5) * 200) * 100;
    }

    //Sets the friends involved in the event
    public void setFriendsInvolved(List<User> friendsInvolved) {
        this.usersInvolved = friendsInvolved;
    }

    //Sets the lines items in the events
    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }
}
