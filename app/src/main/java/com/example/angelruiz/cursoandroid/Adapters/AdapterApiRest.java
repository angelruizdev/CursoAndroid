package com.example.angelruiz.cursoandroid.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.angelruiz.cursoandroid.ArraysAPI_REST.ArrayWSMysqlApi;
import com.example.angelruiz.cursoandroid.R;

import java.util.ArrayList;

public class AdapterApiRest extends RecyclerView.Adapter<AdapterApiRest.ViewHolderAdapterApiRest> {
private ArrayList<ArrayWSMysqlApi> pokemon;
public Context context;
    public  AdapterApiRest(ArrayList<ArrayWSMysqlApi> pokemon, Context context){
       this.pokemon = pokemon;
       this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderAdapterApiRest onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vista_fragm_recy_api_rest, viewGroup, false);

        return new ViewHolderAdapterApiRest(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAdapterApiRest viewHolderAdapterApiRest, int i) {
        //String urlImage = "https://source.unsplash.com/random/"+pokemon.get(i).getImagenPokemon()+".png";
        String urlImage = "https://source.unsplash.com/random/";
        Glide.with(context).load(urlImage).into(viewHolderAdapterApiRest.ivImgProducto);
        //viewHolderAdapterApiRest.ivImgProducto.setImageResource(pokemon.get(i).getImagenPokemon());
        viewHolderAdapterApiRest.tvDetalleProducto.setText(String.valueOf(pokemon.get(i).getNombre()+" - "+pokemon.get(i).getProfesion()));
    }

    @Override
    public int getItemCount() {
        return pokemon.size();
    }

    public class ViewHolderAdapterApiRest extends RecyclerView.ViewHolder {
        TextView tvDetalleProducto;
        ImageView ivImgProducto;
        public ViewHolderAdapterApiRest(@NonNull View itemView) {
            super(itemView);
            tvDetalleProducto = itemView.findViewById(R.id.tvDetalleProducto);
            ivImgProducto = itemView.findViewById(R.id.ivImgProducto);
        }
    }
}
