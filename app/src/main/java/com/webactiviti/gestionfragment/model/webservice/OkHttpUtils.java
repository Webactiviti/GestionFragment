package com.webactiviti.gestionfragment.model.webservice;


import java.net.HttpURLConnection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class OkHttpUtils {

    /**
     * Réalise une requête GET avec l'URL transmise en paramètre et retourne le résultat
     */
    public static String sendGetOkHttpRequest(String url) throws Exception {

        OkHttpClient client = new OkHttpClient();

        //Création de la requête
        Request request = new Request.Builder().url(url).build();

        //Exécution de la requête
        Response response = client.newCall(request).execute();

        //Analyse du code retour
        if (response.code() != HttpURLConnection.HTTP_OK) {
            throw new Exception("Réponse du serveur incorrecte : " + response.code());
        }
        else {
            //Résultat de la requête
            return response.body().string();
        }
    }
}