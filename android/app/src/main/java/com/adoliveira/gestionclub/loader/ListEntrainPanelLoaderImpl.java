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
import com.dao.EntrainementDao;
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

		//@non-generated-start[load][X]
		EntrainementDao oEntrainementDao = BeanLoader.getInstance().getBean(EntrainementDao.class);
		try {
			r_listEntrainement = oEntrainementDao.getListEntrainement(LOAD_CASCADE, p_oContext);
		} catch (DaoException oDaoException) {
			throw new DataloaderException(oDaoException);
		}
		//@non-generated-end
		return r_listEntrainement;
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
