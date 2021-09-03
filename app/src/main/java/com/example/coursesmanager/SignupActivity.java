package com.example.coursesmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignupActivity extends AppCompatActivity {

    private EditText Fullname;
    private EditText Email;
    private EditText Uname;
    private EditText Upassword;
    private Button Signup;
    private TextView Signin;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_signup);
        Fullname = (EditText) findViewById(R.id.etFullname);
        Email = (EditText) findViewById(R.id.etEmail);
        Uname = (EditText) findViewById(R.id.etUname);
        Upassword = (EditText) findViewById(R.id.etUpassword);
        Signup = (Button) findViewById(R.id.btnSignup);
        Signin = (TextView) findViewById(R.id.tvSignin);
        progressBar = (ProgressBar) findViewById(R.id.progress);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Registration");

        mAuth = FirebaseAuth.getInstance();
        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String fullname, email, username, password;
                fullname = String.valueOf(Fullname.getText());
                email = String.valueOf(Email.getText());
                username = String.valueOf(Uname.getText());
                password = String.valueOf(Upassword.getText());
                if (!fullname.equals("") && !email.equals("") && !username.equals("") && !password.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        Email.setError("Please provide valid email!");
                        Email.requestFocus();
                        return;
                    }
                    if (Upassword.length() < 6) {
                        Upassword.setError("Minimum 6 characters required");
                        Upassword.requestFocus();
                        return;
                    }
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                User user = new User(fullname, email, username);

                                FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(), "User has been registered successfully", Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                            progressBar.setVisibility(View.GONE);
                                            finish();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Failed to register ! Try Again", Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(getApplicationContext(), "Failed to register", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                        });

                    }
                }

            });


        }
    }
