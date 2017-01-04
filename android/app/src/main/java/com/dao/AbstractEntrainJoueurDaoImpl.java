package com.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import com.adeuza.movalysfwk.mf4jcommons.core.beans.Initializable;
import com.adeuza.movalysfwk.mobile.mf4android.database.sqlite.MDKSQLiteStatement;
import com.adeuza.movalysfwk.mobile.mf4android.jdbc.AndroidSQLiteConnection;
import com.adeuza.movalysfwk.mobile.mf4android.jdbc.AndroidSQLitePreparedStatement;
import com.adeuza.movalysfwk.mobile.mf4android.jdbc.AndroidSQLiteResultSet;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.context.MContext;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.context.MContextImpl;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.beans.CascadeSet;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.services.BeanLoader;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.AbstractEntityDao;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.CascadeOptim;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.CascadeOptimDefinition;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.DaoException;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.DaoQuery;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.DaoQueryImpl;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.DaoSession;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.Field;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.FieldType;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.query.conditions.SqlEqualsValueCondition;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.query.conditions.SqlInValueCondition;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.query.ResultSetReader;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.query.select.SqlFunctionSelectPart;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.query.SqlDelete;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.query.SqlFunction;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.query.SqlInsert;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.query.SqlQuery;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.query.SqlUpdate;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.query.StatementBinder;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.ResultSetReaderCallBack;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.SqlType;
import com.model.Entrainement;
import com.model.EntrainementFactory;
import com.model.EntrainJoueur;
import com.model.EntrainJoueurFactory;
import com.model.Joueur;
import com.model.JoueurFactory;

/**
 * 
 * <p>Classe de DAO : AbstractEntrainJoueurDaoImpl</p>
 */
public abstract class AbstractEntrainJoueurDaoImpl extends AbstractEntityDao<EntrainJoueur> implements Initializable {

	/**
	 * Requête de sélection des entités
	 */
	private SqlQuery selectQuery;

	/**
	 * Requête de comptage
	 */
	private SqlQuery countQuery;

	/**
	 * Requête d'insertion
	 */
	private SqlInsert insertQuery;

	/**
	 * Requête d'update
	 */
	private SqlUpdate updateQuery;

	/**
	 * Requête de suppression.
	 */
	private SqlDelete deleteQuery;

	/**
	 * Factory EntrainJoueurFactory
	 */
	protected EntrainJoueurFactory entrainJoueurFactory;

	/**
	 * Définition des optimisations des cascades
	 */
	protected CascadeOptimDefinition cascadeOptimDefinition;

	/**
	 * Initializes the private attributes of this DAO: factories and daos use by this dao.
	 */
	@Override
	public void initialize() {
		this.entrainJoueurFactory = BeanLoader.getInstance().getBean(EntrainJoueurFactory.class);

		selectQuery = new SqlQuery();
		selectQuery.addFieldToSelect(EntrainJoueurDao.ALIAS_NAME, EntrainJoueurField.JOUEURID, EntrainJoueurField.ENTRAINEMENTID);
		selectQuery.addToFrom(EntrainJoueurDao.TABLE_NAME, EntrainJoueurDao.ALIAS_NAME);

		countQuery = new SqlQuery();
		countQuery.addCountToSelect(EntrainJoueurField.JOUEURID, EntrainJoueurDao.ALIAS_NAME);
		countQuery.addToFrom(EntrainJoueurDao.TABLE_NAME, EntrainJoueurDao.ALIAS_NAME);

		insertQuery = new SqlInsert(EntrainJoueurDao.TABLE_NAME);

		insertQuery.addBindedField(EntrainJoueurField.JOUEURID);
		insertQuery.addBindedField(EntrainJoueurField.ENTRAINEMENTID);

		updateQuery = new SqlUpdate(EntrainJoueurDao.TABLE_NAME);

		updateQuery.addBindedField(EntrainJoueurField.JOUEURID);
		updateQuery.addBindedField(EntrainJoueurField.ENTRAINEMENTID);

		deleteQuery = new SqlDelete(EntrainJoueurDao.TABLE_NAME);

		cascadeOptimDefinition = new CascadeOptimDefinition(EntrainJoueurDao.PK_FIELDS, EntrainJoueurDao.ALIAS_NAME);

	}

