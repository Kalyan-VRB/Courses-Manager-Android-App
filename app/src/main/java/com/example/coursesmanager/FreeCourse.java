package com.example.coursesmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FreeCourse extends AppCompatActivity {
    private Button coureraBtn,udemyBtn,udacityBtn,edxBtn,stanfordBtn;
    String[] urls = new String[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_course);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Free Courses");

        coureraBtn = findViewById(R.id.coursera);
        udemyBtn = findViewById(R.id.udemy);
        udacityBtn = findViewById(R.id.udacity);
        edxBtn = findViewById(R.id.edx);
        stanfordBtn = findViewById(R.id.stanford);

        urls[0]="https://www.coursera.com/";
        urls[1]="https://www.udemy.com/";
        urls[2]="https://www.udacity.com/";
        urls[3]="https://www.edx.org/";
        urls[4]="https://www.stanford.com/";

        coureraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),WebActivity.class);
                intent.putExtra("links",urls[0]);
                startActivity(intent);
            }
        });
        udemyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),WebActivity.class);
                intent.putExtra("links",urls[1]);
                startActivity(intent);
            }
        });
        udacityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),WebActivity.class);
                intent.putExtra("links",urls[2]);
                startActivity(intent);
            }
        });
        edxBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),WebActivity.class);
                intent.putExtra("links",urls[3]);
                startActivity(intent);
            }
        });
        stanfordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),WebActivity.class);
                intent.putExtra("links",urls[4]);
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