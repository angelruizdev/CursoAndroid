package com.example.angelruiz.cursoandroid.Fragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.angelruiz.cursoandroid.R;
import com.example.angelruiz.cursoandroid.CustomContentPovider.ModelContentProvider.ContractSqliteConstantsCP;
import com.example.angelruiz.cursoandroid.CustomContentPovider.ModelContentProvider.DataBaseCPOpnHpr;

public class FragmentWeightCPBNV extends Fragment {
    View view;
    private EditText etWeigthEnter;
    private TextView tvShowResults;
    private Button btSaveRegisterCP;
    private DataBaseCPOpnHpr connection;
    Context context;
    private String dateCurrent;
    private SQLiteDatabase sqLiteDatabase;

    public FragmentWeightCPBNV() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        connection = new DataBaseCPOpnHpr(context, ContractSqliteConstantsCP.ConstantsSqliteDB.NAME_DATABASE_CP, null, ContractSqliteConstantsCP.ConstantsSqliteDB.VERSION_DATABASE);
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void saveInfoWeight() {
        sqLiteDatabase = connection.getWritableDatabase();

        String peso = etWeigthEnter.getText().toString();
        dateCurrent = obtainDateDevice();

        if (!etWeigthEnter.getText().toString().equals("")){
           ContentValues values = new ContentValues();
           values.put(ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_PESO, peso);
           values.put(ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_DATE, dateCurrent);

           sqLiteDatabase.insert(ContractSqliteConstantsCP.ConstantsSqliteDB.NAME_TABLE, null, values);
           etWeigthEnter.setText("");
           sqLiteDatabase.close();
        }
    }

    private void showInfoWeight(){
        sqLiteDatabase = connection.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + ContractSqliteConstantsCP.ConstantsSqliteDB.NAME_TABLE, null);
          if (cursor != null){
              while (cursor.moveToNext()){
                  String w = cursor.getString(1);
                  String d = cursor.getString(2);
                  tvShowResults.setText("number of raws: " + cursor.getCount() + "---" + w +"-"+ d );
              }
              cursor.close();
          }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
