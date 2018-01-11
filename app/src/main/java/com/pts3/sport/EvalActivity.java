package com.pts3.sport;

import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.pts3.sport.dao.Criteres;
import com.pts3.sport.dao.Eleve;
import com.pts3.sport.dao.Note;
import com.pts3.sport.dao.NoteDetails;
import com.pts3.sport.dao.NoteJSON;
import com.pts3.sport.dao.Sport;
import com.pts3.sport.database.CriteresManager;
import com.pts3.sport.database.EleveManager;
import com.pts3.sport.database.NoteDetailsManager;
import com.pts3.sport.database.NoteManager;
import com.pts3.sport.database.SportManager;
import com.pts3.sport.network.NetworkSyncData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
/*
* Evaluation - Sport
* Taoufik
 */
public class EvalActivity extends AppCompatActivity {

    private ArrayList<Criteres> listeCriteres;
    private Context context = this;
    private SharedPreferences.Editor editor;
    private TextView noteGlobale;
    private LinearLayout linearLayout,linear1;
    private float resultat;
    private int numTest=0;
    private Button btnSave;
    private SharedPreferences preferences;
    NoteJSON noteDetailsEleve;
    NoteDetails noteDetailsEleveJSON;
    //int motricite=0,methodes=0,regles=0,apprendre=0,approprier=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eval_ind);

        linearLayout = (LinearLayout) findViewById(R.id.layoutEvalInd);
        btnSave = (Button) findViewById(R.id.btnSaveNote);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        noteGlobale = (TextView) findViewById(R.id.note);
        noteGlobale.setText("0");


        final SportManager sportManager = new SportManager(this);
        final Sport sport = sportManager.recuperer(preferences.getString("sport", ""));

        //l'étudiant est déjà noté pour ce sport?
        final NoteManager noteManager = new NoteManager(this);
        Eleve eleve = new EleveManager(this).recuperer(preferences.getInt("eleve",1));
        final Note noteEleve = noteManager.recuperer(eleve,sport);


