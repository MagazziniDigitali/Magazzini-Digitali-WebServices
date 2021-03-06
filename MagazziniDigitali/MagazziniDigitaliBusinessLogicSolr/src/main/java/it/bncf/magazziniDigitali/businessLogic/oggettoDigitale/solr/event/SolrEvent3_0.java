/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.event;

import java.util.List;

import org.apache.log4j.Logger;

import gov.loc.premis.v3.EventComplexType;
import gov.loc.premis.v3.EventDetailInformationComplexType;
import gov.loc.premis.v3.EventIdentifierComplexType;
import gov.loc.premis.v3.EventOutcomeInformationComplexType;
import gov.loc.premis.v3.ExtensionComplexType;
import gov.loc.premis.v3.LinkingAgentIdentifierComplexType;
import gov.loc.premis.v3.LinkingObjectIdentifierComplexType;
import gov.loc.premis.v3.StringPlusAuthority;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.bncf.magazziniDigitali.solr.ItemMD;
import it.depositolegale.ticket.Ticket;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public class SolrEvent3_0 extends SolrEvent<EventComplexType, LinkingObjectIdentifierComplexType,
		LinkingAgentIdentifierComplexType, EventOutcomeInformationComplexType, EventIdentifierComplexType> {

	private Logger log = Logger.getLogger(SolrEvent3_0.class);

	/**
	 * 
	 */
	public SolrEvent3_0() {
		super();
	}

	@Override
	public boolean publishSolr(EventComplexType object, AddDocumentMD admd) throws SolrException {
		boolean ris = false;
		String[] st = null;
		
		try {
			params.getParams().clear();
			
			params.add(ItemMD.TIPOOGGETTO, ItemMD.TIPOOGGETTO_EVENTO);
			
			if (object.getEventIdentifier() != null){
				publicSolrEventIdentifier(object.getEventIdentifier());
			}

			if (object.getEventType() != null){
				params.add(ItemMD.EVENTTYPE, object.getEventType().getValue());
			}

			if (object.getEventDateTime() != null){
				st = object.getEventDateTime().split("/");
				for (int x=0; x<st.length; x++){
					params.add(ItemMD.EVENTDATE, convert(st[x]));
				}
			}

			if (object.getEventDetailInformation() != null){
				publicSolrEventDetailInformation(object.getEventDetailInformation());
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

	private void publicSolrEventDetailInformation(List<EventDetailInformationComplexType> eventDetailInformation) {
		for(int x=0; x<eventDetailInformation.size(); x++){
			if (eventDetailInformation.get(x).getEventDetailExtension()!=null){
				publicSolrEventDetailExtension(eventDetailInformation.get(x).getEventDetailExtension());
			}
			if (eventDetailInformation.get(x).getEventDetail() != null){
				params.add(ItemMD.EVENTDETAIL, eventDetailInformation.get(x).getEventDetail());
			}
		}
	}

	private void publicSolrEventDetailExtension(List<ExtensionComplexType> eventDetailExtension) {
		for (int x=0; x<eventDetailExtension.size(); x++) {
			if (eventDetailExtension.get(x).getAny()!= null){
				publicSolrEventDetailExtensionAny(eventDetailExtension.get(x).getAny());
			}
		}

	}

	private void publicSolrEventDetailExtensionAny(List<Object> any) {
		for (int x=0; x<any.size(); x++){
			if (any.get(x) instanceof Ticket){
				publicSolrEventDetailExtensionAnyTicket((Ticket)any.get(x));
			}
		}
	}

	private void publicSolrEventDetailExtensionAnyTicket(Ticket ticket) {

		if (ticket.
				getLinkingRightsStatementIdentifier().
				getLinkingRightsStatementIdentifierType().
				getValue().equals(PremisXsd.UUID_MD_RG)){
			if (ticket.
					getLinkingRightsStatementIdentifier().
					getLinkingRightsStatementIdentifierValue() != null &&
				!ticket.
					getLinkingRightsStatementIdentifier().
					getLinkingRightsStatementIdentifierValue().trim().equals("")){
				params.add(ItemMD.RIGHTOBJECTIDENTIFIER,  ticket.getLinkingRightsStatementIdentifier().getLinkingRightsStatementIdentifierValue());
			}
		}
		params.add(ItemMD.EVENTIPCLIENT, ticket.getIpClient());
		if (ticket.getLoginUtente() != null){
			params.add(ItemMD.EVENTLOGINUTENTE, ticket.getLoginUtente());
		}
	}

	@Override
	protected void publicSolrLinkingObjectIdentifier(List<LinkingObjectIdentifierComplexType> values) {
		LinkingObjectIdentifierComplexType value;
		for (int x=0; x<values.size(); x++){
			value =values.get(x);
			if (value != null) {
				if (value.getLinkingObjectIdentifierType().getValue().equals("codiceNBN")){
					params.add(ItemMD.NBN, value.getLinkingObjectIdentifierValue());
				} else if (value.getLinkingObjectIdentifierType().getValue().equals("urlOriginal")){
					params.add(ItemMD.URL, value.getLinkingObjectIdentifierValue());
				} else {
					params.add(ItemMD._ROOT_, value.getLinkingObjectIdentifierValue());
				}
			}
		}
	}

	@Override
	protected void publicSolrLinkingAgentIdentifier(List<LinkingAgentIdentifierComplexType> values) {
		LinkingAgentIdentifierComplexType value;
		for (int x=0; x<values.size(); x++){
			value =values.get(x);
			
			if (value.getLinkingAgentRole().get(0).getValue().equals(PremisXsd.SOFTWARE)){
				params.add(ItemMD.AGENTSOFTWARE, value.getLinkingAgentIdentifierValue());
			} else if (value.getLinkingAgentRole().get(0).getValue().equals(PremisXsd.DEPOSITANTE)){
				params.add(ItemMD.AGENTDEPOSITANTE, value.getLinkingAgentIdentifierValue());
			} else if (value.getLinkingAgentRole().get(0).getValue().equals(PremisXsd.DEPOSITARIO) ||
					value.getLinkingAgentRole().get(0).getValue().equals(PremisXsd.BIBLIOTECHE)){
				params.add(ItemMD.AGENTBIBLIOTECHE, value.getLinkingAgentIdentifierValue());
			} 
		}
	}

	@Override
	protected void publicSolrEventOutcomeInformation(List<EventOutcomeInformationComplexType> eventOutcomeInformations) {
		EventOutcomeInformationComplexType eventOutcomeInformation;
		for (int x=0; x<eventOutcomeInformations.size(); x++){
			eventOutcomeInformation =eventOutcomeInformations.get(x);
			for (int y=0; y<eventOutcomeInformation.getContent().size(); y++){
				if (eventOutcomeInformation.getContent().get(y).getValue() instanceof StringPlusAuthority){
					params.add(ItemMD.EVENTOUTCOME, ((StringPlusAuthority) eventOutcomeInformation.getContent().get(y).getValue()).getValue());
				}
			}
		}
	}

	@Override
	protected void publicSolrEventIdentifier(EventIdentifierComplexType eventIdentifier) {
		if (eventIdentifier.getEventIdentifierType().getValue().equals(PremisXsd.UUID_MD_EV)){
			params.add(ItemMD.ID, eventIdentifier.getEventIdentifierValue());
			params.add(ItemMD.EVENTID, eventIdentifier.getEventIdentifierValue());
		}
	}

}
