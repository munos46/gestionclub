package com.model;

import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.beans.ICascade;
import com.adeuza.movalysfwk.mf4jcommons.core.beans.MEntity;

/**
 * Enumération de la cascade associée aux objets de type Entrainement.
 */
public enum EntrainementCascade implements ICascade {

	/**
	 * <p>Cascade LIEU</p>
	 * <p>Relation ManyToOne</p><p> targetEntity=Lieu mandatory=true relationOwner=true transient=false</p>
	 */
	LIEU(Lieu.class),

	/**
	 * <p>Cascade JOUEURS</p>
	 * <p>La cascade JOUEUR est un pré-requis à cette cascade</p>
	 * <p>Relation ManyToMany</p><p> targetEntity=Joueur relationOwner=false transient=false</p>
	 */
	JOUEURS(Joueur.class),

	/**
	 * <p>Cascade JOUEUR</p>
	 * <p>Cascade représentant l'association entre les deux entités uniquement.</p>
	 * <p>Ajouter la cascade JOUEURS pour une cascade complète.</p>
	 * <p>Relation ManyToMany</p><p> targetEntity=Joueur relationOwner=false transient=false</p>
	 */
	JOUEUR(Joueur.class);
	/** Type of the targeted entity */
	private Class<? extends MEntity> oResultType;

	/**
	 * Constructor for EntrainementCascade
	 * @param p_oResultEntityClass the class of the linked entity
	 */
	private EntrainementCascade(Class<? extends MEntity> p_oResultEntityClass) {
		oResultType = p_oResultEntityClass;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<? extends MEntity> getResultType() {
		return oResultType;
	}

}
