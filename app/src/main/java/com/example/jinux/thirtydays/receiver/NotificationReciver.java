package com.example.jinux.thirtydays.receiver;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;

import com.example.jinux.thirtydays.BuildConfig;
import com.example.jinux.thirtydays.R;
import com.example.jinux.thirtydays.activity.SummaryDialog;
import com.litesuits.common.assist.Toastor;

/**
 * Created by jinux on 5/15/15.
 */
public class NotificationReciver extends BroadcastReceiver{

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent i = new Intent(context,SummaryDialog.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,1,i,PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.drawable.ic_launcher)//设置状态栏里面的图标（小图标） 　　　　　　　　　　　　　　　　　　　　.setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.i5))//下拉下拉列表里面的图标（大图标） 　　　　　　　.setTicker("this is bitch!") //设置状态栏的显示的信息
                .setWhen(System.currentTimeMillis())//设置时间发生时间
                .setAutoCancel(true)//设置可以清除
                .setContentTitle(context.getString(R.string.notification_title))//设置下拉列表里的标题
                .setContentText(context.getString(R.string.noitification_content))
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_launcher))
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(pendingIntent);
        Notification notification = builder.build();
        manager.notify(1,notification);
    }
}
