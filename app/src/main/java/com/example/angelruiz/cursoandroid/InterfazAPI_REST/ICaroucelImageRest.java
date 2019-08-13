package com.example.angelruiz.cursoandroid.InterfazAPI_REST;

import com.example.angelruiz.cursoandroid.RespuestaAPI_REST.ArrayCaroucelResponceRest;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ICaroucelImageRest {
    @GET("pokemon")
    Call<ArrayCaroucelResponceRest> obtenerImagesRest();
}
