package edu.washington.ischool.commoncents.commoncents.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by iguest on 3/5/17.
 */

public class FriendsInEventAdapter extends RecyclerView.Adapter<FriendsInEventAdapter.ViewHolder> {
    private ArrayList<String> friendsInEvent; // Friends gets saved to be passed into another object later.
    private int itemLayout;
    private int nameId;
    private int percentageId;
    private int amountId;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView percentage;
        public TextView amount;

        public ViewHolder(View itemView, TextView name, TextView percentage, TextView amount) {
            super(itemView);
            this.name = name;
            this.percentage = percentage;
            this.amount = amount;
        }
    }

    public FriendsInEventAdapter(int itemLayout, int nameId, int percentageId, int amountId) {
        this.itemLayout = itemLayout;
        this.friendsInEvent = new ArrayList<>();
        this.nameId = nameId;
        this.percentageId = percentageId;
        this.amountId = amountId;
    }

    @Override
    public FriendsInEventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        TextView name = (TextView) itemView.findViewById(nameId);
        //NEED TO GET IDs by passing in activity  when creating adapter.
        TextView percentage = (TextView) itemView.findViewById(percentageId);
        TextView amount = (TextView) itemView.findViewById(amountId);

        return new ViewHolder(itemView, name, percentage, amount);
    }

    @Override
    public void onBindViewHolder(FriendsInEventAdapter.ViewHolder holder, final int position) {
        holder.name.setText(friendsInEvent.get(position));
        Log.v("ON BIND", friendsInEvent.get(position));
    }

    @Override
    public int getItemCount() {
        Log.v("GET ITEM COUNT", "" + friendsInEvent.size());
        return friendsInEvent.size();

    }

    public void addToFriendsInEvent(String name) {
        this.friendsInEvent.add(name);
        notifyDataSetChanged();
        Log.v("TAG", friendsInEvent.toString());
    }
}
