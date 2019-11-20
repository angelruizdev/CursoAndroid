package com.example.angelruiz.cursoandroid.RxJavaExercises;

public class ArrayTaskRxJava {

    private String description;
    private Boolean isComplet;
    private int priority;

    public  ArrayTaskRxJava(String description, Boolean isComplet, int priority){
        this.description = description;
        this.isComplet = isComplet;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getComplet() {
        return isComplet;
    }

    public void setComplet(Boolean complet) {
        isComplet = complet;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
