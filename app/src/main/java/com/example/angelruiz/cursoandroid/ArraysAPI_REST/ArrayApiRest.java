package com.example.angelruiz.cursoandroid.ArraysAPI_REST;

public final class ArrayApiRest {

    private Integer imagenPokemon;
    private String name, url;

    public Integer getImagenPokemon() {
        String[] urlPartes = url.split("/");

        return Integer.parseInt(urlPartes[urlPartes.length -1]);
    }
    public void setImagenPokemon(Integer imagenPokemon) {
        this.imagenPokemon = imagenPokemon;
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
}
