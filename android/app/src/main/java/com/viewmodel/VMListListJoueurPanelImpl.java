package com.viewmodel;

import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.Dataloader;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.services.BeanLoader;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.services.ExtBeanType;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ListViewModel;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ListViewModelImpl;
import com.adoliveira.gestionclub.loader.ListJoueurPanelLoader;
import com.adoliveira.gestionclub.viewmodel.ViewModelCreator;
import com.model.Joueur;
import com.model.JoueurFactory;

/**
 * 
 */
public class VMListListJoueurPanelImpl
//@non-generated-start[class-signature-extends][X]
		extends ListViewModelImpl<Joueur, VMListJoueurPanel>
//@non-generated-end
		implements VMListListJoueurPanel
//@non-generated-start[class-signature]
//@non-generated-end
{
	//@non-generated-start[attributes]
	//@non-generated-end

	/** 
	 * Constructor
	 */
	public VMListListJoueurPanelImpl() {
		super(VMListJoueurPanel.class);
	}

	/**
	 * Updates the viewmodel using a ListJoueurPanelLoader.
	 * @param p_oDataloader
	 * 			The dataloader.
	 */
	@Override
	public void updateFromDataloader(final Dataloader<?> p_oDataloader) {
		//@non-generated-start[updateFromDataLoader-method][X]
		final ViewModelCreator oVMCreator = (ViewModelCreator) BeanLoader.getInstance().getBean(ExtBeanType.ViewModelCreator);
		if (p_oDataloader == null) {
			this.updateFromIdentifiable(null);
		} else if (ListJoueurPanelLoader.class.isAssignableFrom(p_oDataloader.getClass())) {
			final ListJoueurPanelLoader oDataloader = (ListJoueurPanelLoader) p_oDataloader;
			this.clear();
			oVMCreator.createOrUpdateVMListListJoueurPanel(oDataloader.getData(this.getKey()));
		}
		//@non-generated-end
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
