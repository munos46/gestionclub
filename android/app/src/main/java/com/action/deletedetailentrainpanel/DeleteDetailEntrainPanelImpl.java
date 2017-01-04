package com.action.deletedetailentrainpanel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.action.deletedetailentrainpanel.DeleteDetailEntrainPanel;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericdelete.AbstractPersistentDeleteActionImpl;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.DataloaderException;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.action.ActionException;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.action.NullActionParameterImpl;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.application.Application;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.context.MContext;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.beans.CascadeSet;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.services.BeanLoader;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.DaoException;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.MObjectToSynchronizeDao;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.MObjectToSynchronizeField;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.query.SqlDelete;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.SqlType;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.factory.MObjectToSynchronizeFactory;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.messages.ExtFwkErrors;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.messages.MessageLevel;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.model.MObjectToSynchronize;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ExpandableViewModel;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.ui.model.ListViewModel;
import com.adoliveira.gestionclub.loader.DetailEntrainPanelLoader;
import com.adoliveira.gestionclub.viewmodel.ViewModelCreator;
import com.dao.EntrainementDao;
import com.model.Entrainement;
import com.model.EntrainementCascade;
import com.model.EntrainJoueur;
import com.model.Joueur;
import com.model.Lieu;
import com.viewmodel.VMDetailEntrainPanel;

/**
 * 
 */
public class DeleteDetailEntrainPanelImpl
//@non-generated-start[class-signature-extends][X]
		extends AbstractPersistentDeleteActionImpl<Entrainement>
//@non-generated-end
		implements DeleteDetailEntrainPanel
//@non-generated-start[class-signature]
//@non-generated-end
{
	//@non-generated-start[attributes]
	//@non-generated-end

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Entrainement deleteData(MContext p_oContext, NullActionParameterImpl p_oInParameter) throws ActionException {
		Entrainement r_oEntrainementToDelete = null;
		//@non-generated-start[method-delete-data][X]
		try {
			EntrainementDao oDao = BeanLoader.getInstance().getBean(EntrainementDao.class);
			// ancien objet avant modification 
			if (BeanLoader.getInstance().getBean(DetailEntrainPanelLoader.class).getData(DetailEntrainPanelLoader.DEFAULT_KEY) != null) {
				r_oEntrainementToDelete = BeanLoader.getInstance().getBean(DetailEntrainPanelLoader.class).getData(
						DetailEntrainPanelLoader.DEFAULT_KEY);
				if (r_oEntrainementToDelete != null) {
					oDao.deleteEntrainement(r_oEntrainementToDelete, p_oContext);
				}
			}

			BeanLoader.getInstance().getBean(DetailEntrainPanelLoader.class).addData(DetailEntrainPanelLoader.DEFAULT_KEY, null);
			p_oContext.registerEvent(new DeleteEntrainementEvent(this, r_oEntrainementToDelete));
		} catch (DaoException oException) {
			throw new ActionException(oException);
		}
		//@non-generated-end
		return r_oEntrainementToDelete;
	}

	/**
	 * {@inheritDoc}
	 */
	protected void toSynchronize(MContext p_oContext, Entrainement p_oEntity) throws ActionException {
		//@non-generated-start[method-to-synchronize][X]
		try {
			MObjectToSynchronizeDao oDao = BeanLoader.getInstance().getBean(MObjectToSynchronizeDao.class);
			if (p_oEntity != null) {
				// Entité précédement créée sur le mobile. Sa suppression doit également vider T_MOBJECTTOSYNCHRONIZE
				if (p_oEntity.getId() < 0) {
					SqlDelete oDelete = oDao.getDeleteQuery();
					oDelete.addEqualsConditionToWhere(MObjectToSynchronizeField.OBJECTID, p_oEntity.getId(), SqlType.INTEGER);
					oDelete.addEqualsConditionToWhere(MObjectToSynchronizeField.OBJECTNAME, "com.model.Entrainement", SqlType.VARCHAR);
					oDao.genericDelete(oDelete, p_oContext);
				} else {
					MObjectToSynchronize oObject2Synchronize = BeanLoader.getInstance().getBean(MObjectToSynchronizeFactory.class).createInstance();
					oObject2Synchronize.setObjectId(p_oEntity.getId());
					oObject2Synchronize.setObjectName("com.model.Entrainement");
					oDao.saveOrUpdateMObjectToSynchronize(oObject2Synchronize, p_oContext);
				}
			}
		} catch (DaoException oDaoException) {
			throw new ActionException(oDaoException);
		}
		//@non-generated-end
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
