package com.example.lavanduc.rxjava.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lavanduc.rxjava.R;

public class SumActivity extends AppCompatActivity {

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum);
        int first=getIntent().getIntExtra("first",0);
        int second=getIntent().getIntExtra("second",0);
        int c=first+second;
        intent=new Intent(SumActivity.this,HomeActivity.class);
        intent.putExtra("sum",c);
        setResult(200,intent);
        finish();

    }
}
