package com.example.weatherapplication;

public class WeatherDaily {
    String date, humidity, urlIcon, temp_min, temp_max;

    public WeatherDaily() {
    }

    public WeatherDaily(String date, String humidity, String urlIcon, String temp_min, String temp_max) {
        this.date = date;
        this.humidity = humidity;
        this.urlIcon = urlIcon;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getUrlIcon() {
        return urlIcon;
    }

    public void setUrlIcon(String urlIcon) {
        this.urlIcon = urlIcon;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(String temp_min) {
        this.temp_min = temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }

    @Override
    public String toString() {
        return "WeatherDaily{" +
                "date='" + date + '\'' +
                ", humidity='" + humidity + '\'' +
                ", urlIcon='" + urlIcon + '\'' +
                ", temp_min='" + temp_min + '\'' +
                ", temp_max='" + temp_max + '\'' +
                '}';
    }
}
