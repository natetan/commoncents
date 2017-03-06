package edu.washington.ischool.commoncents.commoncents.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by iguest on 3/5/17.
 */

public class FriendsInEventAdapter extends RecyclerView.Adapter<FriendsInEventAdapter.ViewHolder> {
    private String[] friendsInEvent; // Friends gets saved to be passed into another object later.
    private int itemLayout;
    private int nameId;
    private int percentageId;
    private int amountId;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public EditText name;
        public EditText percentage;
        public EditText amount;

        public ViewHolder(View itemView, EditText name, EditText percentage, EditText amount) {
            super(itemView);
            this.name = name;
            this.percentage = percentage;
            this.amount = amount;
        }
    }

    public FriendsInEventAdapter(int itemLayout, int nameId, int percentageId, int amountId) {
        this.itemLayout = itemLayout;
        this.friendsInEvent = friendsInEvent;
        this.nameId = nameId;
        this.percentageId = percentageId;
        this.amountId = amountId;
    }

    @Override
    public FriendsInEventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        EditText name = (EditText) itemView.findViewById(nameId);
        //NEED TO GET IDs by passing in activity  when creating adapter.
        EditText percentage = (EditText) itemView.findViewById(percentageId);
        EditText amount = (EditText) itemView.findViewById(amountId);

        return new ViewHolder(itemView, name, percentage, amount);
    }

    @Override
    public void onBindViewHolder(FriendsInEventAdapter.ViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return friendsInEvent.length;
    }
}
