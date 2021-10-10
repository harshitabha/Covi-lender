package com.example.travelban;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;

import java.util.ArrayList;
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

        /*// initialize vars
        homeB = (ImageButton) findViewById(R.id.homeB);
        chooseB = (ImageButton) findViewById(R.id.personalizeB);
        calB = (ImageButton) findViewById(R.id.calendarB);
        qCal = (CalendarView) findViewById(R.id.qCal);

        //set up the quarantine cal if a arrival date was given
        if(!(ChooseActivity.getDateSet() == null))
        {
            m = ChooseActivity.getMonth();
            d = ChooseActivity.getDay();
            y = ChooseActivity.getYear();
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.MONTH, m);
            cal.set(Calendar.DAY_OF_MONTH, d);
            cal.set(Calendar.YEAR, y);
            long milliATime = cal.getTimeInMillis();
            qCal.setDate(milliATime, true, true);

            // highlighting the quarantine days
            int[] qEndDate = dateGetter(ChooseActivity.getACountry(), ChooseActivity.getDCountry(), m, d, y);
            markQDates();
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
        });*/
    }

    @Override
    public void onClick(View v) {


    }

    private void markQDates()
    {
        /*ArrayList<DateData> dates=new ArrayList<>();
        dates.add(new DateData(2018,04,26));
        dates.add(new DateData(2018,04,27));

        for(int i=0;i<dates.size();i++) {
            //mark multiple dates with this code.
            qCal.markDate(dates.get(i).getYear(),dates.get(i).getMonth(),dates.get(i).getDay());
        }*/
    }
    private int[] dateGetter(String aCountry, String dCountry, int mon, int day, int year){
        int qua1 = 14;
        int qua2 = 10;
        int qua3 = 7;

        int qDate;

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
        return formatDate(mon, day, year);
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