package com.example.angelruiz.cursoandroid.Fragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.example.angelruiz.cursoandroid.CustomContentPovider.ModelContentProvider.ContractSqliteConstantsCP;
import com.example.angelruiz.cursoandroid.R;
//the objects comented were to access to the db directly without use CP
public class FragmentWeightCPBNV extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    View view;
    private EditText etWeigthEnter;
    private TextView tvShowResults;
    private Button btSaveRegisterCP;
    //private DataBaseCPOpnHpr connection; we coment this object for use Cursor and content resolver for access to the data
    Context context;
    private String dateCurrent;
    //private SQLiteDatabase sqLiteDatabase;

    public FragmentWeightCPBNV() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        //connection = new DataBaseCPOpnHpr(context, ContractSqliteConstantsCP.ConstantsSqliteDB.NAME_DATABASE_CP, null, ContractSqliteConstantsCP.ConstantsSqliteDB.VERSION_DATABASE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     view = inflater.inflate(R.layout.fragment_weight_cpbnv, container, false);

     etWeigthEnter = view.findViewById(R.id.etWeightEnter);
     tvShowResults = view.findViewById(R.id.tvShowResults);
     btSaveRegisterCP = view.findViewById(R.id.btSaveRegisterCP);

     return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btSaveRegisterCP.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                saveInfoWeight();
                showInfoWeight();
            }
        });
    }

    //obtain date for save register
    @RequiresApi(api = Build.VERSION_CODES.N)
    private String obtainDateDevice(){

        //object calendar for obtain hour and date current with this method(getInstance())
        Calendar calendar = Calendar.getInstance();
        //object format for especific the format the date
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd - h:mm a");
        //we format the pattern of date obtained to a string
        String obtainDateCurrent = simpleDateFormat.format(calendar.getTime());

        return obtainDateCurrent;
    }

    //insert data in Sqlite CP
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void saveInfoWeight() {
        //sqLiteDatabase = connection.getWritableDatabase();

        String peso = etWeigthEnter.getText().toString();
        dateCurrent = obtainDateDevice();

        if (!etWeigthEnter.getText().toString().equals("")){
           ContentValues values = new ContentValues();
           values.put(ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_PESO, peso);
           values.put(ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_DATE, dateCurrent);

           //sqLiteDatabase.insert(ContractSqliteConstantsCP.ConstantsSqliteDB.NAME_TABLE, null, values);
           //sqLiteDatabase.close();

           //this object its communicate with method insert of WeightProvider
           Uri newUriInsert = context.getContentResolver().insert(ContractSqliteConstantsCP.ConstantsSqliteDB.CONTENT_URI, values);
           etWeigthEnter.setText("");
        }
    }

    //show registers of Sqlite CP
    private void showInfoWeight(){
        //sqLiteDatabase = connection.getReadableDatabase();
        //Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + ContractSqliteConstantsCP.ConstantsSqliteDB.NAME_TABLE, null);
        String[] projection = {ContractSqliteConstantsCP.ConstantsSqliteDB._ID,
                               ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_PESO,
                               ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_DATE};

        //this object its communicate with method query of WeightProvider
        Cursor cursor = context.getContentResolver().query(ContractSqliteConstantsCP.ConstantsSqliteDB.CONTENT_URI, projection, null, null, null);
        String idPerson, weight, date;

        if (cursor != null){
            while (cursor.moveToNext()){

                idPerson = cursor.getString(0);
                weight = cursor.getString(1);
                date = cursor.getString(2);

                tvShowResults.append("\n" + "number of raws: "+ cursor.getCount() +"\n" + idPerson +"-"+ weight +"-"+ date );
            }
            cursor.close();
        }else {
            Log.i("cursor", "cursor null");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
