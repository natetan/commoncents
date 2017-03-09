package edu.washington.ischool.commoncents.commoncents.activites1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.util.List;
import java.util.Map;

import edu.washington.ischool.commoncents.commoncents.adapters1.SplitByItemsFriendsAdapter;
import edu.washington.ischool.commoncents.commoncents.adapters1.SplitByItemsLineAdapter;
import edu.washington.ischool.commoncents.commoncents.AppState;
import edu.washington.ischool.commoncents.commoncents.DataRepository;
import edu.washington.ischool.commoncents.commoncents.models1.Event;
import edu.washington.ischool.commoncents.commoncents.models1.LineItem;
import edu.washington.ischool.commoncents.commoncents.models1.Payment;
import edu.washington.ischool.commoncents.commoncents.models1.User;
import edu.washington.ischool.commoncents.commoncents.R;

public class SplitByItemActivity extends AppCompatActivity implements SplitByItemsLineAdapter.Listener, SplitByItemsFriendsAdapter.Listener {

    //missing the LOGIC for pairing LineItem's with User's
    //Not selecting existing users yet

    private final String TAG = "SPLIT_BY_ITEM_ACTIVITY";

    private Event currentEvent;

    private Button btnAddFriend;
    private Button btnAddLineItem;
    private Button btnDone;
    private EditText newFriend;
    private EditText newLineItem;
    private EditText newDollar;
    private EditText newCents;
    private TextView friendName;
    private TextView lineItemName;

    private SplitByItemsLineAdapter adapterRight;
    private SplitByItemsFriendsAdapter adapterLeft;
    private RecyclerView splitItemView;
    private RecyclerView splitItemViewFriend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_by_item);

        currentEvent = AppState.getCurrentState().getSelectedEvent();

        //UI elements
        btnAddFriend = (Button) findViewById(R.id.add_friend);
        btnAddLineItem = (Button) findViewById(R.id.add_lineitem);
        btnDone = (Button) findViewById(R.id.to_event_summary);
        newFriend = (EditText) findViewById(R.id.new_friend_name);
        newLineItem = (EditText) findViewById(R.id.new_lineitem_name);
        newDollar = (EditText) findViewById(R.id.new_lineitem_dollar);
        newCents = (EditText) findViewById(R.id.new_lineitem_cents);

        initializeSplitItemView();

        btnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nFriend = newFriend.getText().toString();
                User thisUser = new User(nFriend);
                adapterLeft.addToUsersList(thisUser);
                Toast.makeText(SplitByItemActivity.this, "" + currentEvent.getUsersInvolved().size(), Toast.LENGTH_SHORT).show();
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

                if (nLineItem.equalsIgnoreCase("") || nDollar.equalsIgnoreCase("")) {
                    new AlertDialog.Builder(SplitByItemActivity.this)
                            .setTitle("Cannot Add Line Item")
                            .setMessage("Please input a line item name and/or price.")
                            .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            })
                            .show();
                } else {
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
                    adapterRight.addToLineItemList(thisLineItem);

                    newLineItem.setText("");
                    newDollar.setText("");
                    newCents.setText("");
                }
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<User, List<LineItem>> pairings = AppState.getCurrentState().getCurrentUserLineItemPairing();
                Event e = AppState.getCurrentState().getSelectedEvent();
                List<LineItem> lineItemList = e.getLineItems();
                for (User u : pairings.keySet()) {
                    List<LineItem> values = pairings.get(u);
                    for (int i = 0; i < values.size(); i++) {
                        Payment p = new Payment(u, values.get(i).getPrice());
                        lineItemList.get(i).getPayments().add(p);
                    }
                }
                for (User u : pairings.keySet()) {
                    Log.e(TAG, u.getName());
                    for (LineItem li: pairings.get(u)) {
                        Log.e(TAG, li.getName());
                    }

                }
                finish();
                DataRepository.getInstance().addEvent(currentEvent);
                Intent intent = new Intent(SplitByItemActivity.this, EventSummaryActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initializeSplitItemView() {
        splitItemView = (RecyclerView) findViewById(R.id.lineitems_in_event);

        splitItemView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManagerRight = new LinearLayoutManager(this);
        splitItemView.setLayoutManager(layoutManagerRight);

        adapterRight = new SplitByItemsLineAdapter(this, currentEvent, R.layout.item_line_item, R.id.item, R.id.price, R.id.remove_line_item);
        splitItemView.setAdapter(adapterRight);

        //User List
        splitItemViewFriend = (RecyclerView) findViewById(R.id.user_in_event_list);
        splitItemViewFriend.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerLeft = new LinearLayoutManager(this);
        splitItemViewFriend.setLayoutManager(layoutManagerLeft);
        adapterLeft = new SplitByItemsFriendsAdapter(this, currentEvent, R.layout.item_friend_for_split_item, R.id.user, R.id.remove_user);
        splitItemViewFriend.setAdapter(adapterLeft);
    }

    @Override
    public void onLineItemClicked(View view, int index) {
        adapterRight.removeFromLineItemList(index);
    }

    @Override
    public void onUserClicked(View view, int index) {
        Log.e(TAG, "removing user??????");
        adapterLeft.removeFromUsersList(index);
    }
}
