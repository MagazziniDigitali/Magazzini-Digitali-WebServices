/**
 * 
 */
package it.magazziniDigitali.xsd.rights.v2_2;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import info.lc.xmlns.premis_v2.CopyrightInformationComplexType;
import info.lc.xmlns.premis_v2.LicenseInformationComplexType;
import info.lc.xmlns.premis_v2.LinkingObjectIdentifierComplexType;
import info.lc.xmlns.premis_v2.ObjectFactory;
import info.lc.xmlns.premis_v2.OtherRightsInformationComplexType;
import info.lc.xmlns.premis_v2.RightsComplexType;
import info.lc.xmlns.premis_v2.RightsGrantedComplexType;
import info.lc.xmlns.premis_v2.RightsStatementComplexType;
import info.lc.xmlns.premis_v2.RightsStatementIdentifierComplexType;
import info.lc.xmlns.premis_v2.StartAndEndDateComplexType;
import info.lc.xmlns.premis_v2.StatuteInformationComplexType;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import it.magazziniDigitali.xsd.premis.v2_2.PremisNPM;
import it.magazziniDigitali.xsd.rights.RightsXsd;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 * 
 */
public class RightsV2_2Xsd extends RightsXsd<RightsComplexType, PremisNPM, LicenseInformationComplexType,
		LinkingObjectIdentifierComplexType, RightsStatementComplexType, RightsStatementIdentifierComplexType, 
		CopyrightInformationComplexType, StatuteInformationComplexType, RightsGrantedComplexType, OtherRightsInformationComplexType> {

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
	public RightsV2_2Xsd() {
		super();
		agent = new RightsComplexType();
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
	public RightsV2_2Xsd(java.io.File file) throws XsdException {
		super(file);
	}

	@Override
	protected void init(){
		this.version="2.2";
		schemaLocation = "info:lc/xmlns/premis-v2 https://www.loc.gov/standards/premis/v2/premis-v2-2.xsd";
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
 	public List<Object> getRightsStatementOrRightsExtension(){
		return agent.getRightsStatementOrRightsExtensionOrMdSec();
	}

	@Override
 	public String getXmlID(){
		return agent.getXmlID();
	}

	@Override
 	public void setXmlID(String value){
		agent.setXmlID(value);
	}

	@Override
	public void addRightsStatementComplexTypeAltaRisoluzione(
			String rightsStatementIdentifierValue, String linkingObjectIdentifierValue) {
		RightsStatementComplexType rsct = null;

		rsct = genRightsStatementComplexType(rightsStatementIdentifierValue);

		rsct.setRightsBasis(OTHER);

		rsct.setOtherRightsInformation(genOtherRightsInformation("Copia di archivio (Alta risoluzione in caso di digitalizzazioni)"));
		
		rsct.getRightsGranted().add(genRightsGranted(REPLICATE, null));
		rsct.getRightsGranted().add(genRightsGranted(MIGRATE, null));
		rsct.getRightsGranted().add(genRightsGranted(USE, null));

		if (linkingObjectIdentifierValue != null) {
			rsct.getLinkingObjectIdentifier().add(genLinkingObjectIdentifierComplexType(linkingObjectIdentifierValue));
		}

		agent.getRightsStatementOrRightsExtensionOrMdSec().add(rsct);
	}

	@Override
	public void addRightsStatementComplexTypeAccessoAperto(
			String rightsStatementIdentifierValue) {
		RightsStatementComplexType rsct = null;

		rsct = genRightsStatementComplexType(rightsStatementIdentifierValue);

		rsct.setRightsBasis(STATUTE);

		rsct.getStatuteInformation().add(genStatuteInformation("it", "L. 106/2004; dpr 252/2006 art 38 c 1"));

		rsct.getRightsGranted().add(genRightsGranted(REPLICATE, null));
		rsct.getRightsGranted().add(genRightsGranted(MIGRATE, null));
		rsct.getRightsGranted().add(genRightsGranted(USE, null));
		rsct.getRightsGranted().add(genRightsGranted(DISSEMINATE, null));

		agent.getRightsStatementOrRightsExtensionOrMdSec().add(rsct);
	}

	@Override
	public void addRightsStatementComplexTypeProtettoLicenza(
			String rightsStatementIdentifierValue, 
			String linkingObjectIdentifierValue) {
		RightsStatementComplexType rsct = null;

		rsct = genRightsStatementComplexType(rightsStatementIdentifierValue);

		rsct.setRightsBasis(STATUTE);

		rsct.getStatuteInformation().add(genStatuteInformation("it", "L. 106/2004; dpr 252/2006 art 38 c 2"));

		rsct.getRightsGranted().add(genRightsGranted(REPLICATE, null));
		rsct.getRightsGranted().add(genRightsGranted(MIGRATE, null));
		rsct.getRightsGranted().add(genRightsGranted(USE, null));
		rsct.getRightsGranted().add(genRightsGranted(DISSEMINATE, "IP Nazionali Centrali e Biblioteche Regionali Depositarie"));
		
		if (linkingObjectIdentifierValue != null) {
			rsct.getLinkingObjectIdentifier().add(genLinkingObjectIdentifierComplexType(linkingObjectIdentifierValue));
		}

		agent.getRightsStatementOrRightsExtensionOrMdSec().add(rsct);
	}

	@Override
	protected StatuteInformationComplexType genStatuteInformation(String statuteJurisdiction, String statuteCitation) {
		StatuteInformationComplexType sict = null;

		sict = new StatuteInformationComplexType();

		sict.setStatuteJurisdiction(statuteJurisdiction);

		sict.setStatuteCitation(statuteCitation);
		return sict;
	}

	@Override
	protected RightsGrantedComplexType genRightsGranted(String act, String restriction) {
		RightsGrantedComplexType rgct = null;
		
		rgct = new RightsGrantedComplexType();
		rgct.setAct(act);

		if (restriction != null){
			rgct.getRestriction().add(restriction);
		}

		return rgct;
	}

	@Override
	protected OtherRightsInformationComplexType genOtherRightsInformation(String otherRightsBasis) {
		OtherRightsInformationComplexType orict = null;
		
		orict = new OtherRightsInformationComplexType();
		
		orict.setOtherRightsBasis(otherRightsBasis);
		return orict;
	}

	@Override
	protected LicenseInformationComplexType genLicenseInformationComplexType(String licenseNote,
			GregorianCalendar licenseDateStart, GregorianCalendar licenseDateEnd){
		LicenseInformationComplexType lict = null;
		ObjectFactory of = null;
		StartAndEndDateComplexType saedct = null;
		DecimalFormat df4 = new DecimalFormat("0000");
		DecimalFormat df2 = new DecimalFormat("00");
		
		if (licenseNote != null){
			of = new ObjectFactory();
			lict = new LicenseInformationComplexType();
			lict.getContent().add(of.createLicenseNote(licenseNote));
		} else if (licenseDateStart != null){
			of = new ObjectFactory();
			lict = new LicenseInformationComplexType();
			saedct = new StartAndEndDateComplexType();
			saedct.setStartDate(df4.format(licenseDateStart.get(Calendar.YEAR))+
					df2.format(licenseDateStart.get(Calendar.MONTH)+1)+
					df2.format(licenseDateStart.get(Calendar.DAY_OF_MONTH)));
			if (licenseDateEnd != null){
				saedct.setEndDate(df4.format(licenseDateEnd.get(Calendar.YEAR))+
						df2.format(licenseDateEnd.get(Calendar.MONTH)+1)+
						df2.format(licenseDateEnd.get(Calendar.DAY_OF_MONTH)));
			}
			lict.getContent().add(of.createLicenseApplicableDates(saedct));

		}
		return lict;
	}

	@Override
	protected LinkingObjectIdentifierComplexType genLinkingObjectIdentifierComplexType(String linkingObjectIdentifierValue){
		LinkingObjectIdentifierComplexType loict = null;
		
		loict = new  LinkingObjectIdentifierComplexType();
		loict.setLinkingObjectIdentifierType(PremisXsd.UUID_MD);
		loict.setLinkingObjectIdentifierValue(linkingObjectIdentifierValue);
		return loict;
	}

	@Override
	protected RightsStatementComplexType genRightsStatementComplexType(String rightsStatementIdentifierValue){
		RightsStatementComplexType rsct = null;
		
		rsct = new RightsStatementComplexType();
		
		rsct.setRightsStatementIdentifier(
				genRightsStatementIdentifierComplexType(rightsStatementIdentifierValue));
		return rsct;
	}

	@Override
	protected RightsStatementIdentifierComplexType genRightsStatementIdentifierComplexType(String rightsStatementIdentifierValue){
		RightsStatementIdentifierComplexType rightsStatementIdentifierComplexType = null;
		
		rightsStatementIdentifierComplexType = new RightsStatementIdentifierComplexType();
		rightsStatementIdentifierComplexType.setRightsStatementIdentifierType(PremisXsd.UUID_MD_RG);
		rightsStatementIdentifierComplexType.setRightsStatementIdentifierValue(rightsStatementIdentifierValue);
		return rightsStatementIdentifierComplexType;
	}

	@Override
	protected CopyrightInformationComplexType genCopyrightInformationComplexType(String copyrightStatus, 
			String copyrightJurisdiction, GregorianCalendar copyrightDateStart, 
			GregorianCalendar copyrightDateEnd){
		CopyrightInformationComplexType cict = null;
		StartAndEndDateComplexType saedct = null;
		DecimalFormat df4 = new DecimalFormat("0000");
		DecimalFormat df2 = new DecimalFormat("00");
		
		cict = new CopyrightInformationComplexType();
		
		cict.setCopyrightStatus(copyrightStatus);
		
		cict.setCopyrightJurisdiction(copyrightJurisdiction);
		
		if (copyrightDateStart != null){
			saedct = new StartAndEndDateComplexType();
			saedct.setStartDate(df4.format(copyrightDateStart.get(Calendar.YEAR))+
					df2.format(copyrightDateStart.get(Calendar.MONTH)+1)+
					df2.format(copyrightDateStart.get(Calendar.DAY_OF_MONTH)));
			if (copyrightDateEnd != null){
				saedct.setEndDate(df4.format(copyrightDateEnd.get(Calendar.YEAR))+
						df2.format(copyrightDateEnd.get(Calendar.MONTH)+1)+
						df2.format(copyrightDateEnd.get(Calendar.DAY_OF_MONTH)));
			}
			cict.setCopyrightApplicableDates(saedct);
		}
		return cict;
	}
}
