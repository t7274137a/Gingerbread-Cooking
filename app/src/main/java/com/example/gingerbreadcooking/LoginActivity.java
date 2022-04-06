package com.example.gingerbreadcooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.graphics.Color.RED;

public class LoginActivity extends AppCompatActivity {

    private static FirebaseAuth mAuth;
    //private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            reload();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
    }

    static void logout(){
        mAuth.signOut();
    }

    public void signIn(View view) {
        EditText editTextPassword = findViewById(R.id.editTextTextPassword);
        EditText editTextEmail = findViewById(R.id.LoginEmail);
        String userEmail = editTextEmail.getText().toString();
        String userPassword = editTextPassword.getText().toString();

        // email validation
        if (userEmail.matches("^\\S{1,20}\\@\\S{1,20}\\.\\S{1,20}$")) {
            mAuth.signInWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                System.out.println("Signed in");
                                Intent LoginIntent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(LoginIntent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                System.out.println("Error Signing in");
                            }
                        }
                    });
        } else {
            //sets incorrect input text to red
            editTextEmail.setTextColor(RED);
        }
    }

    public void singup(View view){
        Intent LoginIntent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(LoginIntent);
    }

    public void GuestLogin(View view){
        Intent GuestLogin = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(GuestLogin);
    }

    private void reload() {
        Intent AlreadyLoggedIn = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(AlreadyLoggedIn);
    }

}