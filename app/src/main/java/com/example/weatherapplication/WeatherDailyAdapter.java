package com.example.weatherapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class WeatherDailyAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<WeatherDaily> weatherDailyList;

    public WeatherDailyAdapter(Context context, int layout, List<WeatherDaily> weatherDailyList) {
        this.context = context;
        this.layout = layout;
        this.weatherDailyList = weatherDailyList;
    }

    @Override
    public int getCount() {
        return weatherDailyList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder {
        TextView txvCurDate, txvHumidity, txvMinMax;
        ImageView imgWeatherDaily;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder.txvCurDate = convertView.findViewById(R.id.txvCurDate);
            viewHolder.txvHumidity = convertView.findViewById(R.id.txvHumidity);
            viewHolder.txvMinMax = convertView.findViewById(R.id.txvMinMax);
            viewHolder.imgWeatherDaily = convertView.findViewById(R.id.imgWeatherDaily);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        WeatherDaily weatherDaily = weatherDailyList.get(position);
        viewHolder.txvCurDate.setText(weatherDaily.getDate());
        viewHolder.txvHumidity.setText(weatherDaily.getHumidity()+"%");
        viewHolder.txvMinMax.setText(weatherDaily.getTemp_min()+"/"+weatherDaily.getTemp_max());
        Picasso.get().load(weatherDaily.getUrlIcon()).into(viewHolder.imgWeatherDaily);
        return convertView;
    }
}
