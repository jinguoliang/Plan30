package com.example.jinux.thirtydays;

import android.app.Application;
import android.os.Debug;
import android.util.DebugUtils;
import android.util.Log;

import com.example.jinux.thirtydays.common.Constants;
import com.example.jinux.thirtydays.common.SharedPreferenceUtil;
import com.example.jinux.thirtydays.common.TimeUtil;
import com.example.jinux.thirtydays.common.ToastUtil;

import java.text.ParseException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jinux on 5/12/15.
 */
public class MyApp extends Application{
    public static Application mApplication;
    public static Timer mTimer;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        Log.d("hello","Application created");
        initAlarmTimer();
    }

    private void initAlarmTimer() {
        Date date = null;
        try {
             date = TimeUtil.fmtString2Calendar(SharedPreferenceUtil.getPreferenceString(Constants.PREF_KEY_ALARM_TIME, mApplication.getString(R.string.defualt_alarm_time)), Constants.TIME_FMT).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mTimer = new Timer(true);
        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Log.d("hello", "Alarm Reach");
            }
        }, date, Constants.ONE_DAY);

    }
}