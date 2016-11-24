package com.artkodec.uv_control.request.data;

import com.artkodec.uv_control.model.FavoriteZoneEntity;
import com.artkodec.uv_control.model.InformationEntity;

import java.util.List;

/**
 * Created by junior on 01/02/2016.
 */
public class TrackMessage {

    public String next;
    public String previous;
    public List<InformationEntity> results;

    public TrackMessage(String next, String previous) {
        this.next = next;
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<InformationEntity> getResults() {
        return results;
    }

    public void setResults(List<InformationEntity> results) {
        this.results = results;
    }
}
