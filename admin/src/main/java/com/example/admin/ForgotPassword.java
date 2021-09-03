package com.example.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText resetEmail;
    private Button resetButton;
    private ProgressBar progressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        resetEmail = (EditText)findViewById(R.id.emailReset);
        resetButton = (Button) findViewById(R.id.btnReset);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Forgot Password");

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = resetEmail.getText().toString().trim();

                if(email.isEmpty()){
                    resetEmail.setError("Email is required!");
                    resetEmail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    resetEmail.setError("Please provide a valid email!");
                    resetEmail.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Check your mailbox to reset your password",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Try again! Something went wrong!!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }
}