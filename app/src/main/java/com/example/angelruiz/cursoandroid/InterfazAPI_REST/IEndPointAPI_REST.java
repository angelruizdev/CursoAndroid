package com.example.angelruiz.cursoandroid.InterfazAPI_REST;

import com.example.angelruiz.cursoandroid.RespuestaAPI_REST.ArrayRespuestaApiRest;
import com.example.angelruiz.cursoandroid.RespuestaAPI_REST.FileInformationUploadImage;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

//esta interface nos permite acceder al listado JSON de la API, las notaciones @GET, @POST..., reciben los endPoints, un endPoint es un WS
public interface IEndPointAPI_REST {

    //service save register in bd
    @FormUrlEncoded
    @POST("wsJSONRegistro.php") //this method receive 4 parameters for send to bd
    Call<ArrayRespuestaApiRest> registroAPIRest(@Field("numeroFolio") int numeroFolio,
                                                @Field("nombre") String nombre,
                                                @Field("profesion") String profesion,
                                                @Field("imagen") String imagen);
    //service save register in bd
    @POST("wsJSONRegistro.php")
    Call<ArrayRespuestaApiRest> registroAPIRest1(@Body ArrayRespuestaApiRest parameters);

    //service show registers of bd in RV
    @GET("wsJSONMysql.php") //la peticion es de tipo @GET("") ya traeremos informacion de la API, en las comillas va la parte final de la url(que hace la peticion)
    Call<ArrayRespuestaApiRest> obtenerListadoJson(); //como solo muestra datos de la API, no recibe parametros este metodo

    //service update registers of the bd
    @FormUrlEncoded
    @POST("wsJSONActualizar.php")
    Call<ArrayRespuestaApiRest> actualizarRegistroApiRest(@Field("idPersona") int idPersona,
                                                          @Field("nombre") String nombre);

    //service for delete register of db
    @POST("wsJSONEliminar.php")
    Call<ArrayRespuestaApiRest> eliminarRegApiRest(@Query("idPersona") int idPersona);

    //service for upload image to server
    @Multipart
    @POST("wsJSONUpdate.php")
    Call<FileInformationUploadImage> uploadImageServer(@Part MultipartBody.Part file);
}

/*
@FormUrlEncoded, this notation its allows send the data and encode them in utf-8
@Multipart, permite cargar archivos, video, docs etc..
@Field works with @POST, used to send forms
@Body works with @POST, used to send data in only object, as join various @Field in one,
with body we can send various parameters without especific each one
@Query, it alows search data in the ws, is used when you have url which has '?',
http://google.com/index.html?userid, @Get("index.html")...(@Query ("userid") int id);.
@Path is used when you have url which has '/', http://google.com/index.html/endPoint, http://google.com/index.html/userid
its use with @GET, so: @Get("index.html/{userid}")...getX(@Path ("userid") int userid);*/