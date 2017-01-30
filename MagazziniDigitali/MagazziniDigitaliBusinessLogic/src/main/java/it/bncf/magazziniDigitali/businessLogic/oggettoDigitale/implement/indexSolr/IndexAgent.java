/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.Params;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.magazziniDigitali.xsd.agent.AgentXsd;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public abstract class IndexAgent<AX extends AgentXsd<?, ?, ?, ?, ?, ?>, LRSICT, LAICT> {

	protected String name = null;

	/**
	 * 
	 */
	public IndexAgent(String name) {
		this.name=name;
	}

	@SuppressWarnings("unchecked")
	public void preIndexSolr(AgentXsd<?, ?, ?, ?, ?, ?> agentXsd, AddDocumentMD admd) throws SolrException{
		indexSolr((AX) agentXsd, admd);
	}

	public abstract void indexSolr(AX agentXsd, AddDocumentMD admd) throws SolrException;

	protected abstract void addLinkingRightsStatementIdentifier(Params params, LRSICT lrsi);

	protected abstract void addAgentExtension(Params params, LAICT laict);
}
