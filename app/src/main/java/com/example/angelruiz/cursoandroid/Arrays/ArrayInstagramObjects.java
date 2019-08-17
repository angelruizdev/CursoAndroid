package com.example.angelruiz.cursoandroid.Arrays;

public class ArrayInstagramObjects { //datos a consumir de la api rest
    private String idUser;
    private String fullNameUser;
    private String imageUrlUser;
    private int imageLikes = 0;

    public ArrayInstagramObjects(String fullNameUser, String imageUrlUser, int imageLikes){
        this.fullNameUser = fullNameUser;
        this.imageUrlUser = imageUrlUser;
        this.imageLikes = imageLikes;
    }

    public ArrayInstagramObjects(){

    }

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
}
