package com.panel;

import java.util.List;
import com.action.deletedetailentrainpanel.DeleteDetailEntrainPanel;
import com.action.savedetailentrainpanel.SaveDetailEntrainPanel;
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
import com.adoliveira.gestionclub.loader.ListEntrainPanelLoader;
import com.adoliveira.gestionclub.R;
import com.application.DetailEntrainScreen;
import com.model.Entrainement;
import com.viewmodel.AdapterListEntrainPanelImpl;
import com.viewmodel.VMListEntrainPanel;
import com.viewmodel.VMListEntrainScreen;
import com.viewmodel.VMListListEntrainPanel;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 */
public class ListEntrainPanel
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
		p_oRoot.findViewById(R.id.button_create_detailentrainpanel).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//@non-generated-start[doOnFabClick][X]
				Intent oIntent = new Intent(ListEntrainPanel.this.getActivity(), DetailEntrainScreen.class);
				oIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				ListEntrainPanel.this.getActivity().startActivityForResult(oIntent, DetailEntrainScreen.REQUEST_CODE);
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
		return R.layout.glistentrainpanel__screenlist1__master;
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.fragment.AbstractAutoBindMMFragment#getFragmentViewModel()
	 */
	@Override
	protected ViewModel getFragmentViewModel() {

		return application.getViewModelCreator().getViewModel(VMListListEntrainPanel.class);

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
				ListEntrainPanelLoader.class));

		this.launchAction(LoadDataForMultipleDisplayDetailAction.class, oMultipleDisplayParameter);
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
		oActionParameter.addAdapter(this.mAdapter);
		this.launchAction(GenericUpdateVMForDisplayDetailAction.class, oActionParameter);
		//@non-generated-end
	}

	@Override
	protected int getListViewId() {
		return R.id.vmlistentrainpanelimpl__list__value;
	}

	@Override
	protected MDKAdapter<Entrainement, VMListEntrainPanel, VMListListEntrainPanel> createListAdapter() {
		return new AdapterListEntrainPanelImpl(application.getViewModelCreator().getViewModel(VMListListEntrainPanel.class));
	}

	/**
	 * Callback from adapter event.
	 * @param p_oEvent the event 
	 */
	@ListenerOnBusinessNotification(value = SelectedItemEvent.class, classFilters = { AdapterListEntrainPanelImpl.class })
	public void doOnSelectedItemEvent(SelectedItemEvent p_oEvent) {
		//@non-generated-start[OnSelectedItemEvent][X]
		if (p_oEvent.getSource() == this.getListAdapter()) {
			final Intent oIntent = new Intent(this.getActivity(), DetailEntrainScreen.class);
			oIntent.putExtra("id", p_oEvent.getData());
			oIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			this.getActivity().startActivityForResult(oIntent, DetailEntrainScreen.REQUEST_CODE);
		}
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 */
	@ListenerOnBusinessNotification(value = PerformItemClickEvent.class, classFilters = { AdapterListEntrainPanelImpl.class })
	public void doOnPerformItemClickEvent(final PerformItemClickEvent p_oEvent) {
		//@non-generated-start[OnPerformItemClickEvent][X]
		p_oEvent.getData().performItemClick();
		//@non-generated-end
	}

	/**
	 * Listener on SaveDetailEntrainPanel.ChangeEntrainementEvent
	 * @param p_oEvent the event which triggered the callback
	 */
	@ListenerOnBusinessNotification(SaveDetailEntrainPanel.ChangeEntrainementEvent.class)
	public void doOnChangeEntrainementEvent(SaveDetailEntrainPanel.ChangeEntrainementEvent p_oEvent) {
		//@non-generated-start[doOnSaveDetailEntrainPanel.ChangeEntrainementEvent][X]
		this.doFillAction();
		//@non-generated-end
	}

	/**
	 * Listener on SaveDetailEntrainPanel.AddEntrainementEvent
	 * @param p_oEvent the event which triggered the callback
	 */
	@ListenerOnBusinessNotification(SaveDetailEntrainPanel.AddEntrainementEvent.class)
	public void doOnAddEntrainementEvent(SaveDetailEntrainPanel.AddEntrainementEvent p_oEvent) {
		//@non-generated-start[doOnSaveDetailEntrainPanel.AddEntrainementEvent][X]
		this.doFillAction();
		//@non-generated-end
	}

	/**
	 * Listener on DeleteDetailEntrainPanel.DeleteEntrainementEvent
	 * @param p_oEvent the event which triggered the callback
	 */
	@ListenerOnBusinessNotification(DeleteDetailEntrainPanel.DeleteEntrainementEvent.class)
	public void doOnDeleteEntrainementEvent(DeleteDetailEntrainPanel.DeleteEntrainementEvent p_oEvent) {
		//@non-generated-start[doOnDeleteDetailEntrainPanel.DeleteEntrainementEvent][X]
		this.doFillAction();
		//@non-generated-end
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
