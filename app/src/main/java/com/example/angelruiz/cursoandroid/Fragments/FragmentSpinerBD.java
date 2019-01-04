package com.example.angelruiz.cursoandroid.Fragments;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.angelruiz.cursoandroid.Arrays.ArrayProductosBD;
import com.example.angelruiz.cursoandroid.BDSQLite.ConstantesSqlite;
import com.example.angelruiz.cursoandroid.BDSQLite.SQLiteOpnHpr;
import com.example.angelruiz.cursoandroid.R;

import java.util.ArrayList;

public class FragmentSpinerBD extends Fragment {
View vista;
Context context;
public Spinner sprBDSQL;//creamos objeto spinner
ArrayList<String> productosSPR;//este array mostrara los datos en el adapter
ArrayList<ArrayProductosBD> productosBDSQL;//este array guardara los objetos de la consulta
SQLiteOpnHpr conn;//creamos nuestra conexion a la bd

    public FragmentSpinerBD() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     vista = inflater.inflate(R.layout.fragment_spiner_bd, container, false);
      context = getContext();
      conn = new SQLiteOpnHpr(context,ConstantesSqlite.NOMBRE_BD,null,1);
      sprBDSQL = vista.findViewById(R.id.sprBDSQL);//inicializamos el spinner

      mostrarProductos();//este metodo trae los daros a mostrar en el adapter
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, productosSPR);//al adapter le pasamos el array que se lleno con el array de objetos, para que muestre los registros en el spinner
        sprBDSQL.setAdapter(adapter);//mostramos lo que trae el adapter en el spinner
     return vista;
    }

    private void mostrarProductos() {//crea la logica para abrir y traer los datos de la bd, para ser mostrados en el adapter
        SQLiteDatabase db = conn.getReadableDatabase();//abrimos la bd en modo lectura
        ArrayProductosBD productos;//declaramos constructor vacio
        productosBDSQL = new ArrayList<ArrayProductosBD>();//inicializamos nuestros array de objetos

        Cursor fila = db.rawQuery("SELECT * FROM "+ConstantesSqlite.TABLA_DETALLE, null);//creamos objeto cursor para crear y guardar la consulta
         while (fila.moveToNext()){//si el objeto fila se puede mover(iterar,recorrer) al siguiente registro(hay registros)
           productos = new ArrayProductosBD();//inicializamos constructor vacio
           productos.setIdProducto(fila.getInt(0));//le asignamos los valores de la consulta con su metodo set(), recibe como parametro el objeto fila, que con el metodo corespondiente al tipo de tado de la colimna accede a la posision del registro para asignarla
           productos.setNombreProducto(fila.getString(1));
           productos.setPrecioProducto(fila.getString(2));

           productosBDSQL.add(productos);//al array de objetos lo llenamos con el objeto productos, que trae todos los registro devueltos por la consulta
         }
        fila.close();//cerramos el cursor(consulta)
       traerListaProductosBD();//traemos el metodo que muestra los datos en el adapter
    }

    public void traerListaProductosBD(){//metodo tiene la logica para mostrar los registros en el spinner adapter
        productosSPR = new ArrayList<String>();//inicializamos nuestro array, que guardar todos los datos de la consulta
        productosSPR.add("Seleccione: ");//en la posision cero del spinner mostrara, este mensaje

        for (int i = 0; i < productosBDSQL.size(); i++) {//recorremos el array de objetos
            productosSPR.add(productosBDSQL.get(i).getIdProducto()+" - "+productosBDSQL.get(i).getPrecioProducto()+" - "+productosBDSQL.get(i).getNombreProducto());//llenamos este array con el array de objetos, mediante su posision y metodo get()
        }
    }
}
