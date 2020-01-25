package com.example.myapplication21.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Equipo {

    @PrimaryKey(autoGenerate = true)
    public int idEquipo;

    private String nom;
    private String alias;
    private String nacion;

    private int fav;

    public Equipo(int idEquipo, String nom, String alias, String nacion, int fav) {
        this.idEquipo = idEquipo;
        this.nom = nom;
        this.alias = alias;
        this.nacion = nacion;
        this.fav = fav;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNacion() {
        return nacion;
    }

    public void setNacion(String nacion) {
        this.nacion = nacion;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }
}
