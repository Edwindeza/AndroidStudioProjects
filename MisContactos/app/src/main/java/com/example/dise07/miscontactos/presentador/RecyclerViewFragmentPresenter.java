package com.example.dise07.miscontactos.presentador;

import android.content.Context;

import com.example.dise07.miscontactos.db.ConstructorContactos;
import com.example.dise07.miscontactos.pojo.Contacto;
import com.example.dise07.miscontactos.vista_fragment.IRecicleViewFragmentView;

import java.util.ArrayList;

/**
 * Created by Dise07 on 28/12/2016.
 */

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {

    private IRecicleViewFragmentView iRecicleViewFragmentView;
    private Context context;
    private ConstructorContactos constructorContactos;
    private ArrayList<Contacto> contactos;


    public RecyclerViewFragmentPresenter(IRecicleViewFragmentView iRecicleViewFragmentView, Context context) {
        this.iRecicleViewFragmentView= iRecicleViewFragmentView;

        this.context=context;
    }


    @Override
    public void obtenerContactosBaseDatos() {
        constructorContactos = new ConstructorContactos(context);
        contactos = constructorContactos.obtenerDatos();
        mostrarContactosRV();
    }

    @Override
    public void mostrarContactosRV() {
        iRecicleViewFragmentView.iniciaLizarAdaptador(iRecicleViewFragmentView.crearAdaptador(contactos));
        iRecicleViewFragmentView.generarLinearLayoutVertical();


    }
}
