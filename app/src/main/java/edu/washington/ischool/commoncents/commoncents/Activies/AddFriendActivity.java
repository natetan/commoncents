package edu.washington.ischool.commoncents.commoncents.Activies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

import edu.washington.ischool.commoncents.commoncents.DataRepository;
import edu.washington.ischool.commoncents.commoncents.Models.User;
import edu.washington.ischool.commoncents.commoncents.R;

public class AddFriendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        //UI elements
        final EditText friendName = (EditText) findViewById(R.id.new_friend_name_edit_text);
        final EditText email = (EditText) findViewById(R.id.email_edit_text);
        final EditText phone = (EditText) findViewById(R.id.phone_number_edit_text);
        final Button submitBtn = (Button) findViewById(R.id.submit_button);

        if (friendName.getText().toString() == null || friendName.getText().toString().equals("")) {
            submitBtn.setEnabled(false);
            submitBtn.setClickable(false);
        }

        friendName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                submitBtn.setEnabled(true);
                submitBtn.setClickable(true);
                String friendNameString = friendName.getText().toString();

                if (friendNameString == null || friendNameString.equals("")) {
                    submitBtn.setEnabled(false);
                    submitBtn.setClickable(false);
                }

                return false;
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String friendNameString = friendName.getText().toString();
                String emailString = email.getText().toString();
                String phoneString = phone.getText().toString();

                ArrayList<User> users = (ArrayList<User>) DataRepository.getInstance().getUsers();

                User user = new User(friendNameString);
                if (!users.contains(user)) {
                    Toast.makeText(AddFriendActivity.this, "Friend does not exist, adding the new friend...", Toast.LENGTH_SHORT).show();
                    //If the email field is filled out, then add the email to the User object
                    if (!emailString.equals("")) {
                        user.setEmail(emailString);
                    }
                    //If the phone number field is filled out, then add the phone number to the User object
                    if (!phoneString.equals("")) {
                        user.setPhoneNumber(phoneString);
                    }
                    DataRepository.getInstance().addUser(user);
                } else {
                    Toast.makeText(AddFriendActivity.this, "Friend already exists", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
