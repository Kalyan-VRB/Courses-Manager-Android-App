package com.example.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText Email;
    private EditText Upassword;
    private Button Login;
    private TextView Signup;
    private ProgressBar progressBar;
    private  TextView forgotPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main);
        Email = (EditText) findViewById(R.id.etMail);
        Upassword = (EditText) findViewById(R.id.etPassword);
        Login = (Button) findViewById(R.id.btnLogin);
        Signup = (TextView) findViewById(R.id.tvSignup);
        progressBar = (ProgressBar)findViewById(R.id.progress);
        forgotPassword = (TextView)findViewById(R.id.forgotPwd);

        mAuth = FirebaseAuth.getInstance();


        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Authentication");

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ForgotPassword.class));
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email, password;
                email = String.valueOf(Email.getText());
                password = String.valueOf(Upassword.getText());
                if(email.isEmpty()){
                    Email.setError("Email is required");
                    Email.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Email.setError("Please enter a valid email");
                    Email.requestFocus();
                    return;
                }
                if(password.isEmpty()){
                    Upassword.setError("Password is required");
                    Upassword.requestFocus();
                    return;
                }
                if(password.length()<6){
                    Upassword.setError("Minimum 6 characters required");
                    Upassword.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if(user.isEmailVerified()) {
                                startActivity(new Intent(getApplicationContext(), ManageCourses.class));
                                finish();
                            }
                            else{
                                user.sendEmailVerification();
                                Toast.makeText(getApplicationContext(),"Check your mail to verify your Account!",Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Failed to Login! Please check Username and Password",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }

        });

    }
}