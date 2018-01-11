package com.pts3.sport;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.pts3.sport.dao.Eleve;
import com.pts3.sport.database.EleveManager;

import java.util.ArrayList;
import java.util.List;

public class SelectionEleve extends AppCompatActivity {

    String ip = "http://projetut.pe.hu/tut.php?sql=";

    SharedPreferences preferences;
    String classe;
    SharedPreferences.Editor editor;
    Context context = this;
    List<Eleve> listeString;
    RadioGroup listEleve;
    EleveManager eleveManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_eleve);



        listEleve = (RadioGroup) findViewById(R.id.RGEleve);
        listeString = new ArrayList<Eleve>();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        classe = preferences.getString("classe", "");
        eleveManager = new EleveManager(this);

        listeString = eleveManager.recupererTout();

        for (final Eleve s : listeString) {

            //Ici pour chaque nom de classe on va créer un RadioButton portant le nom de cette classe
            //Pour conserver le nom de la classe au travers de l'appli, on l'enregistre dans
            //un fichier préférences
            final RadioButton rd = new RadioButton(context);
            rd.setText(s.getNom()+" "+s.getPrenom());
            rd.setTextSize(40);
            rd.setTextColor(Color.GRAY);
            rd.setId(s.getId_eleve());

            preferences = PreferenceManager.getDefaultSharedPreferences(context);
            editor = preferences.edit();
            rd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Si le RB est pressé, on enregistre sa valeur et on passe à la
                    //view suivante
                    editor.putInt("eleve", rd.getId());
                    editor.apply();
                    Intent displayActivity = new Intent(context, EvalInd.class);
                    context.startActivity(displayActivity);

                }
            });
            //On ajoute le RB au RadioGroup
            listEleve.addView(rd);


        }





        //JSONTask recupPrenomEleve = new JSONTask("prenom_eleve");

        //recupPrenomEleve.execute(ip + "SELECT%20*%20FROM%20ELEVE,CLASSE%20WHERE%20ELEVE.id_classe=CLASSE.id_classe%20AND%20nom_classe='" + classe + "'");
    }


}
