package com.pts3.sport.dao;


public class Pratique {

    private int id;
    private int id_sport;
    private int id_classe;


    public Pratique(int id, int id_sport, int id_classe) {
        this.id_sport = id_sport;
        this.id_classe = id_classe;
    }

    public int getId_sport() {
        return id_sport;
    }

    public int getId_classe() {
        return id_classe;
    }

    public int getId() {
        return id;
    }
}
