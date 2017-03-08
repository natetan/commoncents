package edu.washington.ischool.commoncents.commoncents.Activies;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.washington.ischool.commoncents.commoncents.Adapters.SplitByItemsFriendsAdapter;
import edu.washington.ischool.commoncents.commoncents.Adapters.SplitByItemsLineAdapter;
import edu.washington.ischool.commoncents.commoncents.AppState;
import edu.washington.ischool.commoncents.commoncents.Models.Event;
import edu.washington.ischool.commoncents.commoncents.Models.LineItem;
import edu.washington.ischool.commoncents.commoncents.Models.User;
import edu.washington.ischool.commoncents.commoncents.R;

public class SplitByItemActivity extends AppCompatActivity implements SplitByItemsLineAdapter.Listener, SplitByItemsFriendsAdapter.Listener {

    //missing the LOGIC for pairing LineItem's with User's


    private final String TAG = "SPLIT_BY_ITEM_ACTIVITY";

    private Event currentEvent;

    private Button btnAddFriend;
    private Button btnAddLineItem;
    private EditText newFriend;
    private EditText newLineItem;
    private EditText newDollar;
    private EditText newCents;
    private TextView friendName;
    private TextView lineItemName;

    private SplitByItemsLineAdapter adapter;
    private RecyclerView splitItemView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_by_item);

        currentEvent = AppState.getCurrentState().getSelectedEvent();

        //UI elements
        btnAddFriend = (Button) findViewById(R.id.add_friend);
        btnAddLineItem = (Button) findViewById(R.id.add_lineitem);
        newFriend = (EditText) findViewById(R.id.new_friend_name);
        newLineItem = (EditText) findViewById(R.id.new_lineitem_name);
        newDollar = (EditText) findViewById(R.id.new_lineitem_dollar);
        newCents = (EditText) findViewById(R.id.new_lineitem_cents);



        initializeSplitItemView();

        btnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nFriend = newFriend.getText().toString();
                newFriend.setText("");

            }
        });

        btnAddLineItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: clicked add line item");

                //take EditText values and create ListItem object
                String nLineItem = newLineItem.getText().toString();
                String nDollar = newDollar.getText().toString();
                int nDollarInt = Integer.parseInt(nDollar);
                String nCents = newCents.getText().toString();
                int nCentsInt;
                if (!nCents.equalsIgnoreCase("")) {
                    nCentsInt = Integer.parseInt(nCents);
                } else {
                    //defaults to 0 if no input;
                    nCentsInt = 0;
                }

                //price to be stored in LineItem object
                int priceInCents = (nDollarInt * 100) + nCentsInt;

                LineItem thisLineItem = new LineItem(nLineItem, priceInCents);
                adapter.addToLineItemList(thisLineItem);

                //newLineItem.setText("");
                //newDollar.setText("");
                newCents.setText("");
            }
        });

    }

    private void initializeSplitItemView() {
        splitItemView = (RecyclerView) findViewById(R.id.lineitems_in_event);

        splitItemView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        splitItemView.setLayoutManager(layoutManager);

        adapter = new SplitByItemsLineAdapter(this, currentEvent, R.layout.item_line_item, R.id.item, R.id.price, R.id.remove_line_item);
        splitItemView.setAdapter(adapter);
    }

    @Override
    public void onLineItemClicked(View view, int index) {
        adapter.removeFromLineItemList(index);
    }
}
