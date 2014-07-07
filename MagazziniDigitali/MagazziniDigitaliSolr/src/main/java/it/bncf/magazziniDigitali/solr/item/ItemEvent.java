/**
 * 
 */
package it.bncf.magazziniDigitali.solr.item;


/**
 * @author massi
 *
 */
public class ItemEvent extends ItemObject{

	/**
	 * Campo relativo al documento dell'opera che verrà utilizzato per le ricerche
	 */
	public static String EVENTID="eventID";

	/**
	 * Campo relativo al documento dell'opera che verrà utilizzato per le ricerche
	 */
	public static String EVENTTYPE="eventType";

	/**
	 * Campo relativo al documento dell'opera che verrà utilizzato per le ricerche
	 */
	public static String EVENTDATE="eventDate";

	public static String EVENTDETAIL="eventDetail";

	/**
	 * Campo relativo al documento dell'opera che verrà utilizzato per le ricerche
	 */
	public static String EVENTOUTCOME="eventOutCome";

	/**
	 * Campo relativo al documento dell'opera che verrà utilizzato per le ricerche
	 */
	public static String AGENTDEPOSITANTE="agentDepositante";

	/**
	 * Campo relativo al documento dell'opera che verrà utilizzato per le ricerche
	 */
	public static String AGENTSOFTWARE="agentSoftware";

	/**
	 * 
	 */
	public ItemEvent() {
		super();
		addColumn(EVENTID, false, true, true, true, true);
		addColumn(EVENTTYPE, false, true, true, true, true);
		addColumn(EVENTDATE, true, true, true, true, true);
		addColumn(EVENTDETAIL, true, true, true, true, true);
		addColumn(EVENTOUTCOME, false, true, true, true, true);
		addColumn(AGENTDEPOSITANTE, false, true, true, true, true);
		addColumn(AGENTSOFTWARE, true, true, true, true, true);
	}

}
