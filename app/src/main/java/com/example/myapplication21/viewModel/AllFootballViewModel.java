package com.example.myapplication21.viewModel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.myapplication21.db.AppDao;
import com.example.myapplication21.db.AppDatabase;
import com.example.myapplication21.model.Equipo;
import com.example.myapplication21.model.Noticia;
import com.example.myapplication21.model.Partido;
import com.example.myapplication21.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class AllFootballViewModel extends AndroidViewModel {

//    public MutableLiveData<List<Noticia>> noticiasList = new MutableLiveData<>();
    public MutableLiveData<List<Partido>> listaPartidos = new MutableLiveData<>();
    public MutableLiveData<Partido> partidoSeleccionado = new MutableLiveData<>();
    public MutableLiveData<String> terminoBusqueda = new MutableLiveData<>();

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
        rellenarListaElementos();
    }

    public String usuario;

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

    public void updateFav(int fav){
        appDao.updateFavoritos(fav);
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

    public LiveData<List<Noticia>> cargarNoticas(){
     return appDao.cargarNoticias();
    }

    public LiveData<List<Equipo>> cargarEquiposFav(){
        return appDao.cargarFavoritos();
    }

    public LiveData<List<Equipo>> cargarEquipos(){
        return appDao.cargarEquipo();
    }

    public List<Equipo> listEquipos(){
        return appDao.listaEquipo();
    }

    public void rellenarListaElementos(){
        List<Partido> partidos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Partido partido = new Partido("equipo " + i, "equipo " + i+1, "10:00", "2-0", "laLiga", "10.10.19");
            partidos.add(partido);
        }
        listaPartidos.setValue(partidos);
    }

    public void establecerPartidoSeleccionado(Partido partido){
        partidoSeleccionado.setValue(partido);
    }

    public void establecerTerminoBusqueda(String termino){
        terminoBusqueda.setValue(termino);
    }

}
