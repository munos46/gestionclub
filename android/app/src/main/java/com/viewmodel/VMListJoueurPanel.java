package com.viewmodel;

import com.adeuza.movalysfwk.mf4jcommons.core.beans.Scope;
import com.adeuza.movalysfwk.mf4jcommons.core.beans.ScopePolicy;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ItemViewModel;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ItemViewModelId;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ListViewModel;
import com.model.Joueur;
import com.model.JoueurFactory;

/**
 * 
 */
@Scope(ScopePolicy.PROTOTYPE)
public interface VMListJoueurPanel extends ItemViewModel<Joueur>, ItemViewModelId
//@non-generated-start[class-signature]
//@non-generated-end
{

	//@non-generated-start[constants]
	//@non-generated-end

	/**
	 * getter method for nom
	 * @return Stringvalue of nom
	 */
	public String getNom();

	/**
	 * setter method for nom
	 * @param p_onom the value to set
	 */
	public void setNom(String p_onom);

	/**
	 * getter method for prenom
	 * @return Stringvalue of prenom
	 */
	public String getPrenom();

	/**
	 * setter method for prenom
	 * @param p_oprenom the value to set
	 */
	public void setPrenom(String p_oprenom);

	//@non-generated-start[methods]
	//@non-generated-end
}
