package com.pts3.sport.dao;

/**
 * Created by Ragnulf on 12/12/2017.
 */

public class Professeur {
    public int id;
    public String nom;
    public String prenom;
    public String mdp;

    public Professeur(int id, String nom, String prenom, String mdp) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMdp() {
        return mdp;
    }
}
