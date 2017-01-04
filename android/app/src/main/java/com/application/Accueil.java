package com.application;

import java.util.List;
import com.adeuza.movalysfwk.mobile.mf4android.activity.AbstractMMActivity;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.displaymain.MFRootActivity;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.application.Application;
import com.adoliveira.gestionclub.R;
import android.support.v7.app.ActionBar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * 
 */
public class Accueil
//@non-generated-start[class-signature-extends][X]
		extends AbstractMMActivity
//@non-generated-end
		implements MFRootActivity
//@non-generated-start[class-signature]
//@non-generated-end
{
	/** 
	 * Result code use with method startActivityForResult.
	 * In support-v7, only the last five digits of the result code are read.
	 * If the result value is greater, an exception will be raised.
	 */
	public static final int REQUEST_CODE = Accueil.class.getSimpleName().hashCode() & REQUEST_CODE_MASK;

	//@non-generated-start[attributes]
	//@non-generated-end

	/**
	 * {@inheritDoc}
	 * @see com.adeuza.movalysfwk.mobile.mf4android.activity.AbstractMMActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(android.os.Bundle p_oSavedInstanceState) {
		super.onCreate(p_oSavedInstanceState);
		this.setContentView(R.layout.gaccueil__screen__master);

		//@non-generated-start[on-create]
		//changing statusbar color
		if (android.os.Build.VERSION.SDK_INT >= 21) {
			Window window = this.getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		}

		//		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		//		getSupportActionBar().setCustomView(R.layout.action_bar);
		//		TextView title = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.title);
		//		title.setText(R.string.app_name);

		//@non-generated-end
	}

	//@non-generated-start[methods]
	//@non-generated-end
}
