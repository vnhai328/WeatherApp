package com.example.weatherapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.weatherapplication.Constant.*;

public class Main extends AppCompatActivity {
    String city = null, longtitude, latitude;
    ViewPager vpgWeather;
    Button btnAdd;
    List<Weather> weatherList = new ArrayList<>();
    WeatherAdapter weatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Intent intent = getIntent();
        if (intent.getStringExtra(CITY_KEY) != null){
            city = intent.getStringExtra(CITY_KEY);
            getWeatherByCityName(city);
        }
        if (intent.getStringExtra(COORD_LATITUDE_KEY) != null && intent.getStringExtra(COORD_LONGTITUDE_KEY) != null){
            latitude = intent.getStringExtra(COORD_LATITUDE_KEY);
            longtitude = intent.getStringExtra(COORD_LONGTITUDE_KEY);
            getWeatherByCoordinate(longtitude,latitude);
        }
        weatherAdapter = new WeatherAdapter(Main.this, weatherList);
        vpgWeather.setAdapter(weatherAdapter);
    }

    private void init(){
        vpgWeather = findViewById(R.id.vpgWeather);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddACity addACity = new AddACity();
                addACity.show(getSupportFragmentManager(), addACity.getTag());
            }
        });
    }

    private void getWeatherByCityName(String city){
        String url_callAPI_weather_byCityName = "https://api.openweathermap.org/data/2.5/weather?q="+ city +"&appid="+ API_KEY +"&units=metric";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url_callAPI_weather_byCityName, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int bcg;
                            JSONObject coord = response.getJSONObject("coord");
                            String coord_longtitude = coord.getString("lon");
                            longtitude = coord_longtitude;
                            String coord_latitude = coord.getString("lat");
                            latitude = coord_latitude;
                            JSONArray weatherArray = response.getJSONArray("weather");
                            JSONObject weatherObject = weatherArray.getJSONObject(0);
                            String desc = weatherObject.getString("description");
                            String icon = weatherObject.getString("icon");
                            String urlIcon = "https://openweathermap.org/img/wn/"+ icon +".png";

                            JSONObject main = response.getJSONObject("main");
                            String temp = main.getString("temp")+"°C";
                            String humidity = main.getString("humidity");

                            JSONObject wind = response.getJSONObject("wind");
                            String speed = wind.getString("speed");

                            JSONObject cloud = response.getJSONObject("clouds");
                            String all = cloud.getString("all");

                            String stringDate = response.getString("dt");
                            Long longDate = Long.parseLong(stringDate);
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            Date date = new Date(longDate*1000);
                            String currentDate = dateFormat.format(date);
                            JSONObject sys = response.getJSONObject("sys");
                            String ct = response.getString("name")+", "+ sys.getString("country");

                            weatherList.add(new Weather(ct, currentDate, temp, desc, urlIcon, all, humidity, speed, coord_latitude, coord_longtitude));
                            weatherAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Main.this, "No data "+ city, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    private void getWeatherByCoordinate(String coord_longtitude, String coord_latitude){
        String url_callAPI_weather_byCoordinate = "https://api.openweathermap.org/data/2.5/weather?lat="+ coord_latitude +"&lon="+ coord_longtitude +"&appid="+ API_KEY +"&units=metric";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url_callAPI_weather_byCoordinate, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int bcg;
                            JSONObject coord = response.getJSONObject("coord");
                            String coord_longtitude = coord.getString("lon");
                            longtitude = coord_longtitude;
                            String coord_latitude = coord.getString("lat");
                            latitude = coord_latitude;
                            JSONArray weatherArray = response.getJSONArray("weather");
                            JSONObject weatherObject = weatherArray.getJSONObject(0);
                            String desc = weatherObject.getString("description");
                            String icon = weatherObject.getString("icon");
                            String urlIcon = "https://openweathermap.org/img/wn/"+ icon +".png";

                            JSONObject main = response.getJSONObject("main");
                            String temp = main.getString("temp")+"°C";
                            String humidity = main.getString("humidity");

                            JSONObject wind = response.getJSONObject("wind");
                            String speed = wind.getString("speed");

                            JSONObject cloud = response.getJSONObject("clouds");
                            String all = cloud.getString("all");

                            String stringDate = response.getString("dt");
                            Long longDate = Long.parseLong(stringDate);
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            Date date = new Date(longDate*1000);
                            String currentDate = dateFormat.format(date);
                            JSONObject sys = response.getJSONObject("sys");
                            String ct = response.getString("name")+", "+ sys.getString("country");

                            weatherList.add(new Weather(ct, currentDate, temp, desc, urlIcon, all, humidity, speed, coord_latitude, coord_longtitude));
                            weatherAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Main.this, "No data", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    public void addCity(String city){
        String url_callAPI_weather_byCityName = "https://api.openweathermap.org/data/2.5/weather?q="+ city +"&appid="+ API_KEY +"&units=metric";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url_callAPI_weather_byCityName, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int bcg;
                            JSONObject coord = response.getJSONObject("coord");
                            String coord_longtitude = coord.getString("lon");
                            longtitude = coord_longtitude;
                            String coord_latitude = coord.getString("lat");
                            latitude = coord_latitude;
                            JSONArray weatherArray = response.getJSONArray("weather");
                            JSONObject weatherObject = weatherArray.getJSONObject(0);
                            String desc = weatherObject.getString("description");
                            String icon = weatherObject.getString("icon");
                            String urlIcon = "https://openweathermap.org/img/wn/"+ icon +".png";

                            JSONObject main = response.getJSONObject("main");
                            String temp = main.getString("temp")+"°C";
                            String humidity = main.getString("humidity");

                            JSONObject wind = response.getJSONObject("wind");
                            String speed = wind.getString("speed");

                            JSONObject cloud = response.getJSONObject("clouds");
                            String all = cloud.getString("all");

                            String stringDate = response.getString("dt");
                            Long longDate = Long.parseLong(stringDate);
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            Date date = new Date(longDate*1000);
                            String currentDate = dateFormat.format(date);
                            JSONObject sys = response.getJSONObject("sys");
                            String ct = response.getString("name")+", "+ sys.getString("country");

                            weatherList.add(new Weather(ct, currentDate, temp, desc, urlIcon, all, humidity, speed, coord_latitude, coord_longtitude));
                            weatherAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Main.this, "No data "+ city, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
}