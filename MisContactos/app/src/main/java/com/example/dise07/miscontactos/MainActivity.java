package com.example.dise07.miscontactos;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.dise07.miscontactos.adapter.PageAdapter;
import com.example.dise07.miscontactos.vista_fragment.PerfilFragment;
import com.example.dise07.miscontactos.vista_fragment.RecicleViewFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    public void closeOptionsMenu() {
        super.closeOptionsMenu();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setUpNewPager();

        if (toolbar != null){
            setSupportActionBar(toolbar);
        }

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

    //Añadir fragments
    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecicleViewFragment());
        fragments.add(new PerfilFragment());
        return fragments;
    }

    private void setUpNewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_contact);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_person);
    }

}
