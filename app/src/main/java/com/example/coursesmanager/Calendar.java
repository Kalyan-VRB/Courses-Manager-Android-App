package com.example.coursesmanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Objects;

public class Calendar extends AppCompatActivity {

    CompactCalendarView compactCalendar;
    private TextView monthYear;
    private TextView eventText;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM-yyyy", Locale.getDefault());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_view);
        final ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle("Calendar");
        Date today = new Date();


        HashMap<String, String> events = new HashMap<>();
        events.put("Sat Aug 14 2021","Cognizant Technical Assessment @06:30PM");
        events.put("Mon Aug 09 2021","Modak Pre-placement Talk, Test @11:00AM");
        eventText = findViewById(R.id.aboutEvent);
        monthYear = findViewById(R.id.dateSelected);
        eventText.setText("");
        monthYear.setText(getMonthForInt(today.getMonth())+"-"+(2000+today.getYear()%100));
        compactCalendar = (CompactCalendarView) findViewById(R.id.calendarview);
        compactCalendar.setUseThreeLetterAbbreviation(true);

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();
                String date = dateClicked.toString();
                if (events.containsKey(date.substring(0,11)+date.substring(30))) {
                    eventText.setText(events.get(date.substring(0,11)+date.substring(30)));
                } else {
                    eventText.setText("No Events Planned for that day");
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                monthYear.setText(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });
    }
    String getMonthForInt(int num){
        String month="wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if(num>=0&&num<=11){
            month=months[num];
        }
        return month;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}