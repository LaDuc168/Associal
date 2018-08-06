package com.example.lavanduc.rxjava.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;

public class MyBroadCast extends BroadcastReceiver {
    OnCallBack onCallBack;

    public OnCallBack getOnCallBack() {
        return onCallBack;
    }

    public void setOnCallBack(OnCallBack onCallBack) {
        this.onCallBack = onCallBack;
    }

    @Override
    public void onReceive(Context context,  Intent intent) {
        final PendingResult pendingResult = goAsync();
        AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                pendingResult.finish();
                return null;
            }
        };
        asyncTask.execute();
         String receipt = intent.getStringExtra("VALUE");
        onCallBack.listener("broadcast one");
    }

    public interface OnCallBack {
        void listener(String value);
    }
}
