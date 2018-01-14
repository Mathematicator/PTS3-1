package com.pts3.sport.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.pts3.sport.R;
import com.pts3.sport.adaptater.ClasseListAdapter;
import com.pts3.sport.adaptater.SportAdapter;
import com.pts3.sport.dao.Classe;
import com.pts3.sport.dao.Sport;
import com.pts3.sport.database.ClasseManager;
import com.pts3.sport.database.PratiqueManager;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;

import static com.pts3.sport.activity.StepperActivity.mStepperLayout;


public class TwoFragment  extends Fragment implements Step {

    private ArrayList<Sport> listSport;
    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private boolean validateForm = false;
    private ListView listView;

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
            //On récupère le nom de la classe précédemment sélectionnée
            String classe = preferences.getString("classe", "");
            Log.d("verifyStep", classe);
            PratiqueManager pratiqueManager = new PratiqueManager(context);
            listSport = pratiqueManager.getSports(classe);
            if(listSport != null && listSport.size() > 0) {
                listView.setAdapter(new SportAdapter(listSport));

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Sport sport = (Sport) adapterView.getItemAtPosition(i);

                        editor.putString("sport", sport.getNom_sport());
                        editor.apply();
                        validateForm = true;
                        mStepperLayout.proceed();

                    }
                });
            }
            else {
                String[] statesList = {"Aucun sport pour cette classe" };
                listView.setAdapter(new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1, statesList));
            }
        }
        else {
            validateForm = false;
        }
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        Log.d("verifyStep", ""+validateForm);
        return validateForm ? null : new VerificationError("Vous devez choisir un sport");
    }


    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }
}
