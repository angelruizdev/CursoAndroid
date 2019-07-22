package com.example.angelruiz.cursoandroid.Components;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.angelruiz.cursoandroid.Arrays.ArrayVPagerInstruction;
import com.example.angelruiz.cursoandroid.R;

import java.util.List;

public class CmpPointIndicatorIntruction extends FrameLayout { //extendemos de esta clase para inflar e inicializar los componentes de la vista personalizada, nos crea 2 métodos constructores
    LinearLayout llcontentPointsIntruction; //aqui se mostraran los puntos
    TextView[] pointsSlideIntro; //array de tipo tv para mostrar puntos
    Context context;
    Button btExitInstruction;

    public CmpPointIndicatorIntruction(Context context) {
        super(context);
        this.context = context;
        View view = inflate(context, R.layout.view_cmp_point_instruction, null);
        llcontentPointsIntruction = view.findViewById(R.id.llcontentPointsIntruction);
        btExitInstruction = view.findViewById(R.id.btExitInstruction);
        this.addView(view);
    }

    public CmpPointIndicatorIntruction(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View view = inflate(context, R.layout.view_cmp_point_instruction, null);
        llcontentPointsIntruction = view.findViewById(R.id.llcontentPointsIntruction);
        btExitInstruction = view.findViewById(R.id.btExitInstruction);
        this.addView(view);
    }

    public void showPointInstruction( int position, List<ArrayVPagerInstruction> informacion){ //creamos y mostramos puntos
        pointsSlideIntro = new TextView[informacion.size()]; //el tamaño del array sera el numero de vistas slide instruction, por si se agraga otra vista al viewPager en automatico se crea otro punto
        llcontentPointsIntruction.removeAllViews();

        for (int i = 0; i < pointsSlideIntro.length; i++) { //decoramos el array tv
            pointsSlideIntro[i] = new TextView(context);
            pointsSlideIntro[i].setText(Html.fromHtml("&#8226;"));
            pointsSlideIntro[i].setTextSize(35);
            pointsSlideIntro[i].setTextColor(getResources().getColor(R.color.accent_material_dark));
            llcontentPointsIntruction.addView(pointsSlideIntro[i]); // al linear le pasamos el array de tv para mostrar los puntos
        }
        if (pointsSlideIntro.length > 0){
            pointsSlideIntro[position].setTextColor(getResources().getColor(R.color.colorPrimary)); //al array tv le mandamos la posision del slide y le ponemos un color al circulito tv para que lo muestre cuando cambie de slide
        }
        if (position == informacion.size() - 1) { //si la posision del slide es = al numero de slides muestra el boton finalizar
            llcontentPointsIntruction.setVisibility(View.INVISIBLE);
            btExitInstruction.setVisibility(View.VISIBLE);
        }
        if (position < informacion.size() - 1) { // si es menor oculta el boton
            llcontentPointsIntruction.setVisibility(View.VISIBLE);
            btExitInstruction.setVisibility(View.INVISIBLE);
        }
    }
}
/*un coponente personalizado trae la logica de funcionamiento, y se ejecuta desde la activity mediante mesiante un obj de dicha clase
por lo regular se extiende de un layout para hacer componentes personalizados*/
