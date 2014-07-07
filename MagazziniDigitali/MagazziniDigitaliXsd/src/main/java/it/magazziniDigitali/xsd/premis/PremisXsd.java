/**
 * 
 */
package it.magazziniDigitali.xsd.premis;

import info.lc.xmlns.premis_v2.AgentComplexType;
import info.lc.xmlns.premis_v2.ContentLocationComplexType;
import info.lc.xmlns.premis_v2.EventComplexType;
import info.lc.xmlns.premis_v2.EventIdentifierComplexType;
import info.lc.xmlns.premis_v2.EventOutcomeDetailComplexType;
import info.lc.xmlns.premis_v2.EventOutcomeInformationComplexType;
import info.lc.xmlns.premis_v2.File;
import info.lc.xmlns.premis_v2.FixityComplexType;
import info.lc.xmlns.premis_v2.FormatComplexType;
import info.lc.xmlns.premis_v2.FormatDesignationComplexType;
import info.lc.xmlns.premis_v2.FormatRegistryComplexType;
import info.lc.xmlns.premis_v2.LinkingAgentIdentifierComplexType;
import info.lc.xmlns.premis_v2.LinkingObjectIdentifierComplexType;
import info.lc.xmlns.premis_v2.LinkingRightsStatementIdentifierComplexType;
import info.lc.xmlns.premis_v2.ObjectCharacteristicsComplexType;
import info.lc.xmlns.premis_v2.ObjectComplexType;
import info.lc.xmlns.premis_v2.ObjectFactory;
import info.lc.xmlns.premis_v2.ObjectIdentifierComplexType;
import info.lc.xmlns.premis_v2.OriginalNameComplexType;
import info.lc.xmlns.premis_v2.PremisComplexType;
import info.lc.xmlns.premis_v2.RelatedObjectIdentificationComplexType;
import info.lc.xmlns.premis_v2.RelationshipComplexType;
import info.lc.xmlns.premis_v2.RightsComplexType;
import info.lc.xmlns.premis_v2.SignificantPropertiesComplexType;
import info.lc.xmlns.premis_v2.StorageComplexType;
import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;

import java.io.IOException;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBElement;

import mx.randalf.xsd.ReadXsd;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 * 
 */
public class PremisXsd extends ReadXsd<PremisComplexType> {

	public static String UUID_MD_RG = "UUID-MD-RG";
	public static String UUID_MD = "UUID-MD";
	public static String UUID_MD_OBJ = "UUID-MD-OBJ";
	public static String UUID_MD_EV = "UUID-MD-EV";
	public static String UUID_MD_AG = "UUID-MD-AG";

	private PremisComplexType premis = null;

	private ObjectFactory objectFactory = new ObjectFactory();

	private java.io.File file = null;

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
	public PremisXsd(
	) {

		premis = new PremisComplexType();
		premis.setVersion("2.2");
	}

	/**
	 * Costrutore con il quale si esegue il caricamente delle infoermazioni
	 * relative ad un file esistente
	 * 
	 * @param file
	 *            Nome del file contracciato Premis esistente
	 * @throws XsdException
	 */
	@SuppressWarnings("unchecked")
	public PremisXsd(java.io.File file) throws XsdException {
		Object obj = null;
		
		obj = read(file);
		if (obj instanceof JAXBElement){
			premis = ((JAXBElement<PremisComplexType>)obj).getValue();
		} else {
			premis = (PremisComplexType) obj;
		}
		this.file = file;
	}

	/**
	 * Metodo utilizzato per leggere la lista degli oggetti presenti
	 * 
	 * @return Lista oggetti presenti
	 */
	public List<ObjectComplexType> getObject(){
		return premis.getObject();
	}

	/**
	 * Metodo utilizzato per leggere la lista degli eventi presenti
	 * 
	 * @return Lista eventi presenti
	 */
	public List<EventComplexType> getEvent(){
		return premis.getEvent();
	}

	/**
	 * Metodo utilizzato per leggere la lista degli agent presenti
	 * 
	 * @return Lista agent presenti
	 */
	public List<AgentComplexType> getAgent(){
		return premis.getAgent();
	}

