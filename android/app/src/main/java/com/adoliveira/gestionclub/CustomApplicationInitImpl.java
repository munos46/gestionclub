package com.adoliveira.gestionclub;

import com.adeuza.movalysfwk.mobile.mf4mjcommons.application.CustomApplicationInit;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.application.CustomInit;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.application.RunInit;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.application.RunInitError;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.context.MContext;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.services.BeanLoader;

/**
 * Does the implementation of custom behavior
 */
public class CustomApplicationInitImpl implements CustomApplicationInit
//@non-generated-start[implements][X]
//@non-generated-end
{
	//@non-generated-start[attributes][X]
	//@non-generated-end
	@Override
	public CustomInit getCustomInit() {
		//@non-generated-start[getCustomInit][X]
		return BeanLoader.getInstance().getBean(CustomInit.class);
		//@non-generated-end
	}

	//@non-generated-start[methodes][X]
	//@non-generated-end
}
