package com.dao;

import com.adeuza.movalysfwk.mobile.mf4mjcommons.data.dao.Field;

/**
 * Enumération des champs
 */
public enum JoueurField implements Field
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
	 * Field PRENOM
	 * type=TEXT not-null=true
	 */
	PRENOM(3),
	/**
	 * Field DATENAISSANCE
	 * type=NUMERIC not-null=true
	 */
	DATENAISSANCE(4),
	/**
	 * Field PHOTO_NAME
	 * type=TEXT not-null=false
	 */
	PHOTO_NAME(5),
	/**
	 * Field PHOTO_URI
	 * type=TEXT not-null=false
	 */
	PHOTO_URI(6),
	/**
	 * Field PHOTO_SVG
	 * type=TEXT not-null=false
	 */
	PHOTO_SVG(7),
	/**
	 * Field PHOTO_DATE
	 * type=NUMERIC not-null=false
	 */
	PHOTO_DATE(8),
	/**
	 * Field PHOTO_DESC
	 * type=TEXT not-null=false
	 */
	PHOTO_DESC(9),
	/**
	 * Field PHOTO_PHOTOSTATE
	 * type=NUMBER(10,0) not-null=false
	 */
	PHOTO_PHOTOSTATE(10),
	/**
	 * Field EMAIL
	 * type=TEXT not-null=false
	 */
	EMAIL(11),
	/**
	 * Field TAILLE
	 * type=REAL not-null=false
	 */
	TAILLE(12),
	/**
	 * Field POIDS
	 * type=REAL not-null=false
	 */
	POIDS(13),
	/**
	 * Field ADRESSE
	 * type=TEXT not-null=false
	 */
	ADRESSE(14),
	/**
	 * Field COMMUNE
	 * type=TEXT not-null=false
	 */
	COMMUNE(15),
	/**
	 * Field VILLE
	 * type=TEXT not-null=false
	 */
	VILLE(16)
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
	private JoueurField(int p_iColumnIndex) {
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
