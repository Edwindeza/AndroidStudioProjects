package com.example.dise07.miscontactos.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dise07.miscontactos.R;
import com.example.dise07.miscontactos.adapter.ContactoAdaptador;
import com.example.dise07.miscontactos.pojo.Contacto;

import java.util.ArrayList;

/**
 * Created by Dise07 on 26/12/2016.
 */

public class RecicleViewFragment extends Fragment {

    ArrayList<Contacto> contactos;
    private RecyclerView listacontactos;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recicleview, container, false);

        listacontactos = (RecyclerView) v.findViewById(R.id.rvContactos);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listacontactos.setLayoutManager(llm);
        inicializarListaContactos();
        inicializarLizeAdaptador();


        return v;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }


    public void inicializarLizeAdaptador(){
        ContactoAdaptador adaptador=new ContactoAdaptador(contactos, getActivity());
        listacontactos.setAdapter(adaptador);
    }

    public void inicializarListaContactos(){
        contactos = new ArrayList<Contacto>();
        contactos.add(new Contacto("Edwin Deza", "987645213", "ejdeza@gmail.com",R.drawable.perfil));
        contactos.add(new Contacto("Darwin Deza", "234453366", "dezacul@gmail.com",R.drawable.perfil));
        contactos.add(new Contacto("Katherinne  Deza", "684963215", "katdc_70@gmail.com",R.drawable.perfil));
        contactos.add(new Contacto("Junior Deza", "975409685", "ejdeza@hotmil.com",R.drawable.perfil));
        contactos.add(new Contacto("Juvenal Deza", "990117206", "serdeza@gmail.com",R.drawable.perfil));

    }
}
