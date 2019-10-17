package com.example.angelruiz.cursoandroid.Activitys;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.angelruiz.cursoandroid.Fragments.FragmentApiRest;
import com.example.angelruiz.cursoandroid.Fragments.FragmentButton;
import com.example.angelruiz.cursoandroid.Fragments.FragmentCalculator;
import com.example.angelruiz.cursoandroid.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavegationViewCP extends AppCompatActivity {

BottomNavigationView bnvContentProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navegation_view_cp);

        bnvContentProvider = findViewById(R.id.bnvContentProvider);
        bnvContentProvider.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().add(R.id.contentCPFragments, new FragmentCalculator()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener
                                                          = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

          Fragment selectFragmnt = null;

            switch (menuItem.getItemId()){
                case R.id.opt_cp_contacts:
                   selectFragmnt = new FragmentCalculator();
                break;

                case R.id.opt_cp_query:
                   selectFragmnt = new FragmentApiRest();
                break;

                case R.id.opt_cp_custom:
                   selectFragmnt = new FragmentButton();
                break;
            }

            if (selectFragmnt != null){
                getSupportFragmentManager().beginTransaction().replace(R.id.contentCPFragments, selectFragmnt).commit();
            }
        return true;
        }
    };
}
