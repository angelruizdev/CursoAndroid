package com.example.angelruiz.cursoandroid.Activitys;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.example.angelruiz.cursoandroid.Fragments.FragmentApiRest;
import com.example.angelruiz.cursoandroid.Fragments.FragmentCalculator;
import com.example.angelruiz.cursoandroid.Fragments.FragmentCaroucelApiRest;
import com.example.angelruiz.cursoandroid.Fragments.FragmentCrudFireBase;
import com.example.angelruiz.cursoandroid.Fragments.FragmentInstagramApiRest;
import com.example.angelruiz.cursoandroid.Fragments.FragmentUssingGit;
import com.example.angelruiz.cursoandroid.Fragments.FragmentRxJavaApiRest;
import com.example.angelruiz.cursoandroid.Fragments.FragmentRxJavaTest;
import com.example.angelruiz.cursoandroid.R;
import com.google.android.material.navigation.NavigationView;

public class WebServiceMysql extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FragmentRxJavaTest fragmentRxJavaTest;
    FragmentCaroucelApiRest fragmentCaroucelApiRest;
    FragmentApiRest fragmentApiRest;
    FragmentCalculator fragmentCalculator;
    FragmentInstagramApiRest fragmentInstagramApiRest;
    FragmentRxJavaApiRest fragmentRxJavaApiRest;
    FragmentCrudFireBase fragmentCrudFireBase;
    FragmentUssingGit fragmentUssingGit;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_service_mysql);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);//desabilitamos actionbar para tener pantalla completa -- > app_bar_web_service_mysql
        //setSupportActionBar(toolbar);
        fragmentRxJavaTest = new FragmentRxJavaTest();
        fragmentCaroucelApiRest = new FragmentCaroucelApiRest();
        fragmentApiRest = new FragmentApiRest();
        fragmentCalculator = new FragmentCalculator();
        fragmentInstagramApiRest = new FragmentInstagramApiRest();
        fragmentRxJavaApiRest = new FragmentRxJavaApiRest();
        fragmentCrudFireBase = new FragmentCrudFireBase();
        fragmentUssingGit = new FragmentUssingGit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);//pide un toolbar le pasamos null
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //return false for not show the 3 dots in toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.web_service_mysql, menu);

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return false;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.rxjava_apirest){
           fragmentTransaction.replace(R.id.contenedorMysqlFragments, fragmentRxJavaApiRest);
        } else if (id == R.id.nav_camera) {
           fragmentTransaction.replace(R.id.contenedorMysqlFragments, fragmentRxJavaTest);
        } else if (id == R.id.nav_gallery) {
           fragmentTransaction.replace(R.id.contenedorMysqlFragments, fragmentCaroucelApiRest);
        } else if (id == R.id.nav_slideshow) {
           fragmentTransaction.replace(R.id.contenedorMysqlFragments, fragmentApiRest);
        } else if (id == R.id.nav_manage) {
           startActivity(new Intent(getApplicationContext(), MapsActivity.class));
        } else if (id == R.id.nav_share) {
           startActivity(new Intent(getApplicationContext(), ActivityVPagerInstruction.class));
        } else if (id == R.id.nav_send) {
           fragmentTransaction.replace(R.id.contenedorMysqlFragments, fragmentCalculator);
        } else if(id == R.id.nav_instagram){
            fragmentTransaction.replace(R.id.contenedorMysqlFragments, fragmentInstagramApiRest);
        }else if(id == R.id.nav_content_provider){
            startActivity(new Intent(getApplicationContext(), BottomNavegationViewCP.class));
        }else if(id == R.id.crud_firebase){
            fragmentTransaction.replace(R.id.contenedorMysqlFragments, fragmentCrudFireBase);
        }else if (id == R.id.nav_fmt_git){
            fragmentTransaction.replace(R.id.contenedorMysqlFragments, fragmentUssingGit);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        fragmentTransaction.commit();
        return true;
    }
}

