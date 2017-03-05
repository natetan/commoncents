package edu.washington.ischool.commoncents.commoncents.Models;

/**
 * Created by iguest on 3/5/17.
 */

public class LineItem {

    private String lineItemName;
    private int price;
    private User user;

    public LineItem(String lineItemName, int price, User user) {
        this.lineItemName = lineItemName;
        this.price = price;
        this.user = user;
    }

    //----------------------------------------------------------------------------------------------
    // Getters
    //----------------------------------------------------------------------------------------------
    
    //Name of the item (drink, food, etc)
    public String getLineItemName() {
        return lineItemName;
    }

    //Price or cost of the item
    public int getPrice() {
        return price;
    }

    //User associated with the item, the user that should pay for the particular item
    public User getUser() {
        return user;
    }
}
