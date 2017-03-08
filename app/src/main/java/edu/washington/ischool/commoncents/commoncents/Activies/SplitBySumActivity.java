package edu.washington.ischool.commoncents.commoncents.Activies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import edu.washington.ischool.commoncents.commoncents.Adapters.FriendsInEventAdapter;
import edu.washington.ischool.commoncents.commoncents.AppState;
import edu.washington.ischool.commoncents.commoncents.Models.Event;
import edu.washington.ischool.commoncents.commoncents.Models.Payment;
import edu.washington.ischool.commoncents.commoncents.Models.User;
import edu.washington.ischool.commoncents.commoncents.R;

public class SplitBySumActivity extends AppCompatActivity {

    private static final String TAG = "SplitBySumActivity";

    private Button doneBtn;
    private Button addPerson;
    private Switch splitEqually;
    private TextView totalPercentage;
    //private List<String> friends;
    private RecyclerView friendsInEventView;
    private FriendsInEventAdapter adapter;
    private EditText name;
    private EditText amount;
    private EditText percentage;
    private EditText dollar;
    private EditText cents;
    private String totalDollars;
    private String totalCents;
    private int totalFriends;
    private int costInCents;

//TODO: implement a better form validation for entering money amounts.
    //TODO: how do we divy up remain cents if the remain cents is more than one cent?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_by_sum);

        //UI elements
        dollar = (EditText) findViewById(R.id.dollar_input);
        cents = (EditText) findViewById(R.id.cent_input);
        splitEqually = (Switch) findViewById(R.id.split_equally_switch);
        name = (EditText) findViewById(R.id.edit_name);
        amount = (EditText) findViewById(R.id.edit_amount);
        percentage = (EditText) findViewById(R.id.edit_percentage);
        addPerson = (Button) findViewById(R.id.add_button);
        doneBtn = (Button) findViewById(R.id.done_button);
        totalPercentage = (TextView) findViewById(R.id.total_percentage);

        splitEqually.setChecked(true);
        totalFriends = 1;

