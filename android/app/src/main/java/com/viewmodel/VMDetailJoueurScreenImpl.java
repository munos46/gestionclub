package com.viewmodel;

import java.util.HashMap;
import java.util.Map;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.Dataloader;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.context.MContext;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.AbstractViewModel;
import com.adoliveira.gestionclub.loader.DetailJoueurPanelLoader;

/**
 * 
 */
public class VMDetailJoueurScreenImpl
//@non-generated-start[class-signature-extends][X]
		extends AbstractViewModel
//@non-generated-end
		implements VMDetailJoueurScreen
//@non-generated-start[class-signature]
//@non-generated-end
{
	/**
	 * sub view model VMDetailJoueurPanelImpl
	 */
	private VMDetailJoueurPanel oVMDetailJoueurPanel = null;

	//@non-generated-start[attributes]
	//@non-generated-end

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VMDetailJoueurPanel getVMDetailJoueurPanel() {
		return this.oVMDetailJoueurPanel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setVMDetailJoueurPanel(VMDetailJoueurPanel p_oData) {
		this.oVMDetailJoueurPanel = p_oData;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEditable() {
		//@non-generated-start[isEditable-method][X]
		return this.oVMDetailJoueurPanel != null && this.oVMDetailJoueurPanel.isEditable();
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEditable(final MContext p_oContext, final Map<String, Object> p_mapParameters) {
		//@non-generated-start[setEditable-method][X]
		if (this.oVMDetailJoueurPanel != null) {
			this.oVMDetailJoueurPanel.setEditable(p_oContext, p_mapParameters);
		}//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isReadyToChanged() {
		//@non-generated-start[isReadyToChanged-method][X]
		return this.oVMDetailJoueurPanel != null && this.oVMDetailJoueurPanel.isReadyToChanged();
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		//@non-generated-start[clear-method][X]
		this.oVMDetailJoueurPanel.clear();

		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doOnDataLoaded(Map<String, Object> p_mapParameters) {
		//@non-generated-start[doOnDataLoaded-method][X]
		this.oVMDetailJoueurPanel.doOnDataLoaded(p_mapParameters);

		//@non-generated-end
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
