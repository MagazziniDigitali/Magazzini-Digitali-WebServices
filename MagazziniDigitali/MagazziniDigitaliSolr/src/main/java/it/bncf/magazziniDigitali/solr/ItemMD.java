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
	public static String TIPOOGGETTO_REGISTRO="registro";

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

	public static String BIDURL="bidUrl";
	public static String JOURNALBID="journalBid";
	public static String JOURNALBIDURL="journalBidUrl";

	public static String JOURNALTITOLO="journalTitolo";

	public static String AUTOREID="autoreId";

	public static String PAGESTART="pageStart";
	public static String PAGEEND="pageEnd";
	public static String URL="url";

	public static String NBN="nbn";

	public static String AGENTIDENTIFIER="agentIdentifier";
	public static String AGENTNAME="agentName";
	public static String AGENTTYPE="agentType";
	public static String AGENTNOTE="agentNote";
	public static String AGENTIDISTITUZIONE="agentIdIstituzione";
	public static String AGENTIDRIGTHS="agentIdRigths";

	public static String RIGHTSIDENTIFIER="rightsIdentifier";
	public static String RIGHTSBASIS="rightsBasis";
	public static String RIGHTSINFORMATIONBASIS="rightsInformationBasis";
	public static String RIGHTSACT="rightsAct";
	public static String RIGHTSRESTRICTION="rightsRestriction";
	public static String RIGHTOBJECTIDENTIFIER="rightsObjectIdentifier";
	public static String RIGHTSSTATUTEJURISDICTION="rightsStatuteJurisdiction";
	public static String RIGHTSSTATUTECITATION="rightsStatuteCitation";

	public static String AGENTMACHINE = "agentMachine";
	
	// Registro Ingresso
	public static String REGISTRO_ID = "registroId";
	public static String REGISTRO_TIMESTAMPINGEST = "registroTimeStampIngest";
	public static String REGISTRO_CONTAINERFINGERPRINT = "registroContainerFingerPrint";
	public static String REGISTRO_CONTAINERFINGERPRINTCHAIN = "registroContainerFingerPrintChain";
	public static String REGISTRO_CONTAINERTYPE = "registroContainerType";
	public static String REGISTRO_STATUS = "registroStatus";
	public static String REGISTRO_TIMESTAMPINIT = "registroTimeStampInit";
	public static String REGISTRO_TIMESTAMPELAB = "registroTimeStampElab";
	public static String REGISTRO_TIMESTAMPCODA = "registroTimeStampCoda";
	public static String REGISTRO_TIMESTAMPPUB = "registroTimeStampPub";
	public static String REGISTRO_TIMESTAMPERR = "registroTimeStampErr";
	
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

		addColumn(BIDURL, true, true, true, true, true);
		addColumn(JOURNALBID, false, true, true, true, false);
		addColumn(JOURNALBIDURL, true, true, true, true, true);

		addColumn(JOURNALTITOLO, true, true, true, true, true);

		addColumn(AUTOREID, true, true, true, true, true);

		addColumn(PAGESTART, true, true, true, true, true);
		addColumn(PAGEEND, true, true, true, true, true);
		addColumn(URL, true, true, true, true, true);

		addColumn(AGENTIDENTIFIER, true, true, true, true, true);
		addColumn(AGENTNAME, true, true, true, true, true);
		addColumn(AGENTTYPE, true, true, true, true, true);
		addColumn(AGENTNOTE, true, true, true, true, true);
		addColumn(AGENTIDISTITUZIONE, true, true, true, true, true);
		addColumn(AGENTIDRIGTHS, true, true, true, true, true);

		addColumn(RIGHTSIDENTIFIER, true, true, true, true, true);
		addColumn(RIGHTSBASIS, true, true, true, true, true);
		addColumn(RIGHTSINFORMATIONBASIS, true, true, true, true, true);
		addColumn(RIGHTSACT, true, true, true, true, true);
		addColumn(RIGHTSRESTRICTION, true, true, true, true, true);
		addColumn(RIGHTOBJECTIDENTIFIER, true, true, true, true, true);
		addColumn(RIGHTSSTATUTEJURISDICTION, true, true, true, true, true);
		addColumn(RIGHTSSTATUTECITATION, true, true, true, true, true);
		addColumn(NBN, true, true, true, true, true);

		addColumn(AGENTMACHINE, true, true, true, true, true);
		
		// Registro Ingresso
		addColumn(REGISTRO_ID, true, true, true, true, true);
		addColumn(REGISTRO_TIMESTAMPINGEST, true, true, true, true, true);
		addColumn(REGISTRO_CONTAINERFINGERPRINT, true, true, true, true, true);
		addColumn(REGISTRO_CONTAINERFINGERPRINTCHAIN, true, true, true, true, true);
		addColumn(REGISTRO_CONTAINERTYPE, true, true, true, true, true);
		addColumn(REGISTRO_STATUS, true, true, true, true, true);
		addColumn(REGISTRO_TIMESTAMPINIT, true, true, true, true, true);
		addColumn(REGISTRO_TIMESTAMPELAB, true, true, true, true, true);
		addColumn(REGISTRO_TIMESTAMPCODA, true, true, true, true, true);
		addColumn(REGISTRO_TIMESTAMPPUB, true, true, true, true, true);
		addColumn(REGISTRO_TIMESTAMPERR, true, true, true, true, true);
	}

}
