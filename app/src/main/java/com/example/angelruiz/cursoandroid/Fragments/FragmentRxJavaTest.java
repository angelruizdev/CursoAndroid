package com.example.angelruiz.cursoandroid.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.angelruiz.cursoandroid.R;
import com.example.angelruiz.cursoandroid.RxJavaExercises.ArrayTaskRxJava;
import com.example.angelruiz.cursoandroid.RxJavaExercises.TaskRxJava;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class FragmentRxJavaTest extends Fragment {
    //observer : subscribe(suscriptor,reactor)
    View view;
    Context context;
    private static final String TAG_RX_JAVA = "TAG_RX_JAVA";

    public FragmentRxJavaTest() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rx_java_test, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //showObservableRxJava();
        //rxJavaTest();
        //observableRxJavaExplained();
        simpleObserverRxJava();
    }

    private void simpleObserverRxJava(){
        //create a new observable
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                List<Integer> fillArrayList = new ArrayList<>();

                for (int i = 0; i < 10; i++) {
                    fillArrayList.add(i);
                }

                for (int i = 0; i < fillArrayList.size(); i++) {
                    emitter.onNext(fillArrayList.get(i));
                }

                emitter.onComplete();
            }

        }).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer <= 7;
            }

        }).subscribeOn(Schedulers.newThread())
          .observeOn(AndroidSchedulers.mainThread());

        //create a new observer
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG_RX_JAVA, "onSubscribe:");
            }

            //a Observable call to this méthod everytime that the Observable issued a element
            @Override
            public void onNext(Integer integer) {

                Log.i(TAG_RX_JAVA, "onNext: " + integer);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //a Observable call to this méthod for indicate that could not can generate the data expected or has found some other error.
            @Override
            public void onError(Throwable e) {
                Log.i(TAG_RX_JAVA, "onError: ");
            }

            //a Observable call to this méthod after having calling to onNext for last time, if you have not found any error.
            @Override
            public void onComplete() {
                Log.i(TAG_RX_JAVA, "onComplete: All Done");
            }
        };

        //subscribe the observer in the observable
        observable.subscribe(observer);
    }

    private void observableRxJavaExplained(){

        List<Integer> numbers = new ArrayList<>();

        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        //create a new observable
        Observable<Integer> observableNumbers = Observable
                .fromIterable(numbers)
                //create a new thread, in this it makes the work to the (observable)
                .subscribeOn(Schedulers.newThread())
                //send the result of the (observer) to the thread main for update IU
                .observeOn(AndroidSchedulers.mainThread());
        //assign(subscribe) the observer to the observable
        observableNumbers.subscribe(new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG_RX_JAVA, "onNext: called " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void showObservableRxJava(){
        //create the observable
        Observable<ArrayTaskRxJava> taskObservable = Observable
                      //this observable itera in a arrayList
                      .fromIterable(TaskRxJava.createTaskList())
                      //performs short duration operations(e/s)
                      .subscribeOn(Schedulers.io())
                      //show only the isComplete true
                      .filter(new Predicate<ArrayTaskRxJava>() {
                          @Override
                          public boolean test(ArrayTaskRxJava arrayTaskRxJava) throws Exception {
                              try {
                                  Thread.sleep(2000);
                              } catch (InterruptedException e) {
                                  e.printStackTrace();
                              }
                              return arrayTaskRxJava.getComplet();
                          }
                      }).subscribeOn(AndroidSchedulers.mainThread());

        //subscribe(connect) the observer to the observable, for who the observer can receive element issued for the observable
        taskObservable.subscribe(new Observer<ArrayTaskRxJava>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG_RX_JAVA, "onSubscribe: called");
            }

            //provee al Observer con nuevos elementos
            @Override
            public void onNext(ArrayTaskRxJava arrayTaskRxJava) {
                Log.i(TAG_RX_JAVA, "onNext: " + Thread.currentThread().getName() +" Task completed: "+ arrayTaskRxJava.getDescription());
                /*try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }

            //notificará al Observer de que se ha producido algún tipo de error
            @Override
            public void onError(Throwable e) {
                Log.i(TAG_RX_JAVA, "onError: ", e);
            }

            //notifica al Observer de que el Observable ha terminado de mandar elementos.
            @Override
            public void onComplete() {
                Log.i(TAG_RX_JAVA, "onComplete: called");
            }
        });
    }

    //observable reduced
    private void rxJavaTest(){

        Observable.just(1, 2, 3, 4, 5, 6).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                //return the numbers pares
                return integer % 2 == 0;
            }

        }).subscribeOn(AndroidSchedulers.mainThread())//excecute the onservable in thread main

        //subscribe the observer in the observable
        .subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG_RX_JAVA, "onSubscribe: " + d);
            }

            @Override
            public void onNext(Integer integer) {

                Log.i(TAG_RX_JAVA, "onNext: impar: " + integer);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG_RX_JAVA, "onComplete... ");
            }
        });
    }
}
