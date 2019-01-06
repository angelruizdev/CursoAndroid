package com.example.angelruiz.cursoandroid.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.angelruiz.cursoandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRegistroWsMysql extends Fragment {
View vista;

    public FragmentRegistroWsMysql() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_registro_ws_mysql, container, false);

        return vista;
    }

}
