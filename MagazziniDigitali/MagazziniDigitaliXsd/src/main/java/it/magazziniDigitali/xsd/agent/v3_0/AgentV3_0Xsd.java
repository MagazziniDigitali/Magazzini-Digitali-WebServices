/**
 * 
 */
package it.magazziniDigitali.xsd.agent.v3_0;

import java.io.Serializable;
import java.util.List;

import gov.loc.premis.v3.AgentComplexType;
import gov.loc.premis.v3.AgentIdentifierComplexType;
import gov.loc.premis.v3.ExtensionComplexType;
import gov.loc.premis.v3.LinkingAgentIdentifierComplexType;
import gov.loc.premis.v3.LinkingRightsStatementIdentifierComplexType;
import gov.loc.premis.v3.ObjectFactory;
import gov.loc.premis.v3.StringPlusAuthority;
import it.magazziniDigitali.xsd.agent.AgentXsd;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import it.magazziniDigitali.xsd.premis.v3_0.PremisNPM;
import it.magazziniDigitali.xsd.premis.v3_0.PremisV3_0Xsd;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 * 
 */
public class AgentV3_0Xsd extends AgentXsd<AgentComplexType, PremisNPM,
		AgentIdentifierComplexType, StringPlusAuthority, ExtensionComplexType,
		LinkingRightsStatementIdentifierComplexType> {

	/**
	 * Costruttore utilizzato per l'inizializzazione di un nuovo tracciato
	 * Premis
	 * 
	 * @param agentIdentifierValue
	 *            Identificativo UUID relativo all'istituto proprietario
	 *            dell'oggetto
	 * @param rightsStatementIdentifierValue
	 *            Identificativo UUID relavito al livello di autorizzazione
	 *            associato all'oggetto
	 * @param rightsBasis
	 *            Designazione di base per il diritto o permesso descritto nella
	 *            rightsStatementIdentifier.
	 */
	public AgentV3_0Xsd() {
		super();
		agent = new AgentComplexType();
		agent.setVersion(this.version);
//		objectFactory = new ObjectFactory();
	}

	/**
	 * Costrutore con il quale si esegue il caricamente delle infoermazioni
	 * relative ad un file esistente
	 * 
	 * @param file
	 *            Nome del file contracciato Premis esistente
	 * @throws XsdException
	 */
	public AgentV3_0Xsd(java.io.File file) throws XsdException {
		super(file);
	}

	@Override
	protected void init(){
		this.version="3.0";
		schemaLocation = "http://www.loc.gov/premis/v3 https://www.loc.gov/standards/premis/premis.xsd";
	}

	@Override
	protected PremisNPM getNamespacePrefixMapper() {
		return new PremisNPM();
	}

	/**
	 * Metodo utilizzato per leggere la lista degli oggetti presenti
	 * 
	 * @return Lista oggetti presenti
	 */
	@Override
 	public List<AgentIdentifierComplexType> getAgentIdentifier(){
		return agent.getAgentIdentifier();
	}

	@Override
	public void addAgentIdentifier(String agentIdentifierType, String agentIdentifierValue) {

		AgentIdentifierComplexType aict = null;
		
		aict = new AgentIdentifierComplexType();
		aict.setAgentIdentifierType(PremisV3_0Xsd.addStringPlusAuthority(agentIdentifierType));
		aict.setAgentIdentifierValue(agentIdentifierValue);
		agent.getAgentIdentifier().add(aict);
	}

	public List<StringPlusAuthority> getAgentName(){
		return agent.getAgentName();
	}
	
	public void addAgentName(String value){
		agent.getAgentName().add(PremisV3_0Xsd.addStringPlusAuthority(value));
	}
	
	public StringPlusAuthority getAgentType(){
		return agent.getAgentType();
	}
	
	public void setAgentType(String value){
		agent.setAgentType(PremisV3_0Xsd.addStringPlusAuthority(value));
	}

	public List<String> getAgentNote(){
		return agent.getAgentNote();
	}
	
	public void addAgentNote(String value){
		agent.getAgentNote().add(value);
	}

	public List<ExtensionComplexType> getAgentExtension(){
		return agent.getAgentExtension();
	}

	public void addAgentExtensionDepositante(String value){
		ObjectFactory of = null;
		LinkingAgentIdentifierComplexType linkingAgentIdentifierComplexType = null;

		of = new ObjectFactory();
		linkingAgentIdentifierComplexType = of.createLinkingAgentIdentifierComplexType();
		linkingAgentIdentifierComplexType.setLinkingAgentIdentifierType(PremisV3_0Xsd.addStringPlusAuthority(PremisXsd.UUID_MD_AG));
		linkingAgentIdentifierComplexType.setLinkingAgentIdentifierValue(value);
		addAgentExtension(of.createLinkingAgentIdentifier(linkingAgentIdentifierComplexType));

	}

	protected void addAgentExtension(Serializable value){
		ObjectFactory of = null;
		
		ExtensionComplexType ect = null;
		
		of = new ObjectFactory();
		ect = of.createExtensionComplexType();
		ect.getAny().add(value);
		
		agent.getAgentExtension().add(of.createAgentExtension(ect).getValue());
	}

	public List<LinkingRightsStatementIdentifierComplexType> getLinkingRightsStatementIdentifier(){
		return agent.getLinkingRightsStatementIdentifier();
	}

	public void addLinkingRightsStatementIdentifier(String value){
		LinkingRightsStatementIdentifierComplexType lrsict = null;
		
		lrsict = new LinkingRightsStatementIdentifierComplexType();
		lrsict.setLinkingRightsStatementIdentifierType(PremisV3_0Xsd.addStringPlusAuthority(PremisXsd.UUID_MD_RG));
		lrsict.setLinkingRightsStatementIdentifierValue(value);
		
		agent.getLinkingRightsStatementIdentifier().add(lrsict);
	}
}
