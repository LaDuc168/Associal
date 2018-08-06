package com.example.lavanduc.rxjava.thread;

import android.content.Context;
import android.os.Message;
import android.widget.TextView;

import com.example.lavanduc.rxjava.testui.HomeActivity;
import com.example.lavanduc.rxjava.testui.ICallBack;

public class MyThread implements Runnable {
    ICallBack listener;

    public ICallBack getListener() {
        return listener;
    }

    public void setListener(ICallBack listener) {
        this.listener = listener;
    }

    TextView textView;
     boolean isCheck=true;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
    public MyThread(TextView textView){
        this.textView=textView;
    }

    @Override
    public void run() {
        int count=0;
        for (int i = 0; i < 300; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {

            }
            count++;

        }

        final int finalCount = count;
        textView.post(new Runnable() {
            @Override
            public void run() {
                textView.setText(String.valueOf(finalCount));
            }
        });
////        listener.listener(String.valueOf(count));
//        Message message = HomeActivity.handler.obtainMessage();
//        message.obj=String.valueOf(count);
//        HomeActivity.handler.sendMessage(message);

    }
}
