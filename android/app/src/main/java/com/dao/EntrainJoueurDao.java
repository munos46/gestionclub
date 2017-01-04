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
 * <p>Interface de DAO : EntrainJoueurDao</p>
 */
@Scope(ScopePolicy.SINGLETON)
public interface EntrainJoueurDao extends EntityDao<EntrainJoueur>
//@non-generated-start[class-signature]
//@non-generated-end
{
	/**
	 * Table du Dao
	 */
	public static final String TABLE_NAME = "T_ENTRAINJOUEUR";

	/**
	 * Alias du DAO
	 */
	public static final String ALIAS_NAME = "entrainJoueur1";

	/**
	 * Tableau de clés primaires
	 */
	@SuppressWarnings("unchecked")
	public static final FieldType[] PK_FIELDS = new FieldType[] { new FieldType(EntrainJoueurField.JOUEURID, SqlType.INTEGER),
			new FieldType(EntrainJoueurField.ENTRAINEMENTID, SqlType.INTEGER) };

	/**
	 * Tableau de clés étrangères pour l'association entrainements.
	 */
	@SuppressWarnings("unchecked")
	public static final FieldType[] FK_ENTRAINEMENTS = new FieldType[] { new FieldType(EntrainJoueurField.ENTRAINEMENTID, SqlType.INTEGER) };

	/**
	 * Tableau de clés étrangères pour l'association joueurs.
	 */
	@SuppressWarnings("unchecked")
	public static final FieldType[] FK_JOUEURS = new FieldType[] { new FieldType(EntrainJoueurField.JOUEURID, SqlType.INTEGER) };

	/**
	 * Number of fields
	 */
	public static final int NB_FIELDS = 2;

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
	 * Retourne une entité EntrainJoueur selon la clé primaire
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * @param p_lJoueurId un paramètre de la clé primaire de type long
	 * @param p_lEntrainementId un paramètre de la clé primaire de type long
	 * @param p_oContext contexte transactionnel
	 * @return une entité EntrainJoueur selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public EntrainJoueur getEntrainJoueur(long p_lJoueurId, long p_lEntrainementId, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une entité EntrainJoueur selon la clé primaire
	 * La cascade est CascadeSet.NONE
	 * 
	 * @param p_lJoueurId un paramètre de la clé primaire de type long
	 * @param p_lEntrainementId un paramètre de la clé primaire de type long
	 * @param p_oDaoQuery requête
	 * @param p_oContext contexte transactionnel
	 * @return une entité EntrainJoueur selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public EntrainJoueur getEntrainJoueur(long p_lJoueurId, long p_lEntrainementId, DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une entité EntrainJoueur selon la clé primaire
	 * Les blocs par défaut sont utilisés
	 * 
	 * @param p_lJoueurId un paramètre de la clé primaire de type long
	 * @param p_lEntrainementId un paramètre de la clé primaire de type long
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une entité EntrainJoueur selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public EntrainJoueur getEntrainJoueur(long p_lJoueurId, long p_lEntrainementId, CascadeSet p_oCascadeSet, MContext p_oContext)
			throws DaoException;

	/**
	 * Retourne une entité EntrainJoueur selon la clé primaire
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * @param p_lJoueurId un paramètre de la clé primaire de type long
	 * @param p_lEntrainementId un paramètre de la clé primaire de type long
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une entité EntrainJoueur selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public EntrainJoueur getEntrainJoueur(long p_lJoueurId, long p_lEntrainementId, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException;

	/**
	 * Retourne une entité EntrainJoueur selon la clé primaire
	 * 
	 * @param p_lJoueurId un paramètre de la clé primaire de type long
	 * @param p_lEntrainementId un paramètre de la clé primaire de type long
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une entité EntrainJoueur selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public EntrainJoueur getEntrainJoueur(long p_lJoueurId, long p_lEntrainementId, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet,
			MContext p_oContext) throws DaoException;

	/**
	 * Retourne une entité EntrainJoueur selon la clé primaire
	 * La cascade est CascadeSet.NONE
	 * 
	 * @param p_lJoueurId un paramètre de la clé primaire de type long
	 * @param p_lEntrainementId un paramètre de la clé primaire de type long
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une entité EntrainJoueur selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public EntrainJoueur getEntrainJoueur(long p_lJoueurId, long p_lEntrainementId, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession,
			MContext p_oContext) throws DaoException;

	/**
	 * Retourne une entité EntrainJoueur selon la clé primaire
	 * Les blocs par défaut sont utilisés
	 * 
	 * @param p_lJoueurId un paramètre de la clé primaire de type long
	 * @param p_lEntrainementId un paramètre de la clé primaire de type long
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une entité EntrainJoueur selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public EntrainJoueur getEntrainJoueur(long p_lJoueurId, long p_lEntrainementId, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession,
			MContext p_oContext) throws DaoException;

	/**
	 * Retourne une entité EntrainJoueur selon la clé primaire
	 * 
	 * @param p_lJoueurId un paramètre de la clé primaire de type long
	 * @param p_lEntrainementId un paramètre de la clé primaire de type long
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une entité EntrainJoueur selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public EntrainJoueur getEntrainJoueur(long p_lJoueurId, long p_lEntrainementId, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet,
			DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Retourne la liste de toutes les entités EntrainJoueur.
	 * La cascade est CascadeSet.NONE
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueur(MContext p_oContext) throws DaoException;

	/**
	 * Retourne la liste des entités EntrainJoueur selon la requête.
	 * La cascade est CascadeSet.NONE
	 * @param p_oDaoQuery requête
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueur(DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException;

	/**
	 * Retourne la liste de toutes les entités EntrainJoueur.
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueur(CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException;

	/**
	 * Retourne la liste de toutes les entités EntrainJoueur.
	 * La cascade est CascadeSet.NONE
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueur(DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Retourne la liste des entités EntrainJoueur selon la requête.
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueur(DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException;

	/**
	 * Retourne la liste des entités EntrainJoueur selon la requête.
	 * La cascade est CascadeSet.NONE
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueur(DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Retourne la liste de toutes les entités EntrainJoueur.
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueur(CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Retourne la liste des entités EntrainJoueur selon la requête.
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueur(DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException;

	/**
	 * Sauve ou met à jour l'entité EntrainJoueur passée en paramètre selon son existence en base.
	 * La cascade est CascadeSet.NONE
	 * @param p_oEntrainJoueur une entité EntrainJoueur
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateEntrainJoueur(EntrainJoueur p_oEntrainJoueur, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Sauve ou met à jour l'entité EntrainJoueur passée en paramètre selon son existence en base.
	 * @param p_oEntrainJoueur une entité EntrainJoueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateEntrainJoueur(EntrainJoueur p_oEntrainJoueur, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException;

	/**
	 * Sauve ou met à jour l'entité EntrainJoueur passée en paramètre selon son existence en base.
	 * @param p_oEntrainJoueur une entité EntrainJoueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateEntrainJoueur(EntrainJoueur p_oEntrainJoueur, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException;

	/**
	 * Sauve ou met à jour laliste d'entité EntrainJoueur passée en paramètre selon leur existence en base.
	 * La cascade est CascadeSet.NONE
	 * @param p_listEntrainJoueur une liste d'entité EntrainJoueur
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateListEntrainJoueur(Collection<EntrainJoueur> p_listEntrainJoueur, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException;

	/**
	 * Sauve ou met à jour la liste d'entité EntrainJoueur passée en paramètre selon leur existence en base.
	 * @param p_listEntrainJoueur une liste d'entité EntrainJoueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateListEntrainJoueur(Collection<EntrainJoueur> p_listEntrainJoueur, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession,
			MContext p_oContext) throws DaoException;

	/**
	 * Sauve, en base de données, l'entité EntrainJoueur passée en paramètre.
	 * Cette entité est considérée absente de la base de données avant son insertion.
	 * La cascade est CascadeSet.NONE
	 * @param p_oEntrainJoueur une entité EntrainJoueur à sauvegarder.
	 * @param p_oCascadeSet La cascade à utiliser lors de la sauvegarde de l'élément.
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveEntrainJoueur(EntrainJoueur p_oEntrainJoueur, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException;

	/**
	 * Sauve, en base de données, chaque élément de la liste d'entité EntrainJoueur passée en paramètre.
	 * Ces éléments sont considérés absents de la base de données avant leur insertion.
	 * @param p_listEntrainJoueur une liste d'entité  à sauvegarder.
	 * @param p_oCascadeSet La cascade à utiliser pour sauver chaque EntrainJoueur.
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveListEntrainJoueur(Collection<EntrainJoueur> p_listEntrainJoueur, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession,
			MContext p_oContext) throws DaoException;

	/**
	 * Met à jour, en base de données, l'entité  passée en paramètre.
	 * @param p_oEntrainJoueur une entité 
	 * @param p_oCascadeSet ensemble de Cascades sur les entités	 
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void updateEntrainJoueur(EntrainJoueur p_oEntrainJoueur, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException;

	/**
	 * Met à jour, en base, chaque élément d'une liste de .
	 * @param p_listEntrainJoueur une liste d'entité 
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void updateListEntrainJoueur(Collection<EntrainJoueur> p_listEntrainJoueur, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession,
			MContext p_oContext) throws DaoException;

	/**
	 * Supprime de la base de données l'entité EntrainJoueur passée en paramètre.
	 * La cascade est this.getDeleteCascade()
	 * @param p_oEntrainJoueur une entité EntrainJoueur
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteEntrainJoueur(EntrainJoueur p_oEntrainJoueur, MContext p_oContext) throws DaoException;

	/**
	 * Supprime de la base de données l'entité EntrainJoueur passée en paramètre.
	 * La cascade doît être correctement indiquée.
	 * @param p_oEntrainJoueur une entité EntrainJoueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteEntrainJoueur(EntrainJoueur p_oEntrainJoueur, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException;

	/**
	 * Supprime de la base de données l'entité EntrainJoueur correspondant aux paramètres.
	 * Cette suppression ne supprime pas les entités liés en cascade.
	 * 
	 * @param p_lJoueurId un paramètre de la clé primaire de type long
	 * @param p_lEntrainementId un paramètre de la clé primaire de type long
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteEntrainJoueur(long p_lJoueurId, long p_lEntrainementId, MContext p_oContext) throws DaoException;

	/**
	 * Supprime de la base de données la liste d'entité EntrainJoueur passée en paramètre.
	 * La cascade est CascadeSet.NONE
	 * @param p_listEntrainJoueur une liste d'entité EntrainJoueur
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteListEntrainJoueur(Collection<EntrainJoueur> p_listEntrainJoueur, MContext p_oContext) throws DaoException;

	/**
	 * Supprime de la base de données la liste d'entité EntrainJoueur passée en paramètre.
	 * @param p_listEntrainJoueur une liste d'entité EntrainJoueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteListEntrainJoueur(Collection<EntrainJoueur> p_listEntrainJoueur, CascadeSet p_oCascadeSet, MContext p_oContext)
			throws DaoException;

	/**
	 * Retourne le nombre d'entité EntrainJoueur en base.
	 * Les blocs par défaut sont utilisés
	 * @param p_oContext contexte transactionnel
	 * @return le nombre d'entité EntrainJoueur en base
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public int getNbEntrainJoueur(MContext p_oContext) throws DaoException;

	/**
	 * Retourne le nombre d'entité EntrainJoueur selon la requête.
	 * @param p_oDaoQuery requête
	 * @param p_oContext contexte transactionnel
	 * @return le nombre d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public int getNbEntrainJoueur(DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException;

	/**
	 * Return delete cascade
	 * @return delete cascade
	 */
	public CascadeSet getDeleteCascade();

	/**
	 * Retourne une liste d'entité EntrainJoueur selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lJoueurId un paramètre de type long
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueurByJoueurId(long p_lJoueurId, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une liste d'entité EntrainJoueur selon les paramètres.
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lJoueurId un paramètre de type long
	 * @param p_oDaoQuery requête
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueurByJoueurId(long p_lJoueurId, DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une liste d'entité EntrainJoueur selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * 
	 * 
	 * @param p_lJoueurId un paramètre de type long
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueurByJoueurId(long p_lJoueurId, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une liste d'entité EntrainJoueur selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lJoueurId un paramètre de type long
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueurByJoueurId(long p_lJoueurId, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une liste d'entité EntrainJoueur selon les paramètres.
	 * 
	 * 
	 * @param p_lJoueurId un paramètre de type long
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueurByJoueurId(long p_lJoueurId, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, MContext p_oContext)
			throws DaoException;

	/**
	 * Retourne une liste d'entité EntrainJoueur selon les paramètres.
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lJoueurId un paramètre de type long
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueurByJoueurId(long p_lJoueurId, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException;

	/**
	 * Retourne une liste d'entité EntrainJoueur selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lJoueurId un paramètre de type long
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueurByJoueurId(long p_lJoueurId, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession,
			MContext p_oContext) throws DaoException;

	/**
	 * Retourne une liste d'entité EntrainJoueur selon les paramètres.
	 * 
	 * 
	 * @param p_lJoueurId un paramètre de type long
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueurByJoueurId(long p_lJoueurId, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet,
			DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Supprime de la base de données l'entité EntrainJoueur selon les paramètres.
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lJoueurId un paramètre de type long
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteEntrainJoueurByJoueurId(long p_lJoueurId, MContext p_oContext) throws DaoException;

	/**
	 * Supprime de la base de données l'entité EntrainJoueur selon les paramètres.
	 * 
	 * 
	 * @param p_lJoueurId un paramètre de type long
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteEntrainJoueurByJoueurId(long p_lJoueurId, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une liste d'entité EntrainJoueur selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lEntrainementId un paramètre de type long
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueurByEntrainementId(long p_lEntrainementId, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une liste d'entité EntrainJoueur selon les paramètres.
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lEntrainementId un paramètre de type long
	 * @param p_oDaoQuery requête
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueurByEntrainementId(long p_lEntrainementId, DaoQuery p_oDaoQuery, MContext p_oContext)
			throws DaoException;

	/**
	 * Retourne une liste d'entité EntrainJoueur selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * 
	 * 
	 * @param p_lEntrainementId un paramètre de type long
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueurByEntrainementId(long p_lEntrainementId, CascadeSet p_oCascadeSet, MContext p_oContext)
			throws DaoException;

	/**
	 * Retourne une liste d'entité EntrainJoueur selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lEntrainementId un paramètre de type long
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueurByEntrainementId(long p_lEntrainementId, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException;

	/**
	 * Retourne une liste d'entité EntrainJoueur selon les paramètres.
	 * 
	 * 
	 * @param p_lEntrainementId un paramètre de type long
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueurByEntrainementId(long p_lEntrainementId, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet,
			MContext p_oContext) throws DaoException;

	/**
	 * Retourne une liste d'entité EntrainJoueur selon les paramètres.
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lEntrainementId un paramètre de type long
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueurByEntrainementId(long p_lEntrainementId, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession,
			MContext p_oContext) throws DaoException;

	/**
	 * Retourne une liste d'entité EntrainJoueur selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lEntrainementId un paramètre de type long
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueurByEntrainementId(long p_lEntrainementId, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession,
			MContext p_oContext) throws DaoException;

	/**
	 * Retourne une liste d'entité EntrainJoueur selon les paramètres.
	 * 
	 * 
	 * @param p_lEntrainementId un paramètre de type long
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueurByEntrainementId(long p_lEntrainementId, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet,
			DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Supprime de la base de données l'entité EntrainJoueur selon les paramètres.
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lEntrainementId un paramètre de type long
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteEntrainJoueurByEntrainementId(long p_lEntrainementId, MContext p_oContext) throws DaoException;

	/**
	 * Supprime de la base de données l'entité EntrainJoueur selon les paramètres.
	 * 
	 * 
	 * @param p_lEntrainementId un paramètre de type long
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteEntrainJoueurByEntrainementId(long p_lEntrainementId, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException;

	/**
	 * Sauvegarde l'entité EntrainJoueur avec les paramètres de la clé primaire.
	 * 
	 * @param p_lJoueurId un paramètre de la clé primaire de type long
	 * @param p_lEntrainementId un paramètre de la clé primaire de type long
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveEntrainJoueur(long p_lJoueurId, long p_lEntrainementId, MContext p_oContext) throws DaoException;

	/**
	 * Sauvegarde l'entité EntrainJoueur. 
	 * @param p_oJoueurs une entité Joueur
	 * @param p_listEntrainements une liste d'entité Entrainement
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveEntrainements(Joueur p_oJoueurs, Collection<Entrainement> p_listEntrainements, MContext p_oContext) throws DaoException;

	/**
	 * Sauvegarde l'entité EntrainJoueur. 
	 * @param p_oJoueurs une entité Joueur
	 * @param p_listEntrainements une liste d'entité Entrainement
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveEntrainements(Joueur p_oJoueurs, Collection<Entrainement> p_listEntrainements, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException;

	/**
	 * Sauvegarde l'entité EntrainJoueur. 
	 * @param p_oEntrainements une entité Entrainement
	 * @param p_listJoueurs une liste d'entité Joueur
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveJoueurs(Entrainement p_oEntrainements, Collection<Joueur> p_listJoueurs, MContext p_oContext) throws DaoException;

	/**
	 * Sauvegarde l'entité EntrainJoueur. 
	 * @param p_oEntrainements une entité Entrainement
	 * @param p_listJoueurs une liste d'entité Joueur
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveJoueurs(Entrainement p_oEntrainements, Collection<Joueur> p_listJoueurs, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException;

	//@non-generated-start[methods]
	//@non-generated-end
}
