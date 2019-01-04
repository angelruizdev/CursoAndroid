package com.example.angelruiz.cursoandroid.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.angelruiz.cursoandroid.Adapters.AdapterFragmentListaRecycler;
import com.example.angelruiz.cursoandroid.Arrays.ArrayFragmentListaRecycler;
import com.example.angelruiz.cursoandroid.R;

import java.util.ArrayList;

public class FragmentListaRecycler extends Fragment {
    View vista;
    ArrayList<ArrayFragmentListaRecycler> personas;
    ArrayList<String> nombres;
    RecyclerView rvPersonas;
    TextView tvIngresar;
    Button btnDatos;
    int pos;
    Context context;
    RecyclerView.ViewHolder viewHolder;//creamos un objeto ,viewHolder, de este tipo para poder obtener la posision del adapterRV desde el AdapterFragmentListaRecycler
    int positionAFLR;//guarda la posision enviada desde el adaptadorFLR

    public FragmentListaRecycler() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {//metdo principal onCreate()
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_lista_recycler, container, false);
        context = getContext();
        personas = new ArrayList<>();
        nombres = new ArrayList<>();
        tvIngresar = vista.findViewById(R.id.tvIngresar);
        rvPersonas = vista.findViewById(R.id.rvPersonas);
        rvPersonas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        registerForContextMenu(rvPersonas);//pasamos el RV como parametro al metodo registerForContextMenu(), para inicializarlo y visualizarlo,(menuContext)

        btnDatos = vista.findViewById(R.id.btnDatos);
        btnDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llenarRecycler();
                recyclerAdapter();
            }
        });
        return vista;
    }

    public void llenarRecycler() {
        nombres.add(tvIngresar.getText().toString());
        for (String noms : nombres) {
            personas.add(new ArrayFragmentListaRecycler(R.drawable.phone, noms));
            nombres.clear();//limpia el arreglo
            tvIngresar.setText("");
        }//tvIngresar.getText().toString().length()<1 podemos checar el tamaño de la cadena dentro del TV
    }

    public void recyclerAdapter() {
        final AdapterFragmentListaRecycler adapter = new AdapterFragmentListaRecycler(personas, getActivity(), getContext());//también podemos declarar el adapter hasta arria en inicializarlo en onCreateView
        adapter.setOnClickListener(new View.OnClickListener() {//mediante el objeto adapter accedemos al metodo: SetOnClickListener del adaptador, para dar eventos a cada item del RV
            @Override
            public void onClick(View view) {//nos genera el metodo onClick del adapterFLR, aqui ya damos funciones a cada item
                //mediante el array personas y el metodo get(), y getChildAdapterPosition() y getNomPersona(),
                pos = rvPersonas.getChildAdapterPosition(view);//nos indicarán la posición de una vista dentro del adaptador
                viewHolder = (RecyclerView.ViewHolder) view.getTag ();//guardamos en el objeto viewHolder el ,RecyclerView.ViewHolder, ya que mediante view podremos recivir la posision desde el adapterFLR, con el metodo getTag()
                positionAFLR=viewHolder.getAdapterPosition();//mediante el objeto viewHolder accedemos al metodo(), getAdapterPosition(), el cual tiene la posision de cada item del RV, la cual la guardamos en la variable ,positionAFLR, y asi manipular dicha posision
                Toast.makeText(getContext(), "Nombre: " + personas.get(rvPersonas.getChildAdapterPosition(view)).getNomPersona() + "\n" + positionAFLR, Toast.LENGTH_SHORT).show();
            }
        });
        rvPersonas.setAdapter(adapter);
    }
    //este metodo nos permite darle funcion al menuContext en base al item seleccionado conectandoce con el adapter
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(getContext(), "borraste", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getContext(), "ver", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

