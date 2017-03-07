package edu.washington.ischool.commoncents.commoncents;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.washington.ischool.commoncents.commoncents.Models.Event;
import edu.washington.ischool.commoncents.commoncents.Models.Friend;
import edu.washington.ischool.commoncents.commoncents.Models.LineItem;
import edu.washington.ischool.commoncents.commoncents.Models.User;

/**
 * Created by keegomyneego on 3/5/17.
 */

public class DataRepository {

    //----------------------------------------------------------------------------------------------
    // Singleton
    //----------------------------------------------------------------------------------------------

    private static DataRepository instance;



    public static DataRepository getInstance() {
        if (instance == null) {
            instance = new DataRepository();
            instance.loadUsers();
            instance.loadFriends();
            instance.loadEvents();
        }

        return instance;
    }

    //----------------------------------------------------------------------------------------------
    // Data - fields holding all data
    //----------------------------------------------------------------------------------------------

    User currentUser = new User("me");

    List<Friend> friends = new ArrayList<>();
    List<User> users = new ArrayList<>();

    List<Event> events = new ArrayList<>();

    Event currentEvent;

    //----------------------------------------------------------------------------------------------
    // Getters - for clients to get data from the repo
    //----------------------------------------------------------------------------------------------

    public List<Friend> getFriends() {
        return friends;
    }
    public List<Event> getEvents() {
        return events;
    }
    public Event getCurrentEvent() { return currentEvent; }


    //----------------------------------------------------------------------------------------------
    // Setters - for the client to set data in repo
    //----------------------------------------------------------------------------------------------
    public void setCurrentEvent(Event e) {
        currentEvent = e;
    }


    public List<User> getUsers() {
        return users;
    }

    //----------------------------------------------------------------------------------------------
    // Loaders - for the repo to get data from external sources
    //----------------------------------------------------------------------------------------------

    private void loadFriends() {
        friends = new ArrayList<>();

        for (User user : users) {
            friends.add(new Friend(currentUser, user));
        }

        // TODO emit broadcast Repo Updated - New Data - Friends
    }

    private void loadUsers() {
        users = new ArrayList<>();

        users.add(new User("Hamzah"));
        users.add(new User("Hai"));
        users.add(new User("Yulong"));
        users.add(new User("Irene"));
        users.add(new User("Keegan"));

        // TODO emit broadcast Repo Updated - New Data - Users
    }

    private void loadEvents() {
        events = new ArrayList<>();
        User me = new User("me");
        List<LineItem> lineItem = new ArrayList<LineItem>();
        lineItem.add(new LineItem("a", 10, me));

        events.add(new Event("Cupcake", new Date(), "cupcake party?", friends, lineItem));

        // TODO emit broadcast Repo Updated - New Friends Data
    }
}
