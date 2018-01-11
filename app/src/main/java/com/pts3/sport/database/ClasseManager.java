package com.pts3.sport.database;

import android.content.Context;
import android.database.Cursor;

import com.pts3.sport.dao.Classe;

import java.util.ArrayList;


public class ClasseManager extends Manager {


    public ClasseManager(Context context) {
        super(context);
    }
    /*
      * Récupérer une classe object
     */
    public Classe recuperer(String classe) {

        Cursor c = db.rawQuery("SELECT * FROM CLASSE WHERE nom_classe='"+classe+"'",null);
        if(c.moveToFirst()) {
            Classe object = new Classe(c.getInt(c.getColumnIndex("id_classe")),c.getString(c.getColumnIndex("nom_classe")),c.getInt(c.getColumnIndex("niveau_classe")),c.getInt(c.getColumnIndex("id_prof")));
            return object;
        }
        else {
            return null;
        }
    }
    public ArrayList<Classe> recupererTout() {
        ArrayList<Classe> classes = new ArrayList<Classe>();
        Cursor c =  db.rawQuery("SELECT * FROM CLASSE", null);
        if (c.moveToFirst()) {
            do {
                Classe classe = new Classe(c.getInt(c.getColumnIndex("id_classe")),c.getString(c.getColumnIndex("nom_classe")),c.getInt(c.getColumnIndex("niveau_classe")),c.getInt(c.getColumnIndex("id_prof")));
                classes.add(classe);

            }
            while (c.moveToNext());
        }
        c.close();
        return classes;
    }

}
