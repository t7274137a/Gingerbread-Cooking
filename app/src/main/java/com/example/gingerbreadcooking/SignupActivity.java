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

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
    }

    public void signUp(View view){
        EditText editTextPassword = findViewById(R.id.SignupPassword);
        EditText editTextEmail = findViewById(R.id.SignupEmail);
        String userEmail = editTextEmail.getText().toString();
        String userPassword = editTextPassword.getText().toString();


        // email validation
        if (userEmail.matches("^\\S{1,20}\\@\\S{1,20}\\.\\S{1,20}$")) {
        mAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent LoginIntent = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(LoginIntent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        } else {
            //sets incorrect input text to red
            editTextEmail.setTextColor(RED);
        }
    }

    public void login(View view){
        Intent LoginIntent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(LoginIntent);
    }
}