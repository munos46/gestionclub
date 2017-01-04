package com.viewmodel;

import java.sql.Date;
import java.util.Set;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.DataLoaderParts;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.services.BeanLoader;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.AbstractItemViewModelId;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.clonable.NonClonableViewModel;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.listener.ListenerOnFieldModified;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ListViewModel;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ListViewModelImpl;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.utils.DateUtils;
import com.model.Entrainement;
import com.model.EntrainementFactory;
import com.model.Lieu;
import com.model.LieuFactory;

/**
 * 
 */
public class VMListEntrainPanelImpl
//@non-generated-start[class-signature-extends][X]
		extends AbstractItemViewModelId<Entrainement>
//@non-generated-end
		implements VMListEntrainPanel
//@non-generated-start[class-signature]
//@non-generated-end
{
	/**
	 * Key used to identify the dateHeure attribute
	 */
	protected static final String KEY_DATEHEURE = "dateHeure";
	/**
	 * Key used to identify the nom attribute
	 */
	protected static final String KEY_NOM = "nom";

	/**
	 * 
	 * 
	 * <p>Attribute </p>
	 * <p> type=Long mandatory=false</p>
	 */
	//@non-generated-start[attribute-dateHeure]
	//@non-generated-end[attribute-dateHeure]
	private Long dateHeure;

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
	private Long heure;
	protected static final String KEY_HEURE = "heure";

	//@non-generated-end

	/**
	 * Default constructor.
	 */
	public VMListEntrainPanelImpl() {
		this.setEditable(false);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Long getDateHeure() {
		return this.dateHeure;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setDateHeure(Long p_lDateHeure) {
		this.affectObjectAndNotify(this.dateHeure, p_lDateHeure, KEY_DATEHEURE);
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
	public void modifyToIdentifiable(Entrainement p_oEntity) {
		//@non-generated-start[modifyToIdentifiable-before][X]
		//@non-generated-end
		if (p_oEntity != null) {
			p_oEntity.setId(this.id_id);
			p_oEntity.setDateHeure(DateUtils.getTime(this.dateHeure));
			{
				Lieu oLieu = p_oEntity.getLieu();
				if (oLieu == null) {
					oLieu = BeanLoader.getInstance().getBean(LieuFactory.class).createInstance();
					p_oEntity.setLieu(oLieu);
				}
				oLieu.setNom(this.nom);

			}
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
	public void updateFromIdentifiable(Entrainement p_oEntity) {
		//@non-generated-start[updateFromIdentifiable-notify-before][X]
		this.setAlwaysNotify(false);
		//@non-generated-end
		//@non-generated-start[updateFromIdentifiable-before][X]
		//@non-generated-end
		this.clear();
		if (p_oEntity != null) {
			this.setId_id(p_oEntity.getId());
			this.setDateHeure(DateUtils.getTime(p_oEntity.getDateHeure()));
			Lieu oLieu1 = p_oEntity.getLieu();
			if (oLieu1 == null) {
				this.clearLieu();
			} else {
				this.setNom(oLieu1.getNom());
			}
			//@non-generated-start[update-from-identifiable]
			this.setHeure(DateUtils.getTime(p_oEntity.getDateHeure()));
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
		this.dateHeure = null;
		this.clearLieu();
		//@non-generated-start[clear-after]
		this.heure = null;

		//@non-generated-end
		super.clear();
	}

	/**
	 * Clear data associated to aLieu.
	 */
	protected void clearLieu() {
		this.nom = null;
	}

	//@non-generated-start[methods]
	public Long getHeure() {
		return heure;
	}

	public void setHeure(Long heure) {
		this.affectObjectAndNotify(this.heure, heure, KEY_HEURE);
	}

	//@non-generated-end
}
