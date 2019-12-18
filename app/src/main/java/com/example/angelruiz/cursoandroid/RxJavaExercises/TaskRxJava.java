package com.example.angelruiz.cursoandroid.RxJavaExercises;

import java.util.ArrayList;
import java.util.List;

//list for simulate a service on operators rxjava
public class TaskRxJava {
    public static List<ArrayTaskRxJava> createTaskList(){

        List<ArrayTaskRxJava> taskArray = new ArrayList<>();
            taskArray.add(new ArrayTaskRxJava("angel", "", "Task One", true, 0));
            taskArray.add(new ArrayTaskRxJava("gustavo", "", "Task Two", false, 1));
            taskArray.add(new ArrayTaskRxJava("eneyda", "", "Task tree", true, 0));
            taskArray.add(new ArrayTaskRxJava("eneyda", "", "Task tree", true, 0)); //for operator distinc
            taskArray.add(new ArrayTaskRxJava("roberto", "", "Task four", false, 1));
            taskArray.add(new ArrayTaskRxJava("david", "", "task five", true, 0 )); //for operator takeWhile
            taskArray.add(new ArrayTaskRxJava("amelia", "", "Task six", false, 1));

        return taskArray;
    }
}
