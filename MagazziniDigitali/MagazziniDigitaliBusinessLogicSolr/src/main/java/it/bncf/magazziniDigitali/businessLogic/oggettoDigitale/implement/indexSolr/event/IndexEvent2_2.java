/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr.event;

import org.apache.log4j.Logger;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr.event.IndexEvent;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.event.SolrEvent2_2;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.magazziniDigitali.xsd.event.v2_2.EventV2_2Xsd;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public class IndexEvent2_2 extends IndexEvent<EventV2_2Xsd> {
	private Logger log = Logger.getLogger(IndexEvent2_2.class);

	public IndexEvent2_2(String name) {
		super(name);
	}

	@Override
	protected void checkEvent(EventV2_2Xsd events, AddDocumentMD admd)
			throws SolrException {
		SolrEvent2_2 se = null;
		
		try {
			if (events != null) {
				log.debug(
						"\n"+name + " [" + events.getEventIdentifier().getEventIdentifierValue() + "]" + " Eventi da preIndicizzare ");
				se = new SolrEvent2_2();
				se.publishSolr(events.getEvent(), admd);
				log.debug("\n"+name + " [" + events.getEventIdentifier().getEventIdentifierValue() + "]" + " Fine preIndicizzare eventi");
			}
		} catch (SolrException e) {
			throw e;
		}
	}

}
