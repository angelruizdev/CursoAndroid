package com.example.angelruiz.cursoandroid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.angelruiz.cursoandroid.Arrays.ArrayImgContacto;
import com.example.angelruiz.cursoandroid.R;

import java.util.ArrayList;
//extendemos de la clase BaseAdapter e implementamos sus metodos, estos nos ayudaran a controlar la posision de cada item
public class AdapterImagenContacto extends BaseAdapter {
//creamos la variable contexy y un array de tipo array de clase
    private Context context;
    private ArrayList<ArrayImgContacto> lstContactos;
//inicializamos la variable y el array con el constructor
    public AdapterImagenContacto(Context context, ArrayList<ArrayImgContacto> lstConstactos){
      this.context=context;
      this.lstContactos=lstConstactos;
    }
    @Override
    public int getCount() {
        return lstContactos.size();
    }

    @Override
    public Object getItem(int position) {
        return lstContactos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lstContactos.get(position).getId();
    }
//mediante este metodo inflamos la vista y mostramos los datos en ella
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view==null){
            LayoutInflater layoutInflater= LayoutInflater.from(context);
            view=layoutInflater.inflate(R.layout.vista_adp_imgcontact,null);
        }
        ImageView ivImgContacto = (ImageView)view.findViewById(R.id.ivImgContacto);
        TextView tvNomComtacto = (TextView)view.findViewById(R.id.tvNomContacto);
//mostramos los datos en el textview e imageview , para imageview usamos el metodo setImageResource()
//le pasamos como parametro la imagen que se encuentre en dicha posicion, para el textview es los mismo solo usamos metodo settext y su metodo get()
        ivImgContacto.setImageResource(lstContactos.get(position).getImagen());
        tvNomComtacto.setText(lstContactos.get(position).getNombre().toString());
        return view;//retornamos la vista o converView
    }
}
