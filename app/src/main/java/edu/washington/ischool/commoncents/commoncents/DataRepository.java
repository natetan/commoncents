package edu.washington.ischool.commoncents.commoncents;

import java.util.ArrayList;
import java.util.List;

import edu.washington.ischool.commoncents.commoncents.Models.Friend;

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
        }

        return instance;
    }

    //----------------------------------------------------------------------------------------------
    // Data - fields holding all data
    //----------------------------------------------------------------------------------------------

    List<Friend> friends = new ArrayList<>();

    //----------------------------------------------------------------------------------------------
    // Getters - for clients to get data from the repo
    //----------------------------------------------------------------------------------------------

    public List<Friend> getFriends() {
        return friends;
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
}
