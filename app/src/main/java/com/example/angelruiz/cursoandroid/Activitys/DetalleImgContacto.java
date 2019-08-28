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
 ArrayImgContacto dataContact;
 int imageContact;
 String nameContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_img_contacto);

        Intent receiveDataImgGrid = getIntent();

        if(receiveDataImgGrid != null) {
            dataContact = receiveDataImgGrid.getParcelableExtra("nombreContactos");
            imageContact = dataContact.getImagen();
            nameContact = dataContact.getNombre();
        }else {
            Toast.makeText(this, "No hay información", Toast.LENGTH_SHORT).show();
        }
            ivDetalleImg = findViewById(R.id.ivDetalleImg);
            tvDetalleImg = findViewById(R.id.tvDetalleImg);

            ivDetalleImg.setImageResource(imageContact);
            tvDetalleImg.setText(nameContact);

            ivDetalleImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(DetalleImgContacto.this, ListaAgenda.class));
            }
        });
    }
}
