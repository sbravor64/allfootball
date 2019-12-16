package com.example.myapplication21.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication21.R;
import com.example.myapplication21.model.Partido;
import com.example.myapplication21.viewModel.AllFootballViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PartidosFragment extends Fragment {

    AllFootballViewModel allFootballViewModel;
    NavController navController;

    public PartidosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_partidos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allFootballViewModel = ViewModelProviders.of(requireActivity()).get(AllFootballViewModel.class);
        navController = Navigation.findNavController(view);

        RecyclerView recyclerView = view.findViewById(R.id.partidosList);

        final PartidosAdapter partidosAdapter = new PartidosAdapter();
        recyclerView.setAdapter(partidosAdapter);
        
        allFootballViewModel.listaPartidos.observe(getViewLifecycleOwner(), new Observer<List<Partido>>() {
            @Override
            public void onChanged(List<Partido> partidos) {
                partidosAdapter.establecerListaElementos(partidos);
            }
        });

    }

    public class PartidosAdapter extends RecyclerView.Adapter<PartidosFragment.PartidosAdapter.PartidosViewHolder> {
        List<Partido> partidosList;
        @NonNull
        @Override
        public PartidosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PartidosViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.partidos_viewholder, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PartidosViewHolder holder, int position) {
            Partido partido = partidosList.get(position);
            holder.fecha.setText(partido.getFecha());
            holder.jornada.setText(partido.getJornada());
            holder.equipoA.setText(partido.getEquipoA());
            holder.equipoB.setText(partido.getEquipoB());
            holder.resultado.setText(partido.getResultado());

//            holder.description.setText(noticias.getDescription());
        }

        @Override
        public int getItemCount() {
            return partidosList == null ? 0 : partidosList.size();
        }

        public void establecerListaElementos(List<Partido> partidos){
            this.partidosList = partidos;
            notifyDataSetChanged();
        }


        public class PartidosViewHolder extends RecyclerView.ViewHolder {

            TextView fecha, jornada, equipoA, equipoB, resultado;

            public PartidosViewHolder(@NonNull View itemView) {
                super(itemView);
                fecha = itemView.findViewById(R.id.textViewFecha);
                jornada = itemView.findViewById(R.id.textViewJornada);
                equipoA = itemView.findViewById(R.id.textViewEquipoA);
                equipoB = itemView.findViewById(R.id.textViewEquipoB);
                resultado = itemView.findViewById(R.id.textViewHoraResultado);
            }
        }
    }
}
