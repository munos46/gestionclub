package com.dao;

import java.util.Collection;
import java.util.List;
import com.adeuza.movalysfwk.mf4jcommons.core.beans.Scope;
import com.adeuza.movalysfwk.mf4jcommons.core.beans.ScopePolicy;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.context.MContext;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.beans.CascadeSet;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.DaoException;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.DaoQuery;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.DaoSession;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.EntityDao;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.Field;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.FieldType;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.query.SqlDelete;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.query.SqlInsert;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.query.SqlUpdate;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.SqlType;
import com.model.Entrainement;
import com.model.EntrainJoueur;
import com.model.Joueur;

/**
 * <p>Interface de DAO : JoueurDao</p>
 */
@Scope(ScopePolicy.SINGLETON)
public interface JoueurDao extends EntityDao<Joueur>
//@non-generated-start[class-signature]
//@non-generated-end
{
	/**
	 * Table du Dao
	 */
	public static final String TABLE_NAME = "T_JOUEUR";

	/**
	 * Alias du DAO
	 */
	public static final String ALIAS_NAME = "joueur1";

	/**
	 * Tableau de clés primaires
	 */
	@SuppressWarnings("unchecked")
	public static final FieldType[] PK_FIELDS = new FieldType[] { new FieldType(JoueurField.ID, SqlType.INTEGER) };

	/**
	 * Number of fields
	 */
	public static final int NB_FIELDS = 5;

	//@non-generated-start[constants]
	//@non-generated-end

	/**
	 * Renvoie un clone de la requête de sélection des entités (pour qu'elle puisse être modifiée)
	 * 
	 * @return un clone de la requête de sélection des entités
	 */
	public DaoQuery getSelectDaoQuery();

	/**
	 * Renvoie un clone de la requête de comptage (pour qu'elle puisse être modifiée)
	 * 
	 * @return un clone de la requête de comptage
	 */
	public DaoQuery getCountDaoQuery();

	/**
	 * Renvoie un clone de la requête d'insertion (pour qu'elle puisse être modifiée)
	 *
	 * @return un clone de la requête d'insertion
	 */
	public SqlInsert getInsertQuery();

	/**
	 * Renvoie un clone de la requête d'update (pour qu'elle puisse être modifiée)
	 * 
	 * @return un clone de la requête d'update
	 */
	public SqlUpdate getUpdateQuery();

	/**
	 * Renvoie un clone de la requête de suppression (pour qu'elle puisse être modifiée)
	 * 
	 * @return renvoie un clone de la requête de suppression
	 */
	public SqlDelete getDeleteQuery();

	/**
	 * Retourne une entité Joueur selon la clé primaire
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oContext contexte transactionnel
	 * @return une entité Joueur selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Joueur getJoueur(long p_lId, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une entité Joueur selon la clé primaire
	 * La cascade est CascadeSet.NONE
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oDaoQuery requête
	 * @param p_oContext contexte transactionnel
	 * @return une entité Joueur selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Joueur getJoueur(long p_lId, DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une entité Joueur selon la clé primaire
	 * Les blocs par défaut sont utilisés
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une entité Joueur selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Joueur getJoueur(long p_lId, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une entité Joueur selon la clé primaire
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une entité Joueur selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Joueur getJoueur(long p_lId, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une entité Joueur selon la clé primaire
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une entité Joueur selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Joueur getJoueur(long p_lId, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une entité Joueur selon la clé primaire
	 * La cascade est CascadeSet.NONE
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une entité Joueur selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Joueur getJoueur(long p_lId, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une entité Joueur selon la clé primaire
	 * Les blocs par défaut sont utilisés
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une entité Joueur selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Joueur getJoueur(long p_lId, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une entité Joueur selon la clé primaire
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une entité Joueur selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Joueur getJoueur(long p_lId, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException;

	/**
	 * Retourne la liste de toutes les entités Joueur.
	 * La cascade est CascadeSet.NONE
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueur(MContext p_oContext) throws DaoException;

	/**
	 * Retourne la liste des entités Joueur selon la requête.
	 * La cascade est CascadeSet.NONE
	 * @param p_oDaoQuery requête
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueur(DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException;

	/**
	 * Retourne la liste de toutes les entités Joueur.
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueur(CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException;

	/**
	 * Retourne la liste de toutes les entités Joueur.
	 * La cascade est CascadeSet.NONE
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueur(DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Retourne la liste des entités Joueur selon la requête.
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueur(DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException;

	/**
	 * Retourne la liste des entités Joueur selon la requête.
	 * La cascade est CascadeSet.NONE
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueur(DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Retourne la liste de toutes les entités Joueur.
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueur(CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Retourne la liste des entités Joueur selon la requête.
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueur(DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException;

	/**
	 * Sauve ou met à jour l'entité Joueur passée en paramètre selon son existence en base.
	 * La cascade est CascadeSet.NONE
	 * @param p_oJoueur une entité Joueur
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateJoueur(Joueur p_oJoueur, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Sauve ou met à jour l'entité Joueur passée en paramètre selon son existence en base.
	 * @param p_oJoueur une entité Joueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateJoueur(Joueur p_oJoueur, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException;

	/**
	 * Sauve ou met à jour l'entité Joueur passée en paramètre selon son existence en base.
	 * @param p_oJoueur une entité Joueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateJoueur(Joueur p_oJoueur, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Sauve ou met à jour laliste d'entité Joueur passée en paramètre selon leur existence en base.
	 * La cascade est CascadeSet.NONE
	 * @param p_listJoueur une liste d'entité Joueur
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateListJoueur(Collection<Joueur> p_listJoueur, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Sauve ou met à jour la liste d'entité Joueur passée en paramètre selon leur existence en base.
	 * @param p_listJoueur une liste d'entité Joueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateListJoueur(Collection<Joueur> p_listJoueur, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException;

	/**
	 * Sauve, en base de données, l'entité Joueur passée en paramètre.
	 * Cette entité est considérée absente de la base de données avant son insertion.
	 * La cascade est CascadeSet.NONE
	 * @param p_oJoueur une entité Joueur à sauvegarder.
	 * @param p_oCascadeSet La cascade à utiliser lors de la sauvegarde de l'élément.
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveJoueur(Joueur p_oJoueur, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Sauve, en base de données, chaque élément de la liste d'entité Joueur passée en paramètre.
	 * Ces éléments sont considérés absents de la base de données avant leur insertion.
	 * @param p_listJoueur une liste d'entité  à sauvegarder.
	 * @param p_oCascadeSet La cascade à utiliser pour sauver chaque Joueur.
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveListJoueur(Collection<Joueur> p_listJoueur, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException;

	/**
	 * Met à jour, en base de données, l'entité  passée en paramètre.
	 * @param p_oJoueur une entité 
	 * @param p_oCascadeSet ensemble de Cascades sur les entités	 
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void updateJoueur(Joueur p_oJoueur, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Met à jour, en base, chaque élément d'une liste de .
	 * @param p_listJoueur une liste d'entité 
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void updateListJoueur(Collection<Joueur> p_listJoueur, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException;

	/**
	 * Supprime de la base de données l'entité Joueur passée en paramètre.
	 * La cascade est this.getDeleteCascade()
	 * @param p_oJoueur une entité Joueur
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteJoueur(Joueur p_oJoueur, MContext p_oContext) throws DaoException;

	/**
	 * Supprime de la base de données l'entité Joueur passée en paramètre.
	 * La cascade doît être correctement indiquée.
	 * @param p_oJoueur une entité Joueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteJoueur(Joueur p_oJoueur, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException;

	/**
	 * Supprime de la base de données l'entité Joueur correspondant aux paramètres.
	 * Cette suppression ne supprime pas les entités liés en cascade.
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteJoueur(long p_lId, MContext p_oContext) throws DaoException;

	/**
	 * Supprime de la base de données la liste d'entité Joueur passée en paramètre.
	 * La cascade est CascadeSet.NONE
	 * @param p_listJoueur une liste d'entité Joueur
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteListJoueur(Collection<Joueur> p_listJoueur, MContext p_oContext) throws DaoException;

	/**
	 * Supprime de la base de données la liste d'entité Joueur passée en paramètre.
	 * @param p_listJoueur une liste d'entité Joueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteListJoueur(Collection<Joueur> p_listJoueur, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException;

	/**
	 * Retourne le nombre d'entité Joueur en base.
	 * Les blocs par défaut sont utilisés
	 * @param p_oContext contexte transactionnel
	 * @return le nombre d'entité Joueur en base
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public int getNbJoueur(MContext p_oContext) throws DaoException;

	/**
	 * Retourne le nombre d'entité Joueur selon la requête.
	 * @param p_oDaoQuery requête
	 * @param p_oContext contexte transactionnel
	 * @return le nombre d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public int getNbJoueur(DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException;

	/**
	 * Return delete cascade
	 * @return delete cascade
	 */
	public CascadeSet getDeleteCascade();

	/**
	 * Retourne une liste d'entité Joueur selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_oEntrainement un paramètre de type Entrainement
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueurByEntrainement(Entrainement p_oEntrainement, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une liste d'entité Joueur selon les paramètres.
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_oEntrainement un paramètre de type Entrainement
	 * @param p_oDaoQuery requête
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueurByEntrainement(Entrainement p_oEntrainement, DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une liste d'entité Joueur selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * 
	 * 
	 * @param p_oEntrainement un paramètre de type Entrainement
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueurByEntrainement(Entrainement p_oEntrainement, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une liste d'entité Joueur selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_oEntrainement un paramètre de type Entrainement
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueurByEntrainement(Entrainement p_oEntrainement, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une liste d'entité Joueur selon les paramètres.
	 * 
	 * 
	 * @param p_oEntrainement un paramètre de type Entrainement
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueurByEntrainement(Entrainement p_oEntrainement, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, MContext p_oContext)
			throws DaoException;

	/**
	 * Retourne une liste d'entité Joueur selon les paramètres.
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_oEntrainement un paramètre de type Entrainement
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueurByEntrainement(Entrainement p_oEntrainement, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException;

	/**
	 * Retourne une liste d'entité Joueur selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_oEntrainement un paramètre de type Entrainement
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueurByEntrainement(Entrainement p_oEntrainement, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession,
			MContext p_oContext) throws DaoException;

	/**
	 * Retourne une liste d'entité Joueur selon les paramètres.
	 * 
	 * 
	 * @param p_oEntrainement un paramètre de type Entrainement
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueurByEntrainement(Entrainement p_oEntrainement, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet,
			DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une liste d'entité Joueur selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lEntrainemententrainementId un paramètre de type long
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueurByEntrainement(long p_lEntrainemententrainementId, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une liste d'entité Joueur selon les paramètres.
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lEntrainemententrainementId un paramètre de type long
	 * @param p_oDaoQuery requête
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueurByEntrainement(long p_lEntrainemententrainementId, DaoQuery p_oDaoQuery, MContext p_oContext)
			throws DaoException;

	/**
	 * Retourne une liste d'entité Joueur selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * 
	 * 
	 * @param p_lEntrainemententrainementId un paramètre de type long
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueurByEntrainement(long p_lEntrainemententrainementId, CascadeSet p_oCascadeSet, MContext p_oContext)
			throws DaoException;

	/**
	 * Retourne une liste d'entité Joueur selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lEntrainemententrainementId un paramètre de type long
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueurByEntrainement(long p_lEntrainemententrainementId, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException;

	/**
	 * Retourne une liste d'entité Joueur selon les paramètres.
	 * 
	 * 
	 * @param p_lEntrainemententrainementId un paramètre de type long
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueurByEntrainement(long p_lEntrainemententrainementId, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet,
			MContext p_oContext) throws DaoException;

	/**
	 * Retourne une liste d'entité Joueur selon les paramètres.
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lEntrainemententrainementId un paramètre de type long
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueurByEntrainement(long p_lEntrainemententrainementId, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession,
			MContext p_oContext) throws DaoException;

	/**
	 * Retourne une liste d'entité Joueur selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lEntrainemententrainementId un paramètre de type long
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueurByEntrainement(long p_lEntrainemententrainementId, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession,
			MContext p_oContext) throws DaoException;

	/**
	 * Retourne une liste d'entité Joueur selon les paramètres.
	 * 
	 * 
	 * @param p_lEntrainemententrainementId un paramètre de type long
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueurByEntrainement(long p_lEntrainemententrainementId, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet,
			DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	//@non-generated-start[methods]
	//@non-generated-end
}
