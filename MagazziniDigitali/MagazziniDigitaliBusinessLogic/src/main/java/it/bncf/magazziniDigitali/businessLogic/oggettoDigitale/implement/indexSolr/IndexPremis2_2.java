/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import info.lc.xmlns.premis_v2.EventComplexType;
import info.lc.xmlns.premis_v2.ObjectComplexType;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.SolrEvent;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.SolrObjectFileAnalyze2_2;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.magazziniDigitali.xsd.premis.v2_2.PremisV2_2Xsd;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public class IndexPremis2_2 extends IndexPremis<PremisV2_2Xsd, ObjectComplexType, EventComplexType> {

	/**
	 * 
	 */
	public IndexPremis2_2(String name) {
		super(name);
	}

	@Override
	protected void checkObject(List<ObjectComplexType> objects, Logger logPublish, String objectIdentifierPremis, AddDocumentMD admd,
			File pathTar, IMDConfiguration<?> configuration) throws SolrException{
		SolrObjectFileAnalyze2_2 sof = null;
		ObjectComplexType object = null;
		
		try {
			if (objects != null && objects.size() > 0) {
				logPublish.info(
						name + " [" + objectIdentifierPremis + "]" + " Oggetto da preIndicizzare " + objects.size());
				sof = new SolrObjectFileAnalyze2_2();
				for (int x = 0; x < objects.size(); x++) {
					if ((x % 100) == 0) {
						logPublish.info(
								name + " [" + objectIdentifierPremis + "]" + " Oggetto " + x + "/" + objects.size());
						System.gc();
					}
					object = objects.get(x);
					if (object instanceof info.lc.xmlns.premis_v2.File) {
						sof.publishSolr((info.lc.xmlns.premis_v2.File) object, admd, pathTar, configuration);
					}
				}
				logPublish.info(name + " [" + objectIdentifierPremis + "]" + " Fine preIndicizzare oggetti");
			}
			System.gc();
		} catch (SolrException e) {
			throw e;
		}
	}

	@Override
	protected void checkEvent(List<EventComplexType> events, Logger logPublish, String objectIdentifierPremis, AddDocumentMD admd) 
			throws SolrException {
		SolrEvent se = null;
		EventComplexType event = null;
		
		try {
			if (events != null && events.size() > 0) {
				logPublish.info(
						name + " [" + objectIdentifierPremis + "]" + " Eventi da preIndicizzare " + events.size());
				se = new SolrEvent();
				for (int x = 0; x < events.size(); x++) {
					if ((x % 100) == 0) {
						logPublish.info(
								name + " [" + objectIdentifierPremis + "]" + " Eventi " + x + "/" + events.size());
						System.gc();
					}
					event = events.get(x);
					se.publishSolr(event, admd);
				}
				logPublish.info(name + " [" + objectIdentifierPremis + "]" + " Fine preIndicizzare eventi");
			}
		} catch (SolrException e) {
			throw e;
		}
	}

}
