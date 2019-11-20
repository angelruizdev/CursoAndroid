package com.example.angelruiz.cursoandroid.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import com.example.angelruiz.cursoandroid.R;

public class FragmentReadContactsCP extends Fragment implements View.OnClickListener{
    Context context;
    View view;
    private TextView tvShowListContacts;
    private Button btShowListContacts;

    public FragmentReadContactsCP() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_read_contacts_cp, container, false);

        tvShowListContacts = view.findViewById(R.id.tvShowListContacts);
        btShowListContacts = view.findViewById(R.id.btShowListContacts);

    return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btShowListContacts.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btShowListContacts:
                 showListContacts();
            break;
        }
    }

    //show the names of the contacts
    private void showListContacts(){
        //use convination de uris for consult the number of contact (CommonDataKinds)
        Uri uriContactsCP = ContactsContract.Contacts.CONTENT_URI;

        String[] fieldsToQuery = {ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.HAS_PHONE_NUMBER};

        CursorLoader cursorLoader = new CursorLoader(context, uriContactsCP, fieldsToQuery, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        //contact have number 1-yes, 0-no
        if (cursor != null && cursor.getCount() > 0){

            while (cursor.moveToNext()){
                String nameContact = cursor.getString(cursor.getColumnIndex(fieldsToQuery[1]));
                String haveNumber = cursor.getString(cursor.getColumnIndex(fieldsToQuery[2]));
                tvShowListContacts.append("\n Nombre: " + nameContact +" | "+ "Con número: " + haveNumber);
            }
            cursor.close();
        }else {
            Toast.makeText(context, "Sin contactos", Toast.LENGTH_SHORT).show();
        }
    }
}
