package com.example.coursesmanager;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.Key;

public class HomescreenActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    private TextView userName;
    private ImageView manageCourses;
    private ImageView freeCourses;
    private ImageView paidCourses;
    private ImageView upcomingCourses;
    private ImageView ongoingCourses;
    private ImageView calendar;
    private TextView trending;
    private ImageView requestCourse;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    private String emailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        userName = (TextView) findViewById(R.id.textUsername);
        bottomNavigation = findViewById(R.id.bottomNavigationView);
        freeCourses = (ImageView) findViewById(R.id.freeCourses);
        paidCourses = (ImageView) findViewById(R.id.paidCourses);
        ongoingCourses = (ImageView) findViewById(R.id.ongoingCourses);
        upcomingCourses = (ImageView) findViewById(R.id.upcomingCourses);
        calendar = (ImageView) findViewById(R.id.calendar);
        requestCourse = (ImageView)findViewById(R.id.requestCourse);
        trending = (TextView) findViewById(R.id.textTrending);
        bottomNavigation.setSelectedItemId(R.id.menuHome);
        freeCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent free = new Intent(getApplicationContext(), FreeCourse.class);
                startActivity(free);
            }
        });
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cal = new Intent(getApplicationContext(), Calendar.class);
                startActivity(cal);
            }
        });
        trending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tren = new Intent(getApplicationContext(), TrendingCourses.class);
                startActivity(tren);
            }
        });
        ongoingCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ongoing = new Intent(getApplicationContext(), OngoingCourses.class);
                startActivity(ongoing);
            }
        });
        upcomingCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent upcoming = new Intent(getApplicationContext(), UpcomingCourses.class);
                startActivity(upcoming);
            }
        });
        paidCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paid = new Intent(getApplicationContext(), PaidCourses.class);
                startActivity(paid);
            }
        });


        user = FirebaseAuth.getInstance().getCurrentUser();

        if(user!=null) {
            reference = FirebaseDatabase.getInstance().getReference("Users");
            userID = user.getUid();


            final TextView username = (TextView) findViewById(R.id.tvAccUname);

            reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User userProfile = snapshot.getValue(User.class);

                    if (userProfile != null) {
                        String fullName = userProfile.fullname;
                        emailAddress = userProfile.email;
                        userName.setText(fullName);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(), "Something went wrong!!", Toast.LENGTH_LONG).show();

                }
            });
        }

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuAccount:
                        startActivity(new Intent(getApplicationContext(), AccountActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menuHome:
                        return true;
                    case R.id.menuSettings:
                        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        requestCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user!=null) {
                    Intent req = new Intent(getApplicationContext(), RequestCourses.class);
                    startActivity(req);
                }else{
                    Toast.makeText(HomescreenActivity.this,"Please Login to request a course",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}

