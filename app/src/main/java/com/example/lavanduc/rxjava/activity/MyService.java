package com.example.lavanduc.rxjava.activity;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class MyService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyService(String name) {
        super(name);
    }

    @Override
    protected  void onHandleIntent(@Nullable Intent intent) {
        synchronized(this){

        }
    }
}
