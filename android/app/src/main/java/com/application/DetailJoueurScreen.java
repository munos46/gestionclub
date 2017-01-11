package com.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.action.deletedetailjoueurpanel.DeleteDetailJoueurPanel;
import com.action.savedetailjoueurpanel.SaveDetailJoueurPanel;
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
import com.adoliveira.gestionclub.loader.DetailJoueurPanelLoader;
import com.adoliveira.gestionclub.R;
import com.adoliveira.gestionclub.viewmodel.ViewModelCreator;
import com.model.Joueur;
import com.panel.DetailJoueurPanel;
import com.viewmodel.VMDetailJoueurPanel;
import com.viewmodel.VMDetailJoueurScreen;
import com.viewmodel.VMDetailJoueurScreenImpl;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

/**
 * 
 */
public class DetailJoueurScreen
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
	public static final int REQUEST_CODE = DetailJoueurScreen.class.getSimpleName().hashCode() & REQUEST_CODE_MASK;

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
	 * Listener on DetailJoueurPanelLoader reload
	 * @param p_oEvent the event sent from the dataloader
	 */
	@ListenerOnDataLoaderReload(DetailJoueurPanelLoader.class)
	public void doOnReloadDetailJoueurPanelLoader(ListenerOnDataLoaderReloadEvent<DetailJoueurPanelLoader> p_oEvent) {
		//@non-generated-start[doOnReloadDetailJoueurPanelLoader][X]

		final InUpdateVMParameter oActionParameter = new InUpdateVMParameter();
		oActionParameter.setDataLoader(DetailJoueurPanelLoader.class);

		oActionParameter.setVm(VMDetailJoueurPanel.class);
		this.launchAction(GenericUpdateVMForDisplayDetailAction.class, oActionParameter);
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.activity.AbstractAutoBindMMActivity#createViewModel()
	 */
	@Override
	public VMDetailJoueurScreen createViewModel() {
		//@non-generated-start[create-view-model][X]
		ViewModelCreator oCreator = (ViewModelCreator) Application.getInstance().getViewModelCreator();
		return oCreator.createVMDetailJoueurScreen();
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.activity.AbstractAutoBindMMActivity#getViewId()
	 */
	@Override
	public int getViewId() {
		return R.layout.gdetailjoueurscreen__screendetail__master;
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

		this.launchAction(SaveDetailJoueurPanel.class, oParameterIn, this);
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getMultiSectionId() {
		return R.id.main__detailjoueurscreen__visualpanel;
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.activity.AbstractMMActivity#getOptionMenuIds()
	 */
	@Override
	public List<Integer> getOptionMenuIds() {
		//@non-generated-start[method-option-menu][X]
		List<Integer> r_listMenuIds = super.getOptionMenuIds();

		r_listMenuIds.add(0, R.menu.detailjoueurscreen_actions);

		return r_listMenuIds;
		//@non-generated-end
	}

	/**
	 * Listener du menu contextuel d'id actionmenu_detailjoueurscreen_delete 
	 */
	//@non-generated-start[onMenuItemactionmenu_detailjoueurscreen_delete][X]
	@ListenerOnMenuItemClick(R.id.actionmenu_detailjoueurscreen_delete)
	//@non-generated-end
	public void launchDetailJoueurScreenDeleteAction() {
		//@non-generated-start[onMenuItemDetailJoueurScreenDeleteAction][X]
		this.launchAction(DeleteDetailJoueurPanel.class, new NullActionParameterImpl());
		//@non-generated-end
	}

	/**
	 * Listener du menu contextuel d'id actionmenu_detailjoueurscreen_save 
	 */
	//@non-generated-start[onMenuItemactionmenu_detailjoueurscreen_save][X]
	@ListenerOnMenuItemClick(R.id.actionmenu_detailjoueurscreen_save)
	//@non-generated-end
	public void launchDetailJoueurScreenSaveAction() {
		//@non-generated-start[onMenuItemDetailJoueurScreenSaveAction][X]
		this.launchAction(SaveDetailJoueurPanel.class, new NullActionParameterImpl());
		//@non-generated-end
	}

	/**
	 * Listener method for SaveDetailJoueurPanel action successfully processed
	 * @param p_oEvent Success event of action
	 */
	@ListenerOnActionSuccess(action = SaveDetailJoueurPanel.class)
	public void doOnSaveDetailJoueurPanelSuccess(ListenerOnActionSuccessEvent<EntityActionParameterImpl<Joueur>> p_oEvent) {
		//@non-generated-start[doOnSaveDetailJoueurPanelSuccess][X]
		this.setResult(Activity.RESULT_OK);
		this.exit();
		//@non-generated-end
	}

	/**
	 * Listener method for DeleteDetailJoueurPanel action successfully processed
	 * @param p_oEvent Success event of action
	 */
	@ListenerOnActionSuccess(action = DeleteDetailJoueurPanel.class)
	public void doOnDeleteDetailJoueurPanelSuccess(ListenerOnActionSuccessEvent<EntityActionParameterImpl<Joueur>> p_oEvent) {
		//@non-generated-start[doOnDeleteDetailJoueurPanelSuccess][X]
		this.setResult(Activity.RESULT_OK);
		this.exit();
		//@non-generated-end
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
