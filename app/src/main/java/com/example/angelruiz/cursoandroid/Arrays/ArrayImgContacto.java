package com.example.angelruiz.cursoandroid.Arrays;

import android.os.Parcel;
import android.os.Parcelable;

//we implement the interface Parcelable for pass data to detalleimgcontacto, or other activity
public class ArrayImgContacto implements Parcelable {

    //data to show in grid and activity detailimgcontact
    private int id;
    private int imagen;
    private String nombre;

    //we start theme with constructor
    public ArrayImgContacto(int id, int imagen, String nombre) {
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
    }

    //we create their methods get and set of each attribute
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
    public int describeContents() { //this method is not very used
        return 0;
    }

    //write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(imagen);
        dest.writeString(nombre);
    }

    //constructor save and inicializa the values of entry
    protected ArrayImgContacto(Parcel in) {
        id = in.readInt();
        imagen = in.readInt();
        nombre = in.readString();
    }

    //this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
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
    //methos implemented for the interface parceable
}
