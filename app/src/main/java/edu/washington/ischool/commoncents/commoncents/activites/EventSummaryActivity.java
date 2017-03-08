package edu.washington.ischool.commoncents.commoncents.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;

import edu.washington.ischool.commoncents.commoncents.adapters.FriendsInEventAdapter;
import edu.washington.ischool.commoncents.commoncents.R;

public class EventSummaryActivity extends AppCompatActivity {

    private Button sendSmsBtn;
    private Button finishBtn;
    private RecyclerView friendsInEventView;
    private FriendsInEventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_summary);

        sendSmsBtn = (Button) findViewById(R.id.send_sms_btn);
        finishBtn = (Button) findViewById(R.id.finish_btn);

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

    }

    private void initializeFriendsInEventView() {

    }
}
