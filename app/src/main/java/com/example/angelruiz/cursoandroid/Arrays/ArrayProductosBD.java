package com.example.angelruiz.cursoandroid.Arrays;

import java.io.Serializable;

public class ArrayProductosBD implements Serializable {//nuestro pojo debe implementar la interface serializable para poder mandar datos de un fragment a otro o de un activity a otro

    private Integer idProducto;//campos a mostrar en el spinner y FragmentRecyProductosBD//por lo general el spinner no lleva adapter ya que no necesita inflarce con una vista
    private String nombreProducto;
    private String precioProducto;
    private Integer fotoProducto;

    public ArrayProductosBD(Integer idProducto, String nombreProducto, String precioProducto, Integer fotoProducto) {//inicializamos los campos con constructor
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.fotoProducto = fotoProducto;
    }

    public ArrayProductosBD(){//constructor vacio para llenarlo con los datos de la consulta

    }

    public Integer getIdProducto() {//getter y setter para asignar y obtener los valores del array que sea de este tipo
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(String precioProducto) {
        this.precioProducto = precioProducto;
    }

    public Integer getFotoProducto() {
        return fotoProducto;
    }

    public void setFotoProducto(Integer fotoProducto) {
        this.fotoProducto = fotoProducto;
    }
}
