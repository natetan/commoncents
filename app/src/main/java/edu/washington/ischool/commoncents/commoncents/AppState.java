package edu.washington.ischool.commoncents.commoncents;

import android.support.annotation.Nullable;

import java.util.List;
import java.util.Map;

import edu.washington.ischool.commoncents.commoncents.models1.Event;
import edu.washington.ischool.commoncents.commoncents.models1.Friend;
import edu.washington.ischool.commoncents.commoncents.models1.LineItem;
import edu.washington.ischool.commoncents.commoncents.models1.User;

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
    private LineItem selectedLineItem;
    private Map<User, List<LineItem>> currentUserLineItemPairing; //tracks index of LineItem fromEvent object

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

    public LineItem getSelectedLineItem() {
        return selectedLineItem;
    }

    public Map<User, List<LineItem>> getCurrentUserLineItemPairing() {
        return currentUserLineItemPairing;
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

    public void selectCurrentUser(User user) {
        currentUser = user;
    }

    public void selectLineItem(LineItem lineItem) { selectedLineItem = lineItem; }

    public void setUserLineItemPairing(Map<User, List<LineItem>> pairing) { currentUserLineItemPairing = pairing; }
}
