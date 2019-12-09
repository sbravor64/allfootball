package com.example.myapplication21.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Noticia {

    @PrimaryKey(autoGenerate = true)
    public int idNoticia;

    private String nomCategoria;
    private String title;
    private String description;

    public Noticia(int idNoticia,String nomCategoria, String title, String description) {
        this.idNoticia = idNoticia;
        this.nomCategoria = nomCategoria;
        this.title = title;
        this.description = description;
    }

    public String getNomCategoria() {
        return nomCategoria;
    }

    public void setNomCategoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

