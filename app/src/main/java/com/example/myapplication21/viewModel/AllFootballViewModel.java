package com.example.myapplication21.viewModel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication21.db.AppDao;
import com.example.myapplication21.db.AppDatabase;
import com.example.myapplication21.model.Noticias;
import com.example.myapplication21.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class AllFootballViewModel extends AndroidViewModel {

    public MutableLiveData<List<Noticias>> noticiasList = new MutableLiveData<>();

    public enum ResultadoDelRegistro {
        CORRECTO,
        USUARIO_NO_DISPONBLE,
        INICIADO
    }

    public enum EstadoDeLaAutenticacion {
        NO_AUTENTICADO,
        AUTENTICADO,
        AUTENTICACION_INVALIDA
    }

    public Usuario usuarioLogeado;

    AppDao appDao;
    public MutableLiveData<ResultadoDelRegistro> resultadoDelRegistro = new MutableLiveData<>();
    public MutableLiveData<EstadoDeLaAutenticacion> estadoDeLaAutenticacion = new MutableLiveData<>(EstadoDeLaAutenticacion.NO_AUTENTICADO);

    public AllFootballViewModel(@NonNull Application application) {
        super(application);

        appDao = AppDatabase.getInstance(application).appDao();
        setNoticias();
    }

    public void registrarUsuario(final String nombre, final String email, final String password, final String biografia) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Usuario usuario = appDao.comprobarEmailDisponible(email);

                if(usuario == null){
                    appDao.insertarUsuario(new Usuario(nombre, email,password,biografia));
                    resultadoDelRegistro.postValue(ResultadoDelRegistro.CORRECTO);
                    iniciarSesion(nombre, password);
                } else {
                    resultadoDelRegistro.postValue(ResultadoDelRegistro.USUARIO_NO_DISPONBLE);
                }
            }
        });
    }

    public void iniciarSesion(final String nombre, final String contrasenya) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Usuario usuario = appDao.autenticar(nombre, contrasenya);
                if(usuario != null){
                    usuarioLogeado = usuario;
                    estadoDeLaAutenticacion.postValue(EstadoDeLaAutenticacion.AUTENTICADO);
                } else {
                    estadoDeLaAutenticacion.postValue(EstadoDeLaAutenticacion.AUTENTICACION_INVALIDA);
                }
            }
        });
    }

    public void cerrarSesion() {
        usuarioLogeado = null;
        estadoDeLaAutenticacion.setValue(EstadoDeLaAutenticacion.NO_AUTENTICADO);
    }

    void setNoticias(){
        // 3 segundos
        List<Noticias> list = new ArrayList<>();
        list.add(new Noticias("Top New","TITULO 2", "Hola, muy buenas2", 1));
        list.add(new Noticias( "Top New","TITULO 1", "Hola, muy buenas1", 10));
        list.add(new Noticias("Top New","TITULO 3", "Hola, muy buenas3", 5));

        noticiasList.setValue(list);
    }

}
