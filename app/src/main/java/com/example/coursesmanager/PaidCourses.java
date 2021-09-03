package com.example.coursesmanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaidCourses extends AppCompatActivity {

    private Button skillshareBtn,linkedinBtn,masterclassBtn,futurelearnBtn;
    String[] urls = new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid_courses);

        skillshareBtn = findViewById(R.id.skillshare);
        linkedinBtn = findViewById(R.id.linkedin);
        masterclassBtn = findViewById(R.id.masterclass);
        futurelearnBtn = findViewById(R.id.futurelearn);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Paid Courses");

        urls[0]="https://www.skillshare.com/";
        urls[1]="https://www.linkedin.com/";
        urls[2]="https://www.masterclass.com/";
        urls[3]="https://www.futurelearn.com/";

        skillshareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),WebActivity.class);
                intent.putExtra("links",urls[0]);
                startActivity(intent);
            }
        });
        linkedinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),WebActivity.class);
                intent.putExtra("links",urls[1]);
                startActivity(intent);
            }
        });
        masterclassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),WebActivity.class);
                intent.putExtra("links",urls[2]);
                startActivity(intent);
            }
        });
        futurelearnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),WebActivity.class);
                intent.putExtra("links",urls[3]);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}