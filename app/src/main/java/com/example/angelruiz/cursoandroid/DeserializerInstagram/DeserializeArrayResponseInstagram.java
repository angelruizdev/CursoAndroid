package com.example.angelruiz.cursoandroid.DeserializerInstagram;

import com.example.angelruiz.cursoandroid.DatosAPI_REST_Instagram.JsonKeysInstagram;
import com.example.angelruiz.cursoandroid.RespuestaAPI_REST.ArrayResponseInstagram;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class DeserializeArrayResponseInstagram implements JsonDeserializer<ArrayResponseInstagram> {
    @Override
    public ArrayResponseInstagram deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        ArrayResponseInstagram arrayResponseInstagram = gson.fromJson(json, ArrayResponseInstagram.class);
        JsonArray dataArrayRespInstagram = json.getAsJsonObject().getAsJsonArray(JsonKeysInstagram.NAME_ARRAY); //name array of object instagram

        return null;
    }
}
