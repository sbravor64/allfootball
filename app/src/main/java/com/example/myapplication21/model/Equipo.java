package com.example.myapplication21.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Equipo {

    @PrimaryKey(autoGenerate = true)
    public int idEquipo;

    private String nom;
    private String alias;
    private String nación;

    public Equipo(int idEquipo, String nom, String alias, String nación) {
        this.idEquipo = idEquipo;
        this.nom = nom;
        this.alias = alias;
        this.nación = nación;
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

    public String getNación() {
        return nación;
    }

    public void setNación(String nación) {
        this.nación = nación;
    }
}
