package com.pts3.sport;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.pts3.sport.dao.Classe;
import com.pts3.sport.database.ClasseManager;

import java.util.ArrayList;

/*
Choix activity
Récupération de la liste des classes, puis affichage et sélection
Auteur : Gabin, Taoufik
*/

public class ChoixActivity extends AppCompatActivity {
    private RadioGroup listView;
    private ArrayList<Classe> listClasse;
    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix);
        context = this;
        listView = (RadioGroup) findViewById(R.id.rgClasse);
        ClasseManager classeManager = new ClasseManager(this);
        listClasse = classeManager.recupererTout();

        for (Classe s : listClasse) {

            //Ici pour chaque nom de classe on va créer un RadioButton portant le nom de cette classe
            //Pour conserver le nom de la classe au travers de l'appli, on l'enregistre dans
            //un fichier préférences
            RadioButton rd = new RadioButton(context);
            rd.setText(s.getNom());
            rd.setTextSize(40);
            rd.setTextColor(Color.GRAY);
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
            editor = preferences.edit();
            rd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Si le RB est pressé, on enregistre sa valeur et on passe à la
                    //view suivante
                    editor.putString("classe", ((Button) v).getText().toString());
                    editor.apply();

                    //ouvrir sportActivity
                    Intent displayActivity = new Intent(context, SportActivity.class);
                    context.startActivity(displayActivity);

                }
            });
            //On ajoute le RB au RadioGroup
            listView.addView(rd);

        }
    }
}
