package com.example.weatherapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static com.example.weatherapplication.Constant.*;

public class Start extends AppCompatActivity {
    EditText edtCity;
    Button btnSearch;
    ImageView imgAnimation, imgLocate;
    GPSTracker gpsTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        init();
        gpsTracker = new GPSTracker(Start.this);
    }

    private void init(){
        edtCity = findViewById(R.id.edtCity);
        imgAnimation = findViewById(R.id.imgAnimation);
        imgAnimation.setBackgroundResource(R.drawable.anim);

        imgAnimation.post(new Runnable() {
            @Override
            public void run() {
                AnimationDrawable animationDrawable = (AnimationDrawable) imgAnimation.getBackground();
                animationDrawable.start();
            }
        });
        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkNull() == false){
                    Intent intent = new Intent(Start.this, Main.class);
                    intent.putExtra(CITY_KEY, edtCity.getText().toString().trim());
                    startActivity(intent);
                }
            }
        });

        imgLocate = findViewById(R.id.imgLocate);
        imgLocate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gpsTracker != null){
                    gpsTracker = null;
                    gpsTracker = new GPSTracker(Start.this);
                    if (gpsTracker.canGetLocation()) {
                        double latitude = gpsTracker.getLatitude();
                        double longitude = gpsTracker.getLongitude();
                        Intent intent = new Intent(Start.this, Main.class);
                        intent.putExtra(COORD_LONGTITUDE_KEY, String.valueOf(longitude));
                        intent.putExtra(COORD_LATITUDE_KEY, String.valueOf(latitude));
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private boolean checkNull(){
        if (edtCity.getText().toString().trim().equals("")){
            Toast.makeText(this, "Mời bạn nhập thành phố!", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            return false;
        }
    }
}