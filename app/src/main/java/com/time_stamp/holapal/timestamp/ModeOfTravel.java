package com.time_stamp.holapal.timestamp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.sql.SQLException;

public class ModeOfTravel extends AppCompatActivity {

    ImageButton walk,bike,car,bus,train,plain;
    Button Viewbutton;
    String[] datasad,datatime,dataimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_of_travel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        walk=(ImageButton)findViewById(R.id.walk);
        bike=(ImageButton)findViewById(R.id.bike);
        car=(ImageButton)findViewById(R.id.car);
        bus=(ImageButton)findViewById(R.id.bus);
        train=(ImageButton)findViewById(R.id.train);
        plain=(ImageButton)findViewById(R.id.plain);
        Viewbutton = (Button)findViewById(R.id.viewbutton);

        walk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               action("Walk");
            }
        });

        bike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action("Bike");
            }
        });

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action("Car");
            }
        });

        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action("Bus");
            }
        });
        train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action("Train");
            }
        });
        plain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action("Airplane");
            }
        });

        Viewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*    Db viewinfo = new Db(ModeOfTravel.this);
                try {
                    viewinfo.open();
                    datasad = viewinfo.displayname1();
                    viewinfo.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ViewLayout.classes = datasad;*/
               titiledata();
               timedata();
               imgdata();
               assignimg();
                Intent next = new Intent("android.intent.action.IMLBAA");
                startActivity(next);


            }
        });



    }
    public void titiledata()
    {
        Db viewinfo = new Db(ModeOfTravel.this);
        try {
            viewinfo.open();
            datasad = viewinfo.title();
            viewinfo.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ImageTextListBaseAdapterActivity.titles = datasad;
    }

    public void timedata()
    {
        Db viewinfo = new Db(ModeOfTravel.this);
        try {
            viewinfo.open();
            datatime = viewinfo.time();
            viewinfo.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ImageTextListBaseAdapterActivity.descriptions = datatime;
    }

    public void imgdata()
    {
        Db viewinfo = new Db(ModeOfTravel.this);

        try {
            viewinfo.open();
            dataimg = viewinfo.img();
            viewinfo.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void assignimg() {

        int n=1;
        Db viewinfo = new Db(ModeOfTravel.this);

        try {
            viewinfo.open();
            n = viewinfo.norow();
            viewinfo.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(int i=0;i<n;i++)
        {
            if(dataimg[i].toString().equals("Walk"))
            {
                ImageTextListBaseAdapterActivity.imglist.add(i, R.drawable.walk);
            }
            else if (dataimg[i].toString().equals("Bike"))
            {
                ImageTextListBaseAdapterActivity.imglist.add(i, R.drawable.bike);
            }
            else if (dataimg[i].toString().equals("Car"))
            {
                ImageTextListBaseAdapterActivity.imglist.add(i, R.drawable.car);
            }
            else if (dataimg[i].toString().equals("Bus"))
            {
                ImageTextListBaseAdapterActivity.imglist.add(i, R.drawable.bus);
            }
            else if (dataimg[i].toString().equals("Train"))
            {
                ImageTextListBaseAdapterActivity.imglist.add(i, R.drawable.train);
            }
            else if (dataimg[i].toString().equals("Airplane"))
            {
                ImageTextListBaseAdapterActivity.imglist.add(i, R.drawable.plane);
            }

        }


    }



    public void action(String mode)
    {
        MainActivity.modeoftraspotation=mode;
        Intent location=new Intent("com.time_stamp.holapal.LOCATION");
        startActivity(location);
        finish();
    }
}
