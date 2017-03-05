package edu.washington.ischool.commoncents.commoncents;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.washington.ischool.commoncents.commoncents.Activies.LoginActivity;

public class SettingsActivity extends Activity {
  private Button signupLoginButton, inviteFriendsButton, changeCurrencyButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);

    ActionBar actionBar = getActionBar();
    if (actionBar != null) {
      actionBar.setTitle("Settings");
    }

    signupLoginButton = (Button) findViewById(R.id.signupLoginButton);
    inviteFriendsButton = (Button) findViewById(R.id.inviteFriendsButton);
    changeCurrencyButton = (Button) findViewById(R.id.changeCurrencyButton);

    signupLoginButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(SettingsActivity.this, LoginActivity.class));
      }
    });
  }
}
