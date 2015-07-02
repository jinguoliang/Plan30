package com.example.jinux.thirtydays.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.example.jinux.thirtydays.R;
import com.example.jinux.thirtydays.common.Constants;
import com.example.jinux.thirtydays.common.SharedPreferenceUtil;
import com.example.jinux.thirtydays.common.TimeUtil;
import com.example.jinux.thirtydays.receiver.NotificationReciver;
import com.example.jinux.thirtydays.widget.MyDateTimePickerDialog;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.text.ParseException;
import java.util.Calendar;


public class SettingsActivity extends BaseActivity implements TextWatcher{

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
        tvAlarmTime.setText(SharedPreferenceUtil.getPreferenceString(Constants.PREF_KEY_ALARM_TIME, getString(R.string.defualt_alarm_time)));
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
        SharedPreferenceUtil.putPreferenceString(Constants.PREF_KEY_ALARM_TIME, s.toString());
        setAlarmToSummaryToday(s.toString());
    }

    private void setAlarmToSummaryToday(String s) {
        String timeString = s;
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
            //no way to here
        }

        Intent intent = new Intent(this, NotificationReciver.class);
        PendingIntent pendingIntnet = PendingIntent.getBroadcast(this, 0, intent, 0);
        setRepeatTo(pendingIntnet, today, Constants.ONE_DAY);
    }

    private void setRepeatTo( PendingIntent pendingIntnet,Calendar start, int interTime) {
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        manager.cancel(pendingIntnet);

        long startTime = start.getTime().getTime();
        manager.setRepeating(AlarmManager.RTC_WAKEUP, startTime, interTime, pendingIntnet);
    }


    @OnClick({R.id.btnBack})
    public void onBackClick(View v){
        this.finish();
    }
}
