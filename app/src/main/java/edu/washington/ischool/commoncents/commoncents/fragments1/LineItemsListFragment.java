package edu.washington.ischool.commoncents.commoncents.fragments1;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import edu.washington.ischool.commoncents.commoncents.AppState;
import edu.washington.ischool.commoncents.commoncents.DataRepository;
import edu.washington.ischool.commoncents.commoncents.R;
import edu.washington.ischool.commoncents.commoncents.activites1.UserProfileActivity;
import edu.washington.ischool.commoncents.commoncents.adapters1.LineItemsListAdapter;
import edu.washington.ischool.commoncents.commoncents.adapters1.UsersListAdapter;
import edu.washington.ischool.commoncents.commoncents.models1.LineItem;
import edu.washington.ischool.commoncents.commoncents.models1.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class LineItemsListFragment extends Fragment {

    private LineItemsListAdapter adapter;

    //----------------------------------------------------------------------------------------------
    // Fragment Implementation
    //----------------------------------------------------------------------------------------------

    public LineItemsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mainView = inflater.inflate(R.layout.fragment_line_items_list, container, false);

        initializePaymentsList(mainView);

        return mainView;
    }

    //----------------------------------------------------------------------------------------------
    // Fragment Implementation
    //----------------------------------------------------------------------------------------------

    private void initializePaymentsList(View mainView) {
        // Get the recycler
        RecyclerView paymentsList = (RecyclerView) mainView.findViewById(R.id.list);

        // Set some basic properties
        paymentsList.setHasFixedSize(true);

        // Use a simple linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        paymentsList.setLayoutManager(layoutManager);

        // Create the adapter
        adapter = new LineItemsListAdapter(getContext(), AppState.getCurrentState().getSelectedEvent().getLineItems());
        paymentsList.setAdapter(adapter);

        listUpdated();
    }

    private void listUpdated() {
        // Notify adapter that data has changed
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

}
