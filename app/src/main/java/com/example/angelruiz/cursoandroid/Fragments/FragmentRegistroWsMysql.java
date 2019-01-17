package com.example.angelruiz.cursoandroid.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.angelruiz.cursoandroid.R;
import com.example.angelruiz.cursoandroid.WebServiceMysql.ConexionWSMysql;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class FragmentRegistroWsMysql extends Fragment implements View.OnClickListener /*implements Response.Listener<JSONObject>, Response.ErrorListener */{

    View vista;
    Context context;
    EditText etFolio, etNombre, etProfesion;
    Button btRegistrar, btConsultar;
    ConexionWSMysql conexion;
    ProgressBar progressBar;
    RequestQueue colaSolicitud;//permiten hacer conexion con WS
    JsonObjectRequest objetoJsonSolicita;

    public FragmentRegistroWsMysql() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_registro_ws_mysql, container, false);
        context = getContext();
        conexion = new ConexionWSMysql();//instanciamos nuestra conexion
        etFolio = vista.findViewById(R.id.etFolio);
        etNombre = vista.findViewById(R.id.etNombre);
        etProfesion = vista.findViewById(R.id.etProfesion);
        btRegistrar = vista.findViewById(R.id.btRegistrar);
        btConsultar = vista.findViewById(R.id.btConsultar);

        btRegistrar.setOnClickListener(this);
        btConsultar.setOnClickListener(this);

        //colaSolicitud = Volley.newRequestQueue(context);
        return vista;
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.btRegistrar:

              try {
                  String response = conexion.execute("http://192.168.0.3/webServiceMysql/wsJSONRegistro.php?numeroFolio="
                                  +etFolio.getText().toString()
                                  +"&nombre="+etNombre.getText().toString()
                                  +"&profesion="+etProfesion.getText().toString()).get();

                  if (response!=null){
                      Toast.makeText(context, "¡Registro guardado!", Toast.LENGTH_SHORT).show();
                      etFolio.setText("");
                      etNombre.setText("");
                      etProfesion.setText("");
                  }
              } catch (ExecutionException e) {
                  e.printStackTrace();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              break;

          case R.id.btConsultar:
              Toast.makeText(context, "isissi", Toast.LENGTH_SHORT).show();
              try {                                                                                    //wsJSONMostDatos.php?numeroFolio=etNum...
                  String response = conexion.execute("http://192.168.0.3/webServiceMysql/wsJSONMostDatos.php?numeroFolio="+etFolio.getText().toString()).get();//ejecutamos la conexion
                  JSONArray jsonArray = new JSONArray(response);

                      JSONObject jsonObject = jsonArray.getJSONObject(0);
                      String numeroFolio = jsonObject.getString("numeroFolio");
                      String nombre = jsonObject.getString("nombre");
                      String profesion = jsonObject.getString("profesion");

                      etFolio.setText(numeroFolio);
                      etNombre.setText(nombre);
                      etProfesion.setText(profesion);

              } catch (ExecutionException e) {
                  e.printStackTrace();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              } catch (JSONException e) {
                  e.printStackTrace();
              }
          break;
      }
    }
        /*String url = "http://192.168.0.3/webServiceMysql/wsJSONRegistro.php?"
                +"numeroFolio="+etFolio.getText().toString()
                +"&nombre="+etNombre.getText().toString()
                +"&profesion="+etProfesion.getText().toString();//ubicacion del WS
        url = url.replaceAll(" ","%20");//reemplazamos los espacios por %20, para no tener problemas con la cadena
        objetoJsonSolicita = new JsonObjectRequest(Request.Method.GET, url,null,this,this);//mandamos la url, por metodo GET, mediante el obj objetoJsonSolicita, para conectarse al WS
        colaSolicitud.add(objetoJsonSolicita);//se lo agregamos a la cola obj solicitud, para comunicarnos con los metodos de las interfaces implementadas            @Override*/


    /*@Override
    public void onResponse(JSONObject response) {//si la url se conecta con el WS, entra aqui
        Snackbar.make(vista, "¡Registro exisoto!", Snackbar.LENGTH_SHORT)
        .setAction("", null).show();
        etFolio.setText("");
        etNombre.setText("");
        etProfesion.setText("");
    }

    @Override
    public void onErrorResponse(VolleyError error) {//si la respuesta de conexion al WS truena lo recibe este metodo
        Snackbar.make(vista, "¡No registrado!", Snackbar.LENGTH_SHORT)
        .setAction("", null).show();
        Log.i("ERROR", error.toString());
    }*/
}
