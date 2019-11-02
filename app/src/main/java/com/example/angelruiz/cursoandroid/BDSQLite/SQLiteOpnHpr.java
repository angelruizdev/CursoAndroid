package com.example.angelruiz.cursoandroid.BDSQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//debemos extender de esta clase, para crear la bd, e implementar su constructor y 2 metodo y
public class SQLiteOpnHpr extends SQLiteOpenHelper {
    private Context context;

    //pasamos los parametros en donde instanciemos este constructor
    public SQLiteOpnHpr(Context context, String nameBD, SQLiteDatabase.CursorFactory factory, int versionBD) {
        super(context, nameBD, factory, versionBD);
        this.context = context;
    }

    //este metodo recibe la bd, dentro de este metodo creamos nuestra/s tablas
    @Override
    public void onCreate(SQLiteDatabase db) {
      String queryTableDetalle = "CREATE TABLE " + ConstantesSqlite.TABLA_DETALLE+ " (" +
                                                   ConstantesSqlite.ID_PRODUCTO+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                   ConstantesSqlite.PRECIO_PRODUCTO+ " INTEGER," +
                                                   ConstantesSqlite.NOMBRE_PRODUCTO + " TEXT, "+
                                                   ConstantesSqlite.FOTO_PRODUCTO+" INTEGER"+")";

      db.execSQL(queryTableDetalle);//ejecutamos el query mediante el objeto db y el metodo executeSQL()
    }

    //este metodo nos permite actualizar la bd si ya existe y se vuelve a instalar la app
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL(" DROP TABLE IF EXISTS "+ConstantesSqlite.TABLA_DETALLE);//si la tabla ya existe la borra
       onCreate(db);//despues la vuelve a crear
    }
}
