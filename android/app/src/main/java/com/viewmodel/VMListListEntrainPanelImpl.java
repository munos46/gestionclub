package com.viewmodel;

import java.sql.Date;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.Dataloader;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.services.BeanLoader;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.services.ExtBeanType;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ListViewModel;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ListViewModelImpl;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.utils.DateUtils;
import com.adoliveira.gestionclub.loader.ListEntrainPanelLoader;
import com.adoliveira.gestionclub.viewmodel.ViewModelCreator;
import com.model.Entrainement;
import com.model.EntrainementFactory;
import com.model.Lieu;
import com.model.LieuFactory;

/**
 * 
 */
public class VMListListEntrainPanelImpl
//@non-generated-start[class-signature-extends][X]
		extends ListViewModelImpl<Entrainement, VMListEntrainPanel>
//@non-generated-end
		implements VMListListEntrainPanel
//@non-generated-start[class-signature]
//@non-generated-end
{
	//@non-generated-start[attributes]
	//@non-generated-end

	/** 
	 * Constructor
	 */
	public VMListListEntrainPanelImpl() {
		super(VMListEntrainPanel.class);
	}

	/**
	 * Updates the viewmodel using a ListEntrainPanelLoader.
	 * @param p_oDataloader
	 * 			The dataloader.
	 */
	@Override
	public void updateFromDataloader(final Dataloader<?> p_oDataloader) {
		//@non-generated-start[updateFromDataLoader-method][X]
		final ViewModelCreator oVMCreator = (ViewModelCreator) BeanLoader.getInstance().getBean(ExtBeanType.ViewModelCreator);
		if (p_oDataloader == null) {
			this.updateFromIdentifiable(null);
		} else if (ListEntrainPanelLoader.class.isAssignableFrom(p_oDataloader.getClass())) {
			final ListEntrainPanelLoader oDataloader = (ListEntrainPanelLoader) p_oDataloader;
			this.clear();
			oVMCreator.createOrUpdateVMListListEntrainPanel(oDataloader.getData(this.getKey()));
		}
		//@non-generated-end
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
