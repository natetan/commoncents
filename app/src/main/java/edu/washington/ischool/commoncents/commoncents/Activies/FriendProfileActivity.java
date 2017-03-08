package edu.washington.ischool.commoncents.commoncents.Activies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import edu.washington.ischool.commoncents.commoncents.Adapters.EventsForFriendAdapter;
import edu.washington.ischool.commoncents.commoncents.AppState;
import edu.washington.ischool.commoncents.commoncents.Helpers.ComponentHelper;
import edu.washington.ischool.commoncents.commoncents.Models.Event;
import edu.washington.ischool.commoncents.commoncents.Models.Friend;
import edu.washington.ischool.commoncents.commoncents.R;

public class FriendProfileActivity extends AppCompatActivity implements EventsForFriendAdapter.Listener {

    EventsForFriendAdapter adapter;

    //----------------------------------------------------------------------------------------------
    // Activity Implementation
    //----------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);

        Friend selectedFriend = AppState.getCurrentState().getSelectedFriend();

        // Exit if there's no selected friend
        if (selectedFriend == null) {
            finish();
            return;
        }

        initializeViews(selectedFriend);
        initializeLineItemList();
    }

    private void initializeViews(Friend selectedFriend) {
        // Profile pic
        ImageView profilePictureView = (ImageView) findViewById(R.id.profile_picture);
        ComponentHelper.getInstance().setProfilePicture(profilePictureView, selectedFriend, ComponentHelper.PictureType.AS_BACKGROUND);

        // Friend name
        TextView friendNameView = (TextView) findViewById(R.id.friend_name);
        friendNameView.setText(selectedFriend.getName());

        // Amount owed
        TextView friendOweAmountView = (TextView) findViewById(R.id.friend_owe_amount);
        ComponentHelper.getInstance().setOweAmount(this, friendOweAmountView, selectedFriend.getAmountOwed());
    }

    private void initializeLineItemList() {
        // Get the recycler
        RecyclerView lineItemList = (RecyclerView) findViewById(R.id.line_item_list);

        // Set some basic properties
        lineItemList.setHasFixedSize(true);

        // Use a simple linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        lineItemList.setLayoutManager(layoutManager);

        // Create the adapter
        adapter = new EventsForFriendAdapter(this, this);
        lineItemList.setAdapter(adapter);

        lineItemListUpdated();
    }

    private void lineItemListUpdated() {
        // Notify adapter that data has changed
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    //----------------------------------------------------------------------------------------------
    // EventsForFriendAdapter.Listener Implementation
    //----------------------------------------------------------------------------------------------

    @Override
    public void onEventClicked(View view, Event event) {
        AppState.getCurrentState().selectEvent(event);
        startActivity(new Intent(this, EventSummaryActivity.class));
    }
}
