package com.pts3.sport.adaptater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pts3.sport.R;
import com.pts3.sport.dao.Sport;
import com.pts3.sport.model.Etudiant;

import java.util.ArrayList;

/**
 * Created by Ragnulf on 14/01/2018.
 */

public class EtudiantAdapter extends BaseAdapter {
    private ArrayList<Etudiant> listEtudiant;
    public EtudiantAdapter(ArrayList<Etudiant> listEtudiant) {
        this.listEtudiant = listEtudiant;
    }
    @Override
    public int getCount() {
        return listEtudiant.size();
    }

    @Override
    public Object getItem(int position) {
        return listEtudiant.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.cardview_eleve_item, parent, false);
        }
        TextView nomEtudiant = (TextView) convertView.findViewById(R.id.nomEtudiant);
        TextView noteMotricite = (TextView) convertView.findViewById(R.id.textView13);
        TextView noteMethodes = (TextView) convertView.findViewById(R.id.textView14);
        TextView noteRegles =(TextView) convertView.findViewById(R.id.textView15);
        TextView noteApprendre = (TextView) convertView.findViewById(R.id.textView16);
        TextView noteApproprier = (TextView) convertView.findViewById(R.id.textView17);
        TextView notePerformance = (TextView) convertView.findViewById(R.id.textView18);
        ImageView image =(ImageView) convertView.findViewById(R.id.imagePlayer);

        Etudiant etudiant = listEtudiant.get(position);
        nomEtudiant.setText(etudiant.getNom());
        noteMotricite.setText(etudiant.getNoteMotricite());
        noteMethodes.setText(etudiant.getNoteMethode());
        noteRegles.setText(etudiant.getNoteRegles());
        noteApprendre.setText(etudiant.getNoteApprendre());
        noteApproprier.setText(etudiant.getNoteApproprier());
        notePerformance.setText(etudiant.getNotePerformances());
        if(etudiant.isSex()) {
            image.setImageResource(R.drawable.player);
        }
        else {
            image.setImageResource(R.drawable.player2);
        }

        return convertView;
    }
}
