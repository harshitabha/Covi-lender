package com.example.travelban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

public class ChooseActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton homeB;
    ImageButton chooseB;
    ImageButton calB;
    Button info;
    Button go;

    Spinner dCountry;
    Spinner aCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        // initialize vars
        homeB = (ImageButton) findViewById(R.id.homeB);
        chooseB = (ImageButton) findViewById(R.id.personalizeB);
        calB = (ImageButton) findViewById(R.id.calendarB);
        info = findViewById(R.id.getInfo);
        go = findViewById(R.id.start);

        // Nav Onclick Listeners
        homeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(ChooseActivity.this, MainActivity.class);
                startActivity(home);
            }
        });

        chooseB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent choose = new Intent(ChooseActivity.this, ChooseActivity.class);
                startActivity(choose);
            }
        });

        calB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cal = new Intent(ChooseActivity.this, CalActivity.class);
                startActivity(cal);
            }
        });

        dCountry = (Spinner) findViewById(R.id.dCountry);
        aCountry = findViewById(R.id.aCountry);

        //generating the drop down menu
        String[] options = getResources().getStringArray(R.array.countries);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dCountry.setAdapter(adapter);
        aCountry.setAdapter(adapter);

        info.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
          


    }
}