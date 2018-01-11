package com.pts3.sport.dao;

/**
 * Created by Ragnulf on 12/12/2017.
 */

public class Classe {

    private int id;
    private String nom;
    private int niveau;
    private int id_prof;


    public Classe(int id, String nom, int niveau, int id_prof) {
        this.id = id;
        this.nom = nom;
        this.niveau = niveau;
        this.id_prof = id_prof;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getNiveau() {
        return niveau;
    }

    public int getId_prof() {
        return id_prof;
    }
}
