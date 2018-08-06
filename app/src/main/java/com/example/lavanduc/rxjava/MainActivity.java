package com.example.lavanduc.rxjava;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lavanduc.rxjava.model.Resource;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btngeturl;
    private ImageView imgresult;
    private EditText edtname,edtpass;
    private Button btnsign;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        addEvent();
    }

    private void addEvent() {
        btngeturl.setOnClickListener(this);
        btnsign.setOnClickListener(this);
        btnlogin.setOnClickListener(this);
    }

    private void init() {
        btngeturl = findViewById(R.id.btngeturl);
        btnsign=findViewById(R.id.btnsign);
        btnlogin=findViewById(R.id.btnlogin);
        imgresult = findViewById(R.id.imgResult);
        edtname=findViewById(R.id.edtname);
        edtpass=findViewById(R.id.edtpass);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btngeturl:
                GetUrl getUrl = new GetUrl("duc", "123");
                getUrl.execute(Resource.URL_POST_DATA);
                break;
            case R.id.btnsign:

                break;
            case R.id.btnlogin:

                break;
        }
    }

    class GetUrl extends AsyncTask<String, Void, String> {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        String name, pass;

        public GetUrl(String name, String pass) {
            this.name = name;
            this.pass = pass;
        }

        @Override
        protected String doInBackground(String... strings) {
            RequestBody requestBody = new MultipartBody.Builder()
                    .addFormDataPart("name", name)
                    .addFormDataPart("pass", pass)
                    .setType(MultipartBody.FORM)
                    .build();
            Request request = new Request.Builder()
                    .url(strings[0])
                    .post(requestBody)
                    .build();
            try {
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null)
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }
}
