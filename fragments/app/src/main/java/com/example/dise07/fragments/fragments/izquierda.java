package com.example.dise07.fragments.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.dise07.fragments.R;
import com.example.dise07.fragments.interfaces.EnviarMensaje;

/**
 * Created by Dise07 on 01/08/2016.
 */
public class izquierda extends Fragment {
    View rootView;
    EditText campo;
    Button boton;
    EnviarMensaje em;


    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstancesState){
        rootView = inflater.inflate(R.layout.izquierda, container, false);
        campo = (EditText) rootView.findViewById(R.id.campotxt);
        boton = (Button) rootView.findViewById(R.id.boton);

        boton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String mensaje = campo.getText().toString();
                em.enviardatos(mensaje);

            }
        });


        return rootView;
    }

    public void onAttach(Context ac){
        super.onAttach(ac);
        try {
            em = (EnviarMensaje) ac;

        }catch (ClassCastException e){
            throw new ClassCastException("Necesitas implementar");
        }

    }

}
