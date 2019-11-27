package com.example.myapplication21.model;

public class Noticias {
    private String nomCategoria;
    private String title;
    private String description;
    private int imageId;

    public Noticias(String nomCategoria, String title, String description, int imageId) {

        this.nomCategoria = nomCategoria;
        this.title = title;
        this.description = description;
        this.imageId = imageId;
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
