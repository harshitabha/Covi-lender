package com.example.travelban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class ChooseActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton homeB;
    ImageButton chooseB;
    ImageButton calB;
    Button info;
    Button go;

    Spinner dCountry;
    Spinner aCountry;

    TextView infoCard;

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
        infoCard = findViewById(R.id.infoCard);

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

    // on click listener for the info button
    @Override
    public void onClick(View v) {
        String selectedDCountry = dCountry.getSelectedItem().toString();
        String selectedACountry = aCountry.getSelectedItem().toString();
        int dCountryIndex = getCountryIndex(selectedDCountry);
        int aCountryIndex = getCountryIndex(selectedACountry);

        updateInfoCard(dCountryIndex, aCountryIndex);

    }

    private void updateInfoCard(int countryA, int countryB){

        /*let US = ["","Unvaccinated visitors from the United States need to provide a negative COVID-19 test result and quarantine to enter the United " +
                "Kingdom.Fully vaccinated visitors from the United States can enter the United Kingdom without restrictions.",
                "Most visitors from the United States will not be allowed to enter India",
                "Most visitors from the United States will not be allowed to enter China.","Most visitors from the United States
        will not be allowed to enter Japan."]; // from this country

        let UK = ["Most visitors from the United Kingdom will not be allowed to enter the United States.","","Most visitors from the United Kingdom will
        not be allowed to enter India.","Most visitors from the United Kingdom will not be allowed to enter China.","Most visitors from the United Kingdom
        will not be allowed to enter Japan."];

        let India = ["Most visitors from India will not be allowed to enter the United States.","Unvaccinated visitors from India need to provide a negative
        COVID-19 test result and quarantine to enter the United Kingdom.Fully vaccinated visitors from India can enter the United Kingdom without restrictions.","","Most visitors from India will not be allowed to enter China.","Most visitors
        from India will not be allowed to enter Japan."];

        let China = ["Most visitors from China will not be allowed to enter the United States.","Unvaccinated visitors from China need to provide a
        negative COVID-19 test result and quarantine to enter the United Kingdom. Fully vaccinated visitors from China can enter the United Kingdom without
        restrictions.","Most visitors from China will not be allowed to enter India.","","Most visitors from China will not be allowed to enter the Japan."];

        let Japan = ["Most visitors from Japan need to provide a negative COVID-19 test result to enter the United States.","Unvaccinated visitors from
        Japan need to provide a negative COVID-19 test result and quarantine to enter the United Kingdom.Fully vaccinated visitors from Japan can enter
        the United Kingdom without restrictions.","Most visitors from Japan will not be allowed to enter India.","Most visitors from Japan will not be allowed to enter China.",""];

        let countries = [US,UK,India,China,Japan];
        infoCard.setText(countries[countryA][countryB]);
        */
    }

    private int getCountryIndex(String country)
    {
        int index;

        if(country.equals("USA")) index = 0;
        else if(country.equals("United Kingdom")) index = 1;
        else if(country.equals("India")) index = 2;
        else if(country.equals("China")) index = 3;
        else index = 4;
        return index;
    }
}