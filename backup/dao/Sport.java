package com.pts3.sport.dao;

/**
 * Created by Ragnulf on 10/12/2017.
 */

public class Sport {

    private int id;
    private String nom;
    private String trimestre;

    public Sport(int id, String nom, String trimestre) {
        this.id = id;
        this.nom = nom;
        this.trimestre = trimestre;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getTrimestre() {
        return trimestre;
    }
}
