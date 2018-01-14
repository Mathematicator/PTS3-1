package com.pts3.sport.adaptater;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.pts3.sport.activity.OneFragment;
import com.pts3.sport.activity.ThreeFragment;
import com.pts3.sport.activity.TwoFragment;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

/**
 * Created by Ragnulf on 13/01/2018.
 */
public class StepperAdapter extends AbstractFragmentStepAdapter {

    private static final String CURRENT_STEP_POSITION_KEY = "messageResourceId";
    public StepperAdapter(FragmentManager fm, Context context) {
        super(fm, context);
    }
    @Override
    public Step createStep(int position) {
        switch (position){
            case 0:
                final OneFragment step1 = new OneFragment();
                Bundle b1 = new Bundle();
                b1.putInt(CURRENT_STEP_POSITION_KEY, position);
                step1.setArguments(b1);
                return step1;
            case 1:
                final TwoFragment step2 = new TwoFragment();
                Bundle b2 = new Bundle();
                b2.putInt(CURRENT_STEP_POSITION_KEY, position);
                step2.setArguments(b2);
                return step2;
            case 2:
                final ThreeFragment step3 = new ThreeFragment();
                Bundle b3 = new Bundle();
                b3.putInt(CURRENT_STEP_POSITION_KEY, position);
                step3.setArguments(b3);
                return step3;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        switch (position){
            case 0:
                return new StepViewModel.Builder(context)
                        .setTitle("Classe") //can be a CharSequence instead
                        .setSubtitle("Choisir une classe")
                        .create();
            case 1:
                return new StepViewModel.Builder(context)
                        .setTitle("Sport") //can be a CharSequence instead
                        .setSubtitle("Choisir un sport")
                        .create();
            case 2:
                return new StepViewModel.Builder(context)
                        .setTitle("Étudiant") //can be a CharSequence instead
                        .setSubtitle("Evaluation des étudiants")
                        .create();
        }
        return null;
    }
}
