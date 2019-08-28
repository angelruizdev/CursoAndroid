package com.example.angelruiz.cursoandroid.Arrays;

import android.os.Parcel;
import android.os.Parcelable;

//podemos implementar la interface Parcelable para pasar datos a otra actividad
public class ArrayImgContacto implements Parcelable {

    //estos son los datos que se mostraran en el list view
    private int id;
    private int imagen;
    private String nombre;

    //los inicializamos con constructor
    public ArrayImgContacto(int id, int imagen, String nombre) {
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
    }

    //creamos sus métodos get y set de cada atributo
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //methods implemented by the interface Parceable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(imagen);
        dest.writeString(nombre);
    }

    protected ArrayImgContacto(Parcel in) {
        id = in.readInt();
        imagen = in.readInt();
        nombre = in.readString();
    }

    public static final Creator<ArrayImgContacto> CREATOR = new Creator<ArrayImgContacto>() {
        @Override
        public ArrayImgContacto createFromParcel(Parcel in) {
            return new ArrayImgContacto(in);
        }

        @Override
        public ArrayImgContacto[] newArray(int size) {
            return new ArrayImgContacto[size];
        }
    };
}
