package com.artkodec.uv_control.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by junior on 09/06/16.
 */
public class OpenWeatherEntity implements Serializable {
    private String base;
    private String id;
    private String name;
    private String cod;
    private String dt;
    private ArrayList<Weather> weather;
   // private Weather weather;
    private Wind wind;
    private MainBody main;
    private Clouds clouds;
    private Rain rain;
    private Sys sys;
    private LocationEntityWeather coord;
    private WeatherIndexUVEntity weatherIndexUVEntity;

    public WeatherIndexUVEntity getWeatherIndexUVEntity() {
        return weatherIndexUVEntity;
    }

    public void setWeatherIndexUVEntity(WeatherIndexUVEntity weatherIndexUVEntity) {
        this.weatherIndexUVEntity = weatherIndexUVEntity;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public MainBody getMain() {
        return main;
    }

    public void setMain(MainBody main) {
        this.main = main;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public LocationEntityWeather getCoord() {
        return coord;
    }

    public void setCoord(LocationEntityWeather coord) {
        this.coord = coord;
    }

    public class Wind implements Serializable{
        private String speed;
        private String deg;

        public String getSpeed() {
            return speed;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public String getDeg() {
            return deg;
        }

        public void setDeg(String deg) {
            this.deg = deg;
        }
    }

    public class MainBody implements Serializable{

        private String temp;
        private String pressure;
        private String humidity;
        private String temp_min;
        private String temp_max;

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
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
    }

    public class Weather implements Serializable{
        private String id;
        private String main;
        private String description;
        private String icon;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    public class Clouds implements Serializable{
        private String all;

        public String getAll() {
            return all;
        }

        public void setAll(String all) {
            this.all = all;
        }
    }

    public class Rain implements Serializable{
        private String all;

        public String getAll() {
            return all;
        }

        public void setAll(String all) {
            this.all = all;
        }
    }

    public class Sys implements Serializable{
        private String id;
        private String type;
        private String message;
        private String country;
        private String sunrise;
        private String sunset;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }
    }

}


