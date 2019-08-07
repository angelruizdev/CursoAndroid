package com.example.angelruiz.cursoandroid.Activitys;

import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.angelruiz.cursoandroid.Fragments.FragmentButton;
import com.example.angelruiz.cursoandroid.Fragments.FragmentCheck;
import com.example.angelruiz.cursoandroid.R;

//como esta activity contendra los dos fragments, implementamos cada uno,con el metodo OnFragmentInteractionListener, si usamos la api < a 23
public class ActivityFragmet extends AppCompatActivity{// implements FragmentButton.OnFragmentInteractionListener, FragmentCheck.OnFragmentInteractionListener{
FragmentButton fragmentButton;
FragmentCheck fragmentCheck;
Button btnFmtButton,btnFmtCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmet);
        btnFmtButton=findViewById(R.id.btnFmtButton);
        btnFmtCheck=findViewById(R.id.btnFmtCheck);
        fragmentButton=new FragmentButton();//creamos un objeto del fragment de su clase fragmentButton, para llamarlo y mostrarlo
        fragmentCheck=new FragmentCheck();
        //con estos metodos damos vida y agregamos nuestro fragment dentro de la activity, commit para aplicar
        getSupportFragmentManager().beginTransaction().add(R.id.contentFmt,fragmentButton).commit();
    }
    public void opsion(View v){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();//creamos objeto tranaction para hacer tranzacciones(cambiar fragments, add, replace etc...)
        switch (v.getId()){
            case R.id.btnFmtButton:
                transaction.replace(R.id.contentFmt,fragmentButton);//mediante el objeto transaction y el metodo replace(), cambiamos un fragment por otro
                break;
            case R.id.btnFmtCheck:
                transaction.replace(R.id.contentFmt,fragmentCheck);
                break;
        }
        transaction.commit();
    }
    /*recordemos que un fragment vive en un Activity:un Fragment va a tener el msimo ciclo de vida que la activity donde este (si la activity se destruye el fragment tambien)
    clase FragmentTransaction:permite realizar todas las transacciones del fragment
    Clase FragmentManager:permite llevar a cabo las operaciones de la clase Fragment*/
/*//se nos pide implementar este metodo, si trabajamos con api < a 23
    @Override
    public void onFragmentInteraction(Uri uri) {

    }*/
}
