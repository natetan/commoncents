package edu.washington.ischool.commoncents.commoncents.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.washington.ischool.commoncents.commoncents.Models.LineItem;

/**
 * Created by keegomyneego on 3/4/17.
 */

public class SplitItemsListAdapter extends RecyclerView.Adapter<SplitItemsListAdapter.ViewHolder> {

    private final String TAG = "SPLIT_ITEMS_ADAPTER";

    private List<LineItem> lineItemList;
    private int itemLayoutId;
    private int lineItemId;
    private int priceId;
    private int removeLineItemId;

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

    public SplitItemsListAdapter(int itemLayoutId, int lineItemId, int priceId, int removeLineItemId) {
        this.itemLayoutId = itemLayoutId;
        this.lineItemId = lineItemId;
        this.priceId = priceId;
        this.removeLineItemId = removeLineItemId;
        this.lineItemList = new ArrayList<>(); //initialize lineItem list to be edited later
    }

    @Override
    public SplitItemsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false);
        TextView lineItem = (TextView) view.findViewById(lineItemId);
        TextView price = (TextView) view.findViewById(priceId);
        Button removeLineItem = (Button) view.findViewById(removeLineItemId);
        return new SplitItemsListAdapter.ViewHolder(view, lineItem, price, removeLineItem);
    }

    @Override
    public void onBindViewHolder(SplitItemsListAdapter.ViewHolder holder, int position) {
        final int index = position;
        final LineItem selectedLineItem = lineItemList.get(position);

        holder.lineItemName.setText(selectedLineItem.getName());
        int priceInCents = selectedLineItem.getPrice();
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
    }

    public interface Listener {
        void onLineItemClicked(View view, int index);
    }


    public void addToLineItemList(LineItem li) {
        this.lineItemList.add(li);
        notifyDataSetChanged();
        Log.e(TAG, lineItemList.toString());
        Log.e(TAG, lineItemList.get(0).getName());
    }

    public void removeFromLineItemList(int index) {
        this.lineItemList.remove(index);
        notifyDataSetChanged();
        Log.e(TAG, lineItemList.toString());
    }
    @Override
    public int getItemCount() {
        return lineItemList.size();
    }
}
