package com.pts3.sport.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.pts3.sport.dao.Note;
import com.pts3.sport.dao.NoteDetails;

import java.util.ArrayList;

public class NoteDetailsManager extends Manager {
    public NoteDetailsManager(Context context) {
        super(context);
    }


    public ArrayList<NoteDetails> recupererTout() {

        ArrayList<NoteDetails> notes = new ArrayList<NoteDetails>();
        Cursor c = db.rawQuery("SELECT * FROM note_details", null);
        if (c.moveToFirst()) {
            do {
                NoteDetails noteDetails = new NoteDetails(c.getInt(c.getColumnIndex("id")),c.getString(c.getColumnIndex("details")),c.getInt(c.getColumnIndex("id_note")));
                notes.add(noteDetails);

            } while (c.moveToNext());
        }
        c.close();
        return notes;
    }

    public NoteDetails recuperer(Note noteUser) {

        Cursor c = db.rawQuery("SELECT * FROM note_details WHERE id_note="+noteUser.getId(), null);
        if (c.moveToFirst()) {
            NoteDetails noteDetails = new NoteDetails(c.getInt(c.getColumnIndex("id")),c.getString(c.getColumnIndex("details")),c.getInt(c.getColumnIndex("id_note")));
            return noteDetails;
        }
        else {
            c.close();
            return null;
        }
    }


    public void ajouter(NoteDetails noteDetails) {
        ContentValues values = new ContentValues();
        Log.d("insert", "inserted data noteDetails");
        values.put("details", noteDetails.getDetails());
        values.put("id_note", noteDetails.getId_note());

        Manager.db.insert("note_details", null, values);
    }

    public void modifier(NoteDetails noteDetails) {
        ContentValues values = new ContentValues();

        Log.d("méthode modifier deta", noteDetails.getDetails());
        Log.d("méthode modifier id:", ""+noteDetails.getId_note());
        values.put("details", noteDetails.getDetails());

        Manager.db.update("note_details", values, "id_note='"+noteDetails.getId_note()+"'", null);
    }
}
