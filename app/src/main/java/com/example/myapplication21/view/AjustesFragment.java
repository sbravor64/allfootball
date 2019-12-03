package com.example.myapplication21.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication21.R;
import com.example.myapplication21.viewModel.AllFootballViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class AjustesFragment extends Fragment {

    AllFootballViewModel allFootballViewModel;
    private TextView usernameTextView;
    public String username;
    public AjustesFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ajustes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allFootballViewModel = ViewModelProviders.of(requireActivity()).get(AllFootballViewModel.class);

        usernameTextView = view.findViewById(R.id.textViewUsername);

        username = allFootballViewModel.usuario;
        usernameTextView.setText(username);
    }
}
