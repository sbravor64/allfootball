package com.example.myapplication21.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication21.model.Usuario;

@Dao
public abstract class AppDao {

    @Query("SELECT * FROM Usuario WHERE email = :email")
    public abstract Usuario comprobarEmailDisponible(String email);


    @Query("SELECT * FROM Usuario WHERE nombre = :nombre AND password = :contrasenya")
    public abstract Usuario autenticar(String nombre, String contrasenya);

    @Insert
    public abstract void insertarUsuario(Usuario usuario);

    @Query("SELECT * FROM Usuario WHERE nombre = :nombre")
    public abstract Usuario comprobarNombreDisponible(String nombre);

}