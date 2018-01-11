package com.pts3.sport.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.pts3.sport.dao.Classe;
import com.pts3.sport.dao.Eleve;

import java.util.ArrayList;

public class EleveManager extends Manager  {


    public EleveManager(Context context) {
        super(context);
    }

    public void ajouter(Eleve eleve) {

        ContentValues values = new ContentValues();

        values.put("nom_eleve", eleve.getNom());
        values.put("prenom_eleve", eleve.getPrenom());
        values.put("sexe", eleve.getSexe());

        db.insert("eleve", null, values);
    }

    /*
    * Récupérer toutes les élèves
     */
    public ArrayList<Eleve> recupererTout() {
        ArrayList<Eleve> eleves = new ArrayList<Eleve>();
        Cursor c =  db.rawQuery("SELECT * FROM eleve", null);
        if (c.moveToFirst())
        {
            do {

                Eleve eleve = new Eleve(c.getInt(c.getColumnIndex("id_eleve")),c.getString(c.getColumnIndex("nom_eleve")),c.getString(c.getColumnIndex("prenom_eleve")),c.getInt(c.getColumnIndex("sexe")),c.getInt(c.getColumnIndex("id_classe")));
                eleves.add(eleve);
            }
            while (c.moveToNext());
        }
        c.close(); // fermeture du curseur

        return eleves;

    }

    /*
    * Récupérer toutes les élèves d'une classe
     */
    public ArrayList<Eleve> recupererTout(Classe classe) {
        ArrayList<Eleve> eleves = new ArrayList<Eleve>();
        Cursor c =  db.rawQuery("SELECT * FROM eleve", null);
        if (c.moveToFirst())
        {
            do {
                if(classe.getId() == c.getInt(c.getColumnIndex("id_classe"))) {
                    Eleve eleve = new Eleve(c.getInt(c.getColumnIndex("id_eleve")), c.getString(c.getColumnIndex("nom_eleve")), c.getString(c.getColumnIndex("prenom_eleve")), c.getInt(c.getColumnIndex("sexe")), c.getInt(c.getColumnIndex("id_classe")));
                    eleves.add(eleve);
                }
            }
            while (c.moveToNext());
        }
        c.close();

        return eleves;

    }

    /*
    Recupérer par identifiant
     */

    public Eleve recuperer(int id) {
        Cursor c =  db.rawQuery("SELECT * FROM eleve WHERE id_eleve="+id, null);
        if(c.moveToFirst()) {
            Eleve eleve = new Eleve(c.getInt(c.getColumnIndex("id_eleve")),c.getString(c.getColumnIndex("nom_eleve")),c.getString(c.getColumnIndex("prenom_eleve")),c.getInt(c.getColumnIndex("sexe")),c.getInt(c.getColumnIndex("id_classe")));
            return eleve;
        }
        else {
            return null;
        }
    }
}
