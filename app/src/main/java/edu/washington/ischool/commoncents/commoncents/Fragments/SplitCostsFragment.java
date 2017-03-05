package edu.washington.ischool.commoncents.commoncents.Fragments;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.Calendar;

import edu.washington.ischool.commoncents.commoncents.Activies.SplitByItemActivity;
import edu.washington.ischool.commoncents.commoncents.Activies.SplitBySumActivity;
import edu.washington.ischool.commoncents.commoncents.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplitCostsFragment extends Fragment {

    private final String TAG = "SPLIT COSTS FRAGMENT";
    private String dateString;

    public SplitCostsFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_split_costs, container, false);

        //UI variables
        final Button btnDatePicker = (Button)v.findViewById(R.id.btn_date_picker);
        final TextView txtEventDate =(TextView) v.findViewById(R.id.txt_event_date);
        Button btnSplitSum = (Button)v.findViewById(R.id.btn_split_sum);
        Button btnSplitItem = (Button)v.findViewById(R.id.btn_split_item);
        final EditText txtEventName = (EditText) v.findViewById(R.id.txt_event_name);

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

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        txtEventDate.setText((new DateFormatSymbols().getMonths()[m]) + " " + d + ", " + y);
                        dateString = y + "," + m + "," + d;
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        btnSplitSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SplitBySumActivity.class);
                String eventName = txtEventName.getText().toString();
                intent.putExtra("eventName", eventName);
                intent.putExtra("eventDate", dateString); //yyyy,mm,dd format
                startActivity(intent);
            }
        });

        btnSplitItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SplitByItemActivity.class);
                String eventName = txtEventName.getText().toString();
                intent.putExtra("eventName", eventName);
                intent.putExtra("eventDate", dateString);
                startActivity(intent);
            }
        });

        //return View
        return v;
    }
}
