package com.example.dise07.fragments.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dise07.fragments.R;
import com.example.dise07.fragments.interfaces.EnviarMensaje;

/**
 * Created by Dise07 on 01/08/2016.
 */
public class derecha extends Fragment{
    View rootView;
    TextView txt;

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState){
        rootView = inflater.inflate(R.layout.derecha, container, false);
        txt = (TextView) rootView.findViewById(R.id.txt);
        return rootView;
    }

    public void obtenerMensaje(String mnj){
        txt.setText(mnj);
    }

}
