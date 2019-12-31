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
        vista = inflater.inflate(R.layout.fragment_ussing_git, container, false);
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

    private void searchRed() {
        Toast.makeText(context, "searching...", Toast.LENGTH_SHORT).show();
    }

    private String showManualUser(){

        return  "11-Capacidades técnicas mínimas del equipo.\n" +
                "12-Software asociado necesario.\n" +
                "13-Mecanismo para acceder al sistema.\n" +
                "14-Una sección de instalación y configuración.\n" +
                "15-Una guía sobre cómo utilizar al menos las principales funciones del sistema, es decir, sus funciones básicas.\n" +
                "16-Una sección de solución de problemas que detalla los posibles errores o problemas que pueden surgir, junto con la forma de solucionarlos.\n" +
                "17-Una sección de preguntas frecuentes, donde encontrar más ayuda, y datos de contacto.\n" +
                "18-Un Glosario y, para documentos más grandes, un Índice.\n" +
                "19.modalidad de pagina.";
    }
}