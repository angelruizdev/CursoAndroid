package com.example.angelruiz.cursoandroid.Arrays;

public class ArrayFragmentRecyProdBd {

    private String detalleProductos;
    private Integer imagenLike;
    private int numeroLikes;

    public ArrayFragmentRecyProdBd(String detalleProductos, Integer imagenLike, int numeroLikes) {
        this.detalleProductos = detalleProductos;
        this.imagenLike = imagenLike;
        this.numeroLikes = numeroLikes;
    }

    public String getDetalleProductos() {
        return detalleProductos;
    }

    public void setDetalleProductos(String detalleProductos) {
        this.detalleProductos = detalleProductos;
    }

    public Integer getImagenLike() {
        return imagenLike;
    }

    public void setImagenLike(Integer iagenLike) {
        this.imagenLike = iagenLike;
    }

    public int getNumeroLikes() {
        return numeroLikes;
    }

    public void setNumeroLikes(int numeroLikes) {
        this.numeroLikes = numeroLikes;
    }
}
