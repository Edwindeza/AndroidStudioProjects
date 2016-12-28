package com.example.dise07.miscontactos.vista_fragment;

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
import com.example.dise07.miscontactos.presentador.IRecyclerViewFragmentPresenter;
import com.example.dise07.miscontactos.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by Dise07 on 26/12/2016.
 */

public class RecicleViewFragment extends Fragment implements IRecicleViewFragmentView{

    ArrayList<Contacto> contactos;
    private RecyclerView listacontactos;
    private IRecyclerViewFragmentPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recicleview, container, false);

        listacontactos = (RecyclerView) v.findViewById(R.id.rvContactos);
        presenter = new RecyclerViewFragmentPresenter(this, getContext());
        return v;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void generarLinearLayoutVertical() {

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listacontactos.setLayoutManager(llm);
    }

    @Override
    public ContactoAdaptador crearAdaptador(ArrayList<Contacto> contactos) {
        ContactoAdaptador adaptador = new ContactoAdaptador(contactos, getActivity());
        return adaptador;
    }

    @Override
    public void iniciaLizarAdaptador(ContactoAdaptador adaptador){
        listacontactos.setAdapter(adaptador);
    }

}
