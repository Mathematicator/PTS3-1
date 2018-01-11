package com.pts3.sport.dao;

import java.util.Arrays;

/**
 * Created by Ragnulf on 05/01/2018.
 */

public class NoteJSON {

    private Object[] motricite;
    private Object[] methode;
    private Object[] regle;
    private Object[] apprendre;
    private Object[] approprier;

    public NoteJSON(Object[] motricite, Object[] methode, Object[] regle, Object[] apprendre, Object[] approprier) {
        this.motricite = motricite;
        this.methode = methode;
        this.regle = regle;
        this.apprendre = apprendre;
        this.approprier = approprier;
    }


    public double getTotalMotricite() {
        return getTotal(motricite);
    }
    public double getTotalMethode() {
        return getTotal(methode);
    }
    public double getTotalRegle() {
        return getTotal(regle);
    }
    public double getTotalApprendre() {
        return getTotal(apprendre);
    }
    public double getTotalApproprier() {
        return getTotal(approprier);
    }

    private double getTotal(Object[] array) {

        double resultat = 0;

        for(int i = 0; i < array.length; i++) {
            if(array[i] != null) {
                resultat += (double)array[i];
            }
        }

        return resultat;
    }

    @Override
    public String toString() {
        return "NoteJSON{" +
                "motricite=" + Arrays.toString(motricite) +
                ", methode=" + Arrays.toString(methode) +
                ", regle=" + Arrays.toString(regle) +
                ", apprendre=" + Arrays.toString(apprendre) +
                ", approprier=" + Arrays.toString(approprier) +
                '}';
    }

    public Object[] getMotricite() {
        return motricite;
    }

    public Object[] getMethode() {
        return methode;
    }

    public Object[] getRegle() {
        return regle;
    }

    public Object[] getApprendre() {
        return apprendre;
    }

    public Object[] getApproprier() {
        return approprier;
    }
}
