package com.example.angelruiz.cursoandroid.SerializerAPI_REST;

import com.example.angelruiz.cursoandroid.ArraysAPI_REST.ArrayWSMysqlApi;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class SerializerArrayWSMysqlApi implements JsonSerializer<ArrayWSMysqlApi> {

    @Override
    public JsonElement serialize(ArrayWSMysqlApi src, Type typeOfSrc, JsonSerializationContext context) {

       JsonObject jsonObject = new JsonObject();
         jsonObject.addProperty("idPersona", src.getIdPersona());
         jsonObject.addProperty("numeroFolio", src.getNumeroFolio());
         jsonObject.addProperty("nombre", src.getNombre());
         jsonObject.addProperty("profesion", src.getProfesion());
        return jsonObject;
    }
}
