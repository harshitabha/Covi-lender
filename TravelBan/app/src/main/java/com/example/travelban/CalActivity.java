package com.example.travelban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;

import java.util.Calendar;

public class CalActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton homeB;
    private ImageButton chooseB;
    private ImageButton calB;

    private CalendarView qCal;

    // initial date
    private int m;
    private int d;
    private int y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);

        // initialize vars
        homeB = (ImageButton) findViewById(R.id.homeB);
        chooseB = (ImageButton) findViewById(R.id.personalizeB);
        calB = (ImageButton) findViewById(R.id.calendarB);
        qCal = (CalendarView) findViewById(R.id.qCal);

        //set up the quarentine cal if a arrival date was given
        if(!(ChooseActivity.getDateSet() == null))
        {
            m = ChooseActivity.getMonth();
            d = ChooseActivity.getDay();
            y = ChooseActivity.getYear();
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.MONTH, m);
            cal.set(Calendar.DAY_OF_MONTH, d);
            cal.set(Calendar.YEAR, y);
            long milliTime = cal.getTimeInMillis();
            qCal.setDate(milliTime, true, true);
        }


        // Nav onclick listeners
        homeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(CalActivity.this, MainActivity.class);
                startActivity(home);
            }
        });
    
        chooseB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent choose = new Intent(CalActivity.this, ChooseActivity.class);
                startActivity(choose);
            }
        });
    
        calB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cal = new Intent(CalActivity.this, CalActivity.class);
                startActivity(cal);
            }
        });
    }

    @Override
    public void onClick(View v) {


    }
}