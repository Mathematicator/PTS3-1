package com.pts3.sport;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.pts3.sport.dao.NoteDetails;
import com.pts3.sport.dao.NoteJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ragnulf on 01/01/2018.
 */

public class CustomAdapter extends ArrayAdapter<CheckboxModel> {

    private ArrayList<CheckboxModel> modelItems = null;
    private Context context;
    private String view;
    public static String notesDetails = "{\"apprendre\":[null,null,null,null,null,null],\"approprier\":[null,null,null,null,null,null],\"methode\":[null,null,null,null,null,null],\"motricite\":[null,null,null,null,null,null],\"regle\":[null,null,null,null,null,null]}";
    private JSONObject reader;
    private Gson gson;
    public static NoteJSON notes;
    public static double noteTotal;

    public CustomAdapter(Context context, ArrayList<CheckboxModel> resource,String view) {
        super(context,R.layout.row,resource);
        this.context = context;
        this.modelItems = resource;
        this.view = view;

        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
        notes = new NoteJSON(new Object[]{0.0,0.0,0.0,0.0,0.0,0.0},new Object[]{0.0,0.0,0.0,0.0,0.0,0.0},new Object[]{0.0,0.0,0.0,0.0,0.0,0.0},new Object[]{0.0,0.0,0.0,0.0,0.0,0.0},new Object[]{0.0,0.0,0.0,0.0,0.0,0.0});
        //initialiser input note
        try {
            JSONObject reader = new JSONObject(notesDetails);
            NoteJSON n = gson.fromJson(reader.toString(),NoteJSON.class);
            noteTotal = n.getTotalApprendre()+n.getTotalMethode()+n.getTotalRegle()+n.getTotalApproprier()+n.getTotalMotricite();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.row, parent, false);
        }
        final CheckBox cb = (CheckBox) convertView.findViewById(R.id.check);

        cb.setText(modelItems.get(position).getName());
        ((ListView) parent).setItemChecked(position, modelItems.get(position).isChecked());
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parent = (View)v.getParent().getParent().getParent().getParent();
                TextView note = parent.findViewById(R.id.note);
                float resultat = Float.parseFloat(note.getText().toString());

                // prepare json object
                try {
                    reader = new JSONObject(notesDetails);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(cb.isChecked()) {
                    if(parent != null) {
                        resultat = resultat + modelItems.get(position).getNote();
                        note.setText(Float.toString(resultat));
                        // with position and layout value we can insert data in json format
                        buildJSON(view,position,true);

                    }
                }
                else {
                    if(parent != null) {
                        resultat = resultat - modelItems.get(position).getNote();
                        note.setText(Float.toString(resultat));

                        buildJSON(view,position,false);

                    }
                }
            }
        });


        return convertView;
    }

    private void buildJSON(String view, int position, boolean check) {

        if(view.equals("motricite") || view.equals("methode") || view.equals("regle")
                || view.equals("apprendre") || view.equals("approprier")) {

            try {

                JSONArray object = reader.getJSONArray(view);
                if(check)
                    object.put(position, modelItems.get(position).getNote());
                else
                    object.put(position, null);

                notesDetails = reader.toString();
                notes = gson.fromJson(notesDetails,NoteJSON.class);

                Log.d("json object", reader.toString());


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }
/*
    private void updateViewNote(String view, View parent) {

        switch (view){
            case "motricite":
                TextView noteMotricite = parent.findViewById(R.id.noteMotricite);
                noteMotricite.setText(""+notes.getTotalMotricite());


                break;
            case "methode":
                TextView noteMethode = parent.findViewById(R.id.noteMethode);

                break;
            case "regle":
                TextView noteRegle = parent.findViewById(R.id.noteRegle);
                break;
            case "apprendre":
                TextView noteApprendre = parent.findViewById(R.id.noteApprendre);
                break;
            case "approprier":
                TextView noteApproprier = parent.findViewById(R.id.noteApproprier);

                break;

        }

    }
*/

}
