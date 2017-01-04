package com.model;

import com.adeuza.movalysfwk.mf4jcommons.core.beans.MEntity;

/**
 * 
 * <p>Interface : EntrainJoueur</p>
 *
 * 
 * 
 * 
 */
public interface EntrainJoueur extends MEntity
//@non-generated-start[class-signature]
//@non-generated-end
{

	/**
	 * Constante indiquant le nom de l'entité
	 */
	public static final String ENTITY_NAME = "EntrainJoueur";

	//@non-generated-start[constants]
	//@non-generated-end

	/**
	 * 
	 * 
	 * <p>Attribute JOUEURID</p>
	 * <p> type=long mandatory=true</p>

	 * 
	 * @return une entité long correspondant à la valeur de l'attribut joueurId
	 */
	public long getJoueurId();

	/**
	 * 
	 *
	 * <p>Attribute JOUEURID</p>
	 * <p> type=long mandatory=true</p>
	 *
	 * @param p_lJoueurId la valeur à affecter à l'attribut joueurId
	 *
	 */
	public void setJoueurId(long p_lJoueurId);

	/**
	 * 
	 * 
	 * <p>Attribute ENTRAINEMENTID</p>
	 * <p> type=long mandatory=true</p>

	 * 
	 * @return une entité long correspondant à la valeur de l'attribut entrainementId
	 */
	public long getEntrainementId();

	/**
	 * 
	 *
	 * <p>Attribute ENTRAINEMENTID</p>
	 * <p> type=long mandatory=true</p>
	 *
	 * @param p_lEntrainementId la valeur à affecter à l'attribut entrainementId
	 *
	 */
	public void setEntrainementId(long p_lEntrainementId);

	/**
	 * Renvoie l'identifiant de l'attribut entrainement au format chaîne de caractères.
	 * 
	 * @return l'identifiant de l'attribut entrainement au format chaîne de caractères.
	 */
	public String entrainementIdToString();

	/**
	 * Renvoie l'identifiant de l'attribut joueur au format chaîne de caractères.
	 * 
	 * @return l'identifiant de l'attribut joueur au format chaîne de caractères.
	 */
	public String joueurIdToString();

	//@non-generated-start[methods]
	//@non-generated-end
}
