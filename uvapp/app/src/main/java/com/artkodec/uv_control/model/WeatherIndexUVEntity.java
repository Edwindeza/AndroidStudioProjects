package com.artkodec.uv_control.model;

import java.io.Serializable;

/**
 * Created by junior on 12/06/16.
 */
public class WeatherIndexUVEntity implements Serializable {
    private String time;
    private LocationEntity location;
    private double data;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }
}
