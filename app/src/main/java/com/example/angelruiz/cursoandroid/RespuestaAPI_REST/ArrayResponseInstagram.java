package com.example.angelruiz.cursoandroid.RespuestaAPI_REST;

import com.example.angelruiz.cursoandroid.Arrays.ArrayInstagramObjects;

import java.util.ArrayList;

public class ArrayResponseInstagram {
    private ArrayList<ArrayInstagramObjects> data; //nombre del array(trae los datos a consumir (pojo)) de la api rest

    public ArrayList<ArrayInstagramObjects> getData() {
        return data;
    }

    public void setData(ArrayList<ArrayInstagramObjects> data) {
        this.data = data;
    }
}
