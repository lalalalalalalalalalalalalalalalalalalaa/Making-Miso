package com.example.miso;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;

import androidx.annotation.Nullable;

public class MainService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private final static int Notification_ID = 0;

    public final static String ACTION_SERVICE_START = "idc.ron.serviced.service.start";

    private PowerManager.WakeLock wakeLock;
    NotificationManager notificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        PowerManager powerManager = (PowerManager)
                getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "myapp:wakelock");
        wakeLock.acquire();


    }
}
