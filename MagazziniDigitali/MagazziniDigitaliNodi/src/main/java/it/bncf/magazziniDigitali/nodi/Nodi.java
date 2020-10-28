/**
 * 
 */
package it.bncf.magazziniDigitali.nodi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

import it.bncf.magazziniDigitali.database.entity.MDNodi;
import it.bncf.magazziniDigitali.nodi.exception.NodiException;
import it.bncf.magazziniDigitali.nodi.exception.NotImplementException;
import it.depositolegale.www.storage.Documenti;
import it.depositolegale.www.storage.Storage;
import mx.randalf.digest.MD5;

/**
 * @author massi
 *
 */
public class Nodi {

	private INodi<?> nodo = null;

	/**
	 * @throws NodiException 
	 * 
	 */
	public Nodi(MDNodi mdNodi) throws NodiException {
		this(mdNodi, null, null);
	}

	public Nodi(MDNodi mdNodi, String nomeFilePremis) throws NodiException {
		this(mdNodi, nomeFilePremis.replace(".premis", ""), nomeFilePremis);
	}

	public Nodi(MDNodi mdNodi, String nomeFileJar, String nomeFilePremis) throws NodiException {
		if (mdNodi.getTipo().equals("F")) { // F
			nodo = new NodiFileSystem(mdNodi, nomeFileJar, nomeFilePremis);
		} else if (mdNodi.getTipo().equals("M")) { // M
			nodo = new NodiMDRsync(mdNodi, nomeFileJar, nomeFilePremis);
		} else if (mdNodi.getTipo().equals("S")) { // S
			nodo = new NodiS3(mdNodi, nomeFileJar, nomeFilePremis);
		} else {
			throw new NodiException("Il tipo di Nodo ["+mdNodi.getTipo()+"] non è supportato");
		}
	}

	public boolean isStorageActive() throws NodiException, NotImplementException {
		return nodo.isStorageActive();
	}

	public String pathStorageActive() throws NodiException, NotImplementException {
		return nodo.pathStorageActive();
	}

	public boolean isFileExists() throws NodiException, NotImplementException {
		return (isFileTarExists() && isFilePremisExists());
	}

	public boolean isFileTarExists() throws NodiException, NotImplementException {
		return nodo.isFileExists(ENodi.TAR);
	}

	public boolean isFilePremisExists() throws NodiException, NotImplementException {
		return nodo.isFileExists(ENodi.PREMIS);
	}

	public String genFileTarDest() {
		return nodo.genFileDest(ENodi.TAR);
	}

	public String genFilePremisDest() {
		return nodo.genFileDest(ENodi.PREMIS);
	}

	public String getNomeFileTar() {
		return nodo.getNomeFile(ENodi.TAR);
	}

	public String getNomeFilePremis() {
		return nodo.getNomeFile(ENodi.PREMIS);
	}

	public boolean copyFile(Nodi nodoInput, ENodi eNodi) throws NodiException, NotImplementException {
		boolean ris = false;
		File fInput = null;
		MD5 md5 = null;

		try {
			if (nodo instanceof NodiMDRsync) {
				fInput = nodoInput.getFile(eNodi);
				md5 = new MD5(fInput);

				ris = ((NodiMDRsync)nodo).copyFile(fInput
						, fInput.length()
						, md5.getDigest()
						, eNodi);
			} else if (nodo instanceof NodiS3) {
				fInput = nodoInput.getFile(eNodi);
				md5 = new MD5(fInput);

				ris = ((NodiS3)nodo).copyFile(fInput
						, fInput.length()
						, md5.getDigest()
						, eNodi);
			}
		} catch (NoSuchAlgorithmException e) {
			throw new NodiException(e.getMessage(), e);
		} catch (FileNotFoundException e) {
			throw new NodiException(e.getMessage(), e);
		} catch (IOException e) {
			throw new NodiException(e.getMessage(), e);
		} catch (NotImplementException e) {
			throw e;
		}
		
		return ris;
	}

	public boolean copyFile(Object is, long lengthFile, String md5Ori, ENodi eNodi) throws NodiException {
		if (nodo instanceof NodiMDRsync) {
			return ((NodiMDRsync)nodo).copyFile((File)is, lengthFile, md5Ori, eNodi);
		} else if (nodo instanceof NodiFileSystem){
			return ((NodiFileSystem)nodo).copyFile((InputStream)is, lengthFile, md5Ori, eNodi);
		} else {
			return ((NodiS3)nodo).copyFile((File)is, lengthFile, md5Ori, eNodi);
		}
	}

	public Storage checkStorage(Documenti documenti) throws NodiException, NotImplementException {
		return nodo.checkStorage(documenti);
	}

	public Documenti genDocumenti() throws NodiException, NotImplementException {
		return nodo.genDocumenti();
	}

	public File getFile(ENodi eNodi) throws NotImplementException {
		return nodo.getFile(eNodi);
	}

	/**
	 * Metodo utilizzato per scaricare un file presente su uno storage remoto e
	 * scriverlo sul disco locale
	 * 
	 * @param eNodi
	 * @param output Nome del file di destinazione
	 * @return Esito della elaborazione
	 */
	public Boolean getFile(ENodi eNodi, File output) throws NodiException, NotImplementException {
		return nodo.getFile(eNodi, output);
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
	public Boolean getFile(ENodi eNodi, File output, Long start, Long end)
			throws NodiException, NotImplementException{
		return nodo.getFile(eNodi, output, start, end);
	}

	public MDNodi getMdNodi() {
		return nodo.getMdNodi();
	}

	public String genFileDest(ENodi eNodi) {
		return nodo.genFileDest(eNodi);
	}

	public String genFileDest(String nomeFile) {
		return nodo.genFileDest(nomeFile);
	}
}
