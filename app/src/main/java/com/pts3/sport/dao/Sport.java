package com.pts3.sport.dao;

/**
 * Created by Ragnulf on 10/12/2017.
 */

public class Sport {

    private int id_sport;
    private String nom_sport;
    private String trimestre;

    public void setId_sport(int id_sport) {
        this.id_sport = id_sport;
    }

    public void setNom_sport(String nom_sport) {
        this.nom_sport = nom_sport;
    }

    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }

    public int getId_sport() {

        return id_sport;
    }

    public String getNom_sport() {
        return nom_sport;
    }

    public String getTrimestre() {
        return trimestre;
    }

    public Sport(int id_sport, String nom_sport, String trimestre) {

        this.id_sport = id_sport;
        this.nom_sport = nom_sport;
        this.trimestre = trimestre;
    }
}
