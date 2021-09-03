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

public class RequestCourses extends AppCompatActivity {

    private EditText nameR;
    private EditText emailR;
    private EditText infoR;
    private Button request;

    DatabaseReference refReq;

    RequestCourse requestCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_courses);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Request Course");

        nameR = (EditText)findViewById(R.id.courseRequest);
        emailR = (EditText)findViewById(R.id.emailRequest);
        infoR = (EditText)findViewById(R.id.textAdditional);
        request = (Button)findViewById(R.id.btnRequest);

        refReq= FirebaseDatabase.getInstance().getReference().child("RequestCourse");
        requestCourse = new RequestCourse();

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name,email,fback;
                name = String.valueOf(nameR.getText());
                email = String.valueOf(emailR.getText());
                fback = String.valueOf(infoR.getText());

                if(name.isEmpty()){
                    nameR.setError("Please enter a course name.");
                    nameR.requestFocus();
                    return;
                }
                if(email.isEmpty()){
                    emailR.setError("Please enter your email address");
                    emailR.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailR.setError("Please enter a valid email");
                    emailR.requestFocus();
                    return;
                }

                requestCourse.setCoursename(nameR.getText().toString().trim());
                requestCourse.setEmail(emailR.getText().toString().trim());
                requestCourse.setInformation(infoR.getText().toString().trim());

                refReq.push().setValue(requestCourse);

                Toast.makeText(getApplicationContext(),"Your request is submitted successfully!!",Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}