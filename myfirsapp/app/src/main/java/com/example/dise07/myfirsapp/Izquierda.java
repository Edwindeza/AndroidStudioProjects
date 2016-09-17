package com.example.dise07.myfirsapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Dise07 on 01/08/2016.
 */
public class Izquierda extends Fragment{

    View rootView;


    public View onCreateview (LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState){
        rootView = inflater.inflate(R.layout.Izquierda, container, false);

        return rootView;
    }

}
