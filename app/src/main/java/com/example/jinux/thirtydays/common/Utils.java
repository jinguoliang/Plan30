package com.example.jinux.thirtydays.common;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by jinux on 15-4-23.
 */
public class Utils {
    public static void toastShow(Context c,String content){
        Toast.makeText(c, content, Toast.LENGTH_LONG).show();
    }

    public static void msg(String msg){
        Log.d("Plan30", msg);
    }
}
