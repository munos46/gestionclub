package com.panel;

import java.util.List;
import com.action.deletedetailjoueurpanel.DeleteDetailJoueurPanel;
import com.action.savedetailjoueurpanel.SaveDetailJoueurPanel;
import com.adeuza.movalysfwk.mobile.mf4android.activity.AbstractAutoBindMMActivity;
import com.adeuza.movalysfwk.mobile.mf4android.activity.AbstractMMActivity;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericdisplay.GenericLoadDataForDisplayDetailAction;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericdisplay.GenericUpdateVMForDisplayDetailAction;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericdisplay.InUpdateVMParameter;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericdisplay.LoadDataForMultipleDisplayDetailAction;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericdisplay.LoadDataForMultipleDisplayDetailActionParameter;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericdisplay.OutUpdateVMParameter;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericsave.ChainSaveDetailAction;
import com.adeuza.movalysfwk.mobile.mf4android.fragment.AbstractAutoBindMMFragment;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.listener.ListenerOnDataLoaderReload;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.listener.ListenerOnDataLoaderReloadEvent;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.action.EntityActionParameterImpl;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.actiontask.listener.ListenerOnActionSuccess;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.actiontask.listener.ListenerOnActionSuccessEvent;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.business.genericdisplay.InDisplayParameter;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ViewModel;
import com.adoliveira.gestionclub.loader.DetailJoueurPanelLoader;
import com.adoliveira.gestionclub.R;
import com.model.Joueur;
import com.viewmodel.VMDetailJoueurPanel;
import com.viewmodel.VMDetailJoueurScreen;
import android.view.ViewGroup;

/**
 * 
 */
public class DetailJoueurPanel
//@non-generated-start[class-signature-extends][X]
		extends AbstractAutoBindMMFragment
//@non-generated-end

//@non-generated-start[class-signature]
//@non-generated-end
{
	//@non-generated-start[attributes]
	//@non-generated-end

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.fragment.AbstractAutoBindMMFragment#doAfterInflate(ViewGroup p_oRoot)
	 */
	@Override
	protected void doAfterInflate(ViewGroup p_oRoot) {
		super.doAfterInflate(p_oRoot);
		//@non-generated-start[do-after-inflate-1][X]
		//@non-generated-end
		//@non-generated-start[do-after-inflate-2][X]
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.fragment.AbstractAutoBindMMFragment#getLayoutId()
	 */
	@Override
	public int getLayoutId() {
		return R.layout.gdetailjoueurpanel__screendetail__master;
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.fragment.AbstractAutoBindMMFragment#getFragmentViewModel()
	 */
	@Override
	protected ViewModel getFragmentViewModel() {

		return application.getViewModelCreator().getViewModel(VMDetailJoueurPanel.class);

	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.activity.AbstractAutoBindMMDialogFragment#doFillAction()
	 */
	@Override
	public void doFillAction() {
		//@non-generated-start[doFillAction][X]
		LoadDataForMultipleDisplayDetailActionParameter oMultipleDisplayParameter = new LoadDataForMultipleDisplayDetailActionParameter();

		oMultipleDisplayParameter.addDisplayParameter(new InDisplayParameter(this.getActivity().getIntent().getStringExtra(IDENTIFIER_CACHE_KEY),
				DetailJoueurPanelLoader.class));

		this.launchAction(LoadDataForMultipleDisplayDetailAction.class, oMultipleDisplayParameter);
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

	//@non-generated-start[methods]
	//@non-generated-end
}
