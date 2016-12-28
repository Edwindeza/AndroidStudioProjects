package com.example.dise07.miscontactos.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dise07.miscontactos.pojo.Contacto;
import com.example.dise07.miscontactos.DetalleContacto;
import com.example.dise07.miscontactos.R;

import java.util.ArrayList;

/**
 * Created by Dise07 on 26/12/2016.
 */

public class ContactoAdaptador extends RecyclerView.Adapter<ContactoAdaptador.ContactoViewHolder>{
    ArrayList<Contacto> contactos;
    Activity activity;

    public ContactoAdaptador(ArrayList<Contacto> contactos, Activity activity) {
        this.contactos  = contactos;
        this.activity   = activity;
    }




    //Inflar ellayout y lo pasará al viewholder para que le obtenga la lista
    @Override
    public ContactoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contacto, parent, false);
        //Esta linea de codigo asocia el layour cardview_contacto con el MainActivity

        return new ContactoViewHolder(v);
    }




    //asocia cada elementode la lista con cada view

    @Override
    public void onBindViewHolder(ContactoViewHolder contactoViewHolder, int position) {
        //Aca pasamos la lista de contactos
       final Contacto contacto = contactos.get(position);

        contactoViewHolder.imgFoto.setImageResource(contacto.getFoto());
        contactoViewHolder.tvNombreCV.setText(contacto.getNombre());
        contactoViewHolder.tvTelefonoCV.setText(contacto.getTlefono());
        contactoViewHolder.tvlikes.setText(String.valueOf(contacto.getLikes())+" Likes");

        contactoViewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, contacto.getNombre(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity, DetalleContacto.class);
                intent.putExtra("nombre",contacto.getNombre());
                intent.putExtra("telefono",contacto.getTlefono());
                intent.putExtra("email",contacto.getEmail());
                activity.startActivity(intent);
                activity.finish();
            }
        });
        contactoViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Diste like a "+ contacto.getNombre(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() { //Cantidad de elementos que contiene la cardviewe
        return contactos.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder{
        //Esta clase es la que interacciona con el layout
        private ImageView imgFoto;
        private TextView tvNombreCV;
        private TextView tvTelefonoCV;
        private ImageButton btnLike;
        private TextView tvlikes;

        public ContactoViewHolder(View itemView) {
            super(itemView);

            imgFoto         =(ImageView) itemView.findViewById(R.id.imgfotocv);
            tvNombreCV      = (TextView) itemView.findViewById(R.id.tvNombreCV);
            tvTelefonoCV    = (TextView) itemView.findViewById(R.id.tvTelefonoCV);
            btnLike         = (ImageButton) itemView.findViewById(R.id.bnLike);
            tvlikes         =(TextView) itemView.findViewById(R.id.tvlikes);
        }
    }

}
