package com.example.dise07.miscontactos.db;

import android.content.Context;

import com.example.dise07.miscontactos.R;
import com.example.dise07.miscontactos.pojo.Contacto;

import java.util.ArrayList;

/**
 * Created by Dise07 on 28/12/2016.
 */

public class ConstructorContactos {

    private Context context;

    public ConstructorContactos(Context context) {
        this.context = context;
    }


    public ArrayList<Contacto> obtenerDatos(){

        ArrayList<Contacto> contactos = new ArrayList<>();
        contactos.add(new Contacto("Edwin Deza", "987645213", "ejdeza@gmail.com", R.drawable.perfil,2));
        contactos.add(new Contacto("Darwin Deza", "234453366", "dezacul@gmail.com",R.drawable.perfil,3));
        contactos.add(new Contacto("Katherinne  Deza", "684963215", "katdc_70@gmail.com",R.drawable.perfil,1));
        contactos.add(new Contacto("Junior Deza", "975409685", "ejdeza@hotmil.com",R.drawable.perfil,5));
        contactos.add(new Contacto("Juvenal Deza", "990117206", "serdeza@gmail.com",R.drawable.perfil,4));

        return contactos;
    }

}
