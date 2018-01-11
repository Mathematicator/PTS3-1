package com.pts3.sport.dao;

public class NoteDetails {

    private int id;
    private String details;
    private int id_note;

    //{"motricite":[1.5,1.5,null,1.5,null],"methode":[0.5,null,null,null,null],"regle":[null,null,null,null,null],"apprendre":[null,null,null,null,null],"approprier":[null,null,null,null,null]}
    public NoteDetails(int id, String details, int id_note) {
        this.id = id;
        this.details = details;
        this.id_note = id_note;
    }
    public NoteDetails(String details, int id_note) {
        this.id = id;
        this.details = details;
        this.id_note = id_note;
    }

    public int getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }

    public int getId_note() {
        return id_note;
    }
}
