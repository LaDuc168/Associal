package com.example.lavanduc.rxjava.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.lavanduc.rxjava.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnregister;
    private EditText edtuser;
    private EditText edtpass;
    private ImageView imgcover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        btnregister=findViewById(R.id.btnregister);
        btnregister.setOnClickListener(this);
        edtuser=findViewById(R.id.edtuser);
        edtpass=findViewById(R.id.edtpass);
        imgcover=findViewById(R.id.imgCover);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnregister:

                break;
            case R.id.imgCover:

                break;
        }
    }
}
