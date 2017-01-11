package com.viewmodel;

import com.adeuza.movalysfwk.mf4jcommons.core.beans.Scope;
import com.adeuza.movalysfwk.mf4jcommons.core.beans.ScopePolicy;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ItemViewModel;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.UpdatableFromDataloader;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ViewModel;

/**
 * 
 */
@Scope(ScopePolicy.SINGLETON)
public interface VMListJoueurScreen extends ViewModel
//@non-generated-start[class-signature]
//@non-generated-end
{

	//@non-generated-start[constants]
	//@non-generated-end

	/**
	 * getter for sub view model VMListListJoueurPanel
	 * @return the value of VMListListJoueurPanel
	 */
	public VMListListJoueurPanel getVMListListJoueurPanel();

	/**
	 * setter for sub view model VMListListJoueurPanel
	 * @param p_oData the new value to set
	 */
	public void setVMListListJoueurPanel(VMListListJoueurPanel p_oData);

	//@non-generated-start[methods]
	//@non-generated-end
}
