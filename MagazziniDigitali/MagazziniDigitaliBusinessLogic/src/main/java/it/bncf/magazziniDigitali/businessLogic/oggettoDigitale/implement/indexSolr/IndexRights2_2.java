/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr;

import info.lc.xmlns.premis_v2.LinkingObjectIdentifierComplexType;
import info.lc.xmlns.premis_v2.OtherRightsInformationComplexType;
import info.lc.xmlns.premis_v2.RightsGrantedComplexType;
import info.lc.xmlns.premis_v2.RightsStatementComplexType;
import info.lc.xmlns.premis_v2.RightsStatementIdentifierComplexType;
import info.lc.xmlns.premis_v2.StatuteInformationComplexType;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.Params;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.bncf.magazziniDigitali.solr.ItemMD;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import it.magazziniDigitali.xsd.rights.v2_2.RightsV2_2Xsd;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public class IndexRights2_2 extends IndexRights<RightsV2_2Xsd, 
	RightsStatementComplexType, RightsStatementIdentifierComplexType, 
	String, OtherRightsInformationComplexType,
	RightsGrantedComplexType, LinkingObjectIdentifierComplexType,
	StatuteInformationComplexType> {

	/**
	 * 
	 */
	public IndexRights2_2(String name) {
		super(name);
	}

	@Override
	public void indexSolr(RightsV2_2Xsd rightsXsd, AddDocumentMD admd) throws SolrException {
		Params params = new Params();
		
		try {
			params.add(ItemMD.TIPOOGGETTO, ItemMD.TIPOOGGETTO_DIRITTI);
			
			for(Object rsore: rightsXsd.getRightsStatementOrRightsExtension()){
				if (rsore instanceof RightsStatementComplexType){
					addRightsStatementComplexType(params, (RightsStatementComplexType)rsore);
				}
			}
			admd.add(params.getParams(), new ItemMD());
		} catch (SolrException e) {
			throw e;
		}
	}

	protected void addRightsStatementComplexType(Params params, RightsStatementComplexType rsct){
		
		addRightsStatementIdentifierComplexType(params, rsct.getRightsStatementIdentifier());
		addRightsBasis(params,rsct.getRightsBasis());
		addOtherRightsInformationComplexType(params, rsct.getOtherRightsInformation());

		for (StatuteInformationComplexType sict: rsct.getStatuteInformation()){
			addStatuteInformationComplexType(params, sict);
		}
		
		for (RightsGrantedComplexType rgct: rsct.getRightsGranted()){
			addRightsGrantedComplexType(params, rgct);
		}
		
		for (LinkingObjectIdentifierComplexType loict: rsct.getLinkingObjectIdentifier()){
			addLinkingObjectIdentifierComplexType(params, loict);
		}
	}

	protected void addRightsStatementIdentifierComplexType(Params params, RightsStatementIdentifierComplexType rsct){
		if (rsct != null){
			if (rsct.getRightsStatementIdentifierType() != null &&
					!rsct.getRightsStatementIdentifierType().trim().equals("")){
				if (rsct.getRightsStatementIdentifierType().equals(PremisXsd.UUID_MD_RG)){
					params.add(ItemMD.RIGHTSIDENTIFIER, rsct.getRightsStatementIdentifierValue());
				}
			}
		}
	}

	protected void addRightsBasis(Params params, String rsct){
		if (rsct != null &&
				!rsct.trim().equals("")){
			params.add(ItemMD.RIGHTSBASIS, rsct);
		}
	}

	protected void addOtherRightsInformationComplexType(Params params, OtherRightsInformationComplexType orict){
		if (orict != null &&
				orict.getOtherRightsBasis() != null &&
				!orict.getOtherRightsBasis().trim().equals("")){
			params.add(ItemMD.RIGHTSINFORMATIONBASIS, orict.getOtherRightsBasis());
		}
	}

	protected void addRightsGrantedComplexType(Params params, RightsGrantedComplexType rgct) {

		params.add(ItemMD.RIGHTSACT, rgct.getAct());

		if (rgct.getRestriction() != null){
			for(String restriction: rgct.getRestriction()){
				params.add(ItemMD.RIGHTSRESTRICTION, restriction);
			}
		}
		
	}

	protected void addLinkingObjectIdentifierComplexType(Params params, LinkingObjectIdentifierComplexType loict) {
		if (loict.getLinkingObjectIdentifierType() != null &&
				!loict.getLinkingObjectIdentifierType().trim().equals("")){
			if (loict.getLinkingObjectIdentifierType().equals(PremisXsd.UUID_MD)){
				if (loict.getLinkingObjectIdentifierValue() != null &&
						!loict.getLinkingObjectIdentifierValue().trim().equals("")){
					params.add(ItemMD.RIGHTOBJECTIDENTIFIER, loict.getLinkingObjectIdentifierValue());
				}
			}
		}
	}

	protected void addStatuteInformationComplexType(Params params, StatuteInformationComplexType sict) {
		params.add(ItemMD.RIGHTSSTATUTEJURISDICTION, sict.getStatuteJurisdiction());
		params.add(ItemMD.RIGHTSSTATUTECITATION, sict.getStatuteCitation());
	}

}
