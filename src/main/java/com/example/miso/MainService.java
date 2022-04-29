package com.example.miso;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.os.PowerManager;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
/*
        db = openOrCreateDatabase("Miso.db", Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT _id, date FROM Date", null);

        String gettime = "";
        int days = 0;

        if (cursor != null) {
            while (cursor.moveToNext()) {
                gettime = cursor.getString(1);
                days = (int)findTime(gettime);

            }
        }

        Notification.Builder builder =new Notification.Builder(MainService.this);
        notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Intent push =new Intent(MainService.this,MainService.class);
        PendingIntent contentIntent = PendingIntent.getActivity(MainService.this,0,push,flags);
        builder
                .setContentTitle(getString(R.string.notify_ttitle))
                .setContentText(getString(R.string.notify_1_content) + days + getString(R.string.notify_2_content))
                .setContentIntent(contentIntent)
                .setTicker("新訊息")
                //.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_foreground))
                .setDefaults(Notification.DEFAULT_ALL)
                .setVisibility(Notification.VISIBILITY_PRIVATE)
                .setPriority(Notification.PRIORITY_DEFAULT)//設定該通知優先順序
                .setCategory(Notification.CATEGORY_MESSAGE)//設定通知類別
                .setSmallIcon(R.drawable.home)
                .setFullScreenIntent(contentIntent, true)//將Notification變為懸掛式Notification
                .setAutoCancel(true);
        Notification notify = builder.build();
        if ((days < 180) && (days % 30 == 0) && (days == 0)) {
            notificationManager.notify(0, notify);
            days += 1;
        } else if (days % 180 == 0 && days !=0) {
            notificationManager.notify(0, notify);
            days += 1;
        }else {
            notificationManager.cancel(0);
        }



        cursor.close();
        db.close();

*/
        return null;
    }

    SQLiteDatabase db;

    private PowerManager.WakeLock wakeLock;
    NotificationManager notificationManager;

    final int flags = PendingIntent.FLAG_CANCEL_CURRENT;

    @Override
    public void onCreate() {
        super.onCreate();
        PowerManager powerManager = (PowerManager)
                getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "myapp:wakelock");
        wakeLock.acquire();

        db = openOrCreateDatabase("Miso.db", Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT _id, date FROM Date", null);

        String gettime = "";
        int days = 0;

        if (cursor != null) {
            while (cursor.moveToNext()) {
                gettime = cursor.getString(1);
                days = (int)findTime(gettime);

                }
        }

        Notification.Builder builder =new Notification.Builder(MainService.this);
        notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Intent push =new Intent(MainService.this,MainService.class);
        PendingIntent contentIntent = PendingIntent.getActivity(MainService.this,0,push,flags);
        builder
                .setContentTitle(getString(R.string.notify_ttitle))
                .setContentText(getString(R.string.notify_1_content) + days + getString(R.string.notify_2_content))
                .setContentIntent(contentIntent)
                .setTicker("新訊息")
                //.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_foreground))
                .setDefaults(Notification.DEFAULT_ALL)
                .setVisibility(Notification.VISIBILITY_PRIVATE)
                .setPriority(Notification.PRIORITY_DEFAULT)//設定該通知優先順序
                .setCategory(Notification.CATEGORY_MESSAGE)//設定通知類別
                .setSmallIcon(R.drawable.home)
                .setFullScreenIntent(contentIntent, true)//將Notification變為懸掛式Notification
                .setAutoCancel(true);
        Notification notify = builder.build();

        //設定開關，0是關閉，1是開啟

        SharedPreferences preferences = getSharedPreferences("notify", Context.MODE_PRIVATE);
        int openorclose = preferences.getInt("notify", 0);

        if (days == 0){
            openorclose = 1;
        }

        if (openorclose == 1 ) {
            if ((days <= 180) && (days % 30 == 0) && (days == 1)) {
                notificationManager.notify(0, notify);
                openorclose = 0;
            } else if (days > 180 && days % 180 == 0) {
                notificationManager.notify(0, notify);
                openorclose = 0;
            }
        }
        else {
            notificationManager.cancel(0);
        }

        cursor.close();
        db.close();
    }

    private long findTime(String a){
        long temp = 0;
        try{
            SimpleDateFormat sim = new SimpleDateFormat("yyyy/MM/dd");//定義日期時間格式，一定要進行ParseException的例外處理
            Date f = sim.parse(a);
            long firstmeet = f.getTime();//取得時間的unix時間
            Date now = new Date();//取得目前即時的時間
            long nowtime = now.getTime();//取得時間的unix時間
            temp = (nowtime-firstmeet)/(1000*60*60*24);
            //Toast.makeText(getContext(), "time= " + f, Toast.LENGTH_SHORT).show();
        }catch(ParseException e){
            //Toast.makeText(getContext(),"Something is wrong", Toast.LENGTH_SHORT).show();
            //Toast.makeText(getContext(),"date0:" + temp , Toast.LENGTH_SHORT).show();
        }
        return temp;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
