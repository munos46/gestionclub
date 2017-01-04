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
