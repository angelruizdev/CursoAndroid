package com.example.angelruiz.cursoandroid.RxJavaExercises;

public class ArrayTaskRxJava {

    //vars
    private String nameWorker;
    private String emailWorker;
    private String description;
    private Boolean isComplete;
    private int priority;

    //constructor
    public  ArrayTaskRxJava(String nameWorker, String emailWorker, String description, Boolean isComplete, int priority){
        this.nameWorker = nameWorker;
        this.emailWorker = emailWorker;
        this.description = description;
        this.isComplete = isComplete;
        this.priority = priority;
    }

    //geter and seter
    public String getNameWorker() {
        return nameWorker;
    }

    public void setNameWorker(String nameWorker) {
        this.nameWorker = nameWorker;
    }

    public String getEmailWorker() {
        return emailWorker;
    }

    public void setEmailWorker(String emailWorker) {
        this.emailWorker = emailWorker;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
