package com.example.angelruiz.cursoandroid.Fragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.angelruiz.cursoandroid.BDSQLite.ConstantesSqlite;
import com.example.angelruiz.cursoandroid.BDSQLite.SQLiteOpnHpr;
import com.example.angelruiz.cursoandroid.R;

public class FragmentSqliteRecycler extends Fragment implements View.OnClickListener {
    View vista;
    private SQLiteOpnHpr conn; //instanciamos la conexion a la bd
    Context context;
    String precioProducto, nombreProducto;
    private EditText etPrecioProducto, etNomProducto, etBuscIdProducto, etCoinsidencia, etCoinsidencia1, etElimIdProducto;
    private Button btGuardarProducto, btBuscarProducto, btActualizarProducto, btEliminarProducto;
    private SQLiteDatabase db;

    public FragmentSqliteRecycler() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();
        conn = new SQLiteOpnHpr(context, ConstantesSqlite.NOMBRE_BD, null, ConstantesSqlite.VERSION_BD); //creamos nuestra conexion con la clase y constructor de la clase .SQLiteOpnHpr.
        db = conn.getReadableDatabase();
        db = conn.getWritableDatabase();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_sqlite_recycler, container, false);

        etPrecioProducto = vista.findViewById(R.id.etPrecioProducto);
        etNomProducto = vista.findViewById(R.id.etNomProducto);
        etBuscIdProducto = vista.findViewById(R.id.etBuscIdProducto);
        btGuardarProducto = vista.findViewById(R.id.btGuardarProducto);
        btBuscarProducto = vista.findViewById(R.id.btBuscarProducto);
        etCoinsidencia = vista.findViewById(R.id.etCoinsidencia);
        etCoinsidencia1 = vista.findViewById(R.id.etCoinsidencia1);
        btActualizarProducto = vista.findViewById(R.id.btActualizarProducto);
        etElimIdProducto = vista.findViewById(R.id.etElimIdProducto);
        btEliminarProducto = vista.findViewById(R.id.btEliminarProducto);

        return vista;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btGuardarProducto.setOnClickListener(this);
        btBuscarProducto.setOnClickListener(this);
        btActualizarProducto.setOnClickListener(this);
        btEliminarProducto.setOnClickListener(this);
    }

    //onClick to buttons
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btGuardarProducto:
               guardarDatos();
            break;
            case R.id.btBuscarProducto:
               buscarProducto();
            break;
            case R.id.btActualizarProducto:
               actualizarProducto();
            break;
            case R.id.btEliminarProducto:
               eliminarProducto();
            break;
        }
    }

    //INSERT SQL
    private void guardarDatos(){

        precioProducto = etPrecioProducto.getText().toString();//datos a guardar en la bd
        nombreProducto = etNomProducto.getText().toString();

      if (!etPrecioProducto.getText().toString().equals("") && !etNomProducto.getText().toString().equals("")) {

          db = conn.getWritableDatabase();//abrimos la bd para escribir(guardar) en ella, mediante el objeto conn
          ContentValues contentValues = new ContentValues();//nos permite guardar registros en la bd, es como usar INSERT INTO...
          contentValues.put(ConstantesSqlite.PRECIO_PRODUCTO, precioProducto);//guardamos los valores de cada campo con metodo put()
          contentValues.put(ConstantesSqlite.NOMBRE_PRODUCTO, nombreProducto);
          contentValues.put(ConstantesSqlite.FOTO_PRODUCTO, R.drawable.face);//guardamos imagen en sqlite

          db.insert(ConstantesSqlite.TABLA_DETALLE, null, contentValues);//guardamos lo que tiene el objeto contentValues en la tabla detalle, mediante el metodo insert()
          Toast.makeText(context, "Guardado", Toast.LENGTH_SHORT).show();
          etPrecioProducto.setText("");
          etNomProducto.setText("");
          db.close();//cerramos la bd
      }
    }

    //LIKE SQL
    private void buscarProducto(){

        db = conn.getReadableDatabase();//para mostrar registro se obtiene el metodo de lectura de bd mediante la conexion(conn)
        String[] buscar = {etBuscIdProducto.getText().toString()};//id(entrada) a buscar debe estar en un array
        String[] datosMostrar = {ConstantesSqlite.PRECIO_PRODUCTO, ConstantesSqlite.NOMBRE_PRODUCTO};//registros a mostrar de la bd, deben estar en un array

        try {//Cursor filaSQL = db.rowQuery("SELECT * FROM "+ConstantesSqlite.TABLA_DETALLE+" WHERE "+ConstantesSqlite.ID_PRODUCTO+"=?", buscar);//es lo mismo pero con sentencias SQL, saca todo no solo 2 campos//es necesario ponerlo en bloque try{} por si no hay datos
            Cursor fila = db.query(ConstantesSqlite.TABLA_DETALLE, datosMostrar, ConstantesSqlite.ID_PRODUCTO+"=?", buscar,null,null,null);//con el obj Cursos fila, extraemos(guardamos) los datos de la consulta
            if (fila.moveToFirst()){//con moveToFirs(), iteramos checamos si nos podemos mover a la la primera posision(si hay registros en la tabla)//String salida = fila.getString(0)+","+ fila.getString(1);//datos juntos para mostrar en un solo TV
                etCoinsidencia.setText(fila.getString(0));//mediante el objeto fila podemos obtener los registros de cada fila, mediante los metodos getStrnig(pos fila) el metodo es segun el tipo de dato guardado puede ser getInt() etc..
                etCoinsidencia1.setText(fila.getString(1));//reciben un entero que es la posision de la columna donde se encuentra cada registro
            }
            fila.close();
        }catch (Exception e){
            Toast.makeText(context, "No encontrado!", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    //UPDATE SQL
    private void actualizarProducto() {

      if (!etCoinsidencia.getText().toString().equals("") || !etCoinsidencia1.getText().toString().equals("")) {

          db = conn.getWritableDatabase();
          String[] actualizar = {etBuscIdProducto.getText().toString()}; //id a buscar para actualizar esa fila
          ContentValues contentValues = new ContentValues();
          contentValues.put(ConstantesSqlite.PRECIO_PRODUCTO, etCoinsidencia.getText().toString());
          contentValues.put(ConstantesSqlite.NOMBRE_PRODUCTO, etCoinsidencia1.getText().toString());
          //db.update(ConstantesSqlite.TABLA_DETALLE, contentValues,ConstantesSqlite.ID_PRODUCTO+"=?", actualizar);//UPDATE con ContentValues

          db.execSQL("UPDATE " + ConstantesSqlite.TABLA_DETALLE + " SET "                   //UPDATE con SQL, execSQL(), para consultas SQL que no devuelven datos(I,U,D), Cursor para las que si(SELECT)
          + ConstantesSqlite.PRECIO_PRODUCTO + "='" + etCoinsidencia.getText().toString() + "',"
          + ConstantesSqlite.NOMBRE_PRODUCTO + "='" + etCoinsidencia1.getText().toString() + "'"
          + " WHERE " + ConstantesSqlite.ID_PRODUCTO + "=?", actualizar);
          Toast.makeText(context, "Registros actualizados!", Toast.LENGTH_SHORT).show();
          db.close();
      }
    }

    //DELETE SQL
    private void eliminarProducto(){

          db = conn.getWritableDatabase();
          String[] idEliminar = {etElimIdProducto.getText().toString()};//id a buscar para eliminar esa fila
          db.execSQL(" DELETE FROM "+ConstantesSqlite.TABLA_DETALLE+" WHERE "+ConstantesSqlite.ID_PRODUCTO+"=?", idEliminar);//consulta delete con SQL usando el metodo execSQL(), de la clase SQLiteDataBase
          //db.delete(ConstantesSqlite.TABLA_DETALLE,ConstantesSqlite.ID_CLIENTE+"=?", idEliminar);//consulta con delete de la clase SQLiteDataBase
          Toast.makeText(context, "Eliminado", Toast.LENGTH_SHORT).show();
    }

    //close db if it destroy the fmt
    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
    }

   /* *moveToFirst()  para posicionarnos al principio del cursor
      *moveToNext() para movernos a la siguiente fila.
      *Para obtener los valores de cada fila,
       usaremos los métodos getString() , getInt() , getFloat() , etc.,
       todas ellas recibiendo como único parámetro un entero que identifica
       la posición de la columna que queramos obtener.
      *la mayoria de las consultas se realizan de la misma forma si usamos la clase SQLiteDatabase de AS*/
}
