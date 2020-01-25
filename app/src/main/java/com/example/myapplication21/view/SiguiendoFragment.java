package com.example.myapplication21.view;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
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
    ImageView imageView;

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
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allFootballViewModel = ViewModelProviders.of(requireActivity()).get(AllFootballViewModel.class);
        imageView = view.findViewById(R.id.imageViewClubFavorito);

        RecyclerView recyclerViewEquipos = view.findViewById(R.id.equiposList);

        final SiguiendoFragment.EquiposAdapter equiposAdapter = new SiguiendoFragment.EquiposAdapter();
        recyclerViewEquipos.setAdapter(equiposAdapter);

        allFootballViewModel.cargarEquipos().observe(getViewLifecycleOwner(), new Observer<List<Equipo>>() {
            @Override
            public void onChanged(List<Equipo> equipos) {
                equiposAdapter.establecerListaEquipos(equipos);
            }
        });

    }

    public static class EquiposAdapter extends RecyclerView.Adapter<EquiposAdapter.EquiposViewHolder> {
        List<Equipo> equiposList;

        @NonNull
        @Override
        public EquiposViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SiguiendoFragment.EquiposAdapter.EquiposViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.equipos_viewholder, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull final SiguiendoFragment.EquiposAdapter.EquiposViewHolder holder, int position) {
            final Equipo equipo = equiposList.get(position);

            holder.nom.setText(equipo.getNom());

            if(equipo.getFav()==0){
                holder.imageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            } else if(equipo.getFav()==1){
                holder.imageView.setImageResource(R.drawable.ic_favorite_black_24dp);
            }

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(equipo.getFav()==0){
                        equipo.setFav(1);
                        holder.imageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                    } else if(equipo.getFav()==1){
                        equipo.setFav(0);
                        holder.imageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);

                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return equiposList == null ? 0 : equiposList.size();
        }

        void establecerListaEquipos(List<Equipo> n){
            equiposList = n;
            notifyDataSetChanged();
        }

        public class EquiposViewHolder extends RecyclerView.ViewHolder {
            TextView nom;
            ImageView imageView;

            public EquiposViewHolder(@NonNull View itemView) {
                super(itemView);
                nom = itemView.findViewById(R.id.textViewNombreEquipo);
                imageView = itemView.findViewById(R.id.imageViewClubFavorito);
            }
        }
    }
}