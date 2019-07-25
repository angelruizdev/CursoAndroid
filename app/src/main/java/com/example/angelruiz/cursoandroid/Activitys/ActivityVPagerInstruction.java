package com.example.angelruiz.cursoandroid.Activitys;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.angelruiz.cursoandroid.Adapters.AdapterVPagerInstruction;
import com.example.angelruiz.cursoandroid.Arrays.ArrayVPagerInstruction;
import com.example.angelruiz.cursoandroid.Components.CmpPointIndicatorIntruction;
import com.example.angelruiz.cursoandroid.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityVPagerInstruction extends AppCompatActivity {
    List<ArrayVPagerInstruction> informacion; //array guarda los datos a mostrar en vista personalizada (pojo)
    ViewPager vpInstruction;
    Context context;
    AdapterVPagerInstruction adapterVPagerInstruction; //adapter para mostrar pojo en vp

    CmpPointIndicatorIntruction cmpPointIndicatorIntruction; //creamos obj del componente custom para llamarlo

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vpager_instruction);

        informacion = new ArrayList<>();
        llenarViewPagerInstruction();
        vpInstruction = findViewById(R.id.vpInstruction);
        context = getApplicationContext();

        adapterVPagerInstruction = new AdapterVPagerInstruction(informacion, context); //pasamos los datos al adapter
        vpInstruction.setAdapter(adapterVPagerInstruction); //se lo pasamos al vp

        cmpPointIndicatorIntruction = findViewById(R.id.llcontentPointsIntruction1);
        vpInstruction.addOnPageChangeListener(onPageChangeListener); //agregamos el escuchador de cambio de slide al vp
        cmpPointIndicatorIntruction.showPointInstruction(0, informacion); //llamamos al metodo del componente mediante su obj
    }



    private void llenarViewPagerInstruction() { //llenamos el array pojo
        informacion.add(new ArrayVPagerInstruction("GUSTAVO", R.drawable.email, "choco", getResources().getColor(R.color.colorBoton)));
        informacion.add(new ArrayVPagerInstruction("ROBERTO", R.drawable.email, "pano", getResources().getColor(R.color.primary_dark_material_dark)));
        informacion.add(new ArrayVPagerInstruction("ENEYDA", R.drawable.email, getResources().getString(R.string.tvtext_description_instruction), getResources().getColor(R.color.colorText)));
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() { //acedemos a la interface OnPageChangeListener, para ver en que slider se encuentra en vp, y obtener la posision, nos genera 3 metodos
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) { //este metodo nos da la posision del slide actual en el vp
            cmpPointIndicatorIntruction.showPointInstruction(i, informacion); //llamamos al metodo del componente mediante su obj, pasandole los parametros que pide
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    public void btExitInstructionM(View view) { //metodo finaliza activity
        finish();
    }
}
//desde el activity llamamos al componente, asi como a sus metodos mediante este