package com.adoliveira.gestionclub.loader;

import java.util.List;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.DataLoaderParts;
import com.adeuza.movalysfwk.mobile.mf4javacommons.dataloader.MMDataloader;
import com.model.Entrainement;
import com.model.Lieu;

/**
 * 
 */
public interface DetailEntrainPanelLoader extends MMDataloader<Entrainement>
//@non-generated-start[class-signature]
//@non-generated-end
{
	public enum DataLoaderPartEnum implements DataLoaderParts {
		/** DATA part */
		DATA,
		/** LIEU part */
		LIEU
	}

	//@non-generated-start[constants]
	//@non-generated-end

	/**
	 * Return a list ofLieu
	 * @return list of <code>Lieu</code> object
	 */
	public List<Lieu> getListLieu();

	//@non-generated-start[methods]
	//@non-generated-end
}
