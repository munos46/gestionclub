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
import com.model.Lieu;

/**
 * <p>Interface de DAO : LieuDao</p>
 */
@Scope(ScopePolicy.SINGLETON)
public interface LieuDao extends EntityDao<Lieu>
//@non-generated-start[class-signature]
//@non-generated-end
{
	/**
	 * Table du Dao
	 */
	public static final String TABLE_NAME = "T_LIEU";

	/**
	 * Alias du DAO
	 */
	public static final String ALIAS_NAME = "lieu1";

	/**
	 * Tableau de clés primaires
	 */
	@SuppressWarnings("unchecked")
	public static final FieldType[] PK_FIELDS = new FieldType[] { new FieldType(LieuField.ID, SqlType.INTEGER) };

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
	 * Retourne une entité Lieu selon la clé primaire
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oContext contexte transactionnel
	 * @return une entité Lieu selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Lieu getLieu(long p_lId, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une entité Lieu selon la clé primaire
	 * La cascade est CascadeSet.NONE
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oDaoQuery requête
	 * @param p_oContext contexte transactionnel
	 * @return une entité Lieu selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Lieu getLieu(long p_lId, DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une entité Lieu selon la clé primaire
	 * Les blocs par défaut sont utilisés
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une entité Lieu selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Lieu getLieu(long p_lId, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une entité Lieu selon la clé primaire
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une entité Lieu selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Lieu getLieu(long p_lId, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une entité Lieu selon la clé primaire
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une entité Lieu selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Lieu getLieu(long p_lId, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une entité Lieu selon la clé primaire
	 * La cascade est CascadeSet.NONE
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une entité Lieu selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Lieu getLieu(long p_lId, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une entité Lieu selon la clé primaire
	 * Les blocs par défaut sont utilisés
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une entité Lieu selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Lieu getLieu(long p_lId, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Retourne une entité Lieu selon la clé primaire
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une entité Lieu selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Lieu getLieu(long p_lId, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException;

	/**
	 * Retourne la liste de toutes les entités Lieu.
	 * La cascade est CascadeSet.NONE
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Lieu
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Lieu> getListLieu(MContext p_oContext) throws DaoException;

	/**
	 * Retourne la liste des entités Lieu selon la requête.
	 * La cascade est CascadeSet.NONE
	 * @param p_oDaoQuery requête
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Lieu
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Lieu> getListLieu(DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException;

	/**
	 * Retourne la liste de toutes les entités Lieu.
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Lieu
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Lieu> getListLieu(CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException;

	/**
	 * Retourne la liste de toutes les entités Lieu.
	 * La cascade est CascadeSet.NONE
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Lieu
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Lieu> getListLieu(DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Retourne la liste des entités Lieu selon la requête.
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Lieu
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Lieu> getListLieu(DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException;

	/**
	 * Retourne la liste des entités Lieu selon la requête.
	 * La cascade est CascadeSet.NONE
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Lieu
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Lieu> getListLieu(DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Retourne la liste de toutes les entités Lieu.
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Lieu
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Lieu> getListLieu(CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Retourne la liste des entités Lieu selon la requête.
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Lieu
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Lieu> getListLieu(DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Sauve ou met à jour l'entité Lieu passée en paramètre selon son existence en base.
	 * La cascade est CascadeSet.NONE
	 * @param p_oLieu une entité Lieu
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateLieu(Lieu p_oLieu, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Sauve ou met à jour l'entité Lieu passée en paramètre selon son existence en base.
	 * @param p_oLieu une entité Lieu
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateLieu(Lieu p_oLieu, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException;

	/**
	 * Sauve ou met à jour l'entité Lieu passée en paramètre selon son existence en base.
	 * @param p_oLieu une entité Lieu
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateLieu(Lieu p_oLieu, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Sauve ou met à jour laliste d'entité Lieu passée en paramètre selon leur existence en base.
	 * La cascade est CascadeSet.NONE
	 * @param p_listLieu une liste d'entité Lieu
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateListLieu(Collection<Lieu> p_listLieu, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Sauve ou met à jour la liste d'entité Lieu passée en paramètre selon leur existence en base.
	 * @param p_listLieu une liste d'entité Lieu
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateListLieu(Collection<Lieu> p_listLieu, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException;

	/**
	 * Sauve, en base de données, l'entité Lieu passée en paramètre.
	 * Cette entité est considérée absente de la base de données avant son insertion.
	 * La cascade est CascadeSet.NONE
	 * @param p_oLieu une entité Lieu à sauvegarder.
	 * @param p_oCascadeSet La cascade à utiliser lors de la sauvegarde de l'élément.
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveLieu(Lieu p_oLieu, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Sauve, en base de données, chaque élément de la liste d'entité Lieu passée en paramètre.
	 * Ces éléments sont considérés absents de la base de données avant leur insertion.
	 * @param p_listLieu une liste d'entité  à sauvegarder.
	 * @param p_oCascadeSet La cascade à utiliser pour sauver chaque Lieu.
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveListLieu(Collection<Lieu> p_listLieu, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException;

	/**
	 * Met à jour, en base de données, l'entité  passée en paramètre.
	 * @param p_oLieu une entité 
	 * @param p_oCascadeSet ensemble de Cascades sur les entités	 
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void updateLieu(Lieu p_oLieu, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException;

	/**
	 * Met à jour, en base, chaque élément d'une liste de .
	 * @param p_listLieu une liste d'entité 
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void updateListLieu(Collection<Lieu> p_listLieu, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException;

	/**
	 * Supprime de la base de données l'entité Lieu passée en paramètre.
	 * La cascade est this.getDeleteCascade()
	 * @param p_oLieu une entité Lieu
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteLieu(Lieu p_oLieu, MContext p_oContext) throws DaoException;

	/**
	 * Supprime de la base de données l'entité Lieu passée en paramètre.
	 * La cascade doît être correctement indiquée.
	 * @param p_oLieu une entité Lieu
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteLieu(Lieu p_oLieu, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException;

	/**
	 * Supprime de la base de données l'entité Lieu correspondant aux paramètres.
	 * Cette suppression ne supprime pas les entités liés en cascade.
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteLieu(long p_lId, MContext p_oContext) throws DaoException;

	/**
	 * Supprime de la base de données la liste d'entité Lieu passée en paramètre.
	 * La cascade est CascadeSet.NONE
	 * @param p_listLieu une liste d'entité Lieu
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteListLieu(Collection<Lieu> p_listLieu, MContext p_oContext) throws DaoException;

	/**
	 * Supprime de la base de données la liste d'entité Lieu passée en paramètre.
	 * @param p_listLieu une liste d'entité Lieu
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteListLieu(Collection<Lieu> p_listLieu, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException;

	/**
	 * Retourne le nombre d'entité Lieu en base.
	 * Les blocs par défaut sont utilisés
	 * @param p_oContext contexte transactionnel
	 * @return le nombre d'entité Lieu en base
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public int getNbLieu(MContext p_oContext) throws DaoException;

	/**
	 * Retourne le nombre d'entité Lieu selon la requête.
	 * @param p_oDaoQuery requête
	 * @param p_oContext contexte transactionnel
	 * @return le nombre d'entité Lieu
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public int getNbLieu(DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException;

	/**
	 * Return delete cascade
	 * @return delete cascade
	 */
	public CascadeSet getDeleteCascade();

	//@non-generated-start[methods]
	//@non-generated-end
}
