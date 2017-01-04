package com.model;

import java.sql.Timestamp;
import java.util.List;
import com.adeuza.movalysfwk.mf4jcommons.core.beans.MEntity;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.model.MPhoto;

/**
 * 
 * <p>Interface : Joueur</p>
 *
 * 
 * 
 * 
 */
public interface Joueur extends MEntity
//@non-generated-start[class-signature]
//@non-generated-end
{

	/**
	 * Constante indiquant le nom de l'entité
	 */
	public static final String ENTITY_NAME = "Joueur";

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
	 * <p>Attribute PRENOM</p>
	 * <p> type=String mandatory=true</p>

	 * 
	 * @return une entité String correspondant à la valeur de l'attribut prenom
	 */
	public String getPrenom();

	/**
	 * 
	 *
	 * <p>Attribute PRENOM</p>
	 * <p> type=String mandatory=true</p>
	 *
	 * @param p_sPrenom la valeur à affecter à l'attribut prenom
	 *
	 */
	public void setPrenom(String p_sPrenom);

	/**
	 * 
	 * 
	 * <p>Attribute DATENAISSANCE</p>
	 * <p> type=Timestamp mandatory=true</p>

	 * 
	 * @return une entité Timestamp correspondant à la valeur de l'attribut dateNaissance
	 */
	public Timestamp getDateNaissance();

	/**
	 * 
	 *
	 * <p>Attribute DATENAISSANCE</p>
	 * <p> type=Timestamp mandatory=true</p>
	 *
	 * @param p_oDateNaissance la valeur à affecter à l'attribut dateNaissance
	 *
	 */
	public void setDateNaissance(Timestamp p_oDateNaissance);

	/**
	 * 
	 * 
	 * <p>Attribute </p>
	 * <p> type=MPhoto mandatory=false</p>

	 * 
	 * @return une entité MPhoto correspondant à la valeur de l'attribut photo
	 */
	public MPhoto getPhoto();

	/**
	 * 
	 *
	 * <p>Attribute </p>
	 * <p> type=MPhoto mandatory=false</p>
	 *
	 * @param p_oPhoto la valeur à affecter à l'attribut photo
	 *
	 */
	public void setPhoto(MPhoto p_oPhoto);

	/**
	 * 
	 * 
	 * <p>Attribute EMAIL</p>
	 * <p> type=String mandatory=false</p>

	 * 
	 * @return une entité String correspondant à la valeur de l'attribut email
	 */
	public String getEmail();

	/**
	 * 
	 *
	 * <p>Attribute EMAIL</p>
	 * <p> type=String mandatory=false</p>
	 *
	 * @param p_sEmail la valeur à affecter à l'attribut email
	 *
	 */
	public void setEmail(String p_sEmail);

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
	 * <p>Cascade ENTRAINEMENTS</p>
	 * <p>Relation ManyToMany</p><p> targetEntity=Entrainement relationOwner=true transient=false</p>

	 * 
	 * @return une liste d'entité Entrainement correspondant à la valeur de l'association entrainements
	 */
	public List<Entrainement> getEntrainements();

	/**
	 * <p>Cascade ENTRAINEMENTS</p>
	 * <p>Relation ManyToMany</p><p> targetEntity=Entrainement relationOwner=true transient=false</p>
	 *
	 * @param p_listEntrainements la valeur à affecter à l'association entrainements
	 *
	 */
	public void setEntrainements(List<Entrainement> p_listEntrainements);

	//@non-generated-start[methods]
	//@non-generated-end
}
