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
import com.adeuza.movalysfwk.mobile.mf4android.fragment.AbstractMMListFragment;
import com.adeuza.movalysfwk.mobile.mf4android.ui.adapters.MDKAdapter;
import com.adeuza.movalysfwk.mobile.mf4android.ui.modele.PerformItemClickEvent;
import com.adeuza.movalysfwk.mobile.mf4android.ui.modele.SelectedItemEvent;
import com.adeuza.movalysfwk.mobile.mf4android.ui.views.MMListView;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.listener.ListenerOnDataLoaderReload;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.listener.ListenerOnDataLoaderReloadEvent;
import com.adeuza.movalysfwk.mobile.mf4javacommons.event.listener.ListenerOnBusinessNotification;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.actiontask.listener.ListenerOnActionSuccess;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.actiontask.listener.ListenerOnActionSuccessEvent;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.business.genericdisplay.InDisplayParameter;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ViewModel;
import com.adoliveira.gestionclub.loader.ListJoueurPanelLoader;
import com.adoliveira.gestionclub.R;
import com.application.DetailJoueurScreen;
import com.model.Joueur;
import com.viewmodel.AdapterListJoueurPanelImpl;
import com.viewmodel.VMListJoueurPanel;
import com.viewmodel.VMListJoueurScreen;
import com.viewmodel.VMListListJoueurPanel;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 */
public class ListJoueurPanel
//@non-generated-start[class-signature-extends][X]
		extends AbstractMMListFragment
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
		p_oRoot.findViewById(R.id.button_create_detailjoueurpanel).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//@non-generated-start[doOnFabClick][X]
				Intent oIntent = new Intent(ListJoueurPanel.this.getActivity(), DetailJoueurScreen.class);
				oIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				ListJoueurPanel.this.getActivity().startActivityForResult(oIntent, DetailJoueurScreen.REQUEST_CODE);
				//@non-generated-end
			}
		});
		//@non-generated-start[do-after-inflate-2][X]
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.fragment.AbstractAutoBindMMFragment#getLayoutId()
	 */
	@Override
	public int getLayoutId() {
		return R.layout.glistjoueurpanel__screenlist1__master;
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.fragment.AbstractAutoBindMMFragment#getFragmentViewModel()
	 */
	@Override
	protected ViewModel getFragmentViewModel() {

		return application.getViewModelCreator().getViewModel(VMListListJoueurPanel.class);

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
				ListJoueurPanelLoader.class));

		this.launchAction(LoadDataForMultipleDisplayDetailAction.class, oMultipleDisplayParameter);
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
		oActionParameter.addAdapter(this.mAdapter);
		this.launchAction(GenericUpdateVMForDisplayDetailAction.class, oActionParameter);
		//@non-generated-end
	}

	@Override
	protected int getListViewId() {
		return R.id.vmlistjoueurpanelimpl__list__value;
	}

	@Override
	protected MDKAdapter<Joueur, VMListJoueurPanel, VMListListJoueurPanel> createListAdapter() {
		return new AdapterListJoueurPanelImpl(application.getViewModelCreator().getViewModel(VMListListJoueurPanel.class));
	}

	/**
	 * Callback from adapter event.
	 * @param p_oEvent the event 
	 */
	@ListenerOnBusinessNotification(value = SelectedItemEvent.class, classFilters = { AdapterListJoueurPanelImpl.class })
	public void doOnSelectedItemEvent(SelectedItemEvent p_oEvent) {
		//@non-generated-start[OnSelectedItemEvent][X]
		if (p_oEvent.getSource() == this.getListAdapter()) {
			final Intent oIntent = new Intent(this.getActivity(), DetailJoueurScreen.class);
			oIntent.putExtra("id", p_oEvent.getData());
			oIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			this.getActivity().startActivityForResult(oIntent, DetailJoueurScreen.REQUEST_CODE);
		}
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 */
	@ListenerOnBusinessNotification(value = PerformItemClickEvent.class, classFilters = { AdapterListJoueurPanelImpl.class })
	public void doOnPerformItemClickEvent(final PerformItemClickEvent p_oEvent) {
		//@non-generated-start[OnPerformItemClickEvent][X]
		p_oEvent.getData().performItemClick();
		//@non-generated-end
	}

	/**
	 * Listener on SaveDetailJoueurPanel.ChangeJoueurEvent
	 * @param p_oEvent the event which triggered the callback
	 */
	@ListenerOnBusinessNotification(SaveDetailJoueurPanel.ChangeJoueurEvent.class)
	public void doOnChangeJoueurEvent(SaveDetailJoueurPanel.ChangeJoueurEvent p_oEvent) {
		//@non-generated-start[doOnSaveDetailJoueurPanel.ChangeJoueurEvent][X]
		this.doFillAction();
		//@non-generated-end
	}

	/**
	 * Listener on SaveDetailJoueurPanel.AddJoueurEvent
	 * @param p_oEvent the event which triggered the callback
	 */
	@ListenerOnBusinessNotification(SaveDetailJoueurPanel.AddJoueurEvent.class)
	public void doOnAddJoueurEvent(SaveDetailJoueurPanel.AddJoueurEvent p_oEvent) {
		//@non-generated-start[doOnSaveDetailJoueurPanel.AddJoueurEvent][X]
		this.doFillAction();
		//@non-generated-end
	}

	/**
	 * Listener on DeleteDetailJoueurPanel.DeleteJoueurEvent
	 * @param p_oEvent the event which triggered the callback
	 */
	@ListenerOnBusinessNotification(DeleteDetailJoueurPanel.DeleteJoueurEvent.class)
	public void doOnDeleteJoueurEvent(DeleteDetailJoueurPanel.DeleteJoueurEvent p_oEvent) {
		//@non-generated-start[doOnDeleteDetailJoueurPanel.DeleteJoueurEvent][X]
		this.doFillAction();
		//@non-generated-end
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
