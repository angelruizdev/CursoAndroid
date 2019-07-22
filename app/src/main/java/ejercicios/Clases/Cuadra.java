package ejercicios.Clases;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.angelruiz.cursoandroid.R;

public class Cuadra extends AppCompatActivity {
    public TextView tv1,tv2;
    public EditText et1,et2,et3;
    public Button bt1,bt2;

    int a = 1;
    int b,c,d;
    int sqrt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_cuadra);

        //tv1 = (TextView)findViewById(R.id.tv1);
        //et1 = (EditText)findViewById(R.id.et1);
        //bt1 = (Button)findViewById(R.id.bt1);

        //et2 = (EditText)findViewById(R.id.et1);
        //tv2 = (TextView) findViewById(R.id.tv2);
        bt2 = (Button)findViewById(R.id.bt2);

    }



}
