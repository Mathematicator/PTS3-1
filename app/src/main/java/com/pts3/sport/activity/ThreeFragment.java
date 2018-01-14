package com.pts3.sport.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.pts3.sport.EvalActivity;
import com.pts3.sport.R;
import com.pts3.sport.adaptater.EtudiantAdapter;
import com.pts3.sport.dao.Eleve;
import com.pts3.sport.dao.Note;
import com.pts3.sport.dao.Sport;
import com.pts3.sport.database.ClasseManager;
import com.pts3.sport.database.EleveManager;
import com.pts3.sport.database.NoteManager;
import com.pts3.sport.database.SportManager;
import com.pts3.sport.model.Etudiant;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;

import static com.pts3.sport.activity.StepperActivity.mStepperLayout;

/**
 * Created by Ragnulf on 13/01/2018.
 */

public class ThreeFragment extends Fragment implements Step {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;
    private ListView listView;
    private boolean validateForm = false;
    private ArrayList<Etudiant> etudiants;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listview, container, false);
        listView = (ListView)v.findViewById(R.id.listViewer);
        context = this.getContext();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
        return v;
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            insertData();
            listView.setAdapter(new EtudiantAdapter(etudiants));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Etudiant etudiant = (Etudiant) adapterView.getItemAtPosition(i);

                    editor.putInt("eleve", etudiant.getId());
                    editor.apply();
                    Intent displayActivity = new Intent(context, EvalActivity.class);
                    context.startActivity(displayActivity);

                }
            });
        }
    }

    //insert data dans Etudiant
    public void insertData() {
        etudiants = new ArrayList<Etudiant>(); //clean
        String classe = preferences.getString("classe", "");
        /*
        * Récupérer les étudiants de la classe..
         */
        EleveManager eleveManager = new EleveManager(context);
        ClasseManager classeManager = new ClasseManager(context);
        ArrayList<Eleve> listEleve = eleveManager.recupererTout(classeManager.recuperer(classe));

        // Un utilisateur a été noté pour ce sport?
        NoteManager noteManager = new NoteManager(context);
        SportManager sportManager = new SportManager(context);
        Sport sport = sportManager.recuperer(preferences.getString("sport", ""));


        for (Eleve s : listEleve) {
            Note noteEleve = noteManager.recuperer(s,sport);
            //eleve déjà noté
            if(noteEleve != null) {
                Etudiant etudiant = new Etudiant(s.getId_eleve(), s.getNom()+" "+s.getPrenom(),Float.toString(noteEleve.getMotricite())
                        ,Float.toString(noteEleve.getMethodes()), Float.toString(noteEleve.getRegles()), Float.toString(noteEleve.getPerformances())
                        ,Float.toString(noteEleve.getApprendre()), Float.toString(noteEleve.getApproprier()),(s.getSexe() == 0), true
                        );

                etudiants.add(etudiant);
            }
            else {
                Etudiant etudiant = new Etudiant(s.getId_eleve(), s.getNom()+" "+s.getPrenom(),"--"
                        ,"--", "--", "--"
                        ,"--", "--",false, false
                );

                etudiants.add(etudiant);
            }

        }
    }
    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }
    @Override
    public void onResume()
    {
        super.onResume();
        //refresh
        setMenuVisibility(true);
    }
    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }
}
