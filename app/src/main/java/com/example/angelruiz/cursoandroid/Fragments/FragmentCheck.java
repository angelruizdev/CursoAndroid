package com.example.angelruiz.cursoandroid.Fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.angelruiz.cursoandroid.R;

public class FragmentCheck extends Fragment {
View vista;
CheckBox cbxA,cbxB,cbxC;
Button btnSelec;
    public FragmentCheck() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_check, container, false);
        cbxA=vista.findViewById(R.id.cbxA);
        cbxB=vista.findViewById(R.id.cbxB);
        cbxC=vista.findViewById(R.id.cbxC);
        btnSelec=vista.findViewById(R.id.btnSelec);
        btnSelec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             String mng="slecciono: ";
                if(cbxA.isChecked()){
                  mng+="A";
                }else if(cbxB.isChecked()) {
                  mng+="B";
                }else if (cbxC.isChecked()){
                  mng+="C";
                }
                Toast.makeText(getContext(), ""+mng, Toast.LENGTH_SHORT).show();
            }
        });
        return vista;
    }
    /*para api < a 23
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }*/
}
