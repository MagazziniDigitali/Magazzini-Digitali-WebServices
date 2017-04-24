/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.indexSolr.rights;

import gov.loc.premis.v3.LinkingObjectIdentifierComplexType;
import gov.loc.premis.v3.OtherRightsInformationComplexType;
import gov.loc.premis.v3.RightsGrantedComplexType;
import gov.loc.premis.v3.RightsStatementComplexType;
import gov.loc.premis.v3.RightsStatementIdentifierComplexType;
import gov.loc.premis.v3.StatuteInformationComplexType;
import gov.loc.premis.v3.StringPlusAuthority;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr.param.Params;
import it.bncf.magazziniDigitali.solr.AddDocumentMD;
import it.bncf.magazziniDigitali.solr.ItemMD;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import it.magazziniDigitali.xsd.rights.v3_0.RightsV3_0Xsd;
import mx.randalf.solr.exception.SolrException;

/**
 * @author massi
 *
 */
public class IndexRights3_0 extends IndexRights<RightsV3_0Xsd, 
	RightsStatementComplexType, RightsStatementIdentifierComplexType, 
	StringPlusAuthority, OtherRightsInformationComplexType,
	RightsGrantedComplexType, LinkingObjectIdentifierComplexType,
	StatuteInformationComplexType> {
	
	/**
	 * 
	 */
	public IndexRights3_0(String name) {
		super(name);
	}

	@Override
	public void indexSolr(RightsV3_0Xsd rightsXsd, AddDocumentMD admd) throws SolrException {
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
					rsct.getRightsStatementIdentifierType().getValue() != null &&
					!rsct.getRightsStatementIdentifierType().getValue().trim().equals("")){
				if (rsct.getRightsStatementIdentifierType().getValue().equals(PremisXsd.UUID_MD_RG)){
					params.add(ItemMD.ID, rsct.getRightsStatementIdentifierValue());
					params.add(ItemMD.RIGHTSIDENTIFIER, rsct.getRightsStatementIdentifierValue());
				}
			}
		}
	}

	protected void addRightsBasis(Params params, StringPlusAuthority rsct){
		if (rsct != null &&
				rsct.getValue() != null &&
				!rsct.getValue().trim().equals("")){
			params.add(ItemMD.RIGHTSBASIS, rsct.getValue());
		}
	}

	protected void addOtherRightsInformationComplexType(Params params, OtherRightsInformationComplexType orict){
		if (orict != null &&
				orict.getOtherRightsBasis() != null &&
				orict.getOtherRightsBasis().getValue() != null &&
				!orict.getOtherRightsBasis().getValue().trim().equals("")){
			params.add(ItemMD.RIGHTSINFORMATIONBASIS, orict.getOtherRightsBasis().getValue());
		}
	}

	protected void addRightsGrantedComplexType(Params params, RightsGrantedComplexType rgct) {

		params.add(ItemMD.RIGHTSACT, rgct.getAct().getValue());

		if (rgct.getRestriction() != null){
			for(StringPlusAuthority restriction: rgct.getRestriction()){
				params.add(ItemMD.RIGHTSRESTRICTION, restriction.getValue());
			}
		}
		
	}

	protected void addLinkingObjectIdentifierComplexType(Params params, LinkingObjectIdentifierComplexType loict) {
		if (loict.getLinkingObjectIdentifierType() != null &&
				loict.getLinkingObjectIdentifierType().getValue() != null &&
				!loict.getLinkingObjectIdentifierType().getValue().trim().equals("")){
			if (loict.getLinkingObjectIdentifierType().getValue().equals(PremisXsd.UUID_MD)){
				if (loict.getLinkingObjectIdentifierValue() != null &&
						!loict.getLinkingObjectIdentifierValue().trim().equals("")){
					params.add(ItemMD.RIGHTOBJECTIDENTIFIER, loict.getLinkingObjectIdentifierValue());
				}
			}
		}
	}

	protected void addStatuteInformationComplexType(Params params, StatuteInformationComplexType sict) {
		params.add(ItemMD.RIGHTSSTATUTEJURISDICTION, sict.getStatuteJurisdiction().getValue());
		params.add(ItemMD.RIGHTSSTATUTECITATION, sict.getStatuteCitation().getValue());
	}

}
