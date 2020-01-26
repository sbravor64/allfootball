package com.example.myapplication21.view;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.SearchView;

import com.example.myapplication21.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.fragmentNotices , R.id.fragmentPartidos, R.id.fragmentSiguiendo, R.id.fragmentAjustes)
                .build();
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller,
                                             @NonNull NavDestination destination, @Nullable Bundle arguments) {
                switch (destination.getId()){
                    case R.id.fragmentNotices:
                    case R.id.fragmentPartidos:
                    case R.id.fragmentAjustes:
                    case R.id.ajustesPerfilFragment:
                    case R.id.ajustesAyudaFragment:
                        navView.setVisibility(View.VISIBLE);
                        getSupportActionBar().show();
                        break;
                    case R.id.fragmentSiguiendo:
                        navView.setVisibility(View.VISIBLE);
                        getSupportActionBar().hide();
                        break;
                    case R.id.fragmentPost:
                        navView.setVisibility(View.GONE);
                        getSupportActionBar().show();
                        break;
                    default:
                        navView.setVisibility(View.GONE);
                        getSupportActionBar().hide();
                        break;
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_search, menu);
//        return true;
//    }

    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void closeContextMenu() {
        super.closeContextMenu();
    }

    //    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        menu.findItem(R.id.menu_buscador).setVisible(false);
//        super.onPrepareOptionsMenu(menu);
//        return false;
//    }
}