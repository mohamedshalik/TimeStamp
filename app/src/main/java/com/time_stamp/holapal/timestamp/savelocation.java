package com.time_stamp.holapal.timestamp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class savelocation extends Activity {

    EditText source,destination;
    Button save;
    public static String s2,s3;
    public static String s=null,d=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savelocation);
        save=(Button)findViewById(R.id.save);
        source=(EditText)findViewById(R.id.SaveSource);
        destination=(EditText)findViewById(R.id.SaveDestination);


           source.setText(s);
           destination.setText(d);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (source.getText().toString().equals("") ) {
                    Dialog d = new Dialog(savelocation.this);
                    d.setTitle("Please enter Source and Destination");
                    TextView v = new TextView(savelocation.this);
                    v.setText("Source and Destination field can not be Empty");
                    d.setContentView(v);
                    d.show();
                } else if ((destination.getText().toString()).equals("")) {
                    Dialog d = new Dialog(savelocation.this);
                    d.setTitle("Please enter Source and Destination");
                    TextView v = new TextView(savelocation.this);
                    v.setText("Source and Destination field can not be Empty");
                    d.setContentView(v);
                    d.show();
                }
                else
                {
                        boolean itwork = true;

                        try {
                            Db entery = new Db(savelocation.this);
                            entery.open();
                            entery.create((source.getText().toString() + "->" + destination.getText().toString()),s2,s3);
                            entery.close();
                        } catch (Exception e) {
                            itwork = false;
                            Dialog d = new Dialog(savelocation.this);
                            d.setTitle("Saving data failed");
                            TextView v = new TextView(savelocation.this);
                            v.setText(e.toString());
                            d.setContentView(v);
                            d.show();

                        } finally {
                            if (itwork) {
                                Toast.makeText(savelocation.this, "Successfully Saved", Toast.LENGTH_SHORT).show();
                            }
                            Intent location=new Intent("android.intent.action.MODEOFTRAVEL");
                            startActivity(location);
                            finish();
                        }

                    }

                }

        });
    }

}
