package com.example.weatherapplication;

public class Weather {
    String city, date, temp, status, urlWeatherIcon, rain, humidity, wind, latitude, longtitude;

    public Weather() {
    }

    public Weather(String city, String date, String temp, String status, String urlWeatherIcon, String rain, String humidity, String wind, String latitude, String longtitude) {
        this.city = city;
        this.date = date;
        this.temp = temp;
        this.status = status;
        this.urlWeatherIcon = urlWeatherIcon;
        this.rain = rain;
        this.humidity = humidity;
        this.wind = wind;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrlWeatherIcon() {
        return urlWeatherIcon;
    }

    public void setUrlWeatherIcon(String urlWeatherIcon) {
        this.urlWeatherIcon = urlWeatherIcon;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "city='" + city + '\'' +
                ", date='" + date + '\'' +
                ", temp='" + temp + '\'' +
                ", status='" + status + '\'' +
                ", urlWeatherIcon='" + urlWeatherIcon + '\'' +
                ", rain='" + rain + '\'' +
                ", humidity='" + humidity + '\'' +
                ", wind='" + wind + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longtitude='" + longtitude + '\'' +
                '}';
    }
}
