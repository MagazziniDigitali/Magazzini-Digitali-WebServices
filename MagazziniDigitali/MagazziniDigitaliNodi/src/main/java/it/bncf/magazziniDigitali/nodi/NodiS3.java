/**
 * 
 */
package it.bncf.magazziniDigitali.nodi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import com.amazonaws.regions.Regions;

import it.bncf.magazziniDigitali.database.entity.MDNodi;
import it.bncf.magazziniDigitali.nodi.exception.NodiException;
import it.bncf.magazziniDigitali.nodi.exception.NotImplementException;
import it.depositolegale.www.storage.Documenti;
import it.depositolegale.www.storage.DocumentiDocumento;
import it.depositolegale.www.storage.DocumentiDocumentoDigests;
import it.depositolegale.www.storage.DocumentiDocumentoDigestsInstance;
import it.depositolegale.www.storage.Storage;
import it.depositolegale.www.storage.StorageDocumenti;
import it.depositolegale.www.storage.StorageDocumentiDocumento;
import it.depositolegale.www.storage.StorageDocumentiDocumentoDigests;
import it.depositolegale.www.storage.StorageDocumentiDocumentoDigestsInstance;
import mx.randalf.protocol.amazonS3.amazonaws.RandalfAmazonS3Aws;
import mx.randalf.protocol.amazonS3.exception.RandalfAmazonS3Exception;

/**
 * @author massi
 *
 */
class NodiS3 extends INodi<File> {

	/**
	 * 
	 */
	public NodiS3(MDNodi mdNodi, String nomeFileJar, String nomeFilePremis) {
		super(mdNodi, nomeFileJar, nomeFilePremis);
	}

	@Override
	public boolean isStorageActive() throws NodiException, NotImplementException {
		URL url = null;
		Socket socket = null;
		boolean result = false;
		int port = -1;

		try {
			url = new URL(pathStorageActive());

			if (url.getPort() == -1) {
				if (url.getProtocol().equals("http")) {
					port = 80;
				} else if (url.getProtocol().equals("https")) {
					port = 443;
				}
			} else {
				port = url.getPort();
			}
			socket = new Socket(url.getHost(), port);
			socket.close();
			result = true;
		} catch (NumberFormatException e) {
			throw new NodiException(e.getMessage(), e);
		} catch (Exception e) {
		}
		return result;
	}

	@Override
	public String pathStorageActive() throws NodiException, NotImplementException {
		return mdNodi.getS3Url();
	}

	@Override
	protected String genFileDest(String nomeFile) {
		String path = "";

		path += splitFolder(nomeFile);
		path += nomeFile;
		return path;
	}

