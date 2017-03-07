package edu.washington.ischool.commoncents.commoncents.Activies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.washington.ischool.commoncents.commoncents.Adapters.FriendsInEventAdapter;
import edu.washington.ischool.commoncents.commoncents.R;

public class SplitByItemActivity extends AppCompatActivity {


    String[] dataset = {"one", "two", "third", "fourth"};

    private Button btnAddFriend;
    private Button btnAddLineItem;
    private EditText newFriend;
    private EditText newLineItem;
    private TextView friendName;
    private TextView lineItemName;

    private FriendsInEventAdapter adapter;


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

    }
}
