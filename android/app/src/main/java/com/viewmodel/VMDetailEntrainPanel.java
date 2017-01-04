package com.viewmodel;

import java.sql.Date;
import com.adeuza.movalysfwk.mf4jcommons.core.beans.Scope;
import com.adeuza.movalysfwk.mf4jcommons.core.beans.ScopePolicy;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.Dataloader;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ItemViewModel;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ItemViewModelId;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ListViewModel;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.UpdatableFromDataloader;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.utils.DateUtils;
import com.adoliveira.gestionclub.loader.DetailEntrainPanelLoader;
import com.model.Entrainement;
import com.model.EntrainementFactory;
import com.model.Lieu;
import com.model.LieuFactory;

/**
 * 
 */
@Scope(ScopePolicy.SINGLETON)
public interface VMDetailEntrainPanel extends ItemViewModel<Entrainement>, UpdatableFromDataloader, ItemViewModelId
//@non-generated-start[class-signature]
//@non-generated-end
{

	//@non-generated-start[constants]
	//@non-generated-end

	/**
	 * getter method for dateHeure
	 * @return Datevalue of dateHeure
	 */
	public Date getDateHeure();

	/**
	 * setter method for dateHeure
	 * @param p_odateHeure the value to set
	 */
	public void setDateHeure(Date p_odateHeure);

	/**
	 * getter method for 
	 * @return VMDetailEntrainPanelLieuvalue of 
	 */
	public VMDetailEntrainPanelLieu getVMDetailEntrainPanelLieu();

	/**
	 * setter method for 
	 * @param p_oVMDetailEntrainPanelLieu the value to set
	 */
	public void setVMDetailEntrainPanelLieu(VMDetailEntrainPanelLieu p_oVMDetailEntrainPanelLieu);

	/**
	 * getter method for 
	 * @return ListViewModelvalue of 
	 */
	public ListViewModel<Lieu, VMDetailEntrainPanelLieu> getLstVMDetailEntrainPanelLieu();

	/**
	 * setter method for 
	 * @param p_oVMDetailEntrainPanelLieu the value to set
	 */
	public void setLstVMDetailEntrainPanelLieu(ListViewModel<Lieu, VMDetailEntrainPanelLieu> p_oVMDetailEntrainPanelLieu);

	//@non-generated-start[methods]
	//@non-generated-end
}
