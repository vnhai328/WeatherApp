package com.example.weatherapplication;

public class WeatherHourly {
    String time, urlIcon, humidity, temperature;

    public WeatherHourly() {
    }

    public WeatherHourly(String time, String urlIcon, String humidity, String temperature) {
        this.time = time;
        this.urlIcon = urlIcon;
        this.humidity = humidity;
        this.temperature = temperature;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrlIcon() {
        return urlIcon;
    }

    public void setUrlIcon(String urlIcon) {
        this.urlIcon = urlIcon;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "WeatherHourly{" +
                "time='" + time + '\'' +
                ", urlIcon='" + urlIcon + '\'' +
                ", humidity='" + humidity + '\'' +
                ", temperature='" + temperature + '\'' +
                '}';
    }
}
