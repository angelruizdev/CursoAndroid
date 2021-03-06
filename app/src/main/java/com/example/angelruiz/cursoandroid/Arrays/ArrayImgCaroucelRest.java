package com.example.angelruiz.cursoandroid.Arrays;

public class ArrayImgCaroucelRest {

    private String name, url;
    private Integer numberImage;

    public ArrayImgCaroucelRest(String name, String url, Integer numberImage) {
        this.name = name;
        this.url = url;
        this.numberImage = numberImage;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Integer getNumberImage() {
        String[] urlParts = url.split("/");
        numberImage = Integer.parseInt(urlParts[urlParts.length -1]); //accedemos a ultima pos del array urlParts que es el num de img
        return numberImage;
    }
    public void setNumberImage(Integer numberImage) {
        this.numberImage = numberImage;
    }
}
