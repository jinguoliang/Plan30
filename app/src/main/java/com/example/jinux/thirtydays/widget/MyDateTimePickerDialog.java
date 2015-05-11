package com.example.jinux.thirtydays.widget;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.jinux.thirtydays.common.Constants;
import com.example.jinux.thirtydays.common.TimeUtil;

import java.text.ParseException;
import java.util.Calendar;

public class MyDateTimePickerDialog {

    public static final int WHICH_DATE = 1;
    public static final int WHICH_TIME = 2;
    private static TextView mTextView;
    private static Dialog dialog;
    final static DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            String dateString = TimeUtil.fmtCalendar2String(calendar,Constants.DATE_FMT);
            mTextView.setText(dateString);
        }
    };

    final static TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
            calendar.set(Calendar.MINUTE,minute);
            String timeString = TimeUtil.fmtCalendar2String(calendar,Constants.TIME_FMT);
            mTextView.setText(timeString);
        }
    };

    private MyDateTimePickerDialog(){}

    public static Dialog create(TextView v,int dataOrTime) throws ParseException {
        String timeString = v.getText().toString();
        if (dataOrTime == WHICH_DATE) {
            Calendar calendar = TimeUtil.fmtString2Calendar(timeString, Constants.DATE_FMT);
          dialog = new DatePickerDialog(v.getContext(), dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        }else{
            Calendar calendar = TimeUtil.fmtString2Calendar(timeString, Constants.TIME_FMT);
            dialog = new TimePickerDialog(v.getContext(), timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),true);
        }
        mTextView = v;
        return dialog;
    }

    public void show() {
        dialog.show();
    }

}