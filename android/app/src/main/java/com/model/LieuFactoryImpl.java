package com.model;

import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.beans.AbstractEntityFactory;

/**
 * 
 */
public class LieuFactoryImpl
//@non-generated-start[class-signature-extends][X]
		extends AbstractEntityFactory<Lieu>
//@non-generated-end
		implements LieuFactory
//@non-generated-start[class-signature]
//@non-generated-end
{
	//@non-generated-start[attributes]
	//@non-generated-end

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.LieuFactory#createInstance()
	 */
	@Override
	public Lieu createInstance() {
		Lieu r_oLieu = new LieuImpl();
		this.init(r_oLieu);
		return r_oLieu;
	}

	/**
	 * Méthode d'initialisation de l'objet
	 *
	 * @param p_oLieu Entité d'interface Lieu
	 */
	protected void init(Lieu p_oLieu) {
		//@non-generated-start[init]
		//@non-generated-end
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
