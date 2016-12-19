/**
 * 
 */
package it.magazziniDigitali.xsd.premis.v2_2;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

/**
 * @author massi
 *
 */
public class PremisNPM extends NamespacePrefixMapper {

	/**
	 * 
	 */
	public PremisNPM() {
	}

	/* (non-Javadoc)
	 * @see com.sun.xml.bind.marshaller.NamespacePrefixMapper#getPreferredPrefix(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public String getPreferredPrefix(String namespaceUri, String suggestion,
			boolean requirePrefix) {
		String value = null;
		if (namespaceUri.equals("http://www.w3.org/2001/XMLSchema-instance")){
			value = "xsi";
		} else if (namespaceUri.equals("info:lc/xmlns/premis-v2")){
			value = "";
		} else if (namespaceUri.equals("http://www.w3.org/1999/xlink")){
			value = "xlink";
		} else {
			System.out.println("namespaceUri: "+namespaceUri+"\tsuggestion: "+suggestion+"\trequirePrefix: "+requirePrefix);
		}
			
		return value;
	}

}
