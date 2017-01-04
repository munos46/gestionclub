package com.dao;

import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.Field;

/**
 * Enumération des champs
 */
public enum LieuField implements Field
//@non-generated-start[class-signature]
//@non-generated-end
{
	/**
	 * Field ID
	 * type=INTEGER not-null=true
	 */
	ID(1),
	/**
	 * Field NOM
	 * type=TEXT not-null=true
	 */
	NOM(2),
	/**
	 * Field POSITION_LATITUDE
	 * type=REAL not-null=false
	 */
	POSITION_LATITUDE(3),
	/**
	 * Field POSITION_LONGITUDE
	 * type=REAL not-null=false
	 */
	POSITION_LONGITUDE(4),
	/**
	 * Field POSITION_COMPL
	 * type=TEXT not-null=false
	 */
	POSITION_COMPL(5),
	/**
	 * Field POSITION_STREET
	 * type=TEXT not-null=false
	 */
	POSITION_STREET(6),
	/**
	 * Field POSITION_CITY
	 * type=TEXT not-null=false
	 */
	POSITION_CITY(7),
	/**
	 * Field POSITION_COUNTRY
	 * type=TEXT not-null=false
	 */
	POSITION_COUNTRY(8)
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
	private LieuField(int p_iColumnIndex) {
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
