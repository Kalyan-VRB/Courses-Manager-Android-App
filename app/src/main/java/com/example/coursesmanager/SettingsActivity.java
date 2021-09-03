package com.example.coursesmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    private TextView about;
    private TextView feedback;
    private boolean aboutClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        bottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavigation.setSelectedItemId(R.id.menuSettings);

        about = (TextView)findViewById(R.id.about);
        feedback = (TextView)findViewById(R.id.feedback);
        about.setText("About");

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),FeedbackActivity.class));
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutClicked = !aboutClicked;
                if(aboutClicked) {
                    about.setText("Courses Manager App:\n\t\tExplore the Top E-Learning Websites and courses at one place.");
                }
                else {
                    about.setText("About");
                }
            }
        });

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Settings");
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuAccount:
                        startActivity(new Intent(getApplicationContext(), AccountActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.menuHome:
                        startActivity(new Intent(getApplicationContext(), HomescreenActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.menuSettings:
                        return true;
                }
                return false;
            }
        });
    }
}