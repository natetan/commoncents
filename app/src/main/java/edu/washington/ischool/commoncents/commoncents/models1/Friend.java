package edu.washington.ischool.commoncents.commoncents.models1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by keegomyneego on 3/5/17.
 */

public class Friend {

    private User user;
    private List<Event> sharedEvents;

    public Friend() {
        // Default constructor required for Firebase calls to DataSnapshot.getValue(Friend.class)
    }

    public Friend(User currentUser, User friend) {

        // Get shared events by calculating the intersection of both user's events
        List<Event> friendsEvents = new ArrayList<>(friend.getEventList());
        friendsEvents.retainAll(currentUser.getEventList());

        this.user = friend;
        this.sharedEvents = friendsEvents;
    }

    //----------------------------------------------------------------------------------------------
    // Getters
    //----------------------------------------------------------------------------------------------

    public String getName() {
        return user.getName();
    }

    public List<Event> getSharedEvents() {
        return sharedEvents;
    }

    public int getAmountOwed() {
        return (int)((Math.random() - 0.5) * 200) * 100;
    }
}
