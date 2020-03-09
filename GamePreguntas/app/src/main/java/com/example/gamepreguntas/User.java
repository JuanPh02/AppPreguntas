package com.example.gamepreguntas;

import java.io.Serializable;

public class User implements Serializable {

    private String nombre;
    private String correo;
    private String password;
    private int puntaje;

    public User(String nombre, String correo, String pass) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = pass;
    }


    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPassword() {
        return password;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
}
