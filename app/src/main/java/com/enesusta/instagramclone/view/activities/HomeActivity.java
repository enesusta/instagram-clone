package com.enesusta.instagramclone.view.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;

import com.enesusta.instagramclone.R;
import com.enesusta.instagramclone.controller.Initialize;
import com.enesusta.instagramclone.view.fragments.FavoritesFragment;
import com.enesusta.instagramclone.view.fragments.HomeFragment;
import com.enesusta.instagramclone.view.fragments.ProfileFragment;
import com.enesusta.instagramclone.view.fragments.SearchFragment;

public class HomeActivity extends AppCompatActivity implements Initialize {

    private ImageView reloadImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initComponents();
        initListeners();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListeler);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListeler =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;

                        case R.id.nav_favorites:
                            selectedFragment = new FavoritesFragment();
                            break;
                        case R.id.nav_search:
                            selectedFragment = new SearchFragment();
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
                            break;


                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    return true;
                }
            };


    @Override
    public void initComponents() {


    }

    @Override
    public void initListeners() {




    }
}
