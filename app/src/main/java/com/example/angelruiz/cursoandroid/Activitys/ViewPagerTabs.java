package com.example.angelruiz.cursoandroid.Activitys;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.angelruiz.cursoandroid.Adapters.ViewPagerAdapter;
import com.example.angelruiz.cursoandroid.Fragments.FragmentListaContacts;
import com.example.angelruiz.cursoandroid.Fragments.FragmentListaRecycler;
import com.example.angelruiz.cursoandroid.Fragments.FragmentSqliteRecycler;
import com.example.angelruiz.cursoandroid.R;

import java.util.ArrayList;

public class ViewPagerTabs extends AppCompatActivity {
//declaramos nuestor controles del xml, los tabs y el view pager, de forma globar
private TabLayout tabLayout;
private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_tabs);
        //inicializamos controles
        Toolbar toolBar = findViewById(R.id.toolBar);//declaramos el toolbar de forma local
        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);
        setViewPager();//llamamos al metodo que pinta los fragment en el viewPger
        if (toolBar !=null){
            //setSupportActionBar(toolBar);
        }
    }
        //creamos un metodo y arreglo de fragments para pasarselo al constructor y a los tabs
        private ArrayList<Fragment> agregaFragmentsTab(){
         ArrayList<Fragment> fragments=new ArrayList<>();
         fragments.add(new FragmentListaRecycler());
         fragments.add(new FragmentSqliteRecycler());
         fragments.add(new FragmentListaContacts());
         return fragments;//retornamos el array
        }
        //este metodo crea el adaptador, pasamos los parametros que pide el constructor del adapter
        private void setViewPager(){
          // viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),agregaFragmentsTab()));//adapter comprimido es lo mismo que el de abajo
         final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),agregaFragmentsTab());
         viewPager.setAdapter(viewPagerAdapter);//mostramos el adapter en el viewPager
         tabLayout.setupWithViewPager(viewPager);//pasamos el viePager ya con los fragments como parametro a cada tab, con este metodo:setupWithViewPager
         tabLayout.getTabAt(0).setText(getResources().getString(R.string.enviar));//colocamos un texto a cada tab, con el metodo getTabAt(), que recibe como parametro la posision,para determinar en cual colocar cada texto o icono
         tabLayout.getTabAt(1).setText(getResources().getString(R.string.rbactivado));
         //tabLayout.getTabAt(2).setText(getResources().getString(R.string.enviar));
         tabLayout.getTabAt(2).setIcon(R.drawable.phone);//tambien podemos colocar un icono en el tab

        }
}
