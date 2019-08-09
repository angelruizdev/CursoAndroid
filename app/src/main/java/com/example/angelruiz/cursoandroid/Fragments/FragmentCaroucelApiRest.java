package com.example.angelruiz.cursoandroid.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.angelruiz.cursoandroid.Adapters.AdapterCaroucelImgRest;
import com.example.angelruiz.cursoandroid.Arrays.ArrayCaroucelResponceRest;
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
    ImageView ivImgGlade;
    Button btCargarImg;
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_pendiente, container, false);
        context = getContext();
        imgCaroucel = new ArrayList<>();
        cmpCarouselImage = vista.findViewById(R.id.ivCaroucelCmp);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        rvImageCaroucel = vista.findViewById(R.id.rvImageCaroucel);
        rvImageCaroucel.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        adapterCaroucelImgRest = new AdapterCaroucelImgRest(context);
        rvImageCaroucel.setAdapter(adapterCaroucelImgRest);
        obtenerDatosApiRest();

        //cmpCarouselImage.carrucelAnimation(imagesCaroucel);
        cmpCarouselImage.setCurrentCaroucel(0);

        fabPrevius = vista.findViewById(R.id.fabPrevius);
        fabPrevius.setOnClickListener(this);
        fabNext = vista.findViewById(R.id.fabNext);
        fabNext.setOnClickListener(this);
        ivImgGlade = vista.findViewById(R.id.ivImgGlade);
        btCargarImg = vista.findViewById(R.id.btCargarImg);
        btCargarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://js2.pngtree.com/v3/images/home/paradrop.png";
                Glide.with(context).load(url).into(ivImgGlade);
            }
        });
        return vista;
    }

    private void obtenerDatosApiRest() {
        ICaroucelImageRest iCaroucelImageRest = retrofit.create(ICaroucelImageRest.class);
        Call<ArrayCaroucelResponceRest> caroucelResponseCall = iCaroucelImageRest.obtenerImagesRest();
        caroucelResponseCall.enqueue(new Callback<ArrayCaroucelResponceRest>() {
            @Override
            public void onResponse(Call<ArrayCaroucelResponceRest> call, Response<ArrayCaroucelResponceRest> response) {
                if(response.isSuccessful()){
                    ArrayCaroucelResponceRest arrayCaroucelResponceRest = response.body();
                    ArrayList<ArrayImgCaroucelRest> names = arrayCaroucelResponceRest.getResults();
                    for (int i = 0; i < names.size(); i++) {

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
            public void onFailure(Call<ArrayCaroucelResponceRest> call, Throwable t) {
                    Log.e(TAG, "onFailure:" + t.getMessage());
            }
        });
    }

    @Override
    public void onClick(View v) {
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
    public void onDestroy() {
        this.cmpCarouselImage.cancelAnimation();
        super.onDestroy();
    }
}
//https://api.instagram.com/oauth/authorize/?client_id=e329a894184b4d45bacc45b0e20ee39e&redirect_uri=https://www.facebook.com/angeldavid.ruizcruz&response_type=code&scope=basic+comments+likes+public_content
   /* curl -F 'client_id=e329a894184b4d45bacc45b0e20ee39e' \n
            -F 'client_secret=1a236022510a4d8099ecf0ef0986c9f3' \n
            -F 'grant_type=authorization_code' \n
            -F 'https://www.facebook.com/angeldavid.ruizcruz' \n
            -F 'code=d839bffd15064d3590c7fc3bd942ed5e' \n
    https://api.instagram.com/oauth/access_token

    https://www.facebook.com/angeldavid.ruizcruz#access_token=17656472546.e329a89.7d53b35629fe416c817f2d77fcd4cc18*/
    //https://api.instagram.com/v1/users/self/?access_token=17656472546.e329a89.7d53b35629fe416c817f2d77fcd4cc18
