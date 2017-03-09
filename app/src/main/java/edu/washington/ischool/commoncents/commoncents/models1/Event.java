package edu.washington.ischool.commoncents.commoncents.models1;

import android.util.Log;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.washington.ischool.commoncents.commoncents.AppState;

/**
 * Created by iguest on 3/5/17.
 */

public class Event extends Indexable {

    static String TAG = "Event";

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

    @Exclude
    // Gets the amount of money owed to the current user from this event
    public int getAmountOwed(User user) {

        User currentUser = AppState.getCurrentState().getCurrentUser();

        if (currentUser == null) {
            return getRandomAmount();
        }

        int total = 0;
        List<LineItem> items = getLineItems();
        if (items == null) {
            Log.e(TAG, "getAmountOwed: lineItems null!");
            return getRandomAmount();
        }

        for (LineItem item : items) {
            List<Payment> payments = item.getPayments();
            if (payments == null) {
                Log.e(TAG, "getAmountOwed: lineItems null!");
                return getRandomAmount();
            }

            for (Payment payment : payments) {
                if (payment.getUser().getName().equals(user.getName())) {
                    total += payment.getAmount();
                }
            }
        }

        return total;
    }

    private int getRandomAmount() {
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
