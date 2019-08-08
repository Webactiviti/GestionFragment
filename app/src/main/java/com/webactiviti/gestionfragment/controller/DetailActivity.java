package com.webactiviti.gestionfragment.controller;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.webactiviti.GestionFragment.R;
import com.webactiviti.gestionfragment.view.FragmentDetail;


import com.webactiviti.gestionfragment.model.beans.Fields;

import java.util.Objects;

// affiche le détail de l'activité sélectionnée
public class DetailActivity extends AppCompatActivity  {

    final static String FIELDS_KEY = "FIELDS_KEY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Données récupération du Fields transmis
        Fields fields = (Fields) Objects.requireNonNull(getIntent().getExtras()).getSerializable(FIELDS_KEY);
        FragmentTransaction fragmentTransaction ;

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction() ;

        // transmission de l'objet fields au fragment
        assert fields != null;
        fragmentTransaction.replace(R.id.flyFrame2, FragmentDetail.newInstance( fields )).commit();


    }


}
