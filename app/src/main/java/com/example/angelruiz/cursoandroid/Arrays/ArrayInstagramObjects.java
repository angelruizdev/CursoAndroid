package com.example.angelruiz.cursoandroid.Arrays;

import android.os.Parcel;
import android.os.Parcelable;

//we use interface parceable for pass data to fragmentdetailinstagramapirest, or to other fragment
public class ArrayInstagramObjects implements Parcelable {

    //datos a consumir de la api rest
    private String idUser;
    private String fullNameUser;
    private String imageUrlUser;
    private int imageLikes = 0;

    //constructor inicialize attributes
    public ArrayInstagramObjects(String fullNameUser, String imageUrlUser, int imageLikes){
        this.fullNameUser = fullNameUser;
        this.imageUrlUser = imageUrlUser;
        this.imageLikes = imageLikes;
    }

    //we use this constructor empty
    public ArrayInstagramObjects(){

    }

    //geter and seter
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getFullNameUser() {
        return fullNameUser;
    }

    public void setFullNameUser(String fullNameUser) {
        this.fullNameUser = fullNameUser;
    }

    public String getImageUrlUser() {
        return imageUrlUser;
    }

    public void setImageUrlUser(String imageUrlUser) {
        this.imageUrlUser = imageUrlUser;
    }

    public int getImageLikes() {
        return imageLikes;
    }

    public void setImageLikes(int imageLikes) {
        this.imageLikes = imageLikes;
    }

    //methos implemented for the interface parceable
    @Override
    public int describeContents() { //this method is not very used
        return 0;
    }

    //write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idUser);
        dest.writeString(fullNameUser);
        dest.writeString(imageUrlUser);
        dest.writeInt(imageLikes);
    }

    //constructor save and inicializa the values of entry
    protected ArrayInstagramObjects(Parcel in) {
        idUser = in.readString();
        fullNameUser = in.readString();
        imageUrlUser = in.readString();
        imageLikes = in.readInt();
    }

    //this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Creator<ArrayInstagramObjects> CREATOR = new Creator<ArrayInstagramObjects>() {
        @Override
        public ArrayInstagramObjects createFromParcel(Parcel in) {
            return new ArrayInstagramObjects(in);
        }

        @Override
        public ArrayInstagramObjects[] newArray(int size) {
            return new ArrayInstagramObjects[size];
        }
    };
    //methos implemented for the interface parceable
}
