package com.model;

import com.adeuza.movalysfwk.mf4jcommons.core.beans.MEntity;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.business.doopenmap.AddressLocation;

/**
 * 
 * <p>Interface : Lieu</p>
 *
 * 
 * 
 * 
 */
public interface Lieu extends MEntity
//@non-generated-start[class-signature]
//@non-generated-end
{

	/**
	 * Constante indiquant le nom de l'entité
	 */
	public static final String ENTITY_NAME = "Lieu";

	//@non-generated-start[constants]
	//@non-generated-end

	/**
	 * 
	 * 
	 * <p>Attribute ID</p>
	 * <p> type=long mandatory=true</p>

	 * 
	 * @return une entité long correspondant à la valeur de l'attribut id
	 */
	public long getId();

	/**
	 * 
	 *
	 * <p>Attribute ID</p>
	 * <p> type=long mandatory=true</p>
	 *
	 * @param p_lId la valeur à affecter à l'attribut id
	 *
	 */
	public void setId(long p_lId);

	/**
	 * 
	 * 
	 * <p>Attribute NOM</p>
	 * <p> type=String mandatory=true</p>

	 * 
	 * @return une entité String correspondant à la valeur de l'attribut nom
	 */
	public String getNom();

	/**
	 * 
	 *
	 * <p>Attribute NOM</p>
	 * <p> type=String mandatory=true</p>
	 *
	 * @param p_sNom la valeur à affecter à l'attribut nom
	 *
	 */
	public void setNom(String p_sNom);

	/**
	 * 
	 * 
	 * <p>Attribute </p>
	 * <p> type=AddressLocation mandatory=true</p>

	 * 
	 * @return une entité AddressLocation correspondant à la valeur de l'attribut position
	 */
	public AddressLocation getPosition();

	/**
	 * 
	 *
	 * <p>Attribute </p>
	 * <p> type=AddressLocation mandatory=true</p>
	 *
	 * @param p_oPosition la valeur à affecter à l'attribut position
	 *
	 */
	public void setPosition(AddressLocation p_oPosition);

	/**
	 * 
	 * 
	 * <p>Attribute </p>
	 * <p> type=long mandatory=false</p>

	 * 
	 * @return une entité long correspondant à la valeur de l'attribut oldId
	 */
	public long getOldId();

	/**
	 * 
	 *
	 * <p>Attribute </p>
	 * <p> type=long mandatory=false</p>
	 *
	 * @param p_lOldId la valeur à affecter à l'attribut oldId
	 *
	 */
	public void setOldId(long p_lOldId);

	//@non-generated-start[methods]
	//@non-generated-end
}
