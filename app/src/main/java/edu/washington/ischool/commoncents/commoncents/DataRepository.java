package edu.washington.ischool.commoncents.commoncents;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.washington.ischool.commoncents.commoncents.models.Event;
import edu.washington.ischool.commoncents.commoncents.models.Friend;
import edu.washington.ischool.commoncents.commoncents.models.Indexable;
import edu.washington.ischool.commoncents.commoncents.models.LineItem;
import edu.washington.ischool.commoncents.commoncents.models.User;

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

    private Map<String, User> users = new HashMap<>();
    private Map<String, Event> events = new HashMap<>();

    private List<Friend> friends = new ArrayList<>();

    //----------------------------------------------------------------------------------------------
    // Getters - for clients to get data from the repo
    //----------------------------------------------------------------------------------------------

    public List<Friend> getFriends() {
        return friends;
    }

    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    public List<Event> getEvents() {
        return new ArrayList<>(events.values());
    }

    //----------------------------------------------------------------------------------------------
    // Observers - for the client to watch data in repo
    //----------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------
    // Mutators - for the client to modify data in repo
    //----------------------------------------------------------------------------------------------

    public void addEvent(Event newEvent) {
        events.put(newEvent.getIndexKey(), newEvent);
        databaseReference.child("events").child(newEvent.getIndexKey()).setValue(newEvent);
    }

    public void addUser(User newUser) {
        users.put(newUser.getIndexKey(), newUser);
        databaseReference.child("users").child(newUser.getIndexKey()).setValue(newUser);
    }

    public void deleteEvent(String indexKey) {
        databaseReference.child("events").child(indexKey).removeValue();
    }

    public void deleteUser(String indexKey) {
        databaseReference.child("users").child(indexKey).removeValue();
    }

    //----------------------------------------------------------------------------------------------
    // Loaders - for the repo to get data from external sources
    //----------------------------------------------------------------------------------------------

    private void loadUsers() {
        users = new HashMap<>();

        // Use Firebase to populate the list.
        databaseReference.child("users").addChildEventListener(getChildEventListener(User.class, users));
    }

    private void loadEvents() {
        events = new HashMap<>();

        // Use Firebase to populate the list.
        databaseReference.child("events").addChildEventListener(getChildEventListener(Event.class, events));
    }

    /**
     * Note: must be called after loadUsers!
     */
    private void loadFriends() {
        friends = new ArrayList<>();

        for (User user : getUsers()) {
            friends.add(new Friend(AppState.getCurrentState().getCurrentUser(), user));
        }

        // TODO emit broadcast Repo Updated - New Data - Friends
    }

    //----------------------------------------------------------------------------------------------
    // Developer Methods
    //----------------------------------------------------------------------------------------------

    private List<String> mockUserIds = new ArrayList<>();
    private List<String> mockEventIds = new ArrayList<>();

    public void addMockData() {
        addMockUsers();
        addMockEvents();
    }

    public void deleteMockData() {
        for (String userId : mockUserIds) {
            deleteUser(userId);
        }

        for (String eventId : mockEventIds) {
            deleteEvent(eventId);
        }
    }

    public void clearDB() {
        databaseReference.child("users").removeValue();
        databaseReference.child("events").removeValue();
    }

    // Adds us into the database as defaults
    private void addMockUsers() {
        String[] newUserNames = new String[] {
                "Hamzah",
                "Hai",
                "Yulong",
                "Irene",
                "Keegan"
        };

        for (String name: newUserNames) {
            User newUser = new User(name);
            mockUserIds.add(newUser.getIndexKey());
            addUser(newUser);
        }
    }

    // Adds bs into the database as defaults
    private void addMockEvents() {

        User currentUser = AppState.getCurrentState().getCurrentUser();

        Event cupcakeEvent = new Event(
                "Cupcake Party",
                new Date(),
                "It's a cupcake party dude!",
                new ArrayList<>(Arrays.asList(
                        currentUser
                )),
                new ArrayList<>(Arrays.asList(
                        new LineItem("cupcake 1", 10),
                        new LineItem("cupcake 2", 20)
                ))
        );
        mockEventIds.add(cupcakeEvent.getIndexKey());
        addEvent(cupcakeEvent);

        Event birthdayPartyEvent = new Event(
                "Birthday Party",
                new Date(),
                "It's everyone's birthday!",
                new ArrayList<>(Arrays.asList(
                        currentUser
                )),
                new ArrayList<>(Arrays.asList(
                        new LineItem("birthday cake 1", 15),
                        new LineItem("birthday cake 2", 25)
                ))
        );
        mockEventIds.add(birthdayPartyEvent.getIndexKey());
        addEvent(birthdayPartyEvent);
    }

    //----------------------------------------------------------------------------------------------
    // Private Helpers
    //----------------------------------------------------------------------------------------------

    private <T extends Indexable> ChildEventListener getChildEventListener(final Class<T> DataType, final Map<String, T> dataStore) {

        return new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                T newData = dataSnapshot.getValue(DataType);
                Log.i("Firebase", "onChildAdded: (" + DataType.getSimpleName() + ")");
                Log.i("Firebase", "   key: " + newData.getIndexKey());
                Log.i("Firebase", "   newValue: " + dataSnapshot.toString());
                dataStore.put(newData.getIndexKey(), newData);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                T changedData = dataSnapshot.getValue(DataType);
                Log.i("Firebase", "onChildChanged: (" + DataType.getSimpleName() + ")");
                Log.i("Firebase", "   key: " + changedData.getIndexKey());
                Log.i("Firebase", "   newValue: " + dataSnapshot.toString());
                dataStore.remove(changedData.getIndexKey());
                dataStore.put(changedData.getIndexKey(), changedData);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                T removedData = dataSnapshot.getValue(DataType);
                Log.i("Firebase", "onChildRemoved: (" + DataType.getSimpleName() + ")");
                Log.i("Firebase", "   key: " + removedData.getIndexKey());
                Log.i("Firebase", "   newValue: " + dataSnapshot.toString());
                dataStore.remove(removedData);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
    }
}
