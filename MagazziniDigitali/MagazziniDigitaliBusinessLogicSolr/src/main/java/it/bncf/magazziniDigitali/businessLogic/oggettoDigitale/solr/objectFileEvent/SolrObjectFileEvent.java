/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileEvent;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr.event.IndexEvent;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr.event.IndexEvent2_2;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr.event.IndexEvent3_0;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.objectFileRights.SolrObjectFileRights;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.magazziniDigitali.xsd.event.EventXsd;
import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public class SolrObjectFileEvent extends SolrObjectFileRights {

	private Logger log = LogManager.getLogger(SolrObjectFileEvent.class);

	/**
	 * 
	 */
	public SolrObjectFileEvent() {
	}

	protected void publicSolrEvent(java.io.File pathTar, AddDocumentMD admd, 
			String name) throws SolrException {
		java.io.File fName = null;
		FileInputStream fInput = null;
		EventXsd<?, ?, ?, ?, ?> eventInput = null;
		IndexEvent<?> indexRights = null;

		try {
			fName = new java.io.File(pathTar.getAbsolutePath() + java.io.File.separator + filename);
			if (fName.exists()) {
				eventInput = EventXsd.open(fName);

				if (eventInput.getVersion().equals("2.2")) {
					indexRights = new IndexEvent2_2(name);
				} else {
					indexRights = new IndexEvent3_0(name);
				}

				indexRights.preIndexSolr(eventInput, admd);
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
