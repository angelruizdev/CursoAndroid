package com.example.angelruiz.cursoandroid.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.angelruiz.cursoandroid.Arrays.ArrayImgContacto;
import com.example.angelruiz.cursoandroid.R;

public class DetalleImgContacto extends AppCompatActivity {

 ImageView ivDetalleImg;
 TextView tvDetalleImg;
 ArrayImgContacto dataContact; //this array save the data obtain of the key, parceable
 int imageContact;
 String nameContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_img_contacto);

        //we receive the data of imagengrid using interface Parceable
        Intent receiveDataImgGrid = getIntent();

        if(receiveDataImgGrid != null) {
            dataContact = receiveDataImgGrid.getParcelableExtra("nombreContactos"); //we obtain and save the key with the data, with parceable
            imageContact = dataContact.getImagen(); //we save and take out the data for show
            nameContact = dataContact.getNombre();
        }else {
            Toast.makeText(this, "No hay información", Toast.LENGTH_SHORT).show();
        }
            ivDetalleImg = findViewById(R.id.ivDetalleImg);
            tvDetalleImg = findViewById(R.id.tvDetalleImg);

            ivDetalleImg.setImageResource(imageContact); //we show the data, the parceable
            tvDetalleImg.setText(nameContact);

            ivDetalleImg.setOnClickListener(new View.OnClickListener() { //we pass to other activity if pressed the image
            @Override
            public void onClick(View view) {
             startActivity(new Intent(DetalleImgContacto.this, ListaAgenda.class));
            }
        });
    }
}