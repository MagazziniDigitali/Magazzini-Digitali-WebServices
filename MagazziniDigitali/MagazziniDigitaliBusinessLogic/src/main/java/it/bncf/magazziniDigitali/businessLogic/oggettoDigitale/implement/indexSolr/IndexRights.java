/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.Params;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.magazziniDigitali.xsd.rights.RightsXsd;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public abstract class IndexRights<RX extends RightsXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?>, 
	RSCT, RSICT, SPA, ORICT, RGCT, LOICT, SICT> {

	protected String name = null;

	/**
	 * 
	 */
	public IndexRights(String name) {
		this.name=name;
	}

	@SuppressWarnings("unchecked")
	public void preIndexSolr(RightsXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> agentXsd, AddDocumentMD admd) throws SolrException{
		indexSolr((RX) agentXsd, admd);
	}

	public abstract void indexSolr(RX agentXsd, AddDocumentMD admd) throws SolrException;

	protected abstract void addRightsStatementComplexType(Params params, RSCT rsct);
	
	protected abstract void addRightsStatementIdentifierComplexType(Params params, RSICT rsct);
	
	protected abstract void addRightsBasis(Params params, SPA rsct);
	
	protected abstract void addOtherRightsInformationComplexType(Params params, ORICT orict);
	
	protected abstract void addRightsGrantedComplexType(Params params, RGCT rgct);
	
	protected abstract void addLinkingObjectIdentifierComplexType(Params params, LOICT loict);
	
	protected abstract void addStatuteInformationComplexType(Params params, SICT sict);
}
