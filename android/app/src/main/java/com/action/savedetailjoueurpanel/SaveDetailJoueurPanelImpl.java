package com.action.savedetailjoueurpanel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.action.savedetailjoueurpanel.SaveDetailJoueurPanel;
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
import com.adoliveira.gestionclub.loader.DetailJoueurPanelLoader;
import com.adoliveira.gestionclub.viewmodel.ViewModelCreator;
import com.dao.JoueurDao;
import com.model.Entrainement;
import com.model.EntrainJoueur;
import com.model.Joueur;
import com.model.JoueurFactory;
import com.viewmodel.VMDetailJoueurPanel;

/**
 * 
 */
public class SaveDetailJoueurPanelImpl
//@non-generated-start[class-signature-extends][X]
		extends AbstractPersistentSaveDetailActionImpl<Joueur>
//@non-generated-end
		implements SaveDetailJoueurPanel
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
		VMDetailJoueurPanel oVMLinkedPanelMain = Application.getInstance().getViewModelCreator().getViewModel(VMDetailJoueurPanel.class);

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
	public Joueur saveData(Joueur p_oJoueur, NullActionParameterImpl p_oInParameter, MContext p_oContext) throws ActionException {
		//@non-generated-start[method-save-data][X]
		Joueur r_oJoueur = p_oJoueur;

		try {
			JoueurDao oDao = BeanLoader.getInstance().getBean(JoueurDao.class);

			oDao.saveOrUpdateJoueur(p_oJoueur, CascadeSet.of(), p_oContext);

		} catch (DaoException oException) {
			throw new ActionException(oException);
		}

		return r_oJoueur;
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericsave.SaveDetailAction#preSaveData(com.adeuza.movalysfwk.mobile.mf4mjcommons.action.NullActionParameterImpl, com.adeuza.movalysfwk.mobile.mf4mjcommons.context.MContext)
	 */
	@Override
	public Joueur preSaveData(NullActionParameterImpl p_oParameterIn, MContext p_oContext) throws ActionException {

		Joueur r_oJoueurToSave = null;

		//@non-generated-start[method-presave-data][X]
		// ancien objet avant modification 
		r_oJoueurToSave = BeanLoader.getInstance().getBean(DetailJoueurPanelLoader.class).getData(DetailJoueurPanelLoader.DEFAULT_KEY);
		if (r_oJoueurToSave == null) {
			r_oJoueurToSave = BeanLoader.getInstance().getBean(JoueurFactory.class).createInstance();
			BeanLoader.getInstance().getBean(DetailJoueurPanelLoader.class).addData(DetailJoueurPanelLoader.DEFAULT_KEY, r_oJoueurToSave);
		}

		// on récupère la vue model parent liée
		VMDetailJoueurPanel oVMLinkedPanelMain = Application.getInstance().getViewModelCreator().getViewModel(VMDetailJoueurPanel.class);

		oVMLinkedPanelMain.modifyToIdentifiable(r_oJoueurToSave);

		this.modifyEntityBeforeSave(r_oJoueurToSave); // for overloading
		//@non-generated-end

		return r_oJoueurToSave;
	}

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericsave.SaveDetailAction#postSaveData(com.adeuza.movalysfwk.mf4jcommons.core.beans.MEntity, com.adeuza.movalysfwk.mobile.mf4mjcommons.action.NullActionParameterImpl, com.adeuza.movalysfwk.mobile.mf4mjcommons.context.MContext)
	 */
	@Override
	public void postSaveData(Joueur p_oJoueur, NullActionParameterImpl p_oParameterIn, MContext p_oContext) throws ActionException {

		//@non-generated-start[method-postsave-data][X]

		// on récupère la vue model parent liée
		VMDetailJoueurPanel oVMLinkedPanelMain = Application.getInstance().getViewModelCreator().getViewModel(VMDetailJoueurPanel.class);

		long lVmId = oVMLinkedPanelMain.getId_id();
		long lEntityId = p_oJoueur.getId();

		boolean bCreation = !(lVmId == lEntityId);

		oVMLinkedPanelMain.updateFromIdentifiable(p_oJoueur);

		// TODO uncomment if view model are not syncronized anymore
		// oVMLinkedPanelMain.doOnDataLoaded(p_oParameterIn.getRuleParameters());

		BusinessEvent<Joueur> oEvent = null;
		// si l'id du view modele a change on modifie le cache des view models
		if (bCreation) {
			oEvent = new AddJoueurEvent(this, p_oJoueur);
		} else {
			oEvent = new ChangeJoueurEvent(this, p_oJoueur);
		}
		p_oContext.registerEvent(oEvent);
		//@non-generated-end
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Collection<MObjectToSynchronize> toSynchronize(MContext p_oContext, Joueur p_oEntity) throws ActionException {
		//@non-generated-start[method-to-synchronize][X]
		final Collection<MObjectToSynchronize> r_listObjectToSync = new ArrayList<MObjectToSynchronize>();
		MObjectToSynchronize oObject2Synchronize = BeanLoader.getInstance().getBean(MObjectToSynchronizeFactory.class).createInstance();
		r_listObjectToSync.add(oObject2Synchronize);
		oObject2Synchronize.setObjectId(p_oEntity.getId());
		oObject2Synchronize.setObjectName(Joueur.class.getName());
		return r_listObjectToSync;
		//@non-generated-end
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
