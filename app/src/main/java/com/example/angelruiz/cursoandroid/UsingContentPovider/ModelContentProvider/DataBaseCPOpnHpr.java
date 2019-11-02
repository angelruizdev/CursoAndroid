package com.example.angelruiz.cursoandroid.UsingContentPovider.ModelContentProvider;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.angelruiz.cursoandroid.BDSQLite.ConstantesSqlite;

public class DataBaseCPOpnHpr extends SQLiteOpenHelper {

    public DataBaseCPOpnHpr(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableWeight = "CREATE TABLE " + ContractSqliteConstantsCP.ConstantsSqliteDB.NAME_TABLE+
                                              " (" + ContractSqliteConstantsCP.ConstantsSqliteDB._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                                                   + ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_PESO+ "INTEGER,"
                                                   + ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_DATE+ " TEXT NOT NULL"+")";
        db.execSQL(createTableWeight);

        String queryTableDetalle = "CREATE TABLE " + ConstantesSqlite.TABLA_DETALLE+ " (" +
                ConstantesSqlite.ID_PRODUCTO+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                ConstantesSqlite.PRECIO_PRODUCTO+ " INTEGER," +
                ConstantesSqlite.NOMBRE_PRODUCTO + " TEXT, "+
                ConstantesSqlite.FOTO_PRODUCTO+" INTEGER"+")";

        db.execSQL(queryTableDetalle);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + ContractSqliteConstantsCP.ConstantsSqliteDB.NAME_TABLE);
        onCreate(db);
    }
}
