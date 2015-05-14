package com.example.jinux.thirtydays.receiver;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import com.example.jinux.thirtydays.R;
import com.litesuits.common.assist.Toastor;

/**
 * Created by jinux on 5/15/15.
 */
public class NotificationReciver extends BroadcastReceiver{

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.drawable.ic_launcher)//设置状态栏里面的图标（小图标） 　　　　　　　　　　　　　　　　　　　　.setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.i5))//下拉下拉列表里面的图标（大图标） 　　　　　　　.setTicker("this is bitch!") //设置状态栏的显示的信息
                .setWhen(System.currentTimeMillis())//设置时间发生时间
                .setAutoCancel(true)//设置可以清除
                .setContentTitle("This is ContentTitle")//设置下拉列表里的标题
                .setContentText("this is ContentText");//设置上下文内容
        manager.notify(1,builder.build());
        new Toastor(context).showLongToast(R.string.app_name);

    }
}
