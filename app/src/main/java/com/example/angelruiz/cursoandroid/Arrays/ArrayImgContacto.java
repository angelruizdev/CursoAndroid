package com.example.angelruiz.cursoandroid.Arrays;

import java.io.Serializable;
//podemos implementar la interface Serializable para mandar datos a otra actividad
//estos son los datos que se mostraran en el list view
public class ArrayImgContacto implements Serializable{
    private Integer id;
    private Integer imagen;
    private String nombre;
//los inicializamos con constructor
    public ArrayImgContacto(Integer id, Integer imagen, String nombre) {
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
    }
//creamos sus métodos get y set de cada atributo
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
