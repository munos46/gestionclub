package com.application;

import java.util.ArrayList;
import java.util.List;
import com.action.deletedetailentrainpanel.DeleteDetailEntrainPanel;
import com.action.savedetailentrainpanel.SaveDetailEntrainPanel;
import com.adeuza.movalysfwk.mobile.mf4android.activity.AbstractMMActivity;
import com.adeuza.movalysfwk.mobile.mf4android.activity.AbstractMultiSectionAutoBindFragmentActivity;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.displaymain.MFRootActivity;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericdisplay.GenericLoadDataForDisplayDetailAction;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericdisplay.GenericUpdateVMForDisplayDetailAction;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericdisplay.InUpdateVMParameter;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericdisplay.LoadDataForMultipleDisplayDetailAction;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericdisplay.LoadDataForMultipleDisplayDetailActionParameter;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericsave.ChainSaveActionDetailParameter;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericsave.ChainSaveDetailAction;
import com.adeuza.movalysfwk.mobile.mf4android.application.AndroidApplication;
import com.adeuza.movalysfwk.mobile.mf4android.application.AndroidApplicationR;
import com.adeuza.movalysfwk.mobile.mf4android.ui.adapters.MDKSpinnerAdapter;
import com.adeuza.movalysfwk.mobile.mf4android.ui.dialog.MMCustomDialogFragment;
import com.adeuza.movalysfwk.mobile.mf4android.ui.views.MMAdaptableListView;
import com.adeuza.movalysfwk.mobile.mf4android.ui.views.MMListView;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.listener.ListenerOnDataLoaderReload;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.listener.ListenerOnDataLoaderReloadEvent;
import com.adeuza.movalysfwk.mobile.mf4javacommons.event.listener.ListenerOnBusinessNotification;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.application.Application;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.business.genericdisplay.InDisplayParameter;
import com.adoliveira.gestionclub.loader.ListEntrainPanelLoader;
import com.adoliveira.gestionclub.R;
import com.adoliveira.gestionclub.viewmodel.ViewModelCreator;
import com.model.Entrainement;
import com.panel.ListEntrainPanel;
import com.viewmodel.AdapterListEntrainPanelImpl;
import com.viewmodel.VMListEntrainScreen;
import com.viewmodel.VMListEntrainScreenImpl;
import com.viewmodel.VMListListEntrainPanel;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.view.Window;
import android.view.WindowManager;

/**
 * 
 */
public class ListEntrainScreen
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
	public static final int REQUEST_CODE = ListEntrainScreen.class.getSimpleName().hashCode() & REQUEST_CODE_MASK;

	//@non-generated-start[attributes]
	//@non-generated-end

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.activity.AbstractMMActivity#doAfterSetContentView()
	 */
	@Override
	protected void doAfterSetContentView() {
		super.doAfterSetContentView();
		//@non-generated-start[do-after-set-content-view]
		if (android.os.Build.VERSION.SDK_INT >= 21) {
			Window window = this.getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		}

		//		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		//		getSupportActionBar().setCustomView(R.layout.action_bar);
		//		getSupportActionBar().setTitle(R.string.ListEntrainScreen);
		//@non-generated-end
	}

	/**
	 * Listener on ListEntrainPanelLoader reload
	 * @param p_oEvent the event sent from the dataloader
	 */
	@ListenerOnDataLoaderReload(ListEntrainPanelLoader.class)
	public void doOnReloadListEntrainPanelLoader(ListenerOnDataLoaderReloadEvent<ListEntrainPanelLoader> p_oEvent) {
		//@non-generated-start[doOnReloadListEntrainPanelLoader][X]

		final InUpdateVMParameter oActionParameter = new InUpdateVMParameter();
		oActionParameter.setDataLoader(ListEntrainPanelLoader.class);

		oActionParameter.setVm(VMListListEntrainPanel.class);
		this.launchAction(GenericUpdateVMForDisplayDetailAction.class, oActionParameter);
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.activity.AbstractAutoBindMMActivity#createViewModel()
	 */
	@Override
	public VMListEntrainScreen createViewModel() {
		//@non-generated-start[create-view-model][X]
		ViewModelCreator oCreator = (ViewModelCreator) Application.getInstance().getViewModelCreator();
		return oCreator.createVMListEntrainScreen();
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.activity.AbstractAutoBindMMActivity#getViewId()
	 */
	@Override
	public int getViewId() {
		return R.layout.glistentrainscreen__screendetail__master;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getMultiSectionId() {
		return R.id.main__listentrainscreen__visualpanel;
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
