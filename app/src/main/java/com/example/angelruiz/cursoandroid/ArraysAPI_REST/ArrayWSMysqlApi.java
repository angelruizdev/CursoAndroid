package com.example.angelruiz.cursoandroid.ArraysAPI_REST;

import com.google.gson.annotations.SerializedName;

public class ArrayWSMysqlApi {//este array de clase, tiene los mismos campos del JSON que devuelve la API, ya que aqui los guardara para mostrarlos en el RV

    private int numeroFolio;
    private String nombre;
    @SerializedName("profesion")
    private String profesion;
    @SerializedName("imagen")//permite cargar archivos, doc, img etc..
    private Integer imagen;

    public int getNumeroFolio() {//creamos get y set de cada uno para acceder a ellos desde el adapterRV
        return numeroFolio;
    }

    public void setNumeroFolio(int numeroFolio) {
        this.numeroFolio = numeroFolio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }
}
