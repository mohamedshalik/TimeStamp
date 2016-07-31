package com.time_stamp.holapal.timestamp;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MOHAMED SHALIK on 31-07-2016.
 */
public class ImageTextListBaseAdapterActivity extends Activity {




    public static String[] titles = new String[] { "Bike",
            "Bus", "Car", "Walk" };

    public static  String[] descriptions = new String[] {
            "It is an aggregate accessory fruit",
            "It is the largest herbaceous flowering plant", "Citrus Fruit",
            "Mixed Fruits" };

    public static List<Integer> imglist = new ArrayList<Integer>();
    public static  Integer[] images =new Integer[]{};


    ListView listView;
    List<RowItem> rowItems;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);


        rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < titles.length; i++) {
            RowItem item = new RowItem(imglist.get(i), titles[i], descriptions[i]);
            rowItems.add(item);
        }

        listView = (ListView) findViewById(R.id.list);
        CustomBaseAdapter adapter = new CustomBaseAdapter(this, rowItems);
        listView.setAdapter(adapter);

    }


}

