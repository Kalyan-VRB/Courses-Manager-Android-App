package com.example.coursesmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {
    private WebView myWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        myWeb= findViewById(R.id.myWeb);
        myWeb.setWebViewClient(new WebViewClient());

        Intent intent = getIntent();
        String weblink = intent.getStringExtra("links");
        myWeb.loadUrl(weblink);
        WebSettings webSettings = myWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}