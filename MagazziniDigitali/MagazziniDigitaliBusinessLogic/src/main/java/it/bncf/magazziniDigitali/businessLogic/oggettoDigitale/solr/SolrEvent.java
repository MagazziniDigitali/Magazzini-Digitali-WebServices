/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public abstract class SolrEvent<ECT, LOICT, LAICT, EOICT, EICT> {

	protected Params params;

	/**
	 * 
	 */
	public SolrEvent() {
		params = new Params();
	}

	public abstract boolean publishSolr(ECT object, AddDocumentMD admd) throws SolrException;

	protected abstract void publicSolrLinkingObjectIdentifier(List<LOICT> values);

	protected abstract void publicSolrLinkingAgentIdentifier(List<LAICT> values);

	protected abstract void publicSolrEventOutcomeInformation(List<EOICT> eventOutcomeInformations);

	protected abstract void publicSolrEventIdentifier(EICT eventIdentifier);

	protected Date convert(String value){
		Date date=null;
		GregorianCalendar gc = null;
		
		gc = new GregorianCalendar();
		gc.set(Calendar.YEAR, Integer.parseInt(value.substring(0,4)));
		gc.set(Calendar.MONTH, Integer.parseInt(value.substring(4,6))-1);
		gc.set(Calendar.DAY_OF_MONTH, Integer.parseInt(value.substring(6,8)));
		gc.set(Calendar.HOUR_OF_DAY, Integer.parseInt(value.substring(9,11)));
		gc.set(Calendar.MINUTE, Integer.parseInt(value.substring(11,13)));
		gc.set(Calendar.SECOND, Integer.parseInt(value.substring(13,15)));
		gc.set(Calendar.MILLISECOND, Integer.parseInt(value.substring(16)));
		date = new Date();
		date.setTime(gc.getTimeInMillis());
		return date;
	}
}
