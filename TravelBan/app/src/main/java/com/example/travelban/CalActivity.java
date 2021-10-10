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

    private void showWarning() {
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(CalActivity.this);

        // Set the message show for the Alert time
        builder.setMessage("Please choose an arrival date from the personalize window to view a quarantine calendar. " +
                "It can be accessed via the right most button in the navigation bar");

        // Set Alert Title
        builder.setTitle("Warning!");

        // Set Cancelable false
        // for when the user clicks on the outside
        // the Dialog Box then it will remain show
        builder.setCancelable(false);

        builder
                .setPositiveButton(
                        "OK",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {

                                // If user click no
                                // then dialog box is closed.
                                dialog.cancel();
                            }
                        });


        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();
    }
}