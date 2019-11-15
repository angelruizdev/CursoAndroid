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
    private Uri receiveDataUri;
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
        //recover data sent from FragmentWeightCPBNV
        Bundle receiveDateWeight = this.getArguments();

        if (receiveDateWeight != null){
            //we parse the uri obtained of FragmentWeightCPBNV
            receiveDataUri = Uri.parse(getArguments().getString("passDateUri"));
        }else {
            Toast.makeText(context, "Peso no encontrada", Toast.LENGTH_SHORT).show();
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

    //load raws for update and delete in db CP
    private void loadDataShow(){
       //columns to load and query in cursor loader
       final String[] projection = {ContractSqliteConstantsCP.ConstantsSqliteDB._ID,
                                    ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_PESO,
                                    ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_DATE};

       //load the data to the CP with cursor loader in background
       final CursorLoader cursorLoader = new CursorLoader(context, receiveDataUri, projection, null, null, null);
       final Cursor cursor = cursorLoader.loadInBackground();

       //columns to show
       int weightIdx, dateIdx;
       String valueWeightIdx, valueDateIdx;

       if (cursor != null){
           //itera in the raws with cursor
           while (cursor.moveToNext()){
               //access to the index of the column
               weightIdx = cursor.getColumnIndex(ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_PESO);
               dateIdx = cursor.getColumnIndex(ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_DATE);
               //with the index access to the regiter to the column
               valueWeightIdx = cursor.getString(weightIdx);
               valueDateIdx = cursor.getString(dateIdx);
               //show the register selected in the viewa
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
            //update the raw selected of the column weight
            case R.id.btUpdateWeight:
                ContentValues values = new ContentValues();
                values.put(ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_PESO, etReceiveWeight.getText().toString());

                context.getContentResolver().update(receiveDataUri, values, null, null);
                fragmentManager.popBackStack(); //close this fmt and show the previus
                break;

            //delete the raw selected of the column weight
            case R.id.btDeleteWeight:
                context.getContentResolver().delete(receiveDataUri, null, null);
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
