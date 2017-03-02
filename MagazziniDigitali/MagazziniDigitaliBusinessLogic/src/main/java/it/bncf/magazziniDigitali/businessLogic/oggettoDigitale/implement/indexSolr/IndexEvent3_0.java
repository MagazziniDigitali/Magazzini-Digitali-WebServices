/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr;

import org.apache.log4j.Logger;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.SolrEvent3_0;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.magazziniDigitali.xsd.event.v3_0.EventV3_0Xsd;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public class IndexEvent3_0 extends IndexEvent<EventV3_0Xsd> {
	private Logger log = Logger.getLogger(IndexEvent3_0.class);
	
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
						name + " [" + events.getEventIdentifier().getEventIdentifierValue() + "]" + " Eventi da preIndicizzare ");
				se = new SolrEvent3_0();
				se.publishSolr(events.getEvent(), admd);
				log.debug(name + " [" + events.getEventIdentifier().getEventIdentifierValue() + "]" + " Fine preIndicizzare eventi");
			}
		} catch (SolrException e) {
			throw e;
		}
	}

}
