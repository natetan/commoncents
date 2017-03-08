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
            instance.loadFriends(); // must be called *after* users are loaded
            instance.loadEvents();
        }

        return instance;
    }

    //----------------------------------------------------------------------------------------------
    // State - fields holding the current app state
    //----------------------------------------------------------------------------------------------

    User currentUser = new User("me");
    Event currentEvent;

    //----------------------------------------------------------------------------------------------
    // Data - fields holding all data
    //----------------------------------------------------------------------------------------------

    List<Friend> friends = new ArrayList<>();
    List<User> users = new ArrayList<>();
    List<Event> events = new ArrayList<>();

    //----------------------------------------------------------------------------------------------
    // Getters - for clients to get data from the repo
    //----------------------------------------------------------------------------------------------

    public List<Friend> getFriends() {
        return friends;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Event> getEvents() {
        return events;
    }

    public Event getCurrentEvent() {
        return currentEvent;
    }

    //----------------------------------------------------------------------------------------------
    // Setters - for the client to set data in repo
    //----------------------------------------------------------------------------------------------

    public void setCurrentEvent(Event e) {
        currentEvent = e;
    }

    //----------------------------------------------------------------------------------------------
    // Loaders - for the repo to get data from external sources
    //----------------------------------------------------------------------------------------------

    private void loadUsers() {
        users = new ArrayList<>();

        users.add(new User("Hamzah"));
        users.add(new User("Hai"));
        users.add(new User("Yulong"));
        users.add(new User("Irene"));
        users.add(new User("Keegan"));

        // TODO emit broadcast Repo Updated - New Data - Users
    }

    /**
     * Note: must be called after loadUsers!
     */
    private void loadFriends() {
        friends = new ArrayList<>();

        for (User user : users) {
            friends.add(new Friend(currentUser, user));
        }

        // TODO emit broadcast Repo Updated - New Data - Friends
    }

    private void loadEvents() {
        events = new ArrayList<>();

        List<LineItem> cupcakeItems = new ArrayList<>();
        cupcakeItems.add(new LineItem("cupcake 1", 10));
        cupcakeItems.add(new LineItem("cupcake 2", 20));
        events.add(new Event("Cupcake Party", new Date(), "It's a cupcake party dude!", friends, cupcakeItems));

        List<LineItem> birthdayPartyItems = new ArrayList<>();
        birthdayPartyItems.add(new LineItem("birthday cake 1", 15));
        birthdayPartyItems.add(new LineItem("birthday cake 2", 25));
        events.add(new Event("Birthday Party", new Date(), "It's everyone's birthday!", friends, birthdayPartyItems));

        // TODO emit broadcast Repo Updated - New Data - Events
    }
}
