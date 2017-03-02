/**
 * 
 */
package it.magazziniDigitali.xsd.event;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.JAXBElement;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

import it.magazziniDigitali.xsd.event.v2_2.EventV2_2Xsd;
import it.magazziniDigitali.xsd.event.v3_0.EventV3_0Xsd;
import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;
import mx.randalf.xsd.ReadXsd;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 *
 */
public abstract class EventXsd<E, NPM extends NamespacePrefixMapper,
		EI, S, LO> 
		extends ReadXsd<E>{

	public static String CODICENBN="codiceNBN";

	public static String URLORIGINAL="urlOriginal";
	
	public static String NBN = "nbn";

	public static String USERAUTHENTICATION="userAuthentication";
	
	protected E event = null;

	protected File file = null;

	protected String version = null;

	protected String schemaLocation = null;

	/**
	 * 
	 */
	public EventXsd() {
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
	public EventXsd(File file) throws XsdException {
		Object obj = null;
		
		obj = read(file);
		if (obj instanceof JAXBElement){
			event = ((JAXBElement<E>)obj).getValue();
		} else {
			event = (E) obj;
		}
		this.file = file;
		init();
	}
	
	protected abstract void init();

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
			write(event, file, getNamespacePrefixMapper(), null, null, schemaLocation);
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

	protected abstract NPM getNamespacePrefixMapper();

	public static EventXsd<?, ?, ?, ?, ?> initialize(){
		return new EventV3_0Xsd();
	}

	public static EventXsd<?, ?, ?, ?, ?> open(File file) throws PremisXsdException{
		FileReader fr = null;
		BufferedReader br = null;
		String line = null;
		EventXsd<?, ?, ?, ?, ?> premisXsd = null;

		try {
			fr = new FileReader(file);
			br = new  BufferedReader(fr);
			line = br.readLine();
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

	private static EventXsd<?, ?, ?, ?, ?> check(String line, File file) throws XsdException{
		String[] st = null;
		EventXsd<?, ?, ?, ?, ?> premisXsd = null;

		try {
			st = line.split("<");

			for (int x=0; x<st.length; x++){
				if (st[x].startsWith("event")){
					if (st[x].indexOf("version=\"2.2\"")>-1){
						premisXsd = new EventV2_2Xsd(file);
					} else if (st[x].indexOf("version=\"3.0\"")>-1){
						premisXsd = new EventV3_0Xsd(file);
					} else if (st[x].indexOf("version=\"")==-1){
						premisXsd = new EventV3_0Xsd(file);
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

	/**
	 * Metodo utilizzato per leggere la lista degli oggetti presenti
	 * 
	 * @return Lista oggetti presenti
	 */
	public abstract EI getEventIdentifier();

	public abstract void setEventIdentifier(String eventIdentifierType, String eventIdentifierValue);

	public abstract S getEventType();
	
	public abstract void setEventType(String value);
	
	public abstract String getEventDateTime();
	
	public abstract void setEventDateTime(String value);

	public abstract List<LO> getLinkingObjectIdentifier();
	
	public abstract void addLinkingObjectIdentifier(String linkingObjectIdentifierType, 
			String linkingObjectIdentifierValue);
	
	public abstract void addLinkingAgentIdentifier(String linkingObjectIdentifierType, 
			String linkingObjectIdentifierValue, String linkingAgentRole);
	
	public abstract void addEventDetailInformationExtension(Serializable extensionComplexType);

	/**
	 * @return the event
	 */
	public E getEvent() {
		return event;
	}
}
