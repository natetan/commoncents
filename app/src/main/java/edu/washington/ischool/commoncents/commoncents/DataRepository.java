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
            instance.loadFriends();
            instance.loadEvents();
        }

        return instance;
    }

    //----------------------------------------------------------------------------------------------
    // Data - fields holding all data
    //----------------------------------------------------------------------------------------------

    List<Friend> friends = new ArrayList<>();

    List<Event> events = new ArrayList<>();

    //----------------------------------------------------------------------------------------------
    // Getters - for clients to get data from the repo
    //----------------------------------------------------------------------------------------------

    public List<Friend> getFriends() {
        return friends;
    }
    public List<Event> getEvents() {
        return events;
    }

    //----------------------------------------------------------------------------------------------
    // Loaders - for the repo to get data from external sources
    //----------------------------------------------------------------------------------------------

    private void loadFriends() {
        friends = new ArrayList<>();

        friends.add(new Friend("Hamzah"));
        friends.add(new Friend("Hai"));
        friends.add(new Friend("Yulong"));
        friends.add(new Friend("Irene"));
        friends.add(new Friend("Keegan"));

        // TODO emit broadcast Repo Updated - New Friends Data
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
