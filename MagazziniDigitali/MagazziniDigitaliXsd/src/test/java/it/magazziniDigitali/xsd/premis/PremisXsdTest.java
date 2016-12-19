/**
 * 
 */
package it.magazziniDigitali.xsd.premis;

import java.io.File;

import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;

/**
 * @author massi
 *
 */
public class PremisXsdTest {

	/**
	 * 
	 */
	public PremisXsdTest() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisXsd = null;
		File file = null;

		try {
			if (args.length==1){
				file = new File(args[0]);

				if (file.exists()){
					premisXsd = PremisXsd.open(file);
					if (premisXsd != null){
						System.out.println("Il file ["+file.getAbsolutePath()+"] è un file premis Version: "+premisXsd.getVersion());
					} else {
						System.out.println("Il file ["+file.getAbsolutePath()+"] non è un premis riconosciuto");
					}
				} else {
					System.out.println("Il file ["+file.getAbsolutePath()+"] non esiste");
				}
			} else {
				System.out.println("E' necessario indicare il file da analizzare");
			}
		} catch (PremisXsdException e) {
			e.printStackTrace();
		}
	}

}
