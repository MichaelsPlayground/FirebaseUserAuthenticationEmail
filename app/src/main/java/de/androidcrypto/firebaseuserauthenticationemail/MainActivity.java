package de.androidcrypto.firebaseuserauthenticationemail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TextView loginStatus;
    EditText userEmail, userPassword;
    Button logIn, signIn, logOut;
    Intent signInIntent;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseUser logedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginStatus = findViewById(R.id.tvStatus);
        userEmail = findViewById(R.id.etLoginEmail);
        userPassword = findViewById(R.id.etLoginPassword);

        logIn = findViewById(R.id.btnLogIn);
        signIn = findViewById(R.id.btnSignIn);
        logOut = findViewById(R.id.btnLogOut);
        signInIntent = new Intent(MainActivity.this, SignInActivity.class);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            System.out.println("*** The user is loged in as " + currentUser.getEmail() + " ***");
            loginStatus.setText("*** The user is loged in as " + currentUser.getEmail() + " ***" + "\n" +
                    loginStatus.getText().toString());
            //reload();
        } else {
            System.out.println("*** No user is loged in ***");
            loginStatus.setText("*** No user is loged in ***" + "\n" +
                    loginStatus.getText().toString());
        }

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = userEmail.getText().toString();
                String password = userPassword.getText().toString();
                if (email.equals("")) {
                    System.out.println("*** Error: Email is empty ***");
                    loginStatus.setText("*** Error: Email is empty ***" + "\n" +
                            loginStatus.getText().toString());
                    return;
                }
                if (password.equals("")) {
                    System.out.println("*** Error: Password is empty ***");
                    loginStatus.setText("*** Error: Password is empty ***" + "\n" +
                            loginStatus.getText().toString());
                    return;
                }
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    loginStatus.setText("signInWithEmail:success" + "\n" +
                                            loginStatus.getText().toString());
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    logedInUser = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    loginStatus.setText("signInWithEmail:failure" + "\n" +
                                            loginStatus.getText().toString());
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    logedInUser = null;
                                    updateUI(null);
                                }
                            }
                        });

            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(signInIntent);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                System.out.println("User signed out");
                loginStatus.setText("User signed out" + "\n" +
                        loginStatus.getText().toString());
            }
        });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            System.out.println("User: " + user.getEmail());
            loginStatus.setText("User: " + user.getEmail() + "\n" +
                    loginStatus.getText().toString());
        }
    }
}