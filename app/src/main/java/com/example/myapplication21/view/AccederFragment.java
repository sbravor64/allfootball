package com.example.myapplication21.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication21.R;
import com.example.myapplication21.viewModel.AllFootballViewModel;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccederFragment extends Fragment {

    private FirebaseAuth mAuth;

    AllFootballViewModel allFootballViewModel;
    NavController navController;
    EditText emailEditText, passwordEditText;
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

        mAuth = FirebaseAuth.getInstance();
//        allFootballViewModel = ViewModelProviders.of(requireActivity()).get(AllFootballViewModel.class);

        navController = Navigation.findNavController(view);

        emailEditText = view.findViewById(R.id.editText_email_acceder);
        passwordEditText = view.findViewById(R.id.editText_password);
        accederButton = view.findViewById(R.id.button_acceder);

        accederButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accederConEmail();
            }
        });

        view.findViewById(R.id.button_crear_cuenta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.registrarFragment);
            }
        });

        accederButton.findViewById(R.id.button_acceder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                allFootballViewModel.iniciarSesion(username, password);
                allFootballViewModel.usuario = emailEditText.getText().toString();
                Log.i("user",allFootballViewModel.usuario);

            }
        });

        view.findViewById(R.id.textView_invitado).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate((R.id.fragmentNotices));
            }
        });

//        allFootballViewModel.estadoDeLaAutenticacion.observe(getViewLifecycleOwner(), new Observer<AllFootballViewModel.EstadoDeLaAutenticacion>() {
//            @Override
//            public void onChanged(AllFootballViewModel.EstadoDeLaAutenticacion estadoDeLaAutenticacion) {
//                switch (estadoDeLaAutenticacion) {
//                    case AUTENTICADO:
//                        navController.navigate(R.id.fragmentNotices);
//                        break;
//                    case AUTENTICACION_INVALIDA:
//                        Toast.makeText(getContext(), "Usuario o Contraseña no validos", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//            }
//        });
    }

    private void accederConEmail() {
        signInForm.setVisibility(View.GONE);
        signInProgressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            actualizarUI(mAuth.getCurrentUser());
                        } else {
                            Snackbar.make(requireView(), "Error: " + task.getException(), Snackbar.LENGTH_LONG).show();
                        }
                        signInForm.setVisibility(View.VISIBLE);
                        signInProgressBar.setVisibility(View.GONE);
                    }
                });
    }

    private void actualizarUI(FirebaseUser currentUser) {
        if(currentUser != null){
            navController.navigate(R.id.homeFragment);
        }
    }
}
