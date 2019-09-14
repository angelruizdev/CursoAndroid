package com.example.angelruiz.cursoandroid.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.angelruiz.cursoandroid.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class Splash extends Activity {

 Button btnMsg;
 FloatingActionButton btnFlota;//declaramos el fab
 EditText edtNombre;
 String nombre;

    //crea la Activity por primera vez o despues de destruirce
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Toast.makeText(this, "Act onCreate", Toast.LENGTH_SHORT).show();

        edtNombre = findViewById(R.id.edtNombres);
        //nombre = edtNombre.getText().toString();
        btnMsg = findViewById(R.id.btnMsg);
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
        btnFlota = findViewById(R.id.btnFloat);
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

    //Inicia la Activity por primera vez o despues de destruirce
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Act onStart", Toast.LENGTH_SHORT).show();
    }

    //Reinicia la Activity si se vuelve a acceder a ella, si se salio de ella con home y se vuelve a entrar (save his state)
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Act onRestart", Toast.LENGTH_SHORT).show();
    }

    //Muestra el activity al ser creada, o si se salio de ella con home y se vuelve a entrar (save his state)
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Act onResume", Toast.LENGTH_SHORT).show();
    }

    //Pausa la Activity si se sale de ella con Home o se pasa a otra Activity o app (save his state)
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Act onPause", Toast.LENGTH_SHORT).show();
    }

    //Detiene la Activity si se sale de ella con Home o se pasa a otra Activity o app (save his state)
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Act onStop", Toast.LENGTH_SHORT).show();
    }

    //Destrulle la Activity, si se presiona el boton back, o se libera estpacio
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Act onDestroy", Toast.LENGTH_SHORT).show();
    }
}
