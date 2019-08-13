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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angelruiz.cursoandroid.Adapters.AdapterCaroucelImgRest;
import com.example.angelruiz.cursoandroid.RespuestaAPI_REST.ArrayCaroucelResponceRest;
import com.example.angelruiz.cursoandroid.Arrays.ArrayImgCaroucelRest;
import com.example.angelruiz.cursoandroid.Components.CmpCarouselImage;
import com.example.angelruiz.cursoandroid.InterfazAPI_REST.ICaroucelImageRest;
import com.example.angelruiz.cursoandroid.R;
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
    AdapterCaroucelImgRest adapterCaroucelImgRest;
    RecyclerView rvImageCaroucel;
    private static final String TAG = "SALIDA";
    FloatingActionButton fabPrevius, fabNext;

    public FragmentCaroucelApiRest() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) { //crea el fmt e inicializa todo menos vistas
        super.onCreate(savedInstanceState);
        context = getContext();
        imgCaroucel = new ArrayList<>();

        retrofit = new Retrofit.Builder() //instanceamos obj retrofit
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtenerDatosApiRest();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { //crea vista de fmt e inicializa vistas
        vista = inflater.inflate(R.layout.fragment_caroucel_api_rest, container, false);
        cmpCarouselImage = vista.findViewById(R.id.ivCaroucelCmp);

        rvImageCaroucel = vista.findViewById(R.id.rvImageCaroucel);
        rvImageCaroucel.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        adapterCaroucelImgRest = new AdapterCaroucelImgRest(context);
        rvImageCaroucel.setAdapter(adapterCaroucelImgRest);
        //cmpCarouselImage.carrucelAnimation(imagesCaroucel);
        cmpCarouselImage.setCurrentCaroucel(0);

        fabPrevius = vista.findViewById(R.id.fabPrevius);
        fabPrevius.setOnClickListener(this);
        fabNext = vista.findViewById(R.id.fabNext);
        fabNext.setOnClickListener(this);

        return vista;
    }

    private void obtenerDatosApiRest() { //obtenemos los datos de la api con retrofit
        ICaroucelImageRest iCaroucelImageRest = retrofit.create(ICaroucelImageRest.class);
        Call<ArrayCaroucelResponceRest> caroucelResponseCall = iCaroucelImageRest.obtenerImagesRest();
        caroucelResponseCall.enqueue(new Callback<ArrayCaroucelResponceRest>() {
            @Override
            public void onResponse(@NonNull Call<ArrayCaroucelResponceRest> call, @NonNull Response<ArrayCaroucelResponceRest> response) {
                if(response.isSuccessful()){
                    ArrayCaroucelResponceRest arrayCaroucelResponceRest = response.body();
                    assert arrayCaroucelResponceRest != null;
                    ArrayList<ArrayImgCaroucelRest> names = arrayCaroucelResponceRest.getResults();
                    for (int i = 0; i < names.size(); i++) { //show names by console

                       ArrayImgCaroucelRest arrayImgCaroucelRest = names.get(i);
                        Log.i(TAG, "NAMES:" + arrayImgCaroucelRest.getName());
                         adapterCaroucelImgRest.listImagesCaroucel(names);
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

    @Override
    public void onClick(View v) { //controls previous and next for caroucel image
       switch (v.getId()){
         case R.id.fabPrevius:
             //cmpCarouselImage.touchRight(imagesCaroucel);
         break;
         case R.id.fabNext:
             //cmpCarouselImage.touchLeft(imagesCaroucel);
             break;
       }
    }

    @Override
    public void onPause() { //if exit of fmt its stop the animacion
        this.cmpCarouselImage.cancelAnimation();
        super.onPause();
    }

    @Override
    public void onDestroy() { //if exit of app its stop the animacion
        this.cmpCarouselImage.cancelAnimation();
        super.onDestroy();
    }
}

