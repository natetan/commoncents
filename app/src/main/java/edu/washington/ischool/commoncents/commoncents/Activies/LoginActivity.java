package edu.washington.ischool.commoncents.commoncents.Activies;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.washington.ischool.commoncents.commoncents.R;

public class LoginActivity extends AppCompatActivity {

    private Button signupButton, loginButton;
    private String email, password;
    private Context context;
    private LinearLayout layout;
    private EditText emailEditText, passwordEditText;
    private AlertDialog.Builder builder;

    // Firebase
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;

    public static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize the Firebase auth instance
        auth = FirebaseAuth.getInstance();
        
        // Tracks if the user is signed in or out
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        context = getBaseContext();
        layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        emailEditText = new EditText(context);
        emailEditText.setHint("email");
        emailEditText.setHintTextColor(Color.GRAY);
        emailEditText.setTextColor(Color.BLACK);
        emailEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        passwordEditText = new EditText(context);
        passwordEditText.setHint("password");
        passwordEditText.setHintTextColor(Color.GRAY);
        passwordEditText.setTextColor(Color.BLACK);
        passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        builder = new AlertDialog.Builder(context);

        signupButton = (Button) findViewById(R.id.signupButton);
        loginButton = (Button) findViewById(R.id.loginButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth("Sign Up", "Create Account", "signup");
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth("Log In", "Log In", "login");
            }
        });
    }

    private void auth(String title, String action, String choice) {
        if (emailEditText.getParent() != null) {
            ((ViewGroup) emailEditText.getParent()).removeView(emailEditText);
        }
        layout.addView(emailEditText);

        if (passwordEditText.getParent() != null) {
            ((ViewGroup) passwordEditText.getParent()).removeView(passwordEditText);
        }
        layout.addView(passwordEditText);
        final String method = choice;

        builder.setView(layout); // <== ISSUE IS WITH THIS LINE WHEN CALLED TWICE?
        builder.setTitle(title);
        builder.setPositiveButton(action, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                email = emailEditText.getText().toString();
                password = passwordEditText.getText().toString();
                Toast.makeText(getBaseContext(), "Email: " + email + "; Password: " +
                    password, Toast.LENGTH_SHORT).show();
                if (method.equals("signup")) {
                    signUp();
                } else {
                    login();
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        })
            .setIcon(android.R.drawable.ic_dialog_email)
            .show();
    }

    // Attaches the FirebaseAuth instance when app starts
    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    // Removes the FirebaseAuth instance when app stops
    @Override
    public void onStop() {
        super.onStop();
        if (authStateListener != null) {
            auth.removeAuthStateListener(authStateListener);
        }
    }

    private void signUp() {

    }

    private void login() {

    }
}
