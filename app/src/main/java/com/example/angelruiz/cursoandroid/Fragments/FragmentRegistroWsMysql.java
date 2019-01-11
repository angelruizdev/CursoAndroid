package com.example.angelruiz.cursoandroid.Fragments;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.angelruiz.cursoandroid.R;

import org.json.JSONObject;

import static android.Manifest.permission.INTERNET;

public class FragmentRegistroWsMysql extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

View vista;
Context context;
private final int PERMISO_INTERNET = 100;
EditText etFolio, etNombre, etProfesion;
Button btRegistrar;
ProgressBar progressBar;
RequestQueue colaSolicitud;//permiten hacer conexion con WS
JsonObjectRequest objetoJsonSolicita;

    public FragmentRegistroWsMysql() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_registro_ws_mysql, container, false);
        context = getContext();

        etFolio = vista.findViewById(R.id.etFolio);
        etNombre = vista.findViewById(R.id.etNombre);
        etProfesion = vista.findViewById(R.id.etProfesion);
        btRegistrar = vista.findViewById(R.id.btRegistrar);

        colaSolicitud = Volley.newRequestQueue(context);
        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (permisoInternet()){
                    cargrarWebServices();
                }
            }
        });

        return vista;
    }

    public boolean permisoInternet(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return true;
        }
        if (context.checkSelfPermission(INTERNET)==PackageManager.PERMISSION_GRANTED){
            return true;
        }
        if (shouldShowRequestPermissionRationale(INTERNET)){
            Snackbar.make(vista,"Permisos necesarios", Snackbar.LENGTH_LONG).setAction("Aceptar", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  requestPermissions(new String[]{INTERNET}, PERMISO_INTERNET);
                }
            }).show();
                }else {
                  requestPermissions(new String[]{INTERNET}, PERMISO_INTERNET);
                }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==PERMISO_INTERNET){
            if (grantResults.length==1 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(context, "Permisos aceptados", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void cargrarWebServices() {
        String url = "http://192.168.0.3/webServiceMysql/wsJSONRegistro.php?numeroFolio="+etFolio.getText().toString()+
                "&nombre="+etNombre.getText().toString()+"&profesion="+etProfesion.getText().toString();//ubicacion del WS
        url = url.replaceAll(" ","%20");//reemplazamos los espacios por %20, para no tener problemas con la cadena
        objetoJsonSolicita = new JsonObjectRequest(Request.Method.GET, url,null,this,this);//mandamos la url, por metodo GET, mediante el obj objetoJsonSolicita, para conectarse al WS
        colaSolicitud.add(objetoJsonSolicita);//se lo agregamos a la cola obj solicitud, para comunicarnos con los metodos de las interfaces implementadas

    }


    @Override
    public void onResponse(JSONObject response) {//si la url se conecta con el WS, entra aqui
        Toast.makeText(context, "¡Se registro con exito!", Toast.LENGTH_SHORT).show();
        etFolio.setText("");
        etNombre.setText("");
        etProfesion.setText("");
    }

    @Override
    public void onErrorResponse(VolleyError error) {//si la respuesta de conexion al WS truena lo recibe este metodo
        Toast.makeText(context, "No se pudo registrar"+error, Toast.LENGTH_SHORT).show();
        Log.i("ERROR", error.toString());
    }
}
