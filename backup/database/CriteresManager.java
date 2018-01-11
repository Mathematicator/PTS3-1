package com.pts3.sport.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.pts3.sport.dao.Criteres;

import java.util.ArrayList;

/**
 * Created by Guillaume on 14/12/2017.
 */

public class CriteresManager extends Manager implements IManager<Criteres> {


    public CriteresManager(Context context) {
        super(context);
    }


    @Override
    public void ajouter(Criteres criteres){

        ContentValues values = new ContentValues();
        values.put("id_critere", criteres.getId());
        values.put("competence", criteres.getCompetence());
        values.put("type", criteres.getType());
        values.put("choix1",criteres.getChoix1());
        values.put("choix2", criteres.getChoix2());
        values.put("choix3", criteres.getChoix3());
        values.put("choix4",criteres.getChoix4());
        values.put("choix5", criteres.getChoix5());
        values.put("choix6", criteres.getChoix6());
        values.put("point1", criteres.getPoint1());
        values.put("point2",criteres.getPoint2());
        values.put("point3", criteres.getPoint3());
        values.put("point4", criteres.getPoint4());
        values.put("point5",criteres.getPoint5());
        values.put("point6", criteres.getPoint6());
        values.put("id_sport", criteres.getIdsport());

        Manager.db.insert("CRITERES", null, values);
    }
    public ArrayList<Criteres> recupererTout() {
        ArrayList<Criteres> criteres = new ArrayList<Criteres>();
        Cursor c =  db.rawQuery("SELECT * FROM CRITERES", null);
        if (c.moveToFirst())
        {
            do {
                Criteres critere = new Criteres(c.getInt(c.getColumnIndex("id_critere")),c.getInt(c.getColumnIndex("competence")),c.getInt(c.getColumnIndex("type")),c.getInt(c.getColumnIndex("id_sport")));

                critere.setChoix1(c.getString(c.getColumnIndex("choix1")));
                critere.setChoix2(c.getString(c.getColumnIndex("choix2")));
                critere.setChoix3(c.getString(c.getColumnIndex("choix3")));
                critere.setChoix4(c.getString(c.getColumnIndex("choix4")));
                critere.setChoix5(c.getString(c.getColumnIndex("choix5")));
                critere.setChoix6(c.getString(c.getColumnIndex("choix6")));
                critere.setPoint1(c.getInt(c.getColumnIndex("point1")));
                critere.setPoint2(c.getInt(c.getColumnIndex("point2")));
                critere.setPoint3(c.getInt(c.getColumnIndex("point3")));
                critere.setPoint4(c.getInt(c.getColumnIndex("point4")));
                critere.setPoint5(c.getInt(c.getColumnIndex("point5")));
                critere.setPoint6(c.getInt(c.getColumnIndex("point6")));
            }
            while (c.moveToNext());
        }
        c.close();

        return criteres;

    }

    @Override
    public void modifier(Criteres objet) {

    }

    @Override
    public void supprimer(Criteres objet) {

    }
    @Override
    public Criteres recuperer(int id) {
        return null;
    }


//    public ArrayList<Criteres> recupererCriteresNonSynchronisés() {
//
//        ArrayList<Criteres> criteres = new ArrayList<Criteres>();
//        Cursor c = db.rawQuery("SELECT * FROM CRITERES", null);
//        if (c.moveToFirst()) {
//            do {
//                Criteres critere = new Criteres(c.getInt(c.getColumnIndex("id_critere")),c.getInt(c.getColumnIndex("competence")),c.getInt(c.getColumnIndex("type")),c.getInt(c.getColumnIndex("id_sport")));
//                //Criteres critere = new Criteres(c.getString(c.getColumnIndex("choix1")),c.getString(c.getColumnIndex("choix2")),c.getString(c.getColumnIndex("choix3")),c.getString(c.getColumnIndex("choix4")),c.getString(c.getColumnIndex("choix5")),c.getString(c.getColumnIndex("choix6")),c.getFloat(c.getColumnIndex("point1")),c.getFloat(c.getColumnIndex("point2")),c.getFloat(c.getColumnIndex("point3")),c.getFloat(c.getColumnIndex("point4")),c.getFloat(c.getColumnIndex("point5")),c.getFloat(c.getColumnIndex("point6")));
//                critere.setChoix1(c.getString(c.getColumnIndex("choix1")));
//                critere.setChoix2(c.getString(c.getColumnIndex("choix2")));
//                critere.setChoix3(c.getString(c.getColumnIndex("choix3")));
//                critere.setChoix4(c.getString(c.getColumnIndex("choix4")));
//                critere.setChoix5(c.getString(c.getColumnIndex("choix5")));
//                critere.setChoix6(c.getString(c.getColumnIndex("choix6")));
//                critere.setPoint1(c.getInt(c.getColumnIndex("point1")));
//                critere.setPoint2(c.getInt(c.getColumnIndex("point2")));
//                critere.setPoint3(c.getInt(c.getColumnIndex("point3")));
//                critere.setPoint4(c.getInt(c.getColumnIndex("point4")));
//                critere.setPoint5(c.getInt(c.getColumnIndex("point5")));
//                critere.setPoint6(c.getInt(c.getColumnIndex("point6")));
//
//                //ajouter à la liste
//                criteres.add(critere);
//            } while (c.moveToNext());
//        }
//        c.close();
//        return criteres;
//    }


}
