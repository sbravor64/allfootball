package com.example.myapplication21.NoticeTabs;


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
import com.example.myapplication21.model.Noticia;
import com.example.myapplication21.viewModel.AllFootballViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EquipoFavoritoFragment extends Fragment {

    AllFootballViewModel allFootballViewModel;
    NavController navController;

    public EquipoFavoritoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_equipo_favorito, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);


        allFootballViewModel = ViewModelProviders.of(requireActivity()).get(AllFootballViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.noticiasFavlist);
        final NoticiasAdapter noticiasAdapter = new NoticiasAdapter();
        recyclerView.setAdapter(noticiasAdapter);

        allFootballViewModel.cargarNoticas().observe(getViewLifecycleOwner(), new Observer<List<Noticia>>() {
            @Override
            public void onChanged(List<Noticia> noticias) {
                noticiasAdapter.establecerListaNoticias(noticias);
            }
        });
    }

    public class NoticiasAdapter extends RecyclerView.Adapter<EquipoFavoritoFragment.NoticiasAdapter.NoticiasViewHolder> {
        private NoticiasSiguiendoFragment.NoticeListener onNoticeListener;
        List<Noticia> noticiasList;

        @NonNull
        @Override
        public NoticiasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new NoticiasViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.noticias_siguiendo_viewholder, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull EquipoFavoritoFragment.NoticiasAdapter.NoticiasViewHolder holder, int position) {

            Noticia noticias = noticiasList.get(position);
            holder.nomCategory.setText(noticias.getNomCategoria());
            holder.title.setText(noticias.getTitle());
//            holder.description.setText(noticias.getDescription());

            holder.setOnNoticeListener(new NoticiasSiguiendoFragment.NoticeListener() {
                @Override
                public void onNoticeClick(View v, int position) {
                    String titulo = noticiasList.get(position).getTitle();
                    String descripcion = noticiasList.get(position).getDescription();

                    Bundle args = new Bundle();
                    args.putString("bTitulo", titulo);
                    args.putString("bDescripcion", descripcion);
                    Navigation.findNavController(v).navigate(R.id.fragmentPost, args);

                }
            });
        }

        @Override
        public int getItemCount() {
            return noticiasList == null ? 0 : noticiasList.size();
        }

        void establecerListaNoticias(List<Noticia> n){
            noticiasList = n;
            notifyDataSetChanged();
        }

        public class NoticiasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView nomCategory, title, description;
            NoticiasSiguiendoFragment.NoticeListener onNoticeListener;

            public NoticiasViewHolder(@NonNull View itemView) {
                super(itemView);
                nomCategory = itemView.findViewById(R.id.textViewCategory);
                title = itemView.findViewById(R.id.textViewTitle);
//                description = itemView.findViewById(R.id.textViewDescription);

                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                onNoticeListener.onNoticeClick(v, getAdapterPosition());
            }

            public void setOnNoticeListener(NoticiasSiguiendoFragment.NoticeListener onNoticeListener) {
                this.onNoticeListener = onNoticeListener;
            }
        }

    }

    public interface NoticeListener {
        void onNoticeClick(View v, int position);
    }
}
