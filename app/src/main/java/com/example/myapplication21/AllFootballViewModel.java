package com.example.myapplication21;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication21.model.Noticias;

import java.util.ArrayList;
import java.util.List;

public class AllFootballViewModel extends AndroidViewModel {

    public MutableLiveData<List<Noticias>> noticiasList = new MutableLiveData<>();

    public AllFootballViewModel(@NonNull Application application) {
        super(application);
        setNoticias();
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
