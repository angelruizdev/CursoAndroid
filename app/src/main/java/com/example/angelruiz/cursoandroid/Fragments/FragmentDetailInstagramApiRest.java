package com.example.angelruiz.cursoandroid.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.angelruiz.cursoandroid.R;

public class FragmentDetailInstagramApiRest extends Fragment {
    View view;
    TextView tvPrueba;
    String email;

    public FragmentDetailInstagramApiRest() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) { //recibimos datos desde fmt instagram
        super.onCreate(savedInstanceState);
        Bundle obtData = getArguments();
        if (obtData != null){
            email = getArguments().getString("email");
            Log.i("arguments", "successful argument: " + email); //log.i information
        }else {
            Log.e("arguments", "without arguments");            //log.e error
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail_instagram_api_rest, container, false);
        tvPrueba = view.findViewById(R.id.tvPrueba);
        tvPrueba.setText(email);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
