package com.example.angelruiz.cursoandroid.RxJavaExercises;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ExercisesRxJava {

public static class TaskRxJava{

    public static List<ArrayTaskRxJava> createTaskList(){

        List<ArrayTaskRxJava> taskArray = new ArrayList<>();
        taskArray.add(new ArrayTaskRxJava("One", false, 0));
        taskArray.add(new ArrayTaskRxJava("Two", true, 1));
        taskArray.add(new ArrayTaskRxJava("tree", false, 0));

    return taskArray;
    }
}

    @SuppressLint("CheckResult")
    public static void main(String[] args){
        final String TAG_RX_JAVA = "TAG_RX_JAVA";
        Observable<ArrayTaskRxJava> taskObservable = Observable
                                    .fromIterable(TaskRxJava.createTaskList())
                                    .subscribeOn(Schedulers.io())
                                    .subscribeOn(AndroidSchedulers.mainThread());//check tomorrow

        taskObservable.subscribe(new Observer<ArrayTaskRxJava>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG_RX_JAVA, "onSubscribe: called");
            }

            @Override
            public void onNext(ArrayTaskRxJava arrayTaskRxJava) {
                Log.i(TAG_RX_JAVA, "onNext: " + Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG_RX_JAVA, "onError: ", e);
            }

            @Override
            public void onComplete() {
                Log.i(TAG_RX_JAVA, "onComplete: called");
            }
        });
    }
}
