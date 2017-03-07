package edu.washington.ischool.commoncents.commoncents.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.washington.ischool.commoncents.commoncents.Activies.LoginActivity;
import edu.washington.ischool.commoncents.commoncents.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    private Button signupLoginButton, inviteFriendsButton, changeCurrencyButton;

    // Firebase
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public final String TAG = getActivity().getClass().getSimpleName();

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

        inviteFriendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
