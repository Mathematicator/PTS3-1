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
import com.pts3.sport.dao.Sport;
import com.pts3.sport.database.PratiqueManager;

import java.util.ArrayList;

/**
    Sport activity
    Récupération de la liste des sport des classes, puis affichage etsélection
    Auteur : Gabin, Taoufik
*/

public class SportActivity extends AppCompatActivity {

    private RadioGroup rg;
    private String classe;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private ArrayList<Sport> listeSport;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        context = this;

        rg = (RadioGroup) findViewById(R.id.rgSport);

        //On récupère le nom de la classe précédemment sélectionnée
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        classe = preferences.getString("classe", "");

        PratiqueManager pratiqueManager = new PratiqueManager(this);
        listeSport = pratiqueManager.getSports(classe);

        for (Sport s : listeSport) {

            RadioButton rd = new RadioButton(this);
            rd.setText(s.getNom_sport());
            rd.setTextSize(40);
            rd.setTextColor(Color.GRAY);
            preferences = PreferenceManager.getDefaultSharedPreferences(this);
            editor = preferences.edit();
            editor.putString("sport", "");

            rd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString("sport", ((Button) v).getText().toString());
                    editor.apply();
                    Intent displayActivity = new Intent(context, SelectionEleve.class);
                    context.startActivity(displayActivity);

                }
            });

            rg.addView(rd);
        }

    }
}
