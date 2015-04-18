package com.example.jinux.thirtydays.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jinux on 15-4-19.
 */
public class TimeUtil {
    public static String fmtCalendar2String(Calendar calendar, String fmt){
        SimpleDateFormat format = new SimpleDateFormat(fmt, Locale.CHINA);
        String dateString = format.format(calendar.getTime());
        return dateString;
    }

    public static Calendar fmtString2Calendar(String dateString,String fmt) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(fmt, Locale.CHINA);
        Date date = format.parse(dateString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}
