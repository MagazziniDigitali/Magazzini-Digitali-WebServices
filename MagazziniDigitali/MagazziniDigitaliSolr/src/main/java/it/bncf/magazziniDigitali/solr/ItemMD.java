/**
 * 
 */
package it.bncf.magazziniDigitali.solr;

import it.bncf.magazziniDigitali.solr.item.ItemDC;

/**
 * @author massi
 *
 */
public class ItemMD extends ItemDC{

	/**
	 * Campo relativo al _tipo_ dell'opera che verrà utilizzato per le ricerche
	 */
	public static String _TIPO_="_tipo_";

	/**
	 * Campo relativo al tipoDoc dell'opera che verrà utilizzato per le ricerche
	 */
	public static String TIPOOGGETTO="tipoOggetto";
	public static String TIPOOGGETTO_FILE="file";
	public static String TIPOOGGETTO_CONTENITORE="contenitore";
	public static String TIPOOGGETTO_DOCUMENTO="documento";
	public static String TIPOOGGETTO_EVENTO="evento";
	public static String TIPOOGGETTO_AGENTE="agente";
	public static String TIPOOGGETTO_DIRITTI="diritti";

	/**
	 * Campo relativo al documento dell'opera che verrà utilizzato per le ricerche
	 */
	public static String PROVENIENZAOGGETTO="provenienzaOggetto";

	/**
	 * Campo relativo al documento dell'opera che verrà utilizzato per le ricerche
	 */
	public static String TIPOCONTENITORE="tipoContenitore";

	/**
	 * Campo relativo al nodo dell'opera che verrà utilizzato per le ricerche
	 */
	public static String NODO="nodo";

	/**
	 * 
	 */
	public ItemMD() {
		super();
		addColumn(_TIPO_, false, true, true, true, true);
		addColumn(TIPOOGGETTO, false, true, true, true, true);
		addColumn(PROVENIENZAOGGETTO, false, true, true, true, true);
		addColumn(TIPOCONTENITORE, false, true, true, true, true);
		addColumn(NODO, true, true, true, true, true);
	}

}
