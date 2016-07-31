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

    public static final String Mtraval= "ModeOfTravel";
    public static final String SAD= "SourceAndDestination";
    public static final String TimeStamp= "time";

    private static final String dbname= "holapal";
    private static final String tablename= "SourceToDestination";
    private static final int dbversion= 1;

    private  DbHelper ourhelp;
    private  final Context ourcontext;
    private SQLiteDatabase ourdb;

    public long create(String s,String s2,String s3) {

        ContentValues cv=new ContentValues();
        cv.put(Mtraval,s3);
        cv.put(SAD,s);
        cv.put(TimeStamp,s2);
        return ourdb.insert(tablename,null,cv);

    }



    public String[] displayname1() {
        String[] columns=new String[]{Mtraval,SAD,TimeStamp};
        Cursor c=ourdb.query(tablename,columns,null,null,null,null,null);
        String[] result=new String[c.getCount()];
        int i;

        int iSAD=c.getColumnIndex(SAD);
        int iTimeStamp=c.getColumnIndex(TimeStamp);
        int iMtraval=c.getColumnIndex(Mtraval);

        for (c.moveToFirst(),i=0;!c.isAfterLast();c.moveToNext(),i++)
        {
            result[i]=c.getString(iSAD)+" : "+c.getString(iTimeStamp);

        }

        return result;
    }

    public String[] img() {

        String[] columns=new String[]{Mtraval,SAD,TimeStamp};
        Cursor c=ourdb.query(tablename,columns,null,null,null,null,null);
        String[] result=new String[c.getCount()];
        int i;

        int iMtraval=c.getColumnIndex(Mtraval);

        for (c.moveToFirst(),i=0;!c.isAfterLast();c.moveToNext(),i++)
        {
            result[i]= c.getString(iMtraval);
        }

        return result;
    }

    public String[] time() {

        String[] columns=new String[]{Mtraval,SAD,TimeStamp};
        Cursor c=ourdb.query(tablename,columns,null,null,null,null,null);
        String[] result=new String[c.getCount()];
        int i;

        int iTimeStamp=c.getColumnIndex(TimeStamp);

        for (c.moveToFirst(),i=0;!c.isAfterLast();c.moveToNext(),i++)
        {
            result[i]=c.getString(iTimeStamp);

        }

        return result;
    }

    public String[] title() {

        String[] columns=new String[]{Mtraval,SAD,TimeStamp};
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

    public int norow() {

        String[] columns=new String[]{Mtraval,SAD,TimeStamp};
        Cursor c=ourdb.query(tablename,columns,null,null,null,null,null);
        int result=c.getCount();

        return result;

    }


    private class DbHelper extends SQLiteOpenHelper{

        public DbHelper(Context context) {
            super(context, dbname, null, dbversion);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            sqLiteDatabase.execSQL(
                    "create table " +tablename+ "(" +Mtraval+ " varchar not null, " +SAD+ " varchar  not null" +
                            "," +TimeStamp+" varchar  not null);"


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
