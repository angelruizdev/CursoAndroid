package com.example.angelruiz.cursoandroid.BDSQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteOpnHpr extends SQLiteOpenHelper {//debemos extender de esta clase, para crear la bd, e implementar su constructor y 2 metodo y
private Context context;
    public SQLiteOpnHpr(Context context, String nameBD, SQLiteDatabase.CursorFactory factory, int versionBD) {//pasamos los parametros en donde instanciemos este constructor
        super(context, nameBD, factory, versionBD);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//este metodo recibe la bd, dentro de este metodo creamos nuestra/s tablas
      String queryTableDetalle = "CREATE TABLE " + ConstantesSqlite.TABLA_DETALLE+ " (" +
              ConstantesSqlite.ID_PRODUCTO+" INTEGER PRIMARY KEY AUTOINCREMENT," +
              ConstantesSqlite.PRECIO_PRODUCTO+ " INTEGER," +
              ConstantesSqlite.NOMBRE_PRODUCTO + " TEXT, "
              +ConstantesSqlite.FOTO_PRODUCTO+" INTEGER"+")";

      db.execSQL(queryTableDetalle);//ejecutamos el query mediante el objeto db y el metodo executeSQL()
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//este metodo nos permite actualizar la bd si ya exixte y se vuelve a instalar la app
       db.execSQL(" DROP TABLE IF EXISTS "+ConstantesSqlite.TABLA_DETALLE);//si la tabla ya exixte la borra
       onCreate(db);//despues la vielve a crear
    }
}
