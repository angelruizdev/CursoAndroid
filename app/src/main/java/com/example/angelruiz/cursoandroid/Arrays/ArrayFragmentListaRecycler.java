package com.example.angelruiz.cursoandroid.Arrays;

import java.io.Serializable;
public class ArrayFragmentListaRecycler implements Serializable {
  private Integer imgPersona;
  private String nomPersona;
    public ArrayFragmentListaRecycler(Integer imgPersona, String nomPersona) {
        this.imgPersona = imgPersona;
        this.nomPersona = nomPersona;
    }
public  ArrayFragmentListaRecycler(){

}
    public Integer getImgPersona() {
        return imgPersona;
    }

    public void setImgPersona(Integer imgPersona) {
        this.imgPersona = imgPersona;
    }

    public String getNomPersona() {
        return nomPersona;
    }

    public void setNomPersona(String nomPersona) {
        this.nomPersona = nomPersona;
    }



}
