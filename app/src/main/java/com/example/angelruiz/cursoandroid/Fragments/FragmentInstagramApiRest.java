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
            public void onClick(View v) {

                fragmentTransaction = getFragmentManager() != null ? getFragmentManager().beginTransaction() : null;
                assert fragmentTransaction != null;

                String email = "angel@gmail.com";
                Bundle datosAEnviar = new Bundle();
                datosAEnviar.putString("email", String.valueOf(dataInstagram.get(0).getImageLike()));
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
