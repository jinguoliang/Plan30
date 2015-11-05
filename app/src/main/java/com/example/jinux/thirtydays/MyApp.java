package com.example.jinux.thirtydays;

import android.app.Application;
import android.os.Debug;
import android.util.DebugUtils;
import android.util.Log;

import com.example.jinux.thirtydays.common.Constants;
import com.example.jinux.thirtydays.common.SharedPreferenceUtil;
import com.example.jinux.thirtydays.common.TimeUtil;
import com.firebase.client.Firebase;

import java.text.ParseException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jinux on 5/12/15.
 */
public class MyApp extends Application{
    public static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        Firebase.setAndroidContext(getApplicationContext());
    }
}