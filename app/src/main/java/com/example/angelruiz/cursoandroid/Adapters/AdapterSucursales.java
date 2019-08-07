package com.example.angelruiz.cursoandroid.Adapters;

import android.app.Activity;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.angelruiz.cursoandroid.Arrays.ArraySucursales;
import com.example.angelruiz.cursoandroid.R;

import java.util.ArrayList;

//podemos pasarle la clase <ViewHolderSucursales> si la creamos aparte y no en esta misma
public class AdapterSucursales extends RecyclerView.Adapter<AdapterSucursales.ViewHolderSucursales> {
    //volvemos a crear e inicializar nuestro arrayL pasandole el array de clase
    private ArrayList<ArraySucursales> sucursales;
    private Activity activity;//funciona como contexto para AlertDialog o Toast al no estar en una Activity
    //constructor para inicializar el arrayL y el contecto
    public AdapterSucursales(ArrayList<ArraySucursales> sucursales, Activity activity){
       this.sucursales=sucursales;
       this.activity=activity;
    }
//inflamos nuestra vista con una vista personalizada con un objeto tipo View usando el parametro parent
    @Override
    public AdapterSucursales.ViewHolderSucursales onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_recicler_sucursales,null,false);
        return new ViewHolderSucursales(vista); //retornamos el objeto vista
    }
//mediante este metodo vamos a pintar los datos en el recyclerV usando el parametro holder y metodo set()
    @Override
    public void onBindViewHolder(AdapterSucursales.ViewHolderSucursales holder, final int position)  {
        holder.ivImgSucursal.setImageResource(sucursales.get(position).getImgSucursal());
        holder.tvNomSucursal.setText(sucursales.get(position).getNombreSucursal());
        holder.ivImgSucursal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(activity);
                builder.setIcon(R.drawable.ic_touch_app);
                builder.setTitle("Detalle");
                builder.setMessage(sucursales.get(position).getNombreSucursal());//pasamos el nombre de la sucursal, que este en el item(position)que se presione
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();
            }
        });
        holder.btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Email"+position, Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnLLamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "LLamar"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }
//a este metodo le pasamos el tamaño del arrayL, que son los items que pintara el recyclerV con el metodo size()
    @Override
    public int getItemCount() {
        return sucursales.size();
    }
//en esta clase debemos declarar los controles de la vista inflate(personalizada), para esto usamos el parametro del metodo ViewHolderSucursales de abajo
    public class ViewHolderSucursales extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView ivImgSucursal;
        TextView tvNomSucursal;
        Button btnEmail,btnLLamar;
        private ViewHolderSucursales(View itemView) {
            super(itemView);
            ivImgSucursal=(ImageView)itemView.findViewById(R.id.ivImgSucursal);
            tvNomSucursal=(TextView)itemView.findViewById(R.id.tvNomSucursal);
            btnEmail=(Button)itemView.findViewById(R.id.btnEmail);
            btnLLamar=(Button)itemView.findViewById(R.id.btnLLamar);
            }
            @Override
            public void onClick(View view) {

            }
}
}
