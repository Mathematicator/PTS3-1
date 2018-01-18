package com.pts3.sport;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pts3.sport.activity.StepperActivity;
import com.pts3.sport.activity.ThreeFragment;
import com.pts3.sport.dao.Eleve;
import com.pts3.sport.database.ClasseManager;
import com.pts3.sport.database.EleveManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class ChronoActivity extends AppCompatActivity {

    private static final String ITERATOR_VALUE = "a";

    private static final String LIST_TIME_VALUE ="c" ;
    //Attributs static à changer
    public static TextView txtValue;
    public static TextView txtAffichage;
    public Button start, lap, stop, valider,suivant,btnRestart;
    private ArrayList<String> listTemps1;
    private ArrayList<TextView> textViewList,textViews2List;
    private ArrayList<EditText> editTextList;

    private SharedPreferences.Editor editor;
    TextView temps1,temps2,temps3,temps4,eleve1,eleve2,eleve3,eleve4,meilleurT1,meilleurT2,meilleurT3,meilleurT4;
    EditText txtInput1,txtInput2,txtInput3,txtInput4;
    private int iterator,iterator2,compteur=0;
    private Chronometre chrono;
    private SharedPreferences preferences;
    private String classe;


    private List<Eleve> listEleve;
    private ArrayList<TextView> besTimeList;
    private MenuItem testMenu;
    private MenuItem testMenu2;
    private Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrono);
        eleve1 = findViewById(R.id.eleve1);
        eleve2 = findViewById(R.id.eleve2);
        eleve3 = findViewById(R.id.eleve3);
        eleve4 = findViewById(R.id.eleve4);
        start = findViewById(R.id.button);
        txtAffichage =  findViewById(R.id.txtAffi);
        txtValue =  findViewById(R.id.txtValue);
        suivant=findViewById(R.id.btnSuivant);
        lap =  findViewById(R.id.buttonLap);
        stop = findViewById(R.id.btnStop);
        valider = findViewById(R.id.btnValider);
        Intent intent=getIntent();
        iterator = intent.getIntExtra(ITERATOR_VALUE,0);
        chrono = new Chronometre(this, new Handler());
         btnRestart = findViewById(R.id.btnRestart);
        besTimeList = new ArrayList<>();
        textViewList = new ArrayList<>();
        editTextList = new ArrayList<>();
        listTemps1 = new ArrayList<>();
        meilleurT1 = findViewById(R.id.meilleurTemps1);
        meilleurT2 = findViewById(R.id.meilleurTemps2);
        meilleurT3 = findViewById(R.id.meilleurTemps3);
        meilleurT4 = findViewById(R.id.meilleurTemps4);
        txtInput1 =  findViewById(R.id.txtInput);
        txtInput2 =  findViewById(R.id.txtInput2);
        txtInput3 =  findViewById(R.id.txtInput3);
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
        besTimeList.add(meilleurT1);
        besTimeList.add(meilleurT2);
        besTimeList.add(meilleurT3);
        besTimeList.add(meilleurT4);
        Log.i("iterateur",""+iterator);



        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        classe = preferences.getString("classe", "");



        EleveManager eleveManager = new EleveManager(this);
        ClasseManager classeManager = new ClasseManager(this);
        listEleve = eleveManager.recupererTout(classeManager.recuperer(classe));


        for(Eleve eleve : listEleve){

            if(!eleve.isEvalue() && iterator2<4){
                textViews2List.get(iterator2).setText(eleve.getNom());
                eleve.setBoolean(true);
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
                chrono.affTemps();
                //associer un numero

            }
        });
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chrono.restart();
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
                for(int i=0;i<=4;i++){
                    int startL = txtAffichage.getLayout().getLineStart(i);
                    int endL= txtAffichage.getLayout().getLineEnd(i);
                    String getTextOnLine = (String) txtAffichage.getText().subSequence(startL,endL);

                    int j=0;
                    for(EditText eT : editTextList){

                        if(String.valueOf(eT.getText()).equals(""+i)){

                            if(compteur > 0){
                                String[] time1 = getTextOnLine.split("-");
                                time1[1]="00:"+time1[1];
                                String[]  time2 = ((String) besTimeList.get(j).getText()).split("-");
                                time2[1]="00:"+time2[1];
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss:SSS", Locale.FRENCH);
                                try {
                                    Date temps1 = simpleDateFormat.parse(time1[1]);
                                    Date temps2 = simpleDateFormat.parse(time2[1]);
                                    if( temps1.getTime() < temps2.getTime()){
                                        besTimeList.get(j).setText(getTextOnLine);
                                    }
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }else{
                                besTimeList.get(j).setText(getTextOnLine);
                            }

                            textViewList.get(j).setText(getTextOnLine);


                        }
                        j++;
                    }

                }
                compteur=1;

            }
        });
        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(TextView tv : besTimeList){
                    listTemps1.add((String) tv.getText());
                }
                for(TextView textView : textViews2List){
                    if (textView.getText().equals("")){
                        Intent intent = new Intent(ChronoActivity.this,ThreeFragment.class);
                        startActivity(intent);
                    }
                }
                Intent intent = new Intent(ChronoActivity.this,ChronoActivity2.class);
                intent.putStringArrayListExtra(LIST_TIME_VALUE,listTemps1);

                startActivity(intent);



            }
        });
        if (haveInternetConnection() == true)
        {
            Log.e("checkInternet","connecté !");
        }
        else
        {
            Log.e("checkInternet","Pas de connexion");
        }
        new Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onPrepareOptionsMenu(menu);
                    }
                });


            }
        },1000,1000);

    }


    public boolean haveInternetConnection(){
        // Fonction haveInternetConnection : return true si connecté, return false dans le cas contraire
        NetworkInfo network = ((ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (network==null || !network.isConnected())
        {
            // Le périphérique n'est pas connecté à Internet
            return false;
        }
        // Le périphérique est connecté à Internet
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_voyant, menu);
        testMenu = menu.findItem(R.id.internet);
        testMenu2 = menu.findItem(R.id.no_internet);

        return true;


    }
    @Override
    public boolean onPrepareOptionsMenu (Menu menu) {
        super.onPrepareOptionsMenu(menu);

        if (haveInternetConnection()) {
            testMenu.setVisible(true);
            testMenu2.setVisible(false);
        } else  {

            testMenu.setVisible(false);
            testMenu2.setVisible(true);
        }

        return true;
    }

}
