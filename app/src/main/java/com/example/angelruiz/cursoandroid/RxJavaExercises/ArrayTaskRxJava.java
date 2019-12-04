package com.example.angelruiz.cursoandroid.RxJavaExercises;

public class ArrayTaskRxJava {

    private String description;
    private Boolean isComplete;
    private int priority;

    public  ArrayTaskRxJava(String description, Boolean isComplete, int priority){
        this.description = description;
        this.isComplete = isComplete;
        this.priority = priority;
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
