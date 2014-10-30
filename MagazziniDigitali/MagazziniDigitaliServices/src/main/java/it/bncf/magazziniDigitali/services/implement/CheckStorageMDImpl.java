package it.bncf.magazziniDigitali.services.implement;

import it.depositolegale.www.storage.Documenti;
import it.depositolegale.www.storage.Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.digest.SHA1;

import org.apache.log4j.Logger;

public class CheckStorageMDImpl {

	private static Logger log = Logger.getLogger(CheckStorageMDImpl.class);

	public CheckStorageMDImpl() {
	}

    public static Storage checkStorageMDOperation(Documenti documenti) throws java.rmi.RemoteException {
    	Storage output = null;
    	File file = null; 
    	SHA1 sha1 = null;

    	output = new Storage(documenti, "OK");
    	if (output.getDocumenti() != null &&
    			output.getDocumenti().getNumDoc().intValue()>0 &&
    			output.getDocumenti().getDocumento() != null &&
    			output.getDocumenti().getDocumento().length>0){
    		sha1 = new SHA1();
    		for (int x=0; x<output.getDocumenti().getDocumento().length; x++){
    			try {
					file = new File(Configuration.getValue("demoni.Publish.pathStorage")+
							File.separator+
							output.getDocumenti().getDocumento()[x].getNomeFile());
					if (file.exists()){
						try {
							if (sha1.getDigest(file).equalsIgnoreCase(output.getDocumenti().getDocumento()[x].getDigest())){
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
						if (!file.getParentFile().exists()){
							if (file.getParentFile().mkdirs()){
								output.getDocumenti().getDocumento()[x].setEsito("NOTFOUND");
							} else{
								output.getDocumenti().getDocumento()[x].setEsito("ERRORCREATEFOLDER");
								addError(output);
							}
						} else {
							output.getDocumenti().getDocumento()[x].setEsito("NOTFOUND");
						}
					}
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
    
    private static void addError(Storage output){
    	BigInteger numErr = null;
    	output.setEsito("KO");
    	
		if (output.getDocumenti().getNumErr()==null){
			numErr = new BigInteger("1");
		} else {
			numErr = output.getDocumenti().getNumErr();
			numErr.add(new BigInteger("1"));
		}
		output.getDocumenti().setNumErr(numErr);
    }
}