        //notes complexe to initialise boolean
        final NoteDetailsManager noteDetailsManager = new NoteDetailsManager(this);
        if(noteEleve != null) {

            noteDetailsEleveJSON = noteDetailsManager.recuperer(noteEleve);
            try {
                JSONObject reader = new JSONObject(noteDetailsEleveJSON.getDetails());
                noteDetailsEleve = new GsonBuilder().create().fromJson(reader.toString(),NoteJSON.class);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        CriteresManager criteresManager = new CriteresManager(this);

        listeCriteres = criteresManager.recupererTout(sport.getId_sport());

        //Create model
        ArrayList<String> layout =  new ArrayList<String>();
        layout.add("motricite");
        layout.add("methode");
        layout.add("regle");
        layout.add("apprendre");
        layout.add("approprier");

        ArrayList<Integer> views =  new ArrayList<Integer>();
        views.add(R.id.listView1);
        views.add(R.id.listView2);
        views.add(R.id.listView3);
        views.add(R.id.listView4);
        views.add(R.id.listView5);

        Log.d("listeCriteres", ""+listeCriteres.size());
        // Créer les cases à cocher
        for(int i = 0; i<listeCriteres.size(); i++) {

            ArrayList<Boolean> booleans = new ArrayList<Boolean>();

            // i = 0    - notes de motricité!
            if(noteDetailsEleve != null) {
                //charger les notes dans adaptater.
                CustomAdapter.notesDetails = noteDetailsEleveJSON.getDetails();

                if(i ==  0) {
                    for(Object n : noteDetailsEleve.getMotricite()){
                        Log.d("notes motricite : ",""+n);
                        if(n == null || n.equals(0)) {
                            booleans.add(false);
                        }
                        else {
                            booleans.add(true);
                        }
                    }
                }
                if(i == 1) {
                    for(Object n : noteDetailsEleve.getMethode()){
                        Log.d("notes methode : ",""+n);
                        if(n == null || n.equals(0)) {
                            booleans.add(false);
                        }
                        else {
                            booleans.add(true);
                        }
                    }
                }
                if(i == 2) {
                    for(Object n : noteDetailsEleve.getRegle()){
                        Log.d("notes apprendre : ",""+n);
                        if(n == null || n.equals(0)) {
                            booleans.add(false);
                        }
                        else {
                            booleans.add(true);
                        }
                    }
                }
                if(i == 3) {
                    for(Object n : noteDetailsEleve.getApprendre()){
                        Log.d("notes appro: ",""+n);
                        if(n == null || n.equals(0)) {
                            booleans.add(false);
                        }
                        else {
                            booleans.add(true);
                        }
                    }
                }
                if(i == 4) {
                    for(Object n : noteDetailsEleve.getApproprier()){
                        Log.d("notes : ",""+n);
                        if(n == null || n.equals(0)) {
                            booleans.add(false);
                        }
                        else {
                            booleans.add(true);
                        }
                    }
                }
            } else {
                booleans.add(false);
                booleans.add(false);
                booleans.add(false);
                booleans.add(false);
                booleans.add(false);
                booleans.add(false);
            }



            Criteres criteresList = listeCriteres.get(i);
            ArrayList<CheckboxModel> checkboxModels = new ArrayList<CheckboxModel>();

            Iterator boolIterator = booleans.iterator();
            Iterator stringIterator = criteresList.getAllChoix().iterator();
            Iterator noteIterator = criteresList.getAllNotes().iterator();

            /*
            * Itération sur les liste pour construire l'élèment à cocher
             */
            while(boolIterator.hasNext() && stringIterator.hasNext()) {
                Boolean checked = (Boolean)boolIterator.next();
                String choix = (String)stringIterator.next();
                float note = (float)noteIterator.next();

                checkboxModels.add(new CheckboxModel(choix,checked,note));
            }

            //ListVIEW
            ListView listview = (ListView)findViewById(views.get(i));
            CustomAdapter arrayAdapter = new CustomAdapter(this,checkboxModels,layout.get(i));
            listview.setAdapter(arrayAdapter);
            listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        }

        noteGlobale.setText(Double.toString(CustomAdapter.noteTotal));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Note note = new Note(preferences.getInt("eleve",1),sport.getId_sport());

                note.setNote(Float.parseFloat(noteGlobale.getText().toString()));
                Log.d("json", ""+CustomAdapter.notes.getTotalApprendre());
                note.setApprendre((float)CustomAdapter.notes.getTotalApprendre());
                note.setApproprier((float)CustomAdapter.notes.getTotalApproprier());
                note.setMethodes((float)CustomAdapter.notes.getTotalMethode());
                note.setMotricite((float)CustomAdapter.notes.getTotalMotricite());
                note.setPerformances(0);
                note.setRegles((float)CustomAdapter.notes.getTotalRegle());
                if(noteEleve != null) { //l'élève est déjà noté pour ce sport
                    noteManager.modifier(note);
                    Log.d("json", "modifier");
                    Log.d("json", "modifier lastUpdateId : " + noteEleve.getId());
                    NoteDetails noteDetails = new NoteDetails(CustomAdapter.notesDetails,noteEleve.getId());
                    Log.d("json", "modifier noteDetails : " + CustomAdapter.notesDetails);
                    noteDetailsManager.modifier(noteDetails);

                }
                else {
                    //ajouter une nouvelle note
                    noteManager.ajouter(note);
                    NoteDetails noteDetails = new NoteDetails(CustomAdapter.notesDetails,noteManager.getLastInsertedId());
                    noteDetailsManager.ajouter(noteDetails);
                }

                //save note details


                registerReceiver(new NetworkSyncData(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
                Toast toast;
                toast = Toast.makeText(context, "Note sauvegardée !", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        //arrayAdapter.notifyDataSetChanged();




        //Log.d("sportid", "size "+listeCriteres.size());

        /*for (Criteres c : listeCriteres) {
            numTest++;

            switch (numTest) {
                case 1 :
                    linear1= (LinearLayout) findViewById(R.id.linearMotricite);
                    break;
                case 2 :
                    linear1= (LinearLayout) findViewById(R.id.linearMéthodes);
                    break;
                case 3 :
                    linear1= (LinearLayout) findViewById(R.id.linearRegles);
                    break;
                case 4 :
                    linear1= (LinearLayout) findViewById(R.id.linearApprendre);
                    break;
                case 5 :
                    linear1= (LinearLayout) findViewById(R.id.linearApproprier);

            }

            if (!c.getChoix1().equals("null")) {
                final CheckBox check1 = new CheckBox(this);
                check1.setText(c.getChoix1());

                check1.setCameraDistance(c.getPoint1());
                check1.setTextColor(Color.GRAY);
                check1.setTextSize(20);
                linear1.addView(check1);
                check1.setOnClickListener(new View.OnClickListener() {

                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(View v) {
                        resultat=Float.parseFloat(text.getText().toString());

                        if (check1.isChecked()) {

                            resultat = resultat + check1.getCameraDistance();
                            text.setText(Float.toString(resultat));
                        }
                        else {
                            resultat = resultat-check1.getCameraDistance();
                            text.setText(Float.toString(resultat));
                        }
                    }
                });
            }

            if (!c.getChoix2().equals("null")) {
                final CheckBox check2 = new CheckBox(this);
                check2.setText(c.getChoix2());
                check2.setCameraDistance(c.getPoint2());
                check2.setTextColor(Color.GRAY);
                check2.setTextSize(20);
                linear1.addView(check2);
                check2.setOnClickListener(new View.OnClickListener() {

                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(View v) {
                        resultat=Float.parseFloat(text.getText().toString());

                        if (check2.isChecked()) {

                            resultat = resultat + check2.getCameraDistance();
                            text.setText(Float.toString(resultat));
                        }
                        else {
                            resultat = resultat-check2.getCameraDistance();
                            text.setText(Float.toString(resultat));
                        }
                    }
                });
            }

            if (!c.getChoix3().equals("null")) {
                final CheckBox check3 = new CheckBox(this);
                check3.setText(c.getChoix3());
                check3.setCameraDistance(c.getPoint3());
                check3.setTextSize(20);
                check3.setTextColor(Color.GRAY);
                linear1.addView(check3);
                check3.setOnClickListener(new View.OnClickListener() {

                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(View v) {
                        resultat=Float.parseFloat(text.getText().toString());

                        if (check3.isChecked()) {

                            resultat = resultat + check3.getCameraDistance();
                            text.setText(Float.toString(resultat));
                        }
                        else {
                            resultat = resultat-check3.getCameraDistance();
                            text.setText(Float.toString(resultat));
                        }
                    }
                });
            }

            if (!c.getChoix4().equals("null")) {
                final CheckBox check4 = new CheckBox(this);
                Log.e("cget",""+c.getChoix4());
                check4.setText(c.getChoix4());
                check4.setCameraDistance(c.getPoint4());
                check4.setTextColor(Color.GRAY);
                check4.setTextSize(20);
                linear1.addView(check4);
                check4.setOnClickListener(new View.OnClickListener() {

                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(View v) {
                        resultat=Float.parseFloat(text.getText().toString());

                        if (check4.isChecked()) {

                            resultat = resultat + check4.getCameraDistance();
                            text.setText(Float.toString(resultat));
                        }
                        else {
                            resultat = resultat-check4.getCameraDistance();
                            text.setText(Float.toString(resultat));
                        }
                    }
                });
            }

            if (!c.getChoix5().equals("null")) {
                final CheckBox check5 = new CheckBox(this);

                check5.setText(c.getChoix5());
                check5.setCameraDistance(c.getPoint5());
                check5.setTextColor(Color.GRAY);
                check5.setTextSize(20);
                linear1.addView(check5);
                check5.setOnClickListener(new View.OnClickListener() {

                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(View v) {
                        resultat=Float.parseFloat(text.getText().toString());

                        if (check5.isChecked()) {

                            resultat = resultat + check5.getCameraDistance();
                            text.setText(Float.toString(resultat));
                        }
                        else {
                            resultat = resultat-check5.getCameraDistance();
                            text.setText(Float.toString(resultat));
                        }
                    }
                });
            }

            if (!c.getChoix6().equals("null")) {
                final CheckBox check6 = new CheckBox(this);
                check6.setText(c.getChoix6());
                check6.setTextSize(20);
                check6.setCameraDistance(c.getPoint6());
                check6.setTextColor(Color.GRAY);
                linear1.addView(check6);
                check6.setOnClickListener(new View.OnClickListener() {

                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(View v) {
                        resultat=Float.parseFloat(text.getText().toString());

                        if (check6.isChecked()) {

                            resultat = resultat + check6.getCameraDistance();
                            text.setText(Float.toString(resultat));
                        }
                        else {
                            resultat = resultat-check6.getCameraDistance();
                            text.setText(Float.toString(resultat));
                        }
                    }
                });
            }

        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NoteManager noteManager = new NoteManager(context);
                Note note = new Note(preferences.getInt("eleve",1),1);
                note.setApprendre(Float.parseFloat(text.getText().toString()));
                note.setNote(1);
                note.setApproprier(0);
                note.setMethodes(0);
                note.setMotricite(0);
                note.setPerformances(0);
                note.setRegles(0);
                noteManager.ajouter(note);

                registerReceiver(new NetworkSyncData(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
                Toast toast;
                toast = Toast.makeText(context, "Note sauvegardée !", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
            */
    }


}
