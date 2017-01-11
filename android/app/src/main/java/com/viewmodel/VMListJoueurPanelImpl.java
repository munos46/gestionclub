package com.viewmodel;

import java.util.Set;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.DataLoaderParts;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.AbstractItemViewModelId;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.listener.ListenerOnFieldModified;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ListViewModel;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ListViewModelImpl;
import com.model.Joueur;
import com.model.JoueurFactory;

/**
 * 
 */
public class VMListJoueurPanelImpl
//@non-generated-start[class-signature-extends][X]
		extends AbstractItemViewModelId<Joueur>
//@non-generated-end
		implements VMListJoueurPanel
//@non-generated-start[class-signature]
//@non-generated-end
{
	/**
	 * Key used to identify the nom attribute
	 */
	protected static final String KEY_NOM = "nom";
	/**
	 * Key used to identify the prenom attribute
	 */
	protected static final String KEY_PRENOM = "prenom";

	/**
	 * 
	 * 
	 * <p>Attribute </p>
	 * <p> type=String mandatory=false</p>
	 */
	//@non-generated-start[attribute-nom]
	//@non-generated-end[attribute-nom]
	private String nom;

	/**
	 * 
	 * 
	 * <p>Attribute </p>
	 * <p> type=String mandatory=false</p>
	 */
	//@non-generated-start[attribute-prenom]
	//@non-generated-end[attribute-prenom]
	private String prenom;

	//@non-generated-start[attributes]
	//@non-generated-end

	/**
	 * Default constructor.
	 */
	public VMListJoueurPanelImpl() {
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
	 */
	@Override
	public String getPrenom() {
		return this.prenom;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setPrenom(String p_sPrenom) {
		this.affectObjectAndNotify(this.prenom, p_sPrenom, KEY_PRENOM);
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ItemViewModel#modifyToIdentifiable(com.adeuza.movalysfwk.mf4jcommons.core.beans.MIdentifiable)
	 */
	public void modifyToIdentifiable(Joueur p_oEntity) {
		//@non-generated-start[modifyToIdentifiable-before][X]
		//@non-generated-end
		if (p_oEntity != null) {
			p_oEntity.setId(this.id_id);
			p_oEntity.setNom(this.nom);
			p_oEntity.setPrenom(this.prenom);
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
	public void updateFromIdentifiable(Joueur p_oEntity) {
		//@non-generated-start[updateFromIdentifiable-notify-before][X]
		this.setAlwaysNotify(false);
		//@non-generated-end
		//@non-generated-start[updateFromIdentifiable-before][X]
		//@non-generated-end
		this.clear();
		if (p_oEntity != null) {
			this.setId_id(p_oEntity.getId());
			this.setNom(p_oEntity.getNom());
			this.setPrenom(p_oEntity.getPrenom());

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
		this.prenom = null;
		//@non-generated-start[clear-after]

		//@non-generated-end
		super.clear();
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
