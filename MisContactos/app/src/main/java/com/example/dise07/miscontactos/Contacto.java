package com.example.dise07.miscontactos;

/**
 * Created by Dise07 on 08/11/2016.
 */

public class Contacto {

    private String nombre;
    private String tlefono;
    private String email;

    public Contacto(String nombre, String tlefono, String email) {
        this.nombre = nombre;
        this.tlefono = tlefono;
        this.email = email;
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
