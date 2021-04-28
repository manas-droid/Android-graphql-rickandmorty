package com.example.swapi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.swapi.ui.Fragments.CharactersFragment;
import com.example.swapi.ui.Fragments.EpisodeFragment;
import com.example.swapi.ui.Fragments.LocationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity   {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new EpisodeFragment())
                .commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener
            = item -> {
        Fragment selectedFragment = null;

        switch (item.getItemId()){

            case R.id.episodes:
                    selectedFragment = new EpisodeFragment();
                    break;
            case R.id.characters:
                selectedFragment = new CharactersFragment();
                break;
            case R.id.location:
                selectedFragment = new LocationFragment();
                break;
            default: break;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .commit();
        return true;
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}