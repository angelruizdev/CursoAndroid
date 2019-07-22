package com.example.angelruiz.cursoandroid.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.angelruiz.cursoandroid.R;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FragmentCalculator extends Fragment implements View.OnClickListener{
    View view;
    Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt0, btAC, btIgual, btPunto, btSuma, btResta, btMulti, btDivision;
    private EditText etEntrada;
    private TextView tvResultado;
    Boolean punto = false;

    ArrayList<Double> sumas;//temporales , sin funcion
    ArrayList<Double> restas;

    public FragmentCalculator() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calculator, container, false);
         bt1 = view.findViewById(R.id.bt1);
         bt2 = view.findViewById(R.id.bt2);
         bt3 = view.findViewById(R.id.bt3);
         bt4 = view.findViewById(R.id.bt4);
         bt5 = view.findViewById(R.id.bt5);
         bt6 = view.findViewById(R.id.bt6);
         bt7 = view.findViewById(R.id.bt7);
         bt8 = view.findViewById(R.id.bt8);
         bt9 = view.findViewById(R.id.bt9);
         bt0 = view.findViewById(R.id.bt0);
         btAC = view.findViewById(R.id.btAC);
         btIgual = view.findViewById(R.id.btIgual);
         btPunto = view.findViewById(R.id.btPunto);
         btSuma = view.findViewById(R.id.btSuma);
         btResta = view.findViewById(R.id.btResta);
         btMulti = view.findViewById(R.id.btMulti);
         btDivision = view.findViewById(R.id.btDivision);

         etEntrada = view.findViewById(R.id.etEntrada);
         etEntrada.setInputType(InputType.TYPE_NULL);
         tvResultado = view.findViewById(R.id.tvResultado);

         bt1.setOnClickListener(this);
         bt2.setOnClickListener(this);
         bt3.setOnClickListener(this);
         bt4.setOnClickListener(this);
         bt5.setOnClickListener(this);
         bt6.setOnClickListener(this);
         bt8.setOnClickListener(this);
         bt9.setOnClickListener(this);
         bt0.setOnClickListener(this);
         btAC.setOnClickListener(this);
         btPunto.setOnClickListener(this);
         btSuma.setOnClickListener(this);
         btResta.setOnClickListener(this);
         btMulti.setOnClickListener(this);
         btDivision.setOnClickListener(this);
         btIgual.setOnClickListener(this);

         sumas = new ArrayList<Double>();//temporales, sin funcion
         restas = new ArrayList<Double>();
        return view;
    }

    @Override
    public void onClick(View v) {
        String entrada = etEntrada.getText().toString();
        Pattern pat = Pattern.compile(".*[0-9]$");
        Matcher mat = pat.matcher(entrada);

        switch (v.getId()){
            case R.id.bt1:
                etEntrada.setText(entrada + "1");
                break;
            case R.id.bt2:
                etEntrada.setText(entrada + "2");
                break;
            case R.id.bt3:
                etEntrada.setText(entrada + "3");
                break;
            case R.id.bt4:
                etEntrada.setText(entrada + "4");
                break;
            case R.id.bt5:
                etEntrada.setText(entrada + "5");
                break;
            case R.id.bt6:
                etEntrada.setText(entrada + "6");
                break;
            case R.id.bt7:
                etEntrada.setText(entrada + "7");
                break;
            case R.id.bt8:
                etEntrada.setText(entrada + "8");
                break;
            case R.id.bt9:
                etEntrada.setText(entrada + "9");
                break;
            case R.id.bt0:
                etEntrada.setText(entrada + "0");
                break;
            case R.id.btAC:
                sumas.clear();//para limpiar un arrayL
                restas.clear();
                tvResultado.setText("");
                if (!entrada.equals("")){
                    String bor = entrada.substring(0, entrada.length()-1);
                    etEntrada.setText(bor);
                }
                punto = false;
                break;
            case R.id.btPunto:
                if (!punto){
                    etEntrada.setText(entrada + ".");
                    punto = true;
                }else {
                    return;
                }
                break;

            case R.id.btSuma:
                if (!entrada.isEmpty()){
                    if (mat.matches()) {
                        etEntrada.setText(entrada + "+");
                        punto = false;
                    }else if (entrada.matches("^[\\d].*") || entrada.length() > 1){
                        etEntrada.setText(entrada.substring(0, entrada.length()-1) + "+");
                    }
                }
                break;
            case R.id.btResta:
                if (entrada.isEmpty()){
                    etEntrada.setText(entrada + "-");
                    punto = false;
                }
                else if (mat.matches()) {
                    etEntrada.setText(entrada + "-");
                    punto = false;
                }else if (entrada.matches("^[\\d].*") || entrada.length() > 1){
                    etEntrada.setText(entrada.substring(0, entrada.length()-1) + "-");
                }

                break;
            case R.id.btMulti:
                if (!entrada.isEmpty()){
                    if (mat.matches()) {
                        etEntrada.setText(entrada + "x");
                        punto = false;
                    }else if (entrada.matches("^[\\d].*") || entrada.length() > 1){
                        etEntrada.setText(entrada.substring(0, entrada.length()-1) + "x");
                    }
                }
                break;
            case R.id.btDivision:
                if (!entrada.isEmpty()){
                    if (mat.matches()) {
                        etEntrada.setText(entrada + "/");
                        punto = false;
                    }else if (entrada.matches("^[\\d].*") || entrada.length() > 1){
                        etEntrada.setText(entrada.substring(0, entrada.length()-1) + "/");
                    }
                }
                break;
            case R.id.btIgual:
                obtSumas(entrada);
                break;
        }
    }
    public void obtSumas(String cadena){
        String[] termino = cadena.split("\\+");
        double[] sumando = new double[termino.length];
        for (int i = 0; i < sumando.length; i++) {
            try {
                double s = Double.parseDouble(termino[i]);
                sumando[i]=s;
            }catch (Exception e){
                sumando[i]=obtRestas(termino[i]);
            }
        }
        double sum = 0;
        for (double s: sumando) {
            sum += s;
        }
        tvResultado.setText(String.valueOf(sum));
    }

    public double obtRestas(String cadena){
        String[] termino = cadena.split("-");
        double[] opr = new double[termino.length];
        for (int i = 0; i < opr.length; i++) {
            try {
                double s = Double.parseDouble(termino[i]);
                opr[i]=s;
            }catch (Exception e){
                opr[i]=obtMultiplicacion(termino[i]);
            }
        }
        double res = 0;
        boolean restar = false;
        for (double s: opr) {
            if (restar){
                res -= s;
            }else {
                res += s;
                restar = true;
            }
        }
        tvResultado.setText(String.valueOf(res));
        return res;
    }
    public double obtMultiplicacion(String cadena){
        String[] termino = cadena.split("x");
        double[] factores = new double[termino.length];
        for (int i = 0; i < factores.length; i++) {
            try {
                double s = Double.parseDouble(termino[i]);
                factores[i]=s;
            }catch (Exception e){
                factores[i]=obtDivision(termino[i]);
            }
        }
        double producto = 1;
        for (double s: factores) {
            producto *= s;
        }
        tvResultado.setText(String.valueOf(producto));
        return producto;
    }

    public double obtDivision(String cadena){
        String[] termino = cadena.split("/");
        double[] opr = new double[termino.length];
        for (int i = 0; i < opr.length; i++) {
            try {
                double s = Double.parseDouble(termino[i]);
                opr[i]=s;
            }catch (Exception e){
                //Toast.makeText(this, "Error!"+e, Toast.LENGTH_SHORT).show();
                //opr[i]=obtRestas(termino[i]);
            }
        }
        double res = 0;
        boolean div = false;
        for (double s: opr) {
            //if (s > 0) {
            if (div) {
                res /= s;
            } else {
                res = s;
                div = true;
            }
            //}else {
            //Toast.makeText(this, "Número invalido!", Toast.LENGTH_SHORT).show();
            //
            // etEntrada.setText(cadena.substring(0, cadena.length()-1));
            //}
        }
        tvResultado.setText(String.valueOf(res));
        return res;
    }
}
