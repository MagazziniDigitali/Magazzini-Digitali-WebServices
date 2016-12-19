/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import gov.loc.premis.v3.EventComplexType;
import gov.loc.premis.v3.ObjectComplexType;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.SolrObjectFileAnalyze3_0;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.magazziniDigitali.xsd.premis.v3_0.PremisV3_0Xsd;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public class IndexPremis3_0 extends IndexPremis<PremisV3_0Xsd, ObjectComplexType, EventComplexType> {

	/**
	 * 
	 */
	public IndexPremis3_0(String name) {
		super(name);
	}

	@Override
	protected void checkObject(List<ObjectComplexType> objects, Logger logPublish, String objectIdentifierPremis,
			AddDocumentMD admd, File pathTar, IMDConfiguration<?> configuration) throws SolrException {
		SolrObjectFileAnalyze3_0 sof = null;
		ObjectComplexType object = null;
		
		try {
			if (objects != null && objects.size() > 0) {
				logPublish.info(
						name + " [" + objectIdentifierPremis + "]" + " Oggetto da preIndicizzare " + objects.size());
				sof = new SolrObjectFileAnalyze3_0();
				for (int x = 0; x < objects.size(); x++) {
					if ((x % 100) == 0) {
						logPublish.info(
								name + " [" + objectIdentifierPremis + "]" + " Oggetto " + x + "/" + objects.size());
						System.gc();
					}
					object = objects.get(x);
					if (object instanceof gov.loc.premis.v3.File) {
						sof.publishSolr((gov.loc.premis.v3.File) object, admd, pathTar, configuration);
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
	protected void checkEvent(List<EventComplexType> events, Logger logPublish, String objectIdentifierPremis,
			AddDocumentMD admd) throws SolrException {
		// TODO Auto-generated method stub
		
	}

}
