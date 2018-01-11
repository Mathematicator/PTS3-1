package com.pts3.sport;

public class CheckboxModel {

    private String name;
    private boolean checked;
    private float note;


    public CheckboxModel(String name, boolean checked, float note) {
        this.name = name;
        this.checked = checked;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public boolean isChecked() {
        return checked;
    }


    public float getNote() {
        return note;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setNote(float note) {
        this.note = note;
    }
}
