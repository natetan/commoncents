package edu.washington.ischool.commoncents.commoncents.models;

/**
 * Created by iguest on 3/5/17.
 */

public class Payment {
    private User user;
    private int amount;

    public Payment() {
        // Default constructor required for Firebase calls to DataSnapshot.getValue(Payment.class)
    }

    public Payment(User user, int amount) {
        this.user = user;
        this.amount = amount;
    }

    //----------------------------------------------------------------------------------------------
    // Getters
    //----------------------------------------------------------------------------------------------

    //User associated with payment
    public User getUser() {
        return user;
    }

    //Amount user paid
    public int getAmount() {
        return amount;
    }

    //Set user amount
    public void setAmount(int newAmount) {
        this.amount = newAmount;
    }
}
