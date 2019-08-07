package com.example.angelruiz.cursoandroid.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.angelruiz.cursoandroid.ArraysAPI_REST.ArrayWSMysqlApi;
import com.example.angelruiz.cursoandroid.InterfazAPI_REST.EndPointAPI_REST;
import com.example.angelruiz.cursoandroid.R;
import com.example.angelruiz.cursoandroid.RespuestaAPI_REST.RespuestaApiRest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterApiRest extends RecyclerView.Adapter<AdapterApiRest.ViewHolderAdapterApiRest> {
private ArrayList<ArrayWSMysqlApi> pokemon;
public Context context;
private Retrofit retrofit;
private static final String TAG = "API_REST";

    public  AdapterApiRest(ArrayList<ArrayWSMysqlApi> pokemon, Context context){
       this.pokemon = pokemon;
       this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderAdapterApiRest onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vista_fragm_recy_api_rest, viewGroup, false);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://proyectosangelito.000webhostapp.com/webServiceMysql/")//url de la API, debe terminar con slash(/)rft2
                .addConverterFactory(GsonConverterFactory.create()).build();
        return new ViewHolderAdapterApiRest(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderAdapterApiRest viewHolderAdapterApiRest, @SuppressLint("RecyclerView") final int pos) {
        //String urlImage = "https://source.unsplash.com/random/"+pokemon.get(i).getImagenPokemon()+".png";
        String urlImage = "https://source.unsplash.com/random/";
        Glide.with(context).load(urlImage).into(viewHolderAdapterApiRest.ivImgProducto);
        //viewHolderAdapterApiRest.ivImgProducto.setImageResource(pokemon.get(pos).getImagenPokemon());
        viewHolderAdapterApiRest.tvDetalleProducto.setText(String.valueOf(pokemon.get(pos).getNombre()+" - "+pokemon.get(pos).getProfesion()));
        viewHolderAdapterApiRest.ivElimProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EndPointAPI_REST service = retrofit.create(EndPointAPI_REST.class);

                Call<RespuestaApiRest> eliminarUsuario = service.eliminarRegApiRest(pokemon.get(pos).getIdPersona());
                eliminarUsuario.enqueue(new Callback<RespuestaApiRest>() {
                    @Override
                    public void onResponse(@NonNull Call<RespuestaApiRest> call, @NonNull Response<RespuestaApiRest> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(context, "Eliminado", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "No eliminado", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<RespuestaApiRest> call, @NonNull Throwable t) {
                        Log.e(TAG, "onFailure" + t.getMessage());
                    }
                });            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemon.size();
    }

    public class ViewHolderAdapterApiRest extends RecyclerView.ViewHolder {
        TextView tvDetalleProducto;
        ImageView ivImgProducto, ivElimProducto;
        public ViewHolderAdapterApiRest(@NonNull View itemView) {
            super(itemView);
            tvDetalleProducto = itemView.findViewById(R.id.tvDetalleProducto);
            ivImgProducto = itemView.findViewById(R.id.ivImgProducto);
            ivElimProducto = itemView.findViewById(R.id.ivElimProducto);
        }
    }
}
