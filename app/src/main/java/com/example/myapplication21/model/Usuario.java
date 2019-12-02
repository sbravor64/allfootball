package com.example.myapplication21.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Usuario {

    @PrimaryKey(autoGenerate = true)
    public int idUsuario;

    public String nombre, email, password, biografia;

    public Usuario(String nombre, String email, String password, String biografia) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.biografia = biografia;
    }
}
