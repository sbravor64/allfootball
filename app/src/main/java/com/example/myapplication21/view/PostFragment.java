package com.example.myapplication21.view;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.myapplication21.NoticeTabs.NoticiasSiguiendoFragment;
import com.example.myapplication21.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment implements NoticiasSiguiendoFragment.OnNoticeListener {

    TextView textViewTitulo, textViewDescripcion;

    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewTitulo = view.findViewById(R.id.textViewTitle);
        textViewDescripcion = view.findViewById(R.id.textViewDescripcion);

    }

    @Override
    public void onNoticeClick(int position) {
    }
}
