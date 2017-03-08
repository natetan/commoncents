package edu.washington.ischool.commoncents.commoncents.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.washington.ischool.commoncents.commoncents.Models.LineItem;
import edu.washington.ischool.commoncents.commoncents.Models.Payment;
import edu.washington.ischool.commoncents.commoncents.R;

/**
 * Created by keegomyneego on 3/4/17.
 */

public class SplitItemsListAdapter extends RecyclerView.Adapter<SplitItemsListAdapter.ViewHolder> {

    private final String TAG = "SPLIT_ITEMS_ADAPTER";

    private List<LineItem> lineItemList;
    private int itemLayoutId;
    private int lineItemId;
    private int priceId;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView lineItemName;
        public TextView price;
        public TextView friendName;

        public ViewHolder(View itemView, TextView lineItemName, TextView price) {
            super(itemView);
            this.lineItemName = lineItemName;
            this.price = price;
        }
    }

    public SplitItemsListAdapter(int itemLayoutId, int lineItemId, int priceId) {
        this.itemLayoutId = itemLayoutId;
        this.lineItemId = lineItemId;
        this.priceId = priceId;
        this.lineItemList = new ArrayList<>(); //initialize lineItem list to be edited later
    }

    @Override
    public SplitItemsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false);
        TextView lineItem = (TextView) view.findViewById(lineItemId);
        TextView price = (TextView) view.findViewById(priceId);
//        TextView friend = (TextView) view.findViewById(friendId);
        return new SplitItemsListAdapter.ViewHolder(view, lineItem, price);
    }

    @Override
    public void onBindViewHolder(SplitItemsListAdapter.ViewHolder holder, int position) {
        holder.lineItemName.setText(lineItemList.get(position).getUser().getName());
    }

    public void addToLineItemList(LineItem li) {
        this.lineItemList.add(li);
        notifyDataSetChanged();
        Log.v(TAG, lineItemList.toString());
    }
    @Override
    public int getItemCount() {
        return lineItemList.size();
    }
}
