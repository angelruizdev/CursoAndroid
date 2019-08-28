package com.example.angelruiz.cursoandroid.DeserializerInstagram;

import com.example.angelruiz.cursoandroid.Arrays.ArrayInstagramObjects;
import com.example.angelruiz.cursoandroid.DatosAPI_REST_Instagram.JsonKeysInstagram;
import com.example.angelruiz.cursoandroid.RespuestaAPI_REST.ArrayResponseInstagram;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DeserializeArrayResponseInstagram implements JsonDeserializer<ArrayResponseInstagram> { //we implement the class of type ArrayResponseInstagram, for i can deserializer,  use the response class to do it

    @Override
    public ArrayResponseInstagram deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException { //we import this metod
        Gson gson = new Gson(); // we create a object gson to tell you wich class you showld deserializer, here is the who receives the response of instagram api
        ArrayResponseInstagram arrayResponseInstagram = gson.fromJson(json, ArrayResponseInstagram.class); //class for deserializer top line too ^
        JsonArray dataArrayRespInstagram = json.getAsJsonObject().getAsJsonArray(JsonKeysInstagram.NAME_ARRAY_INSTAGRAM); //in a object of type JsonArray we obtain as object json and array the data of array of instagram passing the name of this as parameter

        arrayResponseInstagram.setData(deserializerArrayInstagramObjects(dataArrayRespInstagram)); //this array arrayResponseInstagram requires a colection of type (pojo arrayInstagramObjects), we pass the metod deserializerArrayInstagramObjects
        //arrayResponseInstagram.setData(arrayInstagramObjects);                                    //why is this type and return a that colection, a su vez este metodo recibe como parametro un array con los datos de la api le pasamos dataArrayRespInstagram, para usarlos como json dentro del metodo
        //deserializerArrayInstagramObjects(dataArrayRespInstagram);
        return arrayResponseInstagram;
    }

    private ArrayList<ArrayInstagramObjects> deserializerArrayInstagramObjects (JsonArray dataArrayRespInstagram){ //we create this metod of type ArrayInstagramObjects we pass the array dataArrayRespInstagram fill from instagram, here we do the deserialization custom(only what we want to show form the api)
        ArrayList<ArrayInstagramObjects> arrayInstagramObjects = new ArrayList<>(); //we fill with values of object instagram data

        for (int i = 0; i < dataArrayRespInstagram.size(); i++) { // we recover the array for obtain the position of their object

            JsonObject arrayRespInstaObj = dataArrayRespInstagram.get(i).getAsJsonObject(); //this obj have all the data of array instagram *
            JsonObject objectUser = arrayRespInstaObj.getAsJsonObject(JsonKeysInstagram.OBJECT_USER); //get all the object of array arrayRespInstaObj *

            String id = objectUser.get(JsonKeysInstagram.USER_ID).getAsString(); //objectUser save all the obj, we obtain the id and fullName form this *
            String fullName = objectUser.get(JsonKeysInstagram.USER_FULLNAME).getAsString(); //* the value tha key is a String from the json instagram

            JsonObject objectImages = arrayRespInstaObj.getAsJsonObject(JsonKeysInstagram.OBJECT_IMAGES); //how the url are insise of 2 objects parents, we access to 2--
            JsonObject objectImagResolType = objectImages.getAsJsonObject(JsonKeysInstagram.OBJECT_IMAGES_RESOLUTION_TYPE); //--by obtain the url
            String url = objectImagResolType.get(JsonKeysInstagram.URL_IMAGE).getAsString(); //*

            JsonObject objectLikes = arrayRespInstaObj.getAsJsonObject(JsonKeysInstagram.OBJECT_LIKES); //*
            int likes = objectLikes.get(JsonKeysInstagram.LIKES_IMAGE).getAsInt(); //* the value tha key is a integer

            ArrayInstagramObjects arrayInstagramCurrent = new ArrayInstagramObjects();//--> analizar

               arrayInstagramCurrent.setIdUser(id); //we set with values obtain of object arrayRespInstaObj
               arrayInstagramCurrent.setFullNameUser(fullName);
               arrayInstagramCurrent.setImageUrlUser(url);
               arrayInstagramCurrent.setImageLikes(likes);

               arrayInstagramObjects.add(arrayInstagramCurrent); //we fill whit values set
        }

        return arrayInstagramObjects; //we return the array for use it
    }
}
