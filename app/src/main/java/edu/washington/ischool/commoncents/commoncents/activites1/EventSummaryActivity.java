package edu.washington.ischool.commoncents.commoncents.activites1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import edu.washington.ischool.commoncents.commoncents.adapters1.FriendsListAdapter;
import edu.washington.ischool.commoncents.commoncents.AppState;
import edu.washington.ischool.commoncents.commoncents.DataRepository;
import edu.washington.ischool.commoncents.commoncents.models1.Friend;
import edu.washington.ischool.commoncents.commoncents.models1.LineItem;
import edu.washington.ischool.commoncents.commoncents.adapters1.FriendsInEventAdapter;
import edu.washington.ischool.commoncents.commoncents.R;

public class EventSummaryActivity extends AppCompatActivity implements FriendsListAdapter.Listener{

    private Button sendSmsBtn;
    private Button finishBtn;
    private TextView eventName;
    private TextView eventTotal;
    private RecyclerView eventSummaryView;
    private FriendsListAdapter adapter;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_summary);

        sendSmsBtn = (Button) findViewById(R.id.send_sms_btn);
        finishBtn = (Button) findViewById(R.id.finish_btn);
        eventName = (TextView) findViewById(R.id.event_name);
        eventTotal = (TextView) findViewById(R.id.event_total);

        eventName.setText("" + AppState.getCurrentState().getSelectedEvent().getName());
        for (LineItem lineItem: AppState.getCurrentState().getSelectedEvent().getLineItems()) {
            total += lineItem.getPrice() / 100.0;
        }
        eventTotal.setText("" + total);

        sendSmsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager smsManager = SmsManager.getDefault();

            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventSummaryActivity.this, AddEventActivity.class);
                startActivity(intent);
            }
        });

        initializeEventSummaryView();

    }

    private void initializeEventSummaryView() {
        eventSummaryView = (RecyclerView) findViewById(R.id.event_summary_list);
        eventSummaryView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        eventSummaryView.setLayoutManager(layoutManager);

        adapter = new FriendsListAdapter(getBaseContext(), this);
        eventSummaryView.setAdapter(adapter);
    }

    @Override
    public void onFriendClicked(View view, Friend friend) {
        // Go to selected friends profile page
        AppState.getCurrentState().selectFriend(friend);
        //SHOW LINE ITEMS, CREATE ACTIVITY FOR LINE ITEMS
        //startActivity(new Intent(getBaseContext(), FriendProfileActivity.class));
    }

    @Override
    public List<Friend> getUpdatedDataSet() {

    }
}
