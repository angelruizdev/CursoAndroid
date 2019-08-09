package com.example.angelruiz.cursoandroid.Arrays;

public class ArrayInstagramApiRest {

    private Integer imageUser;
    private int imageLike;

    public ArrayInstagramApiRest(Integer imageUser, int imageLike){
        this.imageUser = imageUser;
        this.imageLike = imageLike;
    }

    public Integer getImageUser() {
        return imageUser;
    }

    public void setImageUser(Integer imageUser) {
        this.imageUser = imageUser;
    }

    public int getImageLike() {
        return imageLike;
    }

    public void setImageLike(int imageLike) {
        this.imageLike = imageLike;
    }
}
