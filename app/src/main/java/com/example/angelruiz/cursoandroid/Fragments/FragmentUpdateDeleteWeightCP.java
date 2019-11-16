package com.example.angelruiz.cursoandroid.Fragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.loader.content.CursorLoader;

import com.example.angelruiz.cursoandroid.CustomContentPovider.ModelContentProvider.ContractSqliteConstantsCP;
import com.example.angelruiz.cursoandroid.R;

public class FragmentUpdateDeleteWeightCP extends Fragment implements View.OnClickListener{
    Context context;
    View view;
    private TextView tvReceiveDate;
    private EditText etReceiveWeight;
    private Uri receiveDataUriId; //uri with id
    private Button btUpdateWeight, btDeleteWeight;
    private FragmentManager fragmentManager;

    public FragmentUpdateDeleteWeightCP() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();
        fragmentManager = getFragmentManager();
        //recover data(uri) sent from FragmentWeightCPBNV, with id of the row selected whe do click in item of LV
        Bundle receiveDataWeightId = this.getArguments();

        if (receiveDataWeightId != null){
            //we parse the uri obtained of FragmentWeightCPBNV
            receiveDataUriId = Uri.parse(getArguments().getString("passDataUriId"));
        }else {
            Toast.makeText(context, "Peso no encontrado", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_update_delete_weight_cp, container, false);

        tvReceiveDate = view.findViewById(R.id.tvReceiveDate);
        etReceiveWeight = view.findViewById(R.id.etReceiveWeight);
        btUpdateWeight = view.findViewById(R.id.btUpdateWeight);
        btDeleteWeight = view.findViewById(R.id.btDeleteWeight);

        //created the views we call to loadDataShow()
        loadDataShow();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btUpdateWeight.setOnClickListener(this);
        btDeleteWeight.setOnClickListener(this);
    }

    //load rows for update and delete in db CP
    private void loadDataShow(){
       //columns to load and query in cursor loader
       final String[] projection = {ContractSqliteConstantsCP.ConstantsSqliteDB._ID,
                                    ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_PESO,
                                    ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_DATE};

       //load the data of the CP in base of the id what bring receiveDataUriId, with cursor loader in background
       final CursorLoader cursorLoader = new CursorLoader(context, receiveDataUriId, projection, null, null, null);
       final Cursor cursor = cursorLoader.loadInBackground();

       //columns to show
       int weightIdx, dateIdx;
       String valueWeightIdx, valueDateIdx;

       if (cursor != null){
           //itera in the rows with cursor
           while (cursor.moveToNext()){
               //access to the index of the column
               weightIdx = cursor.getColumnIndex(ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_PESO);
               dateIdx = cursor.getColumnIndex(ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_DATE);
               //with the index access to the regiter to the column
               valueWeightIdx = cursor.getString(weightIdx);
               valueDateIdx = cursor.getString(dateIdx);
               //show the register selected in the views
               etReceiveWeight.setText(valueWeightIdx);
               tvReceiveDate.setText(valueDateIdx);
           }
           cursor.close();
       }
    }

    //onClick to buttons update and delete
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            //update the column peso of the row with the id that brings the uri receiveDataUriId with what has etReceiveWeight
            case R.id.btUpdateWeight:
                ContentValues values = new ContentValues();
                values.put(ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_PESO, etReceiveWeight.getText().toString());

                //update the column peso with base to the id of the row
                //UPDATE CONTENT_URI SET COLUMN_PESO = 'etReceiveWeight' WHERE _ID = 'receiveDataUriId'";
                context.getContentResolver().update(receiveDataUriId, values, null, null);
                fragmentManager.popBackStack(); //close this fmt and show the previus
                break;

            //delete the row selected in base to the id obtained of receiveDataUriId through column peso
            case R.id.btDeleteWeight:
                //DELETE FROM CONTENT_URI WHERE _ID = receiveDataUriId;
                //context.getContentResolver().delete(receiveDataUriId, ContractSqliteConstantsCP.ConstantsSqliteDB._ID, new String[]{String.valueOf(receiveDataUriId)});
                context.getContentResolver().delete(receiveDataUriId, null, null);
                fragmentManager.popBackStack();
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
