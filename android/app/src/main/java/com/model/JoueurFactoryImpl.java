package com.model;

import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.beans.AbstractEntityFactory;

/**
 * 
 */
public class JoueurFactoryImpl
//@non-generated-start[class-signature-extends][X]
		extends AbstractEntityFactory<Joueur>
//@non-generated-end
		implements JoueurFactory
//@non-generated-start[class-signature]
//@non-generated-end
{
	//@non-generated-start[attributes]
	//@non-generated-end

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.JoueurFactory#createInstance()
	 */
	@Override
	public Joueur createInstance() {
		Joueur r_oJoueur = new JoueurImpl();
		this.init(r_oJoueur);
		return r_oJoueur;
	}

	/**
	 * Méthode d'initialisation de l'objet
	 *
	 * @param p_oJoueur Entité d'interface Joueur
	 */
	protected void init(Joueur p_oJoueur) {
		//@non-generated-start[init]
		//@non-generated-end
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
