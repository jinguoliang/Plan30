package com.example.jinux.thirtydays.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jinux.thirtydays.MyApp;
import com.example.jinux.thirtydays.R;
import com.example.jinux.thirtydays.common.Constants;
import com.example.jinux.thirtydays.common.SharedPreferenceUtil;
import com.example.jinux.thirtydays.common.TimeUtil;
import com.example.jinux.thirtydays.common.Utils;
import com.example.jinux.thirtydays.receiver.NotificationReciver;
import com.example.jinux.thirtydays.widget.MyDateTimePickerDialog;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class SettingsActivity extends Activity implements TextWatcher{

    @ViewInject(R.id.tvAlarmTime)
    Button tvAlarmTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ViewUtils.inject(this);

        initData();
        tvAlarmTime.addTextChangedListener(this);
    }

    private void initData() {
        tvAlarmTime.setText(SharedPreferenceUtil.getPreferenceString(Constants.PREF_KEY_ALARM_TIME,getString(R.string.defualt_alarm_time)));
    }

    @OnClick({R.id.tvAlarmTime})
    public void onAlarmTimeClick(View v) throws ParseException {
            Dialog dialog = MyDateTimePickerDialog.create(tvAlarmTime,MyDateTimePickerDialog.WHICH_TIME);
            dialog.show();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String timeString = s.toString();
        SharedPreferenceUtil.putPreferenceString(Constants.PREF_KEY_ALARM_TIME,timeString);
        Calendar date = null;
            Calendar today = Calendar.getInstance();
        try{
             date = TimeUtil.fmtString2Calendar(timeString, Constants.TIME_FMT);
            today.set(Calendar.HOUR_OF_DAY,date.get(Calendar.HOUR_OF_DAY));
            today.set(Calendar.MINUTE,date.get(Calendar.MINUTE));
            today.set(Calendar.SECOND,0);
            today.set(Calendar.MILLISECOND,0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date1 = today.getTime();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long startTime = date1.getTime();
        long interTime = Constants.ONE_DAY;
        Intent intent = new Intent(this, NotificationReciver.class);
        intent.setAction("reapting");
        PendingIntent pendingIntnet = PendingIntent.getBroadcast(this,0,intent,0);
        manager.cancel(pendingIntnet);
        Utils.msg("startTime = " + startTime);
        Utils.msg("now = " + System.currentTimeMillis());
        manager.setRepeating(AlarmManager.RTC_WAKEUP, startTime,interTime,pendingIntnet);
        Utils.msg("hello world");
    }
}
