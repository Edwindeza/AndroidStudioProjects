package com.artkodec.uv_control.model;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by junior on 25/05/16.
 */
public class InformationEntity implements Serializable ,Comparable<InformationEntity>{

    private String id;
    private String description;
    private int weigth;

    public InformationEntity(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWeigth() {
        return weigth;
    }

    public void setWeigth(int weigth) {
        this.weigth = weigth;
    }

    @Override
    public int compareTo(InformationEntity another) {
        if (weigth < another.weigth) {
            return -1;
        }
        if (weigth > another.weigth) {
            return 1;
        }
        return 0;
    }
}
