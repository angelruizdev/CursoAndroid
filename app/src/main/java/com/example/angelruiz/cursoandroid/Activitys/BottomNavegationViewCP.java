package com.example.angelruiz.cursoandroid.Activitys;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.angelruiz.cursoandroid.Fragments.FragmentButton;
import com.example.angelruiz.cursoandroid.Fragments.FragmentCallsContentPBNV;
import com.example.angelruiz.cursoandroid.Fragments.FragmentWeightCPBNV;
import com.example.angelruiz.cursoandroid.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavegationViewCP extends AppCompatActivity {

BottomNavigationView bnvContentProvider;
Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navegation_view_cp);

        //control for listen the item selected to the bnv
        bnvContentProvider = findViewById(R.id.bnvContentProvider);
        bnvContentProvider.setOnNavigationItemSelectedListener(navListener);

        //show this fragment at the beginning
        getSupportFragmentManager().beginTransaction().add(R.id.contentCPFragments, new FragmentCallsContentPBNV()).commit();
    }

    //method for control the icons to the menu bnv
    private BottomNavigationView.OnNavigationItemSelectedListener navListener
                                = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

          Fragment selectFragmnt = null;

            switch (menuItem.getItemId()){
                case R.id.opt_cp_contacts:
                   selectFragmnt = new FragmentCallsContentPBNV();
                break;

                case R.id.opt_cp_query:
                   selectFragmnt = new FragmentWeightCPBNV();
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

    //pending
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        fragment = getSupportFragmentManager().findFragmentById(R.id.contentCPFragments);
        if (fragment != null){
            getSupportFragmentManager().putFragment(outState, "fragmentWeight", fragment);
        }
    }

    //pending
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        fragment = getSupportFragmentManager().getFragment(savedInstanceState, "fragmentWeight");

    }
}
