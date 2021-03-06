package com.example.myapplication21.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.room.Database;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication21.NoticeTabs.EquipoFavoritoFragment;
import com.example.myapplication21.NoticeTabs.NoticiasSiguiendoFragment;
import com.example.myapplication21.NoticeTabs.VideosFragment;
import com.example.myapplication21.R;
import com.example.myapplication21.viewModel.AllFootballViewModel;
import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoticesFragment extends Fragment {
    private static boolean iniciarSesion = false;
    NavController navController;

    public NoticesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_notices, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new PagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        navController = Navigation.findNavController(view);

//        if(iniciarSesion == false){
//            navController.navigate(R.id.accederFragment);
//        }
    }

    class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if(position == 0) return new NoticiasSiguiendoFragment();
            else if(position==1) return new EquipoFavoritoFragment();
            return new VideosFragment();
        }

        @Override
        public int getCount() {
            return 3;
        }

        public CharSequence getPageTitle(int position) {
            if(position == 0) return "SIGUIENDO";
            else if(position == 1) return "MI EQUIPO";
            return "VIDEOS";
        }
    }
}

