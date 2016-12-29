package com.example.dise07.miscontactos.db;

/**
 * Created by Dise07 on 28/12/2016.
 */

public class ConstantesBaseDatos {
    public static final String DATABASE_NAME="contactos";
    public static final int version= 1;

    public static final String TABLE_CONTACTS           ="contacto";
    public static final String TABLE_CONTACTS_ID        ="id";
    public static final String TABLE_CONTACTS_NOMBRE    ="nombre";
    public static final String TABLE_CONTACTS_TELEFONO  ="telefono";
    public static final String TABLE_CONTACTS_EMAIL     ="email";
    public static final String TABLE_CONTACTS_FOTO      ="foto";

    public static final String TABLE_CONTACTS_LIKES             ="contacto_likes";
    public static final String TABLE_CONTACTS_LIKES_ID          ="id_likes";
    public static final String TABLE_CONTACTS_LIKES_ID_CONTACTO ="id_contacto";
    public static final String TABLE_CONTACTS_LIKES_NUMERO_LIKES ="numero_likes" ;
}
