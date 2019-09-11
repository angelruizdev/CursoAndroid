package com.example.angelruiz.cursoandroid.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angelruiz.cursoandroid.ArraysAPI_REST.ArrayWSMysqlApi;
import com.example.angelruiz.cursoandroid.InterfazAPI_REST.IOnClickApiRest;
import com.example.angelruiz.cursoandroid.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Retrofit;

public class AdapterApiRest extends RecyclerView.Adapter<AdapterApiRest.ViewHolderAdapterApiRest> {

public Context context;
private ArrayList<ArrayWSMysqlApi> pokemon;
private IOnClickApiRest listener;
private Retrofit retrofit;
private static final String TAG = "API_REST";

    public  AdapterApiRest(Context context, ArrayList<ArrayWSMysqlApi> pokemon){
       this.context = context;
       this.pokemon = pokemon;
    }

    public void setOnClickListenerDelete(IOnClickApiRest listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolderAdapterApiRest onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vista_fragm_recy_api_rest, viewGroup, false);

        return new ViewHolderAdapterApiRest(vista, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderAdapterApiRest viewHolderAdapterApiRest, @SuppressLint("RecyclerView") final int pos) {
        String urlImage = "https://source.unsplash.com/random/";

        Picasso.with(context)
                .load(urlImage)
                .placeholder(R.drawable.ic_no_image)
                .into(viewHolderAdapterApiRest.ivImgProducto);

        viewHolderAdapterApiRest.tvDetalleProducto.setText(String.valueOf(pokemon.get(pos).getNombre()+" - "+pokemon.get(pos).getProfesion()));

        viewHolderAdapterApiRest.ibElimProducto.setImageResource(R.drawable.ic_delete);
    }

    @Override
    public int getItemCount() {
        return pokemon.size();
    }

    public class ViewHolderAdapterApiRest extends RecyclerView.ViewHolder {
        ImageView ivImgProducto;
        TextView tvDetalleProducto;
        ImageButton ibElimProducto;

        public ViewHolderAdapterApiRest(@NonNull View itemView, final IOnClickApiRest listener) {
            super(itemView);
            tvDetalleProducto = itemView.findViewById(R.id.tvDetalleProducto);
            ivImgProducto = itemView.findViewById(R.id.ivImgProducto);
            ibElimProducto = itemView.findViewById(R.id.ibElimProducto);
            ibElimProducto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listener.onClickImageDelete(position);
                }
            });
        }
    }
}
