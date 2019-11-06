package com.example.angelruiz.cursoandroid.CustomContentPovider.ModelContentProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//connection to the database
public class DataBaseCPOpnHpr extends SQLiteOpenHelper {

    public DataBaseCPOpnHpr(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableWeight = "CREATE TABLE " + ContractSqliteConstantsCP.ConstantsSqliteDB.NAME_TABLE+" (" +
                                    ContractSqliteConstantsCP.ConstantsSqliteDB._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                    ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_PESO+ " INTEGER," +
                                    ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_DATE+ " TEXT NOT NULL"+")";

        db.execSQL(createTableWeight);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + ContractSqliteConstantsCP.ConstantsSqliteDB.NAME_TABLE);
        onCreate(db);
    }
}
