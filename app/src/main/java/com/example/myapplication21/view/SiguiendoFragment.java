package com.example.myapplication21.view;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.myapplication21.R;
import com.example.myapplication21.model.Equipo;
import com.example.myapplication21.viewModel.AllFootballViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SiguiendoFragment extends Fragment {

    AllFootballViewModel allFootballViewModel;

    public SiguiendoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_siguiendo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allFootballViewModel = ViewModelProviders.of(requireActivity()).get(AllFootballViewModel.class);

        RecyclerView recyclerViewEquipos = view.findViewById(R.id.equiposList);

        final SiguiendoFragment.EquiposAdapter equiposAdapter = new SiguiendoFragment.EquiposAdapter();
        recyclerViewEquipos.setAdapter(equiposAdapter);

        allFootballViewModel.cargarEquipos().observe(getViewLifecycleOwner(), new Observer<List<Equipo>>() {
            @Override
            public void onChanged(List<Equipo> equipos) {
                equiposAdapter.establecerListaNoticias(equipos);
            }
        });



    }

    public class EquiposAdapter extends RecyclerView.Adapter<EquiposAdapter.EquiposViewHolder> {
        List<Equipo> equiposList;

        @NonNull
        @Override
        public EquiposViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SiguiendoFragment.EquiposAdapter.EquiposViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.equipos_viewholder, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull SiguiendoFragment.EquiposAdapter.EquiposViewHolder holder, int position) {

            Equipo equipos = equiposList.get(position);
            holder.nom.setText(equipos.getNom());
        }

        @Override
        public int getItemCount() {
            return equiposList == null ? 0 : equiposList.size();
        }

        void establecerListaNoticias(List<Equipo> n){
            equiposList = n;
            notifyDataSetChanged();
        }

        public class EquiposViewHolder extends RecyclerView.ViewHolder {

            TextView nom;

            public EquiposViewHolder(@NonNull View itemView) {
                super(itemView);
                nom = itemView.findViewById(R.id.textViewNombreEquipo);
            }
        }
    }
}
