package com.viewmodel;

import java.sql.Date;
import com.adeuza.movalysfwk.mf4jcommons.core.beans.Scope;
import com.adeuza.movalysfwk.mf4jcommons.core.beans.ScopePolicy;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.Dataloader;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.model.MPhoto;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.model.MPhotoHelper;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ItemViewModel;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ItemViewModelId;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ListViewModel;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.MPhotoVO;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.UpdatableFromDataloader;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.utils.DateUtils;
import com.adoliveira.gestionclub.loader.DetailJoueurPanelLoader;
import com.model.Joueur;
import com.model.JoueurFactory;

/**
 * 
 */
@Scope(ScopePolicy.SINGLETON)
public interface VMDetailJoueurPanel extends ItemViewModel<Joueur>, UpdatableFromDataloader, ItemViewModelId
//@non-generated-start[class-signature]
//@non-generated-end
{

	//@non-generated-start[constants]
	//@non-generated-end

	/**
	 * getter method for nom
	 * @return Stringvalue of nom
	 */
	public String getNom();

	/**
	 * setter method for nom
	 * @param p_onom the value to set
	 */
	public void setNom(String p_onom);

	/**
	 * getter method for prenom
	 * @return Stringvalue of prenom
	 */
	public String getPrenom();

	/**
	 * setter method for prenom
	 * @param p_oprenom the value to set
	 */
	public void setPrenom(String p_oprenom);

	/**
	 * getter method for dateNaissance
	 * @return Datevalue of dateNaissance
	 */
	public Date getDateNaissance();

	/**
	 * setter method for dateNaissance
	 * @param p_odateNaissance the value to set
	 */
	public void setDateNaissance(Date p_odateNaissance);

	/**
	 * getter method for photo
	 * @return MPhotoVOvalue of photo
	 */
	public MPhotoVO getPhoto();

	/**
	 * setter method for photo
	 * @param p_ophoto the value to set
	 */
	public void setPhoto(MPhotoVO p_ophoto);

	/**
	 * getter method for email
	 * @return Stringvalue of email
	 */
	public String getEmail();

	/**
	 * setter method for email
	 * @param p_oemail the value to set
	 */
	public void setEmail(String p_oemail);

	/**
	 * getter method for taille
	 * @return doublevalue of taille
	 */
	public double getTaille();

	/**
	 * setter method for taille
	 * @param p_otaille the value to set
	 */
	public void setTaille(double p_otaille);

	/**
	 * getter method for poids
	 * @return doublevalue of poids
	 */
	public double getPoids();

	/**
	 * setter method for poids
	 * @param p_opoids the value to set
	 */
	public void setPoids(double p_opoids);

	/**
	 * getter method for adresse
	 * @return Stringvalue of adresse
	 */
	public String getAdresse();

	/**
	 * setter method for adresse
	 * @param p_oadresse the value to set
	 */
	public void setAdresse(String p_oadresse);

	/**
	 * getter method for commune
	 * @return Stringvalue of commune
	 */
	public String getCommune();

	/**
	 * setter method for commune
	 * @param p_ocommune the value to set
	 */
	public void setCommune(String p_ocommune);

	/**
	 * getter method for ville
	 * @return Stringvalue of ville
	 */
	public String getVille();

	/**
	 * setter method for ville
	 * @param p_oville the value to set
	 */
	public void setVille(String p_oville);

	//@non-generated-start[methods]
	//@non-generated-end
}
