package com.pts3.sport.network;

import android.content.Context;
import android.net.Network;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.pts3.sport.dao.Note;
import com.pts3.sport.database.NoteManager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Ragnulf on 13/12/2017.
 */
/*
// Cette classe permet la sauvegarde des données interne vers la base de donnée externe

 */
public class NetworkSaveData {

    private Context context;

    public NetworkSaveData(Context context) {
        this.context = context;


        NoteManager noteManager = new NoteManager(context);
        ArrayList<Note> notes = noteManager.recupererTout();
        if(notes != null) {
            for (Note n : notes) {

                String q = "INSERT INTO `NOTE` (`note`, `note_motricite`, `note_performances`, `note_methodes`, `note_regles`, `note_apprendre`, `note_approprier`, `id_eleve`, `id_sport`) VALUES ('" + n.getId() + "', '" + n.getMotricite() + "', '" + n.getPerformances() + "', '" + n.getMethodes() + "', '" + n.getRegles() + "', '" + n.getApprendre() + "', '" + n.getApproprier() + "', '" + n.getId_eleve() + "', '" + n.getId_sport() + "');";

                String url_save_note = null;
                try {
                    url_save_note = "http://projetut.pe.hu/tut.php?sql=" + URLEncoder.encode(q, "UTF-8");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Log.d("save url ", url_save_note);
                save(url_save_note); //lancer la requete.
            }
        }
    }


    private void save(String url) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                toast("Erreur de connexion");
            }
        }
        );

        VolleyHTTPSingleton.getInstance(context).addToRequestQueue(stringRequest);

    }

    private void toast(String text) {

        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

    }
}
