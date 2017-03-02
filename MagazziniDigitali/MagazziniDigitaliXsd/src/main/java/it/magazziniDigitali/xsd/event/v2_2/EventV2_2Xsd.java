/**
 * 
 */
package it.magazziniDigitali.xsd.event.v2_2;

import java.io.Serializable;
import java.util.List;

import info.lc.xmlns.premis_v2.EventComplexType;
import info.lc.xmlns.premis_v2.EventIdentifierComplexType;
import info.lc.xmlns.premis_v2.LinkingAgentIdentifierComplexType;
import info.lc.xmlns.premis_v2.LinkingObjectIdentifierComplexType;
import it.magazziniDigitali.xsd.event.EventXsd;
import it.magazziniDigitali.xsd.premis.v2_2.PremisNPM;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 * 
 */
public class EventV2_2Xsd extends EventXsd<EventComplexType, PremisNPM,
		EventIdentifierComplexType, String, LinkingObjectIdentifierComplexType> {

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
	public EventV2_2Xsd() {
		super();
		event = new EventComplexType();
		event.setVersion(this.version);
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
	public EventV2_2Xsd(java.io.File file) throws XsdException {
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
 	public EventIdentifierComplexType getEventIdentifier(){
		return event.getEventIdentifier();
	}

	@Override
	public void setEventIdentifier(String eventIdentifierType, 
			String eventIdentifierValue) {

		EventIdentifierComplexType eict = null;
		
		eict = new EventIdentifierComplexType();
		eict.setEventIdentifierType(
				eventIdentifierType);
		eict.setEventIdentifierValue(eventIdentifierValue);
		event.setEventIdentifier(eict);
	}

	public String getEventType(){
		return event.getEventType();
	}
	
	public void setEventType(String value){
		event.setEventType(value);
	}
	
	public String getEventDateTime(){
		return event.getEventDateTime();
	}
	
	public void setEventDateTime(String value){
		event.setEventDateTime(value);
	}

	public List<LinkingObjectIdentifierComplexType> getLinkingObjectIdentifier(){
		return event.getLinkingObjectIdentifier();
	}
	
	public void addLinkingObjectIdentifier(String linkingObjectIdentifierType, 
			String linkingObjectIdentifierValue){
		LinkingObjectIdentifierComplexType loict = null;
		
		loict = new LinkingObjectIdentifierComplexType();
		loict.setLinkingObjectIdentifierType(linkingObjectIdentifierType);
		loict.setLinkingObjectIdentifierValue(linkingObjectIdentifierValue);
		event.getLinkingObjectIdentifier().add(loict);
	}

	@Override
	public void addLinkingAgentIdentifier(String linkingAgentIdentifierType, 
			String linkingAgentIdentifierValue, String linkingAgentRole) {
		LinkingAgentIdentifierComplexType laict = null;
		
		laict = new LinkingAgentIdentifierComplexType();
		laict.setLinkingAgentIdentifierType(linkingAgentIdentifierType);
		laict
				.setLinkingAgentIdentifierValue(linkingAgentIdentifierValue);
		laict.getLinkingAgentRole().add(linkingAgentRole);

		event.getLinkingAgentIdentifier().add(laict);
	}

	@Override
	public void addEventDetailInformationExtension(Serializable extensionComplexType) {
	}
}
