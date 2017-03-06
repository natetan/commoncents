package edu.washington.ischool.commoncents.commoncents.Activies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import edu.washington.ischool.commoncents.commoncents.Adapters.FriendsInEventAdapter;
import edu.washington.ischool.commoncents.commoncents.R;

public class SplitBySumActivity extends AppCompatActivity {

    private static final String TAG = "SplitBySumActivity";

    private Button doneBtn;
    private Button addPerson;
    private EditText sumInput;
    private Switch splitEqually;
    private TextView totalPercentage;
    //private List<String> friends;
    private RecyclerView friendsInEventView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_by_sum);

        //UI elements
        doneBtn = (Button) findViewById(R.id.done_button);
        addPerson = (Button) findViewById(R.id.add_button);
        sumInput = (EditText) findViewById(R.id.sum_input);
        splitEqually = (Switch) findViewById(R.id.split_equally_switch);
        totalPercentage = (TextView) findViewById(R.id.total_percentage);

        initializeFriendsInEventView();

        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    protected int calculateTotalPercentage() {
        int total = 0;
        totalPercentage.setText(String.valueOf(total));
        return total;
    }

    private void initializeFriendsInEventView() {
        friendsInEventView = (RecyclerView) findViewById(R.id.friends_in_event_list);

        friendsInEventView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        friendsInEventView.setLayoutManager(layoutManager);

        adapter = new FriendsInEventAdapter(R.layout.item_friend_for_event, R.id.name, R.id.percentage, R.id.amount);
        friendsInEventView.setAdapter(adapter);
    }
}
