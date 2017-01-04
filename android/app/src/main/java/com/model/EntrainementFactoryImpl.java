package com.model;

import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.beans.AbstractEntityFactory;

/**
 * 
 */
public class EntrainementFactoryImpl
//@non-generated-start[class-signature-extends][X]
		extends AbstractEntityFactory<Entrainement>
//@non-generated-end
		implements EntrainementFactory
//@non-generated-start[class-signature]
//@non-generated-end
{
	//@non-generated-start[attributes]
	//@non-generated-end

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.EntrainementFactory#createInstance()
	 */
	@Override
	public Entrainement createInstance() {
		Entrainement r_oEntrainement = new EntrainementImpl();
		this.init(r_oEntrainement);
		return r_oEntrainement;
	}

	/**
	 * Méthode d'initialisation de l'objet
	 *
	 * @param p_oEntrainement Entité d'interface Entrainement
	 */
	protected void init(Entrainement p_oEntrainement) {
		//@non-generated-start[init]
		//@non-generated-end
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
