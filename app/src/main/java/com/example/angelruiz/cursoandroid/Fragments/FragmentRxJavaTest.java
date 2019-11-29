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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
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

        concatObservables();
        //observableUssingZip();
        //showObservableRxJava();
        //rxJavaTest();
        //observableRxJavaExplained();
        //simpleObserverRxJava();
        //obsRxJavaJust();
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

            //notifications of the observer (this 4 methods will notify to the observable about the data issued(results))
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG_RX_JAVA, "onSubscribe: " + Thread.currentThread().getName());
            }

            //provee al Observer con nuevos elementos(transmitter of elements, called for the observable 0+ times)
            @Override
            public void onNext(Integer integer) {
                Log.i(TAG_RX_JAVA, "onNext: called " + integer +"THREAD->"+ Thread.currentThread().getName() );
            }

            //notificará al Observer de que se ha producido algún tipo de error
            @Override
            public void onError(Throwable e) {

            }

            //notifica al Observer de que el Observable ha terminado de mandar elementos.
            @Override
            public void onComplete() {
                Log.i(TAG_RX_JAVA, "onComplete: " + Thread.currentThread().getName());
            }
        });
    }

    private void showObservableRxJava(){
        //create a new object observable
        Observable<ArrayTaskRxJava> taskObservable = Observable
                      //this observable itera in a arrayList
                      .fromIterable(TaskRxJava.createTaskList())
                      //performs short duration operations(e/s), thread in background
                      .subscribeOn(Schedulers.io())
                      //operator show only the task isComplete true
                      .filter(new Predicate<ArrayTaskRxJava>() {
                          @Override
                          public boolean test(ArrayTaskRxJava arrayTaskRxJava) throws Exception {
                              Log.i(TAG_RX_JAVA, "TEST->: " + Thread.currentThread().getName());
                              try {
                                  Thread.sleep(2000);
                              } catch (InterruptedException e) {
                                  e.printStackTrace();
                              }
                              return arrayTaskRxJava.getComplet();
                          }
                      //will issue the notifications(results) of the observer to the thread main UI
                      }).observeOn(AndroidSchedulers.mainThread());

        //subscribe(connect) the observer to the observable, for who the observer can receive element issued for the observable
        taskObservable.subscribe(new Observer<ArrayTaskRxJava>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG_RX_JAVA, "onSubscribe: " + Thread.currentThread().getName());
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
                Log.i(TAG_RX_JAVA, "onComplete: " + Thread.currentThread().getName());
            }
        });
    }

    //observable reduced, just can receive an string, int too
    private void rxJavaTest(){

        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
          .skip(5) //skip the first 5 values
          .filter(new Predicate<Integer>() { //filter for show only numbers pair
            @Override
            public boolean test(Integer integer) throws Exception {
                //return the numbers pares
                return integer % 2 == 0;
            }

        }).subscribeOn(Schedulers.newThread()) //excecute the observable in backgrond (new thread)
          .observeOn(AndroidSchedulers.mainThread()) //excecute the issued (results) in the main thread UI

        //subscribe the observer in the observable
        .subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG_RX_JAVA, "onSubscribe: " + d);
            }

            @Override
            public void onNext(Integer integer) {

                Log.i(TAG_RX_JAVA, "onNext: par: " + integer);
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
                Log.i(TAG_RX_JAVA, "onComplete: " + Thread.currentThread().getName());
            }
        });
    }

    //observable with just and string
    private void obsRxJavaJust(){

        Observable.just("Hola Ángel", "cómo estás", "buena tarde")
                .subscribeOn(Schedulers.newThread())
                .skip(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        String recuveData = fetchData(s);
                        Log.i(TAG_RX_JAVA, "onComplete: " + Thread.currentThread().getName() + recuveData);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private String fetchData(String data){

       return data;
    }

    //obsrvable Ussing Zip
    private void observableUssingZip(){

        //observable with even numbers
        List<Integer> numbersPair = new ArrayList<>();

        for (int i = 2; i <= 10 ; i+=2) {
            numbersPair.add(i);
        }

        Observable<Integer> observablePair = Observable
                .fromIterable(numbersPair)
                .subscribeOn(Schedulers.newThread());

        //observable with odd numbers
        List<Integer> numbersInPair = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            numbersInPair.add(i);
        }

        Observable<Integer> observableInPair = Observable
                .fromIterable(numbersInPair)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {

                        return integer % 2 == 1;
                    }
                }).subscribeOn(Schedulers.newThread());

        //with operator zip we join and execute 2 observables in parallel
        Observable<Integer> observableZip = Observable
                .zip(observablePair, observableInPair, new BiFunction<Integer, Integer, Integer>() {

                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        //cast integer to string for can return concatenated
                        String evenNumbers = String.valueOf(integer);
                        String oddNumbers = String.valueOf(integer2);
                        String concatNumbers = evenNumbers + oddNumbers;

                        Thread.sleep(2000);

                        return Integer.parseInt(concatNumbers);
                    }

                }).subscribeOn(Schedulers.newThread())
                  .observeOn(AndroidSchedulers.mainThread());

                observableZip.subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    //we obtain the emissions of the 2 observables at the same time(parallel)
                    @Override
                    public void onNext(Integer integer) {
                        Log.i(TAG_RX_JAVA, "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG_RX_JAVA, "onComplete: succesfull");
                    }
                });
    }


    private Observable<Integer> observableNumbersId(){

        List<Integer> nameIdFill = new ArrayList<>();
        for (int i = 1; i <= 5 ; i++) {
            nameIdFill.add(i);
        }
        return Observable.fromIterable(nameIdFill);
    }

    private Observable<String> observableNames(){

        return Observable.just("Start second observable...", "Angel", "David", "Gustavo", "Magdalena");
    }

    //obsrvable ussing concat
    private void concatObservables(){

        Observable.concat(observableNumbersId(), observableNames())
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(new Observer<Serializable>() {
                 @Override
                 public void onSubscribe(Disposable d) {
                     Log.i(TAG_RX_JAVA, "onSubscribe: ");
                     Log.i(TAG_RX_JAVA, "Start first observable...");
                 }

                 @Override
                 public void onNext(Serializable serializable) {

                     Log.i(TAG_RX_JAVA, "onNext: " + serializable);

                     try {
                         Thread.sleep(1500);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }

                 @Override
                 public void onError(Throwable e) {

                 }

                 @Override
                 public void onComplete() {
                     Log.i(TAG_RX_JAVA, "onComplete: " + Thread.currentThread().getName());
                 }
             });
    }
}
