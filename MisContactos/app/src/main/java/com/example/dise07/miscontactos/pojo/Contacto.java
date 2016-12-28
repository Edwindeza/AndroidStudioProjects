package com.example.dise07.miscontactos.pojo;

/**
 * Created by Dise07 on 08/11/2016.
 */

public class Contacto {

    private String nombre;
    private String tlefono;
    private String email;
    private int foto;


    public Contacto(String nombre, String tlefono, String email, int foto) {
        this.nombre     = nombre;
        this.tlefono    = tlefono;
        this.email      = email;
        this.foto       = foto;

    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getTlefono() {
        return tlefono;
    }

    public void setTlefono(String tlefono) {
        this.tlefono = tlefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
