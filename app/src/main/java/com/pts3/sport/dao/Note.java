package com.pts3.sport.dao;

/**
 * Created by Ragnulf on 10/12/2017.
 */

public class Note {

    private int id;
    private float note;
    private float motricite;
    private float performances;
    private float methodes;
    private float regles;
    private float apprendre;
    private float approprier;
    private int id_eleve;
    private int id_sport;

    public Note(int id_eleve, int id_sport) {
        this.id_eleve = id_eleve;
        this.id_sport = id_sport;
    }


    public void setNote(float note) {
        this.note = note;
    }

    public void setMotricite(float motricite) {
        this.motricite = motricite;
    }

    public void setPerformances(float performances) {
        this.performances = performances;
    }

    public void setMethodes(float methodes) {
        this.methodes = methodes;
    }

    public void setRegles(float regles) {
        this.regles = regles;
    }

    public void setApprendre(float apprendre) {
        this.apprendre = apprendre;
    }

    public void setApproprier(float approprier) {
        this.approprier = approprier;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMotricite() {
        return motricite;
    }

    public float getPerformances() {
        return performances;
    }

    public float getMethodes() {
        return methodes;
    }

    public float getRegles() {
        return regles;
    }

    public float getApprendre() {
        return apprendre;
    }

    public float getApproprier() {
        return approprier;
    }

    public int getId_eleve() {
        return id_eleve;
    }

    public int getId_sport() {
        return id_sport;
    }

    public int getId() {
        return id;
    }
    /*--------------------*

      */


}
