package com.model;

import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.beans.AbstractEntityFactory;

/**
 * 
 */
public class EntrainJoueurFactoryImpl
//@non-generated-start[class-signature-extends][X]
		extends AbstractEntityFactory<EntrainJoueur>
//@non-generated-end
		implements EntrainJoueurFactory
//@non-generated-start[class-signature]
//@non-generated-end
{
	//@non-generated-start[attributes]
	//@non-generated-end

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.EntrainJoueurFactory#createInstance()
	 */
	@Override
	public EntrainJoueur createInstance() {
		EntrainJoueur r_oEntrainJoueur = new EntrainJoueurImpl();
		this.init(r_oEntrainJoueur);
		return r_oEntrainJoueur;
	}

	/**
	 * Méthode d'initialisation de l'objet
	 *
	 * @param p_oEntrainJoueur Entité d'interface EntrainJoueur
	 */
	protected void init(EntrainJoueur p_oEntrainJoueur) {
		//@non-generated-start[init]
		//@non-generated-end
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
