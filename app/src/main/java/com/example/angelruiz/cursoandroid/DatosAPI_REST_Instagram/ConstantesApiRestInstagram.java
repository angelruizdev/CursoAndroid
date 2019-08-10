package com.example.angelruiz.cursoandroid.DatosAPI_REST_Instagram;

public final class ConstantesApiRestInstagram { //datos de la api instagram
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL_BASE = "https://api.instagram.com" + VERSION; //https://api.instagram.com/v1/ --> url base
    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent";
    public static final String KEY_ACCES_TOKEN = "?access_token=";
    public static final String ACCES_TOKEN = "17656472546.e329a89.7d53b35629fe416c817f2d77fcd4cc18";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + KEY_ACCES_TOKEN + ACCES_TOKEN; //users/self/media/recent/?access_token=17656472546.e329a89.7d53b35629fe416c817f2d77fcd4cc18 --> endpoint

}
