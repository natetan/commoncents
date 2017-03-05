package edu.washington.ischool.commoncents.commoncents.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.washington.ischool.commoncents.commoncents.Models.Friend;

/**
 * Created by keegomyneego on 3/5/17.
 */

public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.ViewHolder> {

    static final String TAG = "FriendsListAdapter";

    private static final int ITEM_LAYOUT_ID = 0;
    private static final int NAME_VIEW_ID = 0;
    private static final int MONEY_VIEW_ID = 0;

    private Listener listener;
    private List<Friend> friends;

    //----------------------------------------------------------------------------------------------
    // Client Interface
    //----------------------------------------------------------------------------------------------

    /**
     * Listener to which event handling logic is delegated
     */
    public interface Listener {
        void onFriendClicked(View view, int position);
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

    public FriendsListAdapter(Listener listener, List<String> itemTitleText, List<String> itemDescriptionText, int itemLayout, int titleTextViewId, int descriptionTextViewId) {
        this.listener = listener;
        this.itemTitleText = itemTitleText;
        this.itemDescriptionText = itemDescriptionText;
        this.itemLayout = itemLayout;
        this.titleTextViewId = titleTextViewId;
        this.descriptionTextViewId = descriptionTextViewId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
