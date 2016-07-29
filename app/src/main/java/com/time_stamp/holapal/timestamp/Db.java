package com.time_stamp.holapal.timestamp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by MOHAMED SHALIK on 28-07-2016.
 */
public class Db {

    public static final String rowid= "ID";
    public static final String SAD= "SourceAndDestination";
    //public static final String number= "number";

    private static final String dbname= "holapal";
    private static final String tablename= "SourceToDestination";
    private static final int dbversion= 1;

    private  DbHelper ourhelp;
    private  final Context ourcontext;
    private SQLiteDatabase ourdb;

    public long create(String s) {

        ContentValues cv=new ContentValues();
        cv.put(SAD,s);
        return ourdb.insert(tablename,null,cv);

    }



    public String[] displayname1() {
        String[] columns=new String[]{rowid,SAD};
        Cursor c=ourdb.query(tablename,columns,null,null,null,null,null);
        String[] result=new String[c.getCount()];
        int i;

        int iSAD=c.getColumnIndex(SAD);


        for (c.moveToFirst(),i=0;!c.isAfterLast();c.moveToNext(),i++)
        {
            result[i]=c.getString(iSAD);

        }

        return result;
    }


    private class DbHelper extends SQLiteOpenHelper{

        public DbHelper(Context context) {
            super(context, dbname, null, dbversion);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            sqLiteDatabase.execSQL(
                    "create table " + tablename + "(" + rowid + " INTEGER PRIMARY KEY   AUTOINCREMENT, " + SAD + " varchar  not null);"


            );


        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            sqLiteDatabase.execSQL("drop table if exists "+tablename);
            onCreate(sqLiteDatabase);


        }
    }
    public Db(Context c)
    {
        ourcontext=c;
    }

    public Db open() throws SQLException{
        ourhelp= new DbHelper(ourcontext);
        ourdb=ourhelp.getWritableDatabase();
        return this;
    }

    public void close(){
        ourhelp.close();
    }

}
