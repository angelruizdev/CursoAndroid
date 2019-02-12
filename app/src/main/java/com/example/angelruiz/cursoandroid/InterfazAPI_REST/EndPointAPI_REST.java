package com.example.angelruiz.cursoandroid.InterfazAPI_REST;

import com.example.angelruiz.cursoandroid.RespuestaAPI_REST.RespuestaApiRest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EndPointAPI_REST {//esta interface nos permite acceder al listado JSON de la API, los metodos @GET o @POST, reciben los endPoints, in endPoint es un WS
    @GET("wsJSONMysql.php")//la peticion es de tipo @GET("") ya traeremos informacion de la API, en las comillas va la parte final de la url(que hace la peticion)
    Call<RespuestaApiRest> obtenerListadoJson();//como solo muestra datos de la API, no recibe parametros este metodo

    @FormUrlEncoded//esta notacion permite mandar los datos
    @POST("wsJSONRegistro.php")
    Call<RespuestaApiRest> registroAPIRest(@Field("numeroFolio") String numeroFolio, @Field("nombre") String nombre,
                                           @Field("profesion") String profesion);
    @POST("wsJSONRegistro.php")
    Call<RespuestaApiRest> registroAPIRest1(@Body RespuestaApiRest parametos);//con body podemos mandar varios parametros sin especificar cada uno

    @GET("wsJSONEliminar.php")
    Call<RespuestaApiRest> eliminarRegApiRest(@Query("idPersona") int idPersona);//@Query permite buscar datos en el ws
    //@Multipart//permite cargar archivos, video, docs etc..
}
