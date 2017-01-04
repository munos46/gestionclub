package com.viewmodel;

import java.util.Set;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.DataLoaderParts;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.AbstractItemViewModelId;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.listener.ListenerOnFieldModified;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ListViewModel;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ListViewModelImpl;
import com.model.Lieu;
import com.model.LieuFactory;

/**
 * 
 */
public class VMListEntrainPanelLieuImpl
//@non-generated-start[class-signature-extends][X]
		extends AbstractItemViewModelId<Lieu>
//@non-generated-end
		implements VMListEntrainPanelLieu
//@non-generated-start[class-signature]
//@non-generated-end
{
	/**
	 * Key used to identify the nom attribute
	 */
	protected static final String KEY_NOM = "nom";

	/**
	 * 
	 * 
	 * <p>Attribute </p>
	 * <p> type=String mandatory=false</p>
	 */
	//@non-generated-start[attribute-nom]
	//@non-generated-end[attribute-nom]
	private String nom;

	//@non-generated-start[attributes]
	//@non-generated-end

	/**
	 * Default constructor.
	 */
	public VMListEntrainPanelLieuImpl() {
		this.setEditable(false);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public String getNom() {
		return this.nom;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setNom(String p_sNom) {
		this.affectObjectAndNotify(this.nom, p_sNom, KEY_NOM);
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ItemViewModel#modifyToIdentifiable(com.adeuza.movalysfwk.mf4jcommons.core.beans.MIdentifiable)
	 */
	public void modifyToIdentifiable(Lieu p_oEntity) {
		//@non-generated-start[modifyToIdentifiable-before][X]
		//@non-generated-end
		if (p_oEntity != null) {
			p_oEntity.setId(this.id_id);
			//@non-generated-start[modify-to-identifiable][X]
			//@non-generated-end
		}
		//@non-generated-start[modifyToIdentifiable-after][X]
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.AbstractItemViewModel#updateFromIdentifiable(com.adeuza.movalysfwk.mf4jcommons.core.beans.MIdentifiable)
	 */
	@Override
	public void updateFromIdentifiable(Lieu p_oEntity) {
		//@non-generated-start[updateFromIdentifiable-notify-before][X]
		this.setAlwaysNotify(false);
		//@non-generated-end
		//@non-generated-start[updateFromIdentifiable-before][X]
		//@non-generated-end
		this.clear();
		if (p_oEntity != null) {
			this.setId_id(p_oEntity.getId());
			this.setNom(p_oEntity.getNom());

			//@non-generated-start[update-from-identifiable][X]
			//@non-generated-end
		}
		//@non-generated-start[updateFromIdentifiable-after][X]
		//@non-generated-end
		this.computeEditableFlag();
		//@non-generated-start[updateFromIdentifiable-notify-after][X]
		this.setAlwaysNotify(true);
		this.doOnDataLoaded(null);
		//@non-generated-end
	}

	/**
	 * Clear this view model.
	 */
	public void clear() {
		this.id_id = -1;
		this.nom = null;
		//@non-generated-start[clear-after]

		//@non-generated-end
		super.clear();
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
