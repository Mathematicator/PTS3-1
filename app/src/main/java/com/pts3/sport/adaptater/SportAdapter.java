package com.pts3.sport.adaptater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pts3.sport.R;
import com.pts3.sport.dao.Sport;

import java.util.ArrayList;

/**
 * Created by Ragnulf on 14/01/2018.
 */

public class SportAdapter extends BaseAdapter {
    private ArrayList<Sport> listSport;
    public SportAdapter(ArrayList<Sport> listSport) {
        this.listSport = listSport;
    }
    @Override
    public int getCount() {
        return listSport.size();
    }

    @Override
    public Object getItem(int position) {
        return listSport.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.cardview_sport_item, parent, false);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        TextView nomSport = (TextView) convertView.findViewById(R.id.nomSport);
        TextView trimestre = (TextView) convertView.findViewById(R.id.trimestre);

        Sport sport = listSport.get(position);
        nomSport.setText(sport.getNom_sport());
        trimestre.setText("Trimestre "+sport.getTrimestre());
        if(sport.getNom_sport().equals("Triathlon")) {
            imageView.setImageResource(R.drawable.triathlon);
        }
        else if(sport.getNom_sport().equals("Parcours gymnique")) {
            imageView.setImageResource(R.drawable.gym);
        }
        else {
            imageView.setImageResource(R.drawable.haie);
        }

        return convertView;
    }
}
