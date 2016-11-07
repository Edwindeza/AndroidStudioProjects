package com.example.dise07.basedatos;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dam.profesor.basedatos.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Abrimos la base de datos 'DBAlumnos' en mdo escritura (Write)
        BDAlumons alumnos = new BDAlumons(this, "BDAlumnos", null, 1);
        SQLiteDatabase db = alumnos.getWritableDatabase();

        //Compruebo que la BD se abra correctamente

        if (db != null){
            for (int i=1; i<=10; i++){
                //Generar valores
                int codigo =1;
                String nombreAlumno = "Alumno"+i;
                //Datos de prueba
                db.execSQL("INSERT INTO Alumnos (codigo, nombre) "+
                        "VALUES (" + codigo +", '"+ nombreAlumno + "')");
            }
            db.close();

        }



    }
}
