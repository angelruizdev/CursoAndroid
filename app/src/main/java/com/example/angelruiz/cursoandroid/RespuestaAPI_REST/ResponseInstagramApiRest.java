package com.example.angelruiz.cursoandroid.RespuestaAPI_REST;

import com.example.angelruiz.cursoandroid.Arrays.ArrayInstagramApiRest;

import java.util.ArrayList;

public class ResponseInstagramApiRest {
    private ArrayList<ArrayInstagramApiRest> data; //nombre del array(trae los datos a consumir) de la api rest

    public ArrayList<ArrayInstagramApiRest> getData() {
        return data;
    }

    public void setData(ArrayList<ArrayInstagramApiRest> data) {
        this.data = data;
    }
}