	/**
	 * Initializes the private attributes of this DAO: factories and daos use by this dao.
	 * @param p_oContext the context to use
	 * @throws DaoException if any
	 */
	public void initialize(MContext p_oContext) throws DaoException {
		// Nothing to do
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTableName() {
		return EntrainJoueurDao.TABLE_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getAliasName() {
		return EntrainJoueurDao.ALIAS_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getEntityName() {
		return EntrainJoueur.ENTITY_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FieldType[] getPKFields() {
		return EntrainJoueurDao.PK_FIELDS;
	}

	/**
	 * Renvoie un clone de la requête de sélection des entités (pour qu'elle puisse être modifiée).
	 * @return un clone de la requête de sélection des entités.
	 */
	@Override
	public DaoQuery getSelectDaoQuery() {
		return new DaoQueryImpl(selectQuery.clone(), this.getEntityName());
	}

	/**
	 * Renvoie un clone de la requête de comptage (pour qu'elle puisse être modifiée).
	 * @return un clone de la requête de comptage.
	 */
	@Override
	public DaoQuery getCountDaoQuery() {
		return new DaoQueryImpl(countQuery.clone(), this.getEntityName());
	}

	/**
	 * Renvoie un clone de la requête d'insertion (pour qu'elle puisse être modifiée).
	 * @return un clone de la requête d'insertion.
	 */
	@Override
	public SqlInsert getInsertQuery() {
		return insertQuery.clone();
	}

	/**
	 * Renvoie un clone de la requête d'update (pour qu'elle puisse être modifiée).
	 * @return un clone de la requête d'update.
	 */
	@Override
	public SqlUpdate getUpdateQuery() {
		return updateQuery.clone();
	}

	/**
	 * Renvoie un clone de la requête de suppression (pour qu'elle puisse être modifiée).
	 * @return renvoie un clone de la requête de suppression.
	 */
	@Override
	public SqlDelete getDeleteQuery() {
		return deleteQuery.clone();
	}

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
	public EntrainJoueur getEntrainJoueur(long p_lJoueurId, long p_lEntrainementId, MContext p_oContext) throws DaoException {
		return this.getEntrainJoueur(p_lJoueurId, p_lEntrainementId, this.getSelectDaoQuery(), CascadeSet.NONE, new DaoSession(), p_oContext);
	}

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
	public EntrainJoueur getEntrainJoueur(long p_lJoueurId, long p_lEntrainementId, DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException {
		return this.getEntrainJoueur(p_lJoueurId, p_lEntrainementId, p_oDaoQuery, CascadeSet.NONE, new DaoSession(), p_oContext);
	}

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
			throws DaoException {
		return this.getEntrainJoueur(p_lJoueurId, p_lEntrainementId, this.getSelectDaoQuery(), p_oCascadeSet, new DaoSession(), p_oContext);
	}

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
			throws DaoException {
		return this.getEntrainJoueur(p_lJoueurId, p_lEntrainementId, this.getSelectDaoQuery(), CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

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
			MContext p_oContext) throws DaoException {
		return this.getEntrainJoueur(p_lJoueurId, p_lEntrainementId, p_oDaoQuery, p_oCascadeSet, new DaoSession(), p_oContext);
	}

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
			MContext p_oContext) throws DaoException {
		return this.getEntrainJoueur(p_lJoueurId, p_lEntrainementId, p_oDaoQuery, CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

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
			MContext p_oContext) throws DaoException {
		return this.getEntrainJoueur(p_lJoueurId, p_lEntrainementId, this.getSelectDaoQuery(), p_oCascadeSet, p_oDaoSession, p_oContext);
	}

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
			DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		EntrainJoueur r_oEntrainJoueur = null;
		p_oDaoQuery.getSqlQuery().addEqualsConditionToWhere(EntrainJoueurField.JOUEURID, EntrainJoueurDao.ALIAS_NAME, p_lJoueurId, SqlType.INTEGER);
		p_oDaoQuery.getSqlQuery().addEqualsConditionToWhere(EntrainJoueurField.ENTRAINEMENTID, EntrainJoueurDao.ALIAS_NAME, p_lEntrainementId,
				SqlType.INTEGER);
		AndroidSQLitePreparedStatement oStatement = p_oDaoQuery.prepareStatement(p_oContext);

		try {
			p_oDaoQuery.bindValues(oStatement);
			ResultSetReader oResultSetReader = new ResultSetReader(oStatement.executeQuery());
			try {
				while (oResultSetReader.next()) {
					r_oEntrainJoueur = this.valueObject(oResultSetReader, p_oDaoQuery, p_oDaoSession, p_oCascadeSet, p_oContext);
				}
			} finally {
				oResultSetReader.close();
			}
		} finally {
			oStatement.close();
		}

		return r_oEntrainJoueur;
	}

	/**
	 * Retourne la liste de toutes les entités EntrainJoueur.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueur(MContext p_oContext) throws DaoException {
		return this.getListEntrainJoueur(getSelectDaoQuery(), CascadeSet.NONE, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne la liste des entités EntrainJoueur selon la requête.
	 * La cascade est CascadeSet.NONE
	 * @param p_oDaoQuery requête
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueur(DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException {
		return this.getListEntrainJoueur(p_oDaoQuery, CascadeSet.NONE, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne la liste de toutes les entités EntrainJoueur.
	 * Les blocs par défaut sont utilisés
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueur(CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		return this.getListEntrainJoueur(getSelectDaoQuery(), p_oCascadeSet, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne la liste de toutes les entités EntrainJoueur.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueur(DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getListEntrainJoueur(getSelectDaoQuery(), CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne la liste des entités EntrainJoueur selon la requête.
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueur(DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		return this.getListEntrainJoueur(p_oDaoQuery, p_oCascadeSet, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne la liste des entités EntrainJoueur selon la requête.
	 * La cascade est CascadeSet.NONE
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueur(DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getListEntrainJoueur(p_oDaoQuery, CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne la liste de toutes les entités EntrainJoueur.
	 * Les blocs par défaut sont utilisés
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<EntrainJoueur> getListEntrainJoueur(CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getListEntrainJoueur(getSelectDaoQuery(), p_oCascadeSet, p_oDaoSession, p_oContext);
	}

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
			throws DaoException {

		return this.getList(p_oDaoQuery, p_oCascadeSet, p_oDaoSession, p_oContext);
	}

	/**
	 * Sauve ou met à jour l'entité EntrainJoueur passée en paramètre selon son existence en base.
	 * La cascade est CascadeSet.NONE
	 * @param p_oEntrainJoueur une entité EntrainJoueur
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateEntrainJoueur(EntrainJoueur p_oEntrainJoueur, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		this.saveOrUpdateEntrainJoueur(p_oEntrainJoueur, CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Sauve ou met à jour l'entité EntrainJoueur passée en paramètre selon son existence en base.
	 * @param p_oEntrainJoueur une entité EntrainJoueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateEntrainJoueur(EntrainJoueur p_oEntrainJoueur, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		this.saveOrUpdateEntrainJoueur(p_oEntrainJoueur, p_oCascadeSet, new DaoSession(), p_oContext);
	}

	/**
	 * Sauve ou met à jour l'entité EntrainJoueur passée en paramètre selon son existence en base.
	 * @param p_oEntrainJoueur une entité EntrainJoueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateEntrainJoueur(EntrainJoueur p_oEntrainJoueur, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {

		if (!p_oDaoSession.isAlreadySaved(EntrainJoueur.ENTITY_NAME, p_oEntrainJoueur)) {

			this.validateBean(p_oEntrainJoueur, p_oContext);
			boolean bHaveErrorMessage = p_oContext.getMessages().hasErrors();

			if (!bHaveErrorMessage) {

				if (this.exist(p_oEntrainJoueur, p_oCascadeSet, p_oContext)) {
					this.updateEntrainJoueur(p_oEntrainJoueur, p_oCascadeSet, p_oDaoSession, p_oContext);
				} else {

					this.saveEntrainJoueur(p_oEntrainJoueur, p_oCascadeSet, p_oDaoSession, p_oContext);
				}

			}
		}
	}

	/**
	 * Sauve ou met à jour laliste d'entité EntrainJoueur passée en paramètre selon leur existence en base.
	 * La cascade est CascadeSet.NONE
	 * @param p_listEntrainJoueur une liste d'entité EntrainJoueur
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateListEntrainJoueur(Collection<EntrainJoueur> p_listEntrainJoueur, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {
		this.saveOrUpdateListEntrainJoueur(p_listEntrainJoueur, CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Sauve ou met à jour laliste d'entité EntrainJoueur passée en paramètre selon leur existence en base.
	 * @param p_listEntrainJoueur une liste d'entité EntrainJoueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateListEntrainJoueur(Collection<EntrainJoueur> p_listEntrainJoueur, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession,
			MContext p_oContext) throws DaoException {

		this.validateBeanList(p_listEntrainJoueur, p_oContext);
		boolean bHaveErrorMessage = p_oContext.getMessages().hasErrors();

		if (!bHaveErrorMessage) {
			List<EntrainJoueur> listSave = new ArrayList<EntrainJoueur>();
			List<EntrainJoueur> listUpdate = new ArrayList<EntrainJoueur>();
			for (EntrainJoueur oEntrainJoueur : p_listEntrainJoueur) {
				if (this.exist(oEntrainJoueur, p_oCascadeSet, p_oContext)) {
					listUpdate.add(oEntrainJoueur);
				} else {

					listSave.add(oEntrainJoueur);
				}
			}

			if (!listSave.isEmpty()) {
				this.saveListEntrainJoueur(listSave, p_oCascadeSet, p_oDaoSession, p_oContext);
			}
			if (!listUpdate.isEmpty()) {
				this.updateListEntrainJoueur(listUpdate, p_oCascadeSet, p_oDaoSession, p_oContext);
			}
		}
	}

	/**
	 * Supprime l'entité EntrainJoueur passée en paramètre de la base de données.
	 * La cascade est this.getDeleteCascade()
	 * @param p_oEntrainJoueur une entité EntrainJoueur
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteEntrainJoueur(EntrainJoueur p_oEntrainJoueur, MContext p_oContext) throws DaoException {
		this.deleteEntrainJoueur(p_oEntrainJoueur, this.getDeleteCascade(), p_oContext);
	}

	/**
	 * Supprime l'entité EntrainJoueur passée en paramètre de la base de données.
	 * @param p_oEntrainJoueur une entité EntrainJoueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteEntrainJoueur(EntrainJoueur p_oEntrainJoueur, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {

		SqlDelete oSqlDelete = getDeleteQuery();
		oSqlDelete.addEqualsConditionToWhere(EntrainJoueurField.JOUEURID, p_oEntrainJoueur.getJoueurId(), SqlType.INTEGER);
		oSqlDelete.addEqualsConditionToWhere(EntrainJoueurField.ENTRAINEMENTID, p_oEntrainJoueur.getEntrainementId(), SqlType.INTEGER);

		AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
		AndroidSQLitePreparedStatement oStatement = oConnection.prepareStatement(oSqlDelete.toSql(p_oContext));
		try {
			oSqlDelete.bindValues(oStatement);
			oStatement.executeUpdate();
		} finally {
			oStatement.close();
		}

	}

	/**
	 * Supprime l'entité EntrainJoueur correspondant aux paramètres de la base de données.
	 * Cette suppression ne supprime pas les entités liés en cascade.
	 * 
	 * @param p_lJoueurId un paramètre de la clé primaire de type long
	 * @param p_lEntrainementId un paramètre de la clé primaire de type long
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteEntrainJoueur(long p_lJoueurId, long p_lEntrainementId, MContext p_oContext) throws DaoException {

		SqlDelete oSqlDelete = getDeleteQuery();
		oSqlDelete.addEqualsConditionToWhere(EntrainJoueurField.JOUEURID, p_lJoueurId, SqlType.INTEGER);
		oSqlDelete.addEqualsConditionToWhere(EntrainJoueurField.ENTRAINEMENTID, p_lEntrainementId, SqlType.INTEGER);
		AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
		AndroidSQLitePreparedStatement oStatement = oConnection.prepareStatement(oSqlDelete.toSql(p_oContext));
		try {
			oSqlDelete.bindValues(oStatement);
			oStatement.executeUpdate();
		} finally {
			oStatement.close();
		}
	}

	/**
	 * Supprime de la base de données la liste d'entité EntrainJoueur passée en paramètre.
	 * La cascade est CascadeSet.NONE
	 * @param p_listEntrainJoueur une liste d'entité EntrainJoueur
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteListEntrainJoueur(Collection<EntrainJoueur> p_listEntrainJoueur, MContext p_oContext) throws DaoException {
		this.deleteListEntrainJoueur(p_listEntrainJoueur, CascadeSet.NONE, p_oContext);
	}

	/**
	 * Supprime de la base de données la liste d'entité EntrainJoueur passée en paramètre.
	 * @param p_listEntrainJoueur une liste d'entité EntrainJoueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteListEntrainJoueur(Collection<EntrainJoueur> p_listEntrainJoueur, CascadeSet p_oCascadeSet, MContext p_oContext)
			throws DaoException {

		if (!p_listEntrainJoueur.isEmpty()) {

			SqlDelete oSqlDelete = getDeleteQuery();
			SqlEqualsValueCondition oSqlEqualsValueCondition1 = oSqlDelete.addEqualsConditionToWhere(EntrainJoueurField.JOUEURID, SqlType.INTEGER);
			SqlEqualsValueCondition oSqlEqualsValueCondition2 = oSqlDelete.addEqualsConditionToWhere(EntrainJoueurField.ENTRAINEMENTID,
					SqlType.INTEGER);

			AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
			AndroidSQLitePreparedStatement oStatement = oConnection.prepareStatement(oSqlDelete.toSql(p_oContext));
			try {
				for (EntrainJoueur oEntrainJoueur : p_listEntrainJoueur) {
					oSqlEqualsValueCondition1.setValue(oEntrainJoueur.getJoueurId());
					oSqlEqualsValueCondition2.setValue(oEntrainJoueur.getEntrainementId());

					oSqlDelete.bindValues(oStatement);
					oStatement.addBatch();
				}

				oStatement.executeBatch();
			} finally {
				oStatement.close();
			}

		}
	}

	/**
	 * Retourne le nombre d'entité EntrainJoueur en base.
	 * Les blocs par défaut sont utilisés
	 * @param p_oContext contexte transactionnel
	 * @return le nombre d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public int getNbEntrainJoueur(MContext p_oContext) throws DaoException {
		return this.getNbEntrainJoueur(getCountDaoQuery(), p_oContext);
	}

	/**
	 * Retourne le nombre d'entité EntrainJoueur selon la requête.
	 * @param p_oDaoQuery requête
	 * @param p_oContext contexte transactionnel
	 * @return le nombre d'entité EntrainJoueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public int getNbEntrainJoueur(DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException {
		return this.getNb(p_oDaoQuery, p_oContext);
	}

	/**
	 * Return delete cascade
	 * @return delete cascade
	 */
	public CascadeSet getDeleteCascade() {
		return CascadeSet.NONE;
	}

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
	public List<EntrainJoueur> getListEntrainJoueurByJoueurId(long p_lJoueurId, MContext p_oContext) throws DaoException {
		return this.getListEntrainJoueurByJoueurId(p_lJoueurId, this.getSelectDaoQuery(), CascadeSet.NONE, new DaoSession(), p_oContext);
	}

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
	public List<EntrainJoueur> getListEntrainJoueurByJoueurId(long p_lJoueurId, DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException {
		return this.getListEntrainJoueurByJoueurId(p_lJoueurId, p_oDaoQuery, CascadeSet.NONE, new DaoSession(), p_oContext);
	}

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
	public List<EntrainJoueur> getListEntrainJoueurByJoueurId(long p_lJoueurId, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		return this.getListEntrainJoueurByJoueurId(p_lJoueurId, this.getSelectDaoQuery(), p_oCascadeSet, new DaoSession(), p_oContext);
	}

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
	public List<EntrainJoueur> getListEntrainJoueurByJoueurId(long p_lJoueurId, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getListEntrainJoueurByJoueurId(p_lJoueurId, this.getSelectDaoQuery(), CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

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
			throws DaoException {
		return this.getListEntrainJoueurByJoueurId(p_lJoueurId, p_oDaoQuery, p_oCascadeSet, new DaoSession(), p_oContext);
	}

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
			throws DaoException {
		return this.getListEntrainJoueurByJoueurId(p_lJoueurId, p_oDaoQuery, CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

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
			MContext p_oContext) throws DaoException {
		return this.getListEntrainJoueurByJoueurId(p_lJoueurId, this.getSelectDaoQuery(), p_oCascadeSet, p_oDaoSession, p_oContext);
	}

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
			DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {

		p_oDaoQuery.getSqlQuery().addEqualsConditionToWhere(EntrainJoueurField.JOUEURID, EntrainJoueurDao.ALIAS_NAME, p_lJoueurId, SqlType.INTEGER);

		return this.getList(p_oDaoQuery, p_oCascadeSet, p_oDaoSession, p_oContext);
	}

	/**
	 * Supprime de la base de données l'entité EntrainJoueur selon les paramètres.
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lJoueurId un paramètre de type long
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteEntrainJoueurByJoueurId(long p_lJoueurId, MContext p_oContext) throws DaoException {
		this.deleteEntrainJoueurByJoueurId(p_lJoueurId, CascadeSet.NONE, p_oContext);
	}

	/**
	 * Supprime de la base de données l'entité EntrainJoueur selon les paramètres.
	 * 
	 * 
	 * @param p_lJoueurId un paramètre de type long
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteEntrainJoueurByJoueurId(long p_lJoueurId, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {

		AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
		SqlDelete oSqlDelete = getDeleteQuery();
		oSqlDelete.addEqualsConditionToWhere(EntrainJoueurField.JOUEURID, p_lJoueurId, SqlType.INTEGER);
		AndroidSQLitePreparedStatement oStatement = oConnection.prepareStatement(oSqlDelete.toSql(p_oContext));
		try {

			oSqlDelete.bindValues(oStatement);
			oStatement.executeUpdate();
		} finally {
			oStatement.close();
		}
	}

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
	public List<EntrainJoueur> getListEntrainJoueurByEntrainementId(long p_lEntrainementId, MContext p_oContext) throws DaoException {
		return this.getListEntrainJoueurByEntrainementId(p_lEntrainementId, this.getSelectDaoQuery(), CascadeSet.NONE, new DaoSession(), p_oContext);
	}

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
			throws DaoException {
		return this.getListEntrainJoueurByEntrainementId(p_lEntrainementId, p_oDaoQuery, CascadeSet.NONE, new DaoSession(), p_oContext);
	}

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
			throws DaoException {
		return this.getListEntrainJoueurByEntrainementId(p_lEntrainementId, this.getSelectDaoQuery(), p_oCascadeSet, new DaoSession(), p_oContext);
	}

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
			throws DaoException {
		return this.getListEntrainJoueurByEntrainementId(p_lEntrainementId, this.getSelectDaoQuery(), CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

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
			MContext p_oContext) throws DaoException {
		return this.getListEntrainJoueurByEntrainementId(p_lEntrainementId, p_oDaoQuery, p_oCascadeSet, new DaoSession(), p_oContext);
	}

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
			MContext p_oContext) throws DaoException {
		return this.getListEntrainJoueurByEntrainementId(p_lEntrainementId, p_oDaoQuery, CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

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
			MContext p_oContext) throws DaoException {
		return this.getListEntrainJoueurByEntrainementId(p_lEntrainementId, this.getSelectDaoQuery(), p_oCascadeSet, p_oDaoSession, p_oContext);
	}

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
			DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {

		p_oDaoQuery.getSqlQuery().addEqualsConditionToWhere(EntrainJoueurField.ENTRAINEMENTID, EntrainJoueurDao.ALIAS_NAME, p_lEntrainementId,
				SqlType.INTEGER);

		return this.getList(p_oDaoQuery, p_oCascadeSet, p_oDaoSession, p_oContext);
	}

	/**
	 * Supprime de la base de données l'entité EntrainJoueur selon les paramètres.
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lEntrainementId un paramètre de type long
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteEntrainJoueurByEntrainementId(long p_lEntrainementId, MContext p_oContext) throws DaoException {
		this.deleteEntrainJoueurByEntrainementId(p_lEntrainementId, CascadeSet.NONE, p_oContext);
	}

	/**
	 * Supprime de la base de données l'entité EntrainJoueur selon les paramètres.
	 * 
	 * 
	 * @param p_lEntrainementId un paramètre de type long
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteEntrainJoueurByEntrainementId(long p_lEntrainementId, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {

		AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
		SqlDelete oSqlDelete = getDeleteQuery();
		oSqlDelete.addEqualsConditionToWhere(EntrainJoueurField.ENTRAINEMENTID, p_lEntrainementId, SqlType.INTEGER);
		AndroidSQLitePreparedStatement oStatement = oConnection.prepareStatement(oSqlDelete.toSql(p_oContext));
		try {

			oSqlDelete.bindValues(oStatement);
			oStatement.executeUpdate();
		} finally {
			oStatement.close();
		}
	}

	/**
	 * Sauvegarde l'entité EntrainJoueur avec les paramètres de la clé primaire.
	 * 
	 * @param p_lJoueurId un paramètre de la clé primaire de type long
	 * @param p_lEntrainementId un paramètre de la clé primaire de type long
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveEntrainJoueur(long p_lJoueurId, long p_lEntrainementId, MContext p_oContext) throws DaoException {

		SqlInsert oSqlInsert = this.getInsertQuery();
		AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
		MDKSQLiteStatement oStatement = oConnection.compileStatement(oSqlInsert.toSql(p_oContext));
		try {
			oStatement.bindLong(p_lJoueurId);
			oStatement.bindLong(p_lEntrainementId);

			oStatement.executeUpdate();
		} finally {
			oStatement.close();
		}
	}

	/**
	 * Sauvegarde l'entité EntrainJoueur.
	 * @param p_oJoueurs une entité Joueur
	 * @param p_listEntrainements une liste d'entité Entrainement
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveEntrainements(Joueur p_oJoueurs, Collection<Entrainement> p_listEntrainements, MContext p_oContext) throws DaoException {
		this.saveEntrainements(p_oJoueurs, p_listEntrainements, new DaoSession(), p_oContext);
	}

	/**
	 * Sauvegarde l'entité EntrainJoueur.
	 * 
	 * @param p_oJoueurs une entité Joueur
	 * @param p_listEntrainements une liste d'entité Entrainement.
	 * @param p_oDaoSession session Dao.
	 * @param p_oContext contexte transactionnel.
	 * 
	 * @throws DaoException déclenchée si une exception technique survient.
	 */
	public void saveEntrainements(Joueur p_oJoueurs, Collection<Entrainement> p_listEntrainements, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {

		Collection<EntrainJoueur> listToDelete = this.getListEntrainJoueurByJoueurId(p_oJoueurs.getId(), CascadeSet.NONE, new DaoSession(),
				p_oContext);
		Collection<EntrainJoueur> listToAdd = new ArrayList<EntrainJoueur>();

		Iterator<EntrainJoueur> iterExistingEntrainJoueur = null;
		EntrainJoueur oExistingEntrainJoueur = null;
		boolean bExisting = true;
		for (Entrainement oEntrainements : p_listEntrainements) {
			bExisting = false;
			iterExistingEntrainJoueur = listToDelete.iterator();
			while (iterExistingEntrainJoueur.hasNext() && !bExisting) {
				oExistingEntrainJoueur = iterExistingEntrainJoueur.next();
				bExisting = oExistingEntrainJoueur.getEntrainementId() == oEntrainements.getId();
				if (bExisting) {
					iterExistingEntrainJoueur.remove();
				}
			}
			if (!bExisting) {
				EntrainJoueur oJoueurs = this.entrainJoueurFactory.createInstance();
				listToAdd.add(oJoueurs);

				oJoueurs.setJoueurId(p_oJoueurs.getId());
				oJoueurs.setEntrainementId(oEntrainements.getId());
			}
		}

		if (!listToAdd.isEmpty()) {
			this.saveListEntrainJoueur(listToAdd, CascadeSet.NONE, p_oDaoSession, p_oContext);
		}
		if (!listToDelete.isEmpty()) {
			this.deleteListEntrainJoueur(listToDelete, CascadeSet.NONE, p_oContext);
		}
	}

	/**
	 * Sauvegarde l'entité EntrainJoueur.
	 * @param p_oEntrainements une entité Entrainement
	 * @param p_listJoueurs une liste d'entité Joueur
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveJoueurs(Entrainement p_oEntrainements, Collection<Joueur> p_listJoueurs, MContext p_oContext) throws DaoException {
		this.saveJoueurs(p_oEntrainements, p_listJoueurs, new DaoSession(), p_oContext);
	}

	/**
	 * Sauvegarde l'entité EntrainJoueur.
	 * 
	 * @param p_oEntrainements une entité Entrainement
	 * @param p_listJoueurs une liste d'entité Joueur.
	 * @param p_oDaoSession session Dao.
	 * @param p_oContext contexte transactionnel.
	 * 
	 * @throws DaoException déclenchée si une exception technique survient.
	 */
	public void saveJoueurs(Entrainement p_oEntrainements, Collection<Joueur> p_listJoueurs, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {

		Collection<EntrainJoueur> listToDelete = this.getListEntrainJoueurByEntrainementId(p_oEntrainements.getId(), CascadeSet.NONE,
				new DaoSession(), p_oContext);
		Collection<EntrainJoueur> listToAdd = new ArrayList<EntrainJoueur>();

		Iterator<EntrainJoueur> iterExistingEntrainJoueur = null;
		EntrainJoueur oExistingEntrainJoueur = null;
		boolean bExisting = true;
		for (Joueur oJoueurs : p_listJoueurs) {
			bExisting = false;
			iterExistingEntrainJoueur = listToDelete.iterator();
			while (iterExistingEntrainJoueur.hasNext() && !bExisting) {
				oExistingEntrainJoueur = iterExistingEntrainJoueur.next();
				bExisting = oExistingEntrainJoueur.getJoueurId() == oJoueurs.getId();
				if (bExisting) {
					iterExistingEntrainJoueur.remove();
				}
			}
			if (!bExisting) {
				EntrainJoueur oEntrainements = this.entrainJoueurFactory.createInstance();
				listToAdd.add(oEntrainements);

				oEntrainements.setJoueurId(oJoueurs.getId());
				oEntrainements.setEntrainementId(p_oEntrainements.getId());
			}
		}

		if (!listToAdd.isEmpty()) {
			this.saveListEntrainJoueur(listToAdd, CascadeSet.NONE, p_oDaoSession, p_oContext);
		}
		if (!listToDelete.isEmpty()) {
			this.deleteListEntrainJoueur(listToDelete, CascadeSet.NONE, p_oContext);
		}
	}

	/**
	 * Saves a EntrainJoueur entity with the cascade sent as parameter
	 * @param p_oEntrainJoueur une entité EntrainJoueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveEntrainJoueur(EntrainJoueur p_oEntrainJoueur, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {

		if (!p_oDaoSession.isAlreadySaved(EntrainJoueur.ENTITY_NAME, p_oEntrainJoueur)) {
			p_oDaoSession.markAsSaved(EntrainJoueur.ENTITY_NAME, p_oEntrainJoueur);

			if (!p_oContext.getMessages().hasErrors()) {
				SqlInsert oSqlInsert = this.getInsertQuery();
				AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
				MDKSQLiteStatement oStatement = oConnection.compileStatement(oSqlInsert.toSql(p_oContext));
				try {
					bindInsert(p_oEntrainJoueur, oStatement, p_oContext);
					oStatement.executeUpdate();

				} finally {
					oStatement.close();
				}
			}

		}
	}

	/**
	 * Saves a list of EntrainJoueur entities with the cascade sent as parameter
	 * @param p_listEntrainJoueur une liste d'entité EntrainJoueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveListEntrainJoueur(Collection<EntrainJoueur> p_listEntrainJoueur, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession,
			MContext p_oContext) throws DaoException {

		Collection<EntrainJoueur> listEntrainJoueurToSave = p_oDaoSession.getEntitiesToPersist(EntrainJoueur.ENTITY_NAME, p_listEntrainJoueur, true);

		if (!p_oContext.getMessages().hasErrors()) {
			SqlInsert oSqlInsert = this.getInsertQuery();
			AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
			MDKSQLiteStatement oStatement = oConnection.compileStatement(oSqlInsert.toSql(p_oContext));
			try {
				for (EntrainJoueur oEntrainJoueur : listEntrainJoueurToSave) {
					bindInsert(oEntrainJoueur, oStatement, p_oContext);
					oStatement.executeInsert();
				}

			} finally {
				oStatement.close();
			}
		}

	}

	/**
	 * Updates the given entity in database
	 * @param p_oEntrainJoueur une entité EntrainJoueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void updateEntrainJoueur(EntrainJoueur p_oEntrainJoueur, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {

		if (!p_oDaoSession.isAlreadySaved(EntrainJoueur.ENTITY_NAME, p_oEntrainJoueur)) {
			p_oDaoSession.markAsSaved(EntrainJoueur.ENTITY_NAME, p_oEntrainJoueur);

			if (!p_oContext.getMessages().hasErrors()) {

				SqlUpdate oSqlUpdate = this.getUpdateQuery();
				oSqlUpdate.addEqualsConditionToWhere(EntrainJoueurField.JOUEURID, SqlType.INTEGER);
				oSqlUpdate.addEqualsConditionToWhere(EntrainJoueurField.ENTRAINEMENTID, SqlType.INTEGER);
				AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
				MDKSQLiteStatement oStatement = oConnection.compileStatement(oSqlUpdate.toSql(p_oContext));
				try {
					bindUpdate(p_oEntrainJoueur, oStatement, p_oContext);
					oStatement.executeUpdate();

				} finally {
					oStatement.close();
				}
			}

		}
	}

	/**
	 * Updates the given list of entities in the database
	 * @param p_listEntrainJoueur une liste d'entité EntrainJoueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session	 
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void updateListEntrainJoueur(Collection<EntrainJoueur> p_listEntrainJoueur, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession,
			MContext p_oContext) throws DaoException {

		Collection<EntrainJoueur> listEntrainJoueurToUpdate = p_oDaoSession
				.getEntitiesToPersist(EntrainJoueur.ENTITY_NAME, p_listEntrainJoueur, true);

		if (!p_oContext.getMessages().hasErrors()) {

			SqlUpdate oSqlUpdate = this.getUpdateQuery();
			oSqlUpdate.addEqualsConditionToWhere(EntrainJoueurField.JOUEURID, SqlType.INTEGER);
			oSqlUpdate.addEqualsConditionToWhere(EntrainJoueurField.ENTRAINEMENTID, SqlType.INTEGER);
			AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
			MDKSQLiteStatement oStatement = oConnection.compileStatement(oSqlUpdate.toSql(p_oContext));
			try {
				for (EntrainJoueur oEntrainJoueur : listEntrainJoueurToUpdate) {
					bindUpdate(oEntrainJoueur, oStatement, p_oContext);
					oStatement.executeUpdate();
				}

			} finally {
				oStatement.close();
			}
		}

	}

	/**
	 * Returns true if the entity given in parameter exists
	 * @param p_oEntrainJoueur une entité EntrainJoueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités	 
	 * @param p_oContext contexte transactionnel
	 * @return un boolean indiquant si l'entité existe en base
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	protected boolean exist(EntrainJoueur p_oEntrainJoueur, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		boolean r_bExist = false;
		DaoQuery oQuery = this.getSelectDaoQuery();
		oQuery.getSqlQuery().addEqualsConditionToWhere(EntrainJoueurField.JOUEURID, EntrainJoueurDao.ALIAS_NAME, p_oEntrainJoueur.getJoueurId(),
				SqlType.INTEGER);
		oQuery.getSqlQuery().addEqualsConditionToWhere(EntrainJoueurField.ENTRAINEMENTID, EntrainJoueurDao.ALIAS_NAME,
				p_oEntrainJoueur.getEntrainementId(), SqlType.INTEGER);
		AndroidSQLitePreparedStatement oStatement = oQuery.prepareStatement(p_oContext);

		try {
			oQuery.bindValues(oStatement);
			AndroidSQLiteResultSet oResultSet = oStatement.executeQuery();
			try {
				if (oResultSet.next()) {
					r_bExist = true;
				}
			} finally {
				oResultSet.close();
			}
		} finally {
			oStatement.close();
		}
		return r_bExist;
	}

	/**
	 * Returns an instance of a EntrainJoueur object from a given ResultSetReader	 * @param p_oResultSetReader résultat de la requête
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return l'entité EntrainJoueur.
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	@Override
	protected EntrainJoueur valueObject(ResultSetReader p_oResultSetReader, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, CascadeSet p_oCascadeSet,
			MContext p_oContext) throws DaoException {

		EntrainJoueur r_oEntrainJoueur = this.entrainJoueurFactory.createInstance();
		r_oEntrainJoueur.setJoueurId(p_oResultSetReader.getLong());
		r_oEntrainJoueur.setEntrainementId(p_oResultSetReader.getLong());
		String sEntityId = r_oEntrainJoueur.idToString();
		EntrainJoueur oCachedEntrainJoueur = (EntrainJoueur) p_oDaoSession.getFromCache(EntrainJoueur.ENTITY_NAME, sEntityId);
		if (oCachedEntrainJoueur == null) {
			p_oDaoSession.addToCache(EntrainJoueur.ENTITY_NAME, sEntityId, r_oEntrainJoueur);
		} else {
			r_oEntrainJoueur = oCachedEntrainJoueur;
		}

		p_oDaoQuery.doResultSetCustomRead(r_oEntrainJoueur, p_oResultSetReader, p_oDaoSession, p_oCascadeSet);

		return r_oEntrainJoueur;
	}

	/**
	 * Returns an instance of a EntrainJoueur object from a given ResultSetReader	 * @param p_oResultSetReader reader de resultset
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oCascadeOptim optimiseur de l'éxécution des cascades
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException.
	 * @return l'entité EntrainJoueur.
	 */
	@Override
	protected EntrainJoueur valueObject(ResultSetReader p_oResultSetReader, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, CascadeSet p_oCascadeSet,
			CascadeOptim p_oCascadeOptim, MContext p_oContext) throws DaoException {

		EntrainJoueur r_oEntrainJoueur = this.entrainJoueurFactory.createInstance();

		r_oEntrainJoueur.setJoueurId(p_oResultSetReader.getLong());
		r_oEntrainJoueur.setEntrainementId(p_oResultSetReader.getLong());

		String sEntityId = r_oEntrainJoueur.idToString();
		EntrainJoueur oCachedEntrainJoueur = (EntrainJoueur) p_oDaoSession.getFromCache(EntrainJoueur.ENTITY_NAME, sEntityId);
		if (oCachedEntrainJoueur == null) {
			p_oDaoSession.addToCache(EntrainJoueur.ENTITY_NAME, sEntityId, r_oEntrainJoueur);
			p_oCascadeOptim.registerEntity(sEntityId, r_oEntrainJoueur, r_oEntrainJoueur.getJoueurId(), r_oEntrainJoueur.getEntrainementId());
		} else {
			r_oEntrainJoueur = oCachedEntrainJoueur;
		}

		p_oDaoQuery.doResultSetCustomRead(r_oEntrainJoueur, p_oResultSetReader, p_oDaoSession, p_oCascadeSet);

		return r_oEntrainJoueur;
	}

	/**
	 * Bind un prepareStatement d'insertion
	 * @param p_oEntrainJoueur une entité EntrainJoueur à insérer
	 * @param p_oPreparedStatement requête SQL précompilée
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	@Override
	protected void bindInsert(EntrainJoueur p_oEntrainJoueur, MDKSQLiteStatement p_oStatement, MContext p_oContext) throws DaoException {
		p_oStatement.bindLong(p_oEntrainJoueur.getJoueurId());
		p_oStatement.bindLong(p_oEntrainJoueur.getEntrainementId());

	}

	/**
	 * Bind un preparedStatement de mise à jour
	 * @param p_oEntrainJoueur une entité EntrainJoueur à mettre à jour
	 * @param p_oPreparedStatement requête SQL précompilée
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	@Override
	protected void bindUpdate(EntrainJoueur p_oEntrainJoueur, MDKSQLiteStatement p_oStatement, MContext p_oContext) throws DaoException {
		p_oStatement.bindLong(p_oEntrainJoueur.getJoueurId());
		p_oStatement.bindLong(p_oEntrainJoueur.getEntrainementId());

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CascadeOptim createCascadeOptim() {
		return this.cascadeOptimDefinition.createCascadeOptim();
	}

	/**
	 * Permet de traiter les cascades pour les méthodes 'getList()'
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oCascadeOptim optimiseur de l'éxécution des cascades
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	@Override
	protected void postTraitementCascade(CascadeSet p_oCascadeSet, CascadeOptim p_oCascadeOptim, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession,
			MContext p_oContext) throws DaoException {

	}

	/**
	 * Permet de traiter les cascades pour les méthodes 'fill()'
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oCascadeOptim optimiseur de l'éxécution des cascades
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	protected void postTraitementFillCascade(CascadeSet p_oCascadeSet, CascadeOptim p_oCascadeOptim, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession,
			MContext p_oContext) throws DaoException {

	}

}
