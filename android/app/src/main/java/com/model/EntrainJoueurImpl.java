package com.model;

import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.beans.AbstractEntity;

/**
 * 
 */
public class EntrainJoueurImpl
//@non-generated-start[class-signature-extends][X]
		extends AbstractEntity<EntrainJoueur>
//@non-generated-end
		implements EntrainJoueur
//@non-generated-start[class-signature]
//@non-generated-end
{

	/**
	 * 
	 * 
	 * <p>Attribute JOUEURID</p>
	 * <p> type=long mandatory=true</p>
	 */

	//@non-generated-start[attribute-joueurId]
	//@non-generated-end[attribute-joueurId]
	private long joueurId;

	/**
	 * 
	 * 
	 * <p>Attribute ENTRAINEMENTID</p>
	 * <p> type=long mandatory=true</p>
	 */

	//@non-generated-start[attribute-entrainementId]
	//@non-generated-end[attribute-entrainementId]
	private long entrainementId;

	//@non-generated-start[attributes]
	//@non-generated-end

	/**
	 * Constructor 
	 */
	protected EntrainJoueurImpl() {
		this.joueurId = -1;
		this.entrainementId = -1;

		//@non-generated-start[constructor]
		//@non-generated-end
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @see com.model.EntrainJoueur#getJoueurId()
	 */
	@Override
	public long getJoueurId() {

		return this.joueurId;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.EntrainJoueur#setJoueurId(long)
	 */
	@Override
	public void setJoueurId(long p_lJoueurId) {

		this.joueurId = p_lJoueurId;

		//@non-generated-start[setter-joueurId]
		//@non-generated-end[setter-joueurId]
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @see com.model.EntrainJoueur#getEntrainementId()
	 */
	@Override
	public long getEntrainementId() {

		return this.entrainementId;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.EntrainJoueur#setEntrainementId(long)
	 */
	@Override
	public void setEntrainementId(long p_lEntrainementId) {

		this.entrainementId = p_lEntrainementId;

		//@non-generated-start[setter-entrainementId]
		//@non-generated-end[setter-entrainementId]
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.adeuza.movalysfwk.mf4jcommons.core.beans.MIdentifiable#idToString()
	 */
	@Override
	public String idToString() {
		StringBuilder r_sId = new StringBuilder();
		r_sId.append(String.valueOf(this.joueurId));
		r_sId.append('|');
		r_sId.append(String.valueOf(this.entrainementId));
		return r_sId.toString();
	}

	/**
	 * Renvoie l'identifiant de l'attribut entrainement au format chaîne de caractères.
	 *
	 * @return l'identifiant de l'attribut entrainement au format chaîne de caractères.
	 */
	public String entrainementIdToString() {
		StringBuffer r_sId = new StringBuffer();
		r_sId.append(String.valueOf(this.entrainementId));
		return r_sId.toString();
	}

	/**
	 * Renvoie l'identifiant de l'attribut joueur au format chaîne de caractères.
	 *
	 * @return l'identifiant de l'attribut joueur au format chaîne de caractères.
	 */
	public String joueurIdToString() {
		StringBuffer r_sId = new StringBuffer();
		r_sId.append(String.valueOf(this.joueurId));
		return r_sId.toString();
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
