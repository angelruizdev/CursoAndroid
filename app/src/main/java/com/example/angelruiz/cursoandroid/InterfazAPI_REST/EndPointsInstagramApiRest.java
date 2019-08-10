package com.example.angelruiz.cursoandroid.InterfazAPI_REST;

import com.example.angelruiz.cursoandroid.DatosAPI_REST_Instagram.ConstantesApiRestInstagram;
import com.example.angelruiz.cursoandroid.RespuestaAPI_REST.ResponseInstagramApiRest;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndPointsInstagramApiRest {
    @GET(ConstantesApiRestInstagram.URL_GET_RECENT_MEDIA_USER) //endpoint de la api
     Call<ResponseInstagramApiRest> getRecentMedia();
}
