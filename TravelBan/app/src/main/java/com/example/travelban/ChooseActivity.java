package com.example.travelban;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChooseActivity
        extends AppCompatActivity
        implements View.OnClickListener {

    private ImageButton homeB;
    private ImageButton chooseB;
    private ImageButton calB;
    private Button info;
    private Button go;
    private Button dateButton;

    private Spinner dCountry;
    private Spinner aCountry;

    private TextView infoCard;

    //selected date month and year
    private static int mSelect;
    private static int dSelect;
    private static int ySelect;
    private static String selectedDCountry;
    private static String selectedACountry;
    private static Boolean dateSet;

    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        // initialize vars
        homeB = (ImageButton) findViewById(R.id.homeB);
        chooseB = (ImageButton) findViewById(R.id.personalizeB);
        //calB = (ImageButton) findViewById(R.id.calendarB);
        info = findViewById(R.id.getInfo);
        go = findViewById(R.id.start);
        dateButton = (Button) findViewById(R.id.date);
        infoCard = findViewById(R.id.infoCard);

        dateSet = false;

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

        dCountry = (Spinner) findViewById(R.id.dCountry);
        aCountry = findViewById(R.id.aCountry);

        //generating the drop down menu
        String[] options = getResources().getStringArray(R.array.countries);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dCountry.setAdapter(adapter);
        aCountry.setAdapter(adapter);

        info.setOnClickListener(this);

        // date stuff
        initDatePicker();
        dateButton.setText(getTodaysDate());

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDCountry = dCountry.getSelectedItem().toString();
                selectedACountry = aCountry.getSelectedItem().toString();

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.MONTH, mSelect);
                cal.set(Calendar.DAY_OF_MONTH, dSelect);
                cal.set(Calendar.YEAR, ySelect);
                long arrival = cal.getTimeInMillis();

                int[] endDate = dateGetter(selectedACountry, selectedDCountry, mSelect, dSelect, ySelect);
                cal.set(Calendar.MONTH, endDate[0]);
                cal.set(Calendar.DAY_OF_MONTH, endDate[1]);
                cal.set(Calendar.YEAR, endDate[2]);
                long endQuarentine = cal.getTimeInMillis();

                addEvent("Required Quarantine", selectedACountry, arrival, endQuarentine);
            }
        });
    }

    // on click listener for the info button
    @Override
    public void onClick(View v) {
        selectedDCountry = dCountry.getSelectedItem().toString();
        selectedACountry = aCountry.getSelectedItem().toString();
        int dCountryIndex = getCountryIndex(selectedDCountry);
        int aCountryIndex = getCountryIndex(selectedACountry);

        updateInfoCard(dCountryIndex, aCountryIndex);

    }

    // add how long they need to quarantine to a calendar
    public void addEvent(String title, String location, long begin, long end) {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(CalendarContract.Events.CONTENT_URI);
        intent.putExtra(CalendarContract.Events.TITLE, title);
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, location);
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin);
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(ChooseActivity.this, "There is no app that can handle this action", Toast.LENGTH_LONG).show();
        }
    }

    private void updateInfoCard(int countryA, int countryB) {
        /**
         * order for each is as follows
         * {us, uk, india, china, japan}
        */
        String[] US = {"",
                "Unvaccinated visitors from the United States need to provide a negative COVID-19 test result and quarantine to enter the United Kingdom." +
                        " Fully vaccinated visitors from the United States can enter the United Kingdom without restrictions. Unvaccinated visitors from the United States will " +
                        "need to quarantine for 10 days upon entering the United Kingdom. Fully vaccinated visitors with approved vaccination certificates do not need to quarantine.",
                "Most visitors from the United States will not be allowed to enter India. Visitors from the United States will need to quarantine for 14 days upon entering India." +
                        " Details and exceptions apply. Anyone arriving to India will be required to go into 7 days quarantine at an institution at their own cost, followed by 7 days self-quarantine.",
                "Most visitors from the United States will not be allowed to enter China. Visitors from the United States will need to quarantine for 14 days upon entering China.",
                "Most visitors from the United States will not be allowed to enter Japan. Visitors from the United States will need to quarantine for 14 days upon entering Japan."};

        String[] UK = {"Most visitors from the United Kingdom will not be allowed to enter the United States. Visitors from the United Kingdom are not required to " +
                "quarantine after entering the United States. Details and exceptions apply. Travelers are not required but recommended to quarantine between 7-10 days after " +
                "arrival in the US.",
                "",
                "Most visitors from the United Kingdom will not be allowed to enter India. Visitors from the United Kingdom will need to quarantine " +
                        "for 14 days upon entering India. Details and exceptions apply. Anyone arriving to India will be required to go into 7 days quarantine at an institution at their own cost, " +
                        "followed by 7 days self-quarantine.",
                "Most visitors from the United Kingdom will not be allowed to enter China. Visitors from the United Kingdom will need to quarantine for 14 days upon entering China.",
                "Most visitors from the United Kingdom will not be allowed to enter Japan. Visitors from the United Kingdom will need to quarantine for 14 days upon entering Japan."};

        String[] India = {"Most visitors from India will not be allowed to enter the United States. Visitors from India are not required to quarantine after entering the United States. " +
                "Details and exceptions apply. Travelers are not required but recommended to quarantine between 7 - 10 days after arrival in the US.",
                "Unvaccinated visitors from India need to provide a negative COVID - 19 test result and quarantine to enter the United Kingdom. Fully " +
                        "vaccinated visitors from India can enter the United Kingdom without restrictions. Unvaccinated visitors from India will need " +
                        "to quarantine for 10 days upon entering the United Kingdom. Fully vaccinated visitors with approved vaccination certificates do not" +
                        " need to quarantine.",
                " ",
                "Most visitors from India will not be allowed to enter China. Visitors from India will need to quarantine for 14 days upon entering China.",
                "Most visitors from India will not be allowed to enter Japan. Visitors from India will need to quarantine for 14 days upon entering Japan."};

        String[] China = {"Most visitors from China will not be allowed to enter the United States. Visitors from China are not required to quarantine after entering " +
                "the United States. Details and exceptions apply. Travelers are not required but recommended to quarantine between 7 - 10 days after arrival in the US.",
                "Unvaccinated visitors from China need to provide a negative COVID -19 test result and quarantine to enter the United Kingdom. Fully vaccinated" +
                        "visitors from China can enter the United Kingdom without restrictions. Unvaccinated visitors from China will need to quarantine for " +
                        "10 days upon entering the United Kingdom. Fully vaccinated visitors with approved vaccination certificates do not need to quarantine.",
                "Most visitors from China will not be allowed to enter India. Visitors from China will need to quarantine for 14 days upon entering India. Details and exceptions apply." +
                        " Anyone arriving to India will be required to go into 7 days quarantine at an institution at their own cost, followed by 7 days self -quarantine. ",
                " ",
                "Most visitors from China will not be allowed to enter the Japan. Visitors from China will need to quarantine for 14 days upon entering Japan. Details and exceptions " +
                        "apply. All travelers, including Japanese nationals, are required to quarantine at a location designated by the quarantine station chief. Travelers are " +
                        "required to take the COVID - 19 test again on the 3 rd day of quarantine. Travelers who test negative are still required to self - " +
                        "quarantine at home until completion of 14 days after entering Japan."};

        String[] Japan = {"Most visitors from Japan need to provide a negative COVID-19 test result to enter the United States. Visitors from Japan are not required to quarantine " +
                "after entering the United States. Details and exceptions apply. Travelers are not required but are recommended to quarantine between 7 - 10 days after " +
                "arrival in the US.",
                "Unvaccinated visitors from Japan need to provide a negative COVID - 19 test result and quarantine to enter the United Kingdom. Fully vaccinated visitors from " +
                        "Japan can enter the United Kingdom without restrictions. Unvaccinated visitors from Japan will need to quarantine for 10 days upon entering the United " +
                        "Kingdom. Fully vaccinated visitors with approved vaccination certificates do not need to quarantine.",
                "Most visitors from Japan will not be allowed to enter India. Visitors from Japan will need to quarantine for 14 days upon entering India.",
                "Most visitors from Japan will not be allowed to enter China. Visitors from Japan will need to quarantine for 14 days upon entering China.",
                ""};

        String[][] countries = {US, UK, India, China, Japan};
        infoCard.setText(countries[countryA][countryB] + "\nInformation was gathered from https://www.kayak.com/travel-restrictions?&region=asia&origin=US");

    }

    private int getCountryIndex(String country) {
        int index;

        if (country.equals("USA")) index = 0;
        else if (country.equals("United Kingdom")) index = 1;
        else if (country.equals("India")) index = 2;
        else if (country.equals("China")) index = 3;
        else index = 4;
        return index;
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        ySelect = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        mSelect = month;
        month = month + 1;
        dSelect = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(dSelect, month, ySelect);
    }

    // pick your date
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // for sending vars to next screen
                mSelect = month;
                dSelect = day;
                ySelect = year;

                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
                dateSet = true;
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    //channing the date formatting so it days the month abr, date, then year to avoid confusion
    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    // figuring out which month string to return
    private String getMonthFormat(int m) {
        if (m == 1) return "JAN";
        else if (m == 2) return "FEB";
        else if (m == 3) return "MAR";
        else if (m == 4) return "APR";
        else if (m == 5) return "MAY";
        else if (m == 6) return "JUN";
        else if (m == 7) return "JUL";
        else if (m == 8) return "AUG";
        else if (m == 9) return "SEPT";
        else if (m == 10) return "OCT";
        else if (m == 11) return "NOV";
        return "DEC";
    }

    // on click listener for date picker
    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    private int[] dateGetter(String aCountry, String dCountry, int mon, int day, int year){
        int qua1 = 14;
        int qua2 = 10;
        int qua3 = 7;

        int qDate = day; // should never happen

        if(dCountry.equals("USA")){
            if(aCountry.equals("United Kingdom")){
                qDate = day + qua2;
            }
            else if(aCountry.equals("India")){
                qDate = day + qua1;
            }
            else if(aCountry.equals("China")){
                qDate = day + qua1;
            }
            else if(aCountry.equals("Japan")){
                qDate = day + qua1;
            }
        }
        else if(dCountry.equals("United Kingdom")){
            if(aCountry.equals("USA")){
                qDate = day + qua3;
            }
            else if(aCountry.equals("India")){
                qDate = day + qua1;
            }
            else if(aCountry.equals("China")){
                qDate = day + qua1;
            }
            else if(aCountry.equals("Japan")){
                qDate = day + qua1;
            }
        }
        else if(dCountry.equals("India")){
            if(aCountry.equals("USA")){
                qDate = day + qua3;
            }
            else if(aCountry.equals("United Kingdom")){
                qDate = day + qua2;
            }
            else if(aCountry.equals("China")){
                qDate = day + qua1;
            }
            else if(aCountry.equals("Japan")){
                qDate = day + qua1;
            }
        }
        else if(dCountry.equals("China")){
            if(aCountry.equals("USA")){
                qDate = day + qua3;
            }
            else if(aCountry.equals("United Kingdom")){
                qDate = day + qua1;
            }
            else if(aCountry.equals("India")){
                qDate = day + qua2;
            }
            else if(aCountry.equals("Japan")){
                qDate = day + qua2;
            }
        }
        else{ // traveling from japan
            if(aCountry.equals("USA")){
                qDate = day + qua3;
            }
            else if(aCountry.equals("United Kingdom")){
                qDate = day + qua2;
            }
            else if(aCountry.equals("India")){
                qDate = day + qua1;
            }
            else if(aCountry.equals("China")){
                qDate = day + qua1;
            }
        }
        return formatDate(mon, qDate, year);
    }

    private int[] formatDate(int m, int d, int y)
    {
        int daysInMonth = number_of_days(m, leap_year(y));
        int day_of_month;
        if(d > daysInMonth) // if the date goes to the next month
        {
            daysInMonth = number_of_days((m + 1) % 12, leap_year(y)); // modular div makes sure the month is one of the 12 months
            if(m == 12)
            {
                m = 1;
                y++;
            }
        }
        day_of_month = d % daysInMonth;

        int[] date = {m, day_of_month, y};
        return date;
    }

    //determines if the year is a leap Year
    private int leap_year(int y){
        if(y % 4 == 0) return 1;
        else return 0;
    }

    //determines the number of days in the month
    private int number_of_days(int month, int leap)
    {
        if(month == 0) month = 12;
        int numofDays;
        if(month < 8 && month % 2 == 1) numofDays = 31;
        else if(month < 8 && month % 2 == 0 && month != 2) numofDays = 30;
        else if(month >= 8 && month % 2 == 0) numofDays = 31;
        else if(month >= 8 && month % 2 == 1) numofDays = 30;


        else
        {
            //the number of days in feb in a leap year
            if(leap == 1) numofDays = 29;
                //the number of days in feb in a normal year
            else numofDays = 28;
        }

        return numofDays;
    }


}