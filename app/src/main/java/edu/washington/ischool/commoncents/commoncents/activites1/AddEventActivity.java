package edu.washington.ischool.commoncents.commoncents.activites1;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;

import edu.washington.ischool.commoncents.commoncents.AppState;
import edu.washington.ischool.commoncents.commoncents.models1.Event;
import edu.washington.ischool.commoncents.commoncents.R;

public class AddEventActivity extends AppCompatActivity {

    private final String TAG = "SPLIT_COSTS_FRAGMENT";
    private Date selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_costs);

        //UI variables
        final Button btnDatePicker = (Button)findViewById(R.id.btn_date_picker);
        final TextView txtEventDate =(TextView) findViewById(R.id.txt_event_date);
        Button btnSplitSum = (Button)findViewById(R.id.btn_split_sum);
        Button btnSplitItem = (Button)findViewById(R.id.btn_split_item);
        final EditText txtEventName = (EditText)findViewById(R.id.txt_event_name);

        //date picker, opens in dialog
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Log.i(TAG, "datePicker button clicked");

            //set default to today's date on first run
            Calendar c = Calendar.getInstance();
            final int year = c.get(Calendar.YEAR);
            final int month = c.get(Calendar.MONTH);
            final int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(AddEventActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int y, int m, int d) {
                txtEventDate.setText((new DateFormatSymbols().getMonths()[m]) + " " + d + ", " + y);
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.MONTH, m);
                cal.set(Calendar.DATE, d);
                cal.set(Calendar.YEAR, y);
                selectedDate = cal.getTime();
                }
            }, year, month, day);
            datePickerDialog.show();
            }
        });

        //Split by SUM
        btnSplitSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (submitEvent(txtEventName)) {
                    Intent intent = new Intent(AddEventActivity.this, SplitBySumActivity.class);
                    startActivity(intent);
                } else {
                    //Alert dialog, STOP user from proceeding
                    alertUser();
                }
            }
        });

        //Split by ITEMS
        btnSplitItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (submitEvent(txtEventName)) {
                    Intent intent = new Intent(AddEventActivity.this, SplitByItemActivity.class);
                    startActivity(intent);
                } else {
                    //Alert dialog, STOP user from proceeding
                    alertUser();
                }
            }
        });
    }

    public void alertUser() {
        new AlertDialog.Builder(this)
                .setTitle("Cannot Proceed")
                .setMessage("Please input the event name/pick a date!")
                .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    //ensures the fields are all filled before starting the next activity
    public boolean submitEvent(EditText txtEventName) {
        String eventName = txtEventName.getText().toString();
        if (selectedDate != null && !eventName.equalsIgnoreCase("")) {
            Event thisEvent = new Event(eventName, selectedDate);
            AppState.getCurrentState().selectEvent(thisEvent);
            return true;
        }

        return false;
    }
}

