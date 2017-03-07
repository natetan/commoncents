package edu.washington.ischool.commoncents.commoncents.Adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

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
    private static final int PROFILE_PIC_VIEW_ID = R.id.profile_picture;

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
        public ImageView profilePicView;

        public ViewHolder(View itemView, TextView nameView, TextView moneyView, ImageView profilePicView) {
            super(itemView);
            this.nameView = nameView;
            this.moneyView = moneyView;
            this.profilePicView = profilePicView;
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
        TextView nameView = (TextView) itemView.findViewById(NAME_VIEW_ID);
        TextView moneyView = (TextView) itemView.findViewById(MONEY_VIEW_ID);
        ImageView profilePicView = (ImageView) itemView.findViewById(PROFILE_PIC_VIEW_ID);

        return new ViewHolder(itemView, nameView, moneyView, profilePicView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Friend friend = friends.get(position);
        int centsOwed = friend.getAmountOwed();
        String amountOwed;

        if (centsOwed >= 0) {
            amountOwed = (centsOwed / 100) + "." + (centsOwed % 100);
        } else {
            amountOwed = "(" + (-centsOwed / 100) + "." + (-centsOwed % 100) + ")";
        }


        // Set the view properties for this cell
        holder.nameView.setText(friend.getName());
        holder.moneyView.setText(amountOwed);
        holder.profilePicView.setImageDrawable(generateProfilePic(friend));

        // Set this cell's onClickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Delegate click logic to listener
                listener.onFriendClicked(view, friend);
            }
        });
    }

    private TextDrawable generateProfilePic(Friend friend) {

        // Generate arbitrary color based on friends name
        float h = (float)Math.abs(friend.getName().hashCode()) % 360;
        float s = 0.5f;
        float v = 0.8f;

        // Calculate the initials for this friends name
        String initials = friend.getName().substring(0, 2);

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(initials, Color.HSVToColor(new float[]{ h, s, v }));

        return drawable;
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
