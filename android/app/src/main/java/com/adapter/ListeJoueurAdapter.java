package com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.adoliveira.gestionclub.R;
import com.model.Joueur;

import java.util.ArrayList;

/**
 * Created by adrien on 03/01/2017.
 */

public class ListeJoueurAdapter extends ArrayAdapter<Joueur> {

    private ArrayList<Joueur> joueurListPresent;
    private ArrayList<Joueur> joueurList;

    public ListeJoueurAdapter(Context pContext, int ptextViewResourceId, ArrayList<Joueur> pJoueurList, ArrayList<Joueur> pJoueurListPresent) {
        super(pContext, ptextViewResourceId, pJoueurList);
        this.joueurListPresent = new ArrayList<Joueur>();
        this.joueurListPresent.addAll(pJoueurListPresent);
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
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Joueur joueur = joueurList.get(position);
        holder.joueurName.setText(joueur.getNom().concat(" ").concat(joueur.getPrenom()));

        // On regarge si le joueur est présent dans la liste
        boolean estPres = false;
        for (Joueur unJoueur : joueurListPresent)
        {
            if (unJoueur.getNom().equals(joueur.getNom()) && unJoueur.getPrenom().equals(joueur.getPrenom()))
            {
                estPres = true;
            }
        }

        holder.estPresent.setChecked(estPres);
        return convertView;
    }
}
