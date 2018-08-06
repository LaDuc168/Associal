package com.example.lavanduc.rxjava.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lavanduc.rxjava.R;
import com.example.lavanduc.rxjava.model.Resource;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnsum;
    private EditText edtfirst;
    private EditText edtsecond;
    private TextView txtresult;
    private ImageView imgreceip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home4);
        init();
Bundle bundle = getIntent().getExtras();
if(bundle!=null)
imgreceip.setImageURI((Uri) bundle.get(Intent.EXTRA_STREAM));
    }

    private void init() {
        btnsum=findViewById(R.id.btnsum);
        edtfirst=findViewById(R.id.edtfirst);
        edtsecond=findViewById(R.id.edtsecond);
        txtresult=findViewById(R.id.txtresult);
        imgreceip=findViewById(R.id.imgreceip);
        btnsum.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnsum:
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("content://contacts/people/1"));
                startActivity(intent);
                finish();

                ContentResolver contentResolver=getContentResolver();
//                contentResolver.query()
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==200){
            int c=data.getIntExtra("sum",0);
            txtresult.setText(c+"");
        }
    }
}
