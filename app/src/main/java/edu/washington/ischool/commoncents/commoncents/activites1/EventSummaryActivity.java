package edu.washington.ischool.commoncents.commoncents.activites1;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import edu.washington.ischool.commoncents.commoncents.MainActivity;
import edu.washington.ischool.commoncents.commoncents.Manifest;
import edu.washington.ischool.commoncents.commoncents.adapters1.UsersListAdapter;
import edu.washington.ischool.commoncents.commoncents.AppState;
import edu.washington.ischool.commoncents.commoncents.DataRepository;
import edu.washington.ischool.commoncents.commoncents.adapters1.UsersListAdapter;
import edu.washington.ischool.commoncents.commoncents.models1.Event;
import edu.washington.ischool.commoncents.commoncents.models1.Friend;
import edu.washington.ischool.commoncents.commoncents.models1.LineItem;
import edu.washington.ischool.commoncents.commoncents.adapters1.FriendsInEventAdapter;
import edu.washington.ischool.commoncents.commoncents.R;
import edu.washington.ischool.commoncents.commoncents.models1.User;
import edu.washington.ischool.commoncents.commoncents.receivers1.MessageBroadcastReceiver;

public class EventSummaryActivity extends AppCompatActivity implements UsersListAdapter.Listener {

    private Button sendSmsBtn;
    private Button finishBtn;
    private TextView eventName;
    private TextView eventTotal;
    private RecyclerView eventSummaryView;
    private UsersListAdapter adapter;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_summary);

        sendSmsBtn = (Button) findViewById(R.id.send_sms_btn);
        finishBtn = (Button) findViewById(R.id.finish_btn);
        eventName = (TextView) findViewById(R.id.event_name);
        eventTotal = (TextView) findViewById(R.id.event_total);

        Event selectedEvent = AppState.getCurrentState().getSelectedEvent();
        Log.i("EventSummaryActivity", "onCreate: selected event: " + selectedEvent);
        eventName.setText(selectedEvent.getName());

        List<LineItem> lineItems = selectedEvent.getLineItems();
        if (lineItems != null) {
            for (LineItem lineItem : lineItems) {
                total += lineItem.getPrice() / 100.0;
            }
        }
        eventTotal.setText("" + total);

        sendSmsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager smsManager = SmsManager.getDefault();

                List<User> users = DataRepository.getInstance().getUsers();
                List<User> usersInCurrentEvent = AppState.getCurrentState().getSelectedEvent().getUsersInvolved();
                List<String> userNameInString = new ArrayList<String>();
                for (int i = 0; i < usersInCurrentEvent.size(); i++) {
                    //adds the name as string of all involved users' name
                    userNameInString.add(usersInCurrentEvent.get(i).getName());
                }

                Log.i("TAG", users.size() + "");

                for (int i = 0; i < users.size(); i++) {
                    for (int j = 0; j < userNameInString.size(); j++) {
                        if (userNameInString.get(j).equalsIgnoreCase(users.get(i).getName())) {
                            smsManager.sendTextMessage(users.get(i).getPhoneNumber(), null, "You have payments you owe!", null, null);
                            Log.i("phone numbers", users.get(i).getPhoneNumber() + "");
                        }
                    }


                }
            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(EventSummaryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        initializeEventSummaryView();

//        DataRepository.getInstance().subscribeToUserCollectionUpdates(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        DataRepository.getInstance().unsubscribeFromUserCollectionUpdates(adapter);
    }

    private void initializeEventSummaryView() {
        eventSummaryView = (RecyclerView) findViewById(R.id.event_summary_list);
        eventSummaryView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        eventSummaryView.setLayoutManager(layoutManager);

        adapter = new UsersListAdapter(this, this, AppState.getCurrentState().getSelectedEvent().getUsersInvolved());
        eventSummaryView.setAdapter(adapter);
    }

    @Override
    public void onUserClicked(View view, User user) {

        AppState.getCurrentState().selectUser(user);
        //SHOW LINE ITEMS, CREATE ACTIVITY FOR LINE ITEMS
        //startActivity(new Intent(getBaseContext(), FriendProfileActivity.class));
    }

    @Override
    public void onUserLongClicked(View view, User user) {
        
    }

    @Override
    public List<User> getUpdatedDataSet() {
        return AppState.getCurrentState().getSelectedEvent().getUsersInvolved();
    }
}
