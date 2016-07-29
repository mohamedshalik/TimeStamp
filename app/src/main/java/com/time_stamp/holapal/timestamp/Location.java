package com.time_stamp.holapal.timestamp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.SQLException;

public class Location extends AppCompatActivity {

    Button next,Viewbutton;
    EditText source,destinatio;
    String[] datasad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        next = (Button)findViewById(R.id.next);
        Viewbutton = (Button)findViewById(R.id.viewbutton);

        source = (EditText)findViewById(R.id.sourcetxtbox);
        destinatio = (EditText)findViewById(R.id.destinationtxtbox);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!(source.getText().toString()).equals(""))
                {
                    MainActivity.sourcetxt1=(source.getText()).toString();
                }
                else{
                    MainActivity.sourcetxt1="Source";
                }

                if (!(destinatio.getText().toString()).equals(""))
                {
                    MainActivity.destinatiotxt1=(destinatio.getText()).toString();
                }
                else {
                    MainActivity.destinatiotxt1="Destination";
                }


                Intent timestamp=new Intent("android.intent.action.TIMESTAMP");
                startActivity(timestamp);
            }
        });

        Viewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Db viewinfo = new Db(Location.this);
                try {
                    viewinfo.open();
                    datasad = viewinfo.displayname1();
                    viewinfo.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ViewLayout.classes = datasad;
                Intent next = new Intent("android.intent.action.VIEWLAYOUT");
                startActivity(next);

            }
        });

    }

}
