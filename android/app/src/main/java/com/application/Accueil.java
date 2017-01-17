package com.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adeuza.movalysfwk.mobile.mf4android.activity.AbstractMMActivity;
import com.adeuza.movalysfwk.mobile.mf4android.activity.business.displaymain.MFRootActivity;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.application.Application;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.context.MContext;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.context.MContextFactory;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.beans.CascadeSet;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.core.services.BeanLoader;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.DaoException;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.DaoQuery;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.OrderAsc;
import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.OrderSet;
import com.adoliveira.gestionclub.R;
import com.dao.EntrainementDao;
import com.dao.JoueurDao;
import com.dao.JoueurField;
import com.model.Entrainement;
import com.model.EntrainementCascade;
import com.model.Joueur;

import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

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
		Button btnExport = (Button) findViewById(R.id.export_excel);
		btnExport.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				exportExcel();
			}
		});



		//@non-generated-end
	}

	//@non-generated-start[methods]

	/**
	 * Fonction qui permet de créer le fichier excel
	 */
	private void exportExcel() {
		WritableWorkbook myFirstWbook = null;
		try {

			// Création du fichier et alimentation
			File dir = new File(Environment.getExternalStorageDirectory() + "/Download/RAF/");
			dir.mkdirs();

			myFirstWbook = Workbook.createWorkbook(new File(dir, "test.xls"));

			// create an Excel sheet
			WritableSheet excelSheet = myFirstWbook.createSheet("Décembre 1", 0);

			// Récupération de la liste des entrainements
			EntrainementDao entrainementDao = BeanLoader.getInstance().getBean(EntrainementDao.class);
			JoueurDao joueurDao = BeanLoader.getInstance().getBean(JoueurDao.class);

			MContextFactory oMContextFactory = (MContextFactory) BeanLoader.getInstance().getBean(MContextFactory.class);
			MContext context = oMContextFactory.createContext();

			Map<String, Integer> mapPositionJoueur = new HashMap<>();

			DaoQuery oQuery = joueurDao.getSelectDaoQuery();
			oQuery.getSqlQuery().setOrderBy(OrderSet.of(OrderAsc.of(JoueurField.NOM),OrderAsc.of(JoueurField.PRENOM)));

			List<Joueur> listJoueur = joueurDao.getListJoueur(oQuery,context);

			int posJoueur = 0;
			for (Joueur unJoueur : listJoueur)
			{
				Label label = new Label(0, posJoueur, unJoueur.getNom().concat(" ").concat(unJoueur.getPrenom()));
				excelSheet.addCell(label);
				mapPositionJoueur.put(unJoueur.getNom().concat(unJoueur.getPrenom()), posJoueur);
				posJoueur++;
			}

			List<Entrainement> listEntrainement = entrainementDao.getListEntrainement(CascadeSet.of(EntrainementCascade.LIEU, EntrainementCascade.JOUEUR, EntrainementCascade.JOUEURS), context);

			myFirstWbook.write();


		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (DaoException e) {
			e.printStackTrace();
		} finally {

			if (myFirstWbook != null) {
				try {
					myFirstWbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (WriteException e) {
					e.printStackTrace();
				}
			}
		}


	}
	//@non-generated-end
}
