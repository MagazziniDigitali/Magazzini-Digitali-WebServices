/**
 * 
 */
package it.bncf.magazziniDigitali.nodi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.GregorianCalendar;
import java.util.Vector;

import it.bncf.magazziniDigitali.database.entity.MDNodi;
import it.bncf.magazziniDigitali.nodi.exception.NodiException;
import it.bncf.magazziniDigitali.nodi.exception.NotImplementException;
import it.depositolegale.www.storage.Documenti;
import it.depositolegale.www.storage.DocumentiDocumento;
import it.depositolegale.www.storage.DocumentiDocumentoDigests;
import it.depositolegale.www.storage.DocumentiDocumentoDigestsInstance;
import it.depositolegale.www.storage.Storage;
import mx.randalf.digest.MD5;
import mx.randalf.digest.SHA1;
import mx.randalf.tools.MD5Tools;

/**
 * @author massi
 *
 */
class NodiFileSystem extends INodi<InputStream> {

	/**
	 * 
	 */
	public NodiFileSystem(MDNodi mdNodi, String nomeFileJar, String nomeFilePremis) {
		super(mdNodi, nomeFileJar, nomeFilePremis);
	}

	@Override
	public boolean isStorageActive() throws NodiException, NotImplementException {
		File fNodo = null;
		fNodo = new File(pathStorageActive());
		return fNodo.exists();
	}

	@Override
	public String pathStorageActive() throws NodiException, NotImplementException {
		return mdNodi.getPathStorage()+File.separator+"storage.id";
	}

	@Override
	public boolean isFileExists(ENodi eNodi) throws NodiException, NotImplementException {
		File fOutput = null;
		boolean result = false;
		
		fOutput = new File(genFileDest(eNodi));
		if (fOutput.exists()) {
			result = true;
		}
		return result;
	}

	/**
	 * Metodo utilizzato per la generazione del percorso di Storicizzazione del materiale
	 * 
	 * @param nomeFile Nome del file da Storizzare
	 * @return PErsorso assoluto della risorsa
	 */
	@Override
	public String genFileDest(String nomeFile)  {
		String path = null;

		path = mdNodi.getPathStorage();
		
		if (!path.endsWith(File.separator)){
			path += File.separator;
		}
		path += splitFolder(nomeFile);
		path += nomeFile;
		return path;
	}

	@Override
	public boolean copyFile(InputStream is, long lengthFile, String md5Ori, ENodi eNodi) throws NodiException {
		boolean ris = false;
		String fileDes = null;
		String md5Des = "";
		
		try {
			fileDes = genFileDest(eNodi);
			if (new File(fileDes).exists()){
				md5Des = MD5Tools.readMD5File(fileDes);
				if (!md5Ori.equals(md5Des)){
					copyFile(is, lengthFile, fileDes);
					md5Des = MD5Tools.readMD5File(fileDes);
				}
			} else {
				copyFile(is, lengthFile, fileDes);
				md5Des = MD5Tools.readMD5File(fileDes);
			}
			ris = md5Ori.equals(md5Des);
		} catch (FileNotFoundException e) {
			throw new NodiException(e.getMessage(), e);
		} catch (NoSuchAlgorithmException e) {
			throw new NodiException(e.getMessage(), e);
		} catch (IOException e) {
			throw new NodiException(e.getMessage(), e);
		} catch (NodiException e) {
			throw e;
		}
		return ris;
	}

	private void copyFile(InputStream br, long lengthFile, String fileDes) throws NodiException {
		File fo = null;
		FileOutputStream bw = null;
		long offset = 0;
		long maxByteRead = 4000000;
		byte[] testo = null;
		int numByte = 0;

		try {
			fo = new File(fileDes);
			if (!fo.getParentFile().exists()) {
				if (!fo.getParentFile().mkdirs()) {
					throw new NodiException(
							"Problema nella creazione della cartella ["
									+ fo.getParentFile().getAbsolutePath()
									+ "]");
				}
			}
			if (fo.getParentFile().getFreeSpace() > lengthFile) {
				bw = new FileOutputStream(fo);
				while (offset < lengthFile) {
					if ((lengthFile - offset) < maxByteRead) {
						testo = new byte[(int)(lengthFile - offset)];
					} else {
						testo = new byte[(int) maxByteRead];
					}
					System.gc();
					numByte = br.read(testo);
					if (numByte > -1) {
						bw.write(testo);
						bw.flush();
						offset += numByte;
					}
				}
			} else {
				throw new NodiException(
						"Spazio non sufficiente per copiare il file in ["
								+ fo.getAbsolutePath() + "] disk free: "
								+ fo.getParentFile().getFreeSpace()
								+ " byte file size: " + lengthFile
								+ " byte");
			}
		} catch (FileNotFoundException e) {
			throw new NodiException("File Not Found [" + e.getMessage() + "]",
					e);
		} catch (NodiException e) {
			throw e;
		} catch (IOException e) {
			throw new NodiException("IO [" + e.getMessage() + "]", e);
		} catch (Exception e) {
			throw new NodiException("Exception [" + e.getMessage() + "]", e);
		} finally {
			try {
				if (bw != null) {
					bw.flush();
					bw.close();
				}
			} catch (IOException e) {
				throw new NodiException("IO [" + e.getMessage() + "]", e);
			}
		}
	}

