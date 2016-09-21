package com.example.dise07.basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dise07 on 21/09/2016.
 */

public class BDAlumons extends SQLiteOpenHelper{

    String sqlCreate = "CREATE TABLE Alumnos (codigo INTEGER, nombre TEXT)";
    String sqlCreate2 = "CREATE TABLE Alumnos (codigo INTEGER, nombre TEXT, direccion TEXT)";


    public BDAlumons(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Sino exite base de datos la crea y ejecuta los siguientes comandos
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST Alumnos");

        db.execSQL(sqlCreate2);

    }
}
