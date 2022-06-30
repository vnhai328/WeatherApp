package com.example.weatherapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class WeatherHourlyAdapter extends RecyclerView.Adapter<WeatherHourlyAdapter.MyViewHolder> {
    Context context;
    List<WeatherHourly> weatherHourlyList;
    LayoutInflater layoutInflater;

    public WeatherHourlyAdapter(Context context, List<WeatherHourly> weatherHourlyList) {
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.weatherHourlyList = weatherHourlyList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.weather_hourly, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        WeatherHourly weatherHourly = weatherHourlyList.get(position);
        holder.txvTime.setText(weatherHourly.getTime());
        holder.txvTem.setText(weatherHourly.getTemperature());
        holder.txvHumid.setText(weatherHourly.getHumidity());
        Picasso.get().load(weatherHourly.getUrlIcon()).into(holder.imgWeatherHourly);
    }

    @Override
    public int getItemCount() {
        return weatherHourlyList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txvTime, txvTem, txvHumid;
        ImageView imgWeatherHourly;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txvTime = itemView.findViewById(R.id.txvTime);
            txvTem = itemView.findViewById(R.id.txvTem);
            txvHumid = itemView.findViewById(R.id.txvHumid);
            imgWeatherHourly = itemView.findViewById(R.id.imgWeatherHourly);
        }
    }
}
