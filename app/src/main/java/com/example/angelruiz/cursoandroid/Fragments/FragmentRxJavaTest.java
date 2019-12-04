package com.example.angelruiz.cursoandroid.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.angelruiz.cursoandroid.R;
import com.example.angelruiz.cursoandroid.RxJavaExercises.ArrayTaskRxJava;
import com.example.angelruiz.cursoandroid.RxJavaExercises.TaskRxJava;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class FragmentRxJavaTest extends Fragment implements View.OnClickListener {
    //observer : subscribe(suscriptor,reactor)
    View view;
    Context context;
    private static final String TAG_RX_JAVA = "TAG_RX_JAVA";
    private TextView tvShowCount;
    private Button btAddCount;
    private int count = 0;
    //for delete the disposables to the observador once recovered the emissions to the observable
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

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

        tvShowCount = view.findViewById(R.id.tvShowCount);
        btAddCount = view.findViewById(R.id.btAddCount);
        return view;
    }

    //save the state(value) of the variable count,(save state of fmt in gral)
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("contador", count);
    }

    //this method too is funtional for manage the change of configuration (rotate screen, life cycle)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        /* observables of test */
        //concatObservables();
        //observableUssingZip();
        //showObservableRxJava();
        //rxJavaTest();
        //observableRxJavaExplained();
        //simpleObserverRxJava();
        //obsRxJavaJust();
        //clickButtonObservable();

        /* observables with more detail */
        //createObservableWithCreate();
        //createObservableWithCreate1();

        //createObservableWithJust();
        //createObservableWithRange();
        //createObservableWithInterval();
        createObservableWithTimer();


        btAddCount.setOnClickListener(this);
        //save instate of the count if its rotate the screen
        if (savedInstanceState != null){
            count = savedInstanceState.getInt("contador");
            tvShowCount.setText(String.valueOf(count));
        }
    }

    @Override
    public void onClick(View view) {
       count++;
       tvShowCount.setText(String.valueOf(count));
    }

    //crating observable with operator create example 0
    private void createObservableWithCreate() {

        ArrayTaskRxJava arrayTaskRxJava = new ArrayTaskRxJava("finish module", true, 1);

        //create a observable with a single object(arrayTaskRxJava)
        Observable<ArrayTaskRxJava> observableWithCreate = Observable
             .create(new ObservableOnSubscribe<ArrayTaskRxJava>() {
                 @Override
                 public void subscribe(ObservableEmitter<ArrayTaskRxJava> emitter) throws Exception {
                     if (!emitter.isDisposed()){
                         emitter.onNext(arrayTaskRxJava);
                         emitter.onComplete();
                     }
                 }
             }).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread());

        observableWithCreate.subscribe(new Observer<ArrayTaskRxJava>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(ArrayTaskRxJava arrayTaskRxJava) {
                Log.i(TAG_RX_JAVA, "onNext: " + arrayTaskRxJava.getDescription());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    //crating observable with operator create example 1
    private void createObservableWithCreate1() {

        //create a observable with an list of objects(arrayTaskRxJava)
        Observable<ArrayTaskRxJava> observableWithCreate = Observable
            .create(new ObservableOnSubscribe<ArrayTaskRxJava>() {
                @Override
                public void subscribe(ObservableEmitter<ArrayTaskRxJava> emitter) throws Exception {

                    //Inside the subscribe method iterate through the list of tasks and call onNext(arrayTaskRxJava)
                    for (ArrayTaskRxJava arrayTaskRxJava: TaskRxJava.createTaskList()) {
                         //if the data emited exists, pass to onNext
                         if (!emitter.isDisposed()){
                             emitter.onNext(arrayTaskRxJava);
                         }else {
                             Toast.makeText(context, "Flujo de datos eliminado o termindo", Toast.LENGTH_SHORT).show();
                         }
                    }

                    //Once the loop is complete, call the onComplete() method
                    if (!emitter.isDisposed()){
                        emitter.onComplete();
                    }
                }
            }).subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread());

        observableWithCreate.subscribe(new Observer<ArrayTaskRxJava>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(ArrayTaskRxJava arrayTaskRxJava) {
                Log.i(TAG_RX_JAVA, "onNext: " + arrayTaskRxJava.getComplete());

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

            }
        });
    }

    //crating observable with operator just example 0
    private void createObservableWithJust() {

        //the operator just(), only allows 10 parameters, int or string
        Observable.just("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
              .observeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Observer<String>() { //view the results creating a new observer

                  @Override
                  public void onSubscribe(Disposable d) {
                      compositeDisposable.add(d);
                  }

                  @Override
                  public void onNext(String s) {
                      Log.i(TAG_RX_JAVA, "onNext: " + s);

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

                  }
              });
    }

    //creating observable with range operator
    private void createObservableWithRange(){

        //this operator create something similar to a loop(for, while)
        Observable.range(0, 6)
            .repeat(2) //repeat 2 times the values of the range
            //.skip(2)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<Integer>() {

                @Override
                public void onSubscribe(Disposable d) {
                    compositeDisposable.add(d);
                }

                //receibe the values from 0 to 5
                @Override
                public void onNext(Integer integer) {
                    Log.i(TAG_RX_JAVA, "onNext: " + integer);

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

                }
            });

    }

    //creating observable with interval operator
    private void createObservableWithInterval(){

        Observable<Long> observableInterval = Observable
             .interval(2, TimeUnit.SECONDS)
             .subscribeOn(Schedulers.io())
             .takeWhile(new Predicate<Long>() {
                 @Override
                 public boolean test(Long aLong) throws Exception {
                     return aLong < 5;
                 }
             })
             .observeOn(AndroidSchedulers.mainThread());

        observableInterval.subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(Long aLong) {
                Log.i(TAG_RX_JAVA, "onNext: " + aLong);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG_RX_JAVA, "onComplete: ready");

            }
        });
    }

    //creating observable with timer operator
    private void createObservableWithTimer(){

        Observable<Long> observableTimer = Observable
             .timer(2, TimeUnit.SECONDS)
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread());

        observableTimer.subscribe(new Observer<Long>() {
            Long time;

            @Override
            public void onSubscribe(Disposable d) {
                time = System.currentTimeMillis() / 1000;
            }

            @Override
            public void onNext(Long aLong) {
                Log.i(TAG_RX_JAVA, "onNext: " +  ((System.currentTimeMillis() / 1000) - time) + " segundos han transcurrido");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /*//check... onClick to button with rxandroid
    private void clickButtonObservable(){

        RxView.clicks(btAddCount)
            .subscribe(new Observer<Unit>() {
             @Override
             public void onSubscribe(Disposable d) {

             }

             @Override
             public void onNext(Unit unit) {
                 Log.i(TAG_RX_JAVA, "onNext: " + unit);
             }

             @Override
             public void onError(Throwable e) {

             }

             @Override
             public void onComplete() {

             }
         });
    }

    //creating obsrvables and observers
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
                .repeat(2)
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
                      //this observable itera in a arrayList(converts the data in observables of single type(is recomended work with data of single type(observable)))
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
                              return arrayTaskRxJava.getComplete();
                          }
                      //will issue the notifications(results) of the observer to the thread main UI
                      }).observeOn(AndroidSchedulers.mainThread());

        //subscribe(connect) the observer to the observable, for who the observer can receive element issued for the observable
        taskObservable.subscribe(new Observer<ArrayTaskRxJava>() {

            //receive the disposables to the observer
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG_RX_JAVA, "onSubscribe: " + Thread.currentThread().getName());
                //we save the disposables for delete
                compositeDisposable.add(d);
            }

            //provee al Observer con nuevos elementos
            @Override
            public void onNext(ArrayTaskRxJava arrayTaskRxJava) {
                Log.i(TAG_RX_JAVA, "onNext: " + Thread.currentThread().getName() +" Task completed: "+ arrayTaskRxJava.getDescription());
                *//*try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*//*
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

        for (int i = 2; i <= 10; i += 2) {
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

    //observable integer for use with concat
    private Observable<Integer> observableNumbersId(){

        List<Integer> nameIdFill = new ArrayList<>();
        for (int i = 1; i <= 5 ; i++) {
            nameIdFill.add(i);
        }
        return Observable.fromIterable(nameIdFill).subscribeOn(Schedulers.io());
    }

    //observable string for use with concat
    private Observable<String> observableNames(){

        return Observable.just("Start second observable...", "Angel", "David", "Gustavo", "Magdalena").subscribeOn(Schedulers.io());
    }

    //operator concat it allow execute 2 observable in series(one at a time)
    private void concatObservables(){

        //obsrvable ussing concat
        Observable.concat(observableNumbersId(), observableNames())
             .subscribeOn(Schedulers.io())
             .delay(4, TimeUnit.SECONDS) //delay the issue for 4 seconds
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
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        //delete the elements disposables to the observer
        compositeDisposable.clear();
    }
}
