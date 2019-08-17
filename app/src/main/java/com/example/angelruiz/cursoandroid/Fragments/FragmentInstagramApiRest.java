package com.example.angelruiz.cursoandroid.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angelruiz.cursoandroid.Adapters.AdapterInstagramApiRest;
import com.example.angelruiz.cursoandroid.Arrays.ArrayInstagramObjects;
import com.example.angelruiz.cursoandroid.DatosAPI_REST_Instagram.AdapterDeserializerInstagram;
import com.example.angelruiz.cursoandroid.InterfazAPI_REST.IEndPointsInstagramApiRest;
import com.example.angelruiz.cursoandroid.R;
import com.example.angelruiz.cursoandroid.RespuestaAPI_REST.ArrayResponseInstagram;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FragmentInstagramApiRest extends Fragment {
    View view;
    Button btPrueba;
    Context context;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FragmentDetailInstagramApiRest fragment;
    ArrayList<ArrayInstagramObjects> dataInstagram;
    AdapterInstagramApiRest adapterInstagramApiRest;
    RecyclerView rvShowInstagram;
    Retrofit retrofit;

    public FragmentInstagramApiRest() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) { //create the fmt and inicializa obj, necessary in fmt
        super.onCreate(savedInstanceState);
        context = getActivity();
        dataInstagram = new ArrayList<>();

      /*  retrofit = new Retrofit.Builder() -->rebisar
                .baseUrl(ConstantesApiRestInstagram.ROOT_URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/

        getDataApiRest();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { //create view of fmt and inicializa their views, necessary in fmt
    view = inflater.inflate(R.layout.fragment_instagram_api_rest, container, false);

    rvShowInstagram = view.findViewById(R.id.rvShowInstagram);
    rvShowInstagram.setLayoutManager(new GridLayoutManager(context, 2));
    adapterInstagramApiRest = new AdapterInstagramApiRest(context, dataInstagram);
    rvShowInstagram.setAdapter(adapterInstagramApiRest);
    btPrueba = view.findViewById(R.id.btPrueba);

        btPrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //we pass data to the fmt detail
                fragmentManager = getFragmentManager();
                if (fragmentManager != null){
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragment = new FragmentDetailInstagramApiRest();
                }

                String email = "angel@gmail.com";
                Bundle datosAEnviar = new Bundle();
                datosAEnviar.putString("email", String.valueOf(dataInstagram.get(2).getImageLikes()));
                fragment.setArguments(datosAEnviar);

                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.replace(R.id.contenedorMysqlFragments, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    return view;
    }

    public void getDataApiRest(){
        //IEndPointsInstagramApiRest iEndPointsInstagramApiRest = retrofit.create(IEndPointsInstagramApiRest.class);-->rebisar
        AdapterDeserializerInstagram adapterDeserializerInstagram = new AdapterDeserializerInstagram();
        Gson gsonDeserializerCustom = adapterDeserializerInstagram.buildGsonDeserializerMediaRecent();
        IEndPointsInstagramApiRest iEndPointsInstagramApiRest = adapterDeserializerInstagram.establishConnectionInstagramApiRest(gsonDeserializerCustom);
        Call<ArrayResponseInstagram> arrayResponseInstagramCall = iEndPointsInstagramApiRest.getRecentMedia();
        arrayResponseInstagramCall.enqueue(new Callback<ArrayResponseInstagram>() {
            @Override
            public void onResponse(@NonNull Call<ArrayResponseInstagram> call, @NonNull Response<ArrayResponseInstagram> response) {
                if (response.isSuccessful()){

                    ArrayResponseInstagram arrayResponseInstagram = response.body(); //body bring all the data
                    assert arrayResponseInstagram != null;
                    dataInstagram = arrayResponseInstagram.getData();

                    ArrayList<ArrayInstagramObjects> coments = arrayResponseInstagram.getData();
                    for (int i = 0; i < coments.size(); i++) {
                        ArrayInstagramObjects arrayInstagramApiRest = coments.get(i);
                        adapterInstagramApiRest.passData(coments);
                        Log.i("instagram", "response: Successful " + dataInstagram.get(i).getImageLikes());
                    }
                }else {
                        Log.i("instagram", "response: Failure");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayResponseInstagram> call, @NonNull Throwable t) {
                Log.i("Server error", "Error: " + t.getMessage());
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) { //este método recibe data de la Activity
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPause() { //save state of fmt, necessary in fmt
        super.onPause();
    }
}
//https://api.instagram.com/oauth/authorize/?client_id=e329a894184b4d45bacc45b0e20ee39e&redirect_uri=https://www.facebook.com/angeldavid.ruizcruz&response_type=code&scope=basic+comments+likes+public_content  //datos a traer de la api insta
   /* curl -F 'client_id=e329a894184b4d45bacc45b0e20ee39e' \n
            -F 'client_secret=1a236022510a4d8099ecf0ef0986c9f3' \n
            -F 'grant_type=authorization_code' \n
            -F 'https://www.facebook.com/angeldavid.ruizcruz' \n
            -F 'code=d839bffd15064d3590c7fc3bd942ed5e' \n
    https://api.instagram.com/oauth/access_token

    https://www.facebook.com/angeldavid.ruizcruz#access_token=17656472546.e329a89.7d53b35629fe416c817f2d77fcd4cc18
    https://api.instagram.com/v1/users/self/?access_token=17656472546.e329a89.7d53b35629fe416c817f2d77fcd4cc18
    https://api.instagram.com/v1/users/self/media/recent/?access_token=17656472546.e329a89.7d53b35629fe416c817f2d77fcd4cc18*/