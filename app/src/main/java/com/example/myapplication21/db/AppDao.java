package com.example.myapplication21.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication21.model.Equipo;
import com.example.myapplication21.model.Noticia;
import com.example.myapplication21.model.Usuario;

import java.util.List;

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

    @Query("SELECT * FROM Noticia")
    public abstract LiveData<List<Noticia>> cargarNoticias();

    @Insert
    public abstract void insertarNoticia(Noticia noticia);

    @Query("SELECT * FROM Equipo")
    public abstract LiveData<List<Equipo>> cargarEquipo();

    @Query("SELECT * FROM Equipo")
    public abstract List<Equipo> listaEquipo();

    @Insert
    public abstract void insertarEquipo(Equipo equipo);

    @Query("UPDATE Equipo SET fav = :fav")
    public abstract void updateFavoritos(int fav);

    @Query("SELECT * FROM Equipo WHERE fav=1")
    public abstract LiveData<List<Equipo>> cargarFavoritos();

}