package com.action.deletedetailentrainpanel;

import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericdelete.DeleteDetailAction;
import com.adeuza.movalysfwk.mobile.mf4javacommons.event.AbstractCUDEvent;
import com.adeuza.movalysfwk.mobile.mf4javacommons.event.CUDType;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.action.DefaultActionStep;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.action.EntityActionParameterImpl;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.action.NullActionParameterImpl;
import com.model.Entrainement;

/**
 * 
 */
public interface DeleteDetailEntrainPanel extends DeleteDetailAction<Entrainement, DefaultActionStep, Void>
//@non-generated-start[class-signature]
//@non-generated-end
{

	//@non-generated-start[constants]
	//@non-generated-end

	public static class DeleteEntrainementEvent extends AbstractCUDEvent<Entrainement> {
		/**
		 * Constructor
		 * @param p_oSource source event.
		 * @param p_oData  entity.
		 */
		public DeleteEntrainementEvent(Object p_oSource, Entrainement p_oData) {
			super(p_oSource, p_oData, CUDType.DELETE);
		}
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
