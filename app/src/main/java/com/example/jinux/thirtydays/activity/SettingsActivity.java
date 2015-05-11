package com.example.jinux.thirtydays.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.os.Bundle;
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
import com.example.jinux.thirtydays.common.ToastUtil;
import com.example.jinux.thirtydays.common.Utils;
import com.example.jinux.thirtydays.widget.MyDateTimePickerDialog;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.text.ParseException;
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
        Utils.msg("afterTextChanged");
        MyApp.mTimer.cancel();

        String timeString = s.toString();
        SharedPreferenceUtil.putPreferenceString(Constants.PREF_KEY_ALARM_TIME,timeString);
        Date date = null;
        try{
             date = TimeUtil.fmtString2Calendar(timeString, Constants.TIME_FMT).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        MyApp.mTimer = new Timer(true);
        MyApp.mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast(MyApp.mApplication, "Alarm Reach");
                    }
                });
            }
        }, date, Constants.ONE_DAY);

    }
}
