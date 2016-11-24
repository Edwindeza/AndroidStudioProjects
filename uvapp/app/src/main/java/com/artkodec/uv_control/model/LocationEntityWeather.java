package com.artkodec.uv_control.model;

import java.io.Serializable;

/**
 * Created by junior on 11/06/16.
 */
public class LocationEntityWeather implements Serializable {
    private double lon;
    private double lat;

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
