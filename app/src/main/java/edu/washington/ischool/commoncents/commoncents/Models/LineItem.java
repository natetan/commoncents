package edu.washington.ischool.commoncents.commoncents.Models;

import java.util.List;

/**
 * Created by iguest on 3/5/17.
 */

public class LineItem {

    private String name;
    private int price;
    private User user;
    private List<Payment> payments;

    public LineItem(String lineItemName, int price, User user) {
        this.name = lineItemName;
        this.price = price;
        this.user = user;
    }

    //----------------------------------------------------------------------------------------------
    // Getters
    //----------------------------------------------------------------------------------------------

    //Name of the item (drink, food, etc)
    public String getName() {
        return name;
    }

    //Price or cost of the item
    public int getPrice() {
        return price;
    }

    //User associated with the item, the user that should pay for the particular item
    public User getUser() {
        return user;
    }

    //List of payments for a single line item (multiple users splitting a single line item) if applicable, otherwise is just a list with one payment
    public List<Payment> getPayments() {
        return payments;
    }
}
