package com.example.angelruiz.cursoandroid.RxJavaExercises;

import java.util.ArrayList;
import java.util.List;

public class TaskRxJava {
    public static List<ArrayTaskRxJava> createTaskList(){

        List<ArrayTaskRxJava> taskArray = new ArrayList<>();
        taskArray.add(new ArrayTaskRxJava("Task One", true, 1));
        taskArray.add(new ArrayTaskRxJava("Task Two", false, 0));
        taskArray.add(new ArrayTaskRxJava("Task tree", true, 1));

    return taskArray;
    }
}