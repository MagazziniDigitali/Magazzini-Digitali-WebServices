/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFilePremis;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr.premis.IndexPremis;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr.premis.IndexPremis2_2;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr.premis.IndexPremis3_0;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileMets.SolrObjectFileMets;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public class SolrObjectFilePremis extends SolrObjectFileMets {

	private Logger log = Logger.getLogger(SolrObjectFilePremis.class);

	/**
	 * 
	 */
	public SolrObjectFilePremis() {
	}

	protected void publicSolrPremis(java.io.File pathTar, AddDocumentMD admd, IMDConfiguration<?> configuration,
			String name, Logger logPublish, String objectIdentifierPremis) throws SolrException {
		java.io.File fName = null;
		FileInputStream fInput = null;
		PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisInput = null;
		IndexPremis<?, ?, ?> indexPremis = null;

		try {

			fName = new java.io.File(pathTar.getAbsolutePath() + java.io.File.separator + filename);
			if (fName.exists()) {
				premisInput = PremisXsd.open(fName);

				if (premisInput.getVersion().equals("2.2")) {
					indexPremis = new IndexPremis2_2(name);
				} else {
					indexPremis = new IndexPremis3_0(name);
				}

				indexPremis.preIndexSolr(premisInput, admd, configuration, logPublish, objectIdentifierPremis, pathTar);
			} else {
				throw new SolrException("Impossibile trovare il file premis: "+filename+" nel contenitore");
			}
		} catch (SolrException e) {
			throw e;
		} catch (PremisXsdException e) {
			log.error(e.getMessage(), e);
			throw new SolrException(e.getMessage(), e);
		} finally {
			try {
				if (fInput != null) {
					fInput.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage(), e);
				throw new SolrException(e.getMessage(), e);
			}
		}

	}
}
