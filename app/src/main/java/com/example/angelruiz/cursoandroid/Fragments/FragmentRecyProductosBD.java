package com.example.angelruiz.cursoandroid.Fragments;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.angelruiz.cursoandroid.Adapters.AdapterFragmentRecyProdBd;
import com.example.angelruiz.cursoandroid.Arrays.ArrayProductosBD;
import com.example.angelruiz.cursoandroid.BDSQLite.ConstantesSqlite;
import com.example.angelruiz.cursoandroid.BDSQLite.SQLiteOpnHpr;
import com.example.angelruiz.cursoandroid.Interfaces.InterfComunicaFgmtDetBDyProBD;
import com.example.angelruiz.cursoandroid.R;

import java.util.ArrayList;

    public class FragmentRecyProductosBD extends Fragment {
    View vista;
    Activity activity;//este objeto de tipo Activity permite establecer la coneccion con la interface, mediante el metodo onAttach de fragments
    InterfComunicaFgmtDetBDyProBD comunicador;//creamos un objeto de la interface para acceder a su metodo y pasarle como argmento todo el contenido del RV y asi pasarla a otro fragment
    Context context;
    SQLiteOpnHpr conn;
    RecyclerView rvProductosBD;
    ArrayList<ArrayProductosBD> listaProductosBD;

        public FragmentRecyProductosBD() {
            // Required empty public constructor
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      vista = inflater.inflate(R.layout.fragment_recy_productos_bd, container, false);
       context = getContext();
       conn = new SQLiteOpnHpr(context, ConstantesSqlite.NOMBRE_BD, null, 1);
       rvProductosBD = vista.findViewById(R.id.rvProductosBD);
       rvProductosBD.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
       DividerItemDecoration did = new DividerItemDecoration(context, LinearLayoutManager.VERTICAL);//linea divisora de items
       rvProductosBD.addItemDecoration(did);
       llenarRecyclerBD();

        final AdapterFragmentRecyProdBd adapter = new AdapterFragmentRecyProdBd(listaProductosBD, context);
        adapter.setOnCliclListener(new View.OnClickListener() {//este metodo proviene deladapter para poder dar click en el item del RV
            @Override
            public void onClick(View v) {//si damos click en un item del RV envia los datos del RV a la interface y activity padre
            comunicador.enviarDatos(listaProductosBD.get(rvProductosBD.getChildAdapterPosition(v)));//accedemos al metodo de la interfaz el cual recibe un Array de clase, le pasamos el array,listaProductosBD el cual tiene todas las posisones del RV

            /*int pos = rvProductosBD.getChildAdapterPosition(v);//nos permite pasar valores del RV mas especifico no todo
            comunicador.enviarDatos(String.valueOf(listaProductosBD.get(pos).getPrecioProducto()));*/
            }
        });
        rvProductosBD.setAdapter(adapter);

               //comunicador.enviarDatos(nombre);//con el objeto interface accedemos a su metodo y le pasamos la cadena a enviar al otro fragment//sirbe para pasar datos de un frgmnt a otro
               /*FragmentRecyDetalleBD enviar = new FragmentRecyDetalleBD();//pasa datos a un fragment, funciona en un activity
                Bundle bundle = new Bundle();
                bundle.putString("nombre", nombre);
                enviar.setArguments(bundle);*/

      return vista;
    }

    public void llenarRecyclerBD(){//llenamos el RV con datos de SQLite
        SQLiteDatabase db = conn.getReadableDatabase();
        ArrayProductosBD productosBD;
        listaProductosBD=new ArrayList<>();

        Cursor fila = db.rawQuery(" SELECT * FROM "+ ConstantesSqlite.TABLA_DETALLE, null);

         while (fila.moveToNext()){
           productosBD = new ArrayProductosBD();
           productosBD.setIdProducto(fila.getInt(0));
           productosBD.setNombreProducto(fila.getString(1));
           productosBD.setPrecioProducto(fila.getString(2));
           productosBD.setFotoProducto(fila.getInt(3));

           listaProductosBD.add(productosBD);
         }
         fila.close();
    }

    @Override
    public void onAttach(Context context) {//este metodo es propio del ciclo de vida de fragment, permite comunicarce con la activity padre que contiene ambos frgmnts
        super.onAttach(context);

        if (context instanceof  Activity){
            this.activity = (Activity)context;
            comunicador = (InterfComunicaFgmtDetBDyProBD) this.activity;//casteamos el context de la activity a la interface
        }
    }
}
