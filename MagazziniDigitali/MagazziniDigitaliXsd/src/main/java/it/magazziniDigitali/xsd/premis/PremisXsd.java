/**
 * 
 */
package it.magazziniDigitali.xsd.premis;

import info.lc.xmlns.premis_v2.AgentComplexType;
import info.lc.xmlns.premis_v2.AgentIdentifierComplexType;
import info.lc.xmlns.premis_v2.ContentLocationComplexType;
import info.lc.xmlns.premis_v2.EventComplexType;
import info.lc.xmlns.premis_v2.EventIdentifierComplexType;
import info.lc.xmlns.premis_v2.EventOutcomeDetailComplexType;
import info.lc.xmlns.premis_v2.EventOutcomeInformationComplexType;
import info.lc.xmlns.premis_v2.File;
import info.lc.xmlns.premis_v2.FixityComplexType;
import info.lc.xmlns.premis_v2.FormatComplexType;
import info.lc.xmlns.premis_v2.FormatDesignationComplexType;
import info.lc.xmlns.premis_v2.LinkingAgentIdentifierComplexType;
import info.lc.xmlns.premis_v2.ObjectCharacteristicsComplexType;
import info.lc.xmlns.premis_v2.ObjectFactory;
import info.lc.xmlns.premis_v2.ObjectIdentifierComplexType;
import info.lc.xmlns.premis_v2.OriginalNameComplexType;
import info.lc.xmlns.premis_v2.PremisComplexType;
import info.lc.xmlns.premis_v2.RightsComplexType;
import info.lc.xmlns.premis_v2.RightsStatementComplexType;
import info.lc.xmlns.premis_v2.RightsStatementIdentifierComplexType;
import info.lc.xmlns.premis_v2.StorageComplexType;
import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;

import java.io.IOException;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import mx.randalf.xsd.ReadXsd;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 * 
 */
public class PremisXsd extends ReadXsd<PremisComplexType> {

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
	public PremisXsd(String agentIdentifierValue,
			String rightsStatementIdentifierValue, String rightsBasis) {
		AgentComplexType agent = null;
		AgentIdentifierComplexType agentIdentifier = null;
		RightsComplexType rights = null;
		RightsStatementComplexType rightsStatement = null;
		RightsStatementIdentifierComplexType rightsStatementIdentifier = null;

		premis = new PremisComplexType();
		premis.setVersion("2.2");

		agent = new AgentComplexType();
		agentIdentifier = new AgentIdentifierComplexType();
		agentIdentifier.setAgentIdentifierType("UUID");
		agentIdentifier.setAgentIdentifierValue(agentIdentifierValue);
		agent.getAgentIdentifier().add(agentIdentifier);
		premis.getAgent().add(agent);

		rights = new RightsComplexType();
		rightsStatementIdentifier = new RightsStatementIdentifierComplexType();
		rightsStatementIdentifier.setRightsStatementIdentifierType("UUID");
		rightsStatementIdentifier
				.setRightsStatementIdentifierValue(rightsStatementIdentifierValue);

		rightsStatement = new RightsStatementComplexType();
		rightsStatement.setRightsStatementIdentifier(rightsStatementIdentifier);
		rightsStatement.setRightsBasis(rightsBasis);
		rights.getRightsStatementOrRightsExtensionOrMdSec()
				.add(rightsStatement);
		premis.getRights().add(rights);
	}

	/**
	 * Costrutore con il quale si esegue il caricamente delle infoermazioni
	 * relative ad un file esistente
	 * 
	 * @param file
	 *            Nome del file contracciato Premis esistente
	 * @throws XsdException
	 */
	public PremisXsd(java.io.File file) throws XsdException {
		premis = read(file);
		this.file = file;
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
	 */
	public void addObjectFile(String objectIdentifierValue,
			BigInteger compositionLevel, String digest, Long size,
			String formatDesignationValue, String originalName) {
		addObjectFile(objectIdentifierValue, compositionLevel, digest, size,
				formatDesignationValue, originalName, null);
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
			String contentLocationValue) {
		File file = null;
		ObjectIdentifierComplexType objectIdentifier = null;
		ObjectCharacteristicsComplexType objectCharacterstics = null;
		FixityComplexType fixity = null;
		FormatComplexType format = null;
		FormatDesignationComplexType formatDesignation = null;
		StorageComplexType storage = null;
		ContentLocationComplexType contentLocation = null;
		OriginalNameComplexType origName = null;

		file = new File();
		file.setVersion("2.2");

		objectIdentifier = new ObjectIdentifierComplexType();
		objectIdentifier.setObjectIdentifierType("UUID-BNCF");
		objectIdentifier.setObjectIdentifierValue(objectIdentifierValue);
		file.getObjectIdentifier().add(objectIdentifier);

		objectCharacterstics = new ObjectCharacteristicsComplexType();
		objectCharacterstics.setCompositionLevel(compositionLevel);

		fixity = new FixityComplexType();
		fixity.setMessageDigestAlgorithm("SHA-1");
		fixity.setMessageDigest(digest);
		objectCharacterstics.getFixity().add(fixity);

		objectCharacterstics.setSize(size);

		format = new FormatComplexType();

		formatDesignation = new FormatDesignationComplexType();
		formatDesignation.setFormatName(formatDesignationValue);
		format.getContent().add(
				objectFactory.createFormatDesignation(formatDesignation));

		objectCharacterstics.getFormat().add(format);

		file.getObjectCharacteristics().add(objectCharacterstics);

		origName = new OriginalNameComplexType();
		origName.setValue(originalName);
		file.setOriginalName(origName);

		if (contentLocationValue != null) {
			storage = new StorageComplexType();
			contentLocation = new ContentLocationComplexType();
			contentLocation.setContentLocationType("tarindexer");
			contentLocation.setContentLocationValue(contentLocationValue);
			storage.getContent().add(
					objectFactory.createContentLocation(contentLocation));

			file.getStorage().add(storage);
		}

		premis.getObject().add(file);
	}

	public void addEvent(String eventXmlID, String eventIdentifierValue,
			String eventType, GregorianCalendar start, GregorianCalendar stop,
			String eventOutcome, String[] eventOutcomeDetails,
			String linkingAgentIdentifierValue) {
		EventComplexType event = null;
		EventIdentifierComplexType eventIdentifier = null;
		EventOutcomeInformationComplexType eventOutcomeInformation = null;
		EventOutcomeDetailComplexType eventOutcomeDetail = null;
		LinkingAgentIdentifierComplexType linkingAgentIdentifier = null;
		String eventDateTime = null;
		DecimalFormat df6 = new DecimalFormat("000000");
		DecimalFormat df4 = new DecimalFormat("0000");
		DecimalFormat df2 = new DecimalFormat("00");

		event = new EventComplexType();
		event.setXmlID(eventXmlID);

		eventIdentifier = new EventIdentifierComplexType();
		eventIdentifier.setEventIdentifierType("MD generated");
		eventIdentifier.setEventIdentifierValue(eventIdentifierValue);
		event.setEventIdentifier(eventIdentifier);

		event.setEventType(eventType);

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

		linkingAgentIdentifier = new LinkingAgentIdentifierComplexType();
		linkingAgentIdentifier.setLinkingAgentIdentifierType("UUID");
		linkingAgentIdentifier
				.setLinkingAgentIdentifierValue(linkingAgentIdentifierValue);
		event.getLinkingAgentIdentifier().add(linkingAgentIdentifier);
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
}
