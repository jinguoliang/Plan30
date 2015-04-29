package com.example.jinux.thirtydays;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.example.jinux.thirtydays.activity.MainActivity;
import com.example.jinux.thirtydays.common.Controller;


public class SplashActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        delay(2000);
    }

    private void delay(int i) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Controller.launchActivity(SplashActivity.this, MainActivity.class);
            }
        },i);
    }
}
