package com.example.angelruiz.cursoandroid.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.example.angelruiz.cursoandroid.InterfazAPI_REST.IOnClickRecyclerInstagram;
import com.example.angelruiz.cursoandroid.R;
import com.example.angelruiz.cursoandroid.RespuestaAPI_REST.ArrayResponseInstagram;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentInstagramApiRest extends Fragment {
    View view;
    private Context context;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    FragmentDetailInstagramApiRest fragmentDetailInstagramApiRest;
    ArrayList<ArrayInstagramObjects> dataInstagram;
    AdapterInstagramApiRest adapterInstagramApiRest;
    RecyclerView rvShowInstagram;
    String nameUsuarioLocal;

    public FragmentInstagramApiRest() {
        // Required empty public constructor
    }

    //create the fmt and inicializa objs, necessary in fmt
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        dataInstagram = new ArrayList<>();
        getDataApiRest();
    }

    //create view of fmt and inicializa their views, necessary in fmt
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_instagram_api_rest, container, false);
    nameUsuarioLocal = "Ángel Ruiz";

    rvShowInstagram = view.findViewById(R.id.rvShowInstagram);
    rvShowInstagram.setLayoutManager(new GridLayoutManager(context, 2)); //rv type grid whit 2 columns
    adapterInstagramApiRest = new AdapterInstagramApiRest(context, dataInstagram); //we initialize the adapter
        rvShowInstagram.setAdapter(adapterInstagramApiRest);

        adapterInstagramApiRest.setOnClickLiatenerItem(new IOnClickRecyclerInstagram() { //we acces the method of the adapter with the adapter
            @Override                                                                    //para tener onClickListener en el item del rv al pasarle la interface nos traera su metodo y la posision del item traida desde el adapter
            public void onClickItemRvIntagram(int position) { //we use the position for pass data from here to FragmentDetailInstagramApiRest
                fragmentManager = getFragmentManager(); //for replace this fmt with FragmentDetailInstagramApiRest
                if (fragmentManager != null){
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentDetailInstagramApiRest = new FragmentDetailInstagramApiRest(); //we instantiate the fmt of replacement
                }

                //with object bundle and interface Parceable we pass data to FragmentDetailInstagramApiRest
                Bundle sendToData = new Bundle();
                sendToData.putParcelable("dataUserInstagram", dataInstagram.get(position)); //we pass whole array with the position obtain form adapter
                sendToData.putString("nameUsuarioLocal", nameUsuarioLocal); //we pass variable local without parceable
                fragmentDetailInstagramApiRest.setArguments(sendToData); //we pass the data the fmt destination

                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.replace(R.id.contenedorMysqlFragments, fragmentDetailInstagramApiRest);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit(); //we replace the fmt passing their data
            }
        });

    return view;
    }

    //this method manages the rensponse of the api and the deserialization
    public void getDataApiRest(){
        AdapterDeserializerInstagram adapterDeserializerInstagram = new AdapterDeserializerInstagram(); //we create a object of AdapterDeserializerInstagram

        Gson gsonDeserializerCustom = adapterDeserializerInstagram.buildGsonDeserializerMediaRecent(); //we save in object Gson the method what build gson deserializer media
        IEndPointsInstagramApiRest iEndPointsInstagramApiRest = adapterDeserializerInstagram.establishConnectionInstagramApiRest(gsonDeserializerCustom); //we pass as parameter this object Gson and save in a object of IEndPointsInstagramApiRest
        Call<ArrayResponseInstagram> arrayResponseInstagramCall = iEndPointsInstagramApiRest.getRecentMedia(); //we access the method of the interface, this bring the response

        arrayResponseInstagramCall.enqueue(new Callback<ArrayResponseInstagram>() { //the method enqueue manages the response of the api instagram
            @Override
            public void onResponse(@NonNull Call<ArrayResponseInstagram> call, @NonNull Response<ArrayResponseInstagram> response) {
                if (response.isSuccessful()){

                    ArrayResponseInstagram arrayResponseInstagram = response.body(); //body bring all the data
                    assert arrayResponseInstagram != null;
                    dataInstagram = arrayResponseInstagram.getData(); //we save the data of the rsponse in array dataInstagram

                    for (int i = 0; i < dataInstagram.size(); i++) {
                        adapterInstagramApiRest.passData(dataInstagram); //we pass the data to the method passData of the adapterInstagramApiRest
                        Log.i("instagram", "response: Successful " + dataInstagram.get(i).getImageLikes()); //we show the likes by Log type info
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) { //this méthod get data of the Activity, if we pass data to his fmt
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPause() { //save state of fmt, necessary in fmt
        super.onPause();
    }

}
  /*data obtain of instagram, code, acces token, data to consume
  https://api.instagram.com/oauth/authorize/?client_id=e329a894184b4d45bacc45b0e20ee39e&redirect_uri=https://www.facebook.com/angeldavid.ruizcruz&response_type=code&scope=basic+comments+likes+public_content  //datos a traer de la api insta
    curl -F 'client_id=e329a894184b4d45bacc45b0e20ee39e' \n
            -F 'client_secret=1a236022510a4d8099ecf0ef0986c9f3' \n
            -F 'grant_type=authorization_code' \n
            -F 'https://www.facebook.com/angeldavid.ruizcruz' \n
            -F 'code=d839bffd15064d3590c7fc3bd942ed5e' \n
    https://api.instagram.com/oauth/access_token

    https://www.facebook.com/angeldavid.ruizcruz#access_token=17656472546.e329a89.7d53b35629fe416c817f2d77fcd4cc18
    https://api.instagram.com/v1/users/self/?access_token=17656472546.e329a89.7d53b35629fe416c817f2d77fcd4cc18
    https://api.instagram.com/v1/users/self/media/recent/?access_token=17656472546.e329a89.7d53b35629fe416c817f2d77fcd4cc18*/