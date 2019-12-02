package com.example.myapplication21.db;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myapplication21.model.Usuario;

@Database(entities = {Usuario.class}, version = 2)
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

}
