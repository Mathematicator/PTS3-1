package com.pts3.sport.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pts3.sport.R;
import com.pts3.sport.adaptater.ClasseListAdapter;
import com.pts3.sport.dao.Classe;
import com.pts3.sport.database.ClasseManager;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;

import static com.pts3.sport.activity.StepperActivity.mStepperLayout;

/**
 * Created by Ragnulf on 13/01/2018.
 */

public class OneFragment extends Fragment implements Step {

    private ArrayList<Classe> listClasse;
    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private boolean validateForm = false;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listview, container, false);
        ListView listView = (ListView)v.findViewById(R.id.listViewer);
        context = this.getContext();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();

        //Récupérer la liste des classe
        ClasseManager classeManager = new ClasseManager(context);
        listClasse = classeManager.recupererTout();
        listView.setAdapter(new ClasseListAdapter(listClasse));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Classe classeModel = (Classe) adapterView.getItemAtPosition(i);

                editor.putString("classe", classeModel.getNom());
                editor.apply();
                validateForm = true;
                mStepperLayout.proceed();

            }
        });
        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        validateForm = false;

    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return validateForm ? null : new VerificationError("Vous devez choisir une classe");
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }


}
