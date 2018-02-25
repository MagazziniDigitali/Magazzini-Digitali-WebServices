/**
 * 
 */
package it.magazziniDigitali.xsd.premis.v3_0;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import gov.loc.premis.v3.AgentComplexType;
import gov.loc.premis.v3.CompositionLevelComplexType;
import gov.loc.premis.v3.ContentLocationComplexType;
import gov.loc.premis.v3.EventComplexType;
import gov.loc.premis.v3.EventDetailInformationComplexType;
import gov.loc.premis.v3.EventIdentifierComplexType;
import gov.loc.premis.v3.EventOutcomeDetailComplexType;
import gov.loc.premis.v3.EventOutcomeInformationComplexType;
import gov.loc.premis.v3.File;
import gov.loc.premis.v3.FixityComplexType;
import gov.loc.premis.v3.FormatComplexType;
import gov.loc.premis.v3.FormatDesignationComplexType;
import gov.loc.premis.v3.FormatRegistryComplexType;
import gov.loc.premis.v3.LinkingAgentIdentifierComplexType;
import gov.loc.premis.v3.LinkingObjectIdentifierComplexType;
import gov.loc.premis.v3.LinkingRightsStatementIdentifierComplexType;
import gov.loc.premis.v3.ObjectCharacteristicsComplexType;
import gov.loc.premis.v3.ObjectComplexType;
import gov.loc.premis.v3.ObjectFactory;
import gov.loc.premis.v3.ObjectIdentifierComplexType;
import gov.loc.premis.v3.OriginalNameComplexType;
import gov.loc.premis.v3.PremisComplexType;
import gov.loc.premis.v3.RelatedObjectIdentifierComplexType;
import gov.loc.premis.v3.RelationshipComplexType;
import gov.loc.premis.v3.RightsComplexType;
import gov.loc.premis.v3.SignificantPropertiesComplexType;
import gov.loc.premis.v3.StorageComplexType;
import gov.loc.premis.v3.StringPlusAuthority;
import it.bncf.magazziniDigitali.database.entity.MDRigths;
import it.bncf.magazziniDigitali.database.entity.MDSoftware;
import it.magazziniDigitali.xsd.premis.PremisDigest;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 * 
 */
