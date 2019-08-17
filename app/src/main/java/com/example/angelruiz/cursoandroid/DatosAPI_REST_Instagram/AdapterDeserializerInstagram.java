package com.example.angelruiz.cursoandroid.DatosAPI_REST_Instagram;

import com.example.angelruiz.cursoandroid.DeserializerInstagram.DeserializeArrayResponseInstagram;
import com.example.angelruiz.cursoandroid.InterfazAPI_REST.IEndPointsInstagramApiRest;
import com.example.angelruiz.cursoandroid.RespuestaAPI_REST.ArrayResponseInstagram;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterDeserializerInstagram {//-->rebisar
    public IEndPointsInstagramApiRest establishConnectionInstagramApiRest(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesApiRestInstagram.ROOT_URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(IEndPointsInstagramApiRest.class);
    }

    public Gson buildGsonDeserializerMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ArrayResponseInstagram.class, new DeserializeArrayResponseInstagram());
        return gsonBuilder.create();
    }
}
