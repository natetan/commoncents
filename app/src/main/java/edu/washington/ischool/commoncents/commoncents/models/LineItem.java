package edu.washington.ischool.commoncents.commoncents.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iguest on 3/5/17.
 */

public class LineItem {

    private String name;
    private int price;
    private List<Payment> payments; //associate user at the Payment level

    //constructor for split by items
    //user will be added as line items are associated
    public LineItem(String lineItemName, int price) {
        this.name = lineItemName;
        this.price = price;
        this.payments = new ArrayList<>();
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


    //List of payments for a single line item (multiple users splitting a single line item) if applicable, otherwise is just a list with one payment
    public List<Payment> getPayments() {
        return payments;
    }
}
