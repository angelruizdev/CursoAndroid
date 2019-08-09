package com.example.angelruiz.cursoandroid.Activitys;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.angelruiz.cursoandroid.Arrays.ArrayProductosBD;
import com.example.angelruiz.cursoandroid.Fragments.FragmentRecyDetalleBD;
import com.example.angelruiz.cursoandroid.Interfaces.InterfComunicaFgmtDetBDyProBD;
import com.example.angelruiz.cursoandroid.R;

public class ActivityFragmentProdMaDeBD extends AppCompatActivity implements InterfComunicaFgmtDetBDyProBD {//implementamos la interface para comunicar ambos frgmnts
//FragmentRecyDetalleBD fragmentRecyDetalleBD; para mandar datod de qui un frgmnt
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_prod_ma_de_bd);
    }

    @Override
    public void enviarDatos(ArrayProductosBD arrayProductosBD) { //implementamos el metodo de la interface, el cual recibe un parametro de tipo ArrayProductosBD, para poder pasar todos los datos del pojo ArrayProductosBD
        FragmentRecyDetalleBD fragmentRecyDetalleBD = (FragmentRecyDetalleBD)getSupportFragmentManager().findFragmentById(R.id.fmtRecyDetallBD); //instanciamos y casteamos el fragmnt que recibira los datos
        if (fragmentRecyDetalleBD != null){//comprovamos que exista el objeto(instancia)
            fragmentRecyDetalleBD.recibirDatos(arrayProductosBD);//si es asi accedemos a su metodo y le pasamos como parametro el objeto del pojo ArrayProductosBD, para pasarlos a FragmentRecyDetalleBD
        }

        /*fragmentRecyDetalleBD = new FragmentRecyDetalleBD();//esto es utilo para fragments dinamicos, pasar datos de una activity a un fragment
        Bundle bundleEnviaDatos = new Bundle();
        bundleEnviaDatos.putSerializable("objeto", arrayProductosBD);
        fragmentRecyDetalleBD.setArguments(bundleEnviaDatos);

        getSupportFragmentManager().beginTransaction().replace(R.id.contfrgmnt, fragmentRecyDetalleBD).addToBackStack(null).commit();*///permite reemplazar al frgmnt de forma mas optima
    }
}
