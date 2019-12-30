package com.example.angelruiz.cursoandroid.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.angelruiz.cursoandroid.R;

public class FragmentUssingGit extends Fragment implements View.OnClickListener /*implements Response.Listener<JSONObject>, Response.ErrorListener */ {

    View vista;
    Context context;
    ProgressBar pbWebService;
    Button btSearchRed;
    TextView tvManualUser, tvShowRedFind;

    public FragmentUssingGit() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_ussing_gitl, container, false);
        pbWebService = vista.findViewById(R.id.pb_web_service);
        tvShowRedFind = vista.findViewById(R.id.tv_show_redfind);
        btSearchRed = vista.findViewById(R.id.bt_search_red);
        tvManualUser = vista.findViewById(R.id.tv_manual_user);

        btSearchRed.setOnClickListener(this);

        return vista;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvShowRedFind.setVisibility(View.GONE);
        tvShowRedFind.setText("Completado...");
        tvManualUser.setText(showManualUser());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_search_red:
                searchRed();
                break;
        }
    }

    public void searchRed() {
        Toast.makeText(context, "searching...", Toast.LENGTH_SHORT).show();
    }

    public String showManualUser(){

        return "Una página de portada.\n" +
                "Una página de título.\n" +
                "Una página de derechos de autor.\n" +
                "Un prefacio, que contiene detalles de los documentos relacionados y la información sobre cómo navegar por la guía del usuario.\n" +
                "Una sección de introducción, que incluye:\n" +
                "Una breve descripción del sistema y su finalidad.\n" +
                "Una sección de novedades desde la última versión.\n" +
                "Una sección de requisitos previos necesarios para usar el sistema, que incluye:\n" +
                "Conocimientos mínimos del usuario\n" +
                "Requisitos técnicos previos, incluyendo:\n" +
                "Capacidades técnicas mínimas del equipo\n" +
                "Software asociado necesario\n" +
                "Mecanismo para acceder al sistema\n" +
                "Una sección de instalación y configuración\n" +
                "Una guía sobre cómo utilizar al menos las principales funciones del sistema, es decir, sus funciones básicas.\n" +
                "Una sección de solución de problemas que detalla los posibles errores o problemas que pueden surgir, junto con la forma de solucionarlos.\n" +
                "Una sección de preguntas frecuentes, donde encontrar más ayuda, y datos de contacto.\n" +
                "Un Glosario y, para documentos más grandes, un Índice.\n" +
                "modalidad de pagina";
    }
}