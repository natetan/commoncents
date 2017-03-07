package edu.washington.ischool.commoncents.commoncents.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.washington.ischool.commoncents.commoncents.Activies.LoginActivity;
import edu.washington.ischool.commoncents.commoncents.R;
import edu.washington.ischool.commoncents.commoncents.SettingsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    private Button signupLoginButton, inviteFriendsButton, changeCurrencyButton;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        signupLoginButton = (Button) view.findViewById(R.id.signupLoginButton);
        inviteFriendsButton = (Button) view.findViewById(R.id.inviteFriendsButton);
        changeCurrencyButton = (Button) view.findViewById(R.id.changeCurrencyButton);

        signupLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
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

}
