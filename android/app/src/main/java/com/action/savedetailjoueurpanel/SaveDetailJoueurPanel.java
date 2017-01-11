package com.action.savedetailjoueurpanel;

import com.adeuza.movalysfwk.mobile.mf4android.activity.business.genericsave.SaveDetailAction;
import com.adeuza.movalysfwk.mobile.mf4javacommons.event.AbstractCUDEvent;
import com.adeuza.movalysfwk.mobile.mf4javacommons.event.CUDType;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.action.DefaultActionStep;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.action.EntityActionParameterImpl;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.action.NullActionParameterImpl;
import com.model.Joueur;

/**
 * 
 */
public interface SaveDetailJoueurPanel extends SaveDetailAction<Joueur, DefaultActionStep, Void>
//@non-generated-start[class-signature]
//@non-generated-end
{

	//@non-generated-start[constants]
	//@non-generated-end

	public static class ChangeJoueurEvent extends AbstractCUDEvent<Joueur> {
		/**
		 * Constructor
		 * @param p_oSource source event.
		 * @param p_oData  entity.
		 */
		public ChangeJoueurEvent(Object p_oSource, Joueur p_oData) {
			super(p_oSource, p_oData, CUDType.UPDATE);
		}
	}

	public static class AddJoueurEvent extends AbstractCUDEvent<Joueur> {
		/**
		 * Constructor
		 * @param p_oSource source event.
		 * @param p_oData  entity.
		 */
		public AddJoueurEvent(Object p_oSource, Joueur p_oData) {
			super(p_oSource, p_oData, CUDType.CREATE);
		}
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
