package com.pts3.sport;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pts3.sport.activity.ThreeFragment;
import com.pts3.sport.dao.Eleve;
import com.pts3.sport.dao.Note;
import com.pts3.sport.dao.Sport;
import com.pts3.sport.database.ClasseManager;
import com.pts3.sport.database.EleveManager;
import com.pts3.sport.database.NoteManager;
import com.pts3.sport.database.SportManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChronoActivity2 extends AppCompatActivity {
    private static final String LIST_TIME_VALUE ="c" ;
    private static final String ITERATOR_VALUE = "a";
    public TextView txtValue;
    public  TextView txtAffichage;
    private ArrayList idEleveList;
    public Button start, lap, stop, valider, suivant,restart;


    private ArrayList<TextView> textViewList, textViews2List,besTimeList,noteTVList;
    private ArrayList<EditText> editTextList;

    private ArrayList<String> listTemps1;
    private TextView temps1, temps2, temps3, temps4, eleve1, eleve2, eleve3, eleve4,meilleurT1,meilleurT2,meilleurT3,meilleurT4,note1,note2,note3,note4;
    private EditText txtInput1, txtInput2, txtInput3, txtInput4;
    private int iterator2,compteur;
    private Chronometre chrono;
    private SharedPreferences preferences;
    private String classe;


    private List<Eleve> listEleve;
    private int iterator;
    private int iterator72;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrono2);
        eleve1 = findViewById(R.id.eleve1);
        eleve2 = findViewById(R.id.eleve2);
        eleve3 = findViewById(R.id.eleve3);
        eleve4 = findViewById(R.id.eleve4);
        start =  findViewById(R.id.button);
        txtAffichage = findViewById(R.id.txtAffi2);
        txtValue =  findViewById(R.id.txtValue2);
        suivant = findViewById(R.id.btnSuivant);
        lap =  findViewById(R.id.buttonLap);
        stop =  findViewById(R.id.btnStop);
        valider =  findViewById(R.id.btnValider);
        restart = findViewById(R.id.btnRestart);
        Intent intent = getIntent();

        chrono = new Chronometre(this, new Handler(),txtAffichage,txtValue);
        listTemps1 = new ArrayList<>();
        listTemps1 = intent.getStringArrayListExtra(LIST_TIME_VALUE);
        besTimeList = new ArrayList<>();
        textViewList = new ArrayList<>();
        editTextList = new ArrayList<>();
        noteTVList = new ArrayList<>();
        meilleurT1 = findViewById(R.id.meilleurTemps1);
        meilleurT2 = findViewById(R.id.meilleurTemps2);
        meilleurT3 = findViewById(R.id.meilleurTemps3);
        meilleurT4 = findViewById(R.id.meilleurTemps4);
        besTimeList.add(meilleurT1);
        besTimeList.add(meilleurT2);
        besTimeList.add(meilleurT3);
        besTimeList.add(meilleurT4);
        note1 = findViewById(R.id.note1);
        note2 = findViewById(R.id.note2);
        note3 = findViewById(R.id.note3);
        note4 = findViewById(R.id.note4);
        noteTVList.add(note1);
        noteTVList.add(note2);
        noteTVList.add(note3);
        noteTVList.add(note4);
        idEleveList = new ArrayList<>();
        txtInput1 = findViewById(R.id.txtInput);
        txtInput2 = findViewById(R.id.txtInput2);
        txtInput3 = findViewById(R.id.txtInput3);
        txtInput4 = findViewById(R.id.txtInput4);
        temps1 = findViewById(R.id.temps1);
        temps2 = findViewById(R.id.temps2);
        temps3 = findViewById(R.id.temps3);
        temps4 = findViewById(R.id.temps4);
        editTextList.add(txtInput1);
        editTextList.add(txtInput2);
        editTextList.add(txtInput3);
        editTextList.add(txtInput4);
        textViewList.add(temps1);
        textViewList.add(temps2);
        textViewList.add(temps3);
        textViewList.add(temps4);
        textViews2List = new ArrayList<>();
        textViews2List.add(eleve1);
        textViews2List.add(eleve2);
        textViews2List.add(eleve3);
        textViews2List.add(eleve4);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        classe = preferences.getString("classe", "");


        final EleveManager eleveManager = new EleveManager(this);
        ClasseManager classeManager = new ClasseManager(this);
        listEleve = eleveManager.recupererTout(classeManager.recuperer(classe));


        for (Eleve eleve : listEleve) {


            if (!isNotay(eleve) && iterator2 <4) {




                idEleveList.add(eleve.getId_eleve());
                textViews2List.get(iterator2 % 4).setText(eleve.getNom());

                iterator2++;
            }

        }


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chrono.start();


            }
        });


        lap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( chrono.isRunning && iterator < 4) {

                    chrono.affTemps();
                    iterator++;
                }
            }


        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chrono.stop();
            }
        });

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lastNum="" ;
                boolean isNotOk=false;
                for(EditText editText : editTextList){

                    String txt = editText.getText()+"";
                    if((editText.getText()+"").equals("")){
                        isNotOk=true;
                        afficherToast();
                    }
                    else if(  (Integer.parseInt(txt) > 4)  || (editText.getText()+"").equals(lastNum)){
                        isNotOk=true;
                        afficherToast();

                    }else if(txtAffichage.getText().equals("")){
                        isNotOk=true;
                        afficherToast();
                    }
                    lastNum = editText.getText()+"";
                }
                if(!isNotOk){
                    if(iterator72 < 2) {
                        validerTemps();
                        iterator72++;
                    }
                }

            }
        });
        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* for(Eleve eleve : listEleve) {
                    if(!eleve.isEvalue()) {
                        Intent intent = new Intent(ChronoActivity2.this, ChronoActivity.class);
                        startActivity(intent);

                    }
                } */
                /*Intent intent = new Intent(ChronoActivity2.this, ChronoActivity.class);
                startActivity(intent);*/
                finish();



            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!chrono.isRunning()) {
                    chrono.restart();
                    iterator = 0;
                }
            }
        });

    }

    private void ajouterNote(int j, double note) {
        SportManager sportManager = new SportManager(this);
        Sport sport = sportManager.recuperer(preferences.getString("sport", ""));
        NoteManager noteManager = new NoteManager(this);
        int i = (int) idEleveList.get(j);
        Log.i("Indice",i+"");
        Eleve eleveANoter = new EleveManager(this).recuperer(i);
        Note noteEleve = noteManager.recuperer(eleveANoter,sport);
        if(noteEleve != null ){
            noteEleve.setPerformances((float) note);
            noteManager.modifier(noteEleve);
        }


    }
    private boolean isNotay(Eleve eleveANoter){
        SportManager sportManager = new SportManager(this);
        Sport sport = sportManager.recuperer(preferences.getString("sport", ""));
        NoteManager noteManager = new NoteManager(this);


        Note noteEleve = noteManager.recuperer(eleveANoter,sport);
        if(noteEleve != null && noteEleve.getPerformances() > 0.0f){
            return true;
        }
        return false;
    }

    private double getPointWithDifferenceBetweenTime(String s, String s1) {
        String[] time1 = s.split("-");
        time1[1] = "00:" + time1[1];
        String[] time2 = s.split("-");
        time2[1] = "00:" + time2[1];

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss:SSS", Locale.FRENCH);
        try {
            //TEMPS PLATS
            Date temps = simpleDateFormat.parse(time1[1]);
            //TEMPS HAIES
            Date temps1 = simpleDateFormat.parse(time2[1]);
            if(temps1.getTime()-temps.getTime() > 2.6){
                return 0;

            }else if(temps1.getTime()-temps.getTime() <= 2.6 && temps1.getTime()-temps.getTime() > 2.3 ){
                return 0.5;

            }
            else if(temps1.getTime()-temps.getTime() <= 2.3 && temps1.getTime()-temps.getTime() > 2){
                return 1;

            }
            else if(temps1.getTime()-temps.getTime() <= 2 && temps1.getTime()-temps.getTime() > 1.7 ){
                return 1.5;

            }
            else if(temps1.getTime()-temps.getTime() <= 1.7 && temps1.getTime()-temps.getTime() > 1.4 ){
                return 2;

            }else if(temps1.getTime()-temps.getTime() <= 1.4 && temps1.getTime()-temps.getTime() > 1.1 ){
                return 2.5;

            }else if(temps1.getTime()-temps.getTime() <1.1 ){
                return 3;

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


        return 0;
    }

    private double getPoint(String s) {
        String[] time1 = s.split("-");
        time1[1] = "00:" + time1[1];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss:SSS", Locale.FRENCH);
        try {
            Date temps = simpleDateFormat.parse(time1[1]);

            if(temps.getTime() > 7.5){
                return 0;
            }else if(temps.getTime() <=7.5 && temps.getTime() > 7.2){
                return 0.5;
            }
            else if(temps.getTime() <=7.2 && temps.getTime() > 6.9){
                return 1;
            }
            else if(temps.getTime() <=6.9 && temps.getTime() > 6.6){
                return 1.5;
            }
            else if(temps.getTime() <=6.6 && temps.getTime() > 6.3){
                return 2;
            }else if(temps.getTime() <=6.3 && temps.getTime() > 6){
                return 2.5;
            }
            else if(temps.getTime() <=6 ){
                return 3;
            }






        } catch (ParseException e) {
            e.printStackTrace();
        }


        return 0;
    }
    public void validerTemps(){
        for (int i = 0; i <= 4; i++) {
            int startL = txtAffichage.getLayout().getLineStart(i);
            int endL = txtAffichage.getLayout().getLineEnd(i);
            String getTextOnLine = (String) txtAffichage.getText().subSequence(startL, endL);
            Log.i("temps", getTextOnLine);
            int j = 0;
            for (EditText eT : editTextList) {

                if (String.valueOf(eT.getText()).equals("" + i)) {
                    if (compteur > 0) {

                        Log.i("numéro", String.valueOf(eT.getText()));

                        String[] time1 = getTextOnLine.split("-");
                        time1[1] = "00:" + time1[1];
                        String[] time2 = ((String) besTimeList.get(j).getText()).split("-");
                        time2[1] = "00:" + time2[1];
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss:SSS", Locale.FRENCH);
                        try {
                            Date temps1 = simpleDateFormat.parse(time1[1]);
                            Date temps2 = simpleDateFormat.parse(time2[1]);
                            if (temps1.getTime() < temps2.getTime()) {
                                besTimeList.get(j).setText(getTextOnLine);
                            }


                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        double points1 = getPoint(listTemps1.get(j));
                        double points2 =  getPointWithDifferenceBetweenTime(listTemps1.get(j),(String) besTimeList.get(j).getText());
                        double points = points1+ points2;
                        noteTVList.get(j).setText(""+ points);
                        ajouterNote(j,points);


                    } else {
                        besTimeList.get(j).setText(getTextOnLine);
                    }
                    textViewList.get(j).setText(getTextOnLine);


                }

                j++;
            }




        }
        compteur=1;
    }
    public void afficherToast(){
        Toast.makeText(getApplicationContext(),"Mauvais entrée de temps",Toast.LENGTH_SHORT).show();
    }

}
