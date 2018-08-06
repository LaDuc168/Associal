package com.example.lavanduc.rxjava.okhttp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lavanduc.rxjava.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnget;
    private TextView txtresult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home6);
        init();
    }

    private void init() {
        btnget=findViewById(R.id.btnget);
        txtresult=findViewById(R.id.txtresult);
        btnget.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnget:

                MyAsync myAsync=new MyAsync();
                myAsync.execute("http://192.168.16.103:85/diachat/getdanhsachuser.php");
                break;
        }
    }

    public class MyAsync extends AsyncTask<String,Void,String>{

        OkHttpClient okHttpClient=new OkHttpClient.Builder().build();

        @Override
        protected String doInBackground(String... strings) {
            Request.Builder builder=new Request.Builder();
            builder.url(strings[0]);
            Request request = builder.build();
            try {
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s!=null){
                Gson gson=new Gson();
                User[] users = gson.fromJson(s, User[].class);
                ArrayList<User> list=new ArrayList<User>(Arrays.asList(users));
                for (User user:list) {
                    txtresult.append(user.getEmai()+"\n");
                }


            }
        }
    }
}