public class PremisV3_0Xsd extends PremisXsd<PremisComplexType, ObjectFactory, 
	ObjectComplexType, EventComplexType, AgentComplexType, RightsComplexType,
	RelationshipComplexType, StorageComplexType, ObjectIdentifierComplexType,
	ObjectCharacteristicsComplexType, OriginalNameComplexType, PremisNPM> {

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
	public PremisV3_0Xsd() {
		super();
		premis = new PremisComplexType();
		premis.setVersion(this.version);
		objectFactory = new ObjectFactory();
	}

	/**
	 * Costrutore con il quale si esegue il caricamente delle infoermazioni
	 * relative ad un file esistente
	 * 
	 * @param file
	 *            Nome del file contracciato Premis esistente
	 * @throws XsdException
	 */
	public PremisV3_0Xsd(java.io.File file) throws XsdException {
		super(file);
	}

	@Override
	protected void init(){
		this.version="3.0";
		schemaLocation = "http://www.loc.gov/premis/v3 https://www.loc.gov/standards/premis/premis.xsd";
	}

	/**
	 * Metodo utilizzato per leggere la lista degli oggetti presenti
	 * 
	 * @return Lista oggetti presenti
	 */
	@Override
 	public List<ObjectComplexType> getObject(){
		return premis.getObject();
	}

	/**
	 * Metodo utilizzato per leggere la lista degli eventi presenti
	 * 
	 * @return Lista eventi presenti
	 */
	@Override
	public List<EventComplexType> getEvent(){
		return premis.getEvent();
	}

	/**
	 * Metodo utilizzato per leggere la lista degli agent presenti
	 * 
	 * @return Lista agent presenti
	 */
	@Override
	public List<AgentComplexType> getAgent(){
		return premis.getAgent();
	}

	/**
	 * Metodo utilizzato per leggere la lista delle rights presenti
	 * 
	 * @return Lista rights presenti
	 */
	@Override
	public List<RightsComplexType> getRights(){
		return premis.getRights();
	}

	/**
	 * Questo metodo viene utilizzato per aggiungere un oggetto
	 * 
	 * @param object Oggeto da aggiungere
	 */
	@Override
	public void addObject(ObjectComplexType object){
		premis.getObject().add(object);
	}

	/**
	 * Questo metodo viene utilizzato per comporre l'Object di Tipo File per il
	 * contenitore Principale
	 * 
	 * @param objectIdentifierValue
	 *            Identificativo Univoco dell'oggetto digitale
	 * @param fType
	 *            Identifica il Tipo di file descrittivo (Mag/ Mets)
	 * @param ext
	 *            Estensione del contenitore
	 * @param compositionLevel
	 *            Livello di composizione 0=senza compressione 1= con
	 *            compressione esempio JPEG
	 * @param digest
	 *            Valore Digest dell'oggetto calcolato con l'algoritmo SHA1
	 * @param size
	 *            Dimensioni del file
	 * @param formatDesignationValue
	 *            Formato dell'oggetto
	 * @param originalName
	 *            Nome Originale del File
	 * @param right
	 *            Identificativo Right relativo all'applicazione che ha
	 *            trasferito il File in Magazzini Digitali
	 */
	@Override
	public void addObjectFileContainer(String objectIdentifierValue,
			String fType, String ext, BigInteger compositionLevel,
			List<PremisDigest> digests, Long size, String formatDesignationValue,
			String originalName, MDRigths right, String formatVersion, String puid) {
		File file = null;
		SignificantPropertiesComplexType fileType = null;
		SignificantPropertiesComplexType actualFileName = null;
		LinkingRightsStatementIdentifierComplexType linkingRightsStatementIdentifier = null;

		file = new File();
		file.setVersion(version);

		// Assegnazione dell'identificatore univoco dell'oggetto
		file.getObjectIdentifier().add(
				addObjectIdentifier(objectIdentifierValue));

		// Assegnazione del Tipo di file
		if (fType != null) {
			fileType = new SignificantPropertiesComplexType();
			fileType.getContent().add(
					objectFactory.createSignificantPropertiesType(
							addStringPlusAuthority("FileType")));
			fileType.getContent().add(
					objectFactory.createSignificantPropertiesValue(fType));
			file.getSignificantProperties().add(fileType);

			actualFileName = new SignificantPropertiesComplexType();
			actualFileName.getContent().add(
					objectFactory
							.createSignificantPropertiesType(
									addStringPlusAuthority("ActualFileName")));
			actualFileName
					.getContent()
					.add(objectFactory
							.createSignificantPropertiesValue(objectIdentifierValue
									+ "." + ext));
			file.getSignificantProperties().add(actualFileName);
		}

		file.getObjectCharacteristics().add(
				addObjectCharacteristics(compositionLevel, digests, size,
						formatDesignationValue, formatVersion, puid));

		if (originalName != null){
			file.setOriginalName(addOriginalName(originalName));
		}

		if (right != null){
			linkingRightsStatementIdentifier = new LinkingRightsStatementIdentifierComplexType();
			linkingRightsStatementIdentifier
					.setLinkingRightsStatementIdentifierType(
							addStringPlusAuthority(UUID_MD_RG));
			linkingRightsStatementIdentifier
					.setLinkingRightsStatementIdentifierValue(right.getId());
			file.getLinkingRightsStatementIdentifier().add(
					linkingRightsStatementIdentifier);
		}
		addObject(file);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static StringPlusAuthority addStringPlusAuthority(String value){
		StringPlusAuthority stringPlusAuthority = null; 
		
		stringPlusAuthority = new StringPlusAuthority();
		stringPlusAuthority.setValue(value);
		return stringPlusAuthority;
	}

	/**
	 * Metodo utilizzato per definire gli Oggetti File
	 * 
	 * @param objectIdentifierValue
	 *            Identificativo Univoco dell'oggetto composto da <UUID><Ente
	 *            pubblicante "F"irenze/"R"oma><Data pubblicazione YYYYMMGG>
	 * @param compositionLevel
	 *            Livello di composizione 0=senza compressione 1= con
	 *            compressione esempio JPEG
	 * @param digest
	 *            Valore Digest dell'oggetto calcolato con l'algoritmo SHA1
	 * @param size
	 *            Dimensioni del file
	 * @param formatDesignationValue
	 *            Formato dell'oggetto
	 * @param originalName
	 *            Nome Originale del File
	 * @param contentLocationValue
	 *            Posizione dell'oggetto digitale all'interno del file Tar
	 */
	@Override
	public void addObjectFile(String objectIdentifierValue,
			BigInteger compositionLevel, List<PremisDigest> digests, Long size,
			String formatDesignationValue, String originalName,
			String contentLocationValue, String formatVersion, String puid,
			String relationshipSubType, String objectIdentifierMaster
			) {
		File file = null;
		 
		file = new File();
		file.setVersion(this.version);

		// Assegnazione dell'identificatore univoco dell'oggetto
		file.getObjectIdentifier().add(
				addObjectIdentifier(objectIdentifierValue));

		file.getObjectCharacteristics().add(
				addObjectCharacteristics(compositionLevel, digests, size,
						formatDesignationValue, formatVersion, puid));

		file.setOriginalName(addOriginalName(originalName));


		file.getStorage().add(addStorage("tarindex", contentLocationValue));

		file.getRelationship().add(addRelationship(relationshipSubType, objectIdentifierMaster));
		addObject(file);
	}

	@Override
	protected RelationshipComplexType addRelationship(String relationshipSubType, String objectIdentifierMaster){
		RelationshipComplexType relationShip = null;
		RelatedObjectIdentifierComplexType relatedObjectIdentification = null;
		
		relationShip = new RelationshipComplexType();
		relationShip.setRelationshipType(addStringPlusAuthority("stru"));
		relationShip.setRelationshipSubType(
				addStringPlusAuthority(relationshipSubType));

		relatedObjectIdentification = new RelatedObjectIdentifierComplexType();
		relatedObjectIdentification.
			setRelatedObjectIdentifierType(
					addStringPlusAuthority(UUID_MD));
		relatedObjectIdentification.setRelatedObjectIdentifierValue(objectIdentifierMaster);
		relationShip.getRelatedObjectIdentifier().add(relatedObjectIdentification);
		return relationShip;
	}

	@Override
	protected StorageComplexType addStorage(String type, String value){
		StorageComplexType storage = null;

		ContentLocationComplexType contentLocation = null;
		contentLocation = new ContentLocationComplexType();
		contentLocation.setContentLocationType(addStringPlusAuthority(type));
		contentLocation.setContentLocationValue(value);
		
		storage = new StorageComplexType();
		storage.getContent().add(objectFactory.createContentLocation(contentLocation));
		return storage;
	}

	@Override
	protected ObjectIdentifierComplexType addObjectIdentifier(
			String objectIdentifierValue) {
		ObjectIdentifierComplexType objectIdentifier = null;
		// Assegnazione dell'identificatore univoco dell'oggetto
		objectIdentifier = new ObjectIdentifierComplexType();
		objectIdentifier.setObjectIdentifierType(addStringPlusAuthority(UUID_MD_OBJ));
		objectIdentifier.setObjectIdentifierValue(objectIdentifierValue);

		return objectIdentifier;

	}

	@Override
	protected ObjectCharacteristicsComplexType addObjectCharacteristics(
			BigInteger compositionLevel, List<PremisDigest> digests, Long size,
			String formatDesignationValue, String formatVersion, String puid) {
		ObjectCharacteristicsComplexType objectCharacterstics = null;
		FixityComplexType fixity = null;
		FormatComplexType format = null;
		FormatDesignationComplexType formatDesignation = null;
		FormatRegistryComplexType formatRegistry = null;
		String[] st = null;

		objectCharacterstics = new ObjectCharacteristicsComplexType();
		objectCharacterstics.setCompositionLevel(addCompositionLevelComplexType(compositionLevel));

		if (digests != null){
			for (PremisDigest digest : digests) {
				fixity = new FixityComplexType();
	//			fixity.setMessageDigestAlgorithm(addStringPlusAuthority("SHA-1"));
				fixity.setMessageDigestAlgorithm(addStringPlusAuthority(digest.getAlgorithm().value()));
				fixity.setMessageDigest(digest.getValue());
				objectCharacterstics.getFixity().add(fixity);
			}
		}

		objectCharacterstics.setSize(size);

		if (formatDesignationValue != null ||
				puid != null){
	
			if (formatDesignationValue != null){
				st = formatDesignationValue.split(",");
				for (int x = 0; x < st.length; x++) {
					format = new FormatComplexType();
					formatDesignation = new FormatDesignationComplexType();
					formatDesignation.setFormatName(addStringPlusAuthority(st[x]));
					formatDesignation.setFormatVersion(formatVersion);
					format.getContent().add(
							objectFactory.createFormatDesignation(formatDesignation));
					objectCharacterstics.getFormat().add(format);
				}
			}
	
			if (puid != null){
				format = new FormatComplexType();
				formatRegistry = new FormatRegistryComplexType();
				formatRegistry.setFormatRegistryName(addStringPlusAuthority("PRONOM"));
				formatRegistry.setFormatRegistryKey(addStringPlusAuthority(puid));
				format.getContent().add(objectFactory.createFormatRegistry(formatRegistry));
				objectCharacterstics.getFormat().add(format);
			}

		}

		return objectCharacterstics;
	}

	private CompositionLevelComplexType addCompositionLevelComplexType(BigInteger value){
		CompositionLevelComplexType compositionLevelComplexType = null;
		
		compositionLevelComplexType = new CompositionLevelComplexType();
		compositionLevelComplexType.setValue(value);
		return compositionLevelComplexType;
	}

	@Override
	protected OriginalNameComplexType addOriginalName(String originalName) {
		OriginalNameComplexType origName = null;

		origName = new OriginalNameComplexType();
		origName.setValue(originalName);

		return origName;
	}

	@Override
	public void addEvent(String eventType, Object oStart, Object oStop,
			String eventDetail,
			String eventOutcome, String[] eventOutcomeDetails,
			String linkingAgentIdentifierValue, MDSoftware linkingSoftwareIdentifierValue,
			String objectIdentifierMaster) {
		EventComplexType event = null;
		EventIdentifierComplexType eventIdentifier = null;
		EventDetailInformationComplexType eventDetailInformationComplexType = null;
		EventOutcomeInformationComplexType eventOutcomeInformation = null;
		EventOutcomeDetailComplexType eventOutcomeDetail = null;
		LinkingAgentIdentifierComplexType linkingAgentIdentifier = null;
		LinkingObjectIdentifierComplexType linkingObjectIdentifier = null;
		String eventDateTime = null;
		DecimalFormat df6 = new DecimalFormat("000000");
		DecimalFormat df4 = new DecimalFormat("0000");
		DecimalFormat df2 = new DecimalFormat("00");
		GregorianCalendar start = null;
		GregorianCalendar stop = null;


		event = new EventComplexType();

		eventIdentifier = new EventIdentifierComplexType();
		eventIdentifier.setEventIdentifierType(addStringPlusAuthority(UUID_MD_EV));
		eventIdentifier.setEventIdentifierValue(UUID.randomUUID().toString()+"-EV");
		event.setEventIdentifier(eventIdentifier);

		event.setEventType(addStringPlusAuthority(eventType));

		if (oStart != null){
			if (oStart instanceof GregorianCalendar){
				start = (GregorianCalendar) oStart;
			} else if (oStart instanceof Timestamp){
				start = new GregorianCalendar();
				start.setTimeInMillis(((Timestamp)oStart).getTime());
			}
			eventDateTime = df4.format(start.get(Calendar.YEAR));
			eventDateTime += df2.format(start.get(Calendar.MONTH) + 1);
			eventDateTime += df2.format(start.get(Calendar.DAY_OF_MONTH));
			eventDateTime += "T";
			eventDateTime += df2.format(start.get(Calendar.HOUR_OF_DAY));
			eventDateTime += df2.format(start.get(Calendar.MINUTE));
			eventDateTime += df2.format(start.get(Calendar.SECOND));
			eventDateTime += "-";
			eventDateTime += df6.format(start.get(Calendar.MILLISECOND));
			if (oStop != null) {
				if (oStop instanceof GregorianCalendar){
					stop = (GregorianCalendar) oStop;
				} else if (oStop instanceof Timestamp){
					stop = new GregorianCalendar();
					stop.setTimeInMillis(((Timestamp)oStop).getTime());
				}
				eventDateTime += "/";
				eventDateTime += df4.format(stop.get(Calendar.YEAR));
				eventDateTime += df2.format(stop.get(Calendar.MONTH) + 1);
				eventDateTime += df2.format(stop.get(Calendar.DAY_OF_MONTH));
				eventDateTime += "T";
				eventDateTime += df2.format(stop.get(Calendar.HOUR_OF_DAY));
				eventDateTime += df2.format(stop.get(Calendar.MINUTE));
				eventDateTime += df2.format(stop.get(Calendar.SECOND));
				eventDateTime += "-";
				eventDateTime += df6.format(stop.get(Calendar.MILLISECOND));
			}
			event.setEventDateTime(eventDateTime);
		}
		
		if (eventDetail != null){
			eventDetailInformationComplexType = new EventDetailInformationComplexType();
			eventDetailInformationComplexType.setEventDetail(eventDetail);
			event.getEventDetailInformation().add(eventDetailInformationComplexType);
		}

		eventOutcomeInformation = new EventOutcomeInformationComplexType();
		eventOutcomeInformation.getContent().add(
				objectFactory.createEventOutcome(addStringPlusAuthority(eventOutcome)));

		if (eventOutcomeDetails != null && eventOutcomeDetails.length > 0) {
			for (int x = 0; x < eventOutcomeDetails.length; x++) {
				eventOutcomeDetail = new EventOutcomeDetailComplexType();
				
				eventOutcomeDetail.getContent().add(
						objectFactory.createEventOutcomeDetailNote(eventOutcomeDetails[x]));
				eventOutcomeInformation.getContent().add(
						objectFactory
								.createEventOutcomeDetail(eventOutcomeDetail));
			}
		}
		event.getEventOutcomeInformation().add(eventOutcomeInformation);

		if (linkingAgentIdentifierValue != null){
			linkingAgentIdentifier = new LinkingAgentIdentifierComplexType();
			linkingAgentIdentifier.setLinkingAgentIdentifierType(addStringPlusAuthority(UUID_MD_AG));
			linkingAgentIdentifier
					.setLinkingAgentIdentifierValue(linkingAgentIdentifierValue);
			linkingAgentIdentifier.getLinkingAgentRole().add(addStringPlusAuthority(DEPOSITANTE));
			event.getLinkingAgentIdentifier().add(linkingAgentIdentifier);
		}

		linkingAgentIdentifier = new LinkingAgentIdentifierComplexType();
		linkingAgentIdentifier.setLinkingAgentIdentifierType(addStringPlusAuthority(UUID_MD_AG));
		linkingAgentIdentifier
				.setLinkingAgentIdentifierValue(linkingSoftwareIdentifierValue.getId());
		linkingAgentIdentifier.getLinkingAgentRole().add(addStringPlusAuthority(SOFTWARE));
		event.getLinkingAgentIdentifier().add(linkingAgentIdentifier);

		linkingObjectIdentifier = new LinkingObjectIdentifierComplexType();
		linkingObjectIdentifier.setLinkingObjectIdentifierType(addStringPlusAuthority(UUID_MD));
		linkingObjectIdentifier.setLinkingObjectIdentifierValue(objectIdentifierMaster);
		event.getLinkingObjectIdentifier().add(linkingObjectIdentifier);
		premis.getEvent().add(event);
	}

	/**
	 * Metodo utilizzato per estrare il nome del
	 * 
	 * @return
	 */
	@Override
	public String getFileName() {
		String fileName = null;
		if (premis.getObject() != null && premis.getObject().size() > 0) {
			if (((File) premis.getObject().get(0)).getObjectIdentifier() != null
					&& ((File) premis.getObject().get(0)).getObjectIdentifier()
							.size() > 0) {
				fileName = ((File) premis.getObject().get(0))
						.getObjectIdentifier().get(0)
						.getObjectIdentifierValue();
			}
		}
		return fileName;
	}

	@Override
	public String getActualFileName(){
		String fileName = null;
		File file;
		SignificantPropertiesComplexType spct;
		String key;
		String value;
		if (premis.getObject() != null && premis.getObject().size() > 0){
			for (int x=0; x<premis.getObject().size(); x++){
				if (premis.getObject().get(x) instanceof File){
					file = (File) premis.getObject().get(x);
					if (file.getSignificantProperties() != null &&
							file.getSignificantProperties().size()>0){
						for (int y=0; y<file.getSignificantProperties().size(); y++){
							spct = file.getSignificantProperties().get(y);
							if (spct.getContent() != null && spct.getContent().size()>0){
								key = ((StringPlusAuthority) spct.getContent().get(0).getValue()).getValue();
								value = (String) spct.getContent().get(1).getValue();
								if (key.equals("ActualFileName")){
									fileName = value;
									break;
								}
							}
						}
					}
				}
				if (fileName != null){
					break;
				}
			}
		}
		return fileName;
	}

	@Override
	protected PremisNPM getNamespacePrefixMapper() {
		return new PremisNPM();
	}

	@Override
	protected String findObjectIdentifierContainer(ObjectComplexType oct) {
		File file = null;
		SignificantPropertiesComplexType significantProprties = null;
		String key = null;
		String objectIdentifierContainer = null;
		if (oct instanceof File) {
			file = (File) oct;
			if (file.getSignificantProperties() != null) {
				for (int y = 0; y < file.getSignificantProperties()
						.size(); y++) {
					significantProprties = file
							.getSignificantProperties().get(y);
					if (significantProprties.getContent() != null) {
						for (int z = 0; z < significantProprties
								.getContent().size(); z++) {
							if (significantProprties.getContent().get(z).getValue() instanceof StringPlusAuthority){
								key = ((StringPlusAuthority) significantProprties
										.getContent().get(z).getValue()).getValue();
								if (key.equals("ActualFileName")) {
									if (file.getObjectIdentifier() != null) {
										for (int a = 0; a < file
												.getObjectIdentifier()
												.size(); a++) {
											if (((StringPlusAuthority)file.getObjectIdentifier()
													.get(a)
													.getObjectIdentifierType()).getValue()
													.equals("UUID-MD-OBJ")) {
												objectIdentifierContainer = file
														.getObjectIdentifier()
														.get(a)
														.getObjectIdentifierValue();
												break;
											}
										}
										if (objectIdentifierContainer != null) {
											break;
										}
									}
								}
							}
						}
						if (objectIdentifierContainer != null) {
							break;
						}
					}
				}
			}
		}
		return objectIdentifierContainer;
	}
}
