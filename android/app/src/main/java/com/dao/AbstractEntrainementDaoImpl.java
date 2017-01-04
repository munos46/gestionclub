package com.dao;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
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
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.query.conditions.SqlEqualsFieldCondition;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.query.conditions.SqlEqualsValueCondition;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.query.conditions.SqlInValueCondition;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.query.conditions.SqlNotInValueCondition;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.query.joins.SqlTableInnerJoin;
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
import com.model.EntrainementCascade;
import com.model.EntrainementFactory;
import com.model.EntrainJoueur;
import com.model.EntrainJoueurFactory;
import com.model.Joueur;
import com.model.JoueurFactory;
import com.model.Lieu;
import com.model.LieuFactory;

/**
 * 
 * <p>Classe de DAO : AbstractEntrainementDaoImpl</p>
 */
public abstract class AbstractEntrainementDaoImpl extends AbstractEntityDao<Entrainement> implements Initializable {

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
	 * Factory EntrainementFactory
	 */
	protected EntrainementFactory entrainementFactory;

	/**
	 * Dao JoueurDao
	 */
	protected JoueurDao joueurDao;

	/**
	 * Dao LieuDao
	 */
	protected LieuDao lieuDao;

	/**
	 * Dao EntrainJoueurDao
	 */
	protected EntrainJoueurDao entrainJoueurDao;

	/**
	 * Factory JoueurFactory
	 */
	protected JoueurFactory joueurFactory;

	/**
	 * Factory LieuFactory
	 */
	protected LieuFactory lieuFactory;

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
		this.entrainementFactory = BeanLoader.getInstance().getBean(EntrainementFactory.class);

		this.joueurDao = BeanLoader.getInstance().getBean(JoueurDao.class);
		this.joueurFactory = BeanLoader.getInstance().getBean(JoueurFactory.class);

		this.lieuDao = BeanLoader.getInstance().getBean(LieuDao.class);
		this.lieuFactory = BeanLoader.getInstance().getBean(LieuFactory.class);

		this.entrainJoueurDao = BeanLoader.getInstance().getBean(EntrainJoueurDao.class);
		this.entrainJoueurFactory = BeanLoader.getInstance().getBean(EntrainJoueurFactory.class);

		selectQuery = new SqlQuery();
		selectQuery.addFieldToSelect(EntrainementDao.ALIAS_NAME, EntrainementField.ID, EntrainementField.DATEHEURE, EntrainementField.LIEUID);
		selectQuery.addToFrom(EntrainementDao.TABLE_NAME, EntrainementDao.ALIAS_NAME);

		countQuery = new SqlQuery();
		countQuery.addCountToSelect(EntrainementField.ID, EntrainementDao.ALIAS_NAME);
		countQuery.addToFrom(EntrainementDao.TABLE_NAME, EntrainementDao.ALIAS_NAME);

		insertQuery = new SqlInsert(EntrainementDao.TABLE_NAME);

		insertQuery.addBindedField(EntrainementField.ID);
		insertQuery.addBindedField(EntrainementField.DATEHEURE);
		insertQuery.addBindedField(EntrainementField.LIEUID);

		updateQuery = new SqlUpdate(EntrainementDao.TABLE_NAME);

		updateQuery.addBindedField(EntrainementField.ID);
		updateQuery.addBindedField(EntrainementField.DATEHEURE);
		updateQuery.addBindedField(EntrainementField.LIEUID);

		deleteQuery = new SqlDelete(EntrainementDao.TABLE_NAME);

		cascadeOptimDefinition = new CascadeOptimDefinition(EntrainementDao.PK_FIELDS, EntrainementDao.ALIAS_NAME);

		cascadeOptimDefinition.registerCascade(EntrainementCascade.LIEU, LieuDao.PK_FIELDS, LieuDao.ALIAS_NAME);
		cascadeOptimDefinition.registerCascade(EntrainementCascade.JOUEURS, EntrainJoueurDao.FK_ENTRAINEMENTS, JoueurDao.ALIAS_NAME);

