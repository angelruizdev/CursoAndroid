package com.example.angelruiz.cursoandroid.InterfazAPI_REST;

import com.example.angelruiz.cursoandroid.Arrays.ArrayCommentsRxApiRest;
import com.example.angelruiz.cursoandroid.Arrays.ArrayPostsRxApiRest;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

//end points to consume of api rest
public interface IPostCommentRxRequestApiRest {

    @GET("/posts")
    Observable<List<ArrayPostsRxApiRest>> getPostsRx();

    @GET("posts/{id}/comments")
    Observable<List<ArrayCommentsRxApiRest>> getCommentsRx(@Path("id") int id);
}
