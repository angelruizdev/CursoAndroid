package com.example.angelruiz.cursoandroid.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.angelruiz.cursoandroid.R;

public class Bienvenido extends AppCompatActivity {
    private EditText edt;
    private Button btn;
    public TextView tvNombre;
    String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
        Bundle extra = getIntent().getExtras();
        String nom = getIntent().getStringExtra("Nombre");
        int so = extra.getInt("entero");

        tvNombre = (TextView) findViewById(R.id.tvNombre);
        edt=findViewById(R.id.edt);

        tvNombre.setText(nom);
        btn = (Button) findViewById(R.id.btnPassword);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               pass=edt.getText().toString();//dentro del metodo guardamos lo que tenga el ET en la variable pass
                if (pass.length()>4){//con el metodo length() contamos el tamaño de la cadena, si es >4 pasa a otra Activity
                   Intent enviar = new Intent(getApplicationContext(), ListaContacto.class);
                   startActivity(enviar);
                }
            }
        });
    }
    //con este método onKeyDown(), vemos que boton presiono el usuario si fue el de back lo regresara a dicha clase,
    //ya que de la que viene se destruto con finish, si no pusieramos este metodo al dar back se finaliza la app ya que no hay otra atras en cola
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
          Intent regSplash=new Intent(getApplicationContext(), Splash.class);
          startActivity(regSplash);
        }
        return super.onKeyDown(keyCode, event);
    }
//este metodo mostrara el menu si presionan la imagen, de igual forma mediante un objeto de la clase PopMenu
//inflamos el menu pasandole el nombre del archivo menu, este menu tiene su propio metodo escuchador:setOnMenuItemClickListener:
//con el cual mediante su parametro:menuItem:podemos saber cual item se selecciona del menu utilizando switch mediante el id del item
    public void menuPopup(View v){
        ImageView ivMenuPopDetalle=(ImageView)findViewById(R.id.menuPopDetalle);
        PopupMenu popupMenu = new PopupMenu(this, ivMenuPopDetalle);//el objeto recibe al control en el constructor
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());//inflamo el menu
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.mDetalle:
                      Toast.makeText(getBaseContext(), "Facebook", Toast.LENGTH_SHORT).show();//recibe un contexto diferente:getBaseContext:al estar dentro de un metodo
                    break;
                }
                return true;//retornamos true
            }
        });
        popupMenu.show();
    }
}
