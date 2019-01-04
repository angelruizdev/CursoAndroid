package com.example.angelruiz.cursoandroid.Arrays;

public class ArrayAgenda {
    private Integer imgPersona;
    private String nombrePersona;
    private String telefonoPersona;

    public ArrayAgenda(Integer imgPersona, String nombrePersona, String telefonoPersona){
      this.imgPersona=imgPersona;
      this.nombrePersona=nombrePersona;
      this.telefonoPersona=telefonoPersona;
    }

    public Integer getImgPersona() {
        return imgPersona;
    }

    public void setImgPersona(Integer imgPersona) {
        this.imgPersona = imgPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getTelefonoPersona() {
        return telefonoPersona;
    }

    public void setTelefonoPersona(String telefonoPersona) {
        this.telefonoPersona = telefonoPersona;
    }
}
