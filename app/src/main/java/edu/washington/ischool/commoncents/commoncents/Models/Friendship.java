package edu.washington.ischool.commoncents.commoncents.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by keegomyneego on 3/8/17.
 */

public class Friendship {

    private List<Event> sharedEvents;

    public Friendship(User currentUser, User friend) {

        // Get shared events by calculating the intersection of both user's events
        List<Event> friendsEvents = new ArrayList<>(friend.getEventList());
        friendsEvents.retainAll(currentUser.getEventList());

        this.sharedEvents = friendsEvents;
    }

    //----------------------------------------------------------------------------------------------
    // Getters
    //----------------------------------------------------------------------------------------------

    public List<Event> getSharedEvents() {
        return sharedEvents;
    }

    public int getAmountOwed() {
        return (int)((Math.random() - 0.5) * 200) * 100;
    }
}