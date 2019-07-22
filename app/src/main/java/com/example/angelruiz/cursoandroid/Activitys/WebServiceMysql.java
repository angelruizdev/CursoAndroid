package com.example.angelruiz.cursoandroid.Activitys;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.angelruiz.cursoandroid.Fragments.FragmentApiRest;
import com.example.angelruiz.cursoandroid.Fragments.FragmentCalculator;
import com.example.angelruiz.cursoandroid.Fragments.FragmentPendiente;
import com.example.angelruiz.cursoandroid.Fragments.FragmentRegistroWsMysql;
import com.example.angelruiz.cursoandroid.R;

public class WebServiceMysql extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FragmentRegistroWsMysql fragment_registro_ws_mysql;
    FragmentPendiente fragmentPendiente;
    FragmentApiRest fragmentApiRest;
    FragmentCalculator fragmentCalculator;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_service_mysql);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);//desabilitamos actionbar para tener pantalla completa -- > app_bar_web_service_mysql
        //setSupportActionBar(toolbar);
        fragment_registro_ws_mysql = new FragmentRegistroWsMysql();
        fragmentPendiente = new FragmentPendiente();
        fragmentApiRest = new FragmentApiRest();
        fragmentCalculator = new FragmentCalculator();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);//pide un toolbar le pasamos null
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.web_service_mysql, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
           fragmentTransaction.replace(R.id.contenedorMysqlFragments, fragment_registro_ws_mysql);
        } else if (id == R.id.nav_gallery) {
           fragmentTransaction.replace(R.id.contenedorMysqlFragments, fragmentPendiente);
        } else if (id == R.id.nav_slideshow) {
           fragmentTransaction.replace(R.id.contenedorMysqlFragments, fragmentApiRest);
        } else if (id == R.id.nav_manage) {
           startActivity(new Intent(getApplicationContext(), MapsActivity.class));
        } else if (id == R.id.nav_share) {
           startActivity(new Intent(getApplicationContext(), ActivityVPagerInstruction.class));
        } else if (id == R.id.nav_send) {
           fragmentTransaction.replace(R.id.contenedorMysqlFragments, fragmentCalculator);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        fragmentTransaction.commit();
        return true;
    }
}
