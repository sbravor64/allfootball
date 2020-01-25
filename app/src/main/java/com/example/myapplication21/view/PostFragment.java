package com.example.myapplication21.view;


import android.app.ActionBar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication21.NoticeTabs.NoticiasSiguiendoFragment;
import com.example.myapplication21.R;
import com.example.myapplication21.viewModel.AllFootballViewModel;
import com.github.chrisbanes.photoview.PhotoViewAttacher;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment {

    TextView textViewTitulo, textViewDescripcion;
    ImageView imageView;

    public PostFragment() {
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

        imageView = view.findViewById(R.id.imageView2);

        Bundle bundle = getArguments();

        String titulo = bundle.getString("bTitulo");
        String descripcion = bundle.getString("bDescripcion");

        textViewTitulo.setText(titulo);
        textViewDescripcion.setText(descripcion);

        ((MainActivity) getActivity()).setActionBarTitle(titulo);

    }

}
