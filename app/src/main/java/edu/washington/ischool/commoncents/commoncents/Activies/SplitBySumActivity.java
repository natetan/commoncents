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

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import edu.washington.ischool.commoncents.commoncents.Adapters.FriendsInEventAdapter;
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
    private EditText sum;
    private String totalPrice;
    private int totalFriends;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_by_sum);

        //UI elements
        doneBtn = (Button) findViewById(R.id.done_button);
        addPerson = (Button) findViewById(R.id.add_button);
        splitEqually = (Switch) findViewById(R.id.split_equally_switch);
        totalPercentage = (TextView) findViewById(R.id.total_percentage);
        name = (EditText) findViewById(R.id.edit_name);
        amount = (EditText) findViewById(R.id.edit_amount);
        percentage = (EditText) findViewById(R.id.edit_percentage);
        sum = (EditText) findViewById(R.id.sum_input);

        splitEqually.setChecked(true);

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

        //Key listener for number inputs
        sum.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Log.v(TAG, sum.getText().toString());

                //Enter key pressed
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    Log.v("TAG", "ENTER KEY PRESSED");
                }
                totalPrice = sum.getText().toString();
                Log.v(TAG, sum.getText().toString());
                return false;
            }
        });

        //On focus change, get what is currently in the edit text view
        sum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Log.v(TAG, "On Focus Changed");
                totalPrice = sum.getText().toString();
                Log.v(TAG, sum.getText().toString());
            }
        });

        //On enter key on the android keyboard, get what is currently in the edit text view
        sum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                Log.v(TAG, "On Editor Action");
                totalPrice = sum.getText().toString();
                Log.v(TAG, sum.getText().toString());
                return false;
            }
        });

        initializeFriendsInEventView();

        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: clicked add person");
                totalFriends++;
                if (splitEqually.isChecked()) {
                    Log.v("TOTAL PRICE", totalPrice);
                    String[] split = totalPrice.split("\\.");
                    Log.v("SPLIT AT 0", split[0]);
                    String totalCents = split[0] + split[1];
                    Log.v("TOTAL CENTS", totalCents);
                    int costInCents = Integer.parseInt(totalCents);
                    Log.v("Cost in cents", "" + costInCents);

                    splitEqually(totalFriends, costInCents);
                }
                //IMPLEMENT LATER USING PAYMENT OBJECT
//                adapter.add(amount.getText().toString());
//                adapter.add(percentage.getText().toString());

                String newName = name.getText().toString();
                String newAmount = amount.getText().toString();
                String newPercentage = amount.getText().toString();

                Log.v(TAG, newAmount);


                User user = new User(newName);
                Payment payment = new Payment(user, Integer.parseInt(newAmount) * 100);

                adapter.addToFriendsInEvent(payment);

                //IMPLEMENT PAYMENT OBJECT HERE
//                adapter.addToFriendsInEvent();
//                adapter.addToFriendsInEvent();
                name.setText("");
                amount.setText("");
                percentage.setText("");
            }
        });

        doneBtn.setEnabled(false);
        doneBtn.setClickable(false);

        //Check if the user has entered a name, amount, and percentage before adding a person to the event
//        if () {
//            doneBtn.setEnabled(true);
//            doneBtn.setEnabled(true);
//        }

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

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        return false;
    }

    private void splitEqually(int numOfFriends, int sum) {
        Log.v("CALLING SPLIT EQUALLY", "CALLING SPLIT EQUALLY");

        amount.setText(sum / numOfFriends);
        percentage.setText(100 / numOfFriends);


        Log.v("AMOUNT", amount.getText().toString());
        Log.v("PERCENTAGE", percentage.getText().toString());
    }

}
