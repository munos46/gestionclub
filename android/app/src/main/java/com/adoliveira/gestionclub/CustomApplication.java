package com.adoliveira.gestionclub;

import com.adeuza.movalysfwk.mobile.mf4android.application.MFAndroidApplication;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.services.BeanLoader;
import com.soprasteria.movalysmdk.widget.core.provider.MDKWidgetApplication;
import com.soprasteria.movalysmdk.widget.core.provider.MDKWidgetComponentActionHelper;
import com.soprasteria.movalysmdk.widget.core.provider.MDKWidgetComponentProvider;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

/**
 * Custom application.
 *
 */
@ReportsCrashes(mailTo = "adrien.oliveira46@gmail.com", // my email here
		mode = ReportingInteractionMode.TOAST,
		resToastText = R.string.crash_toast_text)
public class CustomApplication extends MFAndroidApplication implements MDKWidgetApplication {

	/**
	 * Component provider for mdk widget.
	 */
	private MDKWidgetComponentProvider mdkWidgetComponentProvider ;
	
	/**
	 * Component action helper for mdk widget.
	 */
	private MDKWidgetComponentActionHelper mdkWidgetComponentActionHelper;

	@Override
	public MDKWidgetComponentProvider getMDKWidgetComponentProvider() {
		if (mdkWidgetComponentProvider == null) {
			mdkWidgetComponentProvider = BeanLoader.getInstance().getBean(MDKWidgetComponentProvider.class);
			mdkWidgetComponentProvider.initialize(this);
		}
		return mdkWidgetComponentProvider;
	}

	@Override
	public MDKWidgetComponentActionHelper getMDKWidgetComponentActionHelper() {
		if (mdkWidgetComponentActionHelper == null) {
			mdkWidgetComponentActionHelper = BeanLoader.getInstance().getBean(MDKWidgetComponentActionHelper.class);
		}
		return mdkWidgetComponentActionHelper;
	}

	@Override
	public void onCreate() {
		ACRA.init(this);
		super.onCreate();
	}
}