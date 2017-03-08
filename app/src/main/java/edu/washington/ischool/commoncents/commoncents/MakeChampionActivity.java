package edu.washington.ischool.commoncents.commoncents;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.washington.ischool.commoncents.commoncents.Models.Champion;

/**
 *  Created by yulong on 3/7/17
 *
 *  TESTING THE CHAMPION MODEL WITH FIREBASE
 */
public class MakeChampionActivity extends AppCompatActivity {
    private EditText championNameEditText, ability1, ability2, ability3, ability4;
    private Button createChampionButton;

    // Firebase database
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_champion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get reference to the database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        EditText[] ets = {championNameEditText, ability1, ability2, ability3, ability4};
        int[] ids = {R.id.championNameEditText, R.id.ability1EditText, R.id.ability2EditText,
                R.id.ability3EditText, R.id.ability4EditText};

        for (int i = 0; i < ets.length; i++) {
            ets[i] = (EditText) findViewById(ids[i]);
        }

        createChampionButton = (Button) findViewById(R.id.createChampionButton);
        createChampionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(championNameEditText) || isEmpty(ability1) || isEmpty(ability2) ||
                        isEmpty(ability3) || isEmpty(ability4)) {
                    Toast.makeText(MakeChampionActivity.this, "Don't leave the fields blank!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // Creates a new Champion (adds to Firebase)
                    Champion champion = new Champion(championNameEditText.getText().toString(),
                            ability1.getText().toString(), ability2.getText().toString(),
                            ability3.getText().toString(), ability4.getText().toString());
                    mDatabase.child("champions").setValue(champion);
                }
            }
        });
    }

    public boolean isEmpty(EditText e) {
        return e.getText().toString().length() == 0;
    }

}
