package com.example.angelruiz.cursoandroid.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angelruiz.cursoandroid.Adapters.AdapterInstagramApiRest;
import com.example.angelruiz.cursoandroid.Arrays.ArrayInstagramApiRest;
import com.example.angelruiz.cursoandroid.R;

import java.util.ArrayList;

public class FragmentInstagramApiRest extends Fragment {
View view;
Button btPrueba;
Context context;
FragmentTransaction fragmentTransaction;
Fragment fragment;
ArrayList<ArrayInstagramApiRest> dataInstagram;
AdapterInstagramApiRest adapterInstagramApiRest;
RecyclerView rvShowInstagram;

    public FragmentInstagramApiRest() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_instagram_api_rest, container, false);
    context = getActivity();
    dataInstagram = new ArrayList<>();
    dataInstagram.add(new ArrayInstagramApiRest(R.drawable.email, 1));
    dataInstagram.add(new ArrayInstagramApiRest(R.drawable.email, 1));
    dataInstagram.add(new ArrayInstagramApiRest(R.drawable.email, 1));
    dataInstagram.add(new ArrayInstagramApiRest(R.drawable.email, 1));

    rvShowInstagram = view.findViewById(R.id.rvShowInstagram);
    rvShowInstagram.setLayoutManager(new GridLayoutManager(context, 2));
    adapterInstagramApiRest = new AdapterInstagramApiRest(context, dataInstagram);
    rvShowInstagram.setAdapter(adapterInstagramApiRest);
        btPrueba = view.findViewById(R.id.btPrueba);
        btPrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //pasamos datos al fmt detail

                fragmentTransaction = getFragmentManager() != null ? getFragmentManager().beginTransaction() : null;
                assert fragmentTransaction != null;

                String email = "angel@gmail.com";
                Bundle datosAEnviar = new Bundle();
                datosAEnviar.putString("email", String.valueOf(dataInstagram.get(0).getImageLikes()));
                fragment = new FragmentDetailInstagramApiRest();
                fragment.setArguments(datosAEnviar);

                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.replace(R.id.contenedorMysqlFragments, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    return view;
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