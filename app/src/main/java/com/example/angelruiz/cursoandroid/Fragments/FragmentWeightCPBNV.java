package com.example.angelruiz.cursoandroid.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.angelruiz.cursoandroid.R;

public class FragmentWeightCPBNV extends Fragment {
    View view;
    private EditText etWeigthEnter;
    private TextView tvShowResults;
    private Button btSaveRegisterCP;

    public FragmentWeightCPBNV() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     view = inflater.inflate(R.layout.fragment_weight_cpbnv, container, false);

     etWeigthEnter = view.findViewById(R.id.etWeightEnter);
     tvShowResults = view.findViewById(R.id.tvShowResults);
     btSaveRegisterCP = view.findViewById(R.id.btSaveRegisterCP);

     return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btSaveRegisterCP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterWeight();
            }
        });
    }

    private void showRegisterWeight(){
        String peso = etWeigthEnter.getText().toString();
        tvShowResults.setText(peso);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
