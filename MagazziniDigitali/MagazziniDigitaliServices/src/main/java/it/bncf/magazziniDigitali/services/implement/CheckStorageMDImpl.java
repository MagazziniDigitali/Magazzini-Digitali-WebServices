package it.bncf.magazziniDigitali.services.implement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import it.depositolegale.www.storage.Documenti;
import it.depositolegale.www.storage.DocumentiDocumentoDigests;
import it.depositolegale.www.storage.DocumentiDocumentoDigestsInstance;
import it.depositolegale.www.storage.Storage;
import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.digest.MD5;
import mx.randalf.digest.SHA1;
import mx.randalf.digest.SHA256;

public class CheckStorageMDImpl {

	private static Logger log = Logger.getLogger(CheckStorageMDImpl.class);

	public CheckStorageMDImpl() {
	}

	public static Storage checkStorageMDOperation(Documenti documenti) throws java.rmi.RemoteException {
		Storage output = null;
		File file = null;
		SHA1 sha1 = null;
		SHA256 sha256 = null;
		MD5 md5 = null;
		boolean digestValido = false;

		output = new Storage(documenti, "OK");
		if (output.getDocumenti() != null && output.getDocumenti().getNumDoc().intValue() > 0
				&& output.getDocumenti().getDocumento() != null && output.getDocumenti().getDocumento().length > 0) {
			for (int x = 0; x < output.getDocumenti().getDocumento().length; x++) {
				try {
					file = new File(
							// DepositoLegaleAxisServlet.mdConfiguration.getSoftwareConfigMDNodi("nodo").getPathStorage()+
							Configuration.getValue("demoni.Publish.pathStorage") + File.separator
									+ output.getDocumenti().getDocumento()[x].getNomeFile());
					if (file.exists()) {
						try {

							for (DocumentiDocumentoDigests digest : output.getDocumenti().getDocumento()[x]
									.getDigests()) {
								if (digest.getInstance().getValue().equals(DocumentiDocumentoDigestsInstance._SHA1)) {
									sha1 = new SHA1(file);
									if (sha1.getDigest().equalsIgnoreCase(digest.getValue())) {
										digestValido = true;
										break;
									}
								} else if (digest.getInstance().getValue()
										.equals(DocumentiDocumentoDigestsInstance._SHA256)) {
									sha256 = new SHA256(file);
									if (sha256.getDigest().equalsIgnoreCase(digest.getValue())) {
										digestValido = true;
										break;
									}
								} else if (digest.getInstance().getValue()
										.equals(DocumentiDocumentoDigestsInstance._SHA1)) {
									md5 = new MD5(file);
									if (md5.getDigest().equalsIgnoreCase(digest.getValue())) {
										digestValido = true;
										break;
									}
								}
							}
							if (digestValido) {
								output.getDocumenti().getDocumento()[x].setEsito("FOUND");
							} else {
								output.getDocumenti().getDocumento()[x].setEsito("ERRORDIGEST");
								addError(output);
							}
						} catch (NoSuchAlgorithmException e) {
							log.error(e.getMessage(), e);
							output.getDocumenti().getDocumento()[x].setEsito("ERRORDIGEST");
							addError(output);
						} catch (FileNotFoundException e) {
							log.error(e.getMessage(), e);
							output.getDocumenti().getDocumento()[x].setEsito("ERRORDIGEST");
							addError(output);
						} catch (IOException e) {
							log.error(e.getMessage(), e);
							output.getDocumenti().getDocumento()[x].setEsito("ERRORDIGEST");
							addError(output);
						}
					} else {
						if (!file.getParentFile().exists()) {
							if (file.getParentFile().mkdirs()) {
								output.getDocumenti().getDocumento()[x].setEsito("NOTFOUND");
							} else {
								output.getDocumenti().getDocumento()[x].setEsito("ERRORCREATEFOLDER");
								addError(output);
							}
						} else {
							output.getDocumenti().getDocumento()[x].setEsito("NOTFOUND");
						}
					}
					// } catch (MDConfigurationException e) {
					// log.error(e.getMessage(), e);
					// output.getDocumenti().getDocumento()[x].setEsito("ERROR");
					// addError(output);
				} catch (ConfigurationException e) {
					log.error(e.getMessage(), e);
					output.getDocumenti().getDocumento()[x].setEsito("ERROR");
					addError(output);
				}
			}

		} else {
			output.setEsito("DOCNOTFOUND");
		}
		return output;
	}

	private static void addError(Storage output) {
		BigInteger numErr = null;
		output.setEsito("KO");

		if (output.getDocumenti().getNumErr() == null) {
			numErr = new BigInteger("1");
		} else {
			numErr = output.getDocumenti().getNumErr();
			numErr.add(new BigInteger("1"));
		}
		output.getDocumenti().setNumErr(numErr);
	}
}
