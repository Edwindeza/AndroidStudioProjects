package com.example.dise07.miscontactos.vista_fragment;

import com.example.dise07.miscontactos.adapter.ContactoAdaptador;
import com.example.dise07.miscontactos.pojo.Contacto;

import java.util.ArrayList;

/**
 * Created by Dise07 on 28/12/2016.
 */

public interface IRecicleViewFragmentView {

    public void generarLinearLayoutVertical();

    public ContactoAdaptador crearAdaptador(ArrayList<Contacto> contactos);

    public void iniciaLizarAdaptador(ContactoAdaptador adaptador);


}
