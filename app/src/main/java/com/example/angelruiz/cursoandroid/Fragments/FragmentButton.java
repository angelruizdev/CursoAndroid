package com.example.angelruiz.cursoandroid.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.angelruiz.cursoandroid.R;
//podemos extender de Fragment o suport.v4 para versiones anteriores, la logica va en cada fragment
public class FragmentButton extends Fragment {
View vista;//esta vista espara retornar en el metodo onCreateView para poder instanciar nuestros controles
RadioButton radioA, radioB;
Button btnContar,btnElim;
    public FragmentButton() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    // asignamos a la vista el metodo inflate
    vista=inflater.inflate(R.layout.fragment_button, container, false);
    radioA=vista.findViewById(R.id.radioA);//pasamos la vista antes del mentodo find...
    radioB=vista.findViewById(R.id.radioB);
    btnElim=vista.findViewById(R.id.btnElim);
    btnContar=vista.findViewById(R.id.btnContar);
    btnContar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(radioA.isChecked()){
               btnElim.setEnabled(false);
               btnElim.setText(R.string.rbdesactivado);
            }else if (radioB.isChecked()){
               btnElim.setEnabled(true);
                btnElim.setText(R.string.rbactivado);
            }else {
                Toast.makeText(getContext(), "Toca una opsion", Toast.LENGTH_SHORT).show();
            }
        }
    });
    return vista;//retornamos la vista
    }
    /*implementamos en cada fragment la interface si trabajamos con api < a23
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }*/
}