	@Override
	public boolean copyFile(File is, long lengthFile, String md5Ori, ENodi eNodi) throws NodiException {

		RandalfAmazonS3Aws randalfAmazonS3 = null;
		boolean result = false;

		try {
			randalfAmazonS3 = new RandalfAmazonS3Aws(mdNodi.getS3Url(), Regions.fromName(mdNodi.getS3Region()),
					mdNodi.getS3AccessKey(), mdNodi.getS3SecretKey());
			if (randalfAmazonS3.sendFile(is, null, mdNodi.getS3BucketName(), genFileDest(eNodi))) {
				result = true;
			}
		} catch (RandalfAmazonS3Exception e) {
			throw new NodiException(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public boolean isFileExists(ENodi eNodi) throws NodiException, NotImplementException {
		RandalfAmazonS3Aws randalfAmazonS3 = null;
		boolean result = false;

		try {
			randalfAmazonS3 = new RandalfAmazonS3Aws(mdNodi.getS3Url(), Regions.fromName(mdNodi.getS3Region()),
					mdNodi.getS3AccessKey(), mdNodi.getS3SecretKey());
			
			result = randalfAmazonS3.exists(mdNodi.getS3BucketName(), genFileDest(eNodi));
//			result = randalfAmazonS3.exists(mdNodi.getS3BucketName(), getNomeFile(eNodi));
		} catch (RandalfAmazonS3Exception e) {
			throw new NodiException(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Storage checkStorage(Documenti documenti) throws NodiException, NotImplementException {
		Storage storage = null;
		StorageDocumenti storageDocumenti = null;
		StorageDocumentiDocumento[] storageDocumentiDocumentos = null;
		String esito = "OK";

		try {
			storage = new Storage();
			if (documenti != null && documenti.getNumDoc().intValue() > 0 && documenti.getDocumento() != null
					&& documenti.getDocumento().length > 0) {
				storageDocumenti = new StorageDocumenti();
				storageDocumenti.setNumDoc(new BigInteger(documenti.getDocumento().length + ""));

				storageDocumentiDocumentos = new StorageDocumentiDocumento[documenti.getDocumento().length];
				for (int x = 0; x < documenti.getDocumento().length; x++) {
					storageDocumentiDocumentos[x] = checkStorage(documenti.getDocumento()[x]);
				}
				storageDocumenti.setDocumento(storageDocumentiDocumentos);
				storage.setDocumenti(storageDocumenti);
			} else {
				esito = "DOCNOTFOUND";
			}
			storage.setEsito(esito);
		} catch (RandalfAmazonS3Exception e) {
			throw new NodiException(e.getMessage(), e);
		} catch (Exception e) {
			throw new NodiException(e.getMessage(), e);
		}
		return storage;
	}

	private StorageDocumentiDocumento checkStorage(DocumentiDocumento documento) throws RandalfAmazonS3Exception {
		RandalfAmazonS3Aws randalfAmazonS3 = null;
		StorageDocumentiDocumento storageDocumentiDocumento = null;
		StorageDocumentiDocumentoDigests[] storageDocumentiDocumentoDigests = null;
		String md5 = null;

		try {
			storageDocumentiDocumento = new StorageDocumentiDocumento();

			storageDocumentiDocumento.setDataMod(documento.getDataMod());
			storageDocumentiDocumento.setNomeFile(documento.getNomeFile());

			storageDocumentiDocumentoDigests = new StorageDocumentiDocumentoDigests[documento.getDigests().length];
			for (int x = 0; x < documento.getDigests().length; x++) {
				storageDocumentiDocumentoDigests[x] = checkStorage(documento.getDigests()[x]);
				if (documento.getDigests()[x].getInstance().getValue().equals(DocumentiDocumentoDigestsInstance._MD5)) {
					md5 = documento.getDigests()[x].getValue();
				}
			}
			storageDocumentiDocumento.setDigests(storageDocumentiDocumentoDigests);
			randalfAmazonS3 = new RandalfAmazonS3Aws(mdNodi.getS3Url(), Regions.fromName(mdNodi.getS3Region()),
					mdNodi.getS3AccessKey(), mdNodi.getS3SecretKey());

			if (randalfAmazonS3.exists(mdNodi.getS3BucketName(), documento.getNomeFile())) {
				if (randalfAmazonS3.isValid(mdNodi.getS3BucketName(), documento.getNomeFile(), null, md5)) {
					storageDocumentiDocumento.setEsito("FOUND");
				} else {
					storageDocumentiDocumento.setEsito("ERRORDIGEST");
				}
			} else {
				storageDocumentiDocumento.setEsito("NOTFOUND");
			}
		} catch (RandalfAmazonS3Exception e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}

		return storageDocumentiDocumento;
	}

	private StorageDocumentiDocumentoDigests checkStorage(DocumentiDocumentoDigests digests) {
		StorageDocumentiDocumentoDigests storageDocumentiDocumentoDigests = null;

		storageDocumentiDocumentoDigests = new StorageDocumentiDocumentoDigests();
		storageDocumentiDocumentoDigests
				.setInstance(StorageDocumentiDocumentoDigestsInstance.fromValue(digests.getInstance().getValue()));
		storageDocumentiDocumentoDigests.setValue(digests.getValue());
		return storageDocumentiDocumentoDigests;
	}

	@Override
	public Documenti genDocumenti() throws NodiException, NotImplementException {
		throw new NotImplementException("Metodo non implementato per questo tipo di risorsa");
	}

	@Override
	public File getFile(ENodi eNodi) throws NotImplementException {
		throw new NotImplementException("Metodo non implementato per questo tipo di risorsa");
	}

	@Override
	public Boolean getFile(ENodi eNodi, File output, Long start, Long end) throws NodiException, NotImplementException {
		InputStream inputStream = null;
		RandalfAmazonS3Aws randalfAmazonS3 = null;
		Boolean result = false;

		try {
			if (!output.getParentFile().exists()) {
				if (!output.getParentFile().mkdirs()) {
					throw new NodiException("Riscontrato problema nella creazione dellla cartella ["
							+ output.getParentFile().getAbsolutePath() + "]");
				}
			}
			randalfAmazonS3 = new RandalfAmazonS3Aws(mdNodi.getS3Url(), Regions.fromName(mdNodi.getS3Region()),
					mdNodi.getS3AccessKey(), mdNodi.getS3SecretKey());

			inputStream = randalfAmazonS3.getFile(mdNodi.getS3BucketName(), genFileDest(eNodi), start, end);

			FileUtils.copyInputStreamToFile(inputStream, output);
			result = true;
		} catch (FileNotFoundException e) {
			throw new NodiException(e.getMessage(), e);
		} catch (NodiException e) {
			throw new NodiException(e.getMessage(), e);
		} catch (RandalfAmazonS3Exception e) {
			throw new NodiException(e.getMessage(), e);
		} catch (IOException e) {
			throw new NodiException(e.getMessage(), e);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				throw new NodiException(e.getMessage(), e);
			}
		}
		return result;
	}

}
