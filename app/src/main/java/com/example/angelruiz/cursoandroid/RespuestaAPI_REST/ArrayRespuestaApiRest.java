package com.example.angelruiz.cursoandroid.RespuestaAPI_REST;

import com.example.angelruiz.cursoandroid.ArraysAPI_REST.ArrayWSMysqlApi;

import java.util.ArrayList;

//esta clase recibe, guardara los datos de la API, el array deve tener el mismo nombre del array JSON de la API
public class ArrayRespuestaApiRest {

    //este array es de tipo array de clase ,ArrayWSMysqlApi, ya que recibe los datos del JSON de devueltos por la API REST SERVER
    private ArrayList<ArrayWSMysqlApi> datos;

    //metodos get y set para acceder a sus atributos
    public ArrayList<ArrayWSMysqlApi> getResults() {
        return datos;
    }

    public void setResults(ArrayList<ArrayWSMysqlApi> results) {
        this.datos = results;
    }
}
