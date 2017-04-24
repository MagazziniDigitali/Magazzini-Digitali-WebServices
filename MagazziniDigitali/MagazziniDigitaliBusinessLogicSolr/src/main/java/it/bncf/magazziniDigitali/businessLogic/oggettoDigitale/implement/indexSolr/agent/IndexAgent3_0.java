/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr.agent;

import javax.xml.bind.JAXBElement;

import gov.loc.premis.v3.AgentIdentifierComplexType;
import gov.loc.premis.v3.ExtensionComplexType;
import gov.loc.premis.v3.LinkingAgentIdentifierComplexType;
import gov.loc.premis.v3.LinkingRightsStatementIdentifierComplexType;
import gov.loc.premis.v3.StringPlusAuthority;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.param.Params;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.bncf.magazziniDigitali.solr.ItemMD;
import it.magazziniDigitali.xsd.agent.AgentXsd;
import it.magazziniDigitali.xsd.agent.v3_0.AgentV3_0Xsd;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public class IndexAgent3_0 extends IndexAgent<AgentV3_0Xsd, 
	LinkingRightsStatementIdentifierComplexType, 
	LinkingAgentIdentifierComplexType> {

	
	/**
	 * 
	 */
	public IndexAgent3_0(String name) {
		super(name);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void indexSolr(AgentV3_0Xsd agentXsd, AddDocumentMD admd) throws SolrException {
		Params params = new Params();
		JAXBElement<LinkingAgentIdentifierComplexType> laict = null;
		
		try {
			params.add(ItemMD.TIPOOGGETTO, ItemMD.TIPOOGGETTO_AGENTE);
			for (AgentIdentifierComplexType aict :agentXsd.getAgentIdentifier()){
				if (aict.getAgentIdentifierType().getValue().equals(PremisXsd.UUID_MD_AG)){
					params.add(ItemMD.ID, aict.getAgentIdentifierValue());
					params.add(ItemMD.AGENTIDENTIFIER, aict.getAgentIdentifierValue());
				} else if (aict.getAgentIdentifierType().getValue().equals(AgentXsd.URI)){
					if (aict.getAgentIdentifierValue() != null &&
							!aict.getAgentIdentifierValue().trim().equals("")){
						params.add(ItemMD.URL, aict.getAgentIdentifierValue());
					}
				}
			}
			
			for (StringPlusAuthority an :agentXsd.getAgentName()){
				params.add(ItemMD.AGENTNAME, an.getValue());
			}
			
			if (agentXsd.getAgentType() != null){
				params.add(ItemMD.AGENTTYPE, agentXsd.getAgentType().getValue());
			}

			for (String an :agentXsd.getAgentNote()){
				if (an != null &&
						!an.trim().equals("")){
					params.add(ItemMD.AGENTNOTE, an);
				}
			}

			for (ExtensionComplexType ae:agentXsd.getAgentExtension()){
				for (Object obj: ae.getAny()){
					laict = (JAXBElement<LinkingAgentIdentifierComplexType>) obj;
					addAgentExtension(params, laict.getValue());
				}
			}

			for (LinkingRightsStatementIdentifierComplexType lrsi : agentXsd.getLinkingRightsStatementIdentifier()){
				addLinkingRightsStatementIdentifier(params, lrsi);
			}
			admd.add(params.getParams(), new ItemMD());
		} catch (SolrException e) {
			throw e;
		}
	}

	protected void addLinkingRightsStatementIdentifier(Params params, LinkingRightsStatementIdentifierComplexType lrsi){

		if (lrsi.getLinkingRightsStatementIdentifierType().getValue().equals(PremisXsd.UUID_MD_RG)){
			params.add(ItemMD.AGENTIDRIGTHS, lrsi.getLinkingRightsStatementIdentifierValue());
		}
	}

	protected void addAgentExtension(Params params, LinkingAgentIdentifierComplexType laict){

		if (laict.getLinkingAgentIdentifierType().getValue().equals(PremisXsd.UUID_MD_AG)){
			params.add(ItemMD.AGENTIDISTITUZIONE, laict.getLinkingAgentIdentifierValue());
		}
	}
}
