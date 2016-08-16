package com.time_stamp.holapal.timestamp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread time=new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    Intent savelocal = new Intent("android.intent.action.MODEOFTRAVEL");
                    startActivity(savelocal);
                    finish();
                }
            }
        };
        time.start();
    }

}
