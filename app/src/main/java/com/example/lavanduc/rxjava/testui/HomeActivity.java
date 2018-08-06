package com.example.lavanduc.rxjava.testui;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lavanduc.rxjava.R;
import com.example.lavanduc.rxjava.thread.MyThread;

import java.text.SimpleDateFormat;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private static TextView txtresult;
    private Button btnrun;
    private MyThread myThread;
    private Thread thread;
    private MediaPlayer mp3;
    private Button btnstop;
    private MyService myService;
    private Intent intent;
    private Button btnshow;
    private Intent intentstart;
    //    public static Handler handler=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//           int result= msg.what;
//           btnshow.setText(String.valueOf(result));
//        }
//    } ;
    ServiceConnection sql = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder myBinder = (MyService.MyBinder) service;
            myService = myBinder.getInstance();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myService.startSong();
        }
    };

    private Handler loop = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        addEvent();
        intent = new Intent(HomeActivity.this, FirstService.class);
//        bindService(intent,sql,BIND_AUTO_CREATE);

//        startService(intent);

    }

    private void addEvent() {
        btnrun.setOnClickListener(this);
        btnstop.setOnClickListener(this);
        btnshow.setOnClickListener(this);
    }

    private void init() {
        btnrun = findViewById(R.id.btnRun);
        btnstop = findViewById(R.id.btnstop);
        btnshow = findViewById(R.id.btnshow);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRun:
                    startService(intent);
                break;
            case R.id.btnstop:
                stopService(intent);

                break;

        }
    }

    private String formatCurrentSong(int currentsong) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        return simpleDateFormat.format(currentsong);
    }

    private void updateUISong() {
        loop.postDelayed(new Runnable() {
            @Override
            public void run() {
                int currentSong = myService.getCurrentSong();
                btnshow.setText(formatCurrentSong(currentSong));
                loop.postDelayed(this, 500);
            }
        }, 20);
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
//    }
}
