package com.adoliveira.gestionclub.loader;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.AbstractListDataloader;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.DataloaderException;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.DataLoaderParts;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.context.MContext;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.context.MContextFactory;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.beans.CascadeSet;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.services.BeanLoader;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.DaoException;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.DaoQuery;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.OrderAsc;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.OrderSet;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.SqlType;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.query.SqlBindValue;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.query.SqlField;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.query.conditions.OperatorCondition;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.query.conditions.SqlCompareValueCondition;
import com.dao.EntrainementDao;
import com.dao.EntrainementField;
import com.dao.JoueurField;
import com.dao.LieuDao;
import com.model.Entrainement;
import com.model.EntrainementCascade;
import com.model.Lieu;
import com.model.LieuImpl;

/**
 * 
 */
public class ListEntrainPanelLoaderImpl
//@non-generated-start[class-signature-extends][X]
		extends AbstractListDataloader<Entrainement>
//@non-generated-end

		//@non-generated-start[class-signature]
		implements ListEntrainPanelLoader
//@non-generated-end
{
	//@non-generated-start[loadCascade][X]
	/**
	 * Load cascade
	 **/
	private static final CascadeSet LOAD_CASCADE = CascadeSet.of(EntrainementCascade.LIEU);

	//@non-generated-end

	//@non-generated-start[attributes]
	//@non-generated-end

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DataLoaderParts[] getAllReload() {
		return DataLoaderPartEnum.values();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Entrainement> load(MContext p_oContext, String p_sKey, Set<DataLoaderParts> p_oReload) throws DataloaderException {
		List<Entrainement> r_listEntrainement = null;

		//@non-generated-start[load]
		// On va lire que les entrainements futur et ceux d'il y a 2 jours
		EntrainementDao oEntrainementDao = BeanLoader.getInstance().getBean(EntrainementDao.class);

		MContextFactory oMContextFactory = (MContextFactory) BeanLoader.getInstance().getBean(MContextFactory.class);
		MContext context = oMContextFactory.createContext();

		DaoQuery oQuery = oEntrainementDao.getSelectDaoQuery();

		GregorianCalendar date = new GregorianCalendar();
		date.add(GregorianCalendar.DAY_OF_YEAR, -2);

		Date dateSql = new Date(date.getTimeInMillis());

		SqlBindValue sqlBindMin = new SqlBindValue(dateSql, SqlType.DATE);
		SqlCompareValueCondition sqlcomparevaluecondition1 = new SqlCompareValueCondition(new SqlField(EntrainementField.DATEHEURE), sqlBindMin,
				OperatorCondition.SUPERIOR_OR_EQUALS);

		oQuery.getSqlQuery().addToWhere(sqlcomparevaluecondition1);

		try {
			r_listEntrainement = oEntrainementDao.getListEntrainement(oQuery, LOAD_CASCADE, p_oContext);
		} catch (DaoException oDaoException) {
			throw new DataloaderException(oDaoException);
		}
		//@non-generated-end
		return r_listEntrainement;
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
