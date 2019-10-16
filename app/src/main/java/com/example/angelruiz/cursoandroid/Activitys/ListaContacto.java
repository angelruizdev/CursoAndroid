package com.example.angelruiz.cursoandroid.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.angelruiz.cursoandroid.Arrays.ArrayContactos;
import com.example.angelruiz.cursoandroid.R;

import java.io.FileOutputStream;
import java.util.ArrayList;
//almacenamiento File I/O y Shared Preference

public class ListaContacto extends AppCompatActivity implements View.OnClickListener {//implementamos la interface View.OnClickListener, para poner a escuchar a todos los botones dentro de su metodo
    //creamos nuestro arraylist contactos, le pasamos el arraycontactos, para que sea de ese tipo como si fuera ArrayList<String>
    ArrayList<ArrayContactos> contactos;

    //declaramos nuestro listview como cualquier componente
    ListView listaContactos;
    EditText etNombre, etTelefono;
    TextView tvMosShPreference;
    Button btnGuardarFileIO,btnGuardShPreference,btnMosShPreference;
    ArrayList<String> lstNombreContacto;
    int cont = 0;
    String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_contacto);

        //instanciamos(inicializamos el array)
        contactos = new ArrayList<ArrayContactos>();

        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);
        tvMosShPreference = findViewById(R.id.tvMosShPreference);
        btnGuardarFileIO = findViewById(R.id.btnGuardarFileIO);
        btnGuardShPreference = findViewById(R.id.btnGuardShPreference);
        btnMosShPreference = findViewById(R.id.btnMosShPreference);

        //ponemos a escuchar a cada boton
        btnGuardarFileIO.setOnClickListener(this);
        btnGuardShPreference.setOnClickListener(this);
        btnMosShPreference.setOnClickListener(this);

        //llenamos el array contactos, mediante el constructor del arraycintactos que recibe 3 parametros
        contactos.add(new ArrayContactos("Juan","5566779900","juan@gmai.com"));
        contactos.add(new ArrayContactos("maria","5599203940","mari@hotmail.com"));
        contactos.add(new ArrayContactos("pancho","5519382930","pancho@outlook.com"));

        //inicializamos nuestro listview como cualquier componente
        listaContactos = findViewById(R.id.lsvLista);

        //como en la lista solo solo mostraremos el nombre del contacto, creamos un nuevo arraylist para guardarlos ahi
        //usamos foreach para recorrer ArrayContactos, y agregar cada nombre mediante su metodo get() y el alias contacto, al array lstNombreContacto
        lstNombreContacto = new ArrayList<>();

        for (ArrayContactos nombres : contactos) {
            lstNombreContacto.add(nombres.getNombre());
        }

        //creamos el adaptador para mostrar el array lstNombreContacto en el listview
        listaContactos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lstNombreContacto));

        //el metodo escuchador de listview es setOnItemClickListener(), ya que actua en base a la posision(item) que se presione de la lista
        //creamos un metodo escuchador de listview para que al precionar un item(nombre) de la lista ejecute una accion, en este caso pasa a otra actividad
        listaContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long ld) {
                //Toast.makeText(adapterView.getContext(),"Nombre: "+adapterView.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
                Intent detalleContacto = new Intent(getApplicationContext(), DetalleContacto.class);
                //detalleContacto.putExtra(getString(R.string.param),contactos.get(position).getNombre());
                detalleContacto.putExtra("nombre", contactos.get(position).getNombre());
                detalleContacto.putExtra("telefono", contactos.get(position).getTelefono());
                detalleContacto.putExtra("email", contactos.get(position).getEmail());
                startActivity(detalleContacto);
            }
        });
    }

    //almacenamiento File i/o y SP
    @Override
    public void onClick(View v) {//metodo de la interface, mediante case y id llamamos a cada boton, cada uno ejecuta el metodo correspondiente al case
      switch (v.getId()){
          case R.id.btnGuardarFileIO:
              crearArchivo(v);
              break;
          case R.id.btnGuardShPreference:
              guardarShPreference(v);
              break;
          case R.id.btnMosShPreference:
              mostrarShPreference(v);
              break;
       }
    }
        //almacenamiento File I/O
        public void crearArchivo(View view){//este metodo crea un archivo y escibe en el mediante la entrada etNombre
             nombre = etNombre.getText().toString();

             if (nombre.length() <= 0){//si el tamaño de la cadena es <=0 no crea el archivo
                 Toast.makeText(ListaContacto.this, "ingresa", Toast.LENGTH_SHORT).show();
             }else {
                 cont++;//cada que se crea un archivo aumenta en 1, solo es para dejar de mostrar el mensaje de archivo creado
                 try {
                     FileOutputStream fileOutputStream = null;//creamos un ubjeto de esta clase para crear nuestro archivo
                     fileOutputStream = openFileOutput("ArchivoApp", Context.MODE_PRIVATE);//con el metodo: openFileOutput() creamos el archivo en el primer parametro llamado, ArchivoApp en el segundo parametro ponemos el modo de acceso
                     fileOutputStream.write(nombre.getBytes());//le pasamos la cadena nombre que guardara dentro del archivo, usamos el metodo getBytes(), para que sea mas rapido el envio de la cadena
                     fileOutputStream.close();//cerramos el archivo para poder crearlo
                 }catch (Exception e){
                     e.printStackTrace();//gestionamos si acurrido algun error al crear el archivo
                     Toast.makeText(this, "¡Error al crear archivo!", Toast.LENGTH_SHORT).show();
                 }
                 if (cont == 1) {//si es la primera ves que se crea el archivo, despues ya no mostrara el mensaje
                     Toast.makeText(this, "Archivo creado", Toast.LENGTH_SHORT).show();
                 }
                 etNombre.setText("");
             }
             //para ver este archivo, device file explorer>data>data>nomDeLProyecto>File>
       }

        //almacenamiento SharedPreference(SP)
        public void guardarShPreference(View v){//este metodo crea un archivo xml, y escribe dentro de el

        String telefono = etTelefono.getText().toString();//testo a guardar
          if (!telefono.equals("")) {//si el ET tiene algo se crea la preferencia(archivo XML)
              SharedPreferences sharedPreferences = getSharedPreferences("MiSharedPreference", Context.MODE_PRIVATE);//creamos un objeto de la clase SP, lo igualamos al metodo getSP() el cual permite crear nuestro archivo, recibe en primer parametro el nombre de este y el segundo el modo de acceso a el
              SharedPreferences.Editor editor = sharedPreferences.edit();//mediante el objeto editor se nos permite escribir(manipularlo) en este archivo
              editor.putString("telefono", telefono);//mediante el objeto editor enviamos el contenido al archivo con la forma clave-valor
              editor.apply();//finalmete con el metodo apply(), garantizamos que se cree el archivo y se mande es contenido
              Toast.makeText(this, "Se creo la Shared Preference", Toast.LENGTH_SHORT).show();
              etTelefono.setText("");
          }
          //para ver este SP, device file explorer>data>data>nomDeLProyecto>shared_prefs>
        }

        //este metodo recupera el contenido enviado y lo muestra en un TV
        public void mostrarShPreference(View v){
          SharedPreferences sharedPreferences = getSharedPreferences("MiSharedPreference", Context.MODE_PRIVATE);//puede ser global para no sobreescribirlo
          String telefono = sharedPreferences.getString("telefono", "No se encontro la variable");//recuperamos el contenido mediante la clave
          String datos = "File: "+nombre+"\n ShaPrf: "+telefono;//en datos guardamos la variable de File I/O y el contenido de SP
          tvMosShPreference.setText(datos);//los mostramos en el TV
        }
}