package com.example.miso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private static final String DB_FILE = "Miso.db", DB_TABLE = "Formula", DB_TABLE2 = "Date";

    private BottomNavigationView navigation;

    private SQLiteDatabase db;

    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        replaceFragment(new main());

        navigation = findViewById(R.id.navigation);
        navigation.setOnItemSelectedListener(navigationListener);

        db = openOrCreateDatabase(DB_FILE, MODE_PRIVATE, null);
        try {
            db.execSQL(" CREATE TABLE " + DB_TABLE +
                    "(_id INTEGER PRIMARY KEY," + "FormulaName TEXT NOT NULL," + "Ingredient TEXT," + "Content TEXT);");
            db.execSQL(" CREATE TABLE " + DB_TABLE2 + "(_id INTEGER PRIMARY KEY," + "date TEXT NOT NULL);");
            for ( int k =0; k < 8; k++) {

                db.execSQL("INSERT INTO Formula (FormulaName ,Ingredient, Content) values ('請輸入配方名稱', '請輸入材料', '請輸入做法')");
            }
            db.execSQL("INSERT INTO Date (date) values ('1999/12/31')");
        }catch (Exception e){

        }
        db.close();



    }

    private NavigationBarView.OnItemSelectedListener navigationListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();

            if (id == R.id.formula) {

                replaceFragment(new Formula());
                return true;
            }
            else if (id == R.id.main) {

                replaceFragment(new main());
                return true;
            }
            else if (id == R.id.teach) {

                replaceFragment(new teach());
                return true;
            }
            else if (id == R.id.photo) {

                askCameraPermissions();
                return true;
            }

            return false;
        }
    };


    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainpart,fragment);
        fragmentTransaction.commit();

    }
//*********************************************************
    private void askCameraPermissions() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        }else {
            openCamera();
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERM_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, getString(R.string.capture_unopen), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST_CODE){
            try {
                Bitmap image = (Bitmap) data.getExtras().get("data");
                Bundle sentimage = new Bundle();
                sentimage.putParcelable("sentimage", image);
                Fragment capture = new capture();
                capture.setArguments(sentimage);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.mainpart,capture);
                fragmentTransaction.commit();
            }catch (Exception e){

            }
        }
    }

//***********************************






}