package com.example.jinux.thirtydays.widget;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.jinux.thirtydays.Constants;
import com.example.jinux.thirtydays.common.TimeUtil;

import java.text.ParseException;
import java.util.Calendar;

public class MyDatePickerDialog {
    private static TextView mTextView;
    private static DatePickerDialog dialog;
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

    private MyDatePickerDialog(){

    }

    public static Dialog create(TextView v) throws ParseException {
        String dateString = v.getText().toString();
        Calendar calendar = TimeUtil.fmtString2Calendar(dateString, Constants.DATE_FMT);
        dialog = new DatePickerDialog(v.getContext(), dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        mTextView = v;
        return dialog;
    }

    public void show() {
        dialog.show();
    }

}