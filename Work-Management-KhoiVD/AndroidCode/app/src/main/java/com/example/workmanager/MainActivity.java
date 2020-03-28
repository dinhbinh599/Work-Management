package com.example.workmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActionBar toolBar;
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.workmanager_preferences", MODE_PRIVATE);
        toolBar = getSupportActionBar();

        navigationItemSelectedListener = (BottomNavigationView.OnNavigationItemSelectedListener) (menuItem) -> {
            Fragment fragment;
            switch (menuItem.getItemId()){
                case R.id.navigation_profile: {
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
                }
            }
            return false;
        };
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    private void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).addToBackStack(null).commit();
    }

}
