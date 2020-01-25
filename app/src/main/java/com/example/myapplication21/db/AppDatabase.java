package com.example.myapplication21.db;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myapplication21.model.Equipo;
import com.example.myapplication21.model.Noticia;
import com.example.myapplication21.model.Usuario;

@Database(entities = {Usuario.class, Noticia.class, Equipo.class}, version = 12)
public abstract class AppDatabase extends RoomDatabase {

    public abstract AppDao appDao();

    static AppDatabase appDatabase;

    public static AppDatabase getInstance(Context context){
        if(appDatabase == null){
            appDatabase = Room
                    .databaseBuilder(context, AppDatabase.class, "app.db")
                    .fallbackToDestructiveMigration()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            crearAdminUsuario();
                            crearNoticia();
                            crearEquipo();
                        }
                    })
                    .build();
        }
        return appDatabase;
    }

    private static void crearAdminUsuario() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.appDao().insertarUsuario(new Usuario("admin","admin@gmail.com", "admin", "admin"));
            }
        });
    }

    private static void crearNoticia(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.appDao().insertarNoticia(new Noticia(1,"New", "Messi gana su 3to. Balón de oro", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."));
                appDatabase.appDao().insertarNoticia(new Noticia(2,"New", "Messi gana su 4to. Balón de oro", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."));
                appDatabase.appDao().insertarNoticia(new Noticia(3,"New", "Messi gana su 5to. Balón de oro", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."));
                appDatabase.appDao().insertarNoticia(new Noticia(4,"New", "Messi gana su 6to. Balón de oro", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."));
            }
        });
    }

    private static void crearEquipo(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.appDao().insertarEquipo(new Equipo(1,"Barcelona", "BAR", "España", 0));
                appDatabase.appDao().insertarEquipo(new Equipo(2,"Real Madrid", "RM", "España", 0));
                appDatabase.appDao().insertarEquipo(new Equipo(3,"Atletico de Madrid", "ATR", "España", 0));
                appDatabase.appDao().insertarEquipo(new Equipo(4,"Valencia", "VAL", "España",0));
                appDatabase.appDao().insertarEquipo(new Equipo(5,"Manchester City", "MC", "Inglaterra",1));
            }
        });
    }

}
