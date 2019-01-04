package com.example.angelruiz.cursoandroid.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.angelruiz.cursoandroid.Arrays.ArrayImgContacto;
import com.example.angelruiz.cursoandroid.R;

public class DetalleImgContacto extends AppCompatActivity {
ImageView ivDetalleImg;
TextView tvDetalleImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_img_contacto);

        ArrayImgContacto datosContacto = (ArrayImgContacto) getIntent().getExtras().getSerializable("datosContacto");

        ivDetalleImg=(ImageView) findViewById(R.id.ivDetalleImg);
        tvDetalleImg=(TextView) findViewById(R.id.tvDetalleImg);

        ivDetalleImg.setImageResource(datosContacto.getImagen());
        tvDetalleImg.setText(datosContacto.getNombre());
        ivDetalleImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(getApplicationContext(),ListaAgenda.class));
            }
        });
    }
}
