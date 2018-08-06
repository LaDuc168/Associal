package com.example.lavanduc.rxjava.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SecondBroadCast extends BroadcastReceiver {
    MyBroadCast.OnCallBack onCallBack;

    public MyBroadCast.OnCallBack getOnCallBack() {
        return onCallBack;
    }

    public void setOnCallBack(MyBroadCast.OnCallBack onCallBack) {
        this.onCallBack = onCallBack;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        onCallBack.listener("broadcast two");

    }
}
