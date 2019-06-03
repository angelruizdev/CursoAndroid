package com.example.angelruiz.cursoandroid.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.angelruiz.cursoandroid.R;

public class Splash extends Activity {
Button btnMsg;
FloatingActionButton btnFlota;//declaramos el fab
EditText edtNombre;
String nombre;
    //crea la Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();

        edtNombre = (EditText) findViewById(R.id.edtNombres);
        //nombre = edtNombre.getText().toString();
        btnMsg = (Button)findViewById(R.id.btnMsg);
        btnMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Splash.this, "Enviando...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), WebServiceMysql.class));
                //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com"));
                //startActivity(intent);
            }
        });
        //lo inicializamos como cualquier componente
        btnFlota = (FloatingActionButton)findViewById(R.id.btnFloat);
        //nos brinda un escuchador setOnClickListener, para ejecutar una accion
        btnFlota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creamos un snackbar, que se muestra al tocar el fab,el snack bar tambien nos permite
                //un escuchador OnClickListener(), que proporciona una especie de boton que ejecuta una accion a tocarlo
                Snackbar.make(view, "SnackBar...", Snackbar.LENGTH_LONG).setAction("Enviar", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //los dos toast hacen lo mismo a uno se le jala el valor desde el recurso string, podemos hacerlo de las 2 formas
                                //Toast.makeText(Splash.this, "Enviando...", Toast.LENGTH_SHORT).show();
                                Toast.makeText(Splash.this, getResources().getString(R.string.enviar), Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(),Bienvenido.class);
                                intent.putExtra("Nombre", edtNombre.getText().toString());
                                startActivity(intent);
                                finish();//para no tener todas las activitis que hemos abierto en cola,
                                        // despues de pasar a la otra activity destruimos esta con finish() y asi solo esta en cola la actual,
                                       //esto ahorra espacio en memoria
                            }
                        })
                        //podemos jalar el color de las 2 formas.
                        //.setActionTextColor(Color.GREEN)//nativo con java
                        .setActionTextColor(getResources().getColor(R.color.colorPrimary))//trallendo el recurso del archivo @colors
                        .show();
            }
        });
    }
    //Inicia la Activity
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }
    //Reinicia la Activity si se vuelve a acceder a ella
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }
    //Corre la Activity, la muestra
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }
    //Pausa la Activity si se sale de ella con Home o se pasa a otra Activity o app
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }
    //Detiene la Activity si se sale de ella con Home o se pasa a otra Activity o app
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }
    //Destrulle la Activity, si se presiona el boton back, o se libera estpacio
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }
}
