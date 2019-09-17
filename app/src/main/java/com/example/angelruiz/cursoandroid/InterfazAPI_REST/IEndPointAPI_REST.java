package com.example.angelruiz.cursoandroid.InterfazAPI_REST;

import com.example.angelruiz.cursoandroid.RespuestaAPI_REST.ArrayRespuestaApiRest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IEndPointAPI_REST { //esta interface nos permite acceder al listado JSON de la API, los metodos @GET o @POST, reciben los endPoints, in endPoint es un WS

    @FormUrlEncoded //esta notacion permite mandar los datos y codificarlos en utf-8
    @POST("wsJSONRegistro.php")
    Call<ArrayRespuestaApiRest> registroAPIRest(@Field("numeroFolio") int numeroFolio,
                                                @Field("nombre") String nombre,
                                                @Field("profesion") String profesion);
    @POST("wsJSONRegistro.php")
    Call<ArrayRespuestaApiRest> registroAPIRest1(@Body ArrayRespuestaApiRest parametos); //con body podemos mandar varios parametros sin especificar cada uno

    @GET("wsJSONMysql.php") //la peticion es de tipo @GET("") ya traeremos informacion de la API, en las comillas va la parte final de la url(que hace la peticion)
    Call<ArrayRespuestaApiRest> obtenerListadoJson(); //como solo muestra datos de la API, no recibe parametros este metodo

    @FormUrlEncoded
    @POST("wsJSONActualizar.php")
    Call<ArrayRespuestaApiRest> actualizarRegistroApiRest(@Field("idPersona") int idPersona,
                                                          @Field("nombre") String nombre);

    @POST("wsJSONEliminar.php")
    Call<ArrayRespuestaApiRest> eliminarRegApiRest(@Query("idPersona") int idPersona); //@Query permite buscar datos en el ws
    //@Multipart//permite cargar archivos, video, docs etc..
}
