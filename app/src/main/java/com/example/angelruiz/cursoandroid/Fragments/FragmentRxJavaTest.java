package com.example.angelruiz.cursoandroid.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.angelruiz.cursoandroid.R;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import kotlin.Unit;

public class FragmentRxJavaTest extends Fragment implements View.OnClickListener {
    //observer : subscribe(suscriptor,reactor)
    View view;
    Context context;
    private static final String TAG_RX_JAVA = "TAG_RX_JAVA";
    private TextView tvShowCount, tvShowCountClicks;
    private Button btAddCount, btCountClicks;
    private int count = 0;
    //for delete the disposables to the observador once recovered the emissions to the observable
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private List<Integer> numbersInteger;

    public FragmentRxJavaTest() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        numbersInteger = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rx_java_test, container, false);

        tvShowCount = view.findViewById(R.id.tvShowCount);
        tvShowCountClicks = view.findViewById(R.id.tvShowCountClicks);
        btAddCount = view.findViewById(R.id.btAddCount);
        btCountClicks = view.findViewById(R.id.btCountClicks);
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
        //createObservableWithTimer();

        /* converts objects or data in observable */
        //convertArrayInObservable();
        //convertListInObservable();
        //convertDataInObservable();

        /* Flowables */
        //createFlowable();
        //createSingle();

        /* ussing operators of filter */
        //operatorFilter();

        /* ussing operators of transformation */
        operatorsOfTransformation();

        btAddCount.setOnClickListener(this);
        btCountClicks.setOnClickListener(this);
        //save instate of the count if its rotate the screen
        if (savedInstanceState != null){
            count = savedInstanceState.getInt("contador");
            tvShowCount.setText(String.valueOf(count));
        }
    }

    //count
    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.btAddCount:
               count++;
               tvShowCount.setText(String.valueOf(count));
               break;

           case R.id.btCountClicks:
               countClicksRx();
               break;
       }
    }

    //event onclick(rxjava) in view(button)
    private void countClicksRx(){

        //map - convert the clicks in an integer
        RxView.clicks(btCountClicks)
              .map(new Function<Unit, Integer>() {
                  @Override
                  public Integer apply(Unit unit) throws Exception {
                      return 1;
                  }
              })
              //each 3 seconds save the number of clicks made
              .buffer(3, TimeUnit.SECONDS)
              //if does more of 4 clicks its stop the buffer
              .takeWhile(new Predicate<List<Integer>>() {
                  @Override
                  public boolean test(List<Integer> integers) throws Exception {
                      return integers.size() <= 4;
                  }
              })
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Observer<List<Integer>>() {
                  @Override
                  public void onSubscribe(Disposable d) {
                      compositeDisposable.add(d);
                  }

                  //show the number of clicks made in 3 seconds
                  @Override
                  public void onNext(List<Integer> integers) {
                      Log.i(TAG_RX_JAVA, "onNext: You clicked " + integers.size() + " times in 3 seconds!");

                      String numberClicks = integers.size() + " clicks in 4 seconds!";
                      tvShowCountClicks.setText(numberClicks);
                  }

                  @Override
                  public void onError(Throwable e) {

                  }

                  @Override
                  public void onComplete() {
                      Log.i(TAG_RX_JAVA, "onComplete: Número de clicks superado");
                      tvShowCountClicks.setText("Número de clicks superado");
                  }
              });
    }

    //example ussing operators of transformation
    private void operatorsOfTransformation(){

        /*//fill list of numbers integer
        for (int i = 1; i <= 10 ; i++) {
            numbersInteger.add(i);
        }

        //transform number integers with map operator multiplying by 5
        Observable<Integer> observableUssingMap = Observable
            .fromIterable(numbersInteger)
            .map(new Function<Integer, Integer>() {
                @Override
                public Integer apply(Integer integer) throws Exception {

                    //issues the values transformed
                    return integer * 5;
                }
            })
            .observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

        observableUssingMap.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG_RX_JAVA, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });*/

        /*//transform the object ArrayTaskRxJava delete items duplicates with distinc and with map operator adding email adress
        Observable<ArrayTaskRxJava> observableWithMap = Observable
             .fromIterable(TaskRxJava.createTaskList())
             .doOnNext(c -> System.out.println("processing item on thread " + Thread.currentThread().getName()))
             .subscribeOn(Schedulers.io())

             .distinct(new Function<ArrayTaskRxJava, String>() {
                 @Override
                 public String apply(ArrayTaskRxJava arrayTaskRxJava) throws Exception {
                     //Log.i(TAG_RX_JAVA, "observable(elements) run in thread: " + Thread.currentThread().getName());
                     return arrayTaskRxJava.getNameWorker();
                 }
             })

             .map(new Function<ArrayTaskRxJava, ArrayTaskRxJava>() {
                 @Override
                 public ArrayTaskRxJava apply(ArrayTaskRxJava arrayTaskRxJava) throws Exception {

                     arrayTaskRxJava.setEmailWorker(String.format("%s@rxjava.com", arrayTaskRxJava.getNameWorker()));
                     arrayTaskRxJava.setNameWorker(arrayTaskRxJava.getNameWorker().toUpperCase()); //pass to uppercase the nameWorker
                     //Log.i(TAG_RX_JAVA, "observable(elements) run in thread: " + Thread.currentThread().getName());

                     Thread.sleep(2000);
                     return arrayTaskRxJava;
                 }
             })
             .observeOn(AndroidSchedulers.mainThread());

        observableWithMap.subscribe(new Observer<ArrayTaskRxJava>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(ArrayTaskRxJava arrayTaskRxJava) {
                //Log.i(TAG_RX_JAVA, "onNext: " + arrayTaskRxJava.getNameWorker() + "-" + arrayTaskRxJava.getEmailWorker());
                //Log.i(TAG_RX_JAVA, "observer(iu) run in thread: " + Thread.currentThread().getName());

                tvShowCount.append("\n" + arrayTaskRxJava.getNameWorker());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG_RX_JAVA, "onComplete: success");
            }
        });*/

        /*//emit in package of 2 the items of the observable with operator buffer (not one by one)
        Observable<ArrayTaskRxJava> ObsOperatorBuffer = Observable
            .fromIterable(TaskRxJava.createTaskList())
            .subscribeOn(Schedulers.io());

        //this operator is useful for work with back pressure
        ObsOperatorBuffer
            .distinct(new Function<ArrayTaskRxJava, String>() {
                @Override
                public String apply(ArrayTaskRxJava arrayTaskRxJava) throws Exception {
                    Thread.sleep(1000);
                    return arrayTaskRxJava.getNameWorker();
                }
            })
            .buffer(2)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<List<ArrayTaskRxJava>>() {
                @Override
                public void onSubscribe(Disposable d) {
                    compositeDisposable.add(d);
                }

                @Override
                public void onNext(List<ArrayTaskRxJava> arrayTaskRxJavas) {
                    Log.i(TAG_RX_JAVA, "onNext package of 2 items:-> ");

                    for (ArrayTaskRxJava arrayTask: arrayTaskRxJavas) {
                        Log.i(TAG_RX_JAVA, "onNext: " + arrayTask.getNameWorker());
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {
                    Log.i(TAG_RX_JAVA, "onComplete: success");
                }
            });*/

        //
    }

    /*//example ussing operator filter (gral)
    private void operatorFilter(){

        //with this operator its can use all the methods of the class String
        Observable<ArrayTaskRxJava> obsUssingFilter = Observable
             .fromIterable(TaskRxJava.createTaskList())
             //this operator it will take only the 4 first elements of the list, skip the rest. (take(n))
             .take(4)
             .takeWhile(new Predicate<ArrayTaskRxJava>() {
                 @Override
                 public boolean test(ArrayTaskRxJava arrayTaskRxJava) throws Exception {

                     return arrayTaskRxJava.getDescription().startsWith("T");
                 }
             })
             .filter(new Predicate<ArrayTaskRxJava>() {
                 @Override
                 public boolean test(ArrayTaskRxJava arrayTaskRxJava) throws Exception {

                     //this operator will issue only values(string) that end with e
                     if (arrayTaskRxJava.getDescription().endsWith("e")){
                         return true;
                     }else {
                         Log.i(TAG_RX_JAVA, "without data");
                     }
                     return false;
                 }
             })
             //this operator will delete the objects repetead(data), only will issue different
             .distinct(new Function<ArrayTaskRxJava, String>() {
                 @Override
                 public String apply(ArrayTaskRxJava arrayTaskRxJava) throws Exception {

                     return arrayTaskRxJava.getDescription();
                 }
             })
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread());

        //subscribe the observer
        obsUssingFilter.subscribe(new Observer<ArrayTaskRxJava>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            //receive and show only values that meet the condition of the filter
            @Override
            public void onNext(ArrayTaskRxJava arrayTaskRxJava) {
                Log.i(TAG_RX_JAVA, "onNext: " + arrayTaskRxJava.getDescription());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG_RX_JAVA, "onComplete: success");
            }
        });
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

        //this operator create a observable infinity with a start and period for begin to emit items(how sleep a thread)
        Observable<Long> observableInterval = Observable
             .interval(0, 2, TimeUnit.SECONDS)
             .subscribeOn(Schedulers.io())
             //is how a if, while the sequence be < 5 will issue items(numbers)
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

        //this operator delay the emittions n time(similar to delay)
        Observable<Long> observableTimer = Observable
             .timer(2, TimeUnit.SECONDS)
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread());

        observableTimer.subscribe(new Observer<Long>() {
            //variable to show how much time has passed
            Long time;

            @Override
            public void onSubscribe(Disposable d) {
                time = System.currentTimeMillis() / 1000;
            }

            @Override
            public void onNext(Long aLong) {
                //show how much time has passed
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

    //comverts a array[] in observable with fromArray operator
    private void convertArrayInObservable(){

        ArrayTaskRxJava[] arrayTaskRxJava = new ArrayTaskRxJava[3];

        arrayTaskRxJava[0] = new ArrayTaskRxJava("module maps", false, 1);
        arrayTaskRxJava[1] = new ArrayTaskRxJava("module firebase", false, 1);
        arrayTaskRxJava[2] = new ArrayTaskRxJava("module content provider", true, 0);

        Observable<ArrayTaskRxJava> observableFromArray = Observable
             //this operator convert the array in observable for work best(for have data of a single type)
             .fromArray(arrayTaskRxJava)
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread());

        observableFromArray.subscribe(new Observer<ArrayTaskRxJava>() {
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
                Log.i(TAG_RX_JAVA, "onComplete: ready");
            }
        });
    }

    //converts a list<> in observable with fromIterable operator
    private void convertListInObservable(){

        List<ArrayTaskRxJava> listTaskRxJava = new ArrayList<>();

        listTaskRxJava.add(new ArrayTaskRxJava("module firebase", false, 1));
        listTaskRxJava.add(new ArrayTaskRxJava("module maps", false, 1));
        listTaskRxJava.add(new ArrayTaskRxJava("module sqlite", true, 0));

        Observable<ArrayTaskRxJava> observableFromIterable = Observable
             //this operator convert the list in observable for work best(for have data of a single type)
             .fromIterable(listTaskRxJava)
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread());

        //only if is subscribed the observable will issue items
        observableFromIterable.subscribe(new Observer<ArrayTaskRxJava>() {
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

    //converts a callable<> in observable with fromCallable operator
    private void convertDataInObservable(){

        Observable<ArrayTaskRxJava> observableFromCallable = Observable
             //this operator issue a single element(useful for task with deley)
             .fromCallable(new Callable<ArrayTaskRxJava>() {
                 @Override
                 public ArrayTaskRxJava call() throws Exception {

                     return null;
                 }

             }).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread());

        observableFromCallable.subscribe(new Observer<ArrayTaskRxJava>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(ArrayTaskRxJava arrayTaskRxJava) {
                Log.i(TAG_RX_JAVA, "onNext: " + arrayTaskRxJava.getComplete());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    //useful for work with contrapresion only
    private void createFlowable(){

        List<Integer> numbers = new ArrayList<>();

        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        //this class its create similar to an observable
        Flowable<Integer> createFlowable = Flowable
            .fromIterable(numbers)
            .onBackpressureBuffer()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

        //its subscribe how an observable
        createFlowable.subscribe(new FlowableSubscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG_RX_JAVA, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable t) {
                Log.i(TAG_RX_JAVA, "onError: " + t);
            }

            @Override
            public void onComplete() {
                Log.i(TAG_RX_JAVA, "onComplete: Success");
            }
        });

        //comverts of frowable a observable ussing the method toObservable
        Observable<Integer> backToObservable = createFlowable.toObservable();
            backToObservable.subscribe(new Observer<Integer>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Integer integer) {
                    Log.i(TAG_RX_JAVA, "onNext: " + integer);
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });

        //comverts of observable a frowable ussing the method toFlowable
        Flowable<Integer> backToFlowable = backToObservable.toFlowable(BackpressureStrategy.BUFFER);
            backToFlowable.subscribe(new FlowableSubscriber<Integer>() {
                @Override
                public void onSubscribe(Subscription s) {

                }

                @Override
                public void onNext(Integer integer) {
                    Log.i(TAG_RX_JAVA, "onNext: " + integer);
                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onComplete() {

                }
            });
    }

    //this class is similar to observable, but only issue 1 value
    private void createSingle(){

        //this class is useful for receive response of red
        Single<String> singleResponse = Single
                .create(new SingleOnSubscribe<String>() {
                    @Override
                    public void subscribe(SingleEmitter<String> emitter) throws Exception {

                        if(!emitter.isDisposed()){
                            emitter.onSuccess("Conectado a la red, onSuccess!");
                        }
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        //its subscribe how an observable
        singleResponse.subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            //if the single can issue his item
            @Override
            public void onSuccess(String s) {
                Log.i(TAG_RX_JAVA, "onSuccess: " + s);
            }

            //if the single no can issue his item
            @Override
            public void onError(Throwable e) {

            }
        });
    }



    //check... onClick to button with rxandroid
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
                      //operator show only the task isComplete true(filter a boolean)
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
