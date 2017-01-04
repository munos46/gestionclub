package com.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.adeuza.movalysfwk.mf4jcommons.core.beans.Initializable;
import com.adeuza.movalysfwk.mobile.mf4android.database.sqlite.MDKSQLiteStatement;
import com.adeuza.movalysfwk.mobile.mf4android.jdbc.AndroidSQLiteConnection;
import com.adeuza.movalysfwk.mobile.mf4android.jdbc.AndroidSQLitePreparedStatement;
import com.adeuza.movalysfwk.mobile.mf4android.jdbc.AndroidSQLiteResultSet;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.business.doopenmap.AddressLocation;
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
import com.model.Lieu;
import com.model.LieuFactory;

/**
 * 
 * <p>Classe de DAO : AbstractLieuDaoImpl</p>
 */
public abstract class AbstractLieuDaoImpl extends AbstractEntityDao<Lieu> implements Initializable {

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
	 * Factory LieuFactory
	 */
	protected LieuFactory lieuFactory;

	/**
	 * Définition des optimisations des cascades
	 */
	protected CascadeOptimDefinition cascadeOptimDefinition;

	/**
	 * Initializes the private attributes of this DAO: factories and daos use by this dao.
	 */
	@Override
	public void initialize() {
		this.lieuFactory = BeanLoader.getInstance().getBean(LieuFactory.class);

		selectQuery = new SqlQuery();
		selectQuery.addFieldToSelect(LieuDao.ALIAS_NAME, LieuField.ID, LieuField.NOM, LieuField.POSITION_LATITUDE, LieuField.POSITION_LONGITUDE,
				LieuField.POSITION_COMPL, LieuField.POSITION_STREET, LieuField.POSITION_CITY, LieuField.POSITION_COUNTRY);
		selectQuery.addToFrom(LieuDao.TABLE_NAME, LieuDao.ALIAS_NAME);

		countQuery = new SqlQuery();
		countQuery.addCountToSelect(LieuField.ID, LieuDao.ALIAS_NAME);
		countQuery.addToFrom(LieuDao.TABLE_NAME, LieuDao.ALIAS_NAME);

		insertQuery = new SqlInsert(LieuDao.TABLE_NAME);

		insertQuery.addBindedField(LieuField.ID);
		insertQuery.addBindedField(LieuField.NOM);
		insertQuery.addBindedField(LieuField.POSITION_LATITUDE);
		insertQuery.addBindedField(LieuField.POSITION_LONGITUDE);
		insertQuery.addBindedField(LieuField.POSITION_COMPL);
		insertQuery.addBindedField(LieuField.POSITION_STREET);
		insertQuery.addBindedField(LieuField.POSITION_CITY);
		insertQuery.addBindedField(LieuField.POSITION_COUNTRY);

		updateQuery = new SqlUpdate(LieuDao.TABLE_NAME);

		updateQuery.addBindedField(LieuField.ID);
		updateQuery.addBindedField(LieuField.NOM);
		updateQuery.addBindedField(LieuField.POSITION_LATITUDE);
		updateQuery.addBindedField(LieuField.POSITION_LONGITUDE);
		updateQuery.addBindedField(LieuField.POSITION_COMPL);
		updateQuery.addBindedField(LieuField.POSITION_STREET);
		updateQuery.addBindedField(LieuField.POSITION_CITY);
		updateQuery.addBindedField(LieuField.POSITION_COUNTRY);

		deleteQuery = new SqlDelete(LieuDao.TABLE_NAME);

		cascadeOptimDefinition = new CascadeOptimDefinition(LieuDao.PK_FIELDS, LieuDao.ALIAS_NAME);

	}

