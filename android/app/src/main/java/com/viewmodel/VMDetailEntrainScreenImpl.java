package com.viewmodel;

import java.util.HashMap;
import java.util.Map;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.Dataloader;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.context.MContext;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.AbstractViewModel;
import com.adoliveira.gestionclub.loader.DetailEntrainPanelLoader;

/**
 * 
 */
public class VMDetailEntrainScreenImpl
//@non-generated-start[class-signature-extends][X]
		extends AbstractViewModel
//@non-generated-end
		implements VMDetailEntrainScreen
//@non-generated-start[class-signature]
//@non-generated-end
{
	/**
	 * sub view model VMDetailEntrainPanelImpl
	 */
	private VMDetailEntrainPanel oVMDetailEntrainPanel = null;

	//@non-generated-start[attributes]
	//@non-generated-end

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VMDetailEntrainPanel getVMDetailEntrainPanel() {
		return this.oVMDetailEntrainPanel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setVMDetailEntrainPanel(VMDetailEntrainPanel p_oData) {
		this.oVMDetailEntrainPanel = p_oData;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEditable() {
		//@non-generated-start[isEditable-method][X]
		return this.oVMDetailEntrainPanel != null && this.oVMDetailEntrainPanel.isEditable();
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEditable(final MContext p_oContext, final Map<String, Object> p_mapParameters) {
		//@non-generated-start[setEditable-method][X]
		if (this.oVMDetailEntrainPanel != null) {
			this.oVMDetailEntrainPanel.setEditable(p_oContext, p_mapParameters);
		}//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isReadyToChanged() {
		//@non-generated-start[isReadyToChanged-method][X]
		return this.oVMDetailEntrainPanel != null && this.oVMDetailEntrainPanel.isReadyToChanged();
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		//@non-generated-start[clear-method][X]
		this.oVMDetailEntrainPanel.clear();

		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doOnDataLoaded(Map<String, Object> p_mapParameters) {
		//@non-generated-start[doOnDataLoaded-method][X]
		this.oVMDetailEntrainPanel.doOnDataLoaded(p_mapParameters);

		//@non-generated-end
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
