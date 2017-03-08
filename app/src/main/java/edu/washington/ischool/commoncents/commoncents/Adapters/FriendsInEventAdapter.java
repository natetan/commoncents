package edu.washington.ischool.commoncents.commoncents.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import edu.washington.ischool.commoncents.commoncents.Models.Payment;

/**
 * Created by iguest on 3/5/17.
 */

public class FriendsInEventAdapter extends RecyclerView.Adapter<FriendsInEventAdapter.ViewHolder> {
    private ArrayList<Payment> friendsInEvent; // Friends gets saved to be passed into another object later.
    private int itemLayout;
    private int nameId;
    private int percentageId;
    private int amountId;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView percentage;
        public TextView amount;

        public ViewHolder(View itemView, TextView name, TextView amount, TextView percentage) {
            super(itemView);
            this.name = name;
            this.amount = amount;
            this.percentage = percentage;
        }
    }

    public FriendsInEventAdapter(int itemLayout, int nameId, int amountId, int percentageId) {
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
        //NEED TO GET IDs by passing in activity when creating adapter.
        TextView percentage = (TextView) itemView.findViewById(percentageId);
        TextView amount = (TextView) itemView.findViewById(amountId);

        return new ViewHolder(itemView, name, amount, percentage);
    }

    @Override
    public void onBindViewHolder(FriendsInEventAdapter.ViewHolder holder, final int position) {
        holder.name.setText(friendsInEvent.get(position).getUser().getName());
        holder.amount.setText("$" + new DecimalFormat("#.##").format(friendsInEvent.get(position).getAmount() / 100.0));
        //Divide by total friends in event.
        holder.percentage.setText(new DecimalFormat("#.##").format(100.0 / friendsInEvent.size()) + "%");

        //CALCULATE PERCENTAGE HERE
        Log.v("ON BIND", friendsInEvent.get(position).getUser().getName());
        Log.v("ON BIND", "" + friendsInEvent.get(position).getAmount());

    }

    @Override
    public int getItemCount() {
        Log.v("GET ITEM COUNT", "" + friendsInEvent.size());
        return friendsInEvent.size();

    }

    public void addToFriendsInEvent(Payment payment, int total) {
        boolean splitEqually = true;

        this.friendsInEvent.add(payment);

        if (splitEqually) {
            for (Payment friendsPayment: friendsInEvent ) {
                friendsPayment.setAmount(total / friendsInEvent.size());
            }
            // get # of people in friendsInEvent
            // calculate percentage
            // for each friend in friendsInEvent
            //   set percetage
            //   set dollar amount
        }

        notifyDataSetChanged();

        Log.v("TAG", friendsInEvent.toString());
    }
}
