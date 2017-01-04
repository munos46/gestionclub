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
import com.adeuza.movalysfwk.mobile.mf4mjcommons.model.MPhoto;
import com.model.Entrainement;
import com.model.EntrainementFactory;
import com.model.EntrainJoueur;
import com.model.EntrainJoueurFactory;
import com.model.Joueur;
import com.model.JoueurCascade;
import com.model.JoueurFactory;

/**
 * 
 * <p>Classe de DAO : AbstractJoueurDaoImpl</p>
 */
public abstract class AbstractJoueurDaoImpl extends AbstractEntityDao<Joueur> implements Initializable {

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
	 * Factory JoueurFactory
	 */
	protected JoueurFactory joueurFactory;

	/**
	 * Dao EntrainJoueurDao
	 */
	protected EntrainJoueurDao entrainJoueurDao;

	/**
	 * Dao EntrainementDao
	 */
	protected EntrainementDao entrainementDao;

	/**
	 * Factory EntrainJoueurFactory
	 */
	protected EntrainJoueurFactory entrainJoueurFactory;

	/**
	 * Factory EntrainementFactory
	 */
	protected EntrainementFactory entrainementFactory;

	/**
	 * Définition des optimisations des cascades
	 */
	protected CascadeOptimDefinition cascadeOptimDefinition;

	/**
	 * Initializes the private attributes of this DAO: factories and daos use by this dao.
	 */
	@Override
	public void initialize() {
		this.joueurFactory = BeanLoader.getInstance().getBean(JoueurFactory.class);

		this.entrainJoueurDao = BeanLoader.getInstance().getBean(EntrainJoueurDao.class);
		this.entrainJoueurFactory = BeanLoader.getInstance().getBean(EntrainJoueurFactory.class);

		this.entrainementDao = BeanLoader.getInstance().getBean(EntrainementDao.class);
		this.entrainementFactory = BeanLoader.getInstance().getBean(EntrainementFactory.class);

		selectQuery = new SqlQuery();
		selectQuery.addFieldToSelect(JoueurDao.ALIAS_NAME, JoueurField.ID, JoueurField.NOM, JoueurField.PRENOM, JoueurField.DATENAISSANCE,
				JoueurField.PHOTO_NAME, JoueurField.PHOTO_URI, JoueurField.PHOTO_SVG, JoueurField.PHOTO_DATE, JoueurField.PHOTO_DESC,
				JoueurField.PHOTO_PHOTOSTATE, JoueurField.EMAIL);
		selectQuery.addToFrom(JoueurDao.TABLE_NAME, JoueurDao.ALIAS_NAME);

		countQuery = new SqlQuery();
		countQuery.addCountToSelect(JoueurField.ID, JoueurDao.ALIAS_NAME);
		countQuery.addToFrom(JoueurDao.TABLE_NAME, JoueurDao.ALIAS_NAME);

		insertQuery = new SqlInsert(JoueurDao.TABLE_NAME);

		insertQuery.addBindedField(JoueurField.ID);
		insertQuery.addBindedField(JoueurField.NOM);
		insertQuery.addBindedField(JoueurField.PRENOM);
		insertQuery.addBindedField(JoueurField.DATENAISSANCE);
		insertQuery.addBindedField(JoueurField.PHOTO_NAME);
		insertQuery.addBindedField(JoueurField.PHOTO_URI);
		insertQuery.addBindedField(JoueurField.PHOTO_SVG);
		insertQuery.addBindedField(JoueurField.PHOTO_DATE);
		insertQuery.addBindedField(JoueurField.PHOTO_DESC);
		insertQuery.addBindedField(JoueurField.PHOTO_PHOTOSTATE);
		insertQuery.addBindedField(JoueurField.EMAIL);

		updateQuery = new SqlUpdate(JoueurDao.TABLE_NAME);

		updateQuery.addBindedField(JoueurField.ID);
		updateQuery.addBindedField(JoueurField.NOM);
		updateQuery.addBindedField(JoueurField.PRENOM);
		updateQuery.addBindedField(JoueurField.DATENAISSANCE);
		updateQuery.addBindedField(JoueurField.PHOTO_NAME);
		updateQuery.addBindedField(JoueurField.PHOTO_URI);
		updateQuery.addBindedField(JoueurField.PHOTO_SVG);
		updateQuery.addBindedField(JoueurField.PHOTO_DATE);
		updateQuery.addBindedField(JoueurField.PHOTO_DESC);
		updateQuery.addBindedField(JoueurField.PHOTO_PHOTOSTATE);
		updateQuery.addBindedField(JoueurField.EMAIL);

		deleteQuery = new SqlDelete(JoueurDao.TABLE_NAME);

		cascadeOptimDefinition = new CascadeOptimDefinition(JoueurDao.PK_FIELDS, JoueurDao.ALIAS_NAME);

		cascadeOptimDefinition.registerCascade(JoueurCascade.ENTRAINEMENTS, EntrainJoueurDao.FK_JOUEURS, EntrainementDao.ALIAS_NAME);

		cascadeOptimDefinition.registerJoinClass(JoueurCascade.ENTRAINEMENTS);
	}

