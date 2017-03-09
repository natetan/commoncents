package edu.washington.ischool.commoncents.commoncents.models1;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.List;

import edu.washington.ischool.commoncents.commoncents.AppState;

/**
 * Created by iguest on 3/5/17.
 */

public class User extends Indexable {

    static String TAG = "User";

    @NonNull private String name;
    @NonNull private List<Event> eventList;
    @NonNull private String email;
    @NonNull private String phoneNumber;

    public User() {
        // Default constructor required for Firebase calls to DataSnapshot.getValue(User.class)
    }

    public User(@NonNull String name) {
        this(name, new ArrayList<Event>(), "", "");
    }

    public User(@NonNull String userName, @NonNull List<Event> eventList, @NonNull String email, @NonNull String phoneNumber) {
        this.name = userName;
        this.eventList = eventList;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    //----------------------------------------------------------------------------------------------
    // Getters
    //----------------------------------------------------------------------------------------------

    //User's name
    public String getName() {
        return name;
    }

    //User's list of events
    public List<Event> getEventList() {
        return eventList;
    }

    //User's email address
    public String getEmail() {
        return email;
    }

    //User's phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Exclude
    public int getAmountOwed() {
        User currentUser = AppState.getCurrentState().getCurrentUser();

        if (currentUser == null || eventList == null) {
            return getRandomAmount();
        }

        int total = 0;

        for (Event event : eventList) {
            List<LineItem> items = event.getLineItems();
            if (items != null) {
                Log.e(TAG, "getAmountOwed: lineItems null!");
                for (LineItem item : items) {
                    List<Payment> payments = item.getPayments();
                    if (payments != null) {
                        for (Payment payment : payments) {
                            if (payment.getUser().getName().equals(currentUser.getName())) {
                                total += payment.getAmount();
                            }

                            if (payment.getUser().getName().equals(this.getName())) {
                                total -= payment.getAmount();
                            }
                        }
                    }
                }
            }
        }

        return total;
    }

    private int getRandomAmount() {
        return (int)((Math.random() - 0.5) * 200) * 100;
    }

    //----------------------------------------------------------------------------------------------
    // Setters
    //----------------------------------------------------------------------------------------------

    //Set user's email
    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    //Set user's phone number
    public void setPhoneNumber(@NonNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        User other = (User) o;
        if (other == null) {
            if (this == null) {
                return true;
            } else {
                return false;
            }
        }
        if (this.getName().equals(other.getName())) {
            return true;
        } else {
            return false;
        }

//        if (this.getName().equals(other.getName()) && this.phoneNumber.equals(other.getPhoneNumber()) && this.getEmail().equals(other.getEmail())) {
//
//        }
    }
}
