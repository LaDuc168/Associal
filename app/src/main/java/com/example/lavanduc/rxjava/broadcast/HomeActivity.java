package com.example.lavanduc.rxjava.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lavanduc.rxjava.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private final String VALUE="VALUE";
    private Button btnsend;
    private TextView txtreceipt;
    private MyBroadCast myBroadCast;
    private SecondBroadCast secondBroadCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        init();
        myBroadCast=new MyBroadCast();
        secondBroadCast=new SecondBroadCast();
        addEvent();
        registerMyBroasCast();
        myBroadCast.setOnCallBack(new MyBroadCast.OnCallBack() {
            @Override
            public void listener(String value) {
                txtreceipt.append(value+"\n");
            }
        });
        secondBroadCast.setOnCallBack(new MyBroadCast.OnCallBack() {
            @Override
            public void listener(String value) {
                txtreceipt.append(value+"\n");
            }
        });
    }

    private void registerMyBroasCast() {
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("lavanduc");
        registerReceiver(secondBroadCast,intentFilter);
        registerReceiver(myBroadCast,intentFilter);
    }

    private void addEvent() {
        btnsend.setOnClickListener(this);
    }

    private void init() {
        btnsend = findViewById(R.id.btnsend);
        txtreceipt = findViewById(R.id.txtreceipt);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnsend:
                Intent intent=new Intent();
                intent.setAction("lavanduc");
                intent.putExtra(VALUE,"password: 21919942");
                sendBroadcast(intent);
//                sendOrderedBroadcast();
                break;
        }
    }
}
