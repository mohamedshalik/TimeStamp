package com.time_stamp.holapal.timestamp;

import android.app.Dialog;
import android.os.Bundle;
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
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    static Button start;
    Button reset,save;
    TextView starttxt,endtxt,output,modeoftransport;
    TextView sourcetxt,destinatiotxt;
    static Date starttime, starttime1;
    int count=0;
  //  int timeDifHours=0,timeDifMinutes=0,timeDifSeconds=0;
    public static String modeoftraspotation,sourcetxt1,destinatiotxt1;
    Timer T;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        start = (Button)findViewById(R.id.startbutton);
        save = (Button)findViewById(R.id.save);
        starttxt = (TextView)findViewById(R.id.starttxt);
        endtxt = (TextView)findViewById(R.id.endtxt);
        output = (TextView)findViewById(R.id.Output);
        reset = (Button)findViewById(R.id.Reset);
        modeoftransport = (TextView)findViewById(R.id.modeoftransport);
        modeoftransport.setText(modeoftraspotation);
        sourcetxt = (TextView)findViewById(R.id.from);
        destinatiotxt = (TextView)findViewById(R.id.to);

        sourcetxt.setText(sourcetxt1);
        destinatiotxt.setText(destinatiotxt1);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (count == 0) {
                    count = 1;
                    start(starttxt,output);

                } else if (count == 1) {
                    count = 0;
                    stop(endtxt);


                }


            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean itwork=true;

                try {
                    Db entery = new Db(MainActivity.this);
                    entery.open();
                    entery.create((sourcetxt.getText().toString()+"->"+destinatiotxt.getText().toString()));
                    entery.close();
                }
                catch (Exception e)
                {
                    itwork=false;
                    Dialog d=new Dialog(MainActivity.this);
                    d.setTitle("Hey Update Stoped");
                    TextView v=new TextView(MainActivity.this);
                    v.setText(e.toString());
                    d.setContentView(v);
                    d.show();

                }finally {
                    if (itwork){
                        Dialog d=new Dialog(MainActivity.this);
                        d.setTitle("Hey Updated Sucessfully");
                        TextView v=new TextView(MainActivity.this);
                        v.setText("Success");
                        d.setContentView(v);
                        d.show();
                    }

                }
                Toast.makeText(getApplicationContext(),"Successfully Saved",Toast.LENGTH_SHORT).show();
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
                count=0;
                start.setText("Start");
                Result("Reset",null);

            }
        });

    }



   public void Result(String input, final TextView dyout){


       if (input == "Start") {
           T=new Timer();
           //schedule is method of timer class schedule(timer task, delay in milliseconds, period in milliseconds)
           T.schedule(new TimerTask() {
               @Override
               public void run() {
                   runOnUiThread(new Runnable() {
                       @Override
                       public void run() {

                           Date date= new Date();
                           //Date format
                           DateFormat formatt=new SimpleDateFormat("hh:mm:ss");
                           //note the end time for difference calculation
                           try {
                               starttime1=formatt.parse(formatt.format(date));
                           } catch (ParseException e) {
                               e.printStackTrace();
                           }

                           //calculate the difference in milliseconds
                           long diff = starttime1.getTime() - starttime.getTime();
                           //convert the milliseconds into Sec,Min and Hr
                           long timeDifSeconds = diff / 1000;
                           long timeDifMinutes = diff / (60 * 1000);
                           long timeDifHours = diff / (60 * 60 * 1000);
                           //reset the seconds and minutes value to 0 when it reaches 60
                           while (timeDifSeconds>60||timeDifMinutes>60) {
                               if (timeDifSeconds > 60) {
                                   timeDifSeconds = timeDifSeconds - 60;
                               } else if (timeDifMinutes > 60) {
                                   timeDifMinutes = timeDifMinutes - 60;
                               }
                           }
                           dyout.setText("You have taken " + timeDifHours + " hour " + timeDifMinutes + " mins " + timeDifSeconds + "secs");
                       }
                   });
               }
           }, 0, 1000);

       }
       else{
           //Terminates this timer, discarding any currently scheduled tasks
           T.cancel();
       }

   }

    public  void start(TextView starttxt,TextView output)
    {

            start.setText("Stop");
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

        Result("Start", output);

    }
    public  void stop(TextView endtxt)
    {
        start.setText("Start");
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

        Result("Stop", null);
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
