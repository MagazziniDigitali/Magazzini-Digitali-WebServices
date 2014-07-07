/**
 * 
 */
package it.bncf.magazziniDigitali.solr.item;

import mx.randalf.solr.Item;

/**
 * @author massi
 *
 */
public class ItemObject extends Item {

	/**
	 * Campo relativo al documento dell'opera che verrà utilizzato per le ricerche
	 */
	public static String OBJECTIDENTIFIER="objectIdentifier";

	/**
	 * Campo relativo al documento dell'opera che verrà utilizzato per le ricerche
	 */
	public static String FILETYPE="fileType";

	/**
	 * Campo relativo al documento dell'opera che verrà utilizzato per le ricerche
	 */
	public static String ACTUALFILENAME="actualFileName";

	/**
	 * Campo relativo al documento dell'opera che verrà utilizzato per le ricerche
	 */
	public static String COMPOSITIONLEVEL="compositionLevel";

	/**
	 * Campo relativo al documento dell'opera che verrà utilizzato per le ricerche
	 */
	public static String SHA1="sha1";

	/**
	 * Campo relativo al documento dell'opera che verrà utilizzato per le ricerche
	 */
	public static String SIZE="size";

	/**
	 * Campo relativo al documento dell'opera che verrà utilizzato per le ricerche
	 */
	public static String MIMETYPE="mimeType";

	/**
	 * Campo relativo al documento dell'opera che verrà utilizzato per le ricerche
	 */
	public static String PROMON="promon";

	/**
	 * Campo relativo al documento dell'opera che verrà utilizzato per le ricerche
	 */
	public static String ORIGINALFILENAME="originalFileName";

	/**
	 * Campo relativo al documento dell'opera che verrà utilizzato per le ricerche
	 */
	public static String RIGHTS="rights";

	/**
	 * Campo relativo al documento dell'opera che verrà utilizzato per le ricerche
	 */
	public static String TARINDEX="tarIndex";

	/**
	 * Campo relativo al documento dell'opera che verrà utilizzato per le ricerche
	 */
	public static String RELATIONSHIPTYPE="relationshipType";
	
	/**
	 * 
	 */
	public ItemObject() {
		super();
		addColumn(OBJECTIDENTIFIER, false, true, true, true, true);
		addColumn(FILETYPE, false, true, true, true, true);
		addColumn(ACTUALFILENAME, false, true, true, true, true);
		addColumn(COMPOSITIONLEVEL, false, true, true, true, true);
		addColumn(SHA1, false, true, true, true, true);
		addColumn(SIZE, false, true, true, true, true);
		addColumn(MIMETYPE, true, true, true, true, true);
		addColumn(PROMON, true, true, true, true, true);
		addColumn(ORIGINALFILENAME, false, true, true, true, true);
		addColumn(RIGHTS, false, true, true, true, true);
		addColumn(TARINDEX, false, true, true, true, true);
		addColumn(RELATIONSHIPTYPE, false, true, true, true, true);
	}

}
