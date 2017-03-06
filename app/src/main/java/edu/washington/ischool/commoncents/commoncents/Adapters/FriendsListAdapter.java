package edu.washington.ischool.commoncents.commoncents.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.washington.ischool.commoncents.commoncents.DataRepository;
import edu.washington.ischool.commoncents.commoncents.Models.Friend;
import edu.washington.ischool.commoncents.commoncents.R;

/**
 * Created by keegomyneego on 3/5/17.
 */

public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.ViewHolder> {

    static final String TAG = "FriendsListAdapter";

    private static final int ITEM_LAYOUT_ID = R.layout.item_friend_list_item;
    private static final int NAME_VIEW_ID = R.id.item_title;
    private static final int MONEY_VIEW_ID = R.id.item_description;

    private Listener listener;
    private List<Friend> friends = new ArrayList<>();

    //----------------------------------------------------------------------------------------------
    // Client Interface
    //----------------------------------------------------------------------------------------------

    /**
     * Listener to which event handling logic is delegated
     */
    public interface Listener {
        void onFriendClicked(View view, Friend friend);
    }

    /**
     * Custom view holder for this adapter
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameView;
        public TextView moneyView;

        public ViewHolder(View itemView, TextView nameView, TextView moneyView) {
            super(itemView);
            this.nameView = nameView;
            this.moneyView = moneyView;
        }
    }

    //----------------------------------------------------------------------------------------------
    // Adapter Implementation
    //----------------------------------------------------------------------------------------------

    public FriendsListAdapter(Listener listener) {
        this.listener = listener;

        // Listen to calls to notifyDataSetChanged() so we can keep our local data up to date
        this.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                FriendsListAdapter.this.updateDataSet();
            }
        });

        updateDataSet();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate container view for this item
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(ITEM_LAYOUT_ID, parent, false);
        // Get important views for this item
        TextView nameTextView = (TextView) itemView.findViewById(NAME_VIEW_ID);
        TextView moneyTextView = (TextView) itemView.findViewById(MONEY_VIEW_ID);

        return new ViewHolder(itemView, nameTextView, moneyTextView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Friend friend = friends.get(position);

        // Set the text for this cell based on its position
        holder.nameView.setText(friend.getName());
        holder.moneyView.setText("$100");

        // Set this cell's onClickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Delegate click logic to listener
                listener.onFriendClicked(view, friend);
            }
        });
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    // Update our local collection of friends with those from the repo
    private void updateDataSet() {
        friends = DataRepository.getInstance().getFriends();
    }
}
