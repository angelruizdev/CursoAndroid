package com.example.angelruiz.cursoandroid.PersistenceDataFireBase;

import com.google.firebase.database.FirebaseDatabase;

//this calss allows save data from app without be connected to internet
public class PersistenceDataFrb extends android.app.Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //once connecting to the internet, the data will be sent to firebase db
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
