package com.example.weatherapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.weatherapplication.Constant.*;

public class WeatherAdapter extends PagerAdapter {
    Context context;
    List<Weather> weatherList;
    LayoutInflater layoutInflater;

    public WeatherAdapter(Context context, List<Weather> weatherList) {
        this.context = context;
        this.weatherList = weatherList;
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return weatherList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.weather, container, false);
        TextView txvCity = view.findViewById(R.id.txvCity);
        TextView txvCurrentDate = view.findViewById(R.id.txvCurrentDate);
        TextView txvLongtitude = view.findViewById(R.id.txvLongtitude);
        TextView txvLatitude = view.findViewById(R.id.txvLatitude);
        TextView txvTemp = view.findViewById(R.id.txvTemp);
        TextView txvStatus = view.findViewById(R.id.txvStatus);
        TextView txvRain = view.findViewById(R.id.txvRain);
        TextView txvHumidity = view.findViewById(R.id.txvHumidity);
        TextView txvWind = view.findViewById(R.id.txvWind);
        ImageView imgWeather = view.findViewById(R.id.imgWeather);
        ImageView imgMore = view.findViewById(R.id.imgMore);

        Weather weather = weatherList.get(position);
        imgMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WeatherDetails.class);
                intent.putExtra(COORD_LONGTITUDE_KEY, weather.getLongtitude());
                intent.putExtra(COORD_LATITUDE_KEY, weather.getLatitude());
                intent.putExtra(CITY_KEY, weather.getCity());
                context.startActivity(intent);
            }
        });

        txvCity.setText(weather.getCity());
        txvCurrentDate.setText(weather.getDate());
        txvLongtitude.setText(weather.getLongtitude());
        txvLatitude.setText(weather.getLatitude());
        txvTemp.setText(weather.getTemp());
        txvStatus.setText(weather.getStatus());
        txvRain.setText(weather.getRain());
        txvHumidity.setText(weather.getHumidity());
        txvWind.setText(weather.getWind());
        Picasso.get().load(weather.getUrlWeatherIcon()).into(imgWeather);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
