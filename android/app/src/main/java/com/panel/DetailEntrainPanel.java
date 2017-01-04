package com.panel;

import java.util.ArrayList;
import java.util.List;
import com.action.deletedetailentrainpanel.DeleteDetailEntrainPanel;
import com.action.savedetailentrainpanel.SaveDetailEntrainPanel;
import com.adapter.ListeJoueurAdapter;
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
import com.adeuza.movalysfwk.mobile.mf4android.ui.adapters.connectors.MDKViewConnectorWrapper;
import com.adeuza.movalysfwk.mobile.mf4android.ui.adapters.MDKBaseAdapter;
import com.adeuza.movalysfwk.mobile.mf4android.ui.adapters.MDKSpinnerAdapter;
import com.adeuza.movalysfwk.mobile.mf4android.ui.component.configurable.WidgetWrapperHelper;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.Dataloader;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.listener.ListenerOnDataLoaderReload;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.listener.ListenerOnDataLoaderReloadEvent;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.action.EntityActionParameterImpl;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.actiontask.listener.ListenerOnActionSuccess;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.actiontask.listener.ListenerOnActionSuccessEvent;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.business.genericdisplay.InDisplayParameter;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ListViewModel;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ViewModel;
import com.adoliveira.gestionclub.loader.DetailEntrainPanelLoader;
import com.adoliveira.gestionclub.R;
import com.adoliveira.gestionclub.loader.DetailEntrainPanelLoaderImpl;
import com.model.Entrainement;
import com.model.EntrainementImpl;
import com.model.Joueur;
import com.model.Lieu;
import com.soprasteria.movalysmdk.widget.spinner.MDKRichSpinner;
import com.viewmodel.VMDetailEntrainPanel;
import com.viewmodel.VMDetailEntrainPanelLieu;
import com.viewmodel.VMDetailEntrainScreen;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import fr.ganfra.materialspinner.MaterialSpinner;

/**
 * 
 */
public class DetailEntrainPanel
//@non-generated-start[class-signature-extends][X]
		extends AbstractAutoBindMMFragment
//@non-generated-end

//@non-generated-start[class-signature]
//@non-generated-end
{
	//@non-generated-start[declare-spinner-adapter1][X]
	/**
	 * Adapter associated to the spinner of Lieu.
	 */
	private MDKSpinnerAdapter<Lieu, VMDetailEntrainPanelLieu, ListViewModel<Lieu, VMDetailEntrainPanelLieu>> spinnerAdapter1 = null;

	//@non-generated-end
	//@non-generated-start[attributes]
	ArrayList<Joueur> listeJoueur;
	private ViewGroup aView;
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
		//@non-generated-start[set-spinner-adapter1][X]
		//Spinner of Lieu.
//		MDKRichSpinner oSpinner1 = null;
//		oSpinner1 = (MDKRichSpinner) p_oRoot.findViewById(R.id.detail__VMDetailEntrainPanelLieu__edit);
//		if (oSpinner1 != null) {
//			this.spinnerAdapter1 = new MDKSpinnerAdapter(application.getViewModelCreator().getViewModel(VMDetailEntrainPanel.class)
//					.getLstVMDetailEntrainPanelLieu(), R.layout.gdetailentrainpanel__spinselvmdetailentrainpanellieu__master,
//					R.id.seldetail__nom__value, R.layout.gdetailentrainpanel__spinitemvmdetailentrainpanellieu__master, R.id.lstdetail__nom__value,
//					R.id.lstdetail__nom__value, R.id.seldetail__nom__value);
//			MDKViewConnectorWrapper mConnectorWrapper = WidgetWrapperHelper.getInstance().getConnectorWrapper(oSpinner1.getClass());
//			mConnectorWrapper.configure((MDKBaseAdapter) this.spinnerAdapter1, oSpinner1);
//		}
		String[] ITEMS = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, ITEMS);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		MaterialSpinner spinner = (MaterialSpinner) p_oRoot.getRootView().findViewById(R.id.detail__VMDetailEntrainPanelLieu__edit);
		spinner.setAdapter(adapter);
		aView = p_oRoot;
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
		return R.layout.gdetailentrainpanel__screendetail__master;
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.fragment.AbstractAutoBindMMFragment#getFragmentViewModel()
	 */
	@Override
	protected ViewModel getFragmentViewModel() {

		return application.getViewModelCreator().getViewModel(VMDetailEntrainPanel.class);

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
				DetailEntrainPanelLoader.class));

		this.launchAction(LoadDataForMultipleDisplayDetailAction.class, oMultipleDisplayParameter);
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

		EntrainementImpl entrainement = (EntrainementImpl) p_oEvent.getDataLoader().getData(Dataloader.DEFAULT_KEY);

		this.listeJoueur = new ArrayList<>();
		this.listeJoueur.addAll(entrainement.getJoueurs());

		//create an ArrayAdaptar from the String Array
		ListeJoueurAdapter adapter = new ListeJoueurAdapter(getContext(),
				R.layout.detailentrainpanel_listejoueur, this.listeJoueur);

		ListView listView = (ListView) aView.findViewById(R.id.detailListJoueur);

		// Assign adapter to ListView
		listView.setAdapter(adapter);

//		listView.setOnItemClickListener(new OnItemClickListener() {
//			public void onItemClick(AdapterView<?> parent, View view,
//									int position, long id) {
//				// When clicked, show a toast with the TextView text
//				Country country = (Country) parent.getItemAtPosition(position);
//				Toast.makeText(getApplicationContext(),
//						"Clicked on Row: " + country.getName(),
//						Toast.LENGTH_LONG).show();
//			}
//		});


		//@non-generated-end
	}

	//@non-generated-start[get-spinner-adapter1-method][X]
	/**
	 * Return the adapter for the spinner 1
	 * @return the adapter for the spinner 1
	 */
	public MDKSpinnerAdapter<Lieu, VMDetailEntrainPanelLieu, ListViewModel<Lieu, VMDetailEntrainPanelLieu>> getSpinnerAdapter1() {
		return this.spinnerAdapter1;
	}
	//@non-generated-end

	//@non-generated-start[methods]
	//@non-generated-end
}
