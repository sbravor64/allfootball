package com.example.myapplication21.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication21.R;
import com.example.myapplication21.viewModel.AllFootballViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrarFragment extends Fragment {

    NavController navController;
    AllFootballViewModel allFootballViewModel;
    EditText emailEditText, passwordEditText, biografiaEditText, usernameEditText;


    public RegistrarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registrar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        allFootballViewModel = ViewModelProviders.of(requireActivity()).get(AllFootballViewModel.class);

        usernameEditText = view.findViewById(R.id.editText_username);
        emailEditText = view.findViewById(R.id.editText_email);
        passwordEditText = view.findViewById(R.id.editText_password);
        biografiaEditText = view.findViewById(R.id.editText_biografia);


        view.findViewById(R.id.button_registrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String biografia = biografiaEditText.getText().toString();

                allFootballViewModel.registrarUsuario(username, email, password, biografia);
            }
        });

        allFootballViewModel.resultadoDelRegistro.observe(getViewLifecycleOwner(), new Observer<AllFootballViewModel.ResultadoDelRegistro>() {
            @Override
            public void onChanged(AllFootballViewModel.ResultadoDelRegistro resultadoDelRegistro) {

                switch (resultadoDelRegistro) {
                    case CORRECTO:
                        allFootballViewModel.resultadoDelRegistro.setValue(AllFootballViewModel.ResultadoDelRegistro.INICIADO);
                        break;
                    case USUARIO_NO_DISPONBLE:
                        Toast.makeText(getContext(), "USUARIO O GMAIL NO DISPONIBLE", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        allFootballViewModel.estadoDeLaAutenticacion.observe(getViewLifecycleOwner(), new Observer<AllFootballViewModel.EstadoDeLaAutenticacion>() {

            @Override
            public void onChanged(AllFootballViewModel.EstadoDeLaAutenticacion estadoDeLaAutenticacion) {
                switch (estadoDeLaAutenticacion) {
                    case AUTENTICADO:
                        navController.navigate(R.id.fragmentNotices);
                        break;
                }
            }
        });
    }
}
