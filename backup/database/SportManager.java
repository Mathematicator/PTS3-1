package com.pts3.sport.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.pts3.sport.dao.Sport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guillaume on 14/12/2017.
 */

public class SportManager extends Manager {
    public SportManager(Context context) {
        super(context);
    }

    public void ajouterSport(Sport sport){
        ContentValues values = new ContentValues();

        values.put("id_sport",sport.getId());
        values.put("nom_sport",sport.getNom());
        values.put("trimestre",sport.getTrimestre());

        Manager.db.insert("sport", null, values);


    }
    public ArrayList<Sport> recupererCriteresNonSynchronisés() {

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



















    public Object recuperer(int id) {
        return null;
    }


    public List recupererTout() {
        return null;
    }


    public int ajouter(Object objet) {
        return 0;
    }


    public int modifier(Object objet) {
        return 0;
    }

    public void supprimer(Object objet) {

    }
}
