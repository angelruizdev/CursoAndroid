package com.example.angelruiz.cursoandroid.Activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.angelruiz.cursoandroid.Adapters.AdapterSucursales;
import com.example.angelruiz.cursoandroid.Arrays.ArraySucursales;
import com.example.angelruiz.cursoandroid.R;

import java.util.ArrayList;

public class ReciclerSucursales extends AppCompatActivity {
ArrayList<ArraySucursales>sucursales;
RecyclerView rvSucursales;//declaramos e inicializamos el control recyclerV como cualquier otro
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recicler_sucursales);
        sucursales=new ArrayList<>();//inicializamos el array siempre
        rvSucursales = (RecyclerView)findViewById(R.id.rvSucursales);
        rvSucursales.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));//posision vertical del RV
        //rvSucursales.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));//posision horizontal del RV
        //rvSucursales.setLayoutManager(new GridLayoutManager(this,2));//RV en forma de grid pasamos el numero de columnas

        //sucursales.add(new ArraySucursales(R.drawable.face,"Soriana"));//podemos llenarlo asi, dentro del metodo inicial
        llenarRecycler();//o llamar a un metodo donde llenemos al recyclerV
        AdapterSucursales adapterSucursales=new AdapterSucursales(sucursales,this);//creamos objeto adapter que recibe un array como constructor en esa clase
        rvSucursales.setAdapter(adapterSucursales);//le pasamos el adaptador al RV
    }
        //creamos un metodo para llenar el arrayList, y lo llamamos desde el metodo principal onCreate
        public void llenarRecycler(){
          ArrayList<Integer>imgSucursales=new ArrayList<>();//array para imagenes
          imgSucursales.add(R.drawable.face);
          imgSucursales.add(R.drawable.face);

          String nombre[]={"Soriana","Ahurrera"};//array para nombres
            for (int i = 0; i < imgSucursales.size(); i++) {
             //pasamos como parametros los arrays recorriendolos con for() para colocarlo en cada posision del item del recyclerV
             sucursales.add(new ArraySucursales(imgSucursales.get(i),nombre[i]));
            }
      }
}
