package com.example.angelruiz.cursoandroid.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.angelruiz.cursoandroid.Arrays.ArrayProductosBD;
import com.example.angelruiz.cursoandroid.R;

import java.util.ArrayList;

public class AdapterFragmentRecyProdBd extends RecyclerView.Adapter<AdapterFragmentRecyProdBd.ViewHolderFragmentRecyProdBd> implements View.OnClickListener{
    private ArrayList<ArrayProductosBD> listaProductosBD;//arreglo que guarda toda la informacion de la bd SQLite
    private Context context;
    private View.OnClickListener listener;//objeto para crear onClick en RV

    public AdapterFragmentRecyProdBd(ArrayList<ArrayProductosBD> listaProductosBD, Context context) {
        this.listaProductosBD = listaProductosBD;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterFragmentRecyProdBd.ViewHolderFragmentRecyProdBd onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_fragm_recy_prod_bd, null, false);
        vista.setOnClickListener(this);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,//nos permite poner todos los componente de tipo RV en match y wrap
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new ViewHolderFragmentRecyProdBd(vista);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdapterFragmentRecyProdBd.ViewHolderFragmentRecyProdBd duenioVista, final int position) {
        duenioVista.tvDetalleProducto.setText(listaProductosBD.get(position).getIdProducto()//mostramos los datos mediante el array,listaProductosBD, y la posision(item) y sus metodos get del pojo(ArrayProductosBD) en los componentes de la vista que infla al RV
                +" - "+listaProductosBD.get(position).getPrecioProducto());
        duenioVista.ivImgProducto.setImageResource(listaProductosBD.get(position).getFotoProducto());
    }

    @Override
    public int getItemCount() {
        return listaProductosBD.size();
    }

    public void setOnCliclListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v);
    }

    public class ViewHolderFragmentRecyProdBd extends RecyclerView.ViewHolder {//declaramos los componentes de la vista que infla el RV
        ImageView ivImgProducto;
        TextView tvDetalleProducto;

        public ViewHolderFragmentRecyProdBd(@NonNull final View itemView) {
            super(itemView);
            ivImgProducto = itemView.findViewById(R.id.ivImgProducto);
            tvDetalleProducto = itemView.findViewById(R.id.tvDetalleProducto);
        }
    }
}
