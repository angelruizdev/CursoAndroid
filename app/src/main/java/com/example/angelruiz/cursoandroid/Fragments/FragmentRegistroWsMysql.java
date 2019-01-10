package com.example.angelruiz.cursoandroid.Fragments;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

public class FragmentRegistroWsMysql extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

View vista;
Context context;
EditText etFolio, etNombre, etOcupasion;
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
        etOcupasion = vista.findViewById(R.id.etOcupasion);
        btRegistrar = vista.findViewById(R.id.btRegistrar);

        colaSolicitud = Volley.newRequestQueue(context);
        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargrarWebServices();
            }
        });

        return vista;
    }

    private void cargrarWebServices() {
      String url = "http://192.168.0.3/webServiceMysql/wsJSONRegistro.php?numeroFolio="+etFolio.getText().toString()+
                            "&nombre="+etNombre.getText().toString()+"&profesion="+etOcupasion.getText().toString();//ubicacion del WS
      url = url.replace(" ","%20");//reemplazamos los espacios por %20, para no tener problemas con la cadena
      objetoJsonSolicita = new JsonObjectRequest(Request.Method.GET, url,null,this,this);//mandamos la url, por metodo GET, mediante el obj objetoJsonSolicita, para conectarse al WS
      colaSolicitud.add(objetoJsonSolicita);//se lo agregamos a la cola obj solicitud, para comunicarnos con los metodos de las interfaces implementadas
    }

    @Override
    public void onResponse(JSONObject response) {//si la url se conecta con el WS, entra aqui
        Toast.makeText(context, "¡Se registro con exito!", Toast.LENGTH_SHORT).show();
        etFolio.setText("");
        etNombre.setText("");
        etOcupasion.setText("");
    }

    @Override
    public void onErrorResponse(VolleyError error) {//si la respuesta de conexion al WS truena lo recibe este metodo
        Toast.makeText(context, "No se pudo registrar"+error, Toast.LENGTH_SHORT).show();
        Log.i("ERROR", error.toString());
    }
}
