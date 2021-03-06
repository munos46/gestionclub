package com.adoliveira.gestionclub.loader;

import java.util.List;
import java.util.Set;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.AbstractListDataloader;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.DataloaderException;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.DataLoaderParts;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.context.MContext;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.beans.CascadeSet;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.services.BeanLoader;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.DaoException;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.DaoQuery;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.OrderAsc;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.OrderSet;
import com.dao.JoueurDao;
import com.dao.JoueurField;
import com.model.Joueur;

/**
 * 
 */
public class ListJoueurPanelLoaderImpl
//@non-generated-start[class-signature-extends][X]
		extends AbstractListDataloader<Joueur>
//@non-generated-end

		//@non-generated-start[class-signature]
		implements ListJoueurPanelLoader
//@non-generated-end
{
	//@non-generated-start[loadCascade][X]
	/**
	 * Load cascade
	 **/
	private static final CascadeSet LOAD_CASCADE = CascadeSet.NONE;

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
	protected List<Joueur> load(MContext p_oContext, String p_sKey, Set<DataLoaderParts> p_oReload) throws DataloaderException {
		List<Joueur> r_listJoueur = null;

		//@non-generated-start[load]
		JoueurDao oJoueurDao = BeanLoader.getInstance().getBean(JoueurDao.class);
		try {
			DaoQuery oQuery = oJoueurDao.getSelectDaoQuery();
			oQuery.getSqlQuery().setOrderBy(OrderSet.of(OrderAsc.of(JoueurField.NOM), OrderAsc.of(JoueurField.PRENOM)));

			r_listJoueur = oJoueurDao.getListJoueur(oQuery, LOAD_CASCADE, p_oContext);
		} catch (DaoException oDaoException) {
			throw new DataloaderException(oDaoException);
		}
		//@non-generated-end
		return r_listJoueur;
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
