package com.example.jinux.thirtydays.widget;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.jinux.thirtydays.Constants;
import com.example.jinux.thirtydays.common.TimeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MyDatePickerDialog {
    private static TextView mTextView;
    private static DatePickerDialog dialog;
    final static DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String dateString = year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日";
            mTextView.setText(dateString);
        }
    };

    private MyDatePickerDialog(){

    }

    public static Dialog create(TextView v) throws ParseException {
        String dateString = v.getText().toString();
        Calendar calendar = TimeUtil.fmtString2Calendar(dateString, Constants.DATA_FMT);
        dialog = new DatePickerDialog(v.getContext(), dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        mTextView = v;
        return dialog;
    }

    public void show() {
        dialog.show();
    }

}