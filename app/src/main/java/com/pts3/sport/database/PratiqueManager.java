package com.pts3.sport.database;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.pts3.sport.dao.Classe;
import com.pts3.sport.dao.Sport;

import java.util.ArrayList;

/**
 * Created by Ragnulf on 31/12/2017.
 */

public class PratiqueManager extends Manager {

    public PratiqueManager(Context context) {
        super(context);
    }


    /*
    * Retourner toutes les sports pratiquer par une classe
     */

    public ArrayList<Sport> getSports(Classe classe) {
        return getSports(classe.getNom());
    }
    public ArrayList<Sport> getSports(String classe) {
        Cursor c = db.rawQuery("SELECT SPORT.id_sport,SPORT.nom_sport,SPORT.trimestre FROM PRATIQUE CROSS JOIN SPORT CROSS JOIN CLASSE WHERE SPORT.id_sport=PRATIQUE.id_sport AND CLASSE.id_classe=PRATIQUE.id_classe AND CLASSE.nom_classe='"+classe+"'",null);
        ArrayList<Sport> sports = new ArrayList<Sport>();
        if(c.moveToFirst()) {
            do {
                Sport sport = new Sport(c.getInt(c.getColumnIndex("id_sport")),c.getString(c.getColumnIndex("nom_sport")), c.getString(c.getColumnIndex("trimestre")));
                sports.add(sport);
                Log.d("sport addedd", sport.getNom_sport());
            }
            while(c.moveToNext());
        }
        c.close();
        return sports;
    }
}
