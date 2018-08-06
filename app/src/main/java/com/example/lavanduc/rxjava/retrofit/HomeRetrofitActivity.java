package com.example.lavanduc.rxjava.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lavanduc.rxjava.R;

public class HomeRetrofitActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnregister;
    private Button btnlogin;
    private EditText edtuser;
    private EditText edtpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_retrofit);
        init();
    }

    private void init() {
        btnregister=findViewById(R.id.btnregister);
        btnlogin=findViewById(R.id.btnlogin);
        btnregister.setOnClickListener(this);
        btnlogin.setOnClickListener(this);
        edtuser=findViewById(R.id.edtuser);
        edtpass=findViewById(R.id.edtpass);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnregister:

                break;
            case R.id.btnlogin:

                break;
        }
    }
}
