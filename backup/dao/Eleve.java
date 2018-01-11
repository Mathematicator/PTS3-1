package com.pts3.sport.dao;

/**
 * Created by Ragnulf on 10/12/2017.
 */

public class Eleve {

    private int id_eleve;
    private String nom_eleve;
    private String prenom_eleve;
    private int sexe;
    private int id_classe;

    public Eleve(String nom_eleve, String prenom_eleve, int sexe, int id_classe) {
        //this.id_eleve = id_eleve;
        this.nom_eleve = nom_eleve;
        this.prenom_eleve = prenom_eleve;
        this.sexe = sexe;
        this.id_classe = id_classe;
    }


    public String getNom() {
        return nom_eleve;
    }

    public String getPrenom() {
        return prenom_eleve;
    }

    public int getSexe() {
        return sexe;
    }



    public int getId_eleve() {
        return id_eleve;
    }
}
