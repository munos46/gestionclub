package com.viewmodel;

import java.sql.Date;
import java.util.Set;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.Dataloader;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.DataLoaderParts;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.services.BeanLoader;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.services.ExtBeanType;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.model.MPhoto;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.model.MPhotoHelper;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.AbstractItemViewModelId;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.listener.ListenerOnFieldModified;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.MPhotoVO;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.utils.DateUtils;
import com.adoliveira.gestionclub.loader.DetailJoueurPanelLoader;
import com.adoliveira.gestionclub.viewmodel.ViewModelCreator;
import com.model.Joueur;
import com.model.JoueurFactory;

/**
 * 
 */
public class VMDetailJoueurPanelImpl
//@non-generated-start[class-signature-extends][X]
		extends AbstractItemViewModelId<Joueur>
//@non-generated-end
		implements VMDetailJoueurPanel
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
	 * Key used to identify the dateNaissance attribute
	 */
	protected static final String KEY_DATENAISSANCE = "dateNaissance";
	/**
	 * Key used to identify the photo attribute
	 */
	protected static final String KEY_PHOTO = "photo";
	/**
	 * Key used to identify the email attribute
	 */
	protected static final String KEY_EMAIL = "email";
	/**
	 * Key used to identify the taille attribute
	 */
	protected static final String KEY_TAILLE = "taille";
	/**
	 * Key used to identify the poids attribute
	 */
	protected static final String KEY_POIDS = "poids";
	/**
	 * Key used to identify the adresse attribute
	 */
	protected static final String KEY_ADRESSE = "adresse";
	/**
	 * Key used to identify the commune attribute
	 */
	protected static final String KEY_COMMUNE = "commune";
	/**
	 * Key used to identify the ville attribute
	 */
	protected static final String KEY_VILLE = "ville";

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

	/**
	 * 
	 * 
	 * <p>Attribute </p>
	 * <p> type=Date mandatory=false</p>
	 */
	//@non-generated-start[attribute-dateNaissance]
	//@non-generated-end[attribute-dateNaissance]
	private Date dateNaissance;

	/**
	 * 
	 * 
	 * <p>Attribute </p>
	 * <p> type=MPhotoVO mandatory=false</p>
	 */
	//@non-generated-start[attribute-photo]
	//@non-generated-end[attribute-photo]
	private MPhotoVO photo;

	/**
	 * 
	 * 
	 * <p>Attribute </p>
	 * <p> type=String mandatory=false</p>
	 */
	//@non-generated-start[attribute-email]
	//@non-generated-end[attribute-email]
	private String email;

	/**
	 * 
	 * 
	 * <p>Attribute </p>
	 * <p> type=double mandatory=false</p>
	 */
	//@non-generated-start[attribute-taille]
	//@non-generated-end[attribute-taille]
	private double taille;

	/**
	 * 
	 * 
	 * <p>Attribute </p>
	 * <p> type=double mandatory=false</p>
	 */
	//@non-generated-start[attribute-poids]
	//@non-generated-end[attribute-poids]
	private double poids;

	/**
	 * 
	 * 
	 * <p>Attribute </p>
	 * <p> type=String mandatory=false</p>
	 */
	//@non-generated-start[attribute-adresse]
	//@non-generated-end[attribute-adresse]
	private String adresse;

	/**
	 * 
	 * 
	 * <p>Attribute </p>
	 * <p> type=String mandatory=false</p>
	 */
	//@non-generated-start[attribute-commune]
	//@non-generated-end[attribute-commune]
	private String commune;

	/**
	 * 
	 * 
	 * <p>Attribute </p>
	 * <p> type=String mandatory=false</p>
	 */
	//@non-generated-start[attribute-ville]
	//@non-generated-end[attribute-ville]
	private String ville;

	//@non-generated-start[attributes]
	//@non-generated-end

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
	 */
	@Override
	public Date getDateNaissance() {
		return this.dateNaissance;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setDateNaissance(Date p_oDateNaissance) {
		this.affectObjectAndNotify(this.dateNaissance, p_oDateNaissance, KEY_DATENAISSANCE);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public MPhotoVO getPhoto() {
		return this.photo;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setPhoto(MPhotoVO p_oPhoto) {
		this.affectObjectAndNotify(this.photo, p_oPhoto, KEY_PHOTO);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public String getEmail() {
		return this.email;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setEmail(String p_sEmail) {
		this.affectObjectAndNotify(this.email, p_sEmail, KEY_EMAIL);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public double getTaille() {
		return this.taille;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setTaille(double p_dTaille) {
		this.affectDoubleAndNotify(this.taille, p_dTaille, KEY_TAILLE);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public double getPoids() {
		return this.poids;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setPoids(double p_dPoids) {
		this.affectDoubleAndNotify(this.poids, p_dPoids, KEY_POIDS);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public String getAdresse() {
		return this.adresse;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setAdresse(String p_sAdresse) {
		this.affectObjectAndNotify(this.adresse, p_sAdresse, KEY_ADRESSE);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public String getCommune() {
		return this.commune;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setCommune(String p_sCommune) {
		this.affectObjectAndNotify(this.commune, p_sCommune, KEY_COMMUNE);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public String getVille() {
		return this.ville;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setVille(String p_sVille) {
		this.affectObjectAndNotify(this.ville, p_sVille, KEY_VILLE);
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
			p_oEntity.setDateNaissance(DateUtils.getTime(this.dateNaissance));
			p_oEntity.setPhoto(MPhotoHelper.toComponent(this.photo));
			p_oEntity.setEmail(this.email);
			p_oEntity.setTaille(this.taille);
			p_oEntity.setPoids(this.poids);
			p_oEntity.setAdresse(this.adresse);
			p_oEntity.setCommune(this.commune);
			p_oEntity.setVille(this.ville);
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
			this.setDateNaissance(DateUtils.getDate(p_oEntity.getDateNaissance()));
			this.setPhoto(MPhotoHelper.toViewModel(p_oEntity.getPhoto()));
			this.setEmail(p_oEntity.getEmail());
			this.setTaille(p_oEntity.getTaille());
			this.setPoids(p_oEntity.getPoids());
			this.setAdresse(p_oEntity.getAdresse());
			this.setCommune(p_oEntity.getCommune());
			this.setVille(p_oEntity.getVille());

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
	 * Updates the viewmodel using a DetailJoueurPanelLoader.
	 * @param p_oDataloader
	 * 			The dataloader.
	 */
	@Override
	public void updateFromDataloader(final Dataloader<?> p_oDataloader) {
		//@non-generated-start[updateFromDataLoader-method][X]
		final ViewModelCreator oVMCreator = (ViewModelCreator) BeanLoader.getInstance().getBean(ExtBeanType.ViewModelCreator);
		if (p_oDataloader == null) {
			this.updateFromIdentifiable(null);
		} else if (DetailJoueurPanelLoader.class.isAssignableFrom(p_oDataloader.getClass())) {
			final DetailJoueurPanelLoader oDataloader = (DetailJoueurPanelLoader) p_oDataloader;
			Joueur data = oDataloader.getData(this.getKey());
			if (data == null) {
				data = BeanLoader.getInstance().getBean(JoueurFactory.class).createInstance();
			}

			Set<DataLoaderParts> reload = oDataloader.popReload(this.getKey());
			oVMCreator.createOrUpdateVMDetailJoueurPanel(data, reload.contains(DetailJoueurPanelLoader.DataLoaderPartEnum.DATA));
		}
		//@non-generated-end
	}

	/**
	 * Clear this view model.
	 */
	public void clear() {
		this.id_id = -1;
		this.nom = null;
		this.prenom = null;
		this.dateNaissance = null;
		this.photo = new MPhotoVO();
		this.email = null;
		this.taille = -1;
		this.poids = -1;
		this.adresse = null;
		this.commune = null;
		this.ville = null;
		//@non-generated-start[clear-after]

		//@non-generated-end
		super.clear();
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
