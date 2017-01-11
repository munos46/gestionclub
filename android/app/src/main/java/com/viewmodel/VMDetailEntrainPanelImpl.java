package com.viewmodel;

import java.sql.Date;
import java.util.Set;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.Dataloader;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.DataLoaderParts;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.services.BeanLoader;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.services.ExtBeanType;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.AbstractItemViewModelId;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.clonable.NonClonableViewModel;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.listener.ListenerOnFieldModified;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ListViewModel;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ListViewModelImpl;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.utils.DateUtils;
import com.adoliveira.gestionclub.loader.DetailEntrainPanelLoader;
import com.adoliveira.gestionclub.viewmodel.ViewModelCreator;
import com.model.Entrainement;
import com.model.EntrainementFactory;
import com.model.Lieu;
import com.model.LieuFactory;

/**
 * 
 */
public class VMDetailEntrainPanelImpl
//@non-generated-start[class-signature-extends][X]
		extends AbstractItemViewModelId<Entrainement>
//@non-generated-end
		implements VMDetailEntrainPanel
//@non-generated-start[class-signature]
//@non-generated-end
{
	/**
	 * Key used to identify the dateHeure attribute
	 */
	protected static final String KEY_DATEHEURE = "dateHeure";
	/**
	 * Key used to identify the VMDetailEntrainPanelLieu entity attribute
	 */
	protected static final String KEY_VMDETAILENTRAINPANELLIEU = "VMDetailEntrainPanelLieu";

	/**
	 * 
	 * 
	 * <p>Attribute </p>
	 * <p> type=Date mandatory=false</p>
	 */
	//@non-generated-start[attribute-dateHeure]
	//@non-generated-end[attribute-dateHeure]
	private Date dateHeure;
	/**
	 * selected element on combo 
	 */
	@NonClonableViewModel
	private VMDetailEntrainPanelLieu oVMDetailEntrainPanelLieu;

	/**
	 * combo attribute 
	 */
	private ListViewModel<Lieu, VMDetailEntrainPanelLieu> lstVMDetailEntrainPanelLieu = null;

	//@non-generated-start[attributes]
	//@non-generated-end

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Date getDateHeure() {
		return this.dateHeure;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setDateHeure(Date p_oDateHeure) {
		this.affectObjectAndNotify(this.dateHeure, p_oDateHeure, KEY_DATEHEURE);
	}

	/**
	 * Returns the combo selected item view model oVMDetailEntrainPanelLieu
	 * @return the value of oVMDetailEntrainPanelLieu
	 */
	public VMDetailEntrainPanelLieu getVMDetailEntrainPanelLieu() {
		return this.oVMDetailEntrainPanelLieu;
	}

	/**
	 * returns the combo view model lstVMDetailEntrainPanelLieu
	 * @return the value of lstVMDetailEntrainPanelLieu
	 */
	public ListViewModel<Lieu, VMDetailEntrainPanelLieu> getLstVMDetailEntrainPanelLieu() {
		return this.lstVMDetailEntrainPanelLieu;
	}

	/**
	 * sets the combo selected item view model.
	 * @param p_oVMDetailEntrainPanelLieu combo selected item view model
	 */
	public void setVMDetailEntrainPanelLieu(VMDetailEntrainPanelLieu p_oVMDetailEntrainPanelLieu) {
		if (this.oVMDetailEntrainPanelLieu != p_oVMDetailEntrainPanelLieu) {
			Object sOldVal = this.oVMDetailEntrainPanelLieu;
			this.oVMDetailEntrainPanelLieu = p_oVMDetailEntrainPanelLieu;
			this.notifyFieldChanged(KEY_VMDETAILENTRAINPANELLIEU, sOldVal, p_oVMDetailEntrainPanelLieu);
		}
	}

	/**
	 * sets the combo view model.
	 * @param p_listVMDetailEntrainPanelLieus combo view model
	 */
	public void setLstVMDetailEntrainPanelLieu(ListViewModel<Lieu, VMDetailEntrainPanelLieu> p_listVMDetailEntrainPanelLieus) {
		this.lstVMDetailEntrainPanelLieu = p_listVMDetailEntrainPanelLieus;
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ItemViewModel#modifyToIdentifiable(com.adeuza.movalysfwk.mf4jcommons.core.beans.MIdentifiable)
	 */
	public void modifyToIdentifiable(Entrainement p_oEntity) {
		//@non-generated-start[modifyToIdentifiable-before][X]
		//@non-generated-end
		if (p_oEntity != null) {
			p_oEntity.setId(this.id_id);
			p_oEntity.setDateHeure(DateUtils.getTime(this.dateHeure));
			if (this.oVMDetailEntrainPanelLieu == null) {
				p_oEntity.setLieu(null);
			} else {
				Lieu oLieu = BeanLoader.getInstance().getBean(LieuFactory.class).createInstance();
				this.oVMDetailEntrainPanelLieu.modifyToIdentifiable(oLieu);
				p_oEntity.setLieu(oLieu);
			}
			//@non-generated-start[modify-to-identifiable][X]
			//@non-generated-end
		}
		//@non-generated-start[modifyToIdentifiable-after][X]
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.AbstractItemViewModel#updateFromIdentifiable(com.adeuza.movalysfwk.mf4jcommons.core.beans.MIdentifiable)
	 */
	@Override
	public void updateFromIdentifiable(Entrainement p_oEntity) {
		//@non-generated-start[updateFromIdentifiable-notify-before][X]
		this.setAlwaysNotify(false);
		//@non-generated-end
		//@non-generated-start[updateFromIdentifiable-before][X]
		//@non-generated-end
		this.clear();
		if (p_oEntity != null) {
			this.setId_id(p_oEntity.getId());
			this.setDateHeure(DateUtils.getDate(p_oEntity.getDateHeure()));
			if (p_oEntity.getLieu() == null) {
				this.setVMDetailEntrainPanelLieu(null);
			} else {
				this.setVMDetailEntrainPanelLieu(this.lstVMDetailEntrainPanelLieu.getCacheVMById(p_oEntity.getLieu()));
			}

			//@non-generated-start[update-from-identifiable][X]
			//@non-generated-end
		}
		//@non-generated-start[updateFromIdentifiable-after][X]
		//@non-generated-end
		this.computeEditableFlag();
		//@non-generated-start[updateFromIdentifiable-notify-after][X]
		this.setAlwaysNotify(true);
		this.doOnDataLoaded(null);
		//@non-generated-end
	}

	/**
	 * Updates the viewmodel using a DetailEntrainPanelLoader.
	 * @param p_oDataloader
	 * 			The dataloader.
	 */
	@Override
	public void updateFromDataloader(final Dataloader<?> p_oDataloader) {
		//@non-generated-start[updateFromDataLoader-method][X]
		final ViewModelCreator oVMCreator = (ViewModelCreator) BeanLoader.getInstance().getBean(ExtBeanType.ViewModelCreator);
		if (p_oDataloader == null) {
			this.updateFromIdentifiable(null);
		} else if (DetailEntrainPanelLoader.class.isAssignableFrom(p_oDataloader.getClass())) {
			final DetailEntrainPanelLoader oDataloader = (DetailEntrainPanelLoader) p_oDataloader;
			Entrainement data = oDataloader.getData(this.getKey());
			if (data == null) {
				data = BeanLoader.getInstance().getBean(EntrainementFactory.class).createInstance();
			}

			Set<DataLoaderParts> reload = oDataloader.popReload(this.getKey());
			ViewModelCreator.VOVMDetailEntrainPanel oInfo = new ViewModelCreator.VOVMDetailEntrainPanel();
			oInfo.setListLieu(oDataloader.getListLieu());
			oInfo.setListLieuModified(reload.contains(DetailEntrainPanelLoader.DataLoaderPartEnum.LIEU));

			oVMCreator.createOrUpdateVMDetailEntrainPanel(data, reload.contains(DetailEntrainPanelLoader.DataLoaderPartEnum.DATA), oInfo);
		}
		//@non-generated-end
	}

	/**
	 * Clear this view model.
	 */
	public void clear() {
		this.id_id = -1;
		this.dateHeure = null;
		this.oVMDetailEntrainPanelLieu = null;
		//@non-generated-start[clear-after]

		//@non-generated-end
		super.clear();
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
