/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import it.magazziniDigitali.xsd.premis.PremisXsd;

/**
 * @author massi
 *
 */
public class OggettoDigitale {

	/**
	 * 
	 */
	public OggettoDigitale() {
	}

	protected String genFilePremis(String pathPremis, String type, String name) {
		return 	genFilePremis(pathPremis, type, name, ".premis");
	}
	
	public static String genFilePremis(String pathPremis, String type, String name, String subFix) {
		String result = null;
		GregorianCalendar gc = null;
//		String fPremis = null;
		DecimalFormat df4 = new DecimalFormat("0000");
		DecimalFormat df2 = new DecimalFormat("00");

		gc = new GregorianCalendar();
//		fPremis = premis.getActualFileName() + ".premis";
		result = pathPremis + File.separator + 
				df4.format(gc.get(Calendar.YEAR)) + File.separator + 
				df2.format(gc.get(Calendar.MONTH) + 1) + File.separator + 
				df2.format(gc.get(Calendar.DAY_OF_MONTH)) + File.separator +
				type+File.separator+
				name+ subFix;

		return result;
	}

	protected String genFilePremis(String pathPremis, String filePremis) {
		String result = null;
		result = pathPremis + File.separator + 
				(filePremis.startsWith("./")?
						filePremis.substring(2):filePremis);

		return result;
	}

	protected String writeFilePremisDB(File filePremis, String pathPremis){
		
		return (filePremis==null?
				null:
				filePremis.
					getAbsolutePath().
					replace(pathPremis,"."));
	}

	public static String genPathPremis(String objectIdentifierPremis){
		String result = "";
		String date = "";
		
		result = "./";
		date = objectIdentifierPremis.substring(objectIdentifierPremis.length()-8);
		result += date.substring(0, 4);
		result += "/";
		result += date.substring(4, 6);
		result += "/";
		result += date.substring(6);
		result += "/Validate/";
		result += objectIdentifierPremis;
		return result;
	}

	/**
	 * Metodo utilizzato per ricamare l'identificativo dell'Oggetto dal tracciato Premis
	 * 
	 * @param premis Tracciato Premis da analizzare
	 * @return Valore individuato
	 */
	protected String findObjectIdentifierContainer(PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premis) {

		return premis.findObjectIdentifierContainer();
	}
}
