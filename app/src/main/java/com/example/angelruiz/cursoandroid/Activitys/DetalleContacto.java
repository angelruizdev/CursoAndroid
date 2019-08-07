package com.example.angelruiz.cursoandroid.Activitys;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.angelruiz.cursoandroid.R;

import static android.Manifest.permission.CALL_PHONE;
import static android.content.Intent.ACTION_CALL;

public class DetalleContacto extends AppCompatActivity {
    String nombre, telefono, email;
    private TextView tvNombre, tvTelefono, tvEmail;
    FloatingActionButton fabGrid;
    private final int PERMISSION = 100;
    private final int LLAMADA = 200;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_contacto);
        //damos soporte al actionBar personalizado
        Toolbar actionBar=(Toolbar)findViewById(R.id.actionBarPersonalizado);//v7.widget
        setSupportActionBar(actionBar);
        //mostramos la flechita de regreso en el actionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nombre = getIntent().getStringExtra("nombre");
        telefono = getIntent().getStringExtra("telefono");
        email = getIntent().getStringExtra("email");

        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvTelefono = (TextView) findViewById(R.id.tvTelefono);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        fabGrid = (FloatingActionButton)findViewById(R.id.fabGrid);

        tvNombre.setText(nombre);
        tvTelefono.setText(telefono);
        tvEmail.setText(email);

        fabGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(getApplicationContext(),ImagenGrid.class)); //intent compacto
            }
        });
    }
    //pasamos ambos metodos al lineallayout, con la propiedad onClick="telefono", para que al precionar el numero o email, se ejecute el metodo y el intent que llama o manda correo
    //metodo para llamar con intent implicito
    public void telefono(View v) {

        if (permisoLlamada()){
           Intent llamada = new Intent(ACTION_CALL, Uri.parse("tel:"+telefono));
           startActivityForResult(llamada, LLAMADA);
           Toast.makeText(this, "llamando a: "+telefono, Toast.LENGTH_SHORT).show();
        }
     }

    public boolean permisoLlamada(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return true;
        }
        if (getApplicationContext().checkSelfPermission(CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(CALL_PHONE)){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
             alertDialog.setIcon(R.drawable.phone);
             alertDialog.setTitle("Permisos necesarios");
             alertDialog.setMessage("Activarlos para su completo funcionamiento");
             alertDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                 @TargetApi(Build.VERSION_CODES.M)
                 @RequiresApi(api = Build.VERSION_CODES.M)
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                    requestPermissions(new String[]{CALL_PHONE}, PERMISSION);
                 }
             });
             alertDialog.show();
            }else {
                    requestPermissions(new String[]{CALL_PHONE}, PERMISSION);
          }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==PERMISSION){
            if (grantResults.length == 1 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permisos aceptados", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //metodo para mandar correo con intent implicito
        @SuppressLint("IntentReset")
        public void correo(View v){
            //Toast.makeText(this, "em"+email, Toast.LENGTH_SHORT).show();
            //guardamos el email que esta en el TV, en un array string y ese se lo pasamos al EXTRA_EMAIL, para mandarle un email
            String[] to = {tvEmail.getText().toString()};
            Intent mail=new Intent((Intent.ACTION_SEND));
            mail.setData(Uri.parse("mailto:"));
            mail.putExtra(Intent.EXTRA_EMAIL, to);//recibimos el array que guarda el correo
            mail.setType("text/plain");//le decimos que es texto plano con metodo .setType()
            startActivity(Intent.createChooser(mail,"Email"));//esta linea nos permite abrir las apps disponibles para enviar un mail
        }
}
