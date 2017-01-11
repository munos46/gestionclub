package com.model;

import java.sql.Timestamp;
import java.util.List;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.beans.AbstractEntity;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.model.MPhoto;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.model.MPhotoState;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.rest.json.gson.Unexposed;

/**
 * 
 */
public class JoueurImpl
//@non-generated-start[class-signature-extends][X]
		extends AbstractEntity<Joueur>
//@non-generated-end
		implements Joueur
//@non-generated-start[class-signature]
//@non-generated-end
{

	/**
	 * 
	 * 
	 * <p>Attribute ID</p>
	 * <p> type=long mandatory=true</p>
	 */

	//@non-generated-start[attribute-id]
	//@non-generated-end[attribute-id]
	private long id;

	/**
	 * 
	 * 
	 * <p>Attribute NOM</p>
	 * <p> type=String mandatory=true</p>
	 */

	//@non-generated-start[attribute-nom]
	//@non-generated-end[attribute-nom]
	private String nom;

	/**
	 * 
	 * 
	 * <p>Attribute PRENOM</p>
	 * <p> type=String mandatory=true</p>
	 */

	//@non-generated-start[attribute-prenom]
	//@non-generated-end[attribute-prenom]
	private String prenom;

	/**
	 * 
	 * 
	 * <p>Attribute DATENAISSANCE</p>
	 * <p> type=Timestamp mandatory=true</p>
	 */

	//@non-generated-start[attribute-dateNaissance]
	//@non-generated-end[attribute-dateNaissance]
	private Timestamp dateNaissance;

	/**
	 * 
	 * 
	 * <p>Attribute </p>
	 * <p> type=MPhoto mandatory=false</p>
	 */

	//@non-generated-start[attribute-photo]
	//@non-generated-end[attribute-photo]
	private MPhoto photo;

	/**
	 * 
	 * 
	 * <p>Attribute EMAIL</p>
	 * <p> type=String mandatory=false</p>
	 */

	//@non-generated-start[attribute-email]
	//@non-generated-end[attribute-email]
	private String email;

	/**
	 * 
	 * 
	 * <p>Attribute TAILLE</p>
	 * <p> type=double mandatory=false</p>
	 */

	//@non-generated-start[attribute-taille]
	//@non-generated-end[attribute-taille]
	private double taille;

	/**
	 * 
	 * 
	 * <p>Attribute POIDS</p>
	 * <p> type=double mandatory=false</p>
	 */

	//@non-generated-start[attribute-poids]
	//@non-generated-end[attribute-poids]
	private double poids;

	/**
	 * 
	 * 
	 * <p>Attribute ADRESSE</p>
	 * <p> type=String mandatory=false</p>
	 */

	//@non-generated-start[attribute-adresse]
	//@non-generated-end[attribute-adresse]
	private String adresse;

	/**
	 * 
	 * 
	 * <p>Attribute COMMUNE</p>
	 * <p> type=String mandatory=false</p>
	 */

	//@non-generated-start[attribute-commune]
	//@non-generated-end[attribute-commune]
	private String commune;

	/**
	 * 
	 * 
	 * <p>Attribute VILLE</p>
	 * <p> type=String mandatory=false</p>
	 */

	//@non-generated-start[attribute-ville]
	//@non-generated-end[attribute-ville]
	private String ville;

	/**
	 * 
	 * 
	 * <p>Attribute </p>
	 * <p> type=long mandatory=false</p>
	 */

	//@non-generated-start[attribute-oldId]
	//@non-generated-end[attribute-oldId]
	@Unexposed
	private long oldId;

	/**
	 * 
	 * 
	 * <p>Cascade ENTRAINEMENTS</p>
	 * <p>Relation ManyToMany</p><p> targetEntity=Entrainement relationOwner=true transient=false</p>
	 */

	//@non-generated-start[attribute-entrainements]
	//@non-generated-end[attribute-entrainements]
	private List<Entrainement> entrainements;

	//@non-generated-start[attributes]
	//@non-generated-end

	/**
	 * Constructor 
	 */
	protected JoueurImpl() {
		this.id = -1;
		this.nom = null;
		this.prenom = null;
		this.dateNaissance = null;
		this.photo = new MPhoto();
		this.photo.setName(null);
		this.photo.setUri(null);
		this.photo.setSvg(null);
		this.photo.setDate(null);
		this.photo.setDesc(null);
		this.photo.setPhotoState(MPhotoState.FWK_NONE);
		this.email = null;
		this.taille = -1;
		this.poids = -1;
		this.adresse = null;
		this.commune = null;
		this.ville = null;
		this.oldId = 0;
		this.entrainements = null;

		//@non-generated-start[constructor]
		//@non-generated-end
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#getId()
	 */
	@Override
	public long getId() {

		return this.id;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#setId(long)
	 */
	@Override
	public void setId(long p_lId) {

		this.id = p_lId;

		//@non-generated-start[setter-id]
		//@non-generated-end[setter-id]
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#getNom()
	 */
	@Override
	public String getNom() {

		return this.nom;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#setNom(java.lang.String)
	 */
	@Override
	public void setNom(String p_sNom) {

		this.nom = p_sNom;

		//@non-generated-start[setter-nom]
		//@non-generated-end[setter-nom]
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#getPrenom()
	 */
	@Override
	public String getPrenom() {

		return this.prenom;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#setPrenom(java.lang.String)
	 */
	@Override
	public void setPrenom(String p_sPrenom) {

		this.prenom = p_sPrenom;

		//@non-generated-start[setter-prenom]
		//@non-generated-end[setter-prenom]
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#getDateNaissance()
	 */
	@Override
	public Timestamp getDateNaissance() {

		return this.dateNaissance;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#setDateNaissance(java.sql.Timestamp)
	 */
	@Override
	public void setDateNaissance(Timestamp p_oDateNaissance) {

		this.dateNaissance = p_oDateNaissance;

		//@non-generated-start[setter-dateNaissance]
		//@non-generated-end[setter-dateNaissance]
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#getPhoto()
	 */
	@Override
	public MPhoto getPhoto() {

		return this.photo;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#setPhoto(com.adeuza.movalysfwk.mobile.mf4mjcommons.model.MPhoto)
	 */
	@Override
	public void setPhoto(MPhoto p_oPhoto) {

		this.photo = p_oPhoto;

		//@non-generated-start[setter-photo]
		//@non-generated-end[setter-photo]
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#getEmail()
	 */
	@Override
	public String getEmail() {

		return this.email;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#setEmail(java.lang.String)
	 */
	@Override
	public void setEmail(String p_sEmail) {

		this.email = p_sEmail;

		//@non-generated-start[setter-email]
		//@non-generated-end[setter-email]
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#getTaille()
	 */
	@Override
	public double getTaille() {

		return this.taille;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#setTaille(double)
	 */
	@Override
	public void setTaille(double p_dTaille) {

		this.taille = p_dTaille;

		//@non-generated-start[setter-taille]
		//@non-generated-end[setter-taille]
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#getPoids()
	 */
	@Override
	public double getPoids() {

		return this.poids;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#setPoids(double)
	 */
	@Override
	public void setPoids(double p_dPoids) {

		this.poids = p_dPoids;

		//@non-generated-start[setter-poids]
		//@non-generated-end[setter-poids]
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#getAdresse()
	 */
	@Override
	public String getAdresse() {

		return this.adresse;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#setAdresse(java.lang.String)
	 */
	@Override
	public void setAdresse(String p_sAdresse) {

		this.adresse = p_sAdresse;

		//@non-generated-start[setter-adresse]
		//@non-generated-end[setter-adresse]
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#getCommune()
	 */
	@Override
	public String getCommune() {

		return this.commune;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#setCommune(java.lang.String)
	 */
	@Override
	public void setCommune(String p_sCommune) {

		this.commune = p_sCommune;

		//@non-generated-start[setter-commune]
		//@non-generated-end[setter-commune]
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#getVille()
	 */
	@Override
	public String getVille() {

		return this.ville;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#setVille(java.lang.String)
	 */
	@Override
	public void setVille(String p_sVille) {

		this.ville = p_sVille;

		//@non-generated-start[setter-ville]
		//@non-generated-end[setter-ville]
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#getOldId()
	 */
	@Override
	public long getOldId() {

		return this.oldId;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#setOldId(long)
	 */
	@Override
	public void setOldId(long p_lOldId) {

		this.oldId = p_lOldId;

		//@non-generated-start[setter-oldId]
		//@non-generated-end[setter-oldId]
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#getEntrainements()
	 */
	@Override
	public List<Entrainement> getEntrainements() {

		return this.entrainements;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.Joueur#setEntrainements(java.util.List)
	 */
	@Override
	public void setEntrainements(List<Entrainement> p_listEntrainements) {

		this.entrainements = p_listEntrainements;
		//@non-generated-start[setter-entrainements]
		//@non-generated-end[setter-entrainements]
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.adeuza.movalysfwk.mf4jcommons.core.beans.MIdentifiable#idToString()
	 */
	@Override
	public String idToString() {
		StringBuilder r_sId = new StringBuilder();
		r_sId.append(String.valueOf(this.id));
		return r_sId.toString();
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
