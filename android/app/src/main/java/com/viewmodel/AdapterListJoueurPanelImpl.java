package com.viewmodel;

import com.adeuza.movalysfwk.mobile.mf4android.ui.adapters.MDKAdapter;
import com.adeuza.movalysfwk.mobile.mf4android.ui.modele.ConfigurableListViewHolder;
import com.adeuza.movalysfwk.mobile.mf4android.ui.modele.PerformItemClickEvent;
import com.adeuza.movalysfwk.mobile.mf4android.ui.modele.SelectedItemEvent;
import com.adeuza.movalysfwk.mobile.mf4android.ui.views.MMOnItemClickListener;
import com.adeuza.movalysfwk.mobile.mf4android.ui.views.MMPerformItemClickEventData;
import com.adeuza.movalysfwk.mobile.mf4android.ui.views.MMPerformItemClickListener;
import com.adeuza.movalysfwk.mobile.mf4android.ui.views.MMPerformItemClickView;
import com.adeuza.movalysfwk.mobile.mf4javacommons.event.AbstractBusinessEvent;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.application.Application;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.business.genericdisplay.InDisplayParameter;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ListViewModel;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ViewModel;
import com.adoliveira.gestionclub.R;
import com.model.Joueur;
import com.viewmodel.VMListJoueurPanel;
import com.viewmodel.VMListListJoueurPanel;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;

/**
 * 
 */
public class AdapterListJoueurPanelImpl
//@non-generated-start[class-signature-extends][X]
		extends MDKAdapter<Joueur, VMListJoueurPanel, VMListListJoueurPanel>
//@non-generated-end
		implements MMOnItemClickListener, MMPerformItemClickListener
//@non-generated-start[class-signature]
//@non-generated-end
{
	//@non-generated-start[attributes]
	//@non-generated-end

	/**
	 * Constructor for AdapterListJoueurPanelImpl
	 * @param p_oMasterVM the parent view model

	 */
	public AdapterListJoueurPanelImpl(VMListListJoueurPanel p_oMasterVM) {

		super(p_oMasterVM, R.layout.glistjoueurpanel__listitem1__master, R.id.glistjoueurpanel__listitem1__master);

		//@non-generated-start[constructor]

		//@non-generated-end

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void postInflate(MDKAdapter<Joueur, VMListJoueurPanel, VMListListJoueurPanel> p_oAdapter, View p_oCurrentRow, boolean b_isExpanded) {
		super.postInflate(p_oAdapter, p_oCurrentRow, b_isExpanded);
		//@non-generated-start[postInflate]
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void postBind(MDKAdapter<Joueur, VMListJoueurPanel, VMListListJoueurPanel> p_oAdapter, View p_oCurrentRow, VMListJoueurPanel p_oCurrentVM,
			boolean b_isExpanded, int... p_oPositions) {
		super.postBind(p_oAdapter, p_oCurrentRow, p_oCurrentVM, b_isExpanded, p_oPositions);
		//@non-generated-start[postBind]
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean onPerformItemClick(View p_oView, int p_iPosition, long p_lId, MMPerformItemClickView p_oListView) {
		//@non-generated-start[onPerformItemClick][X]
		Application.getInstance().getController().publishBusinessEvent(
				null,
				new PerformItemClickEvent(this, ((ConfigurableListViewHolder) p_oView.getTag()).getViewModelID(), p_oView, p_iPosition, p_lId,
						p_oListView));
		return false;
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onItemClick(View p_oView, int p_iPosition, long p_iId, MMPerformItemClickView p_oListView, ConfigurableListViewHolder p_oViewHolder) {
		//@non-generated-start[onItemClick][X]
		Application.getInstance().getController().publishBusinessEvent(null, new SelectedItemEvent(this, p_oViewHolder.getViewModelID()));
		//@non-generated-end
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
