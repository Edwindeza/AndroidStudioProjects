package com.artkodec.uv_control.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by junior on 13/06/16.
 */
public class WeatherTrackerEntity implements Serializable {

    private String cod;
    private String calctime;
    private String cnt;
    private ArrayList<OpenWeatherEntity> list;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getCalctime() {
        return calctime;
    }

    public void setCalctime(String calctime) {
        this.calctime = calctime;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public ArrayList<OpenWeatherEntity> getList() {
        return list;
    }

    public void setList(ArrayList<OpenWeatherEntity> list) {
        this.list = list;
    }
}
