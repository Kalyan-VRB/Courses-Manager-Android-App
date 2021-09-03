package com.example.coursesmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TrendingCourses extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    MyAdapter3 myAdapter;
    ArrayList<Trending> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_courses);
        recyclerView = findViewById(R.id.trendingList);
        databaseReference = FirebaseDatabase.getInstance().getReference("Trending");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Trending Courses");

        list = new ArrayList<>();
        myAdapter = new MyAdapter3(this,list);
        recyclerView.setAdapter(myAdapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Trending trending = dataSnapshot.getValue(Trending.class);
                    list.add(trending);
                }

                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}