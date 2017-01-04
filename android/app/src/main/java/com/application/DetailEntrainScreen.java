package com.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.action.deletedetailentrainpanel.DeleteDetailEntrainPanel;
import com.action.savedetailentrainpanel.SaveDetailEntrainPanel;
import com.adeuza.movalysfwk.mobile.mf4android.activity.AbstractMMActivity;
import com.adeuza.movalysfwk.mobile.mf4android.activity.AbstractMultiSectionAutoBindFragmentActivity;
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
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.listener.ListenerOnDataLoaderReload;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.listener.ListenerOnDataLoaderReloadEvent;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.action.Action;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.action.EntityActionParameterImpl;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.action.NullActionParameterImpl;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.actiontask.listener.ListenerOnActionSuccess;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.actiontask.listener.ListenerOnActionSuccessEvent;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.actiontask.listener.ListenerOnMenuItemClick;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.application.Application;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.business.genericdisplay.InDisplayParameter;
import com.adoliveira.gestionclub.loader.DetailEntrainPanelLoader;
import com.adoliveira.gestionclub.R;
import com.adoliveira.gestionclub.viewmodel.ViewModelCreator;
import com.model.Entrainement;
import com.panel.DetailEntrainPanel;
import com.viewmodel.VMDetailEntrainPanel;
import com.viewmodel.VMDetailEntrainScreen;
import com.viewmodel.VMDetailEntrainScreenImpl;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.view.Window;
import android.view.WindowManager;

/**
 * 
 */
public class DetailEntrainScreen
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
	public static final int REQUEST_CODE = DetailEntrainScreen.class.getSimpleName().hashCode() & REQUEST_CODE_MASK;

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
		//		getSupportActionBar().setTitle(R.string.DetailEntrainScreen);
		//@non-generated-end
	}

	/**
	 * Listener on DetailEntrainPanelLoader reload
	 * @param p_oEvent the event sent from the dataloader
	 */
	@ListenerOnDataLoaderReload(DetailEntrainPanelLoader.class)
	public void doOnReloadDetailEntrainPanelLoader(ListenerOnDataLoaderReloadEvent<DetailEntrainPanelLoader> p_oEvent) {
		//@non-generated-start[doOnReloadDetailEntrainPanelLoader][X]

		final InUpdateVMParameter oActionParameter = new InUpdateVMParameter();
		oActionParameter.setDataLoader(DetailEntrainPanelLoader.class);

		oActionParameter.setVm(VMDetailEntrainPanel.class);
		this.launchAction(GenericUpdateVMForDisplayDetailAction.class, oActionParameter);
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.activity.AbstractAutoBindMMActivity#createViewModel()
	 */
	@Override
	public VMDetailEntrainScreen createViewModel() {
		//@non-generated-start[create-view-model][X]
		ViewModelCreator oCreator = (ViewModelCreator) Application.getInstance().getViewModelCreator();
		return oCreator.createVMDetailEntrainScreen();
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.activity.AbstractAutoBindMMActivity#getViewId()
	 */
	@Override
	public int getViewId() {
		return R.layout.gdetailentrainscreen__screendetail__master;
	}

	/**
	 * {@inheritDoc} 
	 * @see com.adeuza.movalysfwk.mobile.mf4android.activity.AbstractAutoBindMMActivity#doOnKeepModifications(android.content.DialogInterface)
	 */
	@Override
	protected void doOnKeepModifications(DialogInterface p_oDialog) {
		//@non-generated-start[doOnKeepModifications][X]
		NullActionParameterImpl oParameterIn = new NullActionParameterImpl();
		oParameterIn.setRuleParameters(this.getParameters());

		this.launchAction(SaveDetailEntrainPanel.class, oParameterIn, this);
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getMultiSectionId() {
		return R.id.main__detailentrainscreen__visualpanel;
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.activity.AbstractMMActivity#getOptionMenuIds()
	 */
	@Override
	public List<Integer> getOptionMenuIds() {
		//@non-generated-start[method-option-menu][X]
		List<Integer> r_listMenuIds = super.getOptionMenuIds();

		r_listMenuIds.add(0, R.menu.detailentrainscreen_actions);

		return r_listMenuIds;
		//@non-generated-end
	}

	/**
	 * Listener du menu contextuel d'id actionmenu_detailentrainscreen_delete 
	 */
	//@non-generated-start[onMenuItemactionmenu_detailentrainscreen_delete][X]
	@ListenerOnMenuItemClick(R.id.actionmenu_detailentrainscreen_delete)
	//@non-generated-end
	public void launchDetailEntrainScreenDeleteAction() {
		//@non-generated-start[onMenuItemDetailEntrainScreenDeleteAction][X]
		this.launchAction(DeleteDetailEntrainPanel.class, new NullActionParameterImpl());
		//@non-generated-end
	}

	/**
	 * Listener du menu contextuel d'id actionmenu_detailentrainscreen_save 
	 */
	//@non-generated-start[onMenuItemactionmenu_detailentrainscreen_save][X]
	@ListenerOnMenuItemClick(R.id.actionmenu_detailentrainscreen_save)
	//@non-generated-end
	public void launchDetailEntrainScreenSaveAction() {
		//@non-generated-start[onMenuItemDetailEntrainScreenSaveAction][X]
		this.launchAction(SaveDetailEntrainPanel.class, new NullActionParameterImpl());
		//@non-generated-end
	}

	/**
	 * Listener method for SaveDetailEntrainPanel action successfully processed
	 * @param p_oEvent Success event of action
	 */
	@ListenerOnActionSuccess(action = SaveDetailEntrainPanel.class)
	public void doOnSaveDetailEntrainPanelSuccess(ListenerOnActionSuccessEvent<EntityActionParameterImpl<Entrainement>> p_oEvent) {
		//@non-generated-start[doOnSaveDetailEntrainPanelSuccess][X]
		this.setResult(Activity.RESULT_OK);
		this.exit();
		//@non-generated-end
	}

	/**
	 * Listener method for DeleteDetailEntrainPanel action successfully processed
	 * @param p_oEvent Success event of action
	 */
	@ListenerOnActionSuccess(action = DeleteDetailEntrainPanel.class)
	public void doOnDeleteDetailEntrainPanelSuccess(ListenerOnActionSuccessEvent<EntityActionParameterImpl<Entrainement>> p_oEvent) {
		//@non-generated-start[doOnDeleteDetailEntrainPanelSuccess][X]
		this.setResult(Activity.RESULT_OK);
		this.exit();
		//@non-generated-end
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
