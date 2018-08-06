package com.example.lavanduc.rxjava.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.lavanduc.rxjava.R;

public class SignActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtname;
    private EditText edtpass;
    private Button btnsign;
    private ImageView imgCover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        init();
        addEvent();
    }

    private void addEvent() {
        btnsign.setOnClickListener(this);
        imgCover.setOnClickListener(this);
    }

    private void init() {
        edtname=findViewById(R.id.edtname);
        edtpass=findViewById(R.id.edtpass);
        btnsign=findViewById(R.id.btnsign);
        imgCover=findViewById(R.id.imgCover);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnsign:

                break;
            case R.id.imgCover:

                break;
        }
    }
}
