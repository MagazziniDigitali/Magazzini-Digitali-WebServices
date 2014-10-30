package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale;

import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.OggettoDigitaleGeoReplica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class OggettoDigitaleGeoReplicaBusiness extends OggettoDigitaleBusiness{
	
	public Logger log = Logger.getLogger(OggettoDigitaleGeoReplicaBusiness.class);

	public OggettoDigitaleGeoReplicaBusiness(HibernateTemplate hibernateTemplate) {
		super(hibernateTemplate);
	}

	/**
	 * Questo metodo viene utilizzato per gestire la pubblicazione del materiale
	 * 
	 * @param application
	 */
	public void esegui(String application, boolean testMode, Logger logPublish) {
		File pCoda = null;
		File[] codas = null;
		OggettoDigitaleGeoReplica odgr = null;
		BufferedReader br = null;
		FileReader fr = null;
		String line = null;
		String[] st = null;
		MDFilesTmpBusiness mdFileTmp = null;

		try {
			logPublish.debug("Ricerco oggetti da GeoReplicare");
			mdFileTmp = new MDFilesTmpBusiness(hibernateTemplate);

			pCoda = new File(Configuration.getValue("demoni.Coda.path"));
			if (pCoda.exists()){
				codas = pCoda.listFiles(new FileFilter() {
					
					@Override
					public boolean accept(File pathname) {
						boolean ris = false;
						File fElab = null;
						if (pathname.isFile()){
							if (pathname.getName().toLowerCase().endsWith(".coda")){
								fElab = new File(pathname.getAbsolutePath()+".elab");
								if (!fElab.exists()){
									ris = true;
								}
							}
						}
						return ris;
					}
				});
			}

			odgr = new OggettoDigitaleGeoReplica(hibernateTemplate,logPublish,"GeoReplica");
			for (File coda: codas){
				try {
					logPublish.info("Analizzo il file: " + coda.getAbsolutePath());
					fr = new FileReader(coda);
					br = new BufferedReader(fr);
					while((line = br.readLine())!= null){
						st = line.split("\t");
						odgr.esegui(st[0], mdFileTmp, application);
					}
				} catch (FileNotFoundException e) {
					log.error(e.getMessage(),e);
				} catch (IOException e) {
					log.error(e.getMessage(),e);
				} finally{
					try {
						if (br != null){
							br.close();
						}
						if (fr != null){
							fr.close();
						}
					} catch (IOException e) {
						log.error(e.getMessage(),e);
					}
				}
			}
		} catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
		}
	}
}
