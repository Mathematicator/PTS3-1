package com.pts3.sport.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.pts3.sport.dao.Note;

import java.util.ArrayList;


public class NoteManager extends Manager implements IManager<Note> {


    public NoteManager(Context context) {
      super(context);
    }

    @Override
    public Note recuperer(int id) {
        return null;
    }


    public void ajouter(Note note) {

        ContentValues values = new ContentValues();
        //values.put("note", note.getId());
        values.put("note_motricite", note.getMotricite());
        values.put("note_performances", note.getPerformances());
        values.put("note_methodes", note.getMethodes());
        values.put("note_apprendre", note.getApprendre());
        values.put("note_approprier", note.getApprendre());
        values.put("note_regles", note.getRegles());
        values.put("id_eleve", note.getId_eleve());
        values.put("id_sport", note.getId_sport());

        Manager.db.insert("note", null, values);
    }



    @Override
    public ArrayList<Note> recupererTout() {

        ArrayList<Note> notes = new ArrayList<Note>();
        Cursor c = db.rawQuery("SELECT * FROM note", null);
        if (c.moveToFirst()) {
            do {
                Note note = new Note(c.getInt(c.getColumnIndex("id_eleve")),c.getInt(c.getColumnIndex("id_sport")));
                note.setApprendre(c.getFloat(c.getColumnIndex("note_apprendre")));
                note.setApproprier(c.getFloat(c.getColumnIndex("note_approprier")));
                note.setMethodes(c.getFloat(c.getColumnIndex("note_methodes")));
                note.setRegles(c.getFloat(c.getColumnIndex("note_regles")));
                note.setPerformances(c.getFloat(c.getColumnIndex("note_performances")));
                note.setMotricite(c.getFloat(c.getColumnIndex("note_motricite")));
                note.setId(c.getInt(c.getColumnIndex("note")));

                //ajouter à la liste
                notes.add(note);
            } while (c.moveToNext());
        }
        c.close();
        return notes;
    }

    @Override
    public void modifier(Note objet) {

    }

    @Override
    public void supprimer(Note objet) {

    }


}
