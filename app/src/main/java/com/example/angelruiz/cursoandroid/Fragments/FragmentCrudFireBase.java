package com.example.angelruiz.cursoandroid.Fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angelruiz.cursoandroid.Adapters.AdapterCrudFireBaseRV;
import com.example.angelruiz.cursoandroid.Arrays.ArrayCrudFirebase;
import com.example.angelruiz.cursoandroid.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//class
public class FragmentCrudFireBase extends Fragment {

    //vars
    private final static String TAG_ARRAY_FIREBASE = "TAG_ARRAY_FIREBASE";
    private Context context;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String userName, userLastName, userEmail, userPassword;
    private List<ArrayCrudFirebase> arrayCrudFirebaseList;
    private AdapterCrudFireBaseRV adapterCrudFireBaseRV;
    private ArrayCrudFirebase arrayCrudItemSelected;


    //views
    View view;
    private Toolbar toolbarCustom;
    private EditText etUserNameFrb, etUserLastNameFrb, etUserEmailFrb, etUserPasswordFrb;
    private RecyclerView rvShowUsersFrb;

    //builder
    public FragmentCrudFireBase() {
        // Required empty public constructor
    }

    //inicialize objects and vars
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        arrayCrudFirebases = new ArrayList<>();
        inicializeFireBase();
        showUserData();
    }

    //connection to firebase database
    private void inicializeFireBase(){
        FirebaseApp.initializeApp(context);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true); not call here the percsistence of data
        databaseReference = firebaseDatabase.getReference();
    }

    //show data from fire base in RV
    private void showUserData() {
        databaseReference.child("UserData").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayCrudFirebases.clear();

                for (DataSnapshot objectDataSnapshot : dataSnapshot.getChildren()) {
                    ArrayCrudFirebase arrayCrudFirebase = objectDataSnapshot.getValue(ArrayCrudFirebase.class);
                    arrayCrudFirebases.add(arrayCrudFirebase);

                    adapterCrudFireBaseRV = new AdapterCrudFireBaseRV(context, arrayCrudFirebases);
                    rvShowUsersFrb.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                    rvShowUsersFrb.setAdapter(adapterCrudFireBaseRV);
                }

                /*ckeck if arrayCrudFirebases has data
                for (ArrayCrudFirebase x: arrayCrudFirebases) {
                    Log.i(TAG_ARRAY_FIREBASE, x.getFrbUserName());
                }

                for (int i = 0; i < arrayCrudFirebases.size(); i++) {
                    Log.i(TAG_ARRAY_FIREBASE, arrayCrudFirebases.get(i).getFrbUserName());
                }*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //inicialize views
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_crud_fire_base, container, false);

        toolbarCustom = view.findViewById(R.id.actionbar_custom_frb);
        setToolBarFragment();
        etUserNameFrb = view.findViewById(R.id.et_user_name_frb);
        etUserLastNameFrb = view.findViewById(R.id.et_user_lastname_frb);
        etUserEmailFrb = view.findViewById(R.id.et_user_email_frb);
        etUserPasswordFrb = view.findViewById(R.id.et_user_password_frb);
        rvShowUsersFrb = view.findViewById(R.id.rv_show_users_frb);

        return view;
    }

    //initialization final
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    //toolbar custom menu for this fmt
    private void setToolBarFragment(){
        setHasOptionsMenu(true);
        toolbarCustom.inflateMenu(R.menu.menu_crud_firebase);
        AppCompatActivity activity = (AppCompatActivity) getActivity();

        if(activity != null){
           activity.setSupportActionBar(toolbarCustom);

           if (activity.getSupportActionBar() != null){
               activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
               activity.getSupportActionBar().setTitle("Fire base");
           }
        }
    }

    //inflate the menu of the toolbar
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        MenuItem settingsItem = menu.findItem(R.id.action_settings);
        //settingsItem.setVisible(false); remove 3 dots of toolbar
        menuInflater.inflate(R.menu.menu_crud_firebase, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    //events to each item of the menu toolbar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        userName = etUserNameFrb.getText().toString();
        userLastName = etUserLastNameFrb.getText().toString();
        userEmail = etUserEmailFrb.getText().toString();
        userPassword = etUserPasswordFrb.getText().toString();

        switch(item.getItemId()){
            case R.id.menu_save_firebase:
                saveDataFireBase(userName, userLastName, userEmail, userPassword);
                break;

            case R.id.menu_update_firebase:
                updateDataFireBase();
                break;

            case R.id.menu_delete_firebase:
                deleteDataFireBase();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //save register in db firebase
    private void saveDataFireBase(String userName, String userLastName, String userEmail, String userPassword) {

        if (userName.isEmpty() || userLastName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()) {
            Toast.makeText(context, "Faltan datos", Toast.LENGTH_SHORT).show();
        } else {

            ArrayCrudFirebase arrayCrudFirebase = new ArrayCrudFirebase();
            arrayCrudFirebase.setFrbUserId(UUID.randomUUID().toString());
            arrayCrudFirebase.setFrbUserName(userName);
            arrayCrudFirebase.setFrbUserLastName(userLastName);
            arrayCrudFirebase.setFrbUserEmail(userEmail);
            arrayCrudFirebase.setFrbUserPassword(userPassword);

            databaseReference.child("UserData").child(arrayCrudFirebase.getFrbUserId()).setValue(arrayCrudFirebase);
            Toast.makeText(context, "Usuario guardado", Toast.LENGTH_SHORT).show();
            cleanEditText();
        }
    }

    //update register in db firebase
    private void updateDataFireBase() {
        Toast.makeText(context, "update", Toast.LENGTH_SHORT).show();
    }

    //delete register in db firebase
    private void deleteDataFireBase() {
        Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show();
    }

    //clean the box text
    private void cleanEditText() {
        etUserNameFrb.setText("");
        etUserLastNameFrb.setText("");
        etUserEmailFrb.setText("");
        etUserPasswordFrb.setText("");
    }

    //destroy objects
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
