package com.example.myapplication21.model;

import java.util.List;

public class Competicion {
    public List<Equipo> clubs;

    String nombre,pais;

    public Competicion(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
