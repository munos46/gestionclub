package com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.adoliveira.gestionclub.R;
import com.model.Joueur;

import java.util.ArrayList;

/**
 * Created by adrien on 03/01/2017.
 */

public class ListeJoueurAdapter extends ArrayAdapter<Joueur> {

    private ArrayList<Joueur> joueurList;

    public ListeJoueurAdapter(Context pContext, int ptextViewResourceId, ArrayList<Joueur> pJoueurList) {
        super(pContext, ptextViewResourceId, pJoueurList);
        this.joueurList = new ArrayList<Joueur>();
        this.joueurList.addAll(pJoueurList);
    }

    private class ViewHolder {
        TextView joueurName;
        CheckBox estPresent;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.detailentrainpanel_listejoueur, null);

            holder = new ViewHolder();
            holder.joueurName = (TextView) convertView.findViewById(R.id.nameJoueur);
            holder.estPresent = (CheckBox) convertView.findViewById(R.id.estPresent);
            convertView.setTag(holder);

//            holder.name.setOnClickListener( new View.OnClickListener() {
//                public void onClick(View v) {
//                    CheckBox cb = (CheckBox) v ;
//                    Country country = (Country) cb.getTag();
//                    Toast.makeText(getApplicationContext(),
//                            "Clicked on Checkbox: " + cb.getText() +
//                                    " is " + cb.isChecked(),
//                            Toast.LENGTH_LONG).show();
//                    country.setSelected(cb.isChecked());
//                }
//            });
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Joueur joueur = joueurList.get(position);
        holder.joueurName.setText(joueur.getNom().concat(" ").concat(joueur.getPrenom()));
        holder.estPresent.setText("test");
        holder.estPresent.setChecked(true);
        holder.estPresent.setTag(joueur);

        return convertView;
    }
}
