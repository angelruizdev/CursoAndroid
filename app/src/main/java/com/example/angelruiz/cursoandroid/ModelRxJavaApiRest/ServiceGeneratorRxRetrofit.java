package com.example.angelruiz.cursoandroid.ModelRxJavaApiRest;

import com.example.angelruiz.cursoandroid.InterfazAPI_REST.IPostCommentRxRequestApiRest;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGeneratorRxRetrofit {

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private static IPostCommentRxRequestApiRest establishConnectionApiRest(){

        Retrofit retrofitBuilder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        return retrofitBuilder.create(IPostCommentRxRequestApiRest.class);
    }







}
