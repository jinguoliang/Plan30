package com.example.jinux.thirtydays.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by jinux on 15-4-6.
 */
public class Controller {
    public static void launchActivity(Context c, Class<? extends Activity> activity) {
       launchActivity(c,activity,null);
    }

    public static void launchActivity(Context c, Class<? extends Activity> activity,Bundle bundle) {
        Intent i = new Intent(c, activity);
        if (bundle != null) {
            i.putExtras(bundle);
        }
        c.startActivity(i);
    }
}