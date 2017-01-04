package com.model;

import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.beans.ICascade;
import com.adeuza.movalysfwk.mf4jcommons.core.beans.MEntity;

/**
 * Enumération de la cascade associée aux objets de type Joueur.
 */
public enum JoueurCascade implements ICascade {

	/**
	 * <p>Cascade ENTRAINEMENTS</p>
	 * <p>La cascade ENTRAINEMENT est un pré-requis à cette cascade</p>
	 * <p>Relation ManyToMany</p><p> targetEntity=Entrainement relationOwner=true transient=false</p>
	 */
	ENTRAINEMENTS(Entrainement.class),

	/**
	 * <p>Cascade ENTRAINEMENT</p>
	 * <p>Cascade représentant l'association entre les deux entités uniquement.</p>
	 * <p>Ajouter la cascade ENTRAINEMENTS pour une cascade complète.</p>
	 * <p>Relation ManyToMany</p><p> targetEntity=Entrainement relationOwner=true transient=false</p>
	 */
	ENTRAINEMENT(Entrainement.class);
	/** Type of the targeted entity */
	private Class<? extends MEntity> oResultType;

	/**
	 * Constructor for JoueurCascade
	 * @param p_oResultEntityClass the class of the linked entity
	 */
	private JoueurCascade(Class<? extends MEntity> p_oResultEntityClass) {
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
