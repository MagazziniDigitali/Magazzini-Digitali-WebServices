/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.event;

import java.util.List;

import org.apache.log4j.Logger;

import info.lc.xmlns.premis_v2.EventComplexType;
import info.lc.xmlns.premis_v2.EventIdentifierComplexType;
import info.lc.xmlns.premis_v2.EventOutcomeInformationComplexType;
import info.lc.xmlns.premis_v2.LinkingAgentIdentifierComplexType;
import info.lc.xmlns.premis_v2.LinkingObjectIdentifierComplexType;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.bncf.magazziniDigitali.solr.ItemMD;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public class SolrEvent2_2 extends SolrEvent<EventComplexType, LinkingObjectIdentifierComplexType,
		LinkingAgentIdentifierComplexType, EventOutcomeInformationComplexType, EventIdentifierComplexType> {

	private Logger log = Logger.getLogger(SolrEvent2_2.class);

	/**
	 * 
	 */
	public SolrEvent2_2() {
		super();
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
				publicSolrEventOutcomeInformation(object.getEventOutcomeInformation());
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

	protected void publicSolrLinkingObjectIdentifier(List<LinkingObjectIdentifierComplexType> values){
		LinkingObjectIdentifierComplexType value;
		for (int x=0; x<values.size(); x++){
			value =values.get(x);
			if (value.getLinkingObjectIdentifierType().equals("codiceNBN")){
				params.add(ItemMD.NBN, value.getLinkingObjectIdentifierValue());
			} else if (value.getLinkingObjectIdentifierType().equals("urlOriginal")){
				params.add(ItemMD.URL, value.getLinkingObjectIdentifierValue());
			} else {
				params.add(ItemMD._ROOT_, value.getLinkingObjectIdentifierValue());
			}
		}
	}

	protected void publicSolrLinkingAgentIdentifier(List<LinkingAgentIdentifierComplexType> values){
		LinkingAgentIdentifierComplexType value;
		for (int x=0; x<values.size(); x++){
			value =values.get(x);
			
			if (value.getLinkingAgentRole().get(0).equals(PremisXsd.SOFTWARE)){
				params.add(ItemMD.AGENTSOFTWARE, value.getLinkingAgentIdentifierValue());
			} else if (value.getLinkingAgentRole().get(0).equals(PremisXsd.DEPOSITANTE)){
				params.add(ItemMD.AGENTDEPOSITANTE, value.getLinkingAgentIdentifierValue());
			} else if (value.getLinkingAgentRole().get(0).equals(PremisXsd.DEPOSITARIO) ||
					value.getLinkingAgentRole().get(0).equals(PremisXsd.BIBLIOTECHE)){
				params.add(ItemMD.AGENTBIBLIOTECHE, value.getLinkingAgentIdentifierValue());
			} 
		}
	}

	protected void publicSolrEventOutcomeInformation(List<EventOutcomeInformationComplexType> eventOutcomeInformations){
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

	protected void publicSolrEventIdentifier(EventIdentifierComplexType eventIdentifier){
		if (eventIdentifier.getEventIdentifierType().equals(PremisXsd.UUID_MD_EV)){
			params.add(ItemMD.ID, eventIdentifier.getEventIdentifierValue());
			params.add(ItemMD.EVENTID, eventIdentifier.getEventIdentifierValue());
		}
	}

}
