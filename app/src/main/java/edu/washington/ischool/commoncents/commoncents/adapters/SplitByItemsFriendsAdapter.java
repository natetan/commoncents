package edu.washington.ischool.commoncents.commoncents.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.washington.ischool.commoncents.commoncents.AppState;
import edu.washington.ischool.commoncents.commoncents.models.Event;
import edu.washington.ischool.commoncents.commoncents.models.LineItem;
import edu.washington.ischool.commoncents.commoncents.models.User;

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
    private User selectedUser;
    private Map<User, List<LineItem>> pairings = new HashMap<User, List<LineItem>>();


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

        //set the userLineItemPairing
        AppState.getCurrentState().setUserLineItemPairing(this.pairings);
        this.pairings = AppState.getCurrentState().getCurrentUserLineItemPairing();

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
        final User thisUser = usersList.get(position);


        holder.user.setText(thisUser.getName());

        //Button
        holder.removeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onUserClicked(view, index);
            }

        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppState.getCurrentState().selectCurrentUser(thisUser);
            }
        });
    }

    public interface Listener {
        void onUserClicked(View view, int index);
    }


    public void addToUsersList(User u) {
        this.usersList.add(u);
        this.pairings.put(u, new ArrayList<LineItem>());
        notifyDataSetChanged();
        Log.e(TAG, pairings.size() + "/" + usersList.size());
        Log.e(TAG, usersList.get(0).getName());
    }

    public void removeFromUsersList(int index) {
        User thisUser = this.usersList.get(index);
        this.usersList.remove(thisUser);
        this.pairings.remove(thisUser);
        notifyDataSetChanged();
        Log.e(TAG, usersList.toString());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}