		cascadeOptimDefinition.registerJoinClass(EntrainementCascade.JOUEURS);
	}

	/**
	 * Initializes the private attributes of this DAO: factories and daos use by this dao.
	 * @param p_oContext the context to use
	 * @throws DaoException if any
	 */
	public void initialize(MContext p_oContext) throws DaoException {
		SqlQuery oSelect = new SqlQuery();
		oSelect.addFunctionToSelect(new SqlFunctionSelectPart(SqlFunction.MIN, EntrainementField.ID, EntrainementDao.ALIAS_NAME));
		oSelect.addToFrom(EntrainementDao.TABLE_NAME, EntrainementDao.ALIAS_NAME);
		this.lastNewId = (Long) this.genericSelect(new DaoQueryImpl(oSelect, this.getEntityName()), p_oContext, new ResultSetReaderCallBack() {
			@Override
			public Object doRead(AndroidSQLiteResultSet p_oResultSet) throws DaoException {
				long r_lMinId = UNSAVED_VALUE;
				if (p_oResultSet.next()) {
					r_lMinId = p_oResultSet.getLong(1);
					if (r_lMinId >= 0L) {
						r_lMinId = UNSAVED_VALUE;
					}
				}
				return r_lMinId;
			}
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTableName() {
		return EntrainementDao.TABLE_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getAliasName() {
		return EntrainementDao.ALIAS_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getEntityName() {
		return Entrainement.ENTITY_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FieldType[] getPKFields() {
		return EntrainementDao.PK_FIELDS;
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
	 * Retourne une entité Entrainement selon la clé primaire
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oContext contexte transactionnel
	 * @return une entité Entrainement selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Entrainement getEntrainement(long p_lId, MContext p_oContext) throws DaoException {
		return this.getEntrainement(p_lId, this.getSelectDaoQuery(), CascadeSet.NONE, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne une entité Entrainement selon la clé primaire
	 * La cascade est CascadeSet.NONE
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oDaoQuery requête	 
	 * @param p_oContext contexte transactionnel
	 * @return une entité Entrainement selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Entrainement getEntrainement(long p_lId, DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException {
		return this.getEntrainement(p_lId, p_oDaoQuery, CascadeSet.NONE, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne une entité Entrainement selon la clé primaire
	 * Les blocs par défaut sont utilisés
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oCascadeSet ensemble de Cascades sur les entités	 
	 * @param p_oContext contexte transactionnel
	 * @return une entité Entrainement selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Entrainement getEntrainement(long p_lId, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		return this.getEntrainement(p_lId, this.getSelectDaoQuery(), p_oCascadeSet, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne une entité Entrainement selon la clé primaire
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une entité Entrainement selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Entrainement getEntrainement(long p_lId, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getEntrainement(p_lId, this.getSelectDaoQuery(), CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne une entité Entrainement selon la clé primaire
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une entité Entrainement selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Entrainement getEntrainement(long p_lId, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		return this.getEntrainement(p_lId, p_oDaoQuery, p_oCascadeSet, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne une entité Entrainement selon la clé primaire
	 * La cascade est CascadeSet.NONE
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une entité Entrainement selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Entrainement getEntrainement(long p_lId, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getEntrainement(p_lId, p_oDaoQuery, CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne une entité Entrainement selon la clé primaire
	 * Les blocs par défaut sont utilisés
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une entité Entrainement selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Entrainement getEntrainement(long p_lId, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getEntrainement(p_lId, this.getSelectDaoQuery(), p_oCascadeSet, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne une entité Entrainement selon la clé primaire
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une entité Entrainement selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Entrainement getEntrainement(long p_lId, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {
		Entrainement r_oEntrainement = null;
		p_oDaoQuery.getSqlQuery().addEqualsConditionToWhere(EntrainementField.ID, EntrainementDao.ALIAS_NAME, p_lId, SqlType.INTEGER);
		AndroidSQLitePreparedStatement oStatement = p_oDaoQuery.prepareStatement(p_oContext);

		try {
			p_oDaoQuery.bindValues(oStatement);
			ResultSetReader oResultSetReader = new ResultSetReader(oStatement.executeQuery());
			try {
				while (oResultSetReader.next()) {
					r_oEntrainement = this.valueObject(oResultSetReader, p_oDaoQuery, p_oDaoSession, p_oCascadeSet, p_oContext);
				}
			} finally {
				oResultSetReader.close();
			}
		} finally {
			oStatement.close();
		}

		return r_oEntrainement;
	}

	/**
	 * Retourne la liste de toutes les entités Entrainement.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainement(MContext p_oContext) throws DaoException {
		return this.getListEntrainement(getSelectDaoQuery(), CascadeSet.NONE, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne la liste des entités Entrainement selon la requête.
	 * La cascade est CascadeSet.NONE
	 * @param p_oDaoQuery requête
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainement(DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException {
		return this.getListEntrainement(p_oDaoQuery, CascadeSet.NONE, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne la liste de toutes les entités Entrainement.
	 * Les blocs par défaut sont utilisés
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainement(CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		return this.getListEntrainement(getSelectDaoQuery(), p_oCascadeSet, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne la liste de toutes les entités Entrainement.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainement(DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getListEntrainement(getSelectDaoQuery(), CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne la liste des entités Entrainement selon la requête.
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainement(DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		return this.getListEntrainement(p_oDaoQuery, p_oCascadeSet, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne la liste des entités Entrainement selon la requête.
	 * La cascade est CascadeSet.NONE
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainement(DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getListEntrainement(p_oDaoQuery, CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne la liste de toutes les entités Entrainement.
	 * Les blocs par défaut sont utilisés
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainement(CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getListEntrainement(getSelectDaoQuery(), p_oCascadeSet, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne la liste des entités Entrainement selon la requête.
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainement(DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {

		return this.getList(p_oDaoQuery, p_oCascadeSet, p_oDaoSession, p_oContext);
	}

	/**
	 * Sauve ou met à jour l'entité Entrainement passée en paramètre selon son existence en base.
	 * La cascade est CascadeSet.NONE
	 * @param p_oEntrainement une entité Entrainement
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateEntrainement(Entrainement p_oEntrainement, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		this.saveOrUpdateEntrainement(p_oEntrainement, CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Sauve ou met à jour l'entité Entrainement passée en paramètre selon son existence en base.
	 * @param p_oEntrainement une entité Entrainement
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateEntrainement(Entrainement p_oEntrainement, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		this.saveOrUpdateEntrainement(p_oEntrainement, p_oCascadeSet, new DaoSession(), p_oContext);
	}

	/**
	 * Sauve ou met à jour l'entité Entrainement passée en paramètre selon son existence en base.
	 * @param p_oEntrainement une entité Entrainement
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateEntrainement(Entrainement p_oEntrainement, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {

		if (!p_oDaoSession.isAlreadySaved(Entrainement.ENTITY_NAME, p_oEntrainement)) {

			this.validateBean(p_oEntrainement, p_oContext);
			boolean bHaveErrorMessage = p_oContext.getMessages().hasErrors();

			if (!bHaveErrorMessage) {

				if (this.exist(p_oEntrainement, p_oCascadeSet, p_oContext)) {
					this.updateEntrainement(p_oEntrainement, p_oCascadeSet, p_oDaoSession, p_oContext);
				} else {

					if (p_oEntrainement.getId() < 0L) {
						p_oEntrainement.setId(this.nextId());
					}

					this.saveEntrainement(p_oEntrainement, p_oCascadeSet, p_oDaoSession, p_oContext);
				}

			}
		}
	}

	/**
	 * Sauve ou met à jour laliste d'entité Entrainement passée en paramètre selon leur existence en base.
	 * La cascade est CascadeSet.NONE
	 * @param p_listEntrainement une liste d'entité Entrainement
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateListEntrainement(Collection<Entrainement> p_listEntrainement, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {
		this.saveOrUpdateListEntrainement(p_listEntrainement, CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Sauve ou met à jour laliste d'entité Entrainement passée en paramètre selon leur existence en base.
	 * @param p_listEntrainement une liste d'entité Entrainement
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateListEntrainement(Collection<Entrainement> p_listEntrainement, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession,
			MContext p_oContext) throws DaoException {

		this.validateBeanList(p_listEntrainement, p_oContext);
		boolean bHaveErrorMessage = p_oContext.getMessages().hasErrors();

		if (!bHaveErrorMessage) {
			List<Entrainement> listSave = new ArrayList<Entrainement>();
			List<Entrainement> listUpdate = new ArrayList<Entrainement>();
			for (Entrainement oEntrainement : p_listEntrainement) {
				if (this.exist(oEntrainement, p_oCascadeSet, p_oContext)) {
					listUpdate.add(oEntrainement);
				} else {

					if (oEntrainement.getId() < 0L) {
						oEntrainement.setId(this.nextId());
					}

					listSave.add(oEntrainement);
				}
			}

			if (!listSave.isEmpty()) {
				this.saveListEntrainement(listSave, p_oCascadeSet, p_oDaoSession, p_oContext);
			}
			if (!listUpdate.isEmpty()) {
				this.updateListEntrainement(listUpdate, p_oCascadeSet, p_oDaoSession, p_oContext);
			}
		}
	}

	/**
	 * Supprime l'entité Entrainement passée en paramètre de la base de données.
	 * La cascade est this.getDeleteCascade()
	 * @param p_oEntrainement une entité Entrainement
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteEntrainement(Entrainement p_oEntrainement, MContext p_oContext) throws DaoException {
		this.deleteEntrainement(p_oEntrainement, this.getDeleteCascade(), p_oContext);
	}

	/**
	 * Supprime l'entité Entrainement passée en paramètre de la base de données.
	 * @param p_oEntrainement une entité Entrainement
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteEntrainement(Entrainement p_oEntrainement, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {

		if (p_oCascadeSet.contains(EntrainementCascade.JOUEURS) && p_oEntrainement.getJoueurs() != null) {
			this.entrainJoueurDao.deleteEntrainJoueurByEntrainementId(p_oEntrainement.getId(), p_oCascadeSet, p_oContext);

		}

		SqlDelete oSqlDelete = getDeleteQuery();
		oSqlDelete.addEqualsConditionToWhere(EntrainementField.ID, p_oEntrainement.getId(), SqlType.INTEGER);

		AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
		AndroidSQLitePreparedStatement oStatement = oConnection.prepareStatement(oSqlDelete.toSql(p_oContext));
		try {
			oSqlDelete.bindValues(oStatement);
			oStatement.executeUpdate();
		} finally {
			oStatement.close();
		}

		if (p_oCascadeSet.contains(EntrainementCascade.LIEU) && p_oEntrainement.getLieu() != null) {
			this.lieuDao.deleteLieu(p_oEntrainement.getLieu(), p_oCascadeSet, p_oContext);
		}

		if (p_oCascadeSet.contains(EntrainementCascade.JOUEURS) && p_oEntrainement.getJoueurs() != null
				&& p_oCascadeSet.contains(EntrainementCascade.JOUEUR)) {
			this.joueurDao.deleteListJoueur(p_oEntrainement.getJoueurs(), p_oCascadeSet, p_oContext);
		}

	}

	/**
	 * Supprime l'entité Entrainement correspondant aux paramètres de la base de données.
	 * Cette suppression ne supprime pas les entités liés en cascade.
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteEntrainement(long p_lId, MContext p_oContext) throws DaoException {

		SqlDelete oSqlDelete = getDeleteQuery();
		oSqlDelete.addEqualsConditionToWhere(EntrainementField.ID, p_lId, SqlType.INTEGER);
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
	 * Supprime de la base de données la liste d'entité Entrainement passée en paramètre.
	 * La cascade est CascadeSet.NONE
	 * @param p_listEntrainement une liste d'entité Entrainement
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteListEntrainement(Collection<Entrainement> p_listEntrainement, MContext p_oContext) throws DaoException {
		this.deleteListEntrainement(p_listEntrainement, CascadeSet.NONE, p_oContext);
	}

	/**
	 * Supprime de la base de données la liste d'entité Entrainement passée en paramètre.
	 * @param p_listEntrainement une liste d'entité Entrainement
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteListEntrainement(Collection<Entrainement> p_listEntrainement, CascadeSet p_oCascadeSet, MContext p_oContext)
			throws DaoException {

		if (!p_listEntrainement.isEmpty()) {

			if (p_oCascadeSet.contains(EntrainementCascade.JOUEURS)) {

				for (Entrainement oEntrainement : p_listEntrainement) {
					if (oEntrainement != null && oEntrainement.getJoueurs() != null) {
						this.entrainJoueurDao.deleteEntrainJoueurByEntrainementId(oEntrainement.getId(), p_oCascadeSet, p_oContext);
					}
				}

			}

			SqlDelete oSqlDelete = getDeleteQuery();
			SqlEqualsValueCondition oSqlEqualsValueCondition1 = oSqlDelete.addEqualsConditionToWhere(EntrainementField.ID, SqlType.INTEGER);

			AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
			AndroidSQLitePreparedStatement oStatement = oConnection.prepareStatement(oSqlDelete.toSql(p_oContext));
			try {
				for (Entrainement oEntrainement : p_listEntrainement) {
					oSqlEqualsValueCondition1.setValue(oEntrainement.getId());

					oSqlDelete.bindValues(oStatement);
					oStatement.addBatch();
				}

				oStatement.executeBatch();
			} finally {
				oStatement.close();
			}

			if (p_oCascadeSet.contains(EntrainementCascade.LIEU)) {

				for (Entrainement oEntrainement : p_listEntrainement) {
					if (oEntrainement != null && oEntrainement.getLieu() != null) {
						this.lieuDao.deleteLieu(oEntrainement.getLieu(), p_oCascadeSet, p_oContext);
					}
				}

			}

			if (p_oCascadeSet.contains(EntrainementCascade.JOUEURS) && p_oCascadeSet.contains(EntrainementCascade.JOUEUR)) {

				for (Entrainement oEntrainement : p_listEntrainement) {
					if (oEntrainement != null && oEntrainement.getJoueurs() != null) {
						this.joueurDao.deleteListJoueur(oEntrainement.getJoueurs(), p_oCascadeSet, p_oContext);
					}
				}

			}

		}
	}

	/**
	 * Retourne le nombre d'entité Entrainement en base.
	 * Les blocs par défaut sont utilisés
	 * @param p_oContext contexte transactionnel
	 * @return le nombre d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public int getNbEntrainement(MContext p_oContext) throws DaoException {
		return this.getNbEntrainement(getCountDaoQuery(), p_oContext);
	}

	/**
	 * Retourne le nombre d'entité Entrainement selon la requête.
	 * @param p_oDaoQuery requête
	 * @param p_oContext contexte transactionnel
	 * @return le nombre d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public int getNbEntrainement(DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException {
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
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_oLieu un paramètre de type Lieu
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByLieu(Lieu p_oLieu, MContext p_oContext) throws DaoException {
		return this.getListEntrainementByLieu(p_oLieu, this.getSelectDaoQuery(), CascadeSet.NONE, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_oLieu un paramètre de type Lieu
	 * @param p_oDaoQuery requête
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByLieu(Lieu p_oLieu, DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException {
		return this.getListEntrainementByLieu(p_oLieu, p_oDaoQuery, CascadeSet.NONE, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * 
	 * 
	 * @param p_oLieu un paramètre de type Lieu
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByLieu(Lieu p_oLieu, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		return this.getListEntrainementByLieu(p_oLieu, this.getSelectDaoQuery(), p_oCascadeSet, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_oLieu un paramètre de type Lieu
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByLieu(Lieu p_oLieu, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getListEntrainementByLieu(p_oLieu, this.getSelectDaoQuery(), CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * 
	 * 
	 * @param p_oLieu un paramètre de type Lieu
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByLieu(Lieu p_oLieu, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, MContext p_oContext)
			throws DaoException {
		return this.getListEntrainementByLieu(p_oLieu, p_oDaoQuery, p_oCascadeSet, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_oLieu un paramètre de type Lieu
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByLieu(Lieu p_oLieu, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {
		return this.getListEntrainementByLieu(p_oLieu, p_oDaoQuery, CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_oLieu un paramètre de type Lieu
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByLieu(Lieu p_oLieu, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {
		return this.getListEntrainementByLieu(p_oLieu, this.getSelectDaoQuery(), p_oCascadeSet, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * 
	 * 
	 * @param p_oLieu un paramètre de type Lieu
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByLieu(Lieu p_oLieu, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession,
			MContext p_oContext) throws DaoException {

		p_oDaoQuery.getSqlQuery().addEqualsConditionToWhere(EntrainementField.LIEUID, EntrainementDao.ALIAS_NAME, p_oLieu.getId(), SqlType.INTEGER);

		return this.getList(p_oDaoQuery, p_oCascadeSet, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lLieuid un paramètre de type long
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByLieu(long p_lLieuid, MContext p_oContext) throws DaoException {
		return this.getListEntrainementByLieu(p_lLieuid, this.getSelectDaoQuery(), CascadeSet.NONE, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lLieuid un paramètre de type long
	 * @param p_oDaoQuery requête
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByLieu(long p_lLieuid, DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException {
		return this.getListEntrainementByLieu(p_lLieuid, p_oDaoQuery, CascadeSet.NONE, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * 
	 * 
	 * @param p_lLieuid un paramètre de type long
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByLieu(long p_lLieuid, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		return this.getListEntrainementByLieu(p_lLieuid, this.getSelectDaoQuery(), p_oCascadeSet, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lLieuid un paramètre de type long
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByLieu(long p_lLieuid, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getListEntrainementByLieu(p_lLieuid, this.getSelectDaoQuery(), CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * 
	 * 
	 * @param p_lLieuid un paramètre de type long
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByLieu(long p_lLieuid, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, MContext p_oContext)
			throws DaoException {
		return this.getListEntrainementByLieu(p_lLieuid, p_oDaoQuery, p_oCascadeSet, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lLieuid un paramètre de type long
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByLieu(long p_lLieuid, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {
		return this.getListEntrainementByLieu(p_lLieuid, p_oDaoQuery, CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lLieuid un paramètre de type long
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByLieu(long p_lLieuid, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {
		return this.getListEntrainementByLieu(p_lLieuid, this.getSelectDaoQuery(), p_oCascadeSet, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * 
	 * 
	 * @param p_lLieuid un paramètre de type long
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByLieu(long p_lLieuid, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession,
			MContext p_oContext) throws DaoException {

		p_oDaoQuery.getSqlQuery().addEqualsConditionToWhere(EntrainementField.LIEUID, EntrainementDao.ALIAS_NAME, p_lLieuid, SqlType.INTEGER);

		return this.getList(p_oDaoQuery, p_oCascadeSet, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_oJoueur un paramètre de type Joueur
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByJoueur(Joueur p_oJoueur, MContext p_oContext) throws DaoException {
		return this.getListEntrainementByJoueur(p_oJoueur, this.getSelectDaoQuery(), CascadeSet.NONE, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_oJoueur un paramètre de type Joueur
	 * @param p_oDaoQuery requête
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByJoueur(Joueur p_oJoueur, DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException {
		return this.getListEntrainementByJoueur(p_oJoueur, p_oDaoQuery, CascadeSet.NONE, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * 
	 * 
	 * @param p_oJoueur un paramètre de type Joueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByJoueur(Joueur p_oJoueur, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		return this.getListEntrainementByJoueur(p_oJoueur, this.getSelectDaoQuery(), p_oCascadeSet, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_oJoueur un paramètre de type Joueur
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByJoueur(Joueur p_oJoueur, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getListEntrainementByJoueur(p_oJoueur, this.getSelectDaoQuery(), CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * 
	 * 
	 * @param p_oJoueur un paramètre de type Joueur
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByJoueur(Joueur p_oJoueur, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, MContext p_oContext)
			throws DaoException {
		return this.getListEntrainementByJoueur(p_oJoueur, p_oDaoQuery, p_oCascadeSet, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_oJoueur un paramètre de type Joueur
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByJoueur(Joueur p_oJoueur, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {
		return this.getListEntrainementByJoueur(p_oJoueur, p_oDaoQuery, CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_oJoueur un paramètre de type Joueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByJoueur(Joueur p_oJoueur, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {
		return this.getListEntrainementByJoueur(p_oJoueur, this.getSelectDaoQuery(), p_oCascadeSet, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * 
	 * 
	 * @param p_oJoueur un paramètre de type Joueur
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByJoueur(Joueur p_oJoueur, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession,
			MContext p_oContext) throws DaoException {

		p_oDaoQuery.getSqlQuery().addEqualsConditionToWhere(EntrainJoueurField.JOUEURID, EntrainJoueurDao.ALIAS_NAME, p_oJoueur.getId(),
				SqlType.INTEGER);
		p_oDaoQuery.getSqlQuery().getFirstFromPart().addSqlJoin(
				new SqlTableInnerJoin(EntrainJoueurDao.TABLE_NAME, EntrainJoueurDao.ALIAS_NAME, EntrainementDao.TABLE_NAME,
						new SqlEqualsFieldCondition(EntrainJoueurField.ENTRAINEMENTID, EntrainJoueurDao.ALIAS_NAME, EntrainementField.ID,
								EntrainementDao.ALIAS_NAME)));

		return this.getList(p_oDaoQuery, p_oCascadeSet, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lJoueurjoueurId un paramètre de type long
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByJoueur(long p_lJoueurjoueurId, MContext p_oContext) throws DaoException {
		return this.getListEntrainementByJoueur(p_lJoueurjoueurId, this.getSelectDaoQuery(), CascadeSet.NONE, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lJoueurjoueurId un paramètre de type long
	 * @param p_oDaoQuery requête
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByJoueur(long p_lJoueurjoueurId, DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException {
		return this.getListEntrainementByJoueur(p_lJoueurjoueurId, p_oDaoQuery, CascadeSet.NONE, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * 
	 * 
	 * @param p_lJoueurjoueurId un paramètre de type long
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByJoueur(long p_lJoueurjoueurId, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		return this.getListEntrainementByJoueur(p_lJoueurjoueurId, this.getSelectDaoQuery(), p_oCascadeSet, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lJoueurjoueurId un paramètre de type long
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByJoueur(long p_lJoueurjoueurId, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getListEntrainementByJoueur(p_lJoueurjoueurId, this.getSelectDaoQuery(), CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * 
	 * 
	 * @param p_lJoueurjoueurId un paramètre de type long
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByJoueur(long p_lJoueurjoueurId, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, MContext p_oContext)
			throws DaoException {
		return this.getListEntrainementByJoueur(p_lJoueurjoueurId, p_oDaoQuery, p_oCascadeSet, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lJoueurjoueurId un paramètre de type long
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByJoueur(long p_lJoueurjoueurId, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {
		return this.getListEntrainementByJoueur(p_lJoueurjoueurId, p_oDaoQuery, CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * 
	 * @param p_lJoueurjoueurId un paramètre de type long
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByJoueur(long p_lJoueurjoueurId, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession,
			MContext p_oContext) throws DaoException {
		return this.getListEntrainementByJoueur(p_lJoueurjoueurId, this.getSelectDaoQuery(), p_oCascadeSet, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne une liste d'entité Entrainement selon les paramètres.
	 * 
	 * 
	 * @param p_lJoueurjoueurId un paramètre de type long
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Entrainement
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Entrainement> getListEntrainementByJoueur(long p_lJoueurjoueurId, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet,
			DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {

		p_oDaoQuery.getSqlQuery().addEqualsConditionToWhere(EntrainJoueurField.JOUEURID, EntrainJoueurDao.ALIAS_NAME, p_lJoueurjoueurId,
				SqlType.INTEGER);
		p_oDaoQuery.getSqlQuery().getFirstFromPart().addSqlJoin(
				new SqlTableInnerJoin(EntrainJoueurDao.TABLE_NAME, EntrainJoueurDao.ALIAS_NAME, EntrainementDao.TABLE_NAME,
						new SqlEqualsFieldCondition(EntrainJoueurField.ENTRAINEMENTID, EntrainJoueurDao.ALIAS_NAME, EntrainementField.ID,
								EntrainementDao.ALIAS_NAME)));

		return this.getList(p_oDaoQuery, p_oCascadeSet, p_oDaoSession, p_oContext);
	}

	/**
	 * Saves a Entrainement entity with the cascade sent as parameter
	 * @param p_oEntrainement une entité Entrainement
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveEntrainement(Entrainement p_oEntrainement, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {

		if (!p_oDaoSession.isAlreadySaved(Entrainement.ENTITY_NAME, p_oEntrainement)) {
			p_oDaoSession.markAsSaved(Entrainement.ENTITY_NAME, p_oEntrainement);

			if (p_oCascadeSet.contains(EntrainementCascade.LIEU) && p_oEntrainement.getLieu() != null) {
				this.lieuDao.saveOrUpdateLieu(p_oEntrainement.getLieu(), p_oCascadeSet, p_oDaoSession, p_oContext);
			}

			if (p_oCascadeSet.contains(EntrainementCascade.JOUEURS) && p_oCascadeSet.contains(EntrainementCascade.JOUEUR)
					&& p_oEntrainement.getJoueurs() != null) {
				this.joueurDao.saveOrUpdateListJoueur(p_oEntrainement.getJoueurs(), p_oCascadeSet, p_oDaoSession, p_oContext);
			}

			if (!p_oContext.getMessages().hasErrors()) {
				SqlInsert oSqlInsert = this.getInsertQuery();
				AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
				MDKSQLiteStatement oStatement = oConnection.compileStatement(oSqlInsert.toSql(p_oContext));
				try {
					bindInsert(p_oEntrainement, oStatement, p_oContext);
					oStatement.executeUpdate();

					p_oEntrainement.setOldId(p_oEntrainement.getId());

				} finally {
					oStatement.close();
				}
			}

			if (p_oCascadeSet.contains(EntrainementCascade.JOUEURS) && p_oEntrainement.getJoueurs() != null) {
				this.entrainJoueurDao.saveJoueurs(p_oEntrainement, p_oEntrainement.getJoueurs(), p_oContext);

			}

		}
	}

	/**
	 * Saves a list of Entrainement entities with the cascade sent as parameter
	 * @param p_listEntrainement une liste d'entité Entrainement
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveListEntrainement(Collection<Entrainement> p_listEntrainement, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession,
			MContext p_oContext) throws DaoException {

		Collection<Entrainement> listEntrainementToSave = p_oDaoSession.getEntitiesToPersist(Entrainement.ENTITY_NAME, p_listEntrainement, true);

		if (p_oCascadeSet.contains(EntrainementCascade.LIEU)) {
			for (Entrainement oEntrainement : listEntrainementToSave) {
				if (oEntrainement.getLieu() != null) {
					this.lieuDao.saveOrUpdateLieu(oEntrainement.getLieu(), p_oCascadeSet, p_oDaoSession, p_oContext);
				}
			}
		}

		if (p_oCascadeSet.contains(EntrainementCascade.JOUEURS) && p_oCascadeSet.contains(EntrainementCascade.JOUEUR)) {
			for (Entrainement oEntrainement : listEntrainementToSave) {
				if (oEntrainement.getJoueurs() != null) {
					this.joueurDao.saveOrUpdateListJoueur(oEntrainement.getJoueurs(), p_oCascadeSet, p_oDaoSession, p_oContext);
				}
			}
		}

		if (!p_oContext.getMessages().hasErrors()) {
			SqlInsert oSqlInsert = this.getInsertQuery();
			AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
			MDKSQLiteStatement oStatement = oConnection.compileStatement(oSqlInsert.toSql(p_oContext));
			try {
				for (Entrainement oEntrainement : listEntrainementToSave) {
					bindInsert(oEntrainement, oStatement, p_oContext);
					oStatement.executeUpdate();
				}

				for (Entrainement oEntrainement : listEntrainementToSave) {
					oEntrainement.setOldId(oEntrainement.getId());
				}

			} finally {
				oStatement.close();
			}
		}

		if (p_oCascadeSet.contains(EntrainementCascade.JOUEURS)) {
			for (Entrainement oEntrainement : listEntrainementToSave) {
				if (oEntrainement.getJoueurs() != null) {
					this.entrainJoueurDao.saveJoueurs(oEntrainement, oEntrainement.getJoueurs(), p_oContext);
				}
			}

		}

	}

	/**
	 * Updates the given entity in database
	 * @param p_oEntrainement une entité Entrainement
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void updateEntrainement(Entrainement p_oEntrainement, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {

		if (!p_oDaoSession.isAlreadySaved(Entrainement.ENTITY_NAME, p_oEntrainement)) {
			p_oDaoSession.markAsSaved(Entrainement.ENTITY_NAME, p_oEntrainement);

			if (p_oCascadeSet.contains(EntrainementCascade.LIEU) && p_oEntrainement.getLieu() != null) {
				this.lieuDao.saveOrUpdateLieu(p_oEntrainement.getLieu(), p_oCascadeSet, p_oDaoSession, p_oContext);
			}

			if (p_oCascadeSet.contains(EntrainementCascade.JOUEURS) && p_oCascadeSet.contains(EntrainementCascade.JOUEUR)
					&& p_oEntrainement.getJoueurs() != null) {
				this.joueurDao.saveOrUpdateListJoueur(p_oEntrainement.getJoueurs(), p_oCascadeSet, p_oDaoSession, p_oContext);
			}

			if (!p_oContext.getMessages().hasErrors()) {

				SqlUpdate oSqlUpdate = this.getUpdateQuery();
				oSqlUpdate.addEqualsConditionToWhere(EntrainementField.ID, SqlType.INTEGER);
				AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
				MDKSQLiteStatement oStatement = oConnection.compileStatement(oSqlUpdate.toSql(p_oContext));
				try {
					bindUpdate(p_oEntrainement, oStatement, p_oContext);
					oStatement.executeUpdate();

					p_oEntrainement.setOldId(p_oEntrainement.getId());

				} finally {
					oStatement.close();
				}
			}

			if (p_oCascadeSet.contains(EntrainementCascade.JOUEURS) && p_oEntrainement.getJoueurs() != null) {
				this.entrainJoueurDao.saveJoueurs(p_oEntrainement, p_oEntrainement.getJoueurs(), p_oContext);

			}

		}
	}

	/**
	 * Updates the given list of entities in the database
	 * @param p_listEntrainement une liste d'entité Entrainement
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session	 
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void updateListEntrainement(Collection<Entrainement> p_listEntrainement, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession,
			MContext p_oContext) throws DaoException {

		Collection<Entrainement> listEntrainementToUpdate = p_oDaoSession.getEntitiesToPersist(Entrainement.ENTITY_NAME, p_listEntrainement, true);

		if (p_oCascadeSet.contains(EntrainementCascade.LIEU)) {
			for (Entrainement oEntrainement : listEntrainementToUpdate) {
				if (oEntrainement.getLieu() != null) {
					this.lieuDao.saveOrUpdateLieu(oEntrainement.getLieu(), p_oCascadeSet, p_oDaoSession, p_oContext);
				}
			}
		}

		if (p_oCascadeSet.contains(EntrainementCascade.JOUEURS) && p_oCascadeSet.contains(EntrainementCascade.JOUEUR)) {
			for (Entrainement oEntrainement : listEntrainementToUpdate) {
				if (oEntrainement.getJoueurs() != null) {
					this.joueurDao.saveOrUpdateListJoueur(oEntrainement.getJoueurs(), p_oCascadeSet, p_oDaoSession, p_oContext);
				}
			}
		}

		if (!p_oContext.getMessages().hasErrors()) {

			SqlUpdate oSqlUpdate = this.getUpdateQuery();
			oSqlUpdate.addEqualsConditionToWhere(EntrainementField.ID, SqlType.INTEGER);
			AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
			MDKSQLiteStatement oStatement = oConnection.compileStatement(oSqlUpdate.toSql(p_oContext));
			try {
				for (Entrainement oEntrainement : listEntrainementToUpdate) {
					bindUpdate(oEntrainement, oStatement, p_oContext);
					oStatement.executeUpdate();
				}

				for (Entrainement oEntrainement : listEntrainementToUpdate) {
					oEntrainement.setOldId(oEntrainement.getId());
				}

			} finally {
				oStatement.close();
			}
		}

		if (p_oCascadeSet.contains(EntrainementCascade.JOUEURS)) {
			for (Entrainement oEntrainement : listEntrainementToUpdate) {
				if (oEntrainement.getJoueurs() != null) {
					this.entrainJoueurDao.saveJoueurs(oEntrainement, oEntrainement.getJoueurs(), p_oContext);
				}
			}

		}

	}

	/**
	 * Returns true if the entity given in parameter exists
	 * @param p_oEntrainement une entité Entrainement
	 * @param p_oCascadeSet ensemble de Cascades sur les entités	 
	 * @param p_oContext contexte transactionnel
	 * @return un boolean indiquant si l'entité existe en base
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	protected boolean exist(Entrainement p_oEntrainement, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		boolean r_bExist = false;
		DaoQuery oQuery = this.getSelectDaoQuery();
		oQuery.getSqlQuery().addEqualsConditionToWhere(EntrainementField.ID, EntrainementDao.ALIAS_NAME, p_oEntrainement.getId(), SqlType.INTEGER);
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
	 * Returns an instance of a Entrainement object from a given ResultSetReader	 * @param p_oResultSetReader résultat de la requête
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return l'entité Entrainement.
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	@Override
	protected Entrainement valueObject(ResultSetReader p_oResultSetReader, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, CascadeSet p_oCascadeSet,
			MContext p_oContext) throws DaoException {

		Entrainement r_oEntrainement = this.entrainementFactory.createInstance();
		r_oEntrainement.setId(p_oResultSetReader.getLong());
		String sEntityId = r_oEntrainement.idToString();
		Entrainement oCachedEntrainement = (Entrainement) p_oDaoSession.getFromCache(Entrainement.ENTITY_NAME, sEntityId);
		if (oCachedEntrainement == null) {
			p_oDaoSession.addToCache(Entrainement.ENTITY_NAME, sEntityId, r_oEntrainement);
			r_oEntrainement.setOldId(r_oEntrainement.getId());
			r_oEntrainement.setDateHeure(p_oResultSetReader.getTimestamp());
			Lieu oLieu = this.lieuFactory.createInstance();
			oLieu.setId(p_oResultSetReader.getLong());
			Lieu oCachedlieu = (Lieu) p_oDaoSession.getFromCache(Lieu.ENTITY_NAME, oLieu.idToString());

			if (oCachedlieu != null) {
				r_oEntrainement.setLieu(oCachedlieu);
			} else {
				r_oEntrainement.setLieu(oLieu);

				if (p_oCascadeSet.contains(EntrainementCascade.LIEU)) {
					DaoQuery oDaoQuery = this.lieuDao.getSelectDaoQuery();
					r_oEntrainement.setLieu(this.lieuDao.getLieu(oLieu.getId(), oDaoQuery, p_oCascadeSet, p_oDaoSession, p_oContext));
				}
			}

			if (p_oCascadeSet.contains(EntrainementCascade.JOUEURS)) {
				DaoQuery oDaoQuery = this.joueurDao.getSelectDaoQuery();
				r_oEntrainement.setJoueurs(this.joueurDao.getListJoueurByEntrainement(r_oEntrainement.getId(), oDaoQuery, p_oCascadeSet,
						p_oDaoSession, p_oContext));
			}
		} else {
			r_oEntrainement = oCachedEntrainement;
		}

		p_oDaoQuery.doResultSetCustomRead(r_oEntrainement, p_oResultSetReader, p_oDaoSession, p_oCascadeSet);

		return r_oEntrainement;
	}

	/**
	 * Returns an instance of a Entrainement object from a given ResultSetReader	 * @param p_oResultSetReader reader de resultset
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oCascadeOptim optimiseur de l'éxécution des cascades
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException.
	 * @return l'entité Entrainement.
	 */
	@Override
	protected Entrainement valueObject(ResultSetReader p_oResultSetReader, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, CascadeSet p_oCascadeSet,
			CascadeOptim p_oCascadeOptim, MContext p_oContext) throws DaoException {

		Entrainement r_oEntrainement = this.entrainementFactory.createInstance();

		r_oEntrainement.setId(p_oResultSetReader.getLong());

		String sEntityId = r_oEntrainement.idToString();
		Entrainement oCachedEntrainement = (Entrainement) p_oDaoSession.getFromCache(Entrainement.ENTITY_NAME, sEntityId);
		if (oCachedEntrainement == null) {
			p_oDaoSession.addToCache(Entrainement.ENTITY_NAME, sEntityId, r_oEntrainement);
			p_oCascadeOptim.registerEntity(sEntityId, r_oEntrainement, r_oEntrainement.getId());
			r_oEntrainement.setOldId(r_oEntrainement.getId());
			r_oEntrainement.setDateHeure(p_oResultSetReader.getTimestamp());
			Lieu oLieu = this.lieuFactory.createInstance();
			oLieu.setId(p_oResultSetReader.getLong());
			Lieu oCachedlieu = (Lieu) p_oDaoSession.getFromCache(Lieu.ENTITY_NAME, oLieu.idToString());

			if (oCachedlieu != null) {
				r_oEntrainement.setLieu(oCachedlieu);
			} else {
				r_oEntrainement.setLieu(oLieu);

				if (p_oCascadeSet.contains(EntrainementCascade.LIEU)) {
					p_oCascadeOptim.registerEntityForCascade(EntrainementCascade.LIEU, oLieu.idToString(), r_oEntrainement, oLieu.getId());
				}
			}

			if (p_oCascadeSet.contains(EntrainementCascade.JOUEURS) && r_oEntrainement.getJoueurs() == null) {
				r_oEntrainement.setJoueurs(new ArrayList<Joueur>());
			}
		} else {
			r_oEntrainement = oCachedEntrainement;
		}

		p_oDaoQuery.doResultSetCustomRead(r_oEntrainement, p_oResultSetReader, p_oDaoSession, p_oCascadeSet);

		return r_oEntrainement;
	}

	/**
	 * Bind un prepareStatement d'insertion
	 * @param p_oEntrainement une entité Entrainement à insérer
	 * @param p_oPreparedStatement requête SQL précompilée
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	@Override
	protected void bindInsert(Entrainement p_oEntrainement, MDKSQLiteStatement p_oStatement, MContext p_oContext) throws DaoException {
		p_oStatement.bindLong(p_oEntrainement.getId());
		p_oStatement.bindTimestamp(p_oEntrainement.getDateHeure());

		if (p_oEntrainement.getLieu() != null && p_oEntrainement.getLieu().getId() != -1) {
			p_oStatement.bindLong(p_oEntrainement.getLieu().getId());
		} else {

			throw new DaoException("Property 'lieu' of object 'Entrainement' is mandatory");

		}

	}

	/**
	 * Bind un preparedStatement de mise à jour
	 * @param p_oEntrainement une entité Entrainement à mettre à jour
	 * @param p_oPreparedStatement requête SQL précompilée
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	@Override
	protected void bindUpdate(Entrainement p_oEntrainement, MDKSQLiteStatement p_oStatement, MContext p_oContext) throws DaoException {
		p_oStatement.bindLong(p_oEntrainement.getId());
		p_oStatement.bindTimestamp(p_oEntrainement.getDateHeure());

		if (p_oEntrainement.getLieu() != null && p_oEntrainement.getLieu().getId() != -1) {
			p_oStatement.bindLong(p_oEntrainement.getLieu().getId());
		} else {

			throw new DaoException("Property 'lieu' of object 'Entrainement' is mandatory");

		}
		p_oStatement.bindLong(p_oEntrainement.getOldId());

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

		this.postTraitementCascadeManyToOneAndOneToOneRelationOwner(p_oCascadeOptim.getInClausesForMainEntities(), p_oCascadeSet, p_oCascadeOptim,
				p_oDaoQuery, p_oDaoSession, p_oContext);
		this.postTraitementCascadeManyToMany(p_oCascadeOptim.getInClausesForMainEntities(), p_oCascadeSet, p_oCascadeOptim, p_oDaoQuery,
				p_oDaoSession, p_oContext);
	}

	/**
	 * Permet de traiter les cascades ManyToOne et OneToOne pour les méthodes 'getList()'
	 * @param p_listSqlInValueCondition Liste des conditions des valeurs
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oCascadeOptim optimiseur de l'éxécution des cascades
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	private void postTraitementCascadeManyToOneAndOneToOneRelationOwner(List<SqlInValueCondition> p_listSqlInValueCondition,
			CascadeSet p_oCascadeSet, CascadeOptim p_oCascadeOptim, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {

		if (p_oCascadeSet.contains(EntrainementCascade.LIEU)) {

			Collection<Lieu> listLieu = new ArrayList<Lieu>();
			for (SqlInValueCondition oSqlInValueCondition : p_oCascadeOptim.getInClausesForCascade(EntrainementCascade.LIEU)) {
				DaoQuery oDaoQuery = this.lieuDao.getSelectDaoQuery();
				oDaoQuery.getSqlQuery().addToWhere(oSqlInValueCondition);
				listLieu.addAll(this.lieuDao.getListLieu(oDaoQuery, p_oCascadeSet, p_oDaoSession, p_oContext));
			}

			for (Lieu oLieu : listLieu) {

				@SuppressWarnings("unchecked")
				List<Entrainement> listEntrainement = (List<Entrainement>) p_oCascadeOptim.getListEntityForCascade(EntrainementCascade.LIEU, oLieu
						.idToString());

				for (Entrainement oEntityEntrainement : listEntrainement) {
					oEntityEntrainement.setLieu(oLieu);

				}
			}
		}

	}

	/**
	 * Permet de traiter les cascades ManyToMany pour les méthodes 'getList()'
	 * @param p_listSqlInValueCondition Liste des conditions des valeurs
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oCascadeOptim optimiseur de l'éxécution des cascades
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	private void postTraitementCascadeManyToMany(List<SqlInValueCondition> p_listSqlInValueCondition, CascadeSet p_oCascadeSet,
			CascadeOptim p_oCascadeOptim, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {

		if (p_oCascadeSet.contains(EntrainementCascade.JOUEURS)) {
			for (SqlInValueCondition oSqlInValueCondition : p_listSqlInValueCondition) {
				DaoQuery oDaoQuery = this.entrainJoueurDao.getSelectDaoQuery();
				oSqlInValueCondition.setFields(EntrainJoueurDao.FK_ENTRAINEMENTS, EntrainJoueurDao.ALIAS_NAME);
				oDaoQuery.getSqlQuery().addToWhere(oSqlInValueCondition);

				Collection<EntrainJoueur> listEntrainJoueur = this.entrainJoueurDao.getListEntrainJoueur(oDaoQuery, p_oCascadeSet, p_oDaoSession,
						p_oContext);

				for (EntrainJoueur oEntrainJoueur : listEntrainJoueur) {
					p_oCascadeOptim.registerJoinEntity(EntrainementCascade.JOUEURS, oEntrainJoueur.entrainementIdToString(), oEntrainJoueur
							.joueurIdToString(), oEntrainJoueur.getJoueurId());
				}
			}
		}

		if (p_oCascadeSet.contains(EntrainementCascade.JOUEURS)) {

			Collection<Joueur> listJoueur = new ArrayList<Joueur>();
			for (SqlInValueCondition oDestInClause : p_oCascadeOptim.getInClausesForJoinClasses(EntrainementCascade.JOUEURS, JoueurDao.PK_FIELDS,
					JoueurDao.ALIAS_NAME)) {
				DaoQuery oDaoQuery = this.joueurDao.getSelectDaoQuery();
				oDaoQuery.getSqlQuery().addToWhere(oDestInClause);
				listJoueur.addAll(this.joueurDao.getListJoueur(oDaoQuery, p_oCascadeSet, p_oDaoSession, p_oContext));
			}

			for (Joueur oJoueurs : listJoueur) {
				List<String> listEntrainementIds = p_oCascadeOptim.getSourceIdsOfJoinEntitiesByTargetId(EntrainementCascade.JOUEURS, oJoueurs
						.idToString());
				for (String sEntrainementId : listEntrainementIds) {
					Entrainement oEntrainement = (Entrainement) p_oCascadeOptim.getEntity(sEntrainementId);
					oEntrainement.getJoueurs().add(oJoueurs);
				}
			}
		}

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

		this.postTraitementFillCascadeManyToOneAndOneToOneRelationOwner(p_oCascadeSet, p_oCascadeOptim, p_oDaoQuery, p_oDaoSession, p_oContext);
		this.postTraitementFillCascadeManyToMany(p_oCascadeSet, p_oCascadeOptim, p_oDaoQuery, p_oDaoSession, p_oContext);
	}

	/**
	 * Permet de traiter les cascades ManyToOne et OneToOne pour les méthodes 'fill()'
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oCascadeOptim optimiseur de l'éxécution des cascades
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	private void postTraitementFillCascadeManyToOneAndOneToOneRelationOwner(CascadeSet p_oCascadeSet, CascadeOptim p_oCascadeOptim,
			DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {

		if (p_oCascadeSet.contains(EntrainementCascade.LIEU)) {

			Collection<Lieu> listLieu = new ArrayList<Lieu>();
			for (SqlInValueCondition oSqlInValueCondition : p_oCascadeOptim.getInClausesForCascade(EntrainementCascade.LIEU)) {
				DaoQuery oDaoQuery = this.lieuDao.getSelectDaoQuery();
				oDaoQuery.getSqlQuery().addToWhere(oSqlInValueCondition);
				listLieu.addAll(this.lieuDao.getListLieu(oDaoQuery, p_oCascadeSet, p_oDaoSession, p_oContext));
			}

			for (Lieu oLieu : listLieu) {

				@SuppressWarnings("unchecked")
				List<Entrainement> listEntrainement = (List<Entrainement>) p_oCascadeOptim.getListEntityForCascade(EntrainementCascade.LIEU, oLieu
						.idToString());

				for (Entrainement oEntityEntrainement : listEntrainement) {
					oEntityEntrainement.setLieu(oLieu);

				}
			}
		}

	}

	/**
	 * Permet de traiter les cascades ManyToMany pour les méthodes 'fill()'
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oCascadeOptim optimiseur de l'éxécution des cascades
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	private void postTraitementFillCascadeManyToMany(CascadeSet p_oCascadeSet, CascadeOptim p_oCascadeOptim, DaoQuery p_oDaoQuery,
			DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {

		if (p_oCascadeSet.contains(EntrainementCascade.JOUEURS) && p_oCascadeOptim.hasEntityForCascade(EntrainementCascade.JOUEURS)) {

			for (SqlInValueCondition oSqlInValueCondition : p_oCascadeOptim.getInClausesForCascade(EntrainementCascade.JOUEURS)) {
				DaoQuery oDaoQuery = this.entrainJoueurDao.getSelectDaoQuery();
				oSqlInValueCondition.setFields(EntrainJoueurDao.FK_ENTRAINEMENTS, EntrainJoueurDao.ALIAS_NAME);
				oDaoQuery.getSqlQuery().addToWhere(oSqlInValueCondition);

				Collection<EntrainJoueur> listEntrainJoueur = this.entrainJoueurDao.getListEntrainJoueur(oDaoQuery, p_oCascadeSet, p_oDaoSession,
						p_oContext);

				for (EntrainJoueur oEntrainJoueur : listEntrainJoueur) {
					p_oCascadeOptim.registerJoinEntity(EntrainementCascade.JOUEURS, oEntrainJoueur.entrainementIdToString(), oEntrainJoueur
							.joueurIdToString(), oEntrainJoueur.getJoueurId());
				}
			}
		}

		if (p_oCascadeSet.contains(EntrainementCascade.JOUEURS)) {

			Collection<Joueur> listJoueur = new ArrayList<Joueur>();
			for (SqlInValueCondition oDestInClause : p_oCascadeOptim.getInClausesForJoinClasses(EntrainementCascade.JOUEURS, JoueurDao.PK_FIELDS,
					JoueurDao.ALIAS_NAME)) {
				DaoQuery oDaoQuery = this.joueurDao.getSelectDaoQuery();
				oDaoQuery.getSqlQuery().addToWhere(oDestInClause);
				listJoueur.addAll(this.joueurDao.getListJoueur(oDaoQuery, p_oCascadeSet, p_oDaoSession, p_oContext));
			}

			for (Joueur oJoueurs : listJoueur) {
				List<String> listEntrainementIds = p_oCascadeOptim.getSourceIdsOfJoinEntitiesByTargetId(EntrainementCascade.JOUEURS, oJoueurs
						.idToString());
				for (String sEntrainementId : listEntrainementIds) {
					Entrainement oEntrainement = (Entrainement) p_oCascadeOptim.getEntity(sEntrainementId);
					oEntrainement.getJoueurs().add(oJoueurs);
				}
			}
		}

	}

}
