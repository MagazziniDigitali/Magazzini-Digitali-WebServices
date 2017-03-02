/**
 * 
 */
package it.magazziniDigitali.xsd.ticket;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

/**
 * @author massi
 *
 */
public class TicketNamespacePrefix extends NamespacePrefixMapper {

	/**
	 * 
	 */
	public TicketNamespacePrefix() {
	}

	/**
	 * @see com.sun.xml.bind.marshaller.NamespacePrefixMapper#getPreferredPrefix(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public String getPreferredPrefix(String namespaceUri, String suggestion, 
			boolean requirePrefix) {
		String value = null;

		if (namespaceUri.equals("http://www.loc.gov/premis/v3")){
			value = "premis";
		} else if (namespaceUri.equals("http://www.depositolegale.it/Ticket")){
			value = "";
		} else if (namespaceUri.equals("http://www.w3.org/2001/XMLSchema-instance")){
			value = "xsi";
		} else {
			System.out.println("namespaceUri: "+namespaceUri+"\tsuggestion: "+suggestion+"\trequirePrefix: "+requirePrefix);
		}
		
		return value;
	}

}
