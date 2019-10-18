package com.example.angelruiz.cursoandroid.Activitys;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.angelruiz.cursoandroid.Fragments.FragmentApiRest;
import com.example.angelruiz.cursoandroid.Fragments.FragmentButton;
import com.example.angelruiz.cursoandroid.Fragments.FragmentContactsCPBNV;
import com.example.angelruiz.cursoandroid.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavegationViewCP extends AppCompatActivity {

BottomNavigationView bnvContentProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navegation_view_cp);

        //control for listen the item selected to the bnv
        bnvContentProvider = findViewById(R.id.bnvContentProvider);
        bnvContentProvider.setOnNavigationItemSelectedListener(navListener);

        //show this fragment at the beginning
        getSupportFragmentManager().beginTransaction().add(R.id.contentCPFragments, new FragmentContactsCPBNV()).commit();
    }

    //method for control the icons to the menu bnv
    private BottomNavigationView.OnNavigationItemSelectedListener navListener
                                                          = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

          Fragment selectFragmnt = null;

            switch (menuItem.getItemId()){
                case R.id.opt_cp_contacts:
                   selectFragmnt = new FragmentContactsCPBNV();
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
