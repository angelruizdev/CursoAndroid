package ejercicios.Clases;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class practicas extends AppCompatActivity {
ListView lvContactos;
//ExpandableListView lvContactos;
    /*
    //Variebles del ejercicio anterior
    public TextView tv1,tv2,tv3;
    public EditText et1,et2;
    public Button bt1,bt2,bt3;
    int a = 1;
    int b,c,d;
    int sqrt;
    */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_practicas);
        //lvContactos = (ListView) findViewById(R.id.lvContactos);
        //lvContactos = (ExpandableListView) findViewById(R.id.lvContactos);
        //Adaptador con array de archivo de recursos XML, > en values>dás>arrayDías:
        //ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource
        //(this, R.array.arrayDias, android.R.layout.simple_expandable_list_item_1);//Gurdamos el array, array días en el adaptador
        //Adaptado con clase ArrayList:
        ArrayList<String> listaNombres = new ArrayList<>();
        listaNombres.add("Gustavo");
        listaNombres.add("Eneyda");
        listaNombres.add("Roberto");
        listaNombres.add("Ángel");
        listaNombres.add("Gustavo");

        //ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,listaNombres);//Adaptador para el ArrayList,
        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaNombres);//Adaptador para el ArrayList,
        lvContactos.setAdapter(adaptador);//le asignamos el adapter al ListView

        lvContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), "El día es :"+parent.getItemAtPosition(position)
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }

/*
//Ejercicio anterior
        tv1 = (TextView)findViewById(R.id.tv1);
        et1 = (EditText)findViewById(R.id.et1);
        bt1 = (Button)findViewById(R.id.bt1);

        et2 = (EditText)findViewById(R.id.et2);
        tv2 = (TextView) findViewById(R.id.tv2);
        bt2 = (Button)findViewById(R.id.bt2);

        tv3 = (TextView) findViewById(R.id.tv3);
        bt3 = (Button)findViewById(R.id.bt3);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //----------------------------------------------------------------------------------
                int fac = 1;
                    b = Integer.parseInt(et1.getText().toString());

                    for (int z = b; z >= 1; z--) {

                        fac = fac * z;
                    }
                    int result = fac;
                    tv1.setText(String.valueOf(result));


                //------------------------------------------------------------------------------
                        // if(b<10){  ... para contar el número de digitos
                       //Toast.makeText(practicas.this, "dos digitos", Toast.LENGTH_SHORT).show();
                     //}else {
                    // }
            }
        });
    }
//--------------------------------------------------------------------------------------------------
                  public void cuad(View v){
                    int e = 1;
                      c = Integer.parseInt(et2.getText().toString());
                            while (e <= c) {

                                //c = Integer.parseInt(et2.getText().toString());
                                sqrt = c * c;
                                tv2.setText(String.valueOf(sqrt));
                                et2.setText("");
                                  //c = Integer.parseInt(et2.getText().toString());
                                    //Toast.makeText(practicas.this, "tabla" + sqrt, Toast.LENGTH_SHORT).show();
                                e++;
                            }
                      Toast.makeText(practicas.this, "otro", Toast.LENGTH_SHORT).show();
                    }
//--------------------------------------------------------------------------------------------------

   public void hilo(View v){
   hilo hilo = new hilo();
   hilo.start();
   }
    class hilo extends Thread {
    public void run() {
        for (int h = 0; h <= 10; h++) {
            Toast.makeText(getApplication(), "Lista :" + h, Toast.LENGTH_SHORT).show();
        }
    }
    ///////////////////////////////////////
    XML, DE ÉSTA LÓGICA.
    <TextView
        android:id="@+id/tv1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        android:layout_gravity="center"
        android:text="Factorial"/>

    <EditText
        android:id="@+id/et1"
        android:layout_width="200dp"
        android:layout_height="50dp"
        android:layout_gravity="center"/>

    <Button
        android:id="@+id/bt1"
        android:layout_width="200dp"
        android:layout_height="50dp"
        android:layout_marginTop="20dp"
        android:layout_gravity="center"
        android:onClick="OnClick"
        android:text="Calcular"/>

    <TextView
        android:id="@+id/tv2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="30dp"
        android:layout_gravity="center"
        android:text="Cuadrado"/>



    <EditText
        android:id="@+id/et2"
        android:layout_width="200dp"
        android:layout_height="50dp"
        android:layout_gravity="center"/>


    <Button
        android:id="@+id/bt2"
        android:layout_width="200dp"
        android:layout_height="50dp"
        android:layout_marginTop="20dp"
        android:layout_gravity="center"
        android:onClick="cuad"
        android:text="Calcular"/>

    <TextView
        android:id="@+id/tv3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="30dp"
        android:layout_gravity="center"
        android:text="Hilo, lista"/>

    <Button
        android:id="@+id/bt3"
        android:layout_width="200dp"
        android:layout_height="50dp"
        android:layout_marginTop="30dp"
        android:layout_gravity="center"
        android:onClick="hilo"
        android:text="Calcular"/>
        ////////////////////////////////////////////
    */


}


