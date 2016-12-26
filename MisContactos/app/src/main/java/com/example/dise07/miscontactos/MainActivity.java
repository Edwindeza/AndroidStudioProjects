package com.example.dise07.miscontactos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contacto>  contactos;
    private RecyclerView listacontactos;

    @Override
    public void closeOptionsMenu() {
        super.closeOptionsMenu();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listacontactos = (RecyclerView) findViewById(R.id.rvContactos);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listacontactos.setLayoutManager(llm);
        inicializarListaContactos();
        inicializarLizeAdaptador();



        /*
        contactos = new ArrayList<Contacto>();
        contactos.add(new Contacto("Edwin Deza", "987645213", "ejdeza@gmail.com",R.drawable.perfil));
        contactos.add(new Contacto("Darwin Deza", "234453366", "dezacul@gmail.com",R.drawable.perfil));
        contactos.add(new Contacto("Katherinne  Deza", "684963215", "katdc_70@gmail.com",R.drawable.perfil));
        contactos.add(new Contacto("Junior Deza", "975409685", "ejdeza@hotmil.com",R.drawable.perfil));
        contactos.add(new Contacto("Juvenal Deza", "990117206", "serdeza@gmail.com",R.drawable.perfil));

        ArrayList<String> nombresContacto= new ArrayList<>();



        for (Contacto contacto: contactos) {
            nombresContacto.add(contacto.getNombre());
        }
        ListView lstContactos = (ListView) findViewById(R.id.lstContactos);
        lstContactos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombresContacto));

        lstContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetalleContacto.class);
                intent.putExtra(getResources().getString(R.string.pnombre),contactos.get(position).getNombre());
                intent.putExtra(getResources().getString(R.string.ptelefono),contactos.get(position).getTlefono());
                intent.putExtra(getResources().getString(R.string.pemail),contactos.get(position).getEmail());
                startActivity(intent);
                finish();
                //Intent explicito que permite unir componentes de una pantalla a otra.
            }
        });
        */
    }

    public void inicializarLizeAdaptador(){
        ContactoAdaptador adaptador=new ContactoAdaptador(contactos);
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
