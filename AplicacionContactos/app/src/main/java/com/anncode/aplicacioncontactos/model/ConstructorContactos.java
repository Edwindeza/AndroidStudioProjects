package com.anncode.aplicacioncontactos.model;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;


import com.anncode.aplicacioncontactos.db.BaseDatos;
import com.anncode.aplicacioncontactos.db.ConstantesBaseDatos;
import com.anncode.recyclerviewfragments.R;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by anahisalgado on 21/04/16.
 */
public class ConstructorContactos{

    private static final int LIKE = 1;
    private Context context;
    public ConstructorContactos(Context context) {
        this.context = context;
    }

    public ArrayList<Contacto> obtenerDatos() {
        BaseDatos db = new BaseDatos(context);
        insertarTresContactos(db);
        return  db.obtenerTodosLosContactos();
    }



    public void insertarTresContactos(BaseDatos db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE, "Anahi Salgado");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO, "77779999");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_EMAIL, "anahi@gmail.com");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_FOTO, R.drawable.candy_icon);

        db.insertarContacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE, "Pedro Sanchez");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO, "88882222");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_EMAIL, "pedro@gmail.com");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_FOTO, R.drawable.yammi_banana_icon);

        db.insertarContacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_NOMBRE, "Mireya Lopez");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_TELEFONO, "33331111");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_EMAIL, "mireya@gmail.com");
        contentValues.put(ConstantesBaseDatos.TABLE_CONTACTS_FOTO, R.drawable.shock_rave_bonus_icon);

        db.insertarContacto(contentValues);
    }

    public void darLikeCotnacto(Contacto contacto){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_CONTACT_ID_CONTACTO, contacto.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_CONTACT_NUMERO_LIKES, LIKE);
        db.insertarLikeContacto(contentValues);
    }

    public int obtenerLikesContacto(Contacto contacto){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesContacto(contacto);
    }


}
