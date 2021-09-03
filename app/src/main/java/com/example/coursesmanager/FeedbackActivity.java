package com.example.coursesmanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackActivity extends AppCompatActivity {

    private EditText nameF;
    private EditText emailF;
    private EditText feedBack;
    private Button submitF;

    DatabaseReference refFeed;

    Feedback feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Feedback");

        nameF = (EditText)findViewById(R.id.nameFeedback);
        emailF = (EditText)findViewById(R.id.emailFeedback);
        feedBack = (EditText)findViewById(R.id.textFeedback);
        submitF = (Button)findViewById(R.id.btnFeedback);

        refFeed= FirebaseDatabase.getInstance().getReference().child("Feedback");
        feedback = new Feedback();

        submitF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name,email,fback;
                name = String.valueOf(nameF.getText());
                email = String.valueOf(emailF.getText());
                fback = String.valueOf(feedBack.getText());

                if(name.isEmpty()){
                    nameF.setError("Please enter your name.");
                    nameF.requestFocus();
                    return;
                }
                if(email.isEmpty()){
                    emailF.setError("Please enter your email address");
                    emailF.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailF.setError("Please enter a valid email");
                    emailF.requestFocus();
                    return;
                }
                if(fback.length()<10){
                    feedBack.setError("Please write your feedback in atleast 10 characters");
                    feedBack.requestFocus();
                    return;
                }

                feedback.setName(nameF.getText().toString().trim());
                feedback.setName(emailF.getText().toString().trim());
                feedback.setFeedback(feedBack.getText().toString().trim());

                refFeed.push().setValue(feedback);

                Toast.makeText(getApplicationContext(),"Thank You :) ! Your feedback is submitted successfully!!",Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}