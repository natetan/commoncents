package edu.washington.ischool.commoncents.commoncents.Activies;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import edu.washington.ischool.commoncents.commoncents.Login;
import edu.washington.ischool.commoncents.commoncents.R;

public class LoginActivity extends AppCompatActivity {

    private Button signupButton, loginButton;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Context context = getBaseContext();
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText emailEditText = new EditText(context);
        emailEditText.setHint("email");
        emailEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        layout.addView(emailEditText);

        final EditText passwordEditText = new EditText(context);
        passwordEditText.setHint("password");
        passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        layout.addView(passwordEditText);

        final AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);

        signupButton = (Button) findViewById(R.id.signupButton);
        loginButton = (Button) findViewById(R.id.loginButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("Sign Up");
                builder.setPositiveButton("Create Account", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        email = emailEditText.getText().toString();
                        password = passwordEditText.getText().toString();
                        Toast.makeText(getBaseContext(), "Email: " + email + "; Password: " +
                                password, Toast.LENGTH_SHORT).show();
                    }
                })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("Log In");
                builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        email = emailEditText.getText().toString();
                        password = passwordEditText.getText().toString();
                        Toast.makeText(getBaseContext(), "Email: " + email + "; Password: " +
                                password, Toast.LENGTH_SHORT).show();
                    }
                })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }
}
