package com.adoliveira.gestionclub.viewmodel;

import java.util.Collection;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.AbstractViewModel;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.DefaultViewModelCreator;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ListViewModel;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ListViewModelImpl;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ViewModel;
import com.model.Entrainement;
import com.model.Lieu;
import com.viewmodel.VMDetailEntrainPanel;
import com.viewmodel.VMDetailEntrainPanelLieu;
import com.viewmodel.VMDetailEntrainScreen;
import com.viewmodel.VMDetailEntrainScreenImpl;
import com.viewmodel.VMListEntrainPanel;
import com.viewmodel.VMListEntrainScreen;
import com.viewmodel.VMListEntrainScreenImpl;
import com.viewmodel.VMListListEntrainPanel;

/**
 * 
 */
public class ViewModelCreator
//@non-generated-start[class-signature-extends][X]
		extends DefaultViewModelCreator
//@non-generated-end

//@non-generated-start[class-signature]
//@non-generated-end
{
	//@non-generated-start[attributes]
	//@non-generated-end

	/**
	 * Modification of data for VMDetailEntrainPanel
	 */
	public static class VOVMDetailEntrainPanel {
		/** attributes */
		/** private Lieu list */
		private Collection<Lieu> listLieu;
		/** private boolean Lieu modified */
		private boolean listLieuModified;

		/** constructor */
		public VOVMDetailEntrainPanel() {
			this.listLieuModified = false;
		}

		/** getter/setter */

		/**
		 * setter for listLieu
		 * @param p_olistLieu the value to set
		 */
		public void setListLieu(Collection<Lieu> p_olistLieu) {
			this.listLieu = p_olistLieu;
		}

		/**
		 * getter for listLieu
		 * @return the value of listLieu
		 */
		public Collection<Lieu> getListLieu() {
			return this.listLieu;
		}

		/**
		 * sets the modified status of listLieu
		 * @param p_olistLieuModified true if the list was modified
		 */
		public void setListLieuModified(boolean p_olistLieuModified) {
			this.listLieuModified = p_olistLieuModified;
		}

		/**
		 * returns whether listLieu was modified
		 * @return true if listLieu was modified
		 */
		public boolean isListLieuModified() {
			return this.listLieuModified;
		}

	}

	/**
	 * Create an empty viewmodel.
	 * @return An empty view model.
	 */
	public VMDetailEntrainPanel createVMDetailEntrainPanel() {
		return this.createOrUpdateVMDetailEntrainPanel(null, true, new VOVMDetailEntrainPanel());
	}

	/**
	 * Create and update a view model using a Entrainement
	 * @param p_oData An instance of Entrainement
	 * @param p_bModified true if the entity was modified
	 * @param p_oInfo an instance of VOVMDetailEntrainPanel
	 * @return The view model representation of an Entrainement instance
	 */
	public VMDetailEntrainPanel createOrUpdateVMDetailEntrainPanel(final Entrainement p_oData, boolean p_bModified, VOVMDetailEntrainPanel p_oInfo) {
		VMDetailEntrainPanel r_oMasterViewModel = null;
		if (p_bModified) {
			r_oMasterViewModel = this.createVM(VMDetailEntrainPanel.class);
		} else {
			r_oMasterViewModel = this.getViewModel(VMDetailEntrainPanel.class);
		}
		ListViewModel<Lieu, VMDetailEntrainPanelLieu> listVMDetailEntrainPanelLieus1 = r_oMasterViewModel.getLstVMDetailEntrainPanelLieu();
		if (listVMDetailEntrainPanelLieus1 == null) {
			listVMDetailEntrainPanelLieus1 = new ListViewModelImpl<Lieu, VMDetailEntrainPanelLieu>(VMDetailEntrainPanelLieu.class);
			r_oMasterViewModel.setLstVMDetailEntrainPanelLieu(listVMDetailEntrainPanelLieus1);
		} else if (p_oInfo.isListLieuModified()) {
			listVMDetailEntrainPanelLieus1.clear();
		}
		if (p_oInfo.getListLieu() != null && p_oInfo.isListLieuModified()) {
			listVMDetailEntrainPanelLieus1.setItems(p_oInfo.getListLieu());
		}

		if (p_bModified) {
			r_oMasterViewModel.updateFromIdentifiable(p_oData);
		}
		return r_oMasterViewModel;
	}

	/**
	 * Create an empty viewmodel.
	 * @return An empty view model.
	 */
	public VMDetailEntrainPanelLieu createVMDetailEntrainPanelLieu() {
		return this.createOrUpdateVMDetailEntrainPanelLieu(null);
	}

	/**
	 * Create and update a view model using a Lieu
	 * @param p_oData An instance of Lieu
	 * @return The view model representation of an Lieu instance
	 */
	public VMDetailEntrainPanelLieu createOrUpdateVMDetailEntrainPanelLieu(final Lieu p_oData) {
		final VMDetailEntrainPanelLieu r_oMasterViewModel = this.createVM(p_oData, VMDetailEntrainPanelLieu.class);
		r_oMasterViewModel.updateFromIdentifiable(p_oData);
		return r_oMasterViewModel;
	}

	/**
	 * Create and update the view model.
	 * @return The view model
	 */
	public VMDetailEntrainScreen createVMDetailEntrainScreen() {
		final VMDetailEntrainScreen r_oMasterViewModel = this.createVM(VMDetailEntrainScreen.class);
		r_oMasterViewModel.setVMDetailEntrainPanel(this.createVMDetailEntrainPanel());

		return r_oMasterViewModel;
	}

	/**
	 * Create an empty viewmodel.
	 * @return An empty view model.
	 */
	public VMListEntrainPanel createVMListEntrainPanel() {
		return this.createOrUpdateVMListEntrainPanel(null);
	}

	/**
	 * Create and update a view model using a Entrainement
	 * @param p_oData An instance of Entrainement
	 * @return The view model representation of an Entrainement instance
	 */
	public VMListEntrainPanel createOrUpdateVMListEntrainPanel(final Entrainement p_oData) {
		final VMListEntrainPanel r_oMasterViewModel = this.createVM(p_oData, VMListEntrainPanel.class);
		r_oMasterViewModel.updateFromIdentifiable(p_oData);
		return r_oMasterViewModel;
	}

	/**
	 * Create and update the view model.
	 * @return The view model
	 */
	public VMListEntrainScreen createVMListEntrainScreen() {
		final VMListEntrainScreen r_oMasterViewModel = this.createVM(VMListEntrainScreen.class);
		r_oMasterViewModel.setVMListListEntrainPanel(this.createVMListListEntrainPanel());

		return r_oMasterViewModel;
	}

	/**
	 * Create an empty viewmodel.
	 * @return An empty view model.
	 */
	public VMListListEntrainPanel createVMListListEntrainPanel() {
		return this.createOrUpdateVMListListEntrainPanel(null);
	}

	/**
	 * Create and update a view model using a collection of Entrainement.
	 * @param p_oDatas A collection of Entrainement.
	 * @return The view model representation of a collection of Entrainement.
	 */
	public VMListListEntrainPanel createOrUpdateVMListListEntrainPanel(final Collection<Entrainement> p_oDatas) {
		final VMListListEntrainPanel r_oMasterViewModel = this.createVM("com.viewmodel.VMListListEntrainPanel", p_oDatas,
				VMListListEntrainPanel.class, VMListEntrainPanel.class);
		return r_oMasterViewModel;
	}

	/**
	 * Retreive a view model into the cache. Here, there isn't creation or update.
	 * @param p_oData A entity's instance. Never null.
	 * @return The view model representation of <code>p_oData</code> if it exists, null otherwise.
	 */
	public VMDetailEntrainPanelLieu getVMDetailEntrainPanelLieu(Lieu p_oData) {
		return this.getViewModel(p_oData, VMDetailEntrainPanelLieu.class);
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
