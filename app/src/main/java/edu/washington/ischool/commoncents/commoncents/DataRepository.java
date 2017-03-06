package edu.washington.ischool.commoncents.commoncents;

import java.util.ArrayList;
import java.util.List;

import edu.washington.ischool.commoncents.commoncents.Models.Friend;
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
        }

        return instance;
    }

    //----------------------------------------------------------------------------------------------
    // Data - fields holding all data
    //----------------------------------------------------------------------------------------------

    List<Friend> friends = new ArrayList<>();
    List<User> users = new ArrayList<>();

    //----------------------------------------------------------------------------------------------
    // Getters - for clients to get data from the repo
    //----------------------------------------------------------------------------------------------

    public List<Friend> getFriends() {
        return friends;
    }

    public List<User> getUsers() {
        return users;
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

    private void loadUsers() {
        friends = new ArrayList<>();

        friends.add(new Friend("Hamzah"));
        friends.add(new Friend("Hai"));
        friends.add(new Friend("Yulong"));
        friends.add(new Friend("Irene"));
        friends.add(new Friend("Keegan"));

        // TODO emit broadcast Repo Updated - New Friends Data
    }
}
