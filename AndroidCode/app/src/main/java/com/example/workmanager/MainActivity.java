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
    String userRole;
    Integer groupId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.workmanager_preferences", MODE_PRIVATE);
        userRole = sharedPreferences.getString("userRole", "");
        int idGroup = sharedPreferences.getInt("groupId", 0);
        groupId = idGroup != 0 ? idGroup : null;
        toolBar = getSupportActionBar();
        loadFragment(new TaskFragment());
        navigationItemSelectedListener = (BottomNavigationView.OnNavigationItemSelectedListener) (menuItem) -> {
            Fragment fragment;
            switch (menuItem.getItemId()){
                case R.id.navigation_tasks: {

                    fragment = new TaskFragment();
                    loadFragment(fragment);
                    return true;
                }
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