	@Override
	public Storage checkStorage(Documenti documenti) throws NodiException, NotImplementException {
		throw new NotImplementException("Metodo non implementato per questo tipo di risorsa");
	}

	@Override
	public Documenti genDocumenti() throws NodiException, NotImplementException {
		Documenti documenti = null;
		DocumentiDocumento[] documento =  null;
		
		try {
			documenti = new Documenti();
			documenti.setNumDoc(new BigInteger("2"));

			documento = new DocumentiDocumento[2];
			
			documento[0] = genDocumento(ENodi.TAR);
			
			documento[1] = genDocumento(ENodi.PREMIS);

			documenti.setDocumento(documento);
		} catch (NodiException e) {
			throw e;
		}

		return documenti;
	}

	private DocumentiDocumento genDocumento(ENodi eNodi) throws NodiException {
		DocumentiDocumento documento = null;
		SHA1 sha1 = null;
		MD5 md5 = null;
		File f = null;
		GregorianCalendar dataMod = null;

		try {
			f = new File(genFileDest(eNodi));
			sha1 = new SHA1(f);
			md5 = new MD5(f);
			dataMod = new GregorianCalendar();
			dataMod.setTimeInMillis(f.lastModified());
			documento = genDocumento(splitFolder(nomeFile.get(eNodi))+nomeFile.get(eNodi)
			, sha1.getDigest()
			, md5.getDigest()
			, dataMod);
		} catch (NoSuchAlgorithmException e) {
			throw new NodiException(e.getMessage(), e);
		} catch (FileNotFoundException e) {
			throw new NodiException(e.getMessage(), e);
		} catch (IOException e) {
			throw new NodiException(e.getMessage(), e);
		}
		return documento;
	}

	private DocumentiDocumento genDocumento(String fileName
			, String sha1
			, String md5
			, GregorianCalendar dataMod) {
		DocumentiDocumento documento =  null;
		Vector<DocumentiDocumentoDigests> digests = null;

		documento= new DocumentiDocumento();
		if (sha1 != null && !sha1.trim().equals("")) {
			if (digests == null) {
				digests = new Vector<DocumentiDocumentoDigests>();
			}
			digests.add(getDigest(DocumentiDocumentoDigestsInstance.SHA1, sha1));
		}
		if (md5 != null && !md5.trim().equals("")) {
			if (digests == null) {
				digests = new Vector<DocumentiDocumentoDigests>();
			}
			digests.add(getDigest(DocumentiDocumentoDigestsInstance.MD5, md5));
		}
		documento.setNomeFile(fileName);
		documento.setDigests(digests.toArray(new DocumentiDocumentoDigests[digests.size()]));
		documento.setDataMod(dataMod);
		
		documento.setEsito("CHECK");
		return documento;
	}

	private DocumentiDocumentoDigests getDigest(DocumentiDocumentoDigestsInstance instance, String value) {
		DocumentiDocumentoDigests digest = null;
		
		digest = new DocumentiDocumentoDigests();
		digest.setInstance(instance);
		digest.setValue(value);
		return digest;
	}

	@Override
	public File getFile(ENodi eNodi) throws NotImplementException{
		return new File(genFileDest(eNodi));
	}

	@Override
	public Boolean getFile(ENodi eNodi, File output, Long start, Long end) throws NodiException, NotImplementException {
		throw new NotImplementException("Metodo non implementato per questo tipo di risorsa");
	}
}
