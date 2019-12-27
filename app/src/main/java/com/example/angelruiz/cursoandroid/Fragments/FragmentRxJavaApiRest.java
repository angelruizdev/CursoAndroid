package com.example.angelruiz.cursoandroid.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angelruiz.cursoandroid.Adapters.AdapterRxPostComentApiRest;
import com.example.angelruiz.cursoandroid.Arrays.ArrayCommentsRxApiRest;
import com.example.angelruiz.cursoandroid.Arrays.ArrayPostsRxApiRest;
import com.example.angelruiz.cursoandroid.ModelRxJavaApiRest.ServiceGeneratorRxRetrofit;
import com.example.angelruiz.cursoandroid.R;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

//class
public class FragmentRxJavaApiRest extends Fragment {

    //vars
    private static final String TAG_RX_JAVA = "TAG_RX_JAVA";
    public View view;
    private Context context;
    private AdapterRxPostComentApiRest adapterRxPostComentApiRest;
    private RecyclerView rvShowPostsComments;
    private CompositeDisposable compositeDisposable;

    //builder
    public FragmentRxJavaApiRest() {
        // Required empty public constructor
    }

    //inicialize the objects
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        compositeDisposable = new CompositeDisposable();
    }

    //inicialize the views
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_rx_java_api_rest, container, false);
   
    rvShowPostsComments = view.findViewById(R.id.rv_show_posts_comments);
    return view;
    }

    //show the data in the views
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapterRxPostComentApiRest = new AdapterRxPostComentApiRest();
        rvShowPostsComments.setLayoutManager(new LinearLayoutManager(context));
        DividerItemDecoration did = new DividerItemDecoration(context, LinearLayoutManager.VERTICAL);
        rvShowPostsComments.addItemDecoration(did);
        rvShowPostsComments.setAdapter(adapterRxPostComentApiRest);

        showPostCommentObservable();
    }

    //show both observables with flatMap operator
    private void showPostCommentObservable(){

        getPostsObservable()
            .flatMap(new Function<ArrayPostsRxApiRest, ObservableSource<ArrayPostsRxApiRest>>() {
                @Override
                public ObservableSource<ArrayPostsRxApiRest> apply(ArrayPostsRxApiRest arrayPostsRxApiRest) throws Exception {

                    //return the observable of comments
                    return getCommentsObservable(arrayPostsRxApiRest);
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<ArrayPostsRxApiRest>() {
                @Override
                public void onSubscribe(Disposable d) {
                    compositeDisposable.add(d);
                }

                //call the method updatePosts for update the items transformed
                @Override
                public void onNext(ArrayPostsRxApiRest arrayPostsRxApiRest) {
                    updatePosts(arrayPostsRxApiRest);
                }

                @Override
                public void onError(Throwable e) {
                    Log.e(TAG_RX_JAVA, "onError: ", e);
                }

                @Override
                public void onComplete() {

                }
            });
    }

    //observable for bring the posts of the api rest
    private Observable<ArrayPostsRxApiRest> getPostsObservable(){

        return ServiceGeneratorRxRetrofit
               .establishConnectionApiRest()
               .getPostsRx()
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .flatMap(new Function<List<ArrayPostsRxApiRest>, ObservableSource<ArrayPostsRxApiRest>>() {
                   @Override
                   public ObservableSource<ArrayPostsRxApiRest> apply(List<ArrayPostsRxApiRest> arrayPostsRxApiRests) throws Exception {

                       //fill the arrayPosts of the adapter through their method set
                       adapterRxPostComentApiRest.setArrayPostsRxApiRest(arrayPostsRxApiRests);
                       return Observable
                                 .fromIterable(arrayPostsRxApiRests)
                                 .subscribeOn(Schedulers.io())
                                 //change the title to uppercase
                                 .map(new Function<ArrayPostsRxApiRest, ArrayPostsRxApiRest>() {
                                     @Override
                                     public ArrayPostsRxApiRest apply(ArrayPostsRxApiRest arrayPostsRxApiRest) throws Exception {

                                         arrayPostsRxApiRest.setTitle(arrayPostsRxApiRest.getTitle().toUpperCase());
                                         return arrayPostsRxApiRest;
                                     }
                                 });
                   }
               });
    }

    //observable for bring the comments of the api rest
    private Observable<ArrayPostsRxApiRest> getCommentsObservable(final ArrayPostsRxApiRest arrayPostsRxApiRest){

        return ServiceGeneratorRxRetrofit
               .establishConnectionApiRest()
                //this method receive an id for show comments in base to the id of the posts(similar to delete of retrofit normal)
               .getCommentsRx(arrayPostsRxApiRest.getId())
               .map(new Function<List<ArrayCommentsRxApiRest>, ArrayPostsRxApiRest>() {
                   @Override
                   public ArrayPostsRxApiRest apply(List<ArrayCommentsRxApiRest> arrayCommentsRxApiRest) throws Exception {

                       //fill the arrayComments of the adapter through their method set
                       adapterRxPostComentApiRest.setArrayCommentsRxApiRest(arrayCommentsRxApiRest);
                       return arrayPostsRxApiRest;
                   }
               })
               .subscribeOn(Schedulers.io());
    }

    //update the items already transformed
    private void updatePosts(final ArrayPostsRxApiRest arrayPostsRxApiRestsss){

        Observable
            .fromIterable(adapterRxPostComentApiRest.getArrayPostsRxApiRest())
            //show only the number of comments with base to the number(id) of posts
            .filter(new Predicate<ArrayPostsRxApiRest>() {
                @Override
                public boolean test(ArrayPostsRxApiRest arrayPostsRxApiRest) throws Exception {

                    return arrayPostsRxApiRest.getId() == arrayPostsRxApiRestsss.getId();
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<ArrayPostsRxApiRest>() {
                @Override
                public void onSubscribe(Disposable d) {
                    compositeDisposable.add(d);
                }

                @Override
                public void onNext(ArrayPostsRxApiRest arrayPostsRxApiRest) {
                    adapterRxPostComentApiRest.updatePosts(arrayPostsRxApiRest);
                }

                @Override
                public void onError(Throwable e) {
                    Log.e(TAG_RX_JAVA, "onError: ", e);

                }

                @Override
                public void onComplete() {

                }
            });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
