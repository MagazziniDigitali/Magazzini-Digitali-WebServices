/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr;

import info.lc.xmlns.premis_v2.EventComplexType;
import info.lc.xmlns.premis_v2.EventIdentifierComplexType;
import info.lc.xmlns.premis_v2.EventOutcomeInformationComplexType;
import info.lc.xmlns.premis_v2.LinkingAgentIdentifierComplexType;
import info.lc.xmlns.premis_v2.LinkingObjectIdentifierComplexType;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.bncf.magazziniDigitali.solr.ItemMD;
import it.magazziniDigitali.xsd.premis.PremisXsd;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import mx.randalf.solr.exception.SolrException;

import org.apache.log4j.Logger;

/**
 * @author massi
 *
 */
public class SolrEvent {

	private Logger log = Logger.getLogger(getClass());
	private Params params;

	/**
	 * 
	 */
	public SolrEvent() {
		params = new Params();
	}

	public boolean publishSolr(EventComplexType object, AddDocumentMD admd) throws SolrException{
		boolean ris = false;
		String[] st = null;
		
		try {
			params.getParams().clear();
			
			params.add(ItemMD.TIPOOGGETTO, ItemMD.TIPOOGGETTO_EVENTO);
			
			if (object.getEventIdentifier() != null){
				publicSolrEventIdentifier(object.getEventIdentifier());
			}

			if (object.getEventType() != null){
				params.add(ItemMD.EVENTTYPE, object.getEventType());
			}

			if (object.getEventDateTime() != null){
				st = object.getEventDateTime().split("/");
				for (int x=0; x<st.length; x++){
					params.add(ItemMD.EVENTDATE, convert(st[x]));
				}
			}

			if (object.getEventDetail() != null){
				params.add(ItemMD.EVENTDETAIL, object.getEventDetail());
			}

			if (object.getEventOutcomeInformation() != null){
				publicSolrEventIdentifier(object.getEventOutcomeInformation());
			}
			if (object.getLinkingAgentIdentifier() != null){
				publicSolrLinkingAgentIdentifier(object.getLinkingAgentIdentifier());
			}
			
			if (object.getLinkingObjectIdentifier() != null) {
				publicSolrLinkingObjectIdentifier(object.getLinkingObjectIdentifier());
			}

			admd.add(params.getParams(), new ItemMD());
			ris = true;
		} catch (SolrException e) {
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new SolrException(e.getMessage(), e);
		}
		return ris;
	}

	private void publicSolrLinkingObjectIdentifier(List<LinkingObjectIdentifierComplexType> values){
		LinkingObjectIdentifierComplexType value;
		for (int x=0; x<values.size(); x++){
			value =values.get(x);
			params.add(ItemMD._ROOT_, value.getLinkingObjectIdentifierValue());
		}
	}

	private void publicSolrLinkingAgentIdentifier(List<LinkingAgentIdentifierComplexType> values){
		LinkingAgentIdentifierComplexType value;
		for (int x=0; x<values.size(); x++){
			value =values.get(x);
			
			if (value.getLinkingAgentRole().get(0).equals("software")){
				params.add(ItemMD.AGENTSOFTWARE, value.getLinkingAgentIdentifierValue());
			} else if (value.getLinkingAgentRole().get(0).equals("depositante")){
				params.add(ItemMD.AGENTDEPOSITANTE, value.getLinkingAgentIdentifierValue());
			} 
		}
	}

	private void publicSolrEventIdentifier(List<EventOutcomeInformationComplexType> eventOutcomeInformations){
		EventOutcomeInformationComplexType eventOutcomeInformation;
		for (int x=0; x<eventOutcomeInformations.size(); x++){
			eventOutcomeInformation =eventOutcomeInformations.get(x);
			for (int y=0; y<eventOutcomeInformation.getContent().size(); y++){
				if (eventOutcomeInformation.getContent().get(y).getValue() instanceof String){
					params.add(ItemMD.EVENTOUTCOME, (String) eventOutcomeInformation.getContent().get(y).getValue());
				}
			}
		}
	}

	private Date convert(String value){
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

	private void publicSolrEventIdentifier(EventIdentifierComplexType eventIdentifier){
		if (eventIdentifier.getEventIdentifierType().equals(PremisXsd.UUID_MD_EV)){
			params.add(ItemMD.ID, eventIdentifier.getEventIdentifierValue());
			params.add(ItemMD.EVENTID, eventIdentifier.getEventIdentifierValue());
		}
	}

}
