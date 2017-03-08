package edu.washington.ischool.commoncents.commoncents;

import android.support.annotation.Nullable;

import java.util.List;

import edu.washington.ischool.commoncents.commoncents.Models.Event;
import edu.washington.ischool.commoncents.commoncents.Models.Friend;
import edu.washington.ischool.commoncents.commoncents.Models.User;

/**
 * Created by keegomyneego on 3/7/17.
 */

public class AppState {

    //----------------------------------------------------------------------------------------------
    // Singleton Pattern
    //----------------------------------------------------------------------------------------------

    private static AppState currentState;

    public static AppState getCurrentState() {
        if (currentState == null) {
            currentState = new AppState();
        }

        return currentState;
    }

    //----------------------------------------------------------------------------------------------
    // State - fields holding the current app state
    //----------------------------------------------------------------------------------------------

    private Friend selectedFriend;
    private Event selectedEvent;
    private User currentUser = new User("me");

    //----------------------------------------------------------------------------------------------
    // Getters
    //----------------------------------------------------------------------------------------------

    public Event getSelectedEvent() {
        return selectedEvent;
    }

    @Nullable
    public Friend getSelectedFriend() {
        return selectedFriend;
    }

    @Nullable
    public User getCurrentUser() {
        return currentUser;
    }

    //----------------------------------------------------------------------------------------------
    // Setters
    //----------------------------------------------------------------------------------------------

    public void selectEvent(Event event) {
        selectedEvent = event;
    }

    public void selectFriend(Friend friend) {
        selectedFriend = friend;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }
}
