package com.time_stamp.holapal.timestamp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {


    Button start,end,reset;
    TextView starttxt,endtxt,output;
    Date starttime, starttime1;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        start = (Button)findViewById(R.id.startbutton);
        starttxt = (TextView)findViewById(R.id.starttxt);
        end = (Button)findViewById(R.id.endbutton);
        endtxt = (TextView)findViewById(R.id.endtxt);
        output = (TextView)findViewById(R.id.Output);
        reset = (Button)findViewById(R.id.Reset);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date= new Date();
                //Time Formate
                DateFormat formatt=new SimpleDateFormat("hh:mm:ss");
                //note the start time for difference calculation
                try {
                    starttime=formatt.parse(formatt.format(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //print the start time
                starttxt.setText(formatt.format(date));

            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date= new Date();
                //Date format
                DateFormat formatt=new SimpleDateFormat("hh:mm:ss");
                //note the end time for difference calculation
                try {
                    starttime1=formatt.parse(formatt.format(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //Print the end time
                endtxt.setText(formatt.format(date));
                //calculate the difference in milliseconds
                long diff = starttime1.getTime() - starttime.getTime();
                //convert the milliseconds into Sec,Min and Hr
                long timeDifSeconds = diff / 1000;
                long timeDifMinutes = diff / (60 * 1000);
                long timeDifHours = diff / (60 * 60 * 1000);

                //Tost the difference
                //Toast.makeText(getApplicationContext(),timeDifHours+"hr\t"+timeDifMinutes+"min \t"+timeDifSeconds+"sec",Toast.LENGTH_LONG).show();
                //Output MSG
                output.setText("You have taken "+timeDifHours+" hour "+timeDifMinutes+" mins "+timeDifSeconds+ "secs");
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Reset the start time
                starttxt.setText("Start Time");
                //Reset the End time
                endtxt.setText("End Time");
                //Reset the Output
                output.setText("You have taken 0 hour 0 mins 0 secs");

            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
