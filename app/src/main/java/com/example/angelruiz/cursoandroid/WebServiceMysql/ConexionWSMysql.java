package com.example.angelruiz.cursoandroid.WebServiceMysql;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConexionWSMysql extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection = null;//esta clase permite hacer la conexion al WS
        URL url = null;
        try {
            url = new URL(strings[0]);//la url esta en el primer parametro que recibe AsyncTask
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            assert url != null;
            connection = (HttpURLConnection)url.openConnection();
            connection.connect();
            int codigoConexion = connection.getResponseCode();//en esta variable guardamos la respuesta de la conexion
            if (codigoConexion == HttpURLConnection.HTTP_OK){//si la respuesta en codigoConexion, es 200, se hizo la conexion al WS
               InputStream in = new BufferedInputStream(connection.getInputStream());//en la variable in seguarda toda la informacion devuelta por el WS
               BufferedReader reader = new BufferedReader(new InputStreamReader(in));//creamos el objeto reader para leer esa informacion, la pasamos como parametro a InputStreamReader
               String line = "";
               StringBuffer stringBuffer = new StringBuffer();
                while ((line = reader.readLine())!= null){//iteramos la informacion, ahora la guardamos en line
                   stringBuffer.append(line);//en stringBuffer agregamos cada nilea devuelta por el WS, hasta que el reader encuentre un null
                }
               return stringBuffer.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
