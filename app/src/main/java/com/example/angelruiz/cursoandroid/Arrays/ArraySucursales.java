package com.example.angelruiz.cursoandroid.Arrays;
//para RecyclerView igual creamos un array de clase con contructor y metodos get y set
public class ArraySucursales {
    private Integer imgSucursal;
    private String nombreSucursal;

    public ArraySucursales(Integer imgSucursal, String nombreSucursal){
        this.imgSucursal=imgSucursal;
        this.nombreSucursal=nombreSucursal;
    }

    public Integer getImgSucursal() {
        return imgSucursal;
    }

    public void setImgSucursal(Integer imgSucursal) {
        this.imgSucursal = imgSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }
}
