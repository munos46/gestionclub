package com.model;

import java.sql.Timestamp;
import java.util.List;
import com.adeuza.movalysfwk.mf4jcommons.core.beans.MEntity;

/**
 * 
 * <p>Interface : Entrainement</p>
 *
 * 
 * 
 * 
 */
public interface Entrainement extends MEntity
//@non-generated-start[class-signature]
//@non-generated-end
{

	/**
	 * Constante indiquant le nom de l'entité
	 */
	public static final String ENTITY_NAME = "Entrainement";

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
	 * <p>Attribute DATEHEURE</p>
	 * <p> type=Timestamp mandatory=true</p>

	 * 
	 * @return une entité Timestamp correspondant à la valeur de l'attribut dateHeure
	 */
	public Timestamp getDateHeure();

	/**
	 * 
	 *
	 * <p>Attribute DATEHEURE</p>
	 * <p> type=Timestamp mandatory=true</p>
	 *
	 * @param p_oDateHeure la valeur à affecter à l'attribut dateHeure
	 *
	 */
	public void setDateHeure(Timestamp p_oDateHeure);

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

	/**
	 * 
	 * 
	 * <p>Attribute </p>
	 * <p> type=long mandatory=false</p>

	 * 
	 * @return une entité long correspondant à la valeur de l'attribut lieuId
	 */
	public long getLieuId();

	/**
	 * 
	 *
	 * <p>Attribute </p>
	 * <p> type=long mandatory=false</p>
	 *
	 * @param p_lLieuId la valeur à affecter à l'attribut lieuId
	 *
	 */
	public void setLieuId(long p_lLieuId);

	/**
	 * <p>Cascade LIEU</p>
	 * <p>Relation ManyToOne</p><p> targetEntity=Lieu mandatory=true relationOwner=true transient=false</p>

	 * 
	 * @return une entité Lieu correspondant à la valeur de l'association lieu
	 */
	public Lieu getLieu();

	/**
	 * <p>Cascade LIEU</p>
	 * <p>Relation ManyToOne</p><p> targetEntity=Lieu mandatory=true relationOwner=true transient=false</p>
	 *
	 * @param p_oLieu la valeur à affecter à l'association lieu
	 *
	 */
	public void setLieu(Lieu p_oLieu);

	/**
	 * <p>Cascade JOUEURS</p>
	 * <p>Relation ManyToMany</p><p> targetEntity=Joueur relationOwner=false transient=false</p>

	 * 
	 * @return une liste d'entité Joueur correspondant à la valeur de l'association joueurs
	 */
	public List<Joueur> getJoueurs();

	/**
	 * <p>Cascade JOUEURS</p>
	 * <p>Relation ManyToMany</p><p> targetEntity=Joueur relationOwner=false transient=false</p>
	 *
	 * @param p_listJoueurs la valeur à affecter à l'association joueurs
	 *
	 */
	public void setJoueurs(List<Joueur> p_listJoueurs);

	//@non-generated-start[methods]
	//@non-generated-end
}
