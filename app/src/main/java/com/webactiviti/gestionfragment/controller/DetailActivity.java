package com.webactiviti.gestionfragment.controller;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.webactiviti.gestionfragment.view.FragmentDetail;
import fr.afpa.GestionFragment.R;
import com.webactiviti.gestionfragment.model.beans.Fields;


public class DetailActivity extends AppCompatActivity  {

    public final static String FIELDS_KEY = "FIELDS_KEY";

    //Composants graphiques
   // private TextView tv_titre, tv_detail;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

       // tv_titre = (TextView) findViewById(R.id.tv_titre);
        //tv_detail = (TextView) findViewById(R.id.tv_detail);

        //Données récupération du Fields transmis
        Fields fields = (Fields) getIntent().getExtras().getSerializable(FIELDS_KEY);

       // tv_titre.setText(fields.getNom_de_la_manifestation());
        //tv_detail.setText(fields.getDescriptif_long());

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction =fragmentManager.beginTransaction() ;

        // transmission de l'objet fields au fragment
        fragmentTransaction.replace(R.id.flyFrame2, FragmentDetail.newInstance( fields )).commit();


    }


}
