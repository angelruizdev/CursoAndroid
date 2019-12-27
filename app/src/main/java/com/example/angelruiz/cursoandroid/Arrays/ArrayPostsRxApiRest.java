package com.example.angelruiz.cursoandroid.Arrays;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
//La anotación @SerializedName es necesaria para que Gson mapee las llaves JSON a campos de objeto Java.
//La anotación @Expose indica que el miembro de la clase debería ser expuesto para serialización o deserialización JSON.

//class / structure of the json -> end point posts/id/coments
public class ArrayPostsRxApiRest {

    //vars
    @SerializedName("userId")
    @Expose
    private int userId;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;

    //builder
    public ArrayPostsRxApiRest(int userId, int id, String title, String body){
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    //geter and seter
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    //comverts the values json to string
    @NonNull
    @Override
    public String toString() {
        return "ArrayPostsRxApiRest{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
