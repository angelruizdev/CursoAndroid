package com.example.angelruiz.cursoandroid.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
    EditText etFolio, etNombre, etProfesion, etFolioBuscar, etMostDatos;
    Button btRegistrar, btConsultar;
    ConexionWSMysql conexion;
    ProgressBar progressBar;
    RequestQueue colaSolicitud; //permiten hacer conexion con WS
    JsonObjectRequest objetoJsonSolicita;
    String numeroFolio,nombre,profesion;

    public FragmentRegistroWsMysql() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_registro_ws_mysql, container, false);
        context = getContext();
        progressBar = vista.findViewById(R.id.pbWebService);
        conexion = new ConexionWSMysql();//instanciamos nuestra conexion
        etFolioBuscar = vista.findViewById(R.id.etFolioBuscar);
        etFolio = vista.findViewById(R.id.etFolio);
        etNombre = vista.findViewById(R.id.etNombre);
        etProfesion = vista.findViewById(R.id.etProfesion);
        etMostDatos = vista.findViewById(R.id.etMostDatos);
        btConsultar = vista.findViewById(R.id.btConsultar);
        btRegistrar = vista.findViewById(R.id.btRegistrar);

        btConsultar.setOnClickListener(this);
        btRegistrar.setOnClickListener(this);

        //colaSolicitud = Volley.newRequestQueue(context);
        MiAsyncTask miAsyncTask = new MiAsyncTask();//new MiAsyncTask.execute();
        miAsyncTask.execute();
        return vista;
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.btConsultar:
              buscarDatos();
              break;
          case R.id.btRegistrar:
              insertarDatos();
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
    public void buscarDatos(){
        try {                                                                                    //wsJSONMostDatos.php?numeroFolio=etNum...
            String response = conexion.execute("http://192.168.0.3/webServiceMysql/wsJSONMostDatos.php?numeroFolio="+etFolioBuscar.getText().toString()).get();//ejecutamos la conexion
            JSONArray jsonArray = new JSONArray(response);

            JSONObject jsonObject = jsonArray.getJSONObject(0);
            numeroFolio = jsonObject.getString("numeroFolio");
            nombre = jsonObject.getString("nombre");
            profesion = jsonObject.getString("profesion");

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            etMostDatos.setText(String.valueOf("Folio: "+numeroFolio+"-"+"Nombre: "+nombre+"-"+"Profesion: "+profesion));
        }
        Toast.makeText(context, "Mostrando", Toast.LENGTH_SHORT).show();
    }

    public void insertarDatos() {
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
    }

    public class MiAsyncTask extends AsyncTask<Void, Integer, Void>{
        public MiAsyncTask() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setMax(100);
            progressBar.setProgress(0);
            Toast.makeText(context, "inicio", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            progressBar.setVisibility(View.VISIBLE);
            for (int i = 0; i < 10; i++) {
                publishProgress(i);
                //Toast.makeText(context, ""+i, Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            Toast.makeText(context, "fin", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}