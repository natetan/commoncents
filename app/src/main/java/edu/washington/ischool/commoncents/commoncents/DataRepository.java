package edu.washington.ischool.commoncents.commoncents;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
    // Singleton Pattern
    //----------------------------------------------------------------------------------------------

    private static DataRepository instance;

    public static DataRepository getInstance() {
        if (instance == null) {
            instance = new DataRepository();
        }

        return instance;
    }

    private DataRepository() {

        // Initialize firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();

        loadUsers();
        loadFriends(); // must be called *after* users are loaded
        loadEvents();
    }

    //----------------------------------------------------------------------------------------------
    // Data - fields holding all data
    //----------------------------------------------------------------------------------------------

    // Firebase database
    private DatabaseReference databaseReference;

    private List<Friend> friends = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Event> events = new ArrayList<>();

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

    //----------------------------------------------------------------------------------------------
    // Mutators - for the client to modify data in repo
    //----------------------------------------------------------------------------------------------

    public void addEvent(Event newEvent) {
        events.add(newEvent);
    }

    public void addFriend(Friend newFriend) {
        friends.add(newFriend);
    }

    public void addUser(User newUser) {
        users.add(newUser);
        databaseReference.child("users").push().child(newUser.getName()).setValue(newUser);
    }

    //----------------------------------------------------------------------------------------------
    // Loaders - for the repo to get data from external sources
    //----------------------------------------------------------------------------------------------

    private void loadUsers() {
        users = new ArrayList<>();

        // Use Firebase to populate the list.
        databaseReference.child("users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("loadUsers", dataSnapshot.getValue().toString());

                try {
                    User u = (User) dataSnapshot.getValue();
                    Log.d("GOT USER", u.toString());
                } catch (Exception e) {

                }

//                users.add(new User())
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                users.remove((String) dataSnapshot.child("title").getValue());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


//        users.add(new User("Hamzah"));
//        users.add(new User("Hai"));
//        users.add(new User("Yulong"));
//        users.add(new User("Irene"));
//        users.add(new User("Keegan"));

        // Adds us into the database as defaults
        databaseReference.child("users").child("Hamzah").setValue(new User("Hamzah"));
        databaseReference.child("users").child("Hai").setValue(new User("Hai"));
        databaseReference.child("users").child("Yulong").setValue(new User("Yulong"));
        databaseReference.child("users").child("Irene").setValue(new User("Irene"));
        databaseReference.child("users").child("Keegan").setValue(new User("Keegan"));


        // TODO emit broadcast Repo Updated - New Data - Users
    }

    /**
     * Note: must be called after loadUsers!
     */
    private void loadFriends() {
        friends = new ArrayList<>();

        for (User user : users) {
            friends.add(new Friend(AppState.getCurrentState().getCurrentUser(), user));
        }

        // TODO emit broadcast Repo Updated - New Data - Friends
    }

    private void loadEvents() {
        events = new ArrayList<>();

        User currentUser = AppState.getCurrentState().getCurrentUser();
        List<User> users = new ArrayList<>();
        users.add(currentUser);

        List<LineItem> cupcakeItems = new ArrayList<>();
        cupcakeItems.add(new LineItem("cupcake 1", 10));
        cupcakeItems.add(new LineItem("cupcake 2", 20));
        events.add(new Event("Cupcake Party", new Date(), "It's a cupcake party dude!", users, cupcakeItems));

        List<LineItem> birthdayPartyItems = new ArrayList<>();
        birthdayPartyItems.add(new LineItem("birthday cake 1", 15));
        birthdayPartyItems.add(new LineItem("birthday cake 2", 25));
        events.add(new Event("Birthday Party", new Date(), "It's everyone's birthday!", users, birthdayPartyItems));

        // TODO emit broadcast Repo Updated - New Data - Events
    }
}
