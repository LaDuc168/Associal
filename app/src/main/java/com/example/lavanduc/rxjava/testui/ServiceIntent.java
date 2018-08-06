package com.example.lavanduc.rxjava.testui;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class ServiceIntent extends IntentService {

    public ServiceIntent(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
