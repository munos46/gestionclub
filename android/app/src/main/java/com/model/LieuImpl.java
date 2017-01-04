package com.model;

import com.adeuza.movalysfwk.mobile.mf4mjcommons.business.doopenmap.AddressLocation;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.beans.AbstractEntity;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.rest.json.gson.Unexposed;

/**
 * 
 */
public class LieuImpl
//@non-generated-start[class-signature-extends][X]
		extends AbstractEntity<Lieu>
//@non-generated-end
		implements Lieu
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
	 * <p>Attribute </p>
	 * <p> type=AddressLocation mandatory=true</p>
	 */

	//@non-generated-start[attribute-position]
	//@non-generated-end[attribute-position]
	private AddressLocation position;

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

	//@non-generated-start[attributes]
	//@non-generated-end

	/**
	 * Constructor 
	 */
	protected LieuImpl() {
		this.id = -1;
		this.nom = null;
		this.position = new AddressLocation();
		this.position.setLatitude(Double.valueOf(0));
		this.position.setLongitude(Double.valueOf(0));
		this.position.setCompl(null);
		this.position.setStreet(null);
		this.position.setCity(null);
		this.position.setCountry(null);
		this.oldId = 0;

		//@non-generated-start[constructor]
		//@non-generated-end
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @see com.model.Lieu#getId()
	 */
	@Override
	public long getId() {

		return this.id;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.Lieu#setId(long)
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
	 * @see com.model.Lieu#getNom()
	 */
	@Override
	public String getNom() {

		return this.nom;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.Lieu#setNom(java.lang.String)
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
	 * @see com.model.Lieu#getPosition()
	 */
	@Override
	public AddressLocation getPosition() {

		return this.position;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.Lieu#setPosition(com.adeuza.movalysfwk.mobile.mf4mjcommons.business.doopenmap.AddressLocation)
	 */
	@Override
	public void setPosition(AddressLocation p_oPosition) {

		this.position = p_oPosition;

		//@non-generated-start[setter-position]
		//@non-generated-end[setter-position]
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @see com.model.Lieu#getOldId()
	 */
	@Override
	public long getOldId() {

		return this.oldId;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.model.Lieu#setOldId(long)
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
