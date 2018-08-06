package com.example.lavanduc.rxjava.testui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.lavanduc.rxjava.MainActivity;
import com.example.lavanduc.rxjava.R;

public class FirstService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder notifiBuilder = new Notification.Builder(this);
        notifiBuilder.setContentTitle("Day la title");
        notifiBuilder.setContentText("Day la phan content");
        notifiBuilder.setSmallIcon(R.drawable.imgback);
        notifiBuilder.setAutoCancel(true); //Tắt khi click vào
        notificationManager.notify(1, notifiBuilder.build()); //Hiển thị Notification
        return START_STICKY;
    }
}
