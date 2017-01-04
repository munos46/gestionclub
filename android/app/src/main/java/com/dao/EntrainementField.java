package com.dao;

import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.Field;

/**
 * Enumération des champs
 */
public enum EntrainementField implements Field
//@non-generated-start[class-signature]
//@non-generated-end
{
	/**
	 * Field ID
	 * type=INTEGER not-null=true
	 */
	ID(1),
	/**
	 * Field DATEHEURE
	 * type=NUMERIC not-null=true
	 */
	DATEHEURE(2),
	/**
	 * Field LIEUID
	 * type=INTEGER not-null=true
	 */
	LIEUID(3)
	//@non-generated-start[enumeration]
	//@non-generated-end
	;
	/**
	 * Index de la column
	 */
	private int columnIndex;

	//@non-generated-start[attributes]
	//@non-generated-end

	/**
	 * Constructeur
	 * @param p_iColumnIndex index de la column
	 */
	private EntrainementField(int p_iColumnIndex) {
		this.columnIndex = p_iColumnIndex;
	}

	/**
	 * Retourne l'index de la colonne
	 * @return index de la colonne
	 */
	@Override
	public int getColumnIndex() {
		return this.columnIndex;
	}

	//@non-generated-start[methodes]
	//@non-generated-end

}
