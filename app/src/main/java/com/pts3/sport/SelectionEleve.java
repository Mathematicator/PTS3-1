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
import com.pts3.sport.dao.Note;
import com.pts3.sport.dao.Sport;
import com.pts3.sport.database.ClasseManager;
import com.pts3.sport.database.EleveManager;
import com.pts3.sport.database.NoteManager;
import com.pts3.sport.database.SportManager;

import java.util.List;

public class SelectionEleve extends AppCompatActivity {

    private SharedPreferences preferences;
    private String classe;
    private SharedPreferences.Editor editor;
    private Context context = this;
    private List<Eleve> listEleve;
    private RadioGroup listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_eleve);


        listView = (RadioGroup) findViewById(R.id.RGEleve);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        classe = preferences.getString("classe", "");
        /*
        * Récupérer les étudiants de la classe..
         */
        EleveManager eleveManager = new EleveManager(this);
        ClasseManager classeManager = new ClasseManager(this);
        listEleve = eleveManager.recupererTout(classeManager.recuperer(classe));

        // Un utilisateur a été noté pour ce sport?
        NoteManager noteManager = new NoteManager(this);
        SportManager sportManager = new SportManager(this);
        Sport sport = sportManager.recuperer(preferences.getString("sport",""));


        for (Eleve s : listEleve) {

            //Ici pour chaque nom de classe on va créer un RadioButton portant le nom de cette classe
            //Pour conserver le nom de la classe au travers de l'appli, on l'enregistre dans
            //un fichier préférences

            Note noteEleve = noteManager.recuperer(s,sport);

            RadioButton rd = new RadioButton(context);
            //eleve déjà noté
            if(noteEleve != null) {
                rd.setText(s.getNom()+" "+s.getPrenom()+" - note motricite : "+noteEleve.getMotricite());
            }
            else {
                rd.setText(s.getNom()+" "+s.getPrenom());
            }
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
                    editor.putInt("eleve", ((RadioButton) v).getId());
                    editor.apply();
                    Intent displayActivity = new Intent(context, EvalActivity.class);
                    context.startActivity(displayActivity);


                }
            });
            //On ajoute le RB au RadioGroup
            listView.addView(rd);


        }
    }

}
