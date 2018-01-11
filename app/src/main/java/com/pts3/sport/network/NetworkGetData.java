package com.pts3.sport.network;


import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.pts3.sport.database.Manager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ragnulf on 12/12/2017.
 */

/**
 * A refaire avec le model DAO..
 */

public class NetworkGetData {

    private Context context;

    public NetworkGetData(Context context){
        super();
        this.context = context;

        Manager manager = new Manager(context); // to save data

        load("http://www.projetut.pe.hu/tut.php?sql=SELECT%20*%20FROM%20ELEVE;", "Eleve");
        load("http://www.projetut.pe.hu/tut.php?sql=SELECT%20*%20FROM%20CRITERES;", "Criteres");
        load("http://www.projetut.pe.hu/tut.php?sql=SELECT%20*%20FROM%20PRATIQUE", "Pratique");
        load("http://www.projetut.pe.hu/tut.php?sql=SELECT%20*%20FROM%20SPORT;", "Sport");
        load("http://www.projetut.pe.hu/tut.php?sql=SELECT%20*%20FROM%20NOTE;", "Note");
        load("http://www.projetut.pe.hu/tut.php?sql=SELECT%20*%20FROM%20PROFESSEUR;", "Professeur");
        load("http://www.projetut.pe.hu/tut.php?sql=SELECT%20*%20FROM%20CLASSE;", "Classe");
        load("http://www.projetut.pe.hu/tut.php?sql=SELECT%20*%20FROM%20NOTE_DETAILS;", "NoteDetails");

    }
    private void load(String url, final String classe) {

        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("NetworkGetData", response.toString());
                        switch (classe) {
                            case "Professeur":
                                save_data_prof(response);
                                break;
                            case "Classe":
                                save_data_classe(response);
                                break;
                            case "Note":
                                save_data_note(response);
                                break;
                            case "Criteres":
                                save_data_critere(response);
                                break;
                            case "Eleve":
                                save_data_eleve(response);
                                break;
                            case "Sport":
                                save_data_sport(response);
                                break;
                            case "Pratique":
                                save_data_pratique(response);
                                break;
                            case "NoteDetails":
                                save_note_details(response);
                                break;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("NetworkGetData", "erreur de la requete" + " " + error.getMessage());
                    }
                }
        );

        VolleyHTTPSingleton.getInstance(context).addToRequestQueue(stringRequest);

    }

    /*
    Insérer les données récupérer en JSON dans la base de donnée interne
     */
    private void save_data_prof(JSONArray response) {

        for(int i=0;i<response.length();i++) {
            JSONObject jresponse = null;
            try {
                jresponse = response.getJSONObject(i);
                Log.d("NetworkGetData", "Loop numéro " + i);
                Log.d("NetworkGetData", "nomprof:" + jresponse.getString("id_prof"));
                Log.d("NetworkGetData", "nomprof:" + jresponse.getString("nom_prof"));
                Log.d("NetworkGetData", "nomprof:" + jresponse.getString("prenom_prof"));
                Log.d("NetworkGetData", "nomprof:" + jresponse.getString("mdp_prof"));
                //
                ContentValues values = new ContentValues();

                values.put("id_prof",jresponse.getString("id_prof"));
                values.put("nom_prof",jresponse.getString("nom_prof"));
                values.put("prenom_prof",jresponse.getString("prenom_prof"));
                values.put("mdp_prof",jresponse.getString("mdp_prof"));
                Manager.db.insert("professeur", null, values); //insérer les données dans la base de donnée interne

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    private void save_data_classe(JSONArray response) {

        for(int i=0;i<response.length();i++) {
            JSONObject jresponse = null;
            try {
                jresponse = response.getJSONObject(i);
                ContentValues values = new ContentValues();

                values.put("id_classe",jresponse.getString("id_classe"));
                values.put("nom_classe",jresponse.getString("nom_classe"));
                values.put("niveau_classe",jresponse.getString("niveau_classe"));
                values.put("id_prof",jresponse.getString("id_prof"));
                Manager.db.insert("classe", null, values); //insérer les données dans la base de donnée interne

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }


    private void save_data_critere(JSONArray response) {
        for(int i=0;i<response.length();i++) {
            JSONObject jresponse = null;
            try {
                jresponse = response.getJSONObject(i);
                ContentValues values = new ContentValues();

                values.put("id_critere", jresponse.getString("id_critere"));
                values.put("competence", jresponse.getString("competence"));
                values.put("type", jresponse.getString("type"));
                values.put("choix1", jresponse.getString("choix1"));
                values.put("choix2", jresponse.getString("choix2"));
                values.put("choix3", jresponse.getString("choix3"));
                values.put("choix4", jresponse.getString("choix4"));
                values.put("choix5", jresponse.getString("choix5"));
                values.put("choix6", jresponse.getString("choix6"));
                values.put("point1", jresponse.getString("point1"));
                values.put("point2", jresponse.getString("point2"));
                values.put("point3", jresponse.getString("point3"));
                values.put("point4", jresponse.getString("point4"));
                values.put("point5", jresponse.getString("point5"));
                values.put("point6", jresponse.getString("point6"));
                values.put("id_sport", jresponse.getString("id_sport"));

                Manager.db.insert("criteres", null, values); //insérer les données dans la base de donnée interne

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private void save_data_note(JSONArray response) {
        for(int i=0;i<response.length();i++) {
            JSONObject jresponse = null;
            try {
                jresponse = response.getJSONObject(i);
                ContentValues values = new ContentValues();

                values.put("note", jresponse.getString("note"));
                values.put("note_motricite", jresponse.getString("note_motricite"));
                values.put("note_performances", jresponse.getString("note_performances"));
                values.put("note_methodes", jresponse.getString("note_methodes"));
                values.put("note_apprendre", jresponse.getString("note_apprendre"));
                values.put("note_approprier", jresponse.getString("note_approprier"));
                values.put("note_regles", jresponse.getString("note_regles"));
                values.put("id_eleve", jresponse.getString("id_eleve"));
                values.put("id_sport", jresponse.getString("id_sport"));
                Manager.db.insert("note", null, values); //insérer les données dans la base de donnée interne

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

    private void save_data_eleve(JSONArray response) {
        for(int i=0;i<response.length();i++) {
            JSONObject jresponse = null;
            try {
                jresponse = response.getJSONObject(i);
                ContentValues values = new ContentValues();

                values.put("id_eleve",jresponse.getString("id_eleve"));
                values.put("nom_eleve",jresponse.getString("nom_eleve"));
                values.put("prenom_eleve",jresponse.getString("prenom_eleve"));
                values.put("sexe",jresponse.getString("sexe"));
                values.put("id_classe",jresponse.getString("id_classe"));
                Manager.db.insert("eleve", null, values); //insérer les données dans la base de donnée interne

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private void save_data_sport(JSONArray response) {
        for(int i=0;i<response.length();i++) {
            JSONObject jresponse = null;
            try {
                jresponse = response.getJSONObject(i);
                ContentValues values = new ContentValues();

                values.put("id_sport",jresponse.getString("id_sport"));
                values.put("nom_sport",jresponse.getString("nom_sport"));
                values.put("trimestre",jresponse.getString("trimestre"));

                Manager.db.insert("sport", null, values); //insérer les données dans la base de donnée interne

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private void save_data_pratique(JSONArray response) {
        for(int i=0;i<response.length();i++) {
            JSONObject jresponse = null;
            try {
                jresponse = response.getJSONObject(i);
                ContentValues values = new ContentValues();

                values.put("id",jresponse.getString("id"));
                values.put("id_sport",jresponse.getString("id_sport"));
                values.put("id_classe",jresponse.getString("id_classe"));

                Manager.db.insert("pratique", null, values); //insérer les données dans la base de donnée interne

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


    private void save_note_details(JSONArray response) {
        for(int i=0;i<response.length();i++) {
            JSONObject jresponse = null;
            try {
                jresponse = response.getJSONObject(i);
                ContentValues values = new ContentValues();

                values.put("id",jresponse.getString("id"));
                values.put("details",jresponse.getString("details"));
                values.put("id_note",jresponse.getString("id_note"));

                Manager.db.insert("note_details", null, values); //insérer les données dans la base de donnée interne

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

}
