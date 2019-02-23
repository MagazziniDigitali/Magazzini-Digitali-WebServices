/**
 * 
 */
package it.bncf.magazziniDigitali.solr.item;


/**
 * @author massi
 *
 */
public class ItemDC extends ItemEvent{

	/**
	 * Campo relativo all'oaiset dell'opera che verrà utilizzato per le ricerche
	 */
	public static String TIPODOCUMENTO="tipoDocumento";
	public static String TIPODOCUMENTO_LIBRODIGITALIZZATO="Libro Digitalizzato";
	public static String TIPODOCUMENTO_PERIODICODIGITALIZZATO="Periodico Digitalizzato";

	/**
	 * Campo relativo al Bid dell'opera che verrà utilizzato per le ricerche
	 */
	public static String BID="bid";

	/**
	 * Campo relativo alla bni dell'opera che verrà utilizzato per le ricerche
	 */
	public static String BNI="bni";

	/**
	 * Campo relativo al titolo dell'opera che verrà utilizzato per le ricerche
	 */
	public static String TITOLO="titolo";

	/**
	 * Campo relativo all'autore dell'opera che verrà utilizzato per le ricerche
	 */
	public static String AUTORE="autore";

	/**
	 * Campo relativo alla pubblicazione dell'opera che verrà utilizzato per le ricerche
	 */
	public static String PUBBLICAZIONE="pubblicazione";
	
	/**
	 * Campo relativo al soggetto dell'opera che verrà utilizzato per le ricerche
	 */
	public static String SOGGETTO="soggetto";

	/**
	 * Campo relativo alla descrizione dell'opera che verrà utilizzato per le ricerche
	 */
	public static String DESCRIZIONE="descrizione";

	/**
	 * Campo relativo al contributo dell'opera che verrà utilizzato per le ricerche
	 */
	public static String CONTRIBUTO="contributo";

	/**
	 * Campo relativo al data dell'opera che verrà utilizzato per le ricerche
	 */
	public static String DATA="data";

	/**
	 * Campo relativo al tipo di risorsa dell'opera che verrà utilizzato per le ricerche
	 */
	public static String TIPORISORSA="tiporisorsa";

	/**
	 * Campo relativo al formato dell'opera che verrà utilizzato per le ricerche
	 */
	public static String FORMATO="formato";

	/**
	 * Campo relativo al fonte dell'opera che verrà utilizzato per le ricerche
	 */
	public static String FONTE="fonte";

	/**
	 * Campo relativo al lingua dell'opera che verrà utilizzato per le ricerche
	 */
	public static String LINGUA="lingua";

	/**
	 * Campo relativo al relazione dell'opera che verrà utilizzato per le ricerche
	 */
	public static String RELAZIONE="relazione";

	/**
	 * Campo relativo al copertura dell'opera che verrà utilizzato per le ricerche
	 */
	public static String COPERTURA="copertura";

	/**
	 * Campo relativo al gestionediritti dell'opera che verrà utilizzato per le ricerche
	 */
	public static String GESTIONEDIRITTI="gestionediritti";

	/**
	 * Campo relativo al biblioteca dell'opera che verrà utilizzato per le ricerche
	 */
	public static String BIBLIOTECA="biblioteca";

	/**
	 * Campo relativo all'inventario dell'opera che verrà utilizzato per le ricerche
	 */
	public static String INVENTARIO="inventario";

	/**
	 * Campo relativo alla Collocazione dell'opera che verrà utilizzato per le ricerche
	 */
	public static String COLLOCAZIONE="collocazione";

	/**
	 * Campo relativo alla pieceGr dell'opera che verrà utilizzato per le ricerche
	 */
	public static String PIECEGR="piecegr";

	/**
	 * Campo relativo alla pieceDt dell'opera che verrà utilizzato per le ricerche
	 */
	public static String PIECEDT="piecedt";

	/**
	 * Campo relativo alla pieceIn dell'opera che verrà utilizzato per le ricerche
	 */
	public static String PIECEIN="piecein";

	/**
	 * Campo relativo alla pieceIn dell'opera che verrà utilizzato per le ricerche
	 */
	public static String PIECEMESE="piecemese";
	public static String PIECEMESEDESCR="piecemesedescr";

	/**
	 * Campo relativo alla pieceIn dell'opera che verrà utilizzato per le ricerche
	 */
	public static String PIECEGIORNO="piecegiorno";

	/**
	 * Campo relativo alla pieceIn dell'opera che verrà utilizzato per le ricerche
	 */
	public static String PIECEANNATA="pieceannata";

	/**
	 * Campo utilizzato per la ricerca per parola su tutti i campi
	 */
	public static String KEYWORDS="keywords";

	/**
	 * Campo utilizzato per la ricerca per parola su tutti i campi
	 */
	public static String KEYWORDSDOC="keywordsDoc";

	/**
	 * 
	 */
	public ItemDC() {
		super();
		addColumn(TIPODOCUMENTO, true, true, true, true, true);
		addColumn(BID, true, true, true, true, true);
		addColumn(BNI, true, true, true, true, true);
		addColumn(TITOLO, true, true, true, true, true);
		addColumn(AUTORE, true, true, true, true, true);
		addColumn(PUBBLICAZIONE, true, true, true, true, true);
		addColumn(SOGGETTO, true, true, true, true, true);
		addColumn(DESCRIZIONE, true, true, true, true, true);
		addColumn(CONTRIBUTO, true, true, true, true, true);
		addColumn(DATA, true, true, true, true, true);
		addColumn(TIPORISORSA, true, true, true, true, true);
		addColumn(FORMATO, true, true, true, true, true);
		addColumn(FONTE, true, true, true, true, true);
		addColumn(LINGUA, true, true, true, true, true);
		addColumn(RELAZIONE, true, true, true, true, true);
		addColumn(COPERTURA, true, true, true, true, true);
		addColumn(GESTIONEDIRITTI, true, true, true, true, true);
		addColumn(BIBLIOTECA, true, true, true, true, true);
		addColumn(INVENTARIO, true, true, true, true, true);
		addColumn(COLLOCAZIONE, true, true, true, true, true);
		addColumn(PIECEGR, true, true, true, true, true);
		addColumn(PIECEDT, true, true, true, true, true);
		addColumn(PIECEIN, true, true, true, true, true);
		addColumn(PIECEMESE, true, true, true, true, true);
		addColumn(PIECEMESEDESCR, true, true, true, true, true);
		addColumn(PIECEGIORNO, true, true, true, true, true);
		addColumn(PIECEANNATA, true, true, true, true, true);
		addColumn(KEYWORDSDOC, true, false, false, false, false);
	}

}