	/**
	 * Metodo utilizzato per leggere la lista delle rights presenti
	 * 
	 * @return Lista rights presenti
	 */
	public List<RightsComplexType> getRights(){
		return premis.getRights();
	}

	/**
	 * Questo metodo viene utilizzato per aggiungere un oggetto
	 * 
	 * @param object Oggeto da aggiungere
	 */
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
	public void addObjectFileContainer(String objectIdentifierValue,
			String fType, String ext, BigInteger compositionLevel,
			String digest, Long size, String formatDesignationValue,
			String originalName, String right, String formatVersion, String puid) {
		File file = null;
		SignificantPropertiesComplexType fileType = null;
		SignificantPropertiesComplexType actualFileName = null;
		LinkingRightsStatementIdentifierComplexType linkingRightsStatementIdentifier = null;

		file = new File();
		file.setVersion("2.2");

		// Assegnazione dell'identificatore univoco dell'oggetto
		file.getObjectIdentifier().add(
				addObjectIdentifier(objectIdentifierValue));

		// Assegnazione del Tipo di file
		if (fType != null) {
			fileType = new SignificantPropertiesComplexType();
			fileType.getContent().add(
					objectFactory.createSignificantPropertiesType("FileType"));
			fileType.getContent().add(
					objectFactory.createSignificantPropertiesValue(fType));
			file.getSignificantProperties().add(fileType);

			actualFileName = new SignificantPropertiesComplexType();
			actualFileName.getContent().add(
					objectFactory
							.createSignificantPropertiesType("ActualFileName"));
			actualFileName
					.getContent()
					.add(objectFactory
							.createSignificantPropertiesValue(objectIdentifierValue
									+ "." + ext));
			file.getSignificantProperties().add(actualFileName);
		}

		file.getObjectCharacteristics().add(
				addObjectCharacteristics(compositionLevel, digest, size,
						formatDesignationValue, formatVersion, puid));

		file.setOriginalName(addOriginalName(originalName));

		if (right != null){
			linkingRightsStatementIdentifier = new LinkingRightsStatementIdentifierComplexType();
			linkingRightsStatementIdentifier
					.setLinkingRightsStatementIdentifierType(UUID_MD_RG);
			linkingRightsStatementIdentifier
					.setLinkingRightsStatementIdentifierValue(right);
			file.getLinkingRightsStatementIdentifier().add(
					linkingRightsStatementIdentifier);
		}
		addObject(file);
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
	public void addObjectFile(String objectIdentifierValue,
			BigInteger compositionLevel, String digest, Long size,
			String formatDesignationValue, String originalName,
			String contentLocationValue, String formatVersion, String puid,
			String relationshipSubType, String objectIdentifierMaster
			) {
		File file = null;
		 
		file = new File();
		file.setVersion("2.2");

		// Assegnazione dell'identificatore univoco dell'oggetto
		file.getObjectIdentifier().add(
				addObjectIdentifier(objectIdentifierValue));

		file.getObjectCharacteristics().add(
				addObjectCharacteristics(compositionLevel, digest, size,
						formatDesignationValue, formatVersion, puid));

		file.setOriginalName(addOriginalName(originalName));


		file.getStorage().add(addStorage("tarindex", contentLocationValue));

		file.getRelationship().add(addRelationship(relationshipSubType, objectIdentifierMaster));
		addObject(file);
	}

	private RelationshipComplexType addRelationship(String relationshipSubType, String objectIdentifierMaster){
		RelationshipComplexType relationShip = null;
		RelatedObjectIdentificationComplexType relatedObjectIdentification = null;
		
		relationShip = new RelationshipComplexType();
		relationShip.setRelationshipType("stru");
		relationShip.setRelationshipSubType(relationshipSubType);
		relatedObjectIdentification = new RelatedObjectIdentificationComplexType();
		relatedObjectIdentification.setRelatedObjectIdentifierType(UUID_MD);
		relatedObjectIdentification.setRelatedObjectIdentifierValue(objectIdentifierMaster);
		relationShip.getRelatedObjectIdentification().add(relatedObjectIdentification);
		return relationShip;
	}

	private StorageComplexType addStorage(String type, String value){
		StorageComplexType storage = null;

		ContentLocationComplexType contentLocation = null;
		contentLocation = new ContentLocationComplexType();
		contentLocation.setContentLocationType(type);
		contentLocation.setContentLocationValue(value);
		
		storage = new StorageComplexType();
		storage.getContent().add(objectFactory.createContentLocation(contentLocation));
		return storage;
	}

	private ObjectIdentifierComplexType addObjectIdentifier(
			String objectIdentifierValue) {
		ObjectIdentifierComplexType objectIdentifier = null;
		// Assegnazione dell'identificatore univoco dell'oggetto
		objectIdentifier = new ObjectIdentifierComplexType();
		objectIdentifier.setObjectIdentifierType(UUID_MD_OBJ);
		objectIdentifier.setObjectIdentifierValue(objectIdentifierValue);

		return objectIdentifier;

	}

	private ObjectCharacteristicsComplexType addObjectCharacteristics(
			BigInteger compositionLevel, String digest, Long size,
			String formatDesignationValue, String formatVersion, String puid) {
		ObjectCharacteristicsComplexType objectCharacterstics = null;
		FixityComplexType fixity = null;
		FormatComplexType format = null;
		FormatDesignationComplexType formatDesignation = null;
		FormatRegistryComplexType formatRegistry = null;
		String[] st = null;

		objectCharacterstics = new ObjectCharacteristicsComplexType();
		objectCharacterstics.setCompositionLevel(compositionLevel);

		fixity = new FixityComplexType();
		fixity.setMessageDigestAlgorithm("SHA-1");
		fixity.setMessageDigest(digest);
		objectCharacterstics.getFixity().add(fixity);

		objectCharacterstics.setSize(size);

		format = new FormatComplexType();

		if (formatDesignationValue != null){
			st = formatDesignationValue.split(",");
			for (int x = 0; x < st.length; x++) {
				formatDesignation = new FormatDesignationComplexType();
				formatDesignation.setFormatName(st[x]);
				formatDesignation.setFormatVersion(formatVersion);
				format.getContent().add(
						objectFactory.createFormatDesignation(formatDesignation));
			}
		}

		formatRegistry = new FormatRegistryComplexType();
		formatRegistry.setFormatRegistryName("PRONOM");
		formatRegistry.setFormatRegistryKey(puid);
		format.getContent().add(objectFactory.createFormatRegistry(formatRegistry));
		objectCharacterstics.getFormat().add(format);

		return objectCharacterstics;
	}

	private OriginalNameComplexType addOriginalName(String originalName) {
		OriginalNameComplexType origName = null;

		origName = new OriginalNameComplexType();
		origName.setValue(originalName);

		return origName;
	}

	public void addEvent(String eventType, GregorianCalendar start, GregorianCalendar stop,
			String eventDetail,
			String eventOutcome, String[] eventOutcomeDetails,
			String linkingAgentIdentifierValue, String linkingSoftwareIdentifierValue,
			String objectIdentifierMaster) {
		EventComplexType event = null;
		EventIdentifierComplexType eventIdentifier = null;
		EventOutcomeInformationComplexType eventOutcomeInformation = null;
		EventOutcomeDetailComplexType eventOutcomeDetail = null;
		LinkingAgentIdentifierComplexType linkingAgentIdentifier = null;
		LinkingObjectIdentifierComplexType linkingObjectIdentifier = null;
		String eventDateTime = null;
		DecimalFormat df6 = new DecimalFormat("000000");
		DecimalFormat df4 = new DecimalFormat("0000");
		DecimalFormat df2 = new DecimalFormat("00");


		event = new EventComplexType();

		eventIdentifier = new EventIdentifierComplexType();
		eventIdentifier.setEventIdentifierType(UUID_MD_EV);
		eventIdentifier.setEventIdentifierValue(UUID.randomUUID().toString()+"-EV");
		event.setEventIdentifier(eventIdentifier);

		event.setEventType(eventType);

		if (start != null){
			eventDateTime = df4.format(start.get(Calendar.YEAR));
			eventDateTime += df2.format(start.get(Calendar.MONTH) + 1);
			eventDateTime += df2.format(start.get(Calendar.DAY_OF_MONTH));
			eventDateTime += "T";
			eventDateTime += df2.format(start.get(Calendar.HOUR_OF_DAY));
			eventDateTime += df2.format(start.get(Calendar.MINUTE));
			eventDateTime += df2.format(start.get(Calendar.SECOND));
			eventDateTime += "-";
			eventDateTime += df6.format(start.get(Calendar.MILLISECOND));
			if (stop != null) {
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
			event.setEventDetail(eventDetail);
		}

		eventOutcomeInformation = new EventOutcomeInformationComplexType();
		eventOutcomeInformation.getContent().add(
				objectFactory.createEventOutcome(eventOutcome));

		if (eventOutcomeDetails != null && eventOutcomeDetails.length > 0) {
			for (int x = 0; x < eventOutcomeDetails.length; x++) {
				eventOutcomeDetail = new EventOutcomeDetailComplexType();
				eventOutcomeDetail
						.setEventOutcomeDetailNote(eventOutcomeDetails[x]);
				eventOutcomeInformation.getContent().add(
						objectFactory
								.createEventOutcomeDetail(eventOutcomeDetail));
			}
		}
		event.getEventOutcomeInformation().add(eventOutcomeInformation);

		if (linkingAgentIdentifierValue != null){
			linkingAgentIdentifier = new LinkingAgentIdentifierComplexType();
			linkingAgentIdentifier.setLinkingAgentIdentifierType(UUID_MD_AG);
			linkingAgentIdentifier
					.setLinkingAgentIdentifierValue(linkingAgentIdentifierValue);
			linkingAgentIdentifier.getLinkingAgentRole().add("depositante");
			event.getLinkingAgentIdentifier().add(linkingAgentIdentifier);
		}

		linkingAgentIdentifier = new LinkingAgentIdentifierComplexType();
		linkingAgentIdentifier.setLinkingAgentIdentifierType(UUID_MD_AG);
		linkingAgentIdentifier
				.setLinkingAgentIdentifierValue(linkingSoftwareIdentifierValue);
		linkingAgentIdentifier.getLinkingAgentRole().add("software");
		event.getLinkingAgentIdentifier().add(linkingAgentIdentifier);

		linkingObjectIdentifier = new LinkingObjectIdentifierComplexType();
		linkingObjectIdentifier.setLinkingObjectIdentifierType(UUID_MD);
		linkingObjectIdentifier.setLinkingObjectIdentifierValue(objectIdentifierMaster);
		event.getLinkingObjectIdentifier().add(linkingObjectIdentifier);
		premis.getEvent().add(event);
	}

	public void write(boolean isLocked) throws PremisXsdException,
			XsdException, IOException {
		write(file, isLocked);
	}

	public void write(java.io.File file, boolean isLocked)
			throws PremisXsdException, XsdException, IOException {
		String schemaLocation = null;
		java.io.File fLock = null;
		try {
			if (file == null) {
				throw new PremisXsdException(
						"E' necessario indicare il nome del file con cui salvare i dati");
			}
			fLock = new java.io.File(file.getAbsolutePath() + ".lock");
			schemaLocation = "info:lc/xmlns/premis-v2 http://www.loc.gov/standards/premis/premis.xsd";
			write(premis, file, new PremisNPM(), null, null, schemaLocation);
			if (isLocked) {
				fLock.createNewFile();
			} else if (fLock.exists()) {
				if (!fLock.delete()) {
					throw new PremisXsdException(
							"Riscontrato un problema nella cancellazione del file ["
									+ fLock.getAbsolutePath() + "]");
				}
			}
		} catch (PremisXsdException e) {
			throw e;
		} catch (XsdException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * Metodo utilizzato per estrare il nome del
	 * 
	 * @return
	 */
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
								key = (String) spct.getContent().get(0).getValue();
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
}
