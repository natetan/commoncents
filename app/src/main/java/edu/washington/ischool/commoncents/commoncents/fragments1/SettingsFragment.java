package edu.washington.ischool.commoncents.commoncents.fragments1;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.washington.ischool.commoncents.commoncents.activites1.LoginActivity;
import edu.washington.ischool.commoncents.commoncents.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    private Button signupLoginButton, inviteFriendsButton, changeCurrencyButton;

    private EditText emailEditText;
    private String email;

    // Firebase
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public final String TAG = "Settings Fragment";

    public static final String SIGN_IN = "Sign Up / Login";
    public static final String SIGN_OUT = "Sign out";

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // Initialize the Firebase auth instance
        mFirebaseAuth = FirebaseAuth.getInstance();

        signupLoginButton = (Button) view.findViewById(R.id.signupLoginButton);
        inviteFriendsButton = (Button) view.findViewById(R.id.inviteFriendsButton);
        changeCurrencyButton = (Button) view.findViewById(R.id.changeCurrencyButton);


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    signupLoginButton.setText(SIGN_OUT);
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    signupLoginButton.setText(SIGN_IN);
                }
            }
        };

        signupLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (signupLoginButton.getText().toString().equals(SIGN_IN)) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    // Sign out user
                    FirebaseAuth.getInstance().signOut();
                }
            }
        });

        // Alert dialog
        inviteFriendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promptForEmail();
            }

        });

        changeCurrencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void promptForEmail() {
        // get prompts xml view
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View promptView = layoutInflater.inflate(R.layout.dialog_email_input, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(promptView);

        emailEditText = (EditText) promptView.findViewById(R.id.emailEditText);
        emailEditText.setHintTextColor(Color.GRAY);
        emailEditText.setTextColor(Color.BLACK);
        emailEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        email = emailEditText.getText().toString();
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
