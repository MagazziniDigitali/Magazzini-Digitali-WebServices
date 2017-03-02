/**
 * 
 */
package it.magazziniDigitali.xsd.ticket;

import it.depositolegale.ticket.Ticket;
import mx.randalf.xsd.ReadXsd;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 *
 */
public class TicketXsd extends ReadXsd<Ticket> {

	/**
	 * 
	 */
	public TicketXsd() {
	}

	public String write(Ticket obj) throws XsdException {
		String schemaLocation = null;

		schemaLocation = "http://www.depositolegale.it/Ticket http://md-www.test.bncf.lan/xsd/ticket.xsd";
		return write(obj, new TicketNamespacePrefix(), null, null, schemaLocation);
	}

}
