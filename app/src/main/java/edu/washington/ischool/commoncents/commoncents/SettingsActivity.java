package edu.washington.ischool.commoncents.commoncents;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import edu.washington.ischool.commoncents.commoncents.Activies.LoginActivity;

public class SettingsActivity extends AppCompatActivity {
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

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
