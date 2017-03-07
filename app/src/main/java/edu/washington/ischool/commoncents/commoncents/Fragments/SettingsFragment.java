package edu.washington.ischool.commoncents.commoncents.Fragments;


import android.app.AlertDialog;
import android.content.Context;
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
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.washington.ischool.commoncents.commoncents.Activies.LoginActivity;
import edu.washington.ischool.commoncents.commoncents.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    private Button signupLoginButton, inviteFriendsButton, changeCurrencyButton;

    private Context context;
    private RelativeLayout layout;
    private EditText emailEditText;
    private AlertDialog.Builder builder;
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

        context = getActivity();
        layout = new RelativeLayout(context);

        emailEditText = new EditText(context);
        emailEditText.setHint("email");
        emailEditText.setHintTextColor(Color.GRAY);
        emailEditText.setTextColor(Color.BLACK);
        emailEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        builder = new AlertDialog.Builder(context);

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
                if (emailEditText.getParent() != null) {
                    ((ViewGroup) emailEditText.getParent()).removeView(emailEditText);
                }
                layout.addView(emailEditText);
                builder.setView(layout); // <== ISSUE IS WITH THIS LINE WHEN CALLED TWICE?
                builder.setTitle("Invite Friend");
                builder.setPositiveButton("Invite Friend", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        email = emailEditText.getText().toString();
                    }

                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setIcon(android.R.drawable.ic_dialog_email).show();
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
