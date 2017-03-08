package edu.washington.ischool.commoncents.commoncents.Fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.washington.ischool.commoncents.commoncents.Activies.AddFriendActivity;
import edu.washington.ischool.commoncents.commoncents.Activies.FriendProfileActivity;
import edu.washington.ischool.commoncents.commoncents.Adapters.FriendsListAdapter;
import edu.washington.ischool.commoncents.commoncents.AppState;
import edu.washington.ischool.commoncents.commoncents.Models.Friend;
import edu.washington.ischool.commoncents.commoncents.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsListFragment extends Fragment implements FriendsListAdapter.Listener {

    private FriendsListAdapter adapter;

    //----------------------------------------------------------------------------------------------
    // Fragment Implementation
    //----------------------------------------------------------------------------------------------

    public FriendsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mainView = inflater.inflate(R.layout.fragment_friends_list, container, false);

        initializeFab(getContext(), mainView);

        initializeFriendsList(mainView);

        return mainView;
    }

    private void initializeFab(final Context context, View mainView) {
        FloatingActionButton fab = (FloatingActionButton) mainView.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, AddFriendActivity.class));
            }
        });
    }

    private void initializeFriendsList(View mainView) {
        // Get the recycler
        RecyclerView friendsList = (RecyclerView) mainView.findViewById(R.id.friends_list);

        // Set some basic properties
        friendsList.setHasFixedSize(true);

        // Use a simple linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        friendsList.setLayoutManager(layoutManager);

        // Create the adapter
        adapter = new FriendsListAdapter(getContext(), this);
        friendsList.setAdapter(adapter);

        friendsListUpdated();
    }

    private void friendsListUpdated() {
        // Notify adapter that data has changed
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    //----------------------------------------------------------------------------------------------
    // FriendsListAdapter.Listener Implementation
    //----------------------------------------------------------------------------------------------

    @Override
    public void onFriendClicked(View view, Friend friend) {
        // Go to selected friends profile page
        AppState.getCurrentState().selectFriend(friend);
        startActivity(new Intent(getContext(), FriendProfileActivity.class));
    }
}
