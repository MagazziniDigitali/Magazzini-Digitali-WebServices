/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileRights;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr.rights.IndexRights;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr.rights.IndexRights2_2;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr.rights.IndexRights3_0;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileAgent.SolrObjectFileAgent;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;
import it.magazziniDigitali.xsd.rights.RightsXsd;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public class SolrObjectFileRights extends SolrObjectFileAgent {

	private Logger log = Logger.getLogger(SolrObjectFileRights.class);

	/**
	 * 
	 */
	public SolrObjectFileRights() {
	}

	protected void publicSolrRights(java.io.File pathTar, AddDocumentMD admd, 
			String name) throws SolrException {
		java.io.File fName = null;
		FileInputStream fInput = null;
		RightsXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> rightsInput = null;
		IndexRights<?, ?, ?, ?, ?, ?, ?, ?> indexRights = null;

		try {
			fName = new java.io.File(pathTar.getAbsolutePath() + java.io.File.separator + filename);
			if (fName.exists()) {
				rightsInput = RightsXsd.open(fName);

				if (rightsInput.getVersion().equals("2.2")) {
					indexRights = new IndexRights2_2(name);
				} else {
					indexRights = new IndexRights3_0(name);
				}

				indexRights.preIndexSolr(rightsInput, admd);;
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
