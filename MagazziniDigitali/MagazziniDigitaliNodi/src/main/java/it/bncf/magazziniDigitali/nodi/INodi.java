/**
 * 
 */
package it.bncf.magazziniDigitali.nodi;

import java.io.File;
import java.util.Hashtable;

import it.bncf.magazziniDigitali.database.entity.MDNodi;
import it.bncf.magazziniDigitali.nodi.exception.NodiException;
import it.bncf.magazziniDigitali.nodi.exception.NotImplementException;
import it.depositolegale.www.storage.Documenti;
import it.depositolegale.www.storage.Storage;

/**
 * @author massi
 *
 */
abstract class INodi<I> {

	protected MDNodi mdNodi = null;

	protected Hashtable<ENodi, String> nomeFile = null;

	public INodi(MDNodi mdNodi, String nomeFileTar, String nomeFilePremis) {
		this.mdNodi = mdNodi;
		nomeFile = new Hashtable<ENodi, String>();
		if (nomeFileTar != null) {
			nomeFile.put(ENodi.TAR, nomeFileTar);
		}
		if (nomeFilePremis != null) {
			nomeFile.put(ENodi.PREMIS, nomeFilePremis);
		}
	}

	public String genFileDest(ENodi eNodi) {
		return genFileDest(getNomeFile(eNodi));
	}

	public String getNomeFile(ENodi eNodi) {
		return nomeFile.get(eNodi);
	}

	protected String splitFolder(String folder) {
		String path = null;
		String key = null;

		key = folder.replace("-", "");

		path = "";
		for (int x = 0; x < 8; x++) {
			path += key.substring(0, 2) + File.separator;
			key = key.substring(2);
		}
		return path;
	}

	public abstract boolean isStorageActive() throws NodiException, NotImplementException;

	public abstract String pathStorageActive() throws NodiException, NotImplementException;

	public abstract boolean isFileExists(ENodi eNodi) throws NodiException, NotImplementException;

	protected abstract String genFileDest(String nomeFile);

	public abstract boolean copyFile(I is, long lengthFile, String md5Ori, ENodi eNodi) throws NodiException;

	public abstract Storage checkStorage(Documenti documenti) throws NodiException, NotImplementException;

	public abstract Documenti genDocumenti() throws NodiException, NotImplementException;

	/**
	 * Restituisce il nome del file presente su un nodo locale
	 * 
	 * @param eNodi
	 * @return
	 */
	public abstract File getFile(ENodi eNodi) throws NotImplementException;

	/**
	 * Metodo utilizzato per scaricare un file presente su uno storage remoto e
	 * scriverlo sul disco locale
	 * 
	 * @param eNodi
	 * @param output Nome del file di destinazione
	 * @return Esito della elaborazione
	 */
	public Boolean getFile(ENodi eNodi, File output) throws NodiException, NotImplementException {
		return getFile(eNodi, output, null, null);
	}

	/**
	 * Metodo utilizzato per scaricare un file presente su uno storage remoto e
	 * scriverlo sul disco locale
	 * 
	 * @param eNodi
	 * @param output Nome del file di destinazione
	 * @param start  Byte di partenza per la lettura di una porzione di file
	 * @param end    Byte di arrivo per la lettura di una porzione di file
	 * @return Esito della elaborazione
	 */
	public abstract Boolean getFile(ENodi eNodi, File output, Long start, Long end)
			throws NodiException, NotImplementException;

	/**
	 * @return the mdNodi
	 */
	public MDNodi getMdNodi() {
		return mdNodi;
	}
}
