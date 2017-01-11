package com.action.deletedetailjoueurpanel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.action.deletedetailjoueurpanel.DeleteDetailJoueurPanel;
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
import com.adoliveira.gestionclub.loader.DetailJoueurPanelLoader;
import com.adoliveira.gestionclub.viewmodel.ViewModelCreator;
import com.dao.JoueurDao;
import com.model.Entrainement;
import com.model.EntrainJoueur;
import com.model.Joueur;
import com.viewmodel.VMDetailJoueurPanel;

/**
 * 
 */
public class DeleteDetailJoueurPanelImpl
//@non-generated-start[class-signature-extends][X]
		extends AbstractPersistentDeleteActionImpl<Joueur>
//@non-generated-end
		implements DeleteDetailJoueurPanel
//@non-generated-start[class-signature]
//@non-generated-end
{
	//@non-generated-start[attributes]
	//@non-generated-end

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Joueur deleteData(MContext p_oContext, NullActionParameterImpl p_oInParameter) throws ActionException {
		Joueur r_oJoueurToDelete = null;
		//@non-generated-start[method-delete-data][X]
		try {
			JoueurDao oDao = BeanLoader.getInstance().getBean(JoueurDao.class);
			// ancien objet avant modification 
			if (BeanLoader.getInstance().getBean(DetailJoueurPanelLoader.class).getData(DetailJoueurPanelLoader.DEFAULT_KEY) != null) {
				r_oJoueurToDelete = BeanLoader.getInstance().getBean(DetailJoueurPanelLoader.class).getData(DetailJoueurPanelLoader.DEFAULT_KEY);
				if (r_oJoueurToDelete != null) {
					oDao.deleteJoueur(r_oJoueurToDelete, p_oContext);
				}
			}

			BeanLoader.getInstance().getBean(DetailJoueurPanelLoader.class).addData(DetailJoueurPanelLoader.DEFAULT_KEY, null);
			p_oContext.registerEvent(new DeleteJoueurEvent(this, r_oJoueurToDelete));
		} catch (DaoException oException) {
			throw new ActionException(oException);
		}
		//@non-generated-end
		return r_oJoueurToDelete;
	}

	/**
	 * {@inheritDoc}
	 */
	protected void toSynchronize(MContext p_oContext, Joueur p_oEntity) throws ActionException {
		//@non-generated-start[method-to-synchronize][X]
		try {
			MObjectToSynchronizeDao oDao = BeanLoader.getInstance().getBean(MObjectToSynchronizeDao.class);
			if (p_oEntity != null) {
				// Entité précédement créée sur le mobile. Sa suppression doit également vider T_MOBJECTTOSYNCHRONIZE
				if (p_oEntity.getId() < 0) {
					SqlDelete oDelete = oDao.getDeleteQuery();
					oDelete.addEqualsConditionToWhere(MObjectToSynchronizeField.OBJECTID, p_oEntity.getId(), SqlType.INTEGER);
					oDelete.addEqualsConditionToWhere(MObjectToSynchronizeField.OBJECTNAME, "com.model.Joueur", SqlType.VARCHAR);
					oDao.genericDelete(oDelete, p_oContext);
				} else {
					MObjectToSynchronize oObject2Synchronize = BeanLoader.getInstance().getBean(MObjectToSynchronizeFactory.class).createInstance();
					oObject2Synchronize.setObjectId(p_oEntity.getId());
					oObject2Synchronize.setObjectName("com.model.Joueur");
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
