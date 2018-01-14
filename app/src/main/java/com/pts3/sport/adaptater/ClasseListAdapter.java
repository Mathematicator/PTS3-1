package com.pts3.sport.adaptater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pts3.sport.R;
import com.pts3.sport.dao.Classe;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Ragnulf on 13/01/2018.
 */

public class ClasseListAdapter extends BaseAdapter {
    private ArrayList<Classe> listClasse;

    public ClasseListAdapter(ArrayList<Classe> listClasse) {
        this.listClasse = listClasse;
    }

    @Override
    public int getCount() {
        return listClasse.size();
    }

    @Override
    public Object getItem(int i) {
        return listClasse.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.list_row_layout, parent, false);
        }
        TextView classeNom = (TextView) convertView.findViewById(R.id.title);
        TextView classeNiveau = (TextView) convertView.findViewById(R.id.subtitle);

        Classe classeModel = listClasse.get(position);
        classeNom.setText(classeModel.getNom());
        classeNiveau.setText("Niveau : " + classeModel.getNiveau());

        return convertView;
    }
}
