package edu.washington.ischool.commoncents.commoncents.activites1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import edu.washington.ischool.commoncents.commoncents.DataRepository;
import edu.washington.ischool.commoncents.commoncents.adapters1.EventsForFriendAdapter;
import edu.washington.ischool.commoncents.commoncents.AppState;
import edu.washington.ischool.commoncents.commoncents.helpers1.ComponentHelper;
import edu.washington.ischool.commoncents.commoncents.models1.Event;
import edu.washington.ischool.commoncents.commoncents.models1.Friend;
import edu.washington.ischool.commoncents.commoncents.R;
import edu.washington.ischool.commoncents.commoncents.models1.User;

public class UserProfileActivity extends AppCompatActivity implements EventsForFriendAdapter.Listener {

    EventsForFriendAdapter adapter;

    //----------------------------------------------------------------------------------------------
    // Activity Implementation
    //----------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);

        User selectedUser = AppState.getCurrentState().getSelectedUser();

        // Exit if there's no selected friend
        if (selectedUser == null) {
            finish();
            return;
        }

        initializeViews(selectedUser);
        initializeLineItemList();
    }

    private void initializeViews(User selectedUser) {
        // Profile pic
        ImageView profilePictureView = (ImageView) findViewById(R.id.profile_picture);
        ComponentHelper.getInstance().setProfilePicture(profilePictureView, selectedUser, ComponentHelper.PictureType.AS_BACKGROUND);

        // Friend name
        TextView friendNameView = (TextView) findViewById(R.id.friend_name);
        friendNameView.setText(selectedUser.getName());

        // Amount owed
        TextView friendOweAmountView = (TextView) findViewById(R.id.friend_owe_amount);
        ComponentHelper.getInstance().setOweAmount(this, friendOweAmountView, selectedUser.getAmountOwed(), false);
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
        adapter = new EventsForFriendAdapter(this, this, DataRepository.getInstance().getEvents());
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
