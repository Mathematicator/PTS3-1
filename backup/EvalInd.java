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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pts3.sport.dao.Criteres;
import com.pts3.sport.dao.Note;
import com.pts3.sport.dao.Sport;
import com.pts3.sport.database.CriteresManager;
import com.pts3.sport.database.NoteManager;
import com.pts3.sport.database.SportManager;
import com.pts3.sport.network.NetworkSyncData;

import java.util.ArrayList;
import java.util.List;

public class EvalInd extends AppCompatActivity {

    List<Sport> listeSport;
    List<Criteres> listeCriteres;
    Context context = this;
    SharedPreferences.Editor editor;
    TextView text;
    LinearLayout linearLayout,linear1,linear2,linear3,linear4,linear5;
    //int point;
    float resultat,point;
    int numTest=0,numTest2=0;
    Button btnSave;
    SharedPreferences preferences;
    int motricite=0,methodes=0,regles=0,apprendre=0,approprier=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eval_ind);
        linearLayout = (LinearLayout) findViewById(R.id.layoutEvalInd);
        btnSave = (Button) findViewById(R.id.btnSaveNote);
        preferences= PreferenceManager.getDefaultSharedPreferences(this);







        text = (TextView) findViewById(R.id.textTest);



        text.setText("0");

        listeCriteres = new ArrayList<Criteres>();
        listeSport = new ArrayList<Sport>();

        SportManager sportManager = new SportManager(this);
        //SELECT à changer, là c'est juste pour montrer à nenet
        //CriteresManager criteresManager = new CriteresManager(this, "SELECT * FROM CRITERES WHERE id_sport=1");
        CriteresManager criteresManager = new CriteresManager(this);

        //listeSport = sportManager.recupererTout();
        //listeCriteres = criteresManager.recupererCriteres();




        for (Criteres c : listeCriteres) {
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
                    }
                });
            }

            if (!c.getChoix2().equals("null")) {
                final CheckBox check2 = new CheckBox(this);
                check2.setText(c.getChoix2());
                check2.setCameraDistance(c.getPoint2());
                check2.setTextColor(Color.GRAY);
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
                //registerReceiver(new NetworkSyncData(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
                Toast toast;
                toast = Toast.makeText(context, "Note sauvegardée !", Toast.LENGTH_SHORT);
                toast.show();
            }
        });













    }
}
