/**
 * 
 */
package it.magazziniDigitali.xsd.premis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import javax.xml.bind.JAXBElement;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

import it.bncf.magazziniDigitali.database.entity.MDRigths;
import it.bncf.magazziniDigitali.database.entity.MDSoftware;
import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;
import it.magazziniDigitali.xsd.premis.v2_2.PremisV2_2Xsd;
import it.magazziniDigitali.xsd.premis.v3_0.PremisV3_0Xsd;
import mx.randalf.xsd.ReadXsd;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 *
 */
public abstract class PremisXsd<C, O, OCT, ECT, ACT, RCT, ReCT, SCT, OICT, OCCT, ONCT,
		NPM extends NamespacePrefixMapper> extends ReadXsd<C>{

	public static String UUID_MD_RG = "UUID-MD-RG";
	public static String UUID_MD = "UUID-MD";
	public static String UUID_MD_OBJ = "UUID-MD-OBJ";
	public static String UUID_MD_EV = "UUID-MD-EV";
	public static String UUID_MD_AG = "UUID-MD-AG";
	public static String SOFTWARE="software";
	public static String DEPOSITANTE="depositante";

	protected C premis = null;

	protected O objectFactory = null;
	
	protected File file = null;

	protected String version = null;

	protected String schemaLocation = null;

	/**
	 * 
	 */
	public PremisXsd() {
		init();
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
	public PremisXsd(File file) throws XsdException {
		Object obj = null;
		
		obj = read(file);
		if (obj instanceof JAXBElement){
			premis = ((JAXBElement<C>)obj).getValue();
		} else {
			premis = (C) obj;
		}
		this.file = file;
		init();
	}
	
	protected abstract void init();

	/**
	 * Metodo utilizzato per leggere la lista degli oggetti presenti
	 * 
	 * @return Lista oggetti presenti
	 */
	public abstract List<OCT> getObject();

	/**
	 * Metodo utilizzato per leggere la lista degli eventi presenti
	 * 
	 * @return Lista eventi presenti
	 */
	public abstract List<ECT> getEvent();

	/**
	 * Metodo utilizzato per leggere la lista degli agent presenti
	 * 
	 * @return Lista agent presenti
	 */
	public abstract List<ACT> getAgent();

	/**
	 * Metodo utilizzato per leggere la lista delle rights presenti
	 * 
	 * @return Lista rights presenti
	 */
	public abstract List<RCT> getRights();

	/**
	 * Questo metodo viene utilizzato per aggiungere un oggetto
	 * 
	 * @param object Oggeto da aggiungere
	 */
	public abstract void addObject(OCT object);

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
	public abstract void addObjectFileContainer(String objectIdentifierValue,
			String fType, String ext, BigInteger compositionLevel,
			String digest, Long size, String formatDesignationValue,
			String originalName, MDRigths right, String formatVersion, String puid);

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
	public abstract void addObjectFile(String objectIdentifierValue,
			BigInteger compositionLevel, String digest, Long size,
			String formatDesignationValue, String originalName,
			String contentLocationValue, String formatVersion, String puid,
			String relationshipSubType, String objectIdentifierMaster
			);

	protected abstract ReCT addRelationship(String relationshipSubType, String objectIdentifierMaster);

	protected abstract SCT addStorage(String type, String value);

	protected abstract OICT addObjectIdentifier(String objectIdentifierValue);

	protected abstract OCCT addObjectCharacteristics(
			BigInteger compositionLevel, String digest, Long size,
			String formatDesignationValue, String formatVersion, String puid);

	protected abstract ONCT addOriginalName(String originalName);
	
	public abstract void addEvent(String eventType, Object oStart, Object oStop,
			String eventDetail,
			String eventOutcome, String[] eventOutcomeDetails,
			String linkingAgentIdentifierValue, MDSoftware linkingSoftwareIdentifierValue,
			String objectIdentifierMaster);

	protected abstract NPM getNamespacePrefixMapper();

	public abstract String getFileName();

	public abstract String getActualFileName();

	public void write(boolean isLocked) throws PremisXsdException,
			XsdException, IOException {
		write(file, isLocked);
	}

	public void write(java.io.File file, boolean isLocked)
			throws PremisXsdException, XsdException, IOException {
		java.io.File fLock = null;
		try {
			if (file == null) {
				throw new PremisXsdException(
						"E' necessario indicare il nome del file con cui salvare i dati");
			}
			fLock = new java.io.File(file.getAbsolutePath() + ".lock");
			write(premis, file, getNamespacePrefixMapper(), null, null, schemaLocation);
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

	public static PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> initialize(){
		return new PremisV3_0Xsd();
	}

	public static PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> open(File file) throws PremisXsdException{
		FileReader fr = null;
		BufferedReader br = null;
		String line = null;
		PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisXsd = null;
		int pos = 0;
		String line2 = null;

		try {
			fr = new FileReader(file);
			br = new  BufferedReader(fr);
			line = br.readLine().trim();
			if (line.startsWith("<?")){
				pos = line.indexOf("?>");
				if (pos >-1){
					line = line.substring(pos+2);
				}
			}
			if (line.indexOf(">")==-1){
				while ((line2= br.readLine()) != null){
					line +=line2;
					if (line.indexOf(">")>-1){
						break;
					}
				}
			}
			if (line != null){
				premisXsd = check(line, file);
			}
			if (premisXsd == null){
				line = br.readLine();
				if (line != null){
					premisXsd = check(line, file);
				}
			}
		} catch (FileNotFoundException e) {
			throw new PremisXsdException(e.getMessage(), e);
		} catch (IOException e) {
			throw new PremisXsdException(e.getMessage(), e);
		} catch (XsdException e) {
			throw new PremisXsdException(e.getMessage(), e);
		} finally {
			try {
				if (br != null){
					br.close();
				}
				if (fr != null){
					fr.close();
				}
			} catch (IOException e) {
				throw new PremisXsdException(e.getMessage(), e);
			}
		}
		return premisXsd;
	}

	private static PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> check(String line, File file) throws XsdException{
		String[] st = null;
		PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisXsd = null;

		try {
			st = line.split("<");

			for (int x=0; x<st.length; x++){
				if (st[x].startsWith("premis")){
					if (st[x].indexOf("version=\"2.2\"")>-1){
						premisXsd = new PremisV2_2Xsd(file);
					}
					if (st[x].indexOf("version=\"3.0\"")>-1){
						premisXsd = new PremisV3_0Xsd(file);
					}
					break;
				}
			}
		} catch (XsdException e) {
			throw e;
		}
		return premisXsd;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	public String findObjectIdentifierContainer(){
		String result = null;
		if (this.getObject() != null) {
			for (int x = 0; x < this.getObject().size(); x++) {
				result = findObjectIdentifierContainer(this.getObject().get(x));
				if (result != null) {
					break;
				}
			}
		}
		return result;
	}

	protected abstract String findObjectIdentifierContainer(OCT oct);
}
