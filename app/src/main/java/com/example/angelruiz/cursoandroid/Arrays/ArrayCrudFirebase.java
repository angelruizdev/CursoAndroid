package com.example.angelruiz.cursoandroid.Arrays;

//class
public class ArrayCrudFirebase {

    //vars
    private String frbUserId;
    private String frbUserName;
    private String frbUserLastName;
    private String frbUserEmail;
    private String frbUserPassword;

    //builder
    public ArrayCrudFirebase(){

    }

    //geter and seter
    public String getFrbUserId() {
        return frbUserId;
    }

    public void setFrbUserId(String frbUserId) {
        this.frbUserId = frbUserId;
    }

    public String getFrbUserName() {
        return frbUserName;
    }

    public void setFrbUserName(String frbUserName) {
        this.frbUserName = frbUserName;
    }

    public String getFrbUserLastName() {
        return frbUserLastName;
    }

    public void setFrbUserLastName(String frbUserLastName) {
        this.frbUserLastName = frbUserLastName;
    }

    public String getFrbUserEmail() {
        return frbUserEmail;
    }

    public void setFrbUserEmail(String frbUserEmail) {
        this.frbUserEmail = frbUserEmail;
    }

    public String getFrbUserPassword() {
        return frbUserPassword;
    }

    public void setFrbUserPassword(String frbUserPassword) {
        this.frbUserPassword = frbUserPassword;
    }

    /*@NonNull
    public String toString(){
        return frbUserName;
    }*/
}
