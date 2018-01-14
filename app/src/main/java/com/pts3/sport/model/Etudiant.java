package com.pts3.sport.model;

/**
 * Created by Ragnulf on 14/01/2018.
 */

public class Etudiant {

    private int id;
    private String nom;
    private String noteMotricite;
    private String noteMethode;
    private String noteRegles;
    private String notePerformances;
    private String noteApprendre;
    private String noteApproprier;
    private boolean sex;
    private boolean completeStatut;


    public Etudiant(int id, String nom, String noteMotricite, String noteMethode, String noteRegles, String notePerformances, String noteApprendre, String noteApproprier, boolean sex, boolean completeStatut) {
        this.nom = nom;
        this.noteMotricite = noteMotricite;
        this.noteMethode = noteMethode;
        this.noteRegles = noteRegles;
        this.notePerformances = notePerformances;
        this.noteApprendre = noteApprendre;
        this.noteApproprier = noteApproprier;
        this.sex=sex;
        this.id = id;
        this.completeStatut = completeStatut;
    }

    public String getNom() {
        return nom;
    }

    public String getNoteMotricite() {
        return noteMotricite;
    }

    public String getNoteMethode() {
        return noteMethode;
    }

    public String getNoteRegles() {
        return noteRegles;
    }

    public String getNotePerformances() {
        return notePerformances;
    }

    public String getNoteApprendre() {
        return noteApprendre;
    }

    public String getNoteApproprier() {
        return noteApproprier;
    }

    public boolean isSex() {
        return sex;
    }

    public int getId() {
        return id;
    }

    public boolean isCompleteStatut() {
        return completeStatut;
    }
}
