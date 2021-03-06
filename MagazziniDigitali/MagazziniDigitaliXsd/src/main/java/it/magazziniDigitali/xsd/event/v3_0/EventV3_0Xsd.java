/**
 * 
 */
package it.magazziniDigitali.xsd.event.v3_0;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import gov.loc.premis.v3.EventComplexType;
import gov.loc.premis.v3.EventDetailInformationComplexType;
import gov.loc.premis.v3.EventIdentifierComplexType;
import gov.loc.premis.v3.ExtensionComplexType;
import gov.loc.premis.v3.LinkingAgentIdentifierComplexType;
import gov.loc.premis.v3.LinkingObjectIdentifierComplexType;
import gov.loc.premis.v3.ObjectFactory;
import gov.loc.premis.v3.StringPlusAuthority;
import it.depositolegale.ticket.Ticket;
import it.magazziniDigitali.xsd.event.EventXsd;
import it.magazziniDigitali.xsd.premis.v3_0.PremisNPM;
import it.magazziniDigitali.xsd.premis.v3_0.PremisV3_0Xsd;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 * 
 */
public class EventV3_0Xsd extends EventXsd<EventComplexType, PremisNPM,
		EventIdentifierComplexType, StringPlusAuthority, LinkingObjectIdentifierComplexType> {

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
	public EventV3_0Xsd() {
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
	public EventV3_0Xsd(java.io.File file) throws XsdException {
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
				PremisV3_0Xsd.addStringPlusAuthority(eventIdentifierType));
		eict.setEventIdentifierValue(eventIdentifierValue);
		event.setEventIdentifier(eict);
	}

	public StringPlusAuthority getEventType(){
		return event.getEventType();
	}
	
	public void setEventType(String value){
		event.setEventType(PremisV3_0Xsd.addStringPlusAuthority(value));
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
		loict.setLinkingObjectIdentifierType(PremisV3_0Xsd.addStringPlusAuthority(linkingObjectIdentifierType));
		loict.setLinkingObjectIdentifierValue(linkingObjectIdentifierValue);
		event.getLinkingObjectIdentifier().add(loict);
	}

	@Override
	public void addLinkingAgentIdentifier(String linkingAgentIdentifierType, 
			String linkingAgentIdentifierValue, String linkingAgentRole) {
		LinkingAgentIdentifierComplexType laict = null;
		
		laict = new LinkingAgentIdentifierComplexType();
		laict.setLinkingAgentIdentifierType(PremisV3_0Xsd.addStringPlusAuthority(linkingAgentIdentifierType));
		laict
				.setLinkingAgentIdentifierValue(linkingAgentIdentifierValue);
		laict.getLinkingAgentRole().add(PremisV3_0Xsd.addStringPlusAuthority(linkingAgentRole));

		event.getLinkingAgentIdentifier().add(laict);
	}

	@Override
	public void addEventDetailInformationExtension(Serializable extensionComplexType) {
		ObjectFactory of = null;
		EventDetailInformationComplexType cedi = null;
		ExtensionComplexType ect = null;
		
		of = new ObjectFactory();
		cedi = of.createEventDetailInformationComplexType();
		ect = of.createExtensionComplexType();
		ect.getAny().add(extensionComplexType);
		cedi.getEventDetailExtension().add(ect);
		event.getEventDetailInformation().add(cedi);
	}

	/* (non-Javadoc)
	 * @see mx.randalf.xsd.WriteXsd#initJAXBContext()
	 */
	@Override
	protected JAXBContext initJAXBContext() throws JAXBException {
		JAXBContext jc = null;

		jc = JAXBContext.newInstance(EventComplexType.class, Ticket.class);

		return jc;
	}
}
