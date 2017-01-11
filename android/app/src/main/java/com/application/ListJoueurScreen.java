package com.application;

import java.util.ArrayList;
import java.util.List;
import com.action.deletedetailjoueurpanel.DeleteDetailJoueurPanel;
import com.action.savedetailjoueurpanel.SaveDetailJoueurPanel;
import com.adeuza.movalysfwk.mobile.mf4android.activity.AbstractMultiSectionAutoBindFragmentActivity;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericdisplay.GenericLoadDataForDisplayDetailAction;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericdisplay.GenericUpdateVMForDisplayDetailAction;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericdisplay.InUpdateVMParameter;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericdisplay.LoadDataForMultipleDisplayDetailAction;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericdisplay.LoadDataForMultipleDisplayDetailActionParameter;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericsave.ChainSaveActionDetailParameter;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericsave.ChainSaveDetailAction;
import com.adeuza.movalysfwk.mobile.mf4android.ui.views.MMAdaptableListView;
import com.adeuza.movalysfwk.mobile.mf4android.ui.views.MMListView;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.listener.ListenerOnDataLoaderReload;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.listener.ListenerOnDataLoaderReloadEvent;
import com.adeuza.movalysfwk.mobile.mf4javacommons.event.listener.ListenerOnBusinessNotification;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.application.Application;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.business.genericdisplay.InDisplayParameter;
import com.adoliveira.gestionclub.loader.ListJoueurPanelLoader;
import com.adoliveira.gestionclub.R;
import com.adoliveira.gestionclub.viewmodel.ViewModelCreator;
import com.model.Joueur;
import com.panel.ListJoueurPanel;
import com.viewmodel.AdapterListJoueurPanelImpl;
import com.viewmodel.VMListJoueurScreen;
import com.viewmodel.VMListJoueurScreenImpl;
import com.viewmodel.VMListListJoueurPanel;
import android.content.Intent;

/**
 * 
 */
public class ListJoueurScreen
//@non-generated-start[class-signature-extends][X]
		extends AbstractMultiSectionAutoBindFragmentActivity
//@non-generated-end

//@non-generated-start[class-signature]
//@non-generated-end
{
	/** 
	 * Result code use with method startActivityForResult.
	 * In support-v7, only the last five digits of the result code are read.
	 * If the result value is greater, an exception will be raised.
	 */
	public static final int REQUEST_CODE = ListJoueurScreen.class.getSimpleName().hashCode() & REQUEST_CODE_MASK;

	//@non-generated-start[attributes]
	//@non-generated-end

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.activity.AbstractMMActivity#doAfterSetContentView()
	 */
	@Override
	protected void doAfterSetContentView() {
		super.doAfterSetContentView();
		//@non-generated-start[do-after-set-content-view][X]
		//@non-generated-end
	}

	/**
	 * Listener on ListJoueurPanelLoader reload
	 * @param p_oEvent the event sent from the dataloader
	 */
	@ListenerOnDataLoaderReload(ListJoueurPanelLoader.class)
	public void doOnReloadListJoueurPanelLoader(ListenerOnDataLoaderReloadEvent<ListJoueurPanelLoader> p_oEvent) {
		//@non-generated-start[doOnReloadListJoueurPanelLoader][X]

		final InUpdateVMParameter oActionParameter = new InUpdateVMParameter();
		oActionParameter.setDataLoader(ListJoueurPanelLoader.class);

		oActionParameter.setVm(VMListListJoueurPanel.class);
		this.launchAction(GenericUpdateVMForDisplayDetailAction.class, oActionParameter);
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.activity.AbstractAutoBindMMActivity#createViewModel()
	 */
	@Override
	public VMListJoueurScreen createViewModel() {
		//@non-generated-start[create-view-model][X]
		ViewModelCreator oCreator = (ViewModelCreator) Application.getInstance().getViewModelCreator();
		return oCreator.createVMListJoueurScreen();
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.activity.AbstractAutoBindMMActivity#getViewId()
	 */
	@Override
	public int getViewId() {
		return R.layout.glistjoueurscreen__screendetail__master;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getMultiSectionId() {
		return R.id.main__listjoueurscreen__visualpanel;
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
