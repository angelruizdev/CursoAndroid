package com.example.angelruiz.cursoandroid.Fragments;


import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.angelruiz.cursoandroid.Activitys.WebServiceMysql;
import com.example.angelruiz.cursoandroid.Adapters.AdapterApiRest;
import com.example.angelruiz.cursoandroid.ArraysAPI_REST.ArrayWSMysqlApi;
import com.example.angelruiz.cursoandroid.InterfazAPI_REST.IEndPointAPI_REST;
import com.example.angelruiz.cursoandroid.InterfazAPI_REST.IOnClickApiRest;
import com.example.angelruiz.cursoandroid.R;
import com.example.angelruiz.cursoandroid.RespuestaAPI_REST.ArrayRespuestaApiRest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentApiRest extends Fragment implements View.OnClickListener {
    private static final int SELECT_PIKTURE = 100;
    private ProgressBar pb;
    Bitmap uriPath;
    Uri path;
    View vista;
    Context context;
    FloatingActionButton fabImagenApi;
    EditText etNumeroFolio, etNombre, etProfesion;
    Button btRegistraApi;
    private Retrofit retrofit;
    ArrayList<ArrayWSMysqlApi> listaJson;
    IEndPointAPI_REST service;
    private AdapterApiRest adapterApiRest;
    private static final String TAG = "API_REST_LOG_E";
    private int offset;
    RecyclerView rvDatosApiRest;
    SwipeRefreshLayout srfRVAPI;

    public FragmentApiRest() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();
        listaJson = new ArrayList<>();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_fragment_api_rest, container, false);

        fabImagenApi = vista.findViewById(R.id.fabImagenApi);
        etNumeroFolio = vista.findViewById(R.id.etNumeroFolio);
        etNombre = vista.findViewById(R.id.etNombre);
        etProfesion = vista.findViewById(R.id.etProfesion);
        btRegistraApi = vista.findViewById(R.id.btRegistraApi);

        pb = vista.findViewById(R.id.pbDatosApi);
        srfRVAPI = vista.findViewById(R.id.srfRVAPI);
        rvDatosApiRest = vista.findViewById(R.id.rvDatosApiRest);

        return vista;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        pb.setVisibility(View.VISIBLE);
        btRegistraApi.setOnClickListener(this);

        rvDatosApiRest.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        //GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        //rvDatosApiRest.setLayoutManager(gridLayoutManager);

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder() //inicializamos nuestro obj retrofit
                .baseUrl("https://proyectosangelito.000webhostapp.com/webServiceMysql/") //url de la API, debe terminar con slash(/)rft2
                .addConverterFactory(GsonConverterFactory.create()).build(); //GsonConverterFactory, nos permite decerealizar los datos Json

        service = retrofit.create(IEndPointAPI_REST.class); //unimos nuestra interfaz mediante el obj retrofit

        offset = 0;

        new DatosApiRest().execute(); //mostramos los datos, con una instancia de la clase que extiende de AsynckTask ejecutamos dicha clase en segundo plano, para ejecutar un AsynckTask creamos un objeto de la clase que lo extiende y accedemos a su metodo execute(), puede recibir parametros

        srfRVAPI.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new DatosApiRest().execute(); //si refrescamos se ejecuta la instancia a la clase que trae los datos en segundo plano(AsynckTask)
            }
        });

        fabImagenApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagenGaleria();
            }
        });
    }

    @SuppressLint("IntentReset")
    private void cargarImagenGaleria() {
        Intent imagenGaleria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        imagenGaleria.setType("image/*");
        startActivityForResult(Intent.createChooser(imagenGaleria, "Seleccione"), SELECT_PIKTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == SELECT_PIKTURE) {
            path = data.getData();
            try {
                uriPath = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), path); //para mandar la imagen a server
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //parametros a guardar en bd(api rest) mediante ws en interfaz
    @Override
    public void onClick(View v) {

        int numberFolio = Integer.parseInt(etNumeroFolio.getText().toString());
        String name = etNombre.getText().toString();
        String profetion = etProfesion.getText().toString();

        if (v.getId() == R.id.btRegistraApi) {

            registrarUsuarioApi(numberFolio, name, profetion);
            etNumeroFolio.setText("");
            etNombre.setText("");
            etProfesion.setText("");
        }
    }

    //register user in bd Api Rest
    private void registrarUsuarioApi(int numberFolio, final String name, String profetion) {

        Call<ArrayRespuestaApiRest> registerUserApiRest = service.registroAPIRest(numberFolio, name, profetion); //mediante el obj de la interfaz accedemos a su metodo y le pasamos los parametros que pide
        registerUserApiRest.enqueue(new Callback<ArrayRespuestaApiRest>() { //con el objeto Call, llamamos al metodo equeue, para crear los metodos onResponse, onFilure, el primero gestiona la respuesta debuelta por el server, el segundo cacha si hay error de malformacion de JSON o wx
            @Override
            public void onResponse(@NonNull Call<ArrayRespuestaApiRest> call, @NonNull Response<ArrayRespuestaApiRest> response) {//gestiona respuesta de datos de server

                if (response.isSuccessful()) {
                    Toast.makeText(context, "Usuario guerdado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error al guardar usuario", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayRespuestaApiRest> call, @NonNull Throwable t) {//gestiona si ocurren fallos al traer datos del server
                Toast.makeText(context, "Error server : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("api rest full", t.getMessage());
                notificacionNvoUsuario(name);
            }
        });
    }

    //notification push to register new user
    private void notificacionNvoUsuario(String name) {

        Intent i = new Intent(context, WebServiceMysql.class);
        PendingIntent pendingIntent =  PendingIntent.getActivity(context,0, i, PendingIntent.FLAG_ONE_SHOT);
        Uri sonidoNotification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(context, "N")
                .setSmallIcon(R.drawable.ic_menu_camera)
                .setContentTitle("Nuevo usuario")
                .setContentText("Se registro, " + name)
                .setSound(sonidoNotification)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificacion.build());
    }

    //esta clase extiende de AsynckTask(hilo) ya que puede ser pesado traer los datos desde el servidor y lo hara en segundo plano
    @SuppressLint("StaticFieldLeak")
    public class DatosApiRest extends AsyncTask<Void, Integer, Void> {

        //lo que queremos que se ejecute antes del doInBackground
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setMax(10); //el maximo para llenar el progressBar
        }

        //lo que se ejecuta durante la operacion
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pb.setProgress(values[0]); //podemos colocar el progreso de un progressBar
        }

        //colocamos la logica en su metodo, doInBackground, lo que hara el AnsyckTask
        @Override
        protected Void doInBackground(Void... voids) {
            showRegisterApiRest();
            return null;
        }
    }

    //show data consumed of Api Rest
    private void showRegisterApiRest(){

        Call<ArrayRespuestaApiRest> callRegisterResponse = service.obtenerListadoJson(); //el metodo para traer datos del server no pide parametros

        callRegisterResponse.enqueue(new Callback<ArrayRespuestaApiRest>() {
            @Override
            public void onResponse(@NonNull Call<ArrayRespuestaApiRest> call, @NonNull Response<ArrayRespuestaApiRest> response) {//el metodo response recibe,ArrayRespuestaApiRest, que guarda los datos de la API
                if (response.isSuccessful()) { //verificamos que la respuesta del servidor sea = 200 a 300 satisfactoria
                    ArrayRespuestaApiRest respuestaApiRest = response.body(); //creamos un obj de ArrayRespuestaApiRest, para que guarde los datos del ws,Body() trae la data, los datos del arreglo json a consumir del ws ApRst

                    if (respuestaApiRest != null) {
                        listaJson = respuestaApiRest.getResults(); //con un obj de tipo ArrayWSMysqlApi guardamos los datos para pasarcelos al adapter del RV

                        if (listaJson != null) { //checamos si el array trae los datos de la API

                            adapterApiRest = new AdapterApiRest(context, listaJson);//si es asi los mostramos en el RV, ya que recibe un array desde eladapter
                            rvDatosApiRest.setAdapter(adapterApiRest);

                            pb.setVisibility(View.GONE); //ocultamos el progressBar cuando se muestren los datos en el RV,publishProgress(n);este metodo recibe un entero y mostrara el estado de progreso en en progressBar
                            srfRVAPI.setRefreshing(false);

                            //mostramos los valores desde el ws por consola
                            for (ArrayWSMysqlApi x : listaJson) {
                                Log.e(TAG, "noms: " + x.getNombre());
                            }

                            for (int i = 0; i < listaJson.size(); i++) {
                                ArrayWSMysqlApi api = listaJson.get(i);
                                Log.e(TAG, "nombres: " + api.getNombre());
                            }
                        } else {
                            Toast.makeText(context, "vacio", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(context, "sin respuesta", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onResponse: "+ response.errorBody());
                    }
                } else {
                    Log.e(TAG, "onResponse: "+ response.errorBody());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayRespuestaApiRest> call, @NonNull Throwable t) { //este metodo cacha si hay un error al conectar al servidor API
                Log.e(TAG, "onFailure: " + t.getMessage());
                Toast.makeText(context, ""+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteRegisterApiRest(){

      adapterApiRest.setOnClickListenerDelete(new IOnClickApiRest() {
         @Override
         public void onClickImageDelete(int position) {

            Call<ArrayRespuestaApiRest> deleteRegisterApiRest = service.eliminarRegApiRest(listaJson.get(position).getIdPersona());
            deleteRegisterApiRest.enqueue(new Callback<ArrayRespuestaApiRest>() {
               @Override
               public void onResponse(@NonNull Call<ArrayRespuestaApiRest> call, @NonNull Response<ArrayRespuestaApiRest> response) {
                    if (response.isSuccessful()){
                        Log.i("response", "succesfull");
                    }else {
                        Log.i("response", "no succesfull");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayRespuestaApiRest> call, @NonNull Throwable t) {
                        Log.i("filure", t.getMessage());
                    }
                });
            }
        });
    }
}



