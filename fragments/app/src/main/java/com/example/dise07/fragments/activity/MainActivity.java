package com.example.dise07.fragments.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dise07.fragments.R;
import com.example.dise07.fragments.fragments.derecha;
import com.example.dise07.fragments.interfaces.EnviarMensaje;

public class MainActivity extends AppCompatActivity implements EnviarMensaje
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void enviardatos(String mensaje) {
        derecha der= (derecha) getFragmentManager().findFragmentById(R.id.derecha);
        der.obtenerMensaje(mensaje);
    }
}
