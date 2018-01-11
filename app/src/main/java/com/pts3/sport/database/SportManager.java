package com.pts3.sport.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.pts3.sport.dao.Sport;

import java.util.ArrayList;


public class SportManager extends Manager {
    public SportManager(Context context) {
        super(context);
    }

    public void ajouter(Sport sport){
        ContentValues values = new ContentValues();

        values.put("id_sport",sport.getId_sport());
        values.put("nom_sport",sport.getNom_sport());
        values.put("trimestre",sport.getTrimestre());

        Manager.db.insert("sport", null, values);


    }
    public ArrayList<Sport> recupererTout() {

        ArrayList<Sport> sports = new ArrayList<Sport>();
        Cursor c = db.rawQuery("SELECT * FROM sport", null);
        if (c.moveToFirst()) {
            do {
                Sport sport = new Sport(c.getInt(c.getColumnIndex("id_sport")),c.getString(c.getColumnIndex("nom_sport")),c.getString(c.getColumnIndex("trimestre")));


                //ajouter à la liste
                sports.add(sport);
            } while (c.moveToNext());
        }
        c.close();
        return sports;
    }
    /*
    * Récupérer objet
    * Paramtère : nom du sport
     */
    public Sport recuperer(String sport) {
        Cursor c = db.rawQuery("SELECT * FROM SPORT WHERE nom_sport='"+sport+"'",null);
        if(c.moveToFirst()) {
            Sport objet = new Sport(c.getInt(c.getColumnIndex("id_sport")),c.getString(c.getColumnIndex("nom_sport")),c.getString(c.getColumnIndex("trimestre")));
            return objet;
        }
        else {
            return null;
        }
    }
}
