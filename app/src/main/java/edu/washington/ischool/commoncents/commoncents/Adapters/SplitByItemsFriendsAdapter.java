package edu.washington.ischool.commoncents.commoncents.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.washington.ischool.commoncents.commoncents.Models.Event;
import edu.washington.ischool.commoncents.commoncents.Models.LineItem;
import edu.washington.ischool.commoncents.commoncents.Models.User;

/**
 * Created by IreneW on 2017-03-07.
 */

public class SplitByItemsFriendsAdapter extends RecyclerView.Adapter<SplitByItemsFriendsAdapter.ViewHolder>{


    private final String TAG = "SPLIT_ITEMS_ADAPTER";

    private List<User> usersList;
    private int itemLayoutId;
    private int userId;
    private int removeUserId;
    private Event currentEvent;

    private SplitByItemsFriendsAdapter.Listener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView user;
        public Button removeUser;

        public ViewHolder(View itemView, TextView user, Button removeUser) {
            super(itemView);
            this.user = user;
            this.removeUser = removeUser;
        }
    }

    public SplitByItemsFriendsAdapter(SplitByItemsFriendsAdapter.Listener listener, Event currentEvent, int itemLayoutId, int userId, int removeUserId) {
        this.listener = listener;
        this.itemLayoutId = itemLayoutId;
        this.userId = userId;
        this.removeUserId = removeUserId;
        this.currentEvent = currentEvent;
        this.usersList = currentEvent.getUsersInvolved(); //initialize user list to be edited later
    }

    @Override
    public SplitByItemsFriendsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false);
        TextView user = (TextView) view.findViewById(userId);
        Button removeUser = (Button) view.findViewById(removeUserId);
        return new SplitByItemsFriendsAdapter.ViewHolder(view, user, removeUser);
    }


    @Override
    public void onBindViewHolder(SplitByItemsFriendsAdapter.ViewHolder holder, int position) {
        final int index = position;
        final User selectedUser = usersList.get(position);


        holder.user.setText(selectedUser.getName());

        //Button
        holder.removeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onUserClicked(view, index);
            }

        });
    }

    public interface Listener {
        void onUserClicked(View view, int index);
    }


    public void addToUsersList(User u) {
        this.usersList.add(u);
        notifyDataSetChanged();
        Log.e(TAG, usersList.toString());
        Log.e(TAG, usersList.get(0).getName());
    }

    public void removeFromUsersList(int index) {
        this.usersList.remove(index);
        notifyDataSetChanged();
        Log.e(TAG, usersList.toString());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}
