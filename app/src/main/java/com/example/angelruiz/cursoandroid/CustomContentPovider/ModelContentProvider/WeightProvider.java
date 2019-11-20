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
import android.util.SparseArray;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WeightProvider extends ContentProvider {
    Context context;
    SQLiteDatabase sqLiteDatabase;
    DataBaseCPOpnHpr dataBaseCPOpnHpr;

    //uri matcher code for bring all the registers of the table peso(*), SELECT * FROM...
    private static final int PESO_PERSONA_URI_INPUT = 0;
    //uri matcher code for bring a only register of the table peso(#), SELECT FROM...
    private static final int PESO_ID_URI_INPUT = 1;

    //obtect UriMatcher for check the content uri
    private static final UriMatcher uriMatcher;
    //mime types
    static SparseArray<String> mimeTypes;

    //static inicializer, its excecute the first time that something is called from the key
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mimeTypes = new SparseArray<>();
        //uri: content://com.example.angelruiz.cursoandroid/registropeso
        uriMatcher.addURI(ContractSqliteConstantsCP.CONTENT_AUTHORITY, ContractSqliteConstantsCP.PATCH_WEIGHT, PESO_PERSONA_URI_INPUT);
        //uri: content://com.example.angelruiz.cursoandroid/registropeso/id(#)
        uriMatcher.addURI(ContractSqliteConstantsCP.CONTENT_AUTHORITY, ContractSqliteConstantsCP.PATCH_WEIGHT + "/#", PESO_ID_URI_INPUT);

    }

    //CREATE SQL - here we connect to the data base
    @Override
    public boolean onCreate() {
        context = getContext();
        dataBaseCPOpnHpr = new DataBaseCPOpnHpr(context, ContractSqliteConstantsCP.ConstantsSqliteDB.NAME_DATABASE_CP, null, ContractSqliteConstantsCP.ConstantsSqliteDB.VERSION_DATABASE);
        sqLiteDatabase = dataBaseCPOpnHpr.getReadableDatabase(); //db mode read
        sqLiteDatabase = dataBaseCPOpnHpr.getWritableDatabase(); //db mode write
    return true;
    }

    //SELECT SQL - we open the db in read mode for show registers
    @Nullable
    @Override//selection = where of SQL
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor cursor;

        int macth = uriMatcher.match(uri);
        switch (macth){
            case PESO_PERSONA_URI_INPUT:
                //consult all registers
                cursor = sqLiteDatabase.query(ContractSqliteConstantsCP.ConstantsSqliteDB.NAME_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case PESO_ID_URI_INPUT:
                //consult a only register based in the id of uri
                selection = ContractSqliteConstantsCP.ConstantsSqliteDB._ID + "=?"; //where id
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))}; //be equal to this id(Long), one or various params
                cursor = sqLiteDatabase.query(ContractSqliteConstantsCP.ConstantsSqliteDB.NAME_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            default: throw new IllegalArgumentException("Unknow Uri: " + uri);
        }
        Log.i("uris", "uri exit: " + uri +" = "+ ContractSqliteConstantsCP.ConstantsSqliteDB.CONTENT_URI);
        //notify any change in the uri of register(table)
        cursor.setNotificationUri(context.getContentResolver(), ContractSqliteConstantsCP.ConstantsSqliteDB.CONTENT_URI);
        return cursor;
    }

    //INSERT SQL
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

          //check which code of uri will use, save all the data
          switch (uriMatcher.match(uri)){
              case PESO_PERSONA_URI_INPUT:

                 return insertWeight(uri, contentValues);

          default: throw new IllegalArgumentException("\n invalid data" + uri);
          }
    }

    //save data in CP Sqlite
    private Uri insertWeight(Uri uri, ContentValues contentValues){
        Uri uriWithId;

        //save the insert in a long
        long id = sqLiteDatabase.insert(ContractSqliteConstantsCP.ConstantsSqliteDB.NAME_TABLE, null, contentValues);

        //if insert return -1 data not saved
        if (id == -1){
            Toast.makeText(context, "¡Error al guardar datos!", Toast.LENGTH_SHORT).show();
            Log.i("ErrorSaveData", "data not saved");
        return null;

        }else{
            Log.i("SaveData", "data saved " + ContentUris.withAppendedId(uri, id));
            uriWithId = ContentUris.withAppendedId(uri, id);
            //notify to cursor loader of a new (change)register
            context.getContentResolver().notifyChange(uriWithId, null);
        }
        //join the uri own CP with the id(insert) for save it
        return ContentUris.withAppendedId(uri, id);
    }

    //UPDATE SQL
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        switch (uriMatcher.match(uri)){
            case PESO_PERSONA_URI_INPUT:
                if (values != null){
                    return updateWeight(uri, values, selection, selectionArgs);
                }

            case PESO_ID_URI_INPUT:
                selection = ContractSqliteConstantsCP.ConstantsSqliteDB._ID + "=?"; //where id
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))}; //be equal to this id(Long)
                Toast.makeText(context, "uri: "+ContractSqliteConstantsCP.ConstantsSqliteDB._ID + "=?", Toast.LENGTH_SHORT).show();

                if (values != null){
                    return updateWeight(uri, values, selection, selectionArgs);
                }

            default: throw new IllegalArgumentException("Update is not support by " + uri);
        }
    }

    //verifyc if the data to update are correct and exists
    public int updateWeight(Uri uri, ContentValues values, String selection, String[] selectionArgs){

        if (values.containsKey(ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_PESO)){
            String weightAux = values.getAsString(ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_PESO);
            if (weightAux == null){
                throw new IllegalArgumentException("El peso es necesario.");
            }
        }

        if (values.containsKey(ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_DATE)){
            Integer dateAux = values.getAsInteger(ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_DATE);
            if (dateAux == null){
                throw new IllegalArgumentException("La fecha es necesaria.");
            }
        }

    context.getContentResolver().notifyChange(uri, null);
    return sqLiteDatabase.update(ContractSqliteConstantsCP.ConstantsSqliteDB.NAME_TABLE, values, selection, selectionArgs);
    }

    //DELETE SQL
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rowDeleted;
        switch (uriMatcher.match(uri)) {
            //delete all tha rows if the uri have not id(#)
            case PESO_PERSONA_URI_INPUT:

                rowDeleted = sqLiteDatabase.delete(ContractSqliteConstantsCP.ConstantsSqliteDB.NAME_TABLE, selection, selectionArgs);
                break;
            //delete the row through his id(#) obtained from the uri
            case PESO_ID_URI_INPUT:

                selection = ContractSqliteConstantsCP.ConstantsSqliteDB._ID + "=?"; //column to compare
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))}; //id to search
                //DELETE FROM NAME_TABLE WHERE selection = selectionArgs;
                rowDeleted = sqLiteDatabase.delete(ContractSqliteConstantsCP.ConstantsSqliteDB.NAME_TABLE, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Elimination not supported for: " + uri);
        }
        context.getContentResolver().notifyChange(uri, null);
    return rowDeleted;
    }

    //for what other apps can use our CP and know their types mime
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        switch (uriMatcher.match(uri)){
            case PESO_PERSONA_URI_INPUT:

                return ContractSqliteConstantsCP.ConstantsSqliteDB.CONTENT_DIR_TYPE; //all register
            case PESO_ID_URI_INPUT:

                return ContractSqliteConstantsCP.ConstantsSqliteDB.CONTENT_ITEM_TYPE; //only register

        default: throw new IllegalArgumentException("UnKnown:" +uri+ "with match" +uriMatcher.match(uri));
        }
    }
}
