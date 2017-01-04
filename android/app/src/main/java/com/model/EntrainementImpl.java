package com.model;

import java.sql.Timestamp;
import java.util.List;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.beans.AbstractEntity;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.rest.json.gson.Unexposed;

/**
 * 
 */
public class EntrainementImpl
//@non-generated-start[class-signature-extends][X]
		extends AbstractEntity<Entrainement>
//@non-generated-end
		implements Entrainement
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
	 * <p>Attribute DATEHEURE</p>
	 * <p> type=Timestamp mandatory=true</p>
	 */

	//@non-generated-start[attribute-dateHeure]
	//@non-generated-end[attribute-dateHeure]
	private Timestamp dateHeure;

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
	 * <p>Attribute </p>
	 * <p> type=long mandatory=false</p>
	 */

	//@non-generated-start[attribute-lieuId]
	//@non-generated-end[attribute-lieuId]
	private long lieuId;

	/**
	 * 
	 * 
	 * <p>Cascade LIEU</p>
	 * <p>Relation ManyToOne</p><p> targetEntity=Lieu mandatory=true relationOwner=true transient=false</p>
	 */

	//@non-generated-start[attribute-lieu]
	//@non-generated-end[attribute-lieu]
	@Unexposed
	private Lieu lieu;

	/**
	 * 
	 * 
	 * <p>Cascade JOUEURS</p>
	 * <p>Relation ManyToMany</p><p> targetEntity=Joueur relationOwner=false transient=false</p>
	 */

	//@non-generated-start[attribute-joueurs]
	//@non-generated-end[attribute-joueurs]
	private List<Joueur> joueurs;

	//@non-generated-start[attributes]
	//@non-generated-end

	/**
	 * Constructor 
	 */
	protected EntrainementImpl() {
		this.id = -1;
		this.dateHeure = null;
		this.oldId = 0;
		this.lieuId = 0;
		this.lieu = null;
		this.joueurs = null;

		//@non-generated-start[constructor]
		//@non-generated-end
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @see com.model.Entrainement#getId()
	 */
	@Override
	public long getId() {

		return this.id;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.Entrainement#setId(long)
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
	 * @see com.model.Entrainement#getDateHeure()
	 */
	@Override
	public Timestamp getDateHeure() {

		return this.dateHeure;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.Entrainement#setDateHeure(java.sql.Timestamp)
	 */
	@Override
	public void setDateHeure(Timestamp p_oDateHeure) {

		this.dateHeure = p_oDateHeure;

		//@non-generated-start[setter-dateHeure]
		//@non-generated-end[setter-dateHeure]
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @see com.model.Entrainement#getOldId()
	 */
	@Override
	public long getOldId() {

		return this.oldId;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.Entrainement#setOldId(long)
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
	 * @see com.model.Entrainement#getLieuId()
	 */
	@Override
	public long getLieuId() {

		return this.lieuId;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.Entrainement#setLieuId(long)
	 */
	@Override
	public void setLieuId(long p_lLieuId) {

		this.lieuId = p_lLieuId;

		//@non-generated-start[setter-lieuId]
		//@non-generated-end[setter-lieuId]
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @see com.model.Entrainement#getLieu()
	 */
	@Override
	public Lieu getLieu() {

		return this.lieu;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.Entrainement#setLieu(com.model.Lieu)
	 */
	@Override
	public void setLieu(Lieu p_oLieu) {

		this.lieu = p_oLieu;

		if (this.lieu != null) {
			this.lieuId = this.lieu.getId();
		} else {
			this.lieuId = 0;
		}

		//@non-generated-start[setter-lieu]
		//@non-generated-end[setter-lieu]
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @see com.model.Entrainement#getJoueurs()
	 */
	@Override
	public List<Joueur> getJoueurs() {

		return this.joueurs;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.Entrainement#setJoueurs(java.util.List)
	 */
	@Override
	public void setJoueurs(List<Joueur> p_listJoueurs) {

		this.joueurs = p_listJoueurs;
		//@non-generated-start[setter-joueurs]
		//@non-generated-end[setter-joueurs]
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
