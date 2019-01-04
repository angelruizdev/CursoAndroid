package com.example.angelruiz.cursoandroid.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.angelruiz.cursoandroid.Adapters.AdapterAgenda;
import com.example.angelruiz.cursoandroid.Arrays.ArrayAgenda;
import com.example.angelruiz.cursoandroid.R;

import java.util.ArrayList;

public class ListaAgenda extends AppCompatActivity {
ListView lstvAgenda;
ArrayList<ArrayAgenda>agenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_agenda);
        lstvAgenda=(ListView)findViewById(R.id.lstvAgenda);
        registerForContextMenu(lstvAgenda);
        agenda=new ArrayList<>();

        agenda.add(new ArrayAgenda(R.drawable.ic_touch_app,"Ángel","5511220088"));
        agenda.add(new ArrayAgenda(R.drawable.phone,"Mario","5599220098"));
        agenda.add(new ArrayAgenda(R.drawable.ic_touch_app,"Ángel","5511220088"));
        agenda.add(new ArrayAgenda(R.drawable.phone,"Mario","5599220098"));
        agenda.add(new ArrayAgenda(R.drawable.ic_touch_app,"Ángel","5511220088"));
        agenda.add(new ArrayAgenda(R.drawable.phone,"Mario","5599220098"));
        agenda.add(new ArrayAgenda(R.drawable.ic_touch_app,"Ángel","5511220088"));
        agenda.add(new ArrayAgenda(R.drawable.phone,"Mario","5599220098"));

        AdapterAgenda adapterAgenda=new AdapterAgenda(ListaAgenda.this, agenda);
        lstvAgenda.setAdapter(adapterAgenda);

        lstvAgenda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(ListaAgenda.this, "position"+position, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),ReciclerSucursales.class));
            }
        });
    }
//sobreescrivimos el metodo:onCreateContextMenu:el cual perite inflar el menu con el archivo .menu. de res que tiene a los items a mostrar
//llamado:menu_opsiones usando el metodo inflate pasando el recurso y el objeto menu y retornamos true
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu, v, menuInfo);
            getMenuInflater().inflate(R.menu.menu_contexto, menu);//inflamos el menú
        }
//sobreescribimos el metodo:onContextItemSelected:el cual nos ayuda a manipular la opsion del item seleccionado del menu
//mediante el parametro item y el metodo:getItemId() y switch buscamos por id el item seleccionado para que realice una accion si se selecciona el item con dicho id
        @Override
        public boolean onContextItemSelected(MenuItem item) {
            switch (item.getItemId()){
                case R.id.lvNombre:
                 Toast.makeText(this, "Dice:"+item, Toast.LENGTH_SHORT).show();
                break;
                case R.id.lvTelefono:
                 Toast.makeText(this, "Dice:"+item, Toast.LENGTH_SHORT).show();
                break;
            }
            return super.onOptionsItemSelected(item);
        }
}
