package com.example.angelruiz.cursoandroid.Fragments;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.angelruiz.cursoandroid.R;

import static android.Manifest.permission.READ_CALL_LOG;
import static android.Manifest.permission.WRITE_CALL_LOG;

//CP: content provider
//Example using CP calls, for show the register of calls
public class FragmentCallsContentPBNV extends Fragment {

private static final int CODE_REQUEST_PERMISSION = 1;
 Context context;
 View view;
 private Button btShowRegisterCall;
 private TextView tvShowLogRegisters;
 Activity activity;

    public FragmentCallsContentPBNV() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();
        activity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      view = inflater.inflate(R.layout.fragment_calls_contentpbnv, container, false);

      btShowRegisterCall = view.findViewById(R.id.btShowRegisterCall);
      tvShowLogRegisters = view.findViewById(R.id.tvShowLogRegisters);

      return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btShowRegisterCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if (requestPermissions()){
                   consultCPCalls();
               }
            }
        });
    }

    //request permission for read and write calls
    private boolean requestPermissions(){

       if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
           return true;

       //if permissions are accepted return true and continue with CP
       }else if(context.checkSelfPermission(READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED &&
                context.checkSelfPermission(WRITE_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
           return true;

       //if anyone of the two permissions is denied, show a explication
       }else if(shouldShowRequestPermissionRationale(READ_CALL_LOG) || shouldShowRequestPermissionRationale(WRITE_CALL_LOG)){

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setIcon(R.drawable.phone);
                alertDialog.setTitle("¡Permisos necesarios!");
                alertDialog.setMessage("Activar para su optimo funcionamiento.");
                alertDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    //if accept explication, show a window for accepted the permission completely
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestPermissions(new String[]{READ_CALL_LOG, WRITE_CALL_LOG}, CODE_REQUEST_PERMISSION);
                    }
                });

               alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                   dialog.dismiss();
               }
           });
           alertDialog.show();
       }
    return false; //if permissions are not accepted return false and not continue with CP
    }

    //receibe the callback of the permission if they are accpted, for do something
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
           case CODE_REQUEST_PERMISSION:
             if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                 Toast.makeText(context, "¡Permisos aceptados!", Toast.LENGTH_SHORT).show();
                 Log.i("permission_accepted", "permisos aceptados");
             }
           break;
           //we can create more cases, for manages other permissions
        }
    }

    //do the query for bring the log the calls | (1)
    private void consultCPCalls(){

        //name table calls log
        Uri directionUriCalls = CallLog.Calls.CONTENT_URI;

        //fields(projection) to show to the CP table calls
        String[] fieldsQuery = {CallLog.Calls.NUMBER, CallLog.Calls.DATE, CallLog.Calls.TYPE, CallLog.Calls.DURATION};

        //this object allows execute querys CRUD in the table directionUriCalls CP
        ContentResolver contentResolver = context.getContentResolver();

        //CursorLoader cursorLoader = new CursorLoader(context, directionUriCalls, fieldsQuery, null, null, null);
        //Cursor cursor = cursorLoader.loadInBackground();
        Cursor cursor = contentResolver.query(directionUriCalls, fieldsQuery, null, null, null);
        if(cursor != null){
            while (cursor.moveToNext()){
                String number = cursor.getString(cursor.getColumnIndex(fieldsQuery[0]));
                long date = cursor.getLong(cursor.getColumnIndex(fieldsQuery[1]));
                int type = cursor.getInt(cursor.getColumnIndex(fieldsQuery[2]));
                String duration = cursor.getString(cursor.getColumnIndex(fieldsQuery[3]));
                String typeCall = "";

                switch (type){
                    case CallLog.Calls.INCOMING_TYPE:
                        typeCall = "entrada";
                        break;

                    case CallLog.Calls.MISSED_TYPE:
                        typeCall = "perdida";
                        break;

                    case CallLog.Calls.OUTGOING_TYPE:
                        typeCall = "salida";
                        break;
                    default: typeCall = "Desconocido";
                }

                String detail = "Número:" + number +"."+
                                "Fecha:" + DateFormat.format("dd/MM/yy h:mm a", date) +"."+
                                "Tipo:" + typeCall +"."+
                                "Duración:" + duration + "s.";

                tvShowLogRegisters.append(".\n" + detail);
            }
                cursor.close();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
/*(1)
content://call_log/calls --> Uri original
content:// :scheme especify Uri of content
call_log/ :provider authority(owner of the CP)
/calls :name of the table, wath saves the call log*/

/*Queries the data and returns results
cursor = getContentResolver().query(
    UserDictionary.Words.CONTENT_URI, // The content URI, name table
    projection,                       // The columns to return for each row
    selectionClause,                  // Selection criteria
    selectionArgs,                    // Selection criteria
    sortOrder);                       // The sort order for the returned rows*/
