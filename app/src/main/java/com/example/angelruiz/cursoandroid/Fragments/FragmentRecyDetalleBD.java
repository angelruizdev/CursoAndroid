  package com.example.angelruiz.cursoandroid.Fragments;

  import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.angelruiz.cursoandroid.Arrays.ArrayProductosBD;
import com.example.angelruiz.cursoandroid.R;
  public class FragmentRecyDetalleBD extends Fragment {
  View vista;
  private ImageView ivImgDetalle; //componentes donde se mostrara el detalle del item seleccionado del RV
  private TextView tvDetalleProducto;

    public FragmentRecyDetalleBD() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments()!= null){//para recibir datos de una activity usar if, para comprobar que trae datos si no manda expepsion
            Bundle bundle = this.getArguments();
            nom=bundle.getString("nombre");
        }*/
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_recy_detalle_bd, container, false);

        ivImgDetalle = vista.findViewById(R.id.ivImgDetalle);
        tvDetalleProducto = vista.findViewById(R.id.tvDetalleProducto);

    return vista;
    }

      @Override
      public void onActivityCreated(@Nullable Bundle savedInstanceState) {
          super.onActivityCreated(savedInstanceState);

          Bundle datosObtenidos = getArguments(); //recibimos los datos desde la activity padre al FragmentRecyDetalleBD//si pasamos los datos de activity a frgmnt
          ArrayProductosBD aProductosBD; //objeto del pojo de la BD para traer todos sus datos
          if (datosObtenidos!= null){
              aProductosBD = (ArrayProductosBD) datosObtenidos.getSerializable("objeto");//en este objeto guardamos los datos recibidos desde activity padre con la key "objeto"
              if (aProductosBD != null) { //si el objeto tiene datos jala al metodo para que los pinte en los componentes correspondientes
                  recibirDatos(aProductosBD);
              }
          }
      }

      public void recibirDatos(ArrayProductosBD aProductosBD) {//este metodo recibe al objetoa,aProductosBD que trae los datos para poder mostrarlos
        ivImgDetalle.setImageResource(aProductosBD.getFotoProducto());//mediante el objeto aProductosBD, mostramos los recursos correspondientes
        tvDetalleProducto.setText(String.valueOf(aProductosBD.getPrecioProducto()+" - $"+aProductosBD.getNombreProducto()));
    }
}
