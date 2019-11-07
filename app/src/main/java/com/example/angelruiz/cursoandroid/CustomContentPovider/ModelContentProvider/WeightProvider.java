package com.example.angelruiz.cursoandroid.CustomContentPovider.ModelContentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WeightProvider extends ContentProvider {
    Context context;
    SQLiteDatabase sqLiteDatabase;
    private DataBaseCPOpnHpr dataBaseCPOpnHpr;

    //uri matcher code for the table of peso
    private static final int PESO_PERSONA = 0;

    //uri matcher code for a only result of the table peso
    private static final int PESO_ID = 1;

    //obtect UriMatcher for check the content uri
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    //static inicializer, its excecute the first time that something is called from the key
    static {
        uriMatcher.addURI(ContractSqliteConstantsCP.CONTENT_AUTHORITY, ContractSqliteConstantsCP.PATCH_WEIGHT, PESO_PERSONA);
        uriMatcher.addURI(ContractSqliteConstantsCP.CONTENT_AUTHORITY, ContractSqliteConstantsCP.PATCH_WEIGHT + "/#", PESO_ID);
    }

    //here we connect to the data base
    @Override
    public boolean onCreate() {
        context = getContext();
        dataBaseCPOpnHpr = new DataBaseCPOpnHpr(context, ContractSqliteConstantsCP.ConstantsSqliteDB.NAME_DATABASE_CP, null, ContractSqliteConstantsCP.ConstantsSqliteDB.VERSION_DATABASE);

        return true;
    }

    //we open the db in read mode
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        sqLiteDatabase = dataBaseCPOpnHpr.getReadableDatabase();
        Cursor cursor;

        int macth = uriMatcher.match(uri);
        switch (macth){
            case PESO_PERSONA:
                cursor = sqLiteDatabase.query(ContractSqliteConstantsCP.ConstantsSqliteDB.NAME_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(context.getContentResolver(), ContractSqliteConstantsCP.ConstantsSqliteDB.CONTENT_URI);
                cursor.close();
                break;

            case PESO_ID:

                break;
            default: throw new IllegalArgumentException("Unknow Uri" + uri);
        }
        return sqLiteDatabase.query(ContractSqliteConstantsCP.ConstantsSqliteDB.NAME_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
