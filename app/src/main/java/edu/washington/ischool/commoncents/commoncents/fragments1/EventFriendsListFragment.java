package edu.washington.ischool.commoncents.commoncents.fragments1;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import edu.washington.ischool.commoncents.commoncents.activites1.AddFriendActivity;
import edu.washington.ischool.commoncents.commoncents.activites1.UserProfileActivity;
import edu.washington.ischool.commoncents.commoncents.adapters1.UsersListAdapter;
import edu.washington.ischool.commoncents.commoncents.AppState;
import edu.washington.ischool.commoncents.commoncents.DataRepository;
import edu.washington.ischool.commoncents.commoncents.R;
import edu.washington.ischool.commoncents.commoncents.models1.Event;
import edu.washington.ischool.commoncents.commoncents.models1.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventFriendsListFragment extends Fragment {

    private UsersListAdapter adapter;

    //----------------------------------------------------------------------------------------------
    // Fragment Implementation
    //----------------------------------------------------------------------------------------------

    public EventFriendsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mainView = inflater.inflate(R.layout.fragment_friends_list, container, false);

        initializeFab(getContext(), mainView);

        initializeFriendsList(mainView);

        DataRepository.getInstance().subscribeToUserCollectionUpdates(adapter);

        return mainView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        DataRepository.getInstance().unsubscribeFromUserCollectionUpdates(adapter);
    }

    //----------------------------------------------------------------------------------------------
    // Fragment Implementation
    //----------------------------------------------------------------------------------------------

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
        adapter = new UsersListAdapter(getContext(), null, AppState.getCurrentState().getSelectedEvent().getUsersInvolved());
        friendsList.setAdapter(adapter);

        friendsListUpdated();
    }

    private void friendsListUpdated() {
        // Notify adapter that data has changed
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
