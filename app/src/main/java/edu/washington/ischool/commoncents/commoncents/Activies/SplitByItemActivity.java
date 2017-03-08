package edu.washington.ischool.commoncents.commoncents.Activies;

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

import edu.washington.ischool.commoncents.commoncents.Adapters.FriendsInEventAdapter;
import edu.washington.ischool.commoncents.commoncents.Adapters.SplitItemsListAdapter;
import edu.washington.ischool.commoncents.commoncents.Models.Payment;
import edu.washington.ischool.commoncents.commoncents.Models.User;
import edu.washington.ischool.commoncents.commoncents.R;

public class SplitByItemActivity extends AppCompatActivity {

    private final String TAG = "SPLIT_BY_ITEM_ACTIVITY";

    String[] dataset = {"one", "two", "third", "fourth"};

    private Button btnAddFriend;
    private Button btnAddLineItem;
    private EditText newFriend;
    private EditText newLineItem;
    private TextView friendName;
    private TextView lineItemName;

    private SplitItemsListAdapter adapter;
    private RecyclerView splitItemView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_by_item);

        //UI elements
        btnAddFriend = (Button) findViewById(R.id.add_friend);
        btnAddLineItem = (Button) findViewById(R.id.add_lineitem);
        newFriend = (EditText) findViewById(R.id.new_friend_name);
        newLineItem = (EditText) findViewById(R.id.new_lineitem_name);

        btnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nFriend = newFriend.getText().toString();
                newFriend.setText("");
            }
        });

        initializeSplitItemView();

        btnAddLineItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.e(TAG, "onClick: clicked add person");
//                //IMPLEMENT LATER USING PAYMENT OBJECT
////                adapter.add(amount.getText().toString());
////                adapter.add(percentage.getText().toString());
//
//                String nLineItem = newLineItem.getText().toString();
//                String newAmount = amount.getText().toString();
//                String newPercentage = amount.getText().toString();
//
//                User user = new User(newName);
//                Payment payment = new Payment(user, Integer.parseInt(newAmount) * 100);
//
//                adapter.addToFriendsInEvent(payment);
//
////                adapter.addToFriendsInEvent();
////                adapter.addToFriendsInEvent();
//                name.setText("");
//                amount.setText("");
//                percentage.setText("");
            }
        });

    }

    private void initializeSplitItemView() {
        splitItemView = (RecyclerView) findViewById(R.id.lineitems_in_event);

        splitItemView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        splitItemView.setLayoutManager(layoutManager);

        adapter = new SplitItemsListAdapter(R.layout.item_line_item, R.id.item, R.id.price);
        splitItemView.setAdapter(adapter);
    }
}
