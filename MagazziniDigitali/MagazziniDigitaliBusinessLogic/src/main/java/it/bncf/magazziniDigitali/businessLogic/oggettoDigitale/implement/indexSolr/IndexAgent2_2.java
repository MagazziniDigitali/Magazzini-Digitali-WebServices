/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr;

import javax.xml.bind.JAXBElement;

import info.lc.xmlns.premis_v2.AgentIdentifierComplexType;
import info.lc.xmlns.premis_v2.ExtensionComplexType;
import info.lc.xmlns.premis_v2.LinkingAgentIdentifierComplexType;
import info.lc.xmlns.premis_v2.LinkingRightsStatementIdentifierComplexType;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.Params;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.bncf.magazziniDigitali.solr.ItemMD;
import it.magazziniDigitali.xsd.agent.AgentXsd;
import it.magazziniDigitali.xsd.agent.v2_2.AgentV2_2Xsd;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public class IndexAgent2_2 extends IndexAgent<AgentV2_2Xsd, 
		LinkingRightsStatementIdentifierComplexType, 
		LinkingAgentIdentifierComplexType> {

	/**
	 * 
	 */
	public IndexAgent2_2(String name) {
		super(name);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void indexSolr(AgentV2_2Xsd agentXsd, AddDocumentMD admd) throws SolrException {
		Params params = new Params();
		JAXBElement<LinkingAgentIdentifierComplexType> laict = null;
		
		try {
			params.add(ItemMD.TIPOOGGETTO, ItemMD.TIPOOGGETTO_AGENTE);
			for (AgentIdentifierComplexType aict :agentXsd.getAgentIdentifier()){
				if (aict.getAgentIdentifierType().equals(PremisXsd.UUID_MD_AG)){
					params.add(ItemMD.ID, aict.getAgentIdentifierValue());
					params.add(ItemMD.AGENTIDENTIFIER, aict.getAgentIdentifierValue());
				} else if (aict.getAgentIdentifierType().equals(AgentXsd.URI)){
					if (aict.getAgentIdentifierValue() != null &&
							!aict.getAgentIdentifierValue().trim().equals("")){
						params.add(ItemMD.URL, aict.getAgentIdentifierValue());
					}
				}
			}
			
			for (String an :agentXsd.getAgentName()){
				params.add(ItemMD.AGENTNAME, an);
			}
			
			if (agentXsd.getAgentType() != null){
				params.add(ItemMD.AGENTTYPE, agentXsd.getAgentType());
			}

			for (String an :agentXsd.getAgentNote()){
				if (an != null &&
						!an.trim().equals("")){
					params.add(ItemMD.AGENTNOTE, an);
				}
			}

			for (ExtensionComplexType ae:agentXsd.getAgentExtension()){
				laict = (JAXBElement<LinkingAgentIdentifierComplexType>) ae.getAny();
				addAgentExtension(params, laict.getValue());
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

		if (lrsi.getLinkingRightsStatementIdentifierType().equals(PremisXsd.UUID_MD_RG)){
			params.add(ItemMD.AGENTIDRIGTHS, lrsi.getLinkingRightsStatementIdentifierValue());
		}
	}

	protected void addAgentExtension(Params params, LinkingAgentIdentifierComplexType laict){

		if (laict.getLinkingAgentIdentifierType().equals(PremisXsd.UUID_MD_AG)){
			params.add(ItemMD.AGENTIDISTITUZIONE, laict.getLinkingAgentIdentifierValue());
		}
	}

}
