package com.webactiviti.gestionfragment.model.webservice;


import org.jetbrains.annotations.NotNull;

import java.net.HttpURLConnection;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



class OkHttpUtils {

    /**
     * Réalise une requête GET avec l'URL transmise en paramètre et retourne le résultat
     */
    @NotNull
    static String sendGetOkHttpRequest(String url) throws Exception {

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
            return Objects.requireNonNull(response.body()).string();
        }
    }
}