package com.example.angelruiz.cursoandroid.ArraysAPI_REST;

import com.google.gson.annotations.SerializedName;

//este array de clase, tiene los mismos campos del JSON que devuelve la API, ya que aqui los guardara para mostrarlos en el RV
public class ArrayWSMysqlApi {

    @SerializedName("idPersona") //permite cargar archivos, doc, img etc.., si bienen con otro nombre desde el ws
    private int idPersona;

    @SerializedName("numeroFolio")
    private int numeroFolio;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("profesion")
    private String profesion;

    @SerializedName("imagen")
    private Integer imagen;

    public ArrayWSMysqlApi(int idPersona, int numeroFolio, String nombre, String profesion) {
        this.idPersona = idPersona;
        this.numeroFolio = numeroFolio;
        this.nombre = nombre;
        this.profesion = profesion;
        this.imagen = imagen;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

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
