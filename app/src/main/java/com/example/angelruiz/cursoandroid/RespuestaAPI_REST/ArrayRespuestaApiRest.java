package com.example.angelruiz.cursoandroid.RespuestaAPI_REST;

import com.example.angelruiz.cursoandroid.ArraysAPI_REST.ArrayWSMysqlApi;

import java.util.ArrayList;

public class ArrayRespuestaApiRest { //esta clase recibe, guardara los datos de la API, el array deve tener el mismo nombre del array JSON de la API
    private ArrayList<ArrayWSMysqlApi> datos; //este array es de tipo array de clase ,ArrayWSMysqlApi, ya que recibe los datos del JSON de devueltos por la API REST SERVER

    public ArrayList<ArrayWSMysqlApi> getResults() { //metodos get y set para acceder a sus atributos
        return datos;
    }

    public void setResults(ArrayList<ArrayWSMysqlApi> results) {
        this.datos = results;
    }
}
