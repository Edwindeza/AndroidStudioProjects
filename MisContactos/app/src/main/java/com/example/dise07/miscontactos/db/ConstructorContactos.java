package com.example.dise07.miscontactos.db;

import android.content.ContentValues;
import android.content.Context;

import com.example.dise07.miscontactos.R;
import com.example.dise07.miscontactos.pojo.Contacto;

import java.util.ArrayList;

/**
 * Created by Dise07 on 28/12/2016.
 */

public class ConstructorContactos {

    private static final int LIKE=1;

    private Context context;

    public ConstructorContactos(Context context) {
        this.context = context;
    }


    public ArrayList<Contacto> obtenerDatos(){
        /*
        ArrayList<Contacto> contactos = new ArrayList<>();
        contactos.add(new Contacto("Edwin Deza", "987645213", "ejdeza@gmail.com", R.drawable.perfil,2));
        contactos.add(new Contacto("Darwin Deza", "234453366", "dezacul@gmail.com",R.drawable.perfil,3));
        contactos.add(new Contacto("Katherinne  Deza", "684963215", "katdc_70@gmail.com",R.drawable.perfil,1));
        contactos.add(new Contacto("Junior Deza", "975409685", "ejdeza@hotmil.com",R.drawable.perfil,5));
        contactos.add(new Contacto("Juvenal Deza", "990117206", "serdeza@gmail.com",R.drawable.perfil,4));

        return contactos;*/

        BaseDatos db = new BaseDatos(context);
        //insertarTresContactos(db);
        return db.obtenerTodosLosContacto();
    }

    public void insertarTresContactos(BaseDatos db){

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE,"Edwin Deza");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO,"987645213");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_EMAIL,"ejdeza@gmail.com");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_FOTO, R.drawable.perfil);

        db.insertarContactos(contentValues);


        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE,"Darwin Deza");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO,"1245347457");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_EMAIL,"dardeza@gmail.com");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_FOTO, R.drawable.perfil);

        db.insertarContactos(contentValues);


        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE,"Juvenal Deza");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO,"523523523");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_EMAIL,"serdezaeirl@gmail.com");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_FOTO, R.drawable.perfil);

        db.insertarContactos(contentValues);

    }

    public void darLikeContacto(Contacto contacto){
        BaseDatos db=new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_LIKES_ID_CONTACTO, contacto.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_LIKES_NUMERO_LIKES,LIKE);
        db.insertarLikeContacto(contentValues);
    }

    public int obtenerLikesContacto(Contacto contacto){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesContacto(contacto);
    }
}
