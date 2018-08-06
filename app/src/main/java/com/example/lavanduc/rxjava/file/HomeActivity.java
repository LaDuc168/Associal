package com.example.lavanduc.rxjava.file;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lavanduc.rxjava.MainActivity;
import com.example.lavanduc.rxjava.R;
import com.example.lavanduc.rxjava.model.MyDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnsave;
    private Button btnread;
    private EditText edtcontent;
    private TextView txtresult;
    private final String FILE_NAME = "filename";
    private final String KEY = "KEY";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public static final String DATABASE_NAME = "quanlylichhen.sqlite";
    public static SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home5);
        init();

        checkAndRequestPermissions();
        sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        edtcontent.setText(sharedPreferences.getString(KEY, ""));

    }

    private void init() {
        btnsave = findViewById(R.id.btnsave);
        btnread = findViewById(R.id.btnread);
        txtresult = findViewById(R.id.txtresult);
        btnread.setOnClickListener(this);
        edtcontent = findViewById(R.id.edtcontent);
        btnsave.setOnClickListener(this);
        sqLiteDatabase = MyDatabase.initDatabase(this, DATABASE_NAME);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnsave:
                ContentResolver contentResolver = getContentResolver();
                Uri uri = Uri.parse("content://com.example.lavanduc.rxjava.MyDatabase/TaiKhoan");
                Cursor cursor = contentResolver.query(uri, null, null, null, null);
                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToPosition(i);
                    String item = cursor.getString(1);
                    Toast.makeText(this, item, Toast.LENGTH_SHORT).show();

                }
//                saveFile();
                break;
            case R.id.btnread:
//                readFile();
                editor = sharedPreferences.edit();
                editor.putString(KEY, "");
                editor.apply();
                break;
        }
    }

    private void checkAndRequestPermissions() {
        String[] permissions = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
        }
    }

    private void saveFile() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.imgback);
        File file;
        File path = new File(Environment.getExternalStorageDirectory().getPath() + "/DEMO");
        path.mkdir();
        file = new File(path.getPath(), "name.png");
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            Toast.makeText(this, "save success", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "loi file", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "loi cha", Toast.LENGTH_SHORT).show();
        }
    }

    private void readFile() {
        FileInputStream fileInputStream;
        File file;
        try {
//            file=new File(null,)
            fileInputStream = openFileInput(FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            StringBuilder stringBuilder = new StringBuilder();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            txtresult.setText(stringBuilder.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SQLiteDatabase initDatabase(Activity activity, String databaseName) {
        try {
            String outFileName = activity.getApplicationInfo()
                    .dataDir + "/databases/" + databaseName;
            File f = new File(outFileName);
            if (!f.exists()) {
                InputStream e = activity.getAssets().open(databaseName);
                File folder = new File(activity.getApplicationInfo()
                        .dataDir + "/databases/");
                if (!folder.exists()) {
                    folder.mkdir();
                }
                FileOutputStream myOutput = new FileOutputStream(outFileName);
                byte[] buffer = new byte[1024];

                int length;
                while ((length = e.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                e.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activity.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
    }
}
