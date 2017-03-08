package edu.washington.ischool.commoncents.commoncents.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import edu.washington.ischool.commoncents.commoncents.AppState;
import edu.washington.ischool.commoncents.commoncents.models.Event;
import edu.washington.ischool.commoncents.commoncents.models.LineItem;
import edu.washington.ischool.commoncents.commoncents.models.User;

/**
 * Created by keegomyneego on 3/4/17.
 */

public class SplitByItemsLineAdapter extends RecyclerView.Adapter<SplitByItemsLineAdapter.ViewHolder> {

    private final String TAG = "SPLIT_ITEMS_ADAPTER";

    private List<LineItem> lineItemList;
    private int itemLayoutId;
    private int lineItemId;
    private int priceId;
    private int removeLineItemId;
    private Event currentEvent;
    private Map<User, List<LineItem>> pairings;

    private Listener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView lineItemName;
        public TextView price;
        public Button removeLineItem;

        public ViewHolder(View itemView, TextView lineItemName, TextView price, Button removeLineItem) {
            super(itemView);
            this.lineItemName = lineItemName;
            this.price = price;
            this.removeLineItem = removeLineItem;
        }
    }

    public SplitByItemsLineAdapter(Listener listener, Event currentEvent, int itemLayoutId, int lineItemId, int priceId, int removeLineItemId) {
        this.listener = listener;
        this.itemLayoutId = itemLayoutId;
        this.lineItemId = lineItemId;
        this.priceId = priceId;
        this.removeLineItemId = removeLineItemId;
        this.currentEvent = currentEvent;
        this.lineItemList = currentEvent.getLineItems(); //initialize lineItem list to be edited later
    }

    @Override
    public SplitByItemsLineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false);
        TextView lineItem = (TextView) view.findViewById(lineItemId);
        TextView price = (TextView) view.findViewById(priceId);
        Button removeLineItem = (Button) view.findViewById(removeLineItemId);
        return new SplitByItemsLineAdapter.ViewHolder(view, lineItem, price, removeLineItem);
    }

    @Override
    public void onBindViewHolder(SplitByItemsLineAdapter.ViewHolder holder, int position) {
        final int index = position;
        final LineItem thisLineItem = lineItemList.get(position);

        holder.lineItemName.setText(thisLineItem.getName());
        int priceInCents = thisLineItem.getPrice();
        int dollar = priceInCents / 100;
        int cents = priceInCents % 100;
        String strCents = "";
        if (cents <= 9) {
            strCents += "0" + cents;
        } else {
            strCents += cents;
        }
        holder.price.setText(dollar + "." + strCents);

        //Button
        holder.removeLineItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            listener.onLineItemClicked(view, index);
            }

        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get most updated pairings
                pairings = AppState.getCurrentState().getCurrentUserLineItemPairing();
                User thisUser = AppState.getCurrentState().getCurrentUser();
                if (thisUser == null) {
                    //don't do anything
                } else {
                    //a USER is selected
                    for (User u : pairings.keySet()) {
                        if (pairings.get(u).contains(thisLineItem)) {
                            pairings.get(u).remove(thisLineItem);
                        }
                    }
                    //key is already added when user is created
                    LineItem thisLineItem = lineItemList.get(index);
                    if (!pairings.get(thisUser).contains(thisLineItem) ) {
                        pairings.get(thisUser).add(thisLineItem);
                    } else {
                        pairings.get(thisUser).remove(thisLineItem);
                    }
                }
            }
        });
    }

    public interface Listener {
        void onLineItemClicked(View view, int index);
    }


    public void addToLineItemList(LineItem li) {
        this.lineItemList.add(li);
        notifyDataSetChanged();
        Log.e(TAG, lineItemList.toString());
    }

    public void removeFromLineItemList(int index) {
        LineItem thisLineItem = this.lineItemList.get(index);
        //remove from the line item list
        this.lineItemList.remove(thisLineItem);
        //remove all occurrences of this line item
        pairings = AppState.getCurrentState().getCurrentUserLineItemPairing();
        if (!pairings.keySet().isEmpty()) {
            for (User u: pairings.keySet()) {
                if (pairings.get(u).contains(thisLineItem)) {
                    pairings.get(u).remove(thisLineItem);
                }
            }
        }
        notifyDataSetChanged();
        Log.e(TAG, lineItemList.toString());
    }

    @Override
    public int getItemCount() {
        return lineItemList.size();
    }
}
