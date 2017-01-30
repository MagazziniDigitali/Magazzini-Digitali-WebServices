/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr.IndexAgent;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr.IndexAgent2_2;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr.IndexAgent3_0;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.magazziniDigitali.xsd.agent.AgentXsd;
import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public class SolrObjectFileAgent extends SolrObjectFilePremis {

	private Logger log = Logger.getLogger(SolrObjectFileAgent.class);

	/**
	 * 
	 */
	public SolrObjectFileAgent() {
	}

	protected void publicSolrAgent(java.io.File pathTar, AddDocumentMD admd
			,String name
			) throws SolrException {
		java.io.File fName = null;
		FileInputStream fInput = null;
		AgentXsd<?, ?, ?, ?, ?, ?> agentInput = null;
		IndexAgent<?,?,?> indexPremis = null;

		try {
			fName = new java.io.File(pathTar.getAbsolutePath() + java.io.File.separator + filename);
			if (fName.exists()) {
				agentInput = AgentXsd.open(fName);

				if (agentInput.getVersion().equals("2.2")) {
					indexPremis = new IndexAgent2_2(name);
				} else {
					indexPremis = new IndexAgent3_0(name);
				}

				indexPremis.preIndexSolr(agentInput, admd);
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
