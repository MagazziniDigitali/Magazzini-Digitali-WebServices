/**
 * 
 */
package it.bncf.magazziniDigitali.utils;

import java.io.File;

/**
 * @author massi
 *
 */
public class GenFileDest {

	/**
	 * 
	 */
	public GenFileDest() {
	}

	/**
	 * Metodo utilizzato per la generazione del percorso di Storicizzazione del materiale
	 * 
	 * @param nomeFile Nome del file da Storizzare
	 * @return PErsorso assoluto della risorsa
	 * @throws ConfigurationException
	 */
	public static File genFileDest(String path, String nomeFile)  {
//		String path = null;
		String key = null;

		path += File.separator;

		key = nomeFile.replace("-", "");

		for (int x = 0; x < 8; x++) {
			path += key.substring(0, 2) + File.separator;
			key = key.substring(2);
		}
		path += nomeFile;
		return new File(path);
	}

}