	/**
	 * Initializes the private attributes of this DAO: factories and daos use by this dao.
	 * @param p_oContext the context to use
	 * @throws DaoException if any
	 */
	public void initialize(MContext p_oContext) throws DaoException {
		SqlQuery oSelect = new SqlQuery();
		oSelect.addFunctionToSelect(new SqlFunctionSelectPart(SqlFunction.MIN, LieuField.ID, LieuDao.ALIAS_NAME));
		oSelect.addToFrom(LieuDao.TABLE_NAME, LieuDao.ALIAS_NAME);
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
		return LieuDao.TABLE_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getAliasName() {
		return LieuDao.ALIAS_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getEntityName() {
		return Lieu.ENTITY_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FieldType[] getPKFields() {
		return LieuDao.PK_FIELDS;
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
	 * Retourne une entité Lieu selon la clé primaire
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oContext contexte transactionnel
	 * @return une entité Lieu selon la clé primaire
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public Lieu getLieu(long p_lId, MContext p_oContext) throws DaoException {
		return this.getLieu(p_lId, this.getSelectDaoQuery(), CascadeSet.NONE, new DaoSession(), p_oContext);
	}

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
	public Lieu getLieu(long p_lId, DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException {
		return this.getLieu(p_lId, p_oDaoQuery, CascadeSet.NONE, new DaoSession(), p_oContext);
	}

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
	public Lieu getLieu(long p_lId, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		return this.getLieu(p_lId, this.getSelectDaoQuery(), p_oCascadeSet, new DaoSession(), p_oContext);
	}

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
	public Lieu getLieu(long p_lId, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getLieu(p_lId, this.getSelectDaoQuery(), CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

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
	public Lieu getLieu(long p_lId, DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		return this.getLieu(p_lId, p_oDaoQuery, p_oCascadeSet, new DaoSession(), p_oContext);
	}

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
	public Lieu getLieu(long p_lId, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getLieu(p_lId, p_oDaoQuery, CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

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
	public Lieu getLieu(long p_lId, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getLieu(p_lId, this.getSelectDaoQuery(), p_oCascadeSet, p_oDaoSession, p_oContext);
	}

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
			throws DaoException {
		Lieu r_oLieu = null;
		p_oDaoQuery.getSqlQuery().addEqualsConditionToWhere(LieuField.ID, LieuDao.ALIAS_NAME, p_lId, SqlType.INTEGER);
		AndroidSQLitePreparedStatement oStatement = p_oDaoQuery.prepareStatement(p_oContext);

		try {
			p_oDaoQuery.bindValues(oStatement);
			ResultSetReader oResultSetReader = new ResultSetReader(oStatement.executeQuery());
			try {
				while (oResultSetReader.next()) {
					r_oLieu = this.valueObject(oResultSetReader, p_oDaoQuery, p_oDaoSession, p_oCascadeSet, p_oContext);
				}
			} finally {
				oResultSetReader.close();
			}
		} finally {
			oStatement.close();
		}

		return r_oLieu;
	}

	/**
	 * Retourne la liste de toutes les entités Lieu.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Lieu
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Lieu> getListLieu(MContext p_oContext) throws DaoException {
		return this.getListLieu(getSelectDaoQuery(), CascadeSet.NONE, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne la liste des entités Lieu selon la requête.
	 * La cascade est CascadeSet.NONE
	 * @param p_oDaoQuery requête
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Lieu
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Lieu> getListLieu(DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException {
		return this.getListLieu(p_oDaoQuery, CascadeSet.NONE, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne la liste de toutes les entités Lieu.
	 * Les blocs par défaut sont utilisés
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Lieu
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Lieu> getListLieu(CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		return this.getListLieu(getSelectDaoQuery(), p_oCascadeSet, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne la liste de toutes les entités Lieu.
	 * Les blocs par défaut sont utilisés
	 * La cascade est CascadeSet.NONE
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Lieu
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Lieu> getListLieu(DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getListLieu(getSelectDaoQuery(), CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne la liste des entités Lieu selon la requête.
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Lieu
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Lieu> getListLieu(DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		return this.getListLieu(p_oDaoQuery, p_oCascadeSet, new DaoSession(), p_oContext);
	}

	/**
	 * Retourne la liste des entités Lieu selon la requête.
	 * La cascade est CascadeSet.NONE
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Lieu
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Lieu> getListLieu(DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getListLieu(p_oDaoQuery, CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne la liste de toutes les entités Lieu.
	 * Les blocs par défaut sont utilisés
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Lieu
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Lieu> getListLieu(CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		return this.getListLieu(getSelectDaoQuery(), p_oCascadeSet, p_oDaoSession, p_oContext);
	}

	/**
	 * Retourne la liste des entités Lieu selon la requête.
	 * @param p_oDaoQuery requête
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession session Dao
	 * @param p_oContext contexte transactionnel
	 * @return une liste d'entité Lieu
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public List<Lieu> getListLieu(DaoQuery p_oDaoQuery, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {

		return this.getList(p_oDaoQuery, p_oCascadeSet, p_oDaoSession, p_oContext);
	}

	/**
	 * Sauve ou met à jour l'entité Lieu passée en paramètre selon son existence en base.
	 * La cascade est CascadeSet.NONE
	 * @param p_oLieu une entité Lieu
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateLieu(Lieu p_oLieu, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		this.saveOrUpdateLieu(p_oLieu, CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Sauve ou met à jour l'entité Lieu passée en paramètre selon son existence en base.
	 * @param p_oLieu une entité Lieu
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateLieu(Lieu p_oLieu, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		this.saveOrUpdateLieu(p_oLieu, p_oCascadeSet, new DaoSession(), p_oContext);
	}

	/**
	 * Sauve ou met à jour l'entité Lieu passée en paramètre selon son existence en base.
	 * @param p_oLieu une entité Lieu
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateLieu(Lieu p_oLieu, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {

		if (!p_oDaoSession.isAlreadySaved(Lieu.ENTITY_NAME, p_oLieu)) {

			this.validateBean(p_oLieu, p_oContext);
			boolean bHaveErrorMessage = p_oContext.getMessages().hasErrors();

			if (!bHaveErrorMessage) {

				if (this.exist(p_oLieu, p_oCascadeSet, p_oContext)) {
					this.updateLieu(p_oLieu, p_oCascadeSet, p_oDaoSession, p_oContext);
				} else {

					if (p_oLieu.getId() < 0L) {
						p_oLieu.setId(this.nextId());
					}

					this.saveLieu(p_oLieu, p_oCascadeSet, p_oDaoSession, p_oContext);
				}

			}
		}
	}

	/**
	 * Sauve ou met à jour laliste d'entité Lieu passée en paramètre selon leur existence en base.
	 * La cascade est CascadeSet.NONE
	 * @param p_listLieu une liste d'entité Lieu
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateListLieu(Collection<Lieu> p_listLieu, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {
		this.saveOrUpdateListLieu(p_listLieu, CascadeSet.NONE, p_oDaoSession, p_oContext);
	}

	/**
	 * Sauve ou met à jour laliste d'entité Lieu passée en paramètre selon leur existence en base.
	 * @param p_listLieu une liste d'entité Lieu
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveOrUpdateListLieu(Collection<Lieu> p_listLieu, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {

		this.validateBeanList(p_listLieu, p_oContext);
		boolean bHaveErrorMessage = p_oContext.getMessages().hasErrors();

		if (!bHaveErrorMessage) {
			List<Lieu> listSave = new ArrayList<Lieu>();
			List<Lieu> listUpdate = new ArrayList<Lieu>();
			for (Lieu oLieu : p_listLieu) {
				if (this.exist(oLieu, p_oCascadeSet, p_oContext)) {
					listUpdate.add(oLieu);
				} else {

					if (oLieu.getId() < 0L) {
						oLieu.setId(this.nextId());
					}

					listSave.add(oLieu);
				}
			}

			if (!listSave.isEmpty()) {
				this.saveListLieu(listSave, p_oCascadeSet, p_oDaoSession, p_oContext);
			}
			if (!listUpdate.isEmpty()) {
				this.updateListLieu(listUpdate, p_oCascadeSet, p_oDaoSession, p_oContext);
			}
		}
	}

	/**
	 * Supprime l'entité Lieu passée en paramètre de la base de données.
	 * La cascade est this.getDeleteCascade()
	 * @param p_oLieu une entité Lieu
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteLieu(Lieu p_oLieu, MContext p_oContext) throws DaoException {
		this.deleteLieu(p_oLieu, this.getDeleteCascade(), p_oContext);
	}

	/**
	 * Supprime l'entité Lieu passée en paramètre de la base de données.
	 * @param p_oLieu une entité Lieu
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteLieu(Lieu p_oLieu, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {

		SqlDelete oSqlDelete = getDeleteQuery();
		oSqlDelete.addEqualsConditionToWhere(LieuField.ID, p_oLieu.getId(), SqlType.INTEGER);

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
	 * Supprime l'entité Lieu correspondant aux paramètres de la base de données.
	 * Cette suppression ne supprime pas les entités liés en cascade.
	 * 
	 * @param p_lId un paramètre de la clé primaire de type long
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteLieu(long p_lId, MContext p_oContext) throws DaoException {

		SqlDelete oSqlDelete = getDeleteQuery();
		oSqlDelete.addEqualsConditionToWhere(LieuField.ID, p_lId, SqlType.INTEGER);
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
	 * Supprime de la base de données la liste d'entité Lieu passée en paramètre.
	 * La cascade est CascadeSet.NONE
	 * @param p_listLieu une liste d'entité Lieu
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteListLieu(Collection<Lieu> p_listLieu, MContext p_oContext) throws DaoException {
		this.deleteListLieu(p_listLieu, CascadeSet.NONE, p_oContext);
	}

	/**
	 * Supprime de la base de données la liste d'entité Lieu passée en paramètre.
	 * @param p_listLieu une liste d'entité Lieu
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void deleteListLieu(Collection<Lieu> p_listLieu, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {

		if (!p_listLieu.isEmpty()) {

			SqlDelete oSqlDelete = getDeleteQuery();
			SqlEqualsValueCondition oSqlEqualsValueCondition1 = oSqlDelete.addEqualsConditionToWhere(LieuField.ID, SqlType.INTEGER);

			AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
			AndroidSQLitePreparedStatement oStatement = oConnection.prepareStatement(oSqlDelete.toSql(p_oContext));
			try {
				for (Lieu oLieu : p_listLieu) {
					oSqlEqualsValueCondition1.setValue(oLieu.getId());

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
	 * Retourne le nombre d'entité Lieu en base.
	 * Les blocs par défaut sont utilisés
	 * @param p_oContext contexte transactionnel
	 * @return le nombre d'entité Lieu
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public int getNbLieu(MContext p_oContext) throws DaoException {
		return this.getNbLieu(getCountDaoQuery(), p_oContext);
	}

	/**
	 * Retourne le nombre d'entité Lieu selon la requête.
	 * @param p_oDaoQuery requête
	 * @param p_oContext contexte transactionnel
	 * @return le nombre d'entité Lieu
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public int getNbLieu(DaoQuery p_oDaoQuery, MContext p_oContext) throws DaoException {
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
	 * Saves a Lieu entity with the cascade sent as parameter
	 * @param p_oLieu une entité Lieu
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveLieu(Lieu p_oLieu, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {

		if (!p_oDaoSession.isAlreadySaved(Lieu.ENTITY_NAME, p_oLieu)) {
			p_oDaoSession.markAsSaved(Lieu.ENTITY_NAME, p_oLieu);

			if (!p_oContext.getMessages().hasErrors()) {
				SqlInsert oSqlInsert = this.getInsertQuery();
				AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
				MDKSQLiteStatement oStatement = oConnection.compileStatement(oSqlInsert.toSql(p_oContext));
				try {
					bindInsert(p_oLieu, oStatement, p_oContext);
					oStatement.executeUpdate();

					p_oLieu.setOldId(p_oLieu.getId());

				} finally {
					oStatement.close();
				}
			}

		}
	}

	/**
	 * Saves a list of Lieu entities with the cascade sent as parameter
	 * @param p_listLieu une liste d'entité Lieu
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void saveListLieu(Collection<Lieu> p_listLieu, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {

		Collection<Lieu> listLieuToSave = p_oDaoSession.getEntitiesToPersist(Lieu.ENTITY_NAME, p_listLieu, true);

		if (!p_oContext.getMessages().hasErrors()) {
			SqlInsert oSqlInsert = this.getInsertQuery();
			AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
			MDKSQLiteStatement oStatement = oConnection.compileStatement(oSqlInsert.toSql(p_oContext));
			try {
				for (Lieu oLieu : listLieuToSave) {
					bindInsert(oLieu, oStatement, p_oContext);
					oStatement.executeUpdate();
				}

				for (Lieu oLieu : listLieuToSave) {
					oLieu.setOldId(oLieu.getId());
				}

			} finally {
				oStatement.close();
			}
		}

	}

	/**
	 * Updates the given entity in database
	 * @param p_oLieu une entité Lieu
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void updateLieu(Lieu p_oLieu, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext) throws DaoException {

		if (!p_oDaoSession.isAlreadySaved(Lieu.ENTITY_NAME, p_oLieu)) {
			p_oDaoSession.markAsSaved(Lieu.ENTITY_NAME, p_oLieu);

			if (!p_oContext.getMessages().hasErrors()) {

				SqlUpdate oSqlUpdate = this.getUpdateQuery();
				oSqlUpdate.addEqualsConditionToWhere(LieuField.ID, SqlType.INTEGER);
				AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
				MDKSQLiteStatement oStatement = oConnection.compileStatement(oSqlUpdate.toSql(p_oContext));
				try {
					bindUpdate(p_oLieu, oStatement, p_oContext);
					oStatement.executeUpdate();

					p_oLieu.setOldId(p_oLieu.getId());

				} finally {
					oStatement.close();
				}
			}

		}
	}

	/**
	 * Updates the given list of entities in the database
	 * @param p_listLieu une liste d'entité Lieu
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oDaoSession dao session	 
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	public void updateListLieu(Collection<Lieu> p_listLieu, CascadeSet p_oCascadeSet, DaoSession p_oDaoSession, MContext p_oContext)
			throws DaoException {

		Collection<Lieu> listLieuToUpdate = p_oDaoSession.getEntitiesToPersist(Lieu.ENTITY_NAME, p_listLieu, true);

		if (!p_oContext.getMessages().hasErrors()) {

			SqlUpdate oSqlUpdate = this.getUpdateQuery();
			oSqlUpdate.addEqualsConditionToWhere(LieuField.ID, SqlType.INTEGER);
			AndroidSQLiteConnection oConnection = ((MContextImpl) p_oContext).getConnection();
			MDKSQLiteStatement oStatement = oConnection.compileStatement(oSqlUpdate.toSql(p_oContext));
			try {
				for (Lieu oLieu : listLieuToUpdate) {
					bindUpdate(oLieu, oStatement, p_oContext);
					oStatement.executeUpdate();
				}

				for (Lieu oLieu : listLieuToUpdate) {
					oLieu.setOldId(oLieu.getId());
				}

			} finally {
				oStatement.close();
			}
		}

	}

	/**
	 * Returns true if the entity given in parameter exists
	 * @param p_oLieu une entité Lieu
	 * @param p_oCascadeSet ensemble de Cascades sur les entités	 
	 * @param p_oContext contexte transactionnel
	 * @return un boolean indiquant si l'entité existe en base
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	protected boolean exist(Lieu p_oLieu, CascadeSet p_oCascadeSet, MContext p_oContext) throws DaoException {
		boolean r_bExist = false;
		DaoQuery oQuery = this.getSelectDaoQuery();
		oQuery.getSqlQuery().addEqualsConditionToWhere(LieuField.ID, LieuDao.ALIAS_NAME, p_oLieu.getId(), SqlType.INTEGER);
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
	 * Returns an instance of a Lieu object from a given ResultSetReader	 * @param p_oResultSetReader résultat de la requête
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oContext contexte transactionnel
	 * @return l'entité Lieu.
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	@Override
	protected Lieu valueObject(ResultSetReader p_oResultSetReader, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, CascadeSet p_oCascadeSet,
			MContext p_oContext) throws DaoException {

		Lieu r_oLieu = this.lieuFactory.createInstance();
		r_oLieu.setId(p_oResultSetReader.getLong());
		String sEntityId = r_oLieu.idToString();
		Lieu oCachedLieu = (Lieu) p_oDaoSession.getFromCache(Lieu.ENTITY_NAME, sEntityId);
		if (oCachedLieu == null) {
			p_oDaoSession.addToCache(Lieu.ENTITY_NAME, sEntityId, r_oLieu);
			r_oLieu.setOldId(r_oLieu.getId());
			r_oLieu.setNom(p_oResultSetReader.getString());
			r_oLieu.setPosition(com.adeuza.movalysfwk.mobile.mf4mjcommons.business.doopenmap.AddressLocationHelper.readResultSet(p_oResultSetReader));
		} else {
			r_oLieu = oCachedLieu;
		}

		p_oDaoQuery.doResultSetCustomRead(r_oLieu, p_oResultSetReader, p_oDaoSession, p_oCascadeSet);

		return r_oLieu;
	}

	/**
	 * Returns an instance of a Lieu object from a given ResultSetReader	 * @param p_oResultSetReader reader de resultset
	 * @param p_oDaoQuery requête
	 * @param p_oDaoSession session Dao
	 * @param p_oCascadeSet ensemble de Cascades sur les entités
	 * @param p_oCascadeOptim optimiseur de l'éxécution des cascades
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException.
	 * @return l'entité Lieu.
	 */
	@Override
	protected Lieu valueObject(ResultSetReader p_oResultSetReader, DaoQuery p_oDaoQuery, DaoSession p_oDaoSession, CascadeSet p_oCascadeSet,
			CascadeOptim p_oCascadeOptim, MContext p_oContext) throws DaoException {

		Lieu r_oLieu = this.lieuFactory.createInstance();

		r_oLieu.setId(p_oResultSetReader.getLong());

		String sEntityId = r_oLieu.idToString();
		Lieu oCachedLieu = (Lieu) p_oDaoSession.getFromCache(Lieu.ENTITY_NAME, sEntityId);
		if (oCachedLieu == null) {
			p_oDaoSession.addToCache(Lieu.ENTITY_NAME, sEntityId, r_oLieu);
			p_oCascadeOptim.registerEntity(sEntityId, r_oLieu, r_oLieu.getId());
			r_oLieu.setOldId(r_oLieu.getId());
			r_oLieu.setNom(p_oResultSetReader.getString());
			r_oLieu.setPosition(com.adeuza.movalysfwk.mobile.mf4mjcommons.business.doopenmap.AddressLocationHelper.readResultSet(p_oResultSetReader));
		} else {
			r_oLieu = oCachedLieu;
		}

		p_oDaoQuery.doResultSetCustomRead(r_oLieu, p_oResultSetReader, p_oDaoSession, p_oCascadeSet);

		return r_oLieu;
	}

	/**
	 * Bind un prepareStatement d'insertion
	 * @param p_oLieu une entité Lieu à insérer
	 * @param p_oPreparedStatement requête SQL précompilée
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	@Override
	protected void bindInsert(Lieu p_oLieu, MDKSQLiteStatement p_oStatement, MContext p_oContext) throws DaoException {
		p_oStatement.bindLong(p_oLieu.getId());
		p_oStatement.bindString(p_oLieu.getNom());
		com.adeuza.movalysfwk.mobile.mf4mjcommons.business.doopenmap.AddressLocationHelper.bindToStatement(p_oLieu.getPosition(), p_oStatement);

	}

	/**
	 * Bind un preparedStatement de mise à jour
	 * @param p_oLieu une entité Lieu à mettre à jour
	 * @param p_oPreparedStatement requête SQL précompilée
	 * @param p_oContext contexte transactionnel
	 * @throws DaoException déclenchée si une exception technique survient
	 */
	@Override
	protected void bindUpdate(Lieu p_oLieu, MDKSQLiteStatement p_oStatement, MContext p_oContext) throws DaoException {
		p_oStatement.bindLong(p_oLieu.getId());
		p_oStatement.bindString(p_oLieu.getNom());
		com.adeuza.movalysfwk.mobile.mf4mjcommons.business.doopenmap.AddressLocationHelper.bindToStatement(p_oLieu.getPosition(), p_oStatement);
		p_oStatement.bindLong(p_oLieu.getOldId());

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
