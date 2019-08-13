package com.example.angelruiz.cursoandroid.Arrays;

public class ArrayInstagramObjects { //datos a consumir de la api rest
    private String idUser;
    private String fullNameUser;
    private String imageUser;
    private int imageLikes = 0;

    public ArrayInstagramObjects(String fullNameUser, String imageUser, int imageLikes){
        this.fullNameUser = fullNameUser;
        this.imageUser = imageUser;
        this.imageLikes = imageLikes;
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
    public String getImageUser() {
        return imageUser;
    }
    public void setImageUser(String imageUser) {
        this.imageUser = imageUser;
    }

    public int getImageLikes() {
        return imageLikes;
    }

    public void setImageLikes(int imageLikes) {
        this.imageLikes = imageLikes;
    }
}
