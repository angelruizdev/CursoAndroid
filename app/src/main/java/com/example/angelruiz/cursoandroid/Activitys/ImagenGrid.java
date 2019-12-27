package com.example.angelruiz.cursoandroid.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.example.angelruiz.cursoandroid.Adapters.AdapterImagenContacto;
import com.example.angelruiz.cursoandroid.Arrays.ArrayImgContacto;
import com.example.angelruiz.cursoandroid.Fragments.FragmentSpinerBD;
import com.example.angelruiz.cursoandroid.R;

import java.util.ArrayList;

public class ImagenGrid extends AppCompatActivity {

private GridView gvImgContact;
public FragmentSpinerBD fragmentSprDB;
public Spinner sprDB; //declaramos el spr

//creamos un ArrayList de tipo ArrayImgContacto, para llenarlo con ese tipo de datos
ArrayList<ArrayImgContacto> nombreContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagen_grid);

        Toolbar toolbar = findViewById(R.id.actionBarPersonalizado);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setTitle("Angel"); //muestra titulo en toolbar
        gvImgContact = findViewById(R.id.gvImgContact);
        fragmentSprDB = new FragmentSpinerBD();
        sprDB = findViewById(R.id.sprBD); //inicializamos el spr
        sprDB.setVisibility(View.GONE); //no se muestra el spiner al iniciar la activity(no ocupa su espacio, Invisible si)

        //arraylist para los nombre
        nombreContactos = new ArrayList<>();
        final ArrayList<String>nombre=new ArrayList<>();
            nombre.add("Juan");
            nombre.add("Ángel");
            nombre.add("Maia");
            nombre.add("Pedro");
            nombre.add("Sandra");
            nombre.add("Karen");

        //arraylist para imagenes
        final ArrayList<Integer> imgContacto = new ArrayList<>();
            imgContacto.add(R.drawable.ic_touch_app);
            imgContacto.add(R.drawable.email);
            imgContacto.add(R.drawable.phone);
            imgContacto.add(R.drawable.ic_touch_app);
            imgContacto.add(R.drawable.email);
            imgContacto.add(R.drawable.phone);

        //recorremos dichos arrayslist y llenamos pasando como parametro los 2 arrays nombre e imagenContacto a nomContactos
        for (int i = 0; i < nombre.size(); i++){
          nombreContactos.add(new ArrayImgContacto(1, imgContacto.get(i), nombre.get(i)));//llenamos con constructor
        }

        //creamos nuestro objeto adapter tipo(constructor), el cual recibe como parametro el contexto y un array, como lo indica el array de clase AdapterImagenContacto
        final AdapterImagenContacto adapter = new AdapterImagenContacto(ImagenGrid.this, nombreContactos);
        gvImgContact.setAdapter(adapter);//mostramos lo que trae el adapter en el gridview con el metodo setAdapter()
        //AdapterImagenContacto adpr = new AdapterImagenContacto(getApplicationContext(),nomContactos);
        //gvImgContact.setAdapter(adpr);
        //final AdapterImagenContacto adapterImgContact = new AdapterImagenContacto(ImagenGrid.this,R.layout.vista_adp_imgcontact,nomContactos,"");
        //gvImgContact.setAdapter(adapterImgContact);

        //we pass image and text to the grid to detalleimgcontacto ussing interface Parceable
        gvImgContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent passDataContactDetail = new Intent(getApplicationContext(), DetalleImgContacto.class);
                passDataContactDetail.putExtra("nombreContactos", nombreContactos.get(position)); //we pass whole the array in the key
                startActivity(passDataContactDetail);

                /*passing data with interface serializable
                Intent detalleImgContacto = new Intent(getApplicationContext(),DetalleImgContacto.class);
                //hacemos un cast para guardar en objeto la imagen y texto seleccionado de un item y lo enviamos a DetalleImgContacto con putExtra()
                ArrayImgContacto datosDetalle = (ArrayImgContacto)adapterView.getItemAtPosition(position);
                detalleImgContacto.putExtra("datosContacto",(Serializable)datosDetalle);
                startActivity(detalleImgContacto);*/
                //getSupportActionBar().setTitle(nombre.get(position)); //muestra el nombre de la poision seleccionada en la toolbar
            }
        });
    }

    //sobreescrivimos el metodo:onOptionsContextMenu:el cual perite inflar el menu con el archivo .menu. de res que tiene a los items a mostrar
    //llamado:menu_contexto usando el metodo inflate pasando el recurso y el objeto menu y retornamos true
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       //inflamos el menú
       getMenuInflater().inflate(R.menu.menu_opsiones, menu);
       return true;
    }

    //sobreescribimos el metodo:onOptionsItemSelected:el cual nos ayuda a manipular la opsion del item seleccionado del menu
    //mediante el parametro item y el metodo:getItemId() y switch buscamos por id el item seleccionado para que realice una accion si se selecciona el item con dicho id
    //en este mismo metodo colocamos el id del item que esta en el app bar para que aga algo si lo presionan
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()){
            case R.id.fragmentSpnrBD: //id de la primera opsion del menu 3pnts
                transaction.replace(R.id.contentFmt1, fragmentSprDB);
                break;
            case R.id.muestraSpinner:
                sprDB.setVisibility(View.VISIBLE);//muestra el spiner
                break;
            case R.id.menuRecyBD: //id del icono del appbar
                startActivity(new Intent(getApplicationContext(), ActivityFragmentProdMaDeBD.class));
                break;
            case R.id.menuTocarTabs:
                startActivity(new Intent(getApplicationContext(), ViewPagerTabs.class));
                break;
        }
      transaction.commit();
    return super.onOptionsItemSelected(item);
     }
}