        //Switch listener for choosing split method (Equal or Unequal)
        splitEqually.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked) {
                    Log.v(TAG, "Switch is currently on and splitting equally");
                    splitEqually.setTextOn("Splitting Equally");
                    Log.v(TAG, splitEqually.getTextOn().toString());
                } else {
                    splitEqually.setTextOff("Splitting Unequally");
                    Log.v(TAG, "Switch is currently off and not splitting equally");
                    Log.v(TAG, splitEqually.getTextOff().toString());
                }
            }
        });

        //----------------------------------------------------------------------------------------------
        // Dollar EditText Listeners
        //----------------------------------------------------------------------------------------------

        //Key listener for number inputs
        dollar.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                int portion = 0;
                double percent = 0;
                //Enter key pressed
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    Log.v("TAG", "ENTER KEY PRESSED");
                }
                totalDollars = dollar.getText().toString();

                //If the user is splitting equally
                if (splitEqually.isChecked() && totalDollars != null && !totalDollars.equals("")) {
                    costInCents = Integer.valueOf(totalDollars) * 100 ;
                    Log.v("TOTAL DOLLARS IN CENTS", "" + costInCents);

                    portion = costInCents / totalFriends;
                    percent = (double) portion * 100 / costInCents;

                    amount.setText("$" + new DecimalFormat("#.##").format(portion / 100.0));
                    percentage.setText("" + percent + "%");
                }
                //The user is splitting unequally
                else {

                }

                return false;
            }
        });

        //On focus change, get what is currently in the edit text view
        dollar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Log.v(TAG, "On Focus Changed");
                totalDollars = dollar.getText().toString();
                Log.v(TAG, dollar.getText().toString());
            }
        });

        //On enter key on the android keyboard, get what is currently in the edit text view
        dollar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                Log.v(TAG, "On Editor Action");
                totalDollars = dollar.getText().toString();
                Log.v(TAG, dollar.getText().toString());
                return false;
            }
        });


        //----------------------------------------------------------------------------------------------
        // Cents EditText Listeners
        //----------------------------------------------------------------------------------------------

        //Key listener for number inputs
        cents.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Log.v(TAG, cents.getText().toString());
                int portion = 0;
                double percent = 0.0;
                //Enter key pressed
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    Log.v("TAG", "ENTER KEY PRESSED");
                }

                totalCents = cents.getText().toString();

                //If the user is splitting equally
                if (splitEqually.isChecked() && totalDollars != null && !totalDollars.equals("") && totalCents != null && !totalCents.equals("")) {
                    costInCents = Integer.valueOf(totalDollars) * 100 + Integer.valueOf(totalCents);
                    Log.v("TOTAL DOLLARS IN CENTS", "" + costInCents);

                    portion = costInCents / totalFriends;
                    percent = (double) portion * 100 / costInCents;

                    amount.setText("$" + new DecimalFormat("#.##").format(portion / 100.0));
                    percentage.setText("" + percent + "%");
                }
                //The user is splitting unequally
                else {

                }

                return false;

            }
        });

        //On focus change, get what is currently in the edit text view
        cents.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Log.v(TAG, "On Focus Changed");
                totalCents = cents.getText().toString();
                Log.v(TAG, cents.getText().toString());
            }
        });

        //On enter key on the android keyboard, get what is currently in the edit text view
        cents.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                Log.v(TAG, "On Editor Action");
                totalCents = cents.getText().toString();
                Log.v(TAG, cents.getText().toString());

                return false;
            }
        });

        //----------------------------------------------------------------------------------------------
        // Amount EditText Listeners
        //----------------------------------------------------------------------------------------------

        amount.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                String amtStr = amount.getText().toString();
                if (amtStr.contains(".") && amtStr.split("\\.").length > 1) {
                    if (amtStr.split("\\.")[1].length() > 2) {
                        amtStr = amtStr.split("\\.")[0] + "." + amtStr.split("\\.")[1].substring(0, 1);
                        amount.setText(amtStr);
                    }

                }
                if (!splitEqually.isChecked()) {
                    String amountText = amount.getText().toString();
                    double percent = 0;
                    if (amountText.contains("$")) {
                        amount.setText("");
                        amountText = amount.getText().toString();

                        Log.v("COST IN CENTS", "" + costInCents);
                        Log.v("AMOUNT TEXT", amountText);
                        if (amountText != null && !amountText.equals("")) {
                            int portion = Integer.valueOf(amountText) * 100;
                            percent = (double) portion / costInCents;
                        }
                    }

                    if (amountText != null && !amountText.equals("")) {
                        int portion = Integer.valueOf(amountText) * 100;
                        Log.v("PORTION", "" + portion);
                        percent = (double) portion / costInCents * 100;
                        Log.v("PERCENT", "" + percent);
                    }
                    percentage.setText(new DecimalFormat("#.##").format(percent) + "%");
                }


                return false;
            }
        });

        //----------------------------------------------------------------------------------------------
        // Percentage EditText Listeners
        //----------------------------------------------------------------------------------------------

        percentage.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (!splitEqually.isChecked()) {
                    double amt = 0;
                    String percentText = percentage.getText().toString();
                    Log.v("PERCENT TEXT", percentText);

                    if (percentText.contains("%")) {
                        percentage.setText("");
                        percentText = percentage.getText().toString();
                    }

                    if (percentText != null && !percentText.equals("")) {
                        double percent = Integer.valueOf(percentText);
                        amt = costInCents * (percent / 100.0) / 100.0;
                        Log.v("AMOUNT", "" + amt);
                        amount.setText("$" + new DecimalFormat("#.##").format(amt));
                    }
                }
                return false;
            }
        });

        initializeFriendsInEventView();


        //----------------------------------------------------------------------------------------------
        // Once total price fields have been filled, enable the button to add a person to the event
        //----------------------------------------------------------------------------------------------


        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: clicked add person");
                totalFriends++;
                Log.v("TOTAL FRIENDS?", "" + totalFriends);

                Log.v("TOTAL DOLLARS?", totalDollars);
                int portion = Integer.valueOf(totalDollars) / totalFriends;
                double percent = (double) portion / Integer.valueOf(totalDollars) * 100;
                Log.v("PERCENTS?", "" + percent);

                Log.v("PORTIONS?", "" + portion);

                String newName = name.getText().toString();
                String newAmount = amount.getText().toString().substring(1);
                String newPercentage = amount.getText().toString();

                if (newAmount.contains(".")) {
                    Log.v("CONTAINS A DOT", "CONTAINS A DOT");
//                    String s = newAmount.split("\\.")[0];
//                    String s2 = newAmount.split("\\.")[1];
//                    Log.v("NEW AMOUNT ARRAY", s + s2);
                    newAmount = newAmount.split("\\.")[0] + newAmount.split("\\.")[1];
                    Log.v("NEW AMMOUNT", newAmount);
                }

                Log.v(TAG, newAmount);

                User user = new User(newName);
                Payment payment = new Payment(user, Integer.valueOf(newAmount));

                adapter.addToFriendsInEvent(payment, Integer.valueOf(costInCents));
                
                name.setText("");
                amount.setText("$" + portion);
                percentage.setText(percent + "%");
            }
        });


//        doneBtn.setEnabled(false);
//        doneBtn.setClickable(false);

        //Check if the user has entered a name, amount, and percentage before adding a person to the event
//        if () {
//            doneBtn.setEnabled(true);
//            doneBtn.setEnabled(true);
//        }

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppState.getCurrentState().getSelectedEvent();

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

        adapter = new FriendsInEventAdapter(R.layout.item_friend_for_event, R.id.name, R.id.amount, R.id.percentage);
        friendsInEventView.setAdapter(adapter);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        return false;
    }

}
