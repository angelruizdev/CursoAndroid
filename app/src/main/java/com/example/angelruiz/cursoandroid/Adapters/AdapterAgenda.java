package com.example.angelruiz.cursoandroid.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.angelruiz.cursoandroid.Arrays.ArrayAgenda;
import com.example.angelruiz.cursoandroid.R;

import java.io.Serializable;
import java.util.ArrayList;
//como vemos también extiende de BaseAdapter e implementamos sus métodos, igual podemos usar la interface Serializable por si queremos pasar datos a otra Activity
public class AdapterAgenda extends BaseAdapter implements Serializable{

private Context context;
private ArrayList<ArrayAgenda> agenda;

    public AdapterAgenda(@NonNull Context context, ArrayList<ArrayAgenda> agenda) {
        this.context=context;
        this.agenda=agenda;
    }

    @Override
    public int getCount() {//muestra el tamaño del arrayL
        return agenda.size();
    }

    @Override
    public Object getItem(int position) {//obtiene la pisision del item seleccionado
        return agenda.get(position);
    }

    @Override
    public long getItemId(int i) {//muestra el id del item seleccionado
        return 0;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

     if(convertView == null){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.vista_agenda,null);
     }

        ImageView ivImgContactoAgenda = convertView.findViewById(R.id.ivImgContactoAgenda);
        TextView tvNomContactoAgenda = convertView.findViewById(R.id.tvNomContactoAgenda);
        TextView tvTelefonoContactoAgenda = convertView.findViewById(R.id.tvTelefonoContactoAgenda);

        ivImgContactoAgenda.setImageResource(agenda.get(position).getImgPersona());
        tvNomContactoAgenda.setText(agenda.get(position).getNombrePersona());
        tvTelefonoContactoAgenda.setText(agenda.get(position).getTelefonoPersona());

     return convertView;
    }
}
