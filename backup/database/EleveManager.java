package com.pts3.sport.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pts3.sport.dao.Eleve;

import java.util.ArrayList;

/*
Ancien version à changer ..
 */
public class EleveManager  {

    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;


    public EleveManager(Context context) {
       maBaseSQLite = MySQLite.getInstance(context);
       db = maBaseSQLite.getReadableDatabase();
    }

    public void ajouterEleve(Eleve eleve) {

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
                Eleve eleve = new Eleve(c.getString(c.getColumnIndex("nom_eleve")),c.getString(c.getColumnIndex("prenom_eleve")),c.getInt(c.getColumnIndex("sexe")),c.getInt(c.getColumnIndex("id_classe")));
                eleves.add(eleve);
            }
            while (c.moveToNext());
        }
        c.close(); // fermeture du curseur

        return eleves;

    }
}
