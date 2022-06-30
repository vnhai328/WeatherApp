package com.example.weatherapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static com.example.weatherapplication.Constant.*;

public class WeatherDetails extends AppCompatActivity {
    String coord_latitude, coord_longtitude;
    TextView txvCity, txvCurrentDateTime, txvTemperature, txvWeather, txvDesc, txvFeelsLike;
    ImageView imgWeather;
    RecyclerView rlvWeatherHourly;
    ListView ltvWeatherDaily;
    List<WeatherHourly> weatherHourlyList;
    WeatherHourlyAdapter weatherHourlyAdapter;
    List<WeatherDaily> weatherDailyList;
    WeatherDailyAdapter weatherDailyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);
        init();
        Intent intent = getIntent();
        coord_latitude = intent.getStringExtra(COORD_LATITUDE_KEY);
        coord_longtitude = intent.getStringExtra(COORD_LONGTITUDE_KEY);
        txvCity.setText(intent.getStringExtra(CITY_KEY));
        weatherHourlyList = new ArrayList<>();
        weatherHourlyAdapter = new WeatherHourlyAdapter(WeatherDetails.this, weatherHourlyList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(WeatherDetails.this, LinearLayoutManager.HORIZONTAL, false);
        weatherDailyList = new ArrayList<>();
        weatherDailyAdapter = new WeatherDailyAdapter(WeatherDetails.this, R.layout.weather_daily, weatherDailyList);
        rlvWeatherHourly.setLayoutManager(layoutManager);
        rlvWeatherHourly.setAdapter(weatherHourlyAdapter);
        ltvWeatherDaily.setAdapter(weatherDailyAdapter);
        getWeatherHourly(coord_longtitude, coord_latitude);
        getWeatherDaily(coord_longtitude, coord_latitude);
    }

    private void init(){
        txvCity = findViewById(R.id.txvCity);
        txvCurrentDateTime = findViewById(R.id.txvCurrentDateTime);
        txvTemperature = findViewById(R.id.txvTemperature);
        txvWeather = findViewById(R.id.txvWeather);
        txvDesc = findViewById(R.id.txvDesc);
        txvFeelsLike = findViewById(R.id.txvFeelsLike);
        imgWeather = findViewById(R.id.imgWeather);
        rlvWeatherHourly = findViewById(R.id.rlvWeatherHourly);
        ltvWeatherDaily = findViewById(R.id.ltvWeatherDaily);
    }

    private void getWeatherHourly(String coord_lon, String coord_lat){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url_callAPI_weather_hourly = "https://api.openweathermap.org/data/2.5/forecast/?lat="+ coord_lat +"&lon="+ coord_lon +"&cnt=5&appid="+ API_KEY + "&units=metric";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url_callAPI_weather_hourly, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray list = response.getJSONArray("list");
                            for (int i = 0; i < list.length(); i++) {
                                JSONObject list_item = list.getJSONObject(i);
                                JSONArray weather = list_item.getJSONArray("weather");
                                JSONObject weather_item = weather.getJSONObject(0);
                                String icon = weather_item.getString("icon");
                                String urlIcon = "https://openweathermap.org/img/wn/"+ icon +".png";
                                String s_dt = list_item.getString("dt");
                                Long l_dt = Long.parseLong(s_dt);
                                Date dt = new Date (l_dt*1000);
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("kk:mm");
                                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+7"));
                                String time = simpleDateFormat.format(dt);
                                JSONObject main = list_item.getJSONObject("main");
                                String temp = main.getString("temp");
                                Double tmp = Double.valueOf(temp);
                                String humidity = main.getString("humidity")+"%";
                                weatherHourlyList.add(new WeatherHourly(time, urlIcon, humidity, String.format("%.0f", tmp)+"°C"));
                            }
                            weatherHourlyAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(WeatherDetails.this, "No data", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    private void getWeatherDaily(String coord_lon, String coord_lat){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url_callAPI_weather_daily = "https://api.openweathermap.org/data/2.5/onecall?lat="+ coord_lat +"&lon="+ coord_lon +"&exclude=hourly,minutely&appid=" + API_KEY +"&units=metric";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url_callAPI_weather_daily, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject current = response.getJSONObject("current");
                            String s_dt = current.getString("dt");
                            Long l_dt = Long.parseLong(s_dt);
                            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("kk:mm:ss, EEEE, dd/MM/yyyy");
                            simpleDateFormat1.setTimeZone(TimeZone.getTimeZone("GMT+7"));
                            Date dt = new Date(l_dt*1000);
                            String current_date = simpleDateFormat1.format(dt);
                            txvCurrentDateTime.setText(current_date);
                            String temperature = current.getString("temp");
                            txvTemperature.setText(temperature+"°C");
                            JSONArray current_weather = current.getJSONArray("weather");
                            JSONObject current_weather_item = current_weather.getJSONObject(0);
                            String icon = current_weather_item.getString("icon");
                            String url_weather_icon = "https://openweathermap.org/img/wn/"+ icon +".png";
                            Picasso.get().load(url_weather_icon).into(imgWeather);
                            String main = current_weather_item.getString("main");
                            txvWeather.setText(main);
                            String description = current_weather_item.getString("description");
                            txvDesc.setText(description);
                            String feels_like = current.getString("feels_like");
                            txvFeelsLike.setText("Feels Like: "+feels_like+"°C");

                            JSONArray daily = response.getJSONArray("daily");
                            for (int i = 1; i < daily.length() ; i++) {
                                JSONObject item_daily = daily.getJSONObject(i);
                                String sNgay = item_daily.getString("dt");
                                long lNgay = Long.parseLong(sNgay);
                                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("EEEE");
                                Date d = new Date (lNgay*1000);
                                String date = simpleDateFormat2.format(d);
                                JSONObject temp = item_daily.getJSONObject("temp");
                                String temp_min = temp.getString("min");
                                double min = Double.valueOf(temp_min);
                                String temp_max = temp.getString("max");
                                double max = Double.valueOf(temp_max);
                                JSONArray weatherArray = item_daily.getJSONArray("weather");
                                JSONObject weatherObject = weatherArray.getJSONObject(0);
                                String humidity = item_daily.getString("humidity");
                                String icons = weatherObject.getString("icon");
                                String urlIcon = "https://openweathermap.org/img/wn/"+ icons +".png";
                                weatherDailyList.add(new WeatherDaily(date, humidity, urlIcon, String.format("%.0f", min)+"°", String.format("%.0f", max)+"°"));
                            }
                            weatherDailyAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(WeatherDetails.this, "No data", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
}