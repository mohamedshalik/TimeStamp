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

public class ModeOfTravel extends AppCompatActivity {

    ImageButton walk,bike,car,bus;
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


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void action(String mode)
    {
        MainActivity.modeoftraspotation=mode;
        Intent timestamp=new Intent("android.intent.action.TIMESTAMP");
        startActivity(timestamp);
    }
}
