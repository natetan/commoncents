package edu.washington.ischool.commoncents.commoncents.Activies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;

import edu.washington.ischool.commoncents.commoncents.Adapters.FriendsInEventAdapter;
import edu.washington.ischool.commoncents.commoncents.R;

public class EventSummaryActivity extends AppCompatActivity {

    private Button sendSmsBtn;
    private Button finishBtn;
    private RecyclerView eventSummaryView;
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
                Intent intent = new Intent(EventSummaryActivity.this, SplitCostsActivity.class);
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

        adapter = new FriendsInEventAdapter(R.layout.item_friend_for_event, R.id.name, R.id.amount, R.id.percentage);
        eventSummaryView.setAdapter(adapter);
    }
}
