package com.example.angelruiz.cursoandroid.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.angelruiz.cursoandroid.R;

import static android.Manifest.permission.READ_CALL_LOG;
import static android.Manifest.permission.WRITE_CALL_LOG;
import static androidx.core.content.ContextCompat.checkSelfPermission;


public class FragmentContactsCPBNV extends Fragment {
 private static final int CODE_REQUEST_PERMISSION = 1;
 //CP: content provider
 Context context;
 View view;
 Button btShowRegisterCall;
 TextView tvShowLogRegisters;
 Activity activity;

    public FragmentContactsCPBNV() {
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
      view = inflater.inflate(R.layout.fragment_contacts_cpbnv, container, false);

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

                if (checkStatusPermission()){
                    tvShowLogRegisters.setText("muy bien");
                    consultCPCalls();
                }else {
                    tvShowLogRegisters.setText("muy mal");
                    requestPermissions();
                }
            }
        });
    }

    //request permission for read and write calls
    private void requestPermissions1(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(context, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED
               && checkSelfPermission(context, Manifest.permission.WRITE_CALL_LOG) != PackageManager.PERMISSION_GRANTED){

                if (shouldShowRequestPermissionRationale(READ_CALL_LOG) || shouldShowRequestPermissionRationale(WRITE_CALL_LOG)){

                }
            }else {
                Toast.makeText(context, "¡Permisos ya aceptados!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //---->pending finish...

    //request permission for read and write calls
    private void requestPermissions(){

        boolean permissionReadLogCalls = ActivityCompat.shouldShowRequestPermissionRationale(activity, READ_CALL_LOG);
        boolean permissionWriteLogCalls = ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_CALL_LOG);

        if (permissionReadLogCalls && permissionWriteLogCalls){
            Toast.makeText(context, "Permisos aceptados", Toast.LENGTH_SHORT).show();
        }else {
            //if the permissions are not accepted again ask for
            ActivityCompat.requestPermissions(activity, new String[]{READ_CALL_LOG, Manifest.permission.WRITE_CALL_LOG}, CODE_REQUEST_PERMISSION);
        }
    }

    //check if permission are accepted
    private boolean checkStatusPermission(){

        boolean permissionReadCallLogs = checkSelfPermission(context, READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED;
        boolean permissionWriteCallLogs = checkSelfPermission(context, Manifest.permission.WRITE_CALL_LOG) == PackageManager.PERMISSION_GRANTED;

          if (permissionReadCallLogs && permissionWriteCallLogs){
             return true;
          }else {
             return false;
          }
    }

    //receibe the callback of the permission if is accpted, for do something
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case CODE_REQUEST_PERMISSION:

              if (checkStatusPermission()){
                  Toast.makeText(context, "¡Permisos ya aceptados!", Toast.LENGTH_SHORT).show();
                  consultCPCalls();
              }else {
                  Toast.makeText(context, "¡Permisos no han sido aceptados!", Toast.LENGTH_SHORT).show();
              }
            break;
        }
    }

    //do the query for bring the log the calls
    private void consultCPCalls(){

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
