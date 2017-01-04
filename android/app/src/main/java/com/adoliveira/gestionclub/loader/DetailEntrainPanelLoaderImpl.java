package com.adoliveira.gestionclub.loader;

import java.util.List;
import java.util.Set;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.AbstractDataloader;
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
public class DetailEntrainPanelLoaderImpl
//@non-generated-start[class-signature-extends][X]
		extends AbstractDataloader<Entrainement>
//@non-generated-end

		//@non-generated-start[class-signature]
		implements DetailEntrainPanelLoader
//@non-generated-end
{
	//@non-generated-start[loadCascade]
	/**
	 * Load cascade
	 **/
	private static final CascadeSet LOAD_CASCADE = CascadeSet.of(EntrainementCascade.LIEU, EntrainementCascade.JOUEUR, EntrainementCascade.JOUEURS);

	//@non-generated-end

	/** attribute for Lieu combo values */
	private List<Lieu> lieu = null;

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
	protected Entrainement load(MContext p_oContext, String p_sKey, Set<DataLoaderParts> p_oReload) throws DataloaderException {

		Entrainement r_oEntrainement = null;

		//@non-generated-start[load][X]
		EntrainementDao oEntrainementDao = BeanLoader.getInstance().getBean(EntrainementDao.class);
		LieuDao oLieuDao = BeanLoader.getInstance().getBean(LieuDao.class);

		try {
			if (p_oReload.contains(DataLoaderPartEnum.DATA)) {
				r_oEntrainement = oEntrainementDao.getEntrainement(this.getItemId(p_sKey), LOAD_CASCADE, p_oContext);
			} else {
				r_oEntrainement = this.getData(p_sKey);
			}
			if (p_oReload.contains(DataLoaderPartEnum.LIEU)) {
				this.lieu = oLieuDao.getListLieu(CascadeSet.NONE, p_oContext);
			}
		} catch (DaoException e) {
			throw new DataloaderException(e);
		}
		//@non-generated-end
		return r_oEntrainement;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Lieu> getListLieu() {
		return this.lieu;
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
