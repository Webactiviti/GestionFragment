package com.webactiviti.gestionfragment.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import com.webactiviti.GestionFragment.R;
import com.webactiviti.gestionfragment.view.FragmentDetail;
import com.webactiviti.gestionfragment.view.FragmentMain;

import com.webactiviti.gestionfragment.model.beans.Fields;
import com.webactiviti.gestionfragment.model.webservice.OpenDataWS;
import com.webactiviti.gestionfragment.view.FieldAdapter;



public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        FieldAdapter.OnFieldsListener,
        FragmentMain.OnFragmentInteractionListener{

    //Composants graphiques
    private TextView ttvInfo;

    private Button btnCharger;

    //Données contenant les évênements
    private ArrayList<Fields> fields;

    private FieldAdapter fieldAdapter;


    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ttvInfo = findViewById(R.id.ttvInfo);
        btnCharger = findViewById(R.id.btnCharger);

        btnCharger.setOnClickListener(this);

        fields = new ArrayList<>();
        fieldAdapter = new FieldAdapter(fields, this);

    }

    // Gestion du click bouton charge
    @Override
    public void onClick(View v) {

        try {
            // Lancement de la tache asynchrone
            TacheAsynchrone TacheAsynchrone = new TacheAsynchrone();
            TacheAsynchrone.execute();
        } catch (Exception e) {
            e.printStackTrace();
            ttvInfo.setText(e.getMessage());
        }

    }

    /**
     * Méthode appelée lors d'un clic sur une ligne et en paramètre le fields en question
     */
    @Override
    public void onClick(Fields fields) {

        //Je lance un nouvel écran et je lui transmets l'évènement à afficher
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.FIELDS_KEY, fields);

        startActivity(intent);


    }

    @Override
    public void onFragmentInteraction(Fields fields) {
        //Affiche le deuxième fragment en mode mobile
        if ( findViewById(R.id.flyFrame2) !=null  ){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction =fragmentManager.beginTransaction() ;
            // transmission de l'objet fields au fragment
            fragmentTransaction.replace(R.id.flyFrame2, FragmentDetail.newInstance( fields )).commit();

        }
        else{
            onClick( fields);
        }

    }

    public void afficheFragment(){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction =fragmentManager.beginTransaction() ;

        // transmission de l'objet fields au fragment
        fragmentTransaction.replace(R.id.flyFrame1, FragmentMain.newInstance( fields )).commit();
    }



    // Tache asynchrone  implementé dans la classe principale
    @SuppressLint("StaticFieldLeak")
    public class TacheAsynchrone extends AsyncTask {

        private ArrayList<Fields> resultat = null;
        private Exception exception = null;

        /**
         * Appel asynchrone : exécuté sur un thread à part
         * On ne peut pas toucher aux éléments graphiques mais on peut y faire des traitements longs
         */
        @Override
        protected Object doInBackground(Object[] params) {

            try {
                resultat = OpenDataWS.getFieldsDuServeur();
            } catch (Exception e) {
                exception = e;
            }

            return null;
        }

        /**
         * Appelée sur le thread principal, on peut toucher aux éléments graphiques
         * mais on ne peut pas y faire de traitements longs
         */
        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(Object o) {
            String messError ;

            if (exception != null) {
                super.onPostExecute(o);
                //Échec
                exception.printStackTrace();
                messError ="Erreur : " + exception.getMessage();
                ttvInfo.setText(messError);
                ttvInfo.setTextColor(Color.RED);
            } else {
                //efface tous les anciens fields
                fields.clear();
                // copy les fields reçus
                fields.addAll(resultat);
                fieldAdapter.notifyDataSetChanged();
                messError ="Chargement terminé";
                ttvInfo.setText(messError);
                ttvInfo.setTextColor(Color.BLUE);
                afficheFragment();
            }
        }
    }
}
