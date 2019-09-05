package com.example.angelruiz.cursoandroid.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.angelruiz.cursoandroid.Arrays.ArrayImgCaroucelRest;
import com.example.angelruiz.cursoandroid.Components.CmpCarouselImage;
import com.example.angelruiz.cursoandroid.InterfazAPI_REST.ICaroucelImageRest;
import com.example.angelruiz.cursoandroid.R;
import com.example.angelruiz.cursoandroid.RespuestaAPI_REST.ArrayCaroucelResponceRest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentCaroucelApiRest extends Fragment implements View.OnClickListener{
    View vista;
    Context context;
    ArrayList<ArrayImgCaroucelRest> imgCaroucel;
    CmpCarouselImage cmpCarouselImage;
    Retrofit retrofit;
    private static final String TAG = "SALIDA";
    FloatingActionButton fabPreviusImage, fabNextImage;
    private int offset;

    public FragmentCaroucelApiRest() {
        // Required empty public constructor
    }

    //crea el fmt e inicializa todo menos vistas
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        imgCaroucel = new ArrayList<>();
        offset = 0;

        //instanceamos obj retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtenerDatosApiRest(offset);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { //crea vista de fmt e inicializa vistas
        vista = inflater.inflate(R.layout.fragment_caroucel_api_rest, container, false);

        cmpCarouselImage = vista.findViewById(R.id.ivCaroucelCmp);
        fabPreviusImage = vista.findViewById(R.id.fabPreviusImage);
        fabNextImage = vista.findViewById(R.id.fabNextImage);

        return vista;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cmpCarouselImage.setCurrentCaroucel(0);

        fabPreviusImage.setOnClickListener(this);
        fabNextImage.setOnClickListener(this);
    }

    //obtenemos los datos de la api con retrofit
    private void obtenerDatosApiRest(int offset) {

        ICaroucelImageRest iCaroucelImageRest = retrofit.create(ICaroucelImageRest.class);
        Call<ArrayCaroucelResponceRest> caroucelResponseCall = iCaroucelImageRest.obtenerImagesRest(5, offset);
        caroucelResponseCall.enqueue(new Callback<ArrayCaroucelResponceRest>() {
            @Override
            public void onResponse(@NonNull Call<ArrayCaroucelResponceRest> call, @NonNull Response<ArrayCaroucelResponceRest> response) {

                if(response.isSuccessful()){
                    ArrayCaroucelResponceRest arrayCaroucelResponceRest = response.body();
                    if(arrayCaroucelResponceRest != null) {

                        //we save the values of the response in array pojo
                        ArrayList<ArrayImgCaroucelRest> names = arrayCaroucelResponceRest.getResults();
                        imgCaroucel.addAll(names);
                        cmpCarouselImage.carrucelAnimation(names);

                        //testing response, show names by console
                        for (int i = 0; i < names.size(); i++) {

                            ArrayImgCaroucelRest arrayImgCaroucelRest = names.get(i);
                            Log.i(TAG, "NAMES: " + arrayImgCaroucelRest.getName());
                        }
                    }
                }else {
                    Log.e(TAG, "onResponse:" + response.errorBody());
                    Toast.makeText(context, "Server error:", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayCaroucelResponceRest> call, @NonNull Throwable t) {
                    Log.e(TAG, "onFailure:" + t.getMessage());
            }
        });
    }

    //controls previous and next for caroucel image
    @Override
    public void onClick(View v) {
       switch (v.getId()){
         case R.id.fabPreviusImage:
             cmpCarouselImage.touchRight(imgCaroucel);
         break;
         case R.id.fabNextImage:
             cmpCarouselImage.touchLeft(imgCaroucel);

         break;
       }
    }

    //if exit of fmt its stop the animacion
    @Override
    public void onPause() {
        this.cmpCarouselImage.cancelAnimation();
        super.onPause();
    }

    //if exit of app its stop the animacion
    @Override
    public void onDestroy() {
        this.cmpCarouselImage.cancelAnimation();
        super.onDestroy();
    }
}

