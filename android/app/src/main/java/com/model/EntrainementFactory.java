package com.model;

import com.adeuza.movalysfwk.mf4jcommons.core.beans.Scope;
import com.adeuza.movalysfwk.mf4jcommons.core.beans.ScopePolicy;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.beans.EntityFactory;

/**
 * 
 */
@Scope(ScopePolicy.SINGLETON)
public interface EntrainementFactory extends EntityFactory<Entrainement>
//@non-generated-start[class-signature]
//@non-generated-end
{

	//@non-generated-start[constants]
	//@non-generated-end

	/**
	 * Méthode de création de l'objet d'interface Entrainement avec l'enregistrement des changements.
	 *
	 * @return Entrainement
	 */
	@Override
	public Entrainement createInstance();

	//@non-generated-start[methods]
	//@non-generated-end
}
