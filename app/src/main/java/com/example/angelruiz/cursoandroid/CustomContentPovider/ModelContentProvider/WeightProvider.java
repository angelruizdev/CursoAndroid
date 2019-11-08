package com.example.angelruiz.cursoandroid.CustomContentPovider.ModelContentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WeightProvider extends ContentProvider {
    Context context;
    SQLiteDatabase sqLiteDatabase;
    DataBaseCPOpnHpr dataBaseCPOpnHpr;

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
        sqLiteDatabase = dataBaseCPOpnHpr.getReadableDatabase();
        return true;
    }

    //we open the db in read mode for show registers
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor cursor;

        int macth = uriMatcher.match(uri);
        switch (macth){
            case PESO_PERSONA:
                //consult all registers
                cursor = sqLiteDatabase.query(ContractSqliteConstantsCP.ConstantsSqliteDB.NAME_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(context.getContentResolver(), ContractSqliteConstantsCP.ConstantsSqliteDB.CONTENT_URI);
                break;

            case PESO_ID:
                //consult a only register based in the id of uri
                selection = ContractSqliteConstantsCP.ConstantsSqliteDB._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = sqLiteDatabase.query(ContractSqliteConstantsCP.ConstantsSqliteDB.NAME_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(context.getContentResolver(), ContractSqliteConstantsCP.ConstantsSqliteDB.CONTENT_URI);
                break;

            default: throw new IllegalArgumentException("Unknow Uri" + uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        return null;
    }


    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        //check which code of uri will use
        int match1 = uriMatcher.match(uri);
          switch (match1){
              case PESO_PERSONA:

                 return insertWeight(uri, contentValues);

          default: throw new IllegalArgumentException("\n" + "invalid data" + uri);
          }
    }

    //save data in CPSqlite
    private Uri insertWeight(Uri uri, ContentValues contentValues){

        //save the the insert in a long
        long id = sqLiteDatabase.insert(ContractSqliteConstantsCP.ConstantsSqliteDB.NAME_TABLE, null, contentValues);

        //if insert return -1 data not saved
        if (id == -1){
            Toast.makeText(context, "¡Error al guardar datos!", Toast.LENGTH_SHORT).show();
            Log.i("ErrorSaveData", "data not saved");
        return null;

        }else{
            Log.i("SaveData", "data saved " + ContentUris.withAppendedId(uri, id));
        }
        return ContentUris.withAppendedId(uri, id); // join the uri own with the id(insert) for save it
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