	/**
	 * Initializes the private attributes of this DAO: factories and daos use by this dao.
	 * @param p_oContext the context to use
	 * @throws DaoException if any
	 */
	public void initialize(MContext p_oContext) throws DaoException {
		SqlQuery oSelect = new SqlQuery();
		oSelect.addFunctionToSelect(new SqlFunctionSelectPart(SqlFunction.MIN, JoueurField.ID, JoueurDao.ALIAS_NAME));
		oSelect.addToFrom(JoueurDao.TABLE_NAME, JoueurDao.ALIAS_NAME);
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
		return JoueurDao.TABLE_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getAliasName() {
		return JoueurDao.ALIAS_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getEntityName() {
		return Joueur.ENTITY_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FieldType[] getPKFields() {
		return JoueurDao.PK_FIELDS;
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
	 * Retourne une entité Joueur selon la clé primaire
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oContext contexte transactionnel
	 * @return une entité Joueur selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Joueur getJoueur(long p_lId, MContext p_oContext) throws DaoException {
		return this.getJoueur(p_lId, this.getSelectDaoQuery(), CascadeSet.NONE, new DaoSession(), p_oContext);
	}

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
	public Joueur getJoueur(long p_lId, DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException {
		return this.getJoueur(p_lId, p_oDaoQuery, CascadeSet.NONE, new DaoSession(), p_oContext);
	}

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
	public Joueur getJoueur(long p_lId, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		return this.getJoueur(p_lId, this.getSelectDaoQuery(), p_oCascadeSet, new DaoSession(), p_oContext);
	}

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
	public Joueur getJoueur(long p_lId, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getJoueur(p_lId, this.getSelectDaoQuery(), CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

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
	public Joueur getJoueur(long p_lId, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		return this.getJoueur(p_lId, p_oDaoQuery, p_oCascadeSet, new DaoSession(), p_oContext);
	}

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
	public Joueur getJoueur(long p_lId, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getJoueur(p_lId, p_oDaoQuery, CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

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
	public Joueur getJoueur(long p_lId, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getJoueur(p_lId, this.getSelectDaoQuery(), p_oCascadeSet, p_oDaoSession, p_oContext);
	}

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
			throws DaoException {
		Joueur r_oJoueur = null;
		p_oDaoQuery.getSqlQuery().addEqualsConditionToWhere(JoueurField.ID, JoueurDao.ALIAS_NAME, p_lId, SqlType.INTEGER);
		AndroidSQLitePreparedStatement oStatement = p_oDaoQuery.prepareStatement(p_oContext);

		try {
			p_oDaoQuery.bindValues(oStatement);
			ResultSetReader oResultSetReader = new ResultSetReader(oStatement.executeQuery());
			try {
				while (oResultSetReader.next()) {
					r_oJoueur = this.valueObject(oResultSetReader, p_oDaoQuery, p_oDaoSession, p_oCascadeSet, p_oContext);
				}
			} finally {
				oResultSetReader.close();
			}
		} finally {
			oStatement.close();
		}

		return r_oJoueur;
	}

	/**
	 * Retourne la liste de toutes les entités Joueur.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueur(MContext p_oContext) throws DaoException {
		return this.getListJoueur(getSelectDaoQuery(), CascadeSet.NONE, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne la liste des entités Joueur selon la requête.
	 * La cascade est CascadeSet.NONE
	 * @param p_oDaoQuery requête
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueur(DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException {
		return this.getListJoueur(p_oDaoQuery, CascadeSet.NONE, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne la liste de toutes les entités Joueur.
	 * Les blocs par défaut sont utilisés
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueur(CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		return this.getListJoueur(getSelectDaoQuery(), p_oCascadeSet, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne la liste de toutes les entités Joueur.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueur(DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getListJoueur(getSelectDaoQuery(), CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne la liste des entités Joueur selon la requête.
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueur(DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		return this.getListJoueur(p_oDaoQuery, p_oCascadeSet, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne la liste des entités Joueur selon la requête.
	 * La cascade est CascadeSet.NONE
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueur(DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getListJoueur(p_oDaoQuery, CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne la liste de toutes les entités Joueur.
	 * Les blocs par défaut sont utilisés
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Joueur> getListJoueur(CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getListJoueur(getSelectDaoQuery(), p_oCascadeSet, p_oDaoSession, p_oContext);
	}

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
			throws DaoException {

		return this.getList(p_oDaoQuery, p_oCascadeSet, p_oDaoSession, p_oContext);
	}

	/**
	 * Sauve ou met à jour l'entité Joueur passée en paramètre selon son existence en base.
	 * La cascade est CascadeSet.NONE
	 * @param p_oJoueur une entité Joueur
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateJoueur(Joueur p_oJoueur, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		this.saveOrUpdateJoueur(p_oJoueur, CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Sauve ou met à jour l'entité Joueur passée en paramètre selon son existence en base.
	 * @param p_oJoueur une entité Joueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateJoueur(Joueur p_oJoueur, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		this.saveOrUpdateJoueur(p_oJoueur, p_oCascadeSet, new DaoSession(), p_oContext);
	}

	/**
	 * Sauve ou met à jour l'entité Joueur passée en paramètre selon son existence en base.
	 * @param p_oJoueur une entité Joueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateJoueur(Joueur p_oJoueur, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {

		if (!p_oDaoSession.isAlreadySaved(Joueur.ENTITY_NAME, p_oJoueur)) {

			this.validateBean(p_oJoueur, p_oContext);
			boolean bHaveErrorMessage = p_oContext.getMessages().hasErrors();

			if (!bHaveErrorMessage) {

				if (this.exist(p_oJoueur, p_oCascadeSet, p_oContext)) {
					this.updateJoueur(p_oJoueur, p_oCascadeSet, p_oDaoSession, p_oContext);
				} else {

					if (p_oJoueur.getId() < 0L) {
						p_oJoueur.setId(this.nextId());
					}

					this.saveJoueur(p_oJoueur, p_oCascadeSet, p_oDaoSession, p_oContext);
				}

			}
		}
	}

	/**
	 * Sauve ou met à jour laliste d'entité Joueur passée en paramètre selon leur existence en base.
	 * La cascade est CascadeSet.NONE
	 * @param p_listJoueur une liste d'entité Joueur
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateListJoueur(Collection<Joueur> p_listJoueur, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		this.saveOrUpdateListJoueur(p_listJoueur, CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Sauve ou met à jour laliste d'entité Joueur passée en paramètre selon leur existence en base.
	 * @param p_listJoueur une liste d'entité Joueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateListJoueur(Collection<Joueur> p_listJoueur, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {

		this.validateBeanList(p_listJoueur, p_oContext);
		boolean bHaveErrorMessage = p_oContext.getMessages().hasErrors();

		if (!bHaveErrorMessage) {
			List<Joueur> listSave = new ArrayList<Joueur>();
			List<Joueur> listUpdate = new ArrayList<Joueur>();
			for (Joueur oJoueur : p_listJoueur) {
				if (this.exist(oJoueur, p_oCascadeSet, p_oContext)) {
					listUpdate.add(oJoueur);
				} else {

					if (oJoueur.getId() < 0L) {
						oJoueur.setId(this.nextId());
					}

					listSave.add(oJoueur);
				}
			}

			if (!listSave.isEmpty()) {
				this.saveListJoueur(listSave, p_oCascadeSet, p_oDaoSession, p_oContext);
			}
			if (!listUpdate.isEmpty()) {
				this.updateListJoueur(listUpdate, p_oCascadeSet, p_oDaoSession, p_oContext);
			}
		}
	}

	/**
	 * Supprime l'entité Joueur passée en paramètre de la base de données.
	 * La cascade est this.getDeleteCascade()
	 * @param p_oJoueur une entité Joueur
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteJoueur(Joueur p_oJoueur, MContext p_oContext) throws DaoException {
		this.deleteJoueur(p_oJoueur, this.getDeleteCascade(), p_oContext);
	}

	/**
	 * Supprime l'entité Joueur passée en paramètre de la base de données.
	 * @param p_oJoueur une entité Joueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteJoueur(Joueur p_oJoueur, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {

		if (p_oCascadeSet.contains(JoueurCascade.ENTRAINEMENTS) && p_oJoueur.getEntrainements() != null) {
			this.entrainJoueurDao.deleteEntrainJoueurByJoueurId(p_oJoueur.getId(), p_oCascadeSet, p_oContext);

		}

		SqlDelete oSqlDelete = getDeleteQuery();
		oSqlDelete.addEqualsConditionToWhere(JoueurField.ID, p_oJoueur.getId(), SqlType.INTEGER);

		AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
		AndroidSQLitePreparedStatement oStatement = oConnection.prepareStatement(oSqlDelete.toSql(p_oContext));
		try {
			oSqlDelete.bindValues(oStatement);
			oStatement.executeUpdate();
		} finally {
			oStatement.close();
		}

		if (p_oCascadeSet.contains(JoueurCascade.ENTRAINEMENTS) && p_oJoueur.getEntrainements() != null
				&& p_oCascadeSet.contains(JoueurCascade.ENTRAINEMENT)) {
			this.entrainementDao.deleteListEntrainement(p_oJoueur.getEntrainements(), p_oCascadeSet, p_oContext);
		}

	}

	/**
	 * Supprime l'entité Joueur correspondant aux paramètres de la base de données.
	 * Cette suppression ne supprime pas les entités liés en cascade.
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteJoueur(long p_lId, MContext p_oContext) throws DaoException {

		SqlDelete oSqlDelete = getDeleteQuery();
		oSqlDelete.addEqualsConditionToWhere(JoueurField.ID, p_lId, SqlType.INTEGER);
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
	 * Supprime de la base de données la liste d'entité Joueur passée en paramètre.
	 * La cascade est CascadeSet.NONE
	 * @param p_listJoueur une liste d'entité Joueur
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteListJoueur(Collection<Joueur> p_listJoueur, MContext p_oContext) throws DaoException {
		this.deleteListJoueur(p_listJoueur, CascadeSet.NONE, p_oContext);
	}

	/**
	 * Supprime de la base de données la liste d'entité Joueur passée en paramètre.
	 * @param p_listJoueur une liste d'entité Joueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteListJoueur(Collection<Joueur> p_listJoueur, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {

		if (!p_listJoueur.isEmpty()) {

			if (p_oCascadeSet.contains(JoueurCascade.ENTRAINEMENTS)) {

				for (Joueur oJoueur : p_listJoueur) {
					if (oJoueur != null && oJoueur.getEntrainements() != null) {
						this.entrainJoueurDao.deleteEntrainJoueurByJoueurId(oJoueur.getId(), p_oCascadeSet, p_oContext);
					}
				}

			}

			SqlDelete oSqlDelete = getDeleteQuery();
			SqlEqualsValueCondition oSqlEqualsValueCondition1 = oSqlDelete.addEqualsConditionToWhere(JoueurField.ID, SqlType.INTEGER);

			AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
			AndroidSQLitePreparedStatement oStatement = oConnection.prepareStatement(oSqlDelete.toSql(p_oContext));
			try {
				for (Joueur oJoueur : p_listJoueur) {
					oSqlEqualsValueCondition1.setValue(oJoueur.getId());

					oSqlDelete.bindValues(oStatement);
					oStatement.addBatch();
				}

				oStatement.executeBatch();
			} finally {
				oStatement.close();
			}

			if (p_oCascadeSet.contains(JoueurCascade.ENTRAINEMENTS) && p_oCascadeSet.contains(JoueurCascade.ENTRAINEMENT)) {

				for (Joueur oJoueur : p_listJoueur) {
					if (oJoueur != null && oJoueur.getEntrainements() != null) {
						this.entrainementDao.deleteListEntrainement(oJoueur.getEntrainements(), p_oCascadeSet, p_oContext);
					}
				}

			}

		}
	}

	/**
	 * Retourne le nombre d'entité Joueur en base.
	 * Les blocs par défaut sont utilisés
	 * @param p_oContext contexte transactionnel
	 * @return le nombre d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public int getNbJoueur(MContext p_oContext) throws DaoException {
		return this.getNbJoueur(getCountDaoQuery(), p_oContext);
	}

	/**
	 * Retourne le nombre d'entité Joueur selon la requête.
	 * @param p_oDaoQuery requête
	 * @param p_oContext contexte transactionnel
	 * @return le nombre d'entité Joueur
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public int getNbJoueur(DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException {
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
	public List<Joueur> getListJoueurByEntrainement(Entrainement p_oEntrainement, MContext p_oContext) throws DaoException {
		return this.getListJoueurByEntrainement(p_oEntrainement, this.getSelectDaoQuery(), CascadeSet.NONE, new DaoSession(), p_oContext);
	}

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
	public List<Joueur> getListJoueurByEntrainement(Entrainement p_oEntrainement, DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException {
		return this.getListJoueurByEntrainement(p_oEntrainement, p_oDaoQuery, CascadeSet.NONE, new DaoSession(), p_oContext);
	}

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
	public List<Joueur> getListJoueurByEntrainement(Entrainement p_oEntrainement, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		return this.getListJoueurByEntrainement(p_oEntrainement, this.getSelectDaoQuery(), p_oCascadeSet, new DaoSession(), p_oContext);
	}

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
	public List<Joueur> getListJoueurByEntrainement(Entrainement p_oEntrainement, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getListJoueurByEntrainement(p_oEntrainement, this.getSelectDaoQuery(), CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

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
			throws DaoException {
		return this.getListJoueurByEntrainement(p_oEntrainement, p_oDaoQuery, p_oCascadeSet, new DaoSession(), p_oContext);
	}

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
			throws DaoException {
		return this.getListJoueurByEntrainement(p_oEntrainement, p_oDaoQuery, CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

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
			MContext p_oContext) throws DaoException {
		return this.getListJoueurByEntrainement(p_oEntrainement, this.getSelectDaoQuery(), p_oCascadeSet, p_oDaoSession, p_oContext);
	}

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
			DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {

		p_oDaoQuery.getSqlQuery().addEqualsConditionToWhere(EntrainJoueurField.ENTRAINEMENTID, EntrainJoueurDao.ALIAS_NAME, p_oEntrainement.getId(),
				SqlType.INTEGER);
		p_oDaoQuery.getSqlQuery().getFirstFromPart().addSqlJoin(
				new SqlTableInnerJoin(EntrainJoueurDao.TABLE_NAME, EntrainJoueurDao.ALIAS_NAME, JoueurDao.TABLE_NAME, new SqlEqualsFieldCondition(
						EntrainJoueurField.JOUEURID, EntrainJoueurDao.ALIAS_NAME, JoueurField.ID, JoueurDao.ALIAS_NAME)));

		return this.getList(p_oDaoQuery, p_oCascadeSet, p_oDaoSession, p_oContext);
	}

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
	public List<Joueur> getListJoueurByEntrainement(long p_lEntrainemententrainementId, MContext p_oContext) throws DaoException {
		return this.getListJoueurByEntrainement(p_lEntrainemententrainementId, this.getSelectDaoQuery(), CascadeSet.NONE, new DaoSession(),
				p_oContext);
	}

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
			throws DaoException {
		return this.getListJoueurByEntrainement(p_lEntrainemententrainementId, p_oDaoQuery, CascadeSet.NONE, new DaoSession(), p_oContext);
	}

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
			throws DaoException {
		return this.getListJoueurByEntrainement(p_lEntrainemententrainementId, this.getSelectDaoQuery(), p_oCascadeSet, new DaoSession(), p_oContext);
	}

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
			throws DaoException {
		return this.getListJoueurByEntrainement(p_lEntrainemententrainementId, this.getSelectDaoQuery(), CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

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
			MContext p_oContext) throws DaoException {
		return this.getListJoueurByEntrainement(p_lEntrainemententrainementId, p_oDaoQuery, p_oCascadeSet, new DaoSession(), p_oContext);
	}

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
			MContext p_oContext) throws DaoException {
		return this.getListJoueurByEntrainement(p_lEntrainemententrainementId, p_oDaoQuery, CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

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
			MContext p_oContext) throws DaoException {
		return this.getListJoueurByEntrainement(p_lEntrainemententrainementId, this.getSelectDaoQuery(), p_oCascadeSet, p_oDaoSession, p_oContext);
	}

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
			DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {

		p_oDaoQuery.getSqlQuery().addEqualsConditionToWhere(EntrainJoueurField.ENTRAINEMENTID, EntrainJoueurDao.ALIAS_NAME,
				p_lEntrainemententrainementId, SqlType.INTEGER);
		p_oDaoQuery.getSqlQuery().getFirstFromPart().addSqlJoin(
				new SqlTableInnerJoin(EntrainJoueurDao.TABLE_NAME, EntrainJoueurDao.ALIAS_NAME, JoueurDao.TABLE_NAME, new SqlEqualsFieldCondition(
						EntrainJoueurField.JOUEURID, EntrainJoueurDao.ALIAS_NAME, JoueurField.ID, JoueurDao.ALIAS_NAME)));

		return this.getList(p_oDaoQuery, p_oCascadeSet, p_oDaoSession, p_oContext);
	}

	/**
	 * Saves a Joueur entity with the cascade sent as parameter
	 * @param p_oJoueur une entité Joueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveJoueur(Joueur p_oJoueur, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {

		if (!p_oDaoSession.isAlreadySaved(Joueur.ENTITY_NAME, p_oJoueur)) {
			p_oDaoSession.markAsSaved(Joueur.ENTITY_NAME, p_oJoueur);

			if (p_oCascadeSet.contains(JoueurCascade.ENTRAINEMENTS) && p_oCascadeSet.contains(JoueurCascade.ENTRAINEMENT)
					&& p_oJoueur.getEntrainements() != null) {
				this.entrainementDao.saveOrUpdateListEntrainement(p_oJoueur.getEntrainements(), p_oCascadeSet, p_oDaoSession, p_oContext);
			}

			if (!p_oContext.getMessages().hasErrors()) {
				SqlInsert oSqlInsert = this.getInsertQuery();
				AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
				MDKSQLiteStatement oStatement = oConnection.compileStatement(oSqlInsert.toSql(p_oContext));
				try {
					bindInsert(p_oJoueur, oStatement, p_oContext);
					oStatement.executeUpdate();

					p_oJoueur.setOldId(p_oJoueur.getId());

				} finally {
					oStatement.close();
				}
			}

			if (p_oCascadeSet.contains(JoueurCascade.ENTRAINEMENTS) && p_oJoueur.getEntrainements() != null) {
				this.entrainJoueurDao.saveEntrainements(p_oJoueur, p_oJoueur.getEntrainements(), p_oContext);

			}

		}
	}

	/**
	 * Saves a list of Joueur entities with the cascade sent as parameter
	 * @param p_listJoueur une liste d'entité Joueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveListJoueur(Collection<Joueur> p_listJoueur, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {

		Collection<Joueur> listJoueurToSave = p_oDaoSession.getEntitiesToPersist(Joueur.ENTITY_NAME, p_listJoueur, true);

		if (p_oCascadeSet.contains(JoueurCascade.ENTRAINEMENTS) && p_oCascadeSet.contains(JoueurCascade.ENTRAINEMENT)) {
			for (Joueur oJoueur : listJoueurToSave) {
				if (oJoueur.getEntrainements() != null) {
					this.entrainementDao.saveOrUpdateListEntrainement(oJoueur.getEntrainements(), p_oCascadeSet, p_oDaoSession, p_oContext);
				}
			}
		}

		if (!p_oContext.getMessages().hasErrors()) {
			SqlInsert oSqlInsert = this.getInsertQuery();
			AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
			MDKSQLiteStatement oStatement = oConnection.compileStatement(oSqlInsert.toSql(p_oContext));
			try {
				for (Joueur oJoueur : listJoueurToSave) {
					bindInsert(oJoueur, oStatement, p_oContext);
					oStatement.executeUpdate();
				}

				for (Joueur oJoueur : listJoueurToSave) {
					oJoueur.setOldId(oJoueur.getId());
				}

			} finally {
				oStatement.close();
			}
		}

		if (p_oCascadeSet.contains(JoueurCascade.ENTRAINEMENTS)) {
			for (Joueur oJoueur : listJoueurToSave) {
				if (oJoueur.getEntrainements() != null) {
					this.entrainJoueurDao.saveEntrainements(oJoueur, oJoueur.getEntrainements(), p_oContext);
				}
			}

		}

	}

	/**
	 * Updates the given entity in database
	 * @param p_oJoueur une entité Joueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void updateJoueur(Joueur p_oJoueur, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {

		if (!p_oDaoSession.isAlreadySaved(Joueur.ENTITY_NAME, p_oJoueur)) {
			p_oDaoSession.markAsSaved(Joueur.ENTITY_NAME, p_oJoueur);

			if (p_oCascadeSet.contains(JoueurCascade.ENTRAINEMENTS) && p_oCascadeSet.contains(JoueurCascade.ENTRAINEMENT)
					&& p_oJoueur.getEntrainements() != null) {
				this.entrainementDao.saveOrUpdateListEntrainement(p_oJoueur.getEntrainements(), p_oCascadeSet, p_oDaoSession, p_oContext);
			}

			if (!p_oContext.getMessages().hasErrors()) {

				SqlUpdate oSqlUpdate = this.getUpdateQuery();
				oSqlUpdate.addEqualsConditionToWhere(JoueurField.ID, SqlType.INTEGER);
				AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
				MDKSQLiteStatement oStatement = oConnection.compileStatement(oSqlUpdate.toSql(p_oContext));
				try {
					bindUpdate(p_oJoueur, oStatement, p_oContext);
					oStatement.executeUpdate();

					p_oJoueur.setOldId(p_oJoueur.getId());

				} finally {
					oStatement.close();
				}
			}

			if (p_oCascadeSet.contains(JoueurCascade.ENTRAINEMENTS) && p_oJoueur.getEntrainements() != null) {
				this.entrainJoueurDao.saveEntrainements(p_oJoueur, p_oJoueur.getEntrainements(), p_oContext);

			}

		}
	}

	/**
	 * Updates the given list of entities in the database
	 * @param p_listJoueur une liste d'entité Joueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session	 
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void updateListJoueur(Collection<Joueur> p_listJoueur, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {

		Collection<Joueur> listJoueurToUpdate = p_oDaoSession.getEntitiesToPersist(Joueur.ENTITY_NAME, p_listJoueur, true);

		if (p_oCascadeSet.contains(JoueurCascade.ENTRAINEMENTS) && p_oCascadeSet.contains(JoueurCascade.ENTRAINEMENT)) {
			for (Joueur oJoueur : listJoueurToUpdate) {
				if (oJoueur.getEntrainements() != null) {
					this.entrainementDao.saveOrUpdateListEntrainement(oJoueur.getEntrainements(), p_oCascadeSet, p_oDaoSession, p_oContext);
				}
			}
		}

		if (!p_oContext.getMessages().hasErrors()) {

			SqlUpdate oSqlUpdate = this.getUpdateQuery();
			oSqlUpdate.addEqualsConditionToWhere(JoueurField.ID, SqlType.INTEGER);
			AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
			MDKSQLiteStatement oStatement = oConnection.compileStatement(oSqlUpdate.toSql(p_oContext));
			try {
				for (Joueur oJoueur : listJoueurToUpdate) {
					bindUpdate(oJoueur, oStatement, p_oContext);
					oStatement.executeUpdate();
				}

				for (Joueur oJoueur : listJoueurToUpdate) {
					oJoueur.setOldId(oJoueur.getId());
				}

			} finally {
				oStatement.close();
			}
		}

		if (p_oCascadeSet.contains(JoueurCascade.ENTRAINEMENTS)) {
			for (Joueur oJoueur : listJoueurToUpdate) {
				if (oJoueur.getEntrainements() != null) {
					this.entrainJoueurDao.saveEntrainements(oJoueur, oJoueur.getEntrainements(), p_oContext);
				}
			}

		}

	}

	/**
	 * Returns true if the entity given in parameter exists
	 * @param p_oJoueur une entité Joueur
	 * @param p_oCascadeSet ensemble de Cascades sur les entités	 
	 * @param p_oContext contexte transactionnel
	 * @return un boolean indiquant si l'entité existe en base
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	protected boolean exist(Joueur p_oJoueur, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		boolean r_bExist = false;
		DaoQuery oQuery = this.getSelectDaoQuery();
		oQuery.getSqlQuery().addEqualsConditionToWhere(JoueurField.ID, JoueurDao.ALIAS_NAME, p_oJoueur.getId(), SqlType.INTEGER);
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
	 * Returns an instance of a Joueur object from a given ResultSetReader	 * @param p_oResultSetReader résultat de la requête
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return l'entité Joueur.
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	@Override
	protected Joueur valueObject(ResultSetReader p_oResultSetReader, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, CascadeSet p_oCascadeSet,
			MContext p_oContext) throws DaoException {

		Joueur r_oJoueur = this.joueurFactory.createInstance();
		r_oJoueur.setId(p_oResultSetReader.getLong());
		String sEntityId = r_oJoueur.idToString();
		Joueur oCachedJoueur = (Joueur) p_oDaoSession.getFromCache(Joueur.ENTITY_NAME, sEntityId);
		if (oCachedJoueur == null) {
			p_oDaoSession.addToCache(Joueur.ENTITY_NAME, sEntityId, r_oJoueur);
			r_oJoueur.setOldId(r_oJoueur.getId());
			r_oJoueur.setNom(p_oResultSetReader.getString());
			r_oJoueur.setPrenom(p_oResultSetReader.getString());
			r_oJoueur.setDateNaissance(p_oResultSetReader.getTimestamp());
			r_oJoueur.setPhoto(com.adeuza.movalysfwk.mobile.mf4mjcommons.model.MPhotoHelper.readResultSet(p_oResultSetReader));
			r_oJoueur.setEmail(p_oResultSetReader.getString());
			if (p_oCascadeSet.contains(JoueurCascade.ENTRAINEMENTS)) {
				DaoQuery oDaoQuery = this.entrainementDao.getSelectDaoQuery();
				r_oJoueur.setEntrainements(this.entrainementDao.getListEntrainementByJoueur(r_oJoueur.getId(), oDaoQuery, p_oCascadeSet,
						p_oDaoSession, p_oContext));
			}
		} else {
			r_oJoueur = oCachedJoueur;
		}

		p_oDaoQuery.doResultSetCustomRead(r_oJoueur, p_oResultSetReader, p_oDaoSession, p_oCascadeSet);

		return r_oJoueur;
	}

	/**
	 * Returns an instance of a Joueur object from a given ResultSetReader	 * @param p_oResultSetReader reader de resultset
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oCascadeOptim optimiseur de l'éxécution des cascades
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException.
	 * @return l'entité Joueur.
	 */
	@Override
	protected Joueur valueObject(ResultSetReader p_oResultSetReader, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, CascadeSet p_oCascadeSet,
			CascadeOptim p_oCascadeOptim, MContext p_oContext) throws DaoException {

		Joueur r_oJoueur = this.joueurFactory.createInstance();

		r_oJoueur.setId(p_oResultSetReader.getLong());

		String sEntityId = r_oJoueur.idToString();
		Joueur oCachedJoueur = (Joueur) p_oDaoSession.getFromCache(Joueur.ENTITY_NAME, sEntityId);
		if (oCachedJoueur == null) {
			p_oDaoSession.addToCache(Joueur.ENTITY_NAME, sEntityId, r_oJoueur);
			p_oCascadeOptim.registerEntity(sEntityId, r_oJoueur, r_oJoueur.getId());
			r_oJoueur.setOldId(r_oJoueur.getId());
			r_oJoueur.setNom(p_oResultSetReader.getString());
			r_oJoueur.setPrenom(p_oResultSetReader.getString());
			r_oJoueur.setDateNaissance(p_oResultSetReader.getTimestamp());
			r_oJoueur.setPhoto(com.adeuza.movalysfwk.mobile.mf4mjcommons.model.MPhotoHelper.readResultSet(p_oResultSetReader));
			r_oJoueur.setEmail(p_oResultSetReader.getString());

			if (p_oCascadeSet.contains(JoueurCascade.ENTRAINEMENTS) && r_oJoueur.getEntrainements() == null) {
				r_oJoueur.setEntrainements(new ArrayList<Entrainement>());
			}
		} else {
			r_oJoueur = oCachedJoueur;
		}

		p_oDaoQuery.doResultSetCustomRead(r_oJoueur, p_oResultSetReader, p_oDaoSession, p_oCascadeSet);

		return r_oJoueur;
	}

	/**
	 * Bind un prepareStatement d'insertion
	 * @param p_oJoueur une entité Joueur à insérer
	 * @param p_oPreparedStatement requête SQL précompilée
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	@Override
	protected void bindInsert(Joueur p_oJoueur, MDKSQLiteStatement p_oStatement, MContext p_oContext) throws DaoException {
		p_oStatement.bindLong(p_oJoueur.getId());
		p_oStatement.bindString(p_oJoueur.getNom());
		p_oStatement.bindString(p_oJoueur.getPrenom());
		p_oStatement.bindTimestamp(p_oJoueur.getDateNaissance());
		com.adeuza.movalysfwk.mobile.mf4mjcommons.model.MPhotoHelper.bindToStatement(p_oJoueur.getPhoto(), p_oStatement);
		p_oStatement.bindString(p_oJoueur.getEmail());

	}

	/**
	 * Bind un preparedStatement de mise à jour
	 * @param p_oJoueur une entité Joueur à mettre à jour
	 * @param p_oPreparedStatement requête SQL précompilée
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	@Override
	protected void bindUpdate(Joueur p_oJoueur, MDKSQLiteStatement p_oStatement, MContext p_oContext) throws DaoException {
		p_oStatement.bindLong(p_oJoueur.getId());
		p_oStatement.bindString(p_oJoueur.getNom());
		p_oStatement.bindString(p_oJoueur.getPrenom());
		p_oStatement.bindTimestamp(p_oJoueur.getDateNaissance());
		com.adeuza.movalysfwk.mobile.mf4mjcommons.model.MPhotoHelper.bindToStatement(p_oJoueur.getPhoto(), p_oStatement);
		p_oStatement.bindString(p_oJoueur.getEmail());
		p_oStatement.bindLong(p_oJoueur.getOldId());

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

		this.postTraitementCascadeManyToMany(p_oCascadeOptim.getInClausesForMainEntities(), p_oCascadeSet, p_oCascadeOptim, p_oDaoQuery,
				p_oDaoSession, p_oContext);
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

		if (p_oCascadeSet.contains(JoueurCascade.ENTRAINEMENTS)) {
			for (SqlInValueCondition oSqlInValueCondition : p_listSqlInValueCondition) {
				DaoQuery oDaoQuery = this.entrainJoueurDao.getSelectDaoQuery();
				oSqlInValueCondition.setFields(EntrainJoueurDao.FK_JOUEURS, EntrainJoueurDao.ALIAS_NAME);
				oDaoQuery.getSqlQuery().addToWhere(oSqlInValueCondition);

				Collection<EntrainJoueur> listEntrainJoueur = this.entrainJoueurDao.getListEntrainJoueur(oDaoQuery, p_oCascadeSet, p_oDaoSession,
						p_oContext);

				for (EntrainJoueur oEntrainJoueur : listEntrainJoueur) {
					p_oCascadeOptim.registerJoinEntity(JoueurCascade.ENTRAINEMENTS, oEntrainJoueur.joueurIdToString(), oEntrainJoueur
							.entrainementIdToString(), oEntrainJoueur.getEntrainementId());
				}
			}
		}

		if (p_oCascadeSet.contains(JoueurCascade.ENTRAINEMENTS)) {

			Collection<Entrainement> listEntrainement = new ArrayList<Entrainement>();
			for (SqlInValueCondition oDestInClause : p_oCascadeOptim.getInClausesForJoinClasses(JoueurCascade.ENTRAINEMENTS,
					EntrainementDao.PK_FIELDS, EntrainementDao.ALIAS_NAME)) {
				DaoQuery oDaoQuery = this.entrainementDao.getSelectDaoQuery();
				oDaoQuery.getSqlQuery().addToWhere(oDestInClause);
				listEntrainement.addAll(this.entrainementDao.getListEntrainement(oDaoQuery, p_oCascadeSet, p_oDaoSession, p_oContext));
			}

			for (Entrainement oEntrainements : listEntrainement) {
				List<String> listJoueurIds = p_oCascadeOptim.getSourceIdsOfJoinEntitiesByTargetId(JoueurCascade.ENTRAINEMENTS, oEntrainements
						.idToString());
				for (String sJoueurId : listJoueurIds) {
					Joueur oJoueur = (Joueur) p_oCascadeOptim.getEntity(sJoueurId);
					oJoueur.getEntrainements().add(oEntrainements);
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

		this.postTraitementFillCascadeManyToMany(p_oCascadeSet, p_oCascadeOptim, p_oDaoQuery, p_oDaoSession, p_oContext);
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

		if (p_oCascadeSet.contains(JoueurCascade.ENTRAINEMENTS) && p_oCascadeOptim.hasEntityForCascade(JoueurCascade.ENTRAINEMENTS)) {

			for (SqlInValueCondition oSqlInValueCondition : p_oCascadeOptim.getInClausesForCascade(JoueurCascade.ENTRAINEMENTS)) {
				DaoQuery oDaoQuery = this.entrainJoueurDao.getSelectDaoQuery();
				oSqlInValueCondition.setFields(EntrainJoueurDao.FK_JOUEURS, EntrainJoueurDao.ALIAS_NAME);
				oDaoQuery.getSqlQuery().addToWhere(oSqlInValueCondition);

				Collection<EntrainJoueur> listEntrainJoueur = this.entrainJoueurDao.getListEntrainJoueur(oDaoQuery, p_oCascadeSet, p_oDaoSession,
						p_oContext);

				for (EntrainJoueur oEntrainJoueur : listEntrainJoueur) {
					p_oCascadeOptim.registerJoinEntity(JoueurCascade.ENTRAINEMENTS, oEntrainJoueur.joueurIdToString(), oEntrainJoueur
							.entrainementIdToString(), oEntrainJoueur.getEntrainementId());
				}
			}
		}

		if (p_oCascadeSet.contains(JoueurCascade.ENTRAINEMENTS)) {

			Collection<Entrainement> listEntrainement = new ArrayList<Entrainement>();
			for (SqlInValueCondition oDestInClause : p_oCascadeOptim.getInClausesForJoinClasses(JoueurCascade.ENTRAINEMENTS,
					EntrainementDao.PK_FIELDS, EntrainementDao.ALIAS_NAME)) {
				DaoQuery oDaoQuery = this.entrainementDao.getSelectDaoQuery();
				oDaoQuery.getSqlQuery().addToWhere(oDestInClause);
				listEntrainement.addAll(this.entrainementDao.getListEntrainement(oDaoQuery, p_oCascadeSet, p_oDaoSession, p_oContext));
			}

			for (Entrainement oEntrainements : listEntrainement) {
				List<String> listJoueurIds = p_oCascadeOptim.getSourceIdsOfJoinEntitiesByTargetId(JoueurCascade.ENTRAINEMENTS, oEntrainements
						.idToString());
				for (String sJoueurId : listJoueurIds) {
					Joueur oJoueur = (Joueur) p_oCascadeOptim.getEntity(sJoueurId);
					oJoueur.getEntrainements().add(oEntrainements);
				}
			}
		}

	}

}
