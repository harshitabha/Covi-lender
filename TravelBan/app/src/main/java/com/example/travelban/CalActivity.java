package com.example.travelban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class CalActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton homeB;
    private ImageButton chooseB;
    private ImageButton calB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);

        // initilize vars
        homeB = (ImageButton) findViewById(R.id.homeB);
        chooseB = (ImageButton) findViewById(R.id.personalizeB);
        calB = (ImageButton) findViewById(R.id.calendarB);

        // Nav onclick listerners
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