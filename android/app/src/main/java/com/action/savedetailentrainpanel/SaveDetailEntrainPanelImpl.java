package com.action.savedetailentrainpanel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.action.savedetailentrainpanel.SaveDetailEntrainPanel;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericsave.AbstractPersistentSaveDetailActionImpl;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.DataloaderException;
import com.adeuza.movalysfwk.mobile.mf4javacommons.event.BusinessEvent;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.action.Action;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.action.ActionException;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.action.NullActionParameterImpl;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.application.Application;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.context.MContext;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.beans.CascadeSet;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.services.BeanLoader;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.DaoException;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.MObjectToSynchronizeDao;
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
import com.model.EntrainementFactory;
import com.model.EntrainJoueur;
import com.model.Joueur;
import com.model.Lieu;
import com.viewmodel.VMDetailEntrainPanel;

/**
 * 
 */
public class SaveDetailEntrainPanelImpl
//@non-generated-start[class-signature-extends][X]
		extends AbstractPersistentSaveDetailActionImpl<Entrainement>
//@non-generated-end
		implements SaveDetailEntrainPanel
//@non-generated-start[class-signature]
//@non-generated-end
{
	//@non-generated-start[attributes]
	//@non-generated-end

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericsave.SaveDetailAction#validateData(com.adeuza.movalysfwk.mobile.mf4mjcommons.context.MContext, com.adeuza.movalysfwk.mobile.mf4mjcommons.action.NullActionParameterImpl)
	 */
	@Override
	public boolean validateData(NullActionParameterImpl p_oParameterIn, MContext p_oContext) throws ActionException {

		// on récupère la vue model parent liée
		VMDetailEntrainPanel oVMLinkedPanelMain = Application.getInstance().getViewModelCreator().getViewModel(VMDetailEntrainPanel.class);

		// validComponents retourne true if une erreur est détectée.
		boolean r_bValid = !oVMLinkedPanelMain.validComponents(p_oContext, p_oParameterIn == null ? null : p_oParameterIn.getRuleParameters());
		if (r_bValid) {
			int iErrorsNumber = p_oContext.getNumberOfMessagesByLevel(MessageLevel.ERROR);
			r_bValid = oVMLinkedPanelMain.validViewModel(p_oContext, p_oParameterIn == null ? null : p_oParameterIn.getRuleParameters());
			r_bValid = oVMLinkedPanelMain.validateUserErrors(p_oContext) && r_bValid;

			// si une erreur est détectée et qu'aucun message d'erreur n'a été ajouté au contexte, ajout du message par défaut.
			if (iErrorsNumber == p_oContext.getNumberOfMessagesByLevel(MessageLevel.ERROR) && !r_bValid) {
				p_oContext.getMessages().addMessage(ExtFwkErrors.InvalidViewModelDataError);
			}
		} else {
			// Des problèmes existent dans les composants, on n'évalue donc pas la validité du viewmodel car cela pourrait être source d'erreurs
			p_oContext.getMessages().addMessage(ExtFwkErrors.InvalidViewModelDataError);
		}

		return r_bValid;
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericsave.AbstractSaveDetailActionImpl#saveData(com.adeuza.movalysfwk.mf4jcommons.core.beans.MEntity, com.adeuza.movalysfwk.mobile.mf4mjcommons.context.MContext, com.adeuza.movalysfwk.mobile.mf4mjcommons.action.NullActionParameterImpl)
	 */
	@Override
	public Entrainement saveData(Entrainement p_oEntrainement, NullActionParameterImpl p_oInParameter, MContext p_oContext) throws ActionException {
		//@non-generated-start[method-save-data][X]
		Entrainement r_oEntrainement = p_oEntrainement;

		try {
			EntrainementDao oDao = BeanLoader.getInstance().getBean(EntrainementDao.class);

			oDao.saveOrUpdateEntrainement(p_oEntrainement, CascadeSet.of(), p_oContext);

		} catch (DaoException oException) {
			throw new ActionException(oException);
		}

		return r_oEntrainement;
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericsave.SaveDetailAction#preSaveData(com.adeuza.movalysfwk.mobile.mf4mjcommons.action.NullActionParameterImpl, com.adeuza.movalysfwk.mobile.mf4mjcommons.context.MContext)
	 */
	@Override
	public Entrainement preSaveData(NullActionParameterImpl p_oParameterIn, MContext p_oContext) throws ActionException {

		Entrainement r_oEntrainementToSave = null;

		//@non-generated-start[method-presave-data][X]
		// ancien objet avant modification 
		r_oEntrainementToSave = BeanLoader.getInstance().getBean(DetailEntrainPanelLoader.class).getData(DetailEntrainPanelLoader.DEFAULT_KEY);
		if (r_oEntrainementToSave == null) {
			r_oEntrainementToSave = BeanLoader.getInstance().getBean(EntrainementFactory.class).createInstance();
			BeanLoader.getInstance().getBean(DetailEntrainPanelLoader.class).addData(DetailEntrainPanelLoader.DEFAULT_KEY, r_oEntrainementToSave);
		}

		// on récupère la vue model parent liée
		VMDetailEntrainPanel oVMLinkedPanelMain = Application.getInstance().getViewModelCreator().getViewModel(VMDetailEntrainPanel.class);

		oVMLinkedPanelMain.modifyToIdentifiable(r_oEntrainementToSave);

		this.modifyEntityBeforeSave(r_oEntrainementToSave); // for overloading
		//@non-generated-end

		return r_oEntrainementToSave;
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericsave.SaveDetailAction#postSaveData(com.adeuza.movalysfwk.mf4jcommons.core.beans.MEntity, com.adeuza.movalysfwk.mobile.mf4mjcommons.action.NullActionParameterImpl, com.adeuza.movalysfwk.mobile.mf4mjcommons.context.MContext)
	 */
	@Override
	public void postSaveData(Entrainement p_oEntrainement, NullActionParameterImpl p_oParameterIn, MContext p_oContext) throws ActionException {

		//@non-generated-start[method-postsave-data][X]

		// on récupère la vue model parent liée
		VMDetailEntrainPanel oVMLinkedPanelMain = Application.getInstance().getViewModelCreator().getViewModel(VMDetailEntrainPanel.class);

		long lVmId = oVMLinkedPanelMain.getId_id();
		long lEntityId = p_oEntrainement.getId();

		boolean bCreation = !(lVmId == lEntityId);

		oVMLinkedPanelMain.updateFromIdentifiable(p_oEntrainement);

		// TODO uncomment if view model are not syncronized anymore
		// oVMLinkedPanelMain.doOnDataLoaded(p_oParameterIn.getRuleParameters());

		BusinessEvent<Entrainement> oEvent = null;
		// si l'id du view modele a change on modifie le cache des view models
		if (bCreation) {
			oEvent = new AddEntrainementEvent(this, p_oEntrainement);
		} else {
			oEvent = new ChangeEntrainementEvent(this, p_oEntrainement);
		}
		p_oContext.registerEvent(oEvent);
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Collection<MObjectToSynchronize> toSynchronize(MContext p_oContext, Entrainement p_oEntity) throws ActionException {
		//@non-generated-start[method-to-synchronize][X]
		final Collection<MObjectToSynchronize> r_listObjectToSync = new ArrayList<MObjectToSynchronize>();
		MObjectToSynchronize oObject2Synchronize = BeanLoader.getInstance().getBean(MObjectToSynchronizeFactory.class).createInstance();
		r_listObjectToSync.add(oObject2Synchronize);
		oObject2Synchronize.setObjectId(p_oEntity.getId());
		oObject2Synchronize.setObjectName(Entrainement.class.getName());
		return r_listObjectToSync;
		//@non-generated-end
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
