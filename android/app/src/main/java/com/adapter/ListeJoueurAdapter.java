package com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.adeuza.movalysfwk.mobile.mf4mjcommons.context.MContext;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.context.MContextFactory;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.services.BeanLoader;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.DaoException;
import com.adoliveira.gestionclub.R;
import com.dao.EntrainJoueurDao;
import com.model.Joueur;
import com.panel.DetailEntrainPanel;

import java.util.ArrayList;

/**
 * Created by adrien on 03/01/2017.
 */

public class ListeJoueurAdapter extends ArrayAdapter<Joueur> implements View.OnClickListener{
    private ArrayList<Joueur> joueurList;
    private long idEntrain;

    public ListeJoueurAdapter(Context pContext, int ptextViewResourceId, ArrayList<Joueur> pJoueurList, long pIdEntrain) {
        super(pContext, ptextViewResourceId, pJoueurList);
        this.joueurList = new ArrayList<Joueur>();
        this.joueurList.addAll(pJoueurList);
        idEntrain = pIdEntrain;
    }

    private class TagHolder {
        ViewHolder holder;
        int position;
    }

    private class ViewHolder {
        TextView joueurName;
        CheckBox estPresent;
    }

    @Override
    public void onClick(View v) {
        int pos = ((TagHolder) v.getTag()).position;

        CheckBox estPresent =	(CheckBox) v.findViewById(R.id.estPresent);
        Joueur joueur = (Joueur) joueurList.get(pos);
        MContextFactory oMContextFactory = (MContextFactory) BeanLoader.getInstance().getBean(MContextFactory.class);
        MContext context = oMContextFactory.createContext();

        EntrainJoueurDao entrainJoueurDao = BeanLoader.getInstance().getBean(EntrainJoueurDao.class);

        if (estPresent.isChecked())
        {
            joueurList.get(pos).setEstPres(false);
            estPresent.setChecked(false);
            try {
                entrainJoueurDao.deleteEntrainJoueur(joueur.getId(),this.idEntrain, context);
            } catch (DaoException e) {
                e.printStackTrace();
            }

            context.endTransaction();
            context.close();

            DetailEntrainPanel.enleveJoueur();
        }
        else
        {
            joueurList.get(pos).setEstPres(true);
            estPresent.setChecked(true);
            try {
                entrainJoueurDao.saveEntrainJoueur(joueur.getId(),this.idEntrain, context);
            } catch (DaoException e) {
                e.printStackTrace();
            }

            context.endTransaction();
            context.close();

            DetailEntrainPanel.ajouteJoueur();
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        TagHolder tag = new TagHolder();
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.detailentrainpanel_listejoueur, null);

            holder = new ViewHolder();
            holder.joueurName = (TextView) convertView.findViewById(R.id.nameJoueur);
            holder.estPresent = (CheckBox) convertView.findViewById(R.id.estPresent);
            tag.holder = holder;
        }
        else {
            tag = (TagHolder) convertView.getTag();
            holder = tag.holder;
        }

        Joueur joueur = joueurList.get(position);
        holder.joueurName.setText(joueur.getNom().concat(" ").concat(joueur.getPrenom()));

        holder.estPresent.setChecked(joueur.isEstPres() == null ? false : joueur.isEstPres());

        tag.position = position;
        convertView.setTag(tag);
        convertView.setOnClickListener(this);
        return convertView;
    }
}
