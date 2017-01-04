package com.viewmodel;

import java.util.HashMap;
import java.util.Map;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.Dataloader;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.context.MContext;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.AbstractViewModel;
import com.adoliveira.gestionclub.loader.ListEntrainPanelLoader;

/**
 * 
 */
public class VMListEntrainScreenImpl
//@non-generated-start[class-signature-extends][X]
		extends AbstractViewModel
//@non-generated-end
		implements VMListEntrainScreen
//@non-generated-start[class-signature]
//@non-generated-end
{
	/**
	 * sub view model VMListListEntrainPanelImpl
	 */
	private VMListListEntrainPanel oVMListListEntrainPanel = null;

	//@non-generated-start[attributes]
	//@non-generated-end

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VMListListEntrainPanel getVMListListEntrainPanel() {
		return this.oVMListListEntrainPanel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setVMListListEntrainPanel(VMListListEntrainPanel p_oData) {
		this.oVMListListEntrainPanel = p_oData;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEditable() {
		//@non-generated-start[isEditable-method][X]
		return this.oVMListListEntrainPanel != null && this.oVMListListEntrainPanel.isEditable();
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEditable(final MContext p_oContext, final Map<String, Object> p_mapParameters) {
		//@non-generated-start[setEditable-method][X]
		if (this.oVMListListEntrainPanel != null) {
			this.oVMListListEntrainPanel.setEditable(p_oContext, p_mapParameters);
		}//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isReadyToChanged() {
		//@non-generated-start[isReadyToChanged-method][X]
		return this.oVMListListEntrainPanel != null && this.oVMListListEntrainPanel.isReadyToChanged();
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		//@non-generated-start[clear-method][X]
		this.oVMListListEntrainPanel.clear();

		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doOnDataLoaded(Map<String, Object> p_mapParameters) {
		//@non-generated-start[doOnDataLoaded-method][X]
		this.oVMListListEntrainPanel.doOnDataLoaded(p_mapParameters);

		//@non-generated-end
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
