package com.artkodec.uv_control.model;

import java.io.Serializable;

/**
 * Created by junior on 12/06/16.
 */
public class LocationEntity implements Serializable {
    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
