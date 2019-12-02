package com.example.myapplication21.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication21.R;
import com.example.myapplication21.viewModel.AllFootballViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccederFragment extends Fragment {

    AllFootballViewModel allFootballViewModel;

    NavController navController;
    EditText usernameEditText, passwordEditText;
    Button accederButton;

    public AccederFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_acceder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allFootballViewModel = ViewModelProviders.of(requireActivity()).get(AllFootballViewModel.class);

        navController = Navigation.findNavController(view);

        usernameEditText = view.findViewById(R.id.editText_username);
        passwordEditText = view.findViewById(R.id.editText_password);

        accederButton = view.findViewById(R.id.button_acceder);


        view.findViewById(R.id.textView_irAlRegistro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.registrarFragment);
            }
        });

        accederButton.findViewById(R.id.button_acceder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                allFootballViewModel.iniciarSesion(username, password);
            }
        });

        allFootballViewModel.estadoDeLaAutenticacion.observe(getViewLifecycleOwner(), new Observer<AllFootballViewModel.EstadoDeLaAutenticacion>() {
            @Override
            public void onChanged(AllFootballViewModel.EstadoDeLaAutenticacion estadoDeLaAutenticacion) {
                switch (estadoDeLaAutenticacion) {
                    case AUTENTICADO:
                        navController.navigate(R.id.fragmentNotices);
                        break;
                    case AUTENTICACION_INVALIDA:
                        Toast.makeText(getContext(), "Usuario o Contraseña no validos", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
