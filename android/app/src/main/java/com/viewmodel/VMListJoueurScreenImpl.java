package com.viewmodel;

import java.util.HashMap;
import java.util.Map;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.Dataloader;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.context.MContext;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.AbstractViewModel;
import com.adoliveira.gestionclub.loader.ListJoueurPanelLoader;

/**
 * 
 */
public class VMListJoueurScreenImpl
//@non-generated-start[class-signature-extends][X]
		extends AbstractViewModel
//@non-generated-end
		implements VMListJoueurScreen
//@non-generated-start[class-signature]
//@non-generated-end
{
	/**
	 * sub view model VMListListJoueurPanelImpl
	 */
	private VMListListJoueurPanel oVMListListJoueurPanel = null;

	//@non-generated-start[attributes]
	//@non-generated-end

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VMListListJoueurPanel getVMListListJoueurPanel() {
		return this.oVMListListJoueurPanel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setVMListListJoueurPanel(VMListListJoueurPanel p_oData) {
		this.oVMListListJoueurPanel = p_oData;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEditable() {
		//@non-generated-start[isEditable-method][X]
		return this.oVMListListJoueurPanel != null && this.oVMListListJoueurPanel.isEditable();
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEditable(final MContext p_oContext, final Map<String, Object> p_mapParameters) {
		//@non-generated-start[setEditable-method][X]
		if (this.oVMListListJoueurPanel != null) {
			this.oVMListListJoueurPanel.setEditable(p_oContext, p_mapParameters);
		}//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isReadyToChanged() {
		//@non-generated-start[isReadyToChanged-method][X]
		return this.oVMListListJoueurPanel != null && this.oVMListListJoueurPanel.isReadyToChanged();
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		//@non-generated-start[clear-method][X]
		this.oVMListListJoueurPanel.clear();

		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doOnDataLoaded(Map<String, Object> p_mapParameters) {
		//@non-generated-start[doOnDataLoaded-method][X]
		this.oVMListListJoueurPanel.doOnDataLoaded(p_mapParameters);

		//@non-generated-end
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
