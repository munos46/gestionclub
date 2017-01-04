package com.viewmodel;

import java.sql.Date;
import com.adeuza.movalysfwk.mf4jcommons.core.beans.Scope;
import com.adeuza.movalysfwk.mf4jcommons.core.beans.ScopePolicy;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ItemViewModel;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ItemViewModelId;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ListViewModel;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.utils.DateUtils;
import com.model.Entrainement;
import com.model.EntrainementFactory;
import com.model.Lieu;
import com.model.LieuFactory;

/**
 * 
 */
@Scope(ScopePolicy.PROTOTYPE)
public interface VMListEntrainPanel extends ItemViewModel<Entrainement>, ItemViewModelId
//@non-generated-start[class-signature]
//@non-generated-end
{

	//@non-generated-start[constants]
	//@non-generated-end

	/**
	 * getter method for dateHeure
	 * @return Longvalue of dateHeure
	 */
	public Long getDateHeure();

	/**
	 * setter method for dateHeure
	 * @param p_odateHeure the value to set
	 */
	public void setDateHeure(Long p_odateHeure);

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

	//@non-generated-start[methods]
	//@non-generated-end
}
