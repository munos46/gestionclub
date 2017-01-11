package com.adoliveira.gestionclub.loader;

import java.util.Set;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.AbstractDataloader;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.DataloaderException;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.DataLoaderParts;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.context.MContext;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.beans.CascadeSet;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.services.BeanLoader;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.DaoException;
import com.dao.JoueurDao;
import com.model.Joueur;

/**
 * 
 */
public class DetailJoueurPanelLoaderImpl
//@non-generated-start[class-signature-extends][X]
		extends AbstractDataloader<Joueur>
//@non-generated-end

		//@non-generated-start[class-signature]
		implements DetailJoueurPanelLoader
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
	protected Joueur load(MContext p_oContext, String p_sKey, Set<DataLoaderParts> p_oReload) throws DataloaderException {

		Joueur r_oJoueur = null;

		//@non-generated-start[load][X]
		JoueurDao oJoueurDao = BeanLoader.getInstance().getBean(JoueurDao.class);
		try {
			if (p_oReload.contains(DataLoaderPartEnum.DATA)) {
				r_oJoueur = oJoueurDao.getJoueur(this.getItemId(p_sKey), LOAD_CASCADE, p_oContext);
			} else {
				r_oJoueur = this.getData(p_sKey);
			}
		} catch (DaoException e) {
			throw new DataloaderException(e);
		}
		//@non-generated-end
		return r_oJoueur;

	}

	//@non-generated-start[methods]
	//@non-generated-end
}
