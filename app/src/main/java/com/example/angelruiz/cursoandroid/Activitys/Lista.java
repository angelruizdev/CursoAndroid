package com.example.angelruiz.cursoandroid.Activitys;

import android.content.Intent;
import android.os.Bundle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.angelruiz.cursoandroid.R;

public class Lista extends AppCompatActivity {
SwipeRefreshLayout srlRefresh;
ListView lsvLista;
Adapter adapter;
EditText edtDatos;
//int x;
//Random rnd = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        //x = (int)(rnd.nextInt() + 1);
        //int[] nombres ={x};
        //de ésta forma traemos los valores del array desde archivo satrings.
        String[] nombre = getResources().getStringArray(R.array.nombres);

        edtDatos = (EditText) findViewById(R.id.edtDatos);
        srlRefresh = (SwipeRefreshLayout)findViewById(R.id.srlRefresh);
        lsvLista = (ListView)findViewById(R.id.lsvLista);

        //lsvLista.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, Collections.singletonList(nombres)));
        //lsvLista.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, nombre));
          ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.nombres,
                  android.R.layout.simple_list_item_1);
          lsvLista.setAdapter(adaptador);
          //accedemos al metodo setOnRefreshListener para que ejecute una accion al deslizarlo
          srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(Lista.this, "cargando...", Toast.LENGTH_SHORT).show();
                cargar();
            }
        });
//el metodo escuchador de listview es setOnItemClickListener(), ya que actua en base a la posision(item) que se presione de la lista
        lsvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent nLista=new Intent(getApplicationContext(), ListaContacto.class);
                startActivity(nLista);            }
        });
    }
        public void cargar(){
        //guardamos en un array string el array que tenemos en el archivo strings, y se lo pasamos al adapter
          String[] nombre = getResources().getStringArray(R.array.nombres);
          lsvLista.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,nombre));
          //nos permite ocultar el refresh indicator accediendo mediante el objeto al método setRefreshing();, y pasandole como parametro false.
          srlRefresh.setRefreshing(false);
        }

}
