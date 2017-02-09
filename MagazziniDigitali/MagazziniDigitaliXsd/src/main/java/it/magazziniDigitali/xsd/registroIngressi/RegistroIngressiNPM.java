package it.magazziniDigitali.xsd.registroIngressi;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

public class RegistroIngressiNPM extends NamespacePrefixMapper {

	public RegistroIngressiNPM() {
	}

	@Override
	public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
		String value = null;
		if (namespaceUri.equals("http://www.loc.gov/premis/v3")){
			value = "premis";
		} else if (namespaceUri.equals("http://www.depositolegale.it/rgistroIngresso")){
			value = "";
		} else if (namespaceUri.equals("http://www.w3.org/2001/XMLSchema-instance")){
			value = "xsi";
		} else {
			System.out.println("namespaceUri: "+namespaceUri+"\tsuggestion: "+suggestion+"\trequirePrefix: "+requirePrefix);
		}
		return value;
	}

}
