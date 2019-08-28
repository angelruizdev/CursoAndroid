package com.example.angelruiz.cursoandroid.DatosAPI_REST_Instagram;

import com.example.angelruiz.cursoandroid.DeserializerInstagram.DeserializeArrayResponseInstagram;
import com.example.angelruiz.cursoandroid.InterfazAPI_REST.IEndPointsInstagramApiRest;
import com.example.angelruiz.cursoandroid.RespuestaAPI_REST.ArrayResponseInstagram;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//here we can create more deserializers customs

public class AdapterDeserializerInstagram { //this class joins the pojo to deserialize and his deserializer
    public IEndPointsInstagramApiRest establishConnectionInstagramApiRest(Gson gson){ //this method receives the deserializer custom(show objects specific the array json instagram)
        Retrofit retrofit = new Retrofit.Builder() // we create our object retrofit normal
                .baseUrl(ConstantesApiRestInstagram.ROOT_URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson)) //we pass the deserializer custom for what not deserialize by default
                .build();
        return retrofit.create(IEndPointsInstagramApiRest.class);
    }

    public Gson buildGsonDeserializerMediaRecent(){ //build the deserializer custom gson
        GsonBuilder gsonBuilder = new GsonBuilder(); //we create the object gsonBuilder for create the derializer
        gsonBuilder.registerTypeAdapter(ArrayResponseInstagram.class, new DeserializeArrayResponseInstagram()); //we build the gson type adapter
        return gsonBuilder.create();                                                                           //this method join the array of response with his deserializer for show the data json of instagram custom
    }
}
