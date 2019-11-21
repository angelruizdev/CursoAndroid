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

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class FragmentRxJavaTest extends Fragment {
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
        rxJavaTest();
    }

    private void showObservableRxJava(){
        //create the observable
        Observable<ArrayTaskRxJava> taskObservable = Observable
                      .fromIterable(TaskRxJava.createTaskList())
                      //in where its will ecxecute the observable
                      .subscribeOn(Schedulers.io())
                      //show only the isComplet true
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

        //subscribe(connect) the observer to the observable
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

    private void rxJavaTest(){

        Observable.just(1, 2, 3, 4, 5, 6).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                //return the numbers pares
                return integer % 2 == 0;
            }

        }).subscribe(new Observer<Integer>() {
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
