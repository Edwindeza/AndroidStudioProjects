package com.example.dise07.miscontactos.pojo;

/**
 * Created by Dise07 on 08/11/2016.
 */

public class Contacto {

    private int id;
    private String nombre;
    private String tlefono;
    private String email;
    private int foto;

    public Contacto(String nombre, String tlefono, String email, int foto, int likes) {
        this.nombre = nombre;
        this.tlefono = tlefono;
        this.email = email;
        this.foto = foto;
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int likes;


    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
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
