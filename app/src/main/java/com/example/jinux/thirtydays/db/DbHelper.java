package com.example.jinux.thirtydays.db;

import android.content.Context;

import com.lidroid.xutils.DbUtils;

/**
 * Created by jinux on 15-4-12.
 */
public class DbHelper {
    public static void initDb(Context c){
        DbUtils  db = DbUtils.create(c);

    }
}
