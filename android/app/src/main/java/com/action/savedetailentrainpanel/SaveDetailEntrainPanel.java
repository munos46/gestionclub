package com.action.savedetailentrainpanel;

import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericsave.SaveDetailAction;
import com.adeuza.movalysfwk.mobile.mf4javacommons.event.AbstractCUDEvent;
import com.adeuza.movalysfwk.mobile.mf4javacommons.event.CUDType;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.action.DefaultActionStep;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.action.EntityActionParameterImpl;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.action.NullActionParameterImpl;
import com.model.Entrainement;

/**
 * 
 */
public interface SaveDetailEntrainPanel extends SaveDetailAction<Entrainement, DefaultActionStep, Void>
//@non-generated-start[class-signature]
//@non-generated-end
{

	//@non-generated-start[constants]
	//@non-generated-end

	public static class ChangeEntrainementEvent extends AbstractCUDEvent<Entrainement> {
		/**
		 * Constructor
		 * @param p_oSource source event.
		 * @param p_oData  entity.
		 */
		public ChangeEntrainementEvent(Object p_oSource, Entrainement p_oData) {
			super(p_oSource, p_oData, CUDType.UPDATE);
		}
	}

	public static class AddEntrainementEvent extends AbstractCUDEvent<Entrainement> {
		/**
		 * Constructor
		 * @param p_oSource source event.
		 * @param p_oData  entity.
		 */
		public AddEntrainementEvent(Object p_oSource, Entrainement p_oData) {
			super(p_oSource, p_oData, CUDType.CREATE);
		}
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
