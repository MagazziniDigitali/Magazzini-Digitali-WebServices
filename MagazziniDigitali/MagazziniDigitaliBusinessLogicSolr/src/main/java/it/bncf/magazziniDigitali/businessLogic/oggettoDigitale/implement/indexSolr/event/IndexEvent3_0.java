/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.event.SolrEvent3_0;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.magazziniDigitali.xsd.event.v3_0.EventV3_0Xsd;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public class IndexEvent3_0 extends IndexEvent<EventV3_0Xsd> {
	private Logger log = LogManager.getLogger(IndexEvent3_0.class);
	
	public IndexEvent3_0(String name) {
		super(name);
	}

	@Override
	protected void checkEvent(EventV3_0Xsd events, AddDocumentMD admd)
			throws SolrException {
		SolrEvent3_0 se = null;
		
		try {
			if (events != null) {
				log.debug(
						"\n"+name + " [" + events.getEventIdentifier().getEventIdentifierValue() + "]" + " Eventi da preIndicizzare ");
				se = new SolrEvent3_0();
				se.publishSolr(events.getEvent(), admd);
				log.debug("\n"+name + " [" + events.getEventIdentifier().getEventIdentifierValue() + "]" + " Fine preIndicizzare eventi");
			}
		} catch (SolrException e) {
			throw e;
		}
	}

}
