package com.action.deletedetailjoueurpanel;

import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericdelete.DeleteDetailAction;
import com.adeuza.movalysfwk.mobile.mf4javacommons.event.AbstractCUDEvent;
import com.adeuza.movalysfwk.mobile.mf4javacommons.event.CUDType;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.action.DefaultActionStep;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.action.EntityActionParameterImpl;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.action.NullActionParameterImpl;
import com.model.Joueur;

/**
 * 
 */
public interface DeleteDetailJoueurPanel extends DeleteDetailAction<Joueur, DefaultActionStep, Void>
//@non-generated-start[class-signature]
//@non-generated-end
{

	//@non-generated-start[constants]
	//@non-generated-end

	public static class DeleteJoueurEvent extends AbstractCUDEvent<Joueur> {
		/**
		 * Constructor
		 * @param p_oSource source event.
		 * @param p_oData  entity.
		 */
		public DeleteJoueurEvent(Object p_oSource, Joueur p_oData) {
			super(p_oSource, p_oData, CUDType.DELETE);
		}
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
