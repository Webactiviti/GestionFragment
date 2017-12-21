package com.webactiviti.gestionfragment.model.beans;

import java.io.Serializable;



public class Fields implements Serializable {

    private String descriptif_court;
    private String descriptif_long;
    private String nom_de_la_manifestation;

    public String getDescriptif_court() {
        return descriptif_court;
    }

    public void setDescriptif_court(String descriptif_court) {
        this.descriptif_court = descriptif_court;
    }

    public String getDescriptif_long() {
        return descriptif_long;
    }

    public void setDescriptif_long(String descriptif_long) {
        this.descriptif_long = descriptif_long;
    }

    public String getNom_de_la_manifestation() {
        return nom_de_la_manifestation;
    }

    public void setNom_de_la_manifestation(String nom_de_la_manifestation) {
        this.nom_de_la_manifestation = nom_de_la_manifestation;
    }
}
