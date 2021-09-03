package com.example.admin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ManageCourses extends AppCompatActivity {

    private EditText courseType;
    private EditText courseName;
    private EditText courseLink;
    private Button addOngoing;
    private Button addUpcoming;
    private Button addTrending;
    DatabaseReference refOng,refUpc,refTre;
    Ongoing ongoing;
    Upcoming upcoming;
    Trending trending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_courses);

        courseType = (EditText)findViewById(R.id.courseType);
        courseName = (EditText)findViewById(R.id.courseName);
        courseLink = (EditText)findViewById(R.id.courseLink);
        addOngoing = (Button)findViewById(R.id.btnAddOngoing);
        addUpcoming = (Button)findViewById(R.id.btnAddUpcoming);
        addTrending = (Button)findViewById(R.id.btnAddTrending);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Manage Courses");

        refOng= FirebaseDatabase.getInstance().getReference().child("Ongoing");
        refUpc= FirebaseDatabase.getInstance().getReference().child("Upcoming");
        refTre= FirebaseDatabase.getInstance().getReference().child("Trending");
        ongoing = new Ongoing();
        upcoming = new Upcoming();
        trending = new Trending();

        addOngoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String type,name,link;
                type = String.valueOf(courseType.getText());
                name = String.valueOf(courseName.getText());
                link = String.valueOf(courseLink.getText());

                if(type.isEmpty()){
                    courseType.setError("Field required");
                    courseType.requestFocus();
                    return;
                }
                if(name.isEmpty()){
                    courseName.setError("Field required");
                    courseName.requestFocus();
                    return;
                }
                if(link.isEmpty()){
                    courseLink.setError("Field required");
                    courseLink.requestFocus();
                    return;
                }

                ongoing.setType(courseType.getText().toString().trim());
                ongoing.setName(courseName.getText().toString().trim());
                ongoing.setLink(courseLink.getText().toString().trim());

                refOng.push().setValue(ongoing);
                Toast.makeText(ManageCourses.this, "Course added Successfully", Toast.LENGTH_LONG).show();
            }
        });
        addTrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String type,name,link;
                type = String.valueOf(courseType.getText());
                name = String.valueOf(courseName.getText());
                link = String.valueOf(courseLink.getText());

                if(type.isEmpty()){
                    courseType.setError("Field required");
                    courseType.requestFocus();
                    return;
                }
                if(name.isEmpty()){
                    courseName.setError("Field required");
                    courseName.requestFocus();
                    return;
                }
                if(link.isEmpty()){
                    courseLink.setError("Field required");
                    courseLink.requestFocus();
                    return;
                }

                trending.setType(courseType.getText().toString().trim());
                trending.setName(courseName.getText().toString().trim());
                trending.setLink(courseLink.getText().toString().trim());

                refTre.push().setValue(trending);
                Toast.makeText(ManageCourses.this, "Course added Successfully", Toast.LENGTH_LONG).show();
            }
        });

        addUpcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String type,name,link;
                type = String.valueOf(courseType.getText());
                name = String.valueOf(courseName.getText());
                link = String.valueOf(courseLink.getText());

                if(type.isEmpty()){
                    courseType.setError("Field required");
                    courseType.requestFocus();
                    return;
                }
                if(name.isEmpty()){
                    courseName.setError("Field required");
                    courseName.requestFocus();
                    return;
                }
                if(link.isEmpty()){
                    courseLink.setError("Field required");
                    courseLink.requestFocus();
                    return;
                }

                upcoming.setType(courseType.getText().toString().trim());
                upcoming.setName(courseName.getText().toString().trim());
                upcoming.setLink(courseLink.getText().toString().trim());

                refUpc.push().setValue(upcoming);
                Toast.makeText(ManageCourses.this, "Course added Successfully", Toast.LENGTH_LONG).show();
            }
        });
    }
}