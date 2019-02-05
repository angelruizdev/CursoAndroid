package com.example.angelruiz.cursoandroid.Fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.angelruiz.cursoandroid.Adapters.AdapterApiRest;
import com.example.angelruiz.cursoandroid.ArraysAPI_REST.ArrayWSMysqlApi;
import com.example.angelruiz.cursoandroid.InterfazAPI_REST.API_REST;
import com.example.angelruiz.cursoandroid.R;
import com.example.angelruiz.cursoandroid.RespuestaAPI_REST.RespuestaApiRest;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentApiRest extends Fragment implements View.OnClickListener {
    private static final int SELECT_PIKTURE = 100;
    Bitmap uriPath;
    Uri path;
    View vista;
    Context context;
    FloatingActionButton fabImagenApi;
    EditText etNumeroFolio, etNombre, etProfesion;
    Button btRegistraApi;
    private Retrofit retrofit;
    private static final String TAG = "API_REST";
    private int offset;
    RecyclerView rvDatosApiRest;
    SwipeRefreshLayout srfRVAPI;

    public FragmentApiRest() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_fragment_api_rest, container, false);
        context = getContext();
        fabImagenApi = vista.findViewById(R.id.fabImagenApi);
        etNumeroFolio = vista.findViewById(R.id.etNumeroFolio);
        etNombre = vista.findViewById(R.id.etNombre);
        etProfesion = vista.findViewById(R.id.etProfesion);
        btRegistraApi = vista.findViewById(R.id.btRegistraApi);

        srfRVAPI = vista.findViewById(R.id.srfRVAPI);
        btRegistraApi.setOnClickListener(this);
        rvDatosApiRest = vista.findViewById(R.id.rvDatosApiRest);
        rvDatosApiRest.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        //rvDatosApiRest.setLayoutManager(gridLayoutManager);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://proyectosangelito.000webhostapp.com/webServiceMysql/")//url de la API, debe terminar con slash(/)rft2
                .addConverterFactory(GsonConverterFactory.create()).build();//GsonConverterFactory, nos permite decerealizar los datos Json
        offset = 0;
        obtenerDatosApi();

        srfRVAPI.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                obtenerDatosApi();
            }
        });

        fabImagenApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagenGaleria();
            }
        });
        return vista;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btRegistraApi:
                registrarUsuarioApi();
                etNumeroFolio.setText("");
                etNombre.setText("");
                etProfesion.setText("");
                break;
        }
    }

    @SuppressLint("IntentReset")
    public void cargarImagenGaleria() {
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
                uriPath = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), path);//para mandar la imagen a server
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void registrarUsuarioApi() {
        API_REST service = retrofit.create(API_REST.class);
        String numeroFolio = etNumeroFolio.getText().toString();
        String nombre = etNombre.getText().toString();
        String profesion = etProfesion.getText().toString();

            Call<RespuestaApiRest> registrarUusario = service.registroAPIRest(numeroFolio, nombre, profesion);
            registrarUusario.enqueue(new Callback<RespuestaApiRest>() {
                @Override
                public void onResponse(@NonNull Call<RespuestaApiRest> call, @NonNull Response<RespuestaApiRest> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(context, "Se guardo", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Error al guardar usuario", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<RespuestaApiRest> call, @NonNull Throwable t) {
                    Toast.makeText(context, "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
    }

        private void obtenerDatosApi () {
            API_REST service = retrofit.create(API_REST.class);
            Call<RespuestaApiRest> llamarRespuesta = service.obtenerListadoJson();
            llamarRespuesta.enqueue(new Callback<RespuestaApiRest>() {
                @Override
                public void onResponse(@NonNull Call<RespuestaApiRest> call, @NonNull Response<RespuestaApiRest> response) {//el metodo response recibe,RespuestaApiRest, que guarda los datos de la API
                    if (response.isSuccessful()) {//verificamos que la respuesta del servidor sea = 200 a 300 satisfactoria
                        RespuestaApiRest respuestaApiRest = response.body();

                        if (respuestaApiRest != null) {
                            Toast.makeText(context, "Entro", Toast.LENGTH_SHORT).show();
                            ArrayList<ArrayWSMysqlApi> listaJson = respuestaApiRest.getResults();

                            if (listaJson != null) {//checamos si el array trae los datos de la API
                                final AdapterApiRest adapterApiRest = new AdapterApiRest(listaJson, context);//si es asi los mostramos en el RV, ya que recibe un array desde eladapter
                                rvDatosApiRest.setAdapter(adapterApiRest);
                                srfRVAPI.setRefreshing(false);
                            } else {
                                Toast.makeText(context, "vacio", Toast.LENGTH_SHORT).show();
                            }
                            //ArrayApiRest api = listaJson.get(i);
                            //Log.i(TAG, "Nombre Pokemon"+ api.getName());
                        } else {
                            Toast.makeText(context, "sin respuesta", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "error" + response.errorBody(), Toast.LENGTH_SHORT).show();
                        //Log.e(TAG, "onResponse: "+ response.errorBody());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<RespuestaApiRest> call, @NonNull Throwable t) {//este metodo cacha si hay un error al conectar al servidor API
                    Log.e(TAG, "onFailure: " + t.getMessage());
                    Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
      }
}

