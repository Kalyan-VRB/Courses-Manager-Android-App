package com.example.coursesmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountActivity extends AppCompatActivity {
    private Button logout;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_account);
        bottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavigation.setSelectedItemId(R.id.menuAccount);
        logout = (Button) findViewById(R.id.btnLogout);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Account");


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            reference = FirebaseDatabase.getInstance().getReference("Users");
            userID = user.getUid();

            final TextView fullname = (TextView) findViewById(R.id.tvAccName);
            final TextView email = (TextView) findViewById(R.id.tvAccEmail);
            final TextView username = (TextView) findViewById(R.id.tvAccUname);
            email.setMovementMethod(new ScrollingMovementMethod());
            fullname.setMovementMethod(new ScrollingMovementMethod());
            username.setMovementMethod(new ScrollingMovementMethod());

            reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User userProfile = snapshot.getValue(User.class);

                    if (userProfile != null) {
                        String fullName = userProfile.fullname;
                        String emailAddress = userProfile.email;
                        String userName = userProfile.username;

                        fullname.setText("Full Name : " + fullName);
                        email.setText("Email Address : " + emailAddress);
                        username.setText("User Name : " + userName);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(), "Something went wrong!!", Toast.LENGTH_LONG).show();

                }
            });
        } else {
            logout.setText("Login");
            Toast.makeText(getApplicationContext(), "Please Login to the App :)", Toast.LENGTH_LONG).show();
        }
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuAccount:
                        return true;
                    case R.id.menuHome:
                        startActivity(new Intent(getApplicationContext(), HomescreenActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.menuSettings:
                        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

}