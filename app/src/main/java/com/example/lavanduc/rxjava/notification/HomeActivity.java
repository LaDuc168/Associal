package com.example.lavanduc.rxjava.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lavanduc.rxjava.MainActivity;
import com.example.lavanduc.rxjava.R;
import com.example.lavanduc.rxjava.model.Resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnsend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home3);
        init();
    }

    private void init() {
        btnsend = findViewById(R.id.btnsend);
        btnsend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnsend:
                showNotification();
                break;
        }
    }

    private void showNotification() {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("this is a title");
        builder.setContentText("Content");
        builder.setSmallIcon(R.drawable.imgback);
        Intent intent=new Intent(HomeActivity.this, MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);
//        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Uri uri = Uri.parse("android.resource://" + getPackageName() +"/"+ R.raw.one);
//        builder.setSound(uri);
        builder.addAction(R.drawable.imgback,"Call",pendingIntent);
        builder.addAction(R.drawable.imgback,"Mess",pendingIntent);

        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(Resource.CONTENT));
        final NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i+=10) {
                    builder.setProgress(100,i,false);
                    manager.notify(1,builder.build());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                builder.setProgress(0,0,false);
                builder.setContentText("dowloaded");
                manager.notify(1,builder.build());
            }
        }).start();
    }

        public static SQLiteDatabase initDatabase(Activity activity, String databaseName){
            try {
                String outFileName = activity
                        .getApplicationInfo()
                        .dataDir + "/databases/" + databaseName;

                File f = new File(outFileName);
                if(!f.exists()) {
                    InputStream e = activity.getAssets().open(databaseName);
                    File folder = new File(activity
                            .getApplicationInfo()
                            .dataDir + "/databases/");
                    if (!folder.exists()) {
                        folder.mkdir();
                    }
                    FileOutputStream myOutput = new FileOutputStream(outFileName);
                    byte[] buffer = new byte[1024];

                    int length;
                    while ((length = e.read(buffer)) > 0) {
                        myOutput.write(buffer, 0, length);
                    }

                    myOutput.flush();
                    myOutput.close();
                    e.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return activity.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
        }
}
