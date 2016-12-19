/**
 * 
 */
package it.magazziniDigitali.xsd.agent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.JAXBElement;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

import it.magazziniDigitali.xsd.agent.v2_2.AgentV2_2Xsd;
import it.magazziniDigitali.xsd.agent.v3_0.AgentV3_0Xsd;
import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;
import mx.randalf.xsd.ReadXsd;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 *
 */
public abstract class AgentXsd<C, NPM extends NamespacePrefixMapper,
		AI, S, ECT, LRSICT> 
		extends ReadXsd<C>{

//	public static String UUID_MD_AG_ID = "UUID-MD-AG-ID";
//	public static String UUID_MD_RG_ID = "UUID-MD-RG-ID";
	public static String URI="URI";
	public static String USER="user";

	protected C agent = null;

	protected File file = null;

	protected String version = null;

	protected String schemaLocation = null;

	/**
	 * 
	 */
	public AgentXsd() {
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
	public AgentXsd(File file) throws XsdException {
		Object obj = null;
		
		obj = read(file);
		if (obj instanceof JAXBElement){
			agent = ((JAXBElement<C>)obj).getValue();
		} else {
			agent = (C) obj;
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
			write(agent, file, getNamespacePrefixMapper(), null, null, schemaLocation);
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

	public static AgentXsd<?, ?, ?, ?, ?, ?> initialize(){
		return new AgentV3_0Xsd();
	}

	public static AgentXsd<?, ?, ?, ?, ?, ?> open(File file) throws PremisXsdException{
		FileReader fr = null;
		BufferedReader br = null;
		String line = null;
		AgentXsd<?, ?, ?, ?, ?, ?> premisXsd = null;

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

	private static AgentXsd<?, ?, ?, ?, ?, ?> check(String line, File file) throws XsdException{
		String[] st = null;
		AgentXsd<?, ?, ?, ?, ?, ?> premisXsd = null;

		try {
			st = line.split("<");

			for (int x=0; x<st.length; x++){
				if (st[x].startsWith("premis")){
					if (st[x].indexOf("version=\"2.2\"")>-1){
						premisXsd = new AgentV2_2Xsd(file);
					}
					if (st[x].indexOf("version=\"3.0\"")>-1){
						premisXsd = new AgentV3_0Xsd(file);
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
	public abstract List<AI> getAgentIdentifier();

	public abstract void addAgentIdentifier(String agentIdentifierType, String agentIdentifierValue);

	public abstract List<S> getAgentName();
	
	public abstract void addAgentName(String value);
	
	public abstract S getAgentType();
	
	public abstract void setAgentType(String value);

	public abstract List<String> getAgentNote();
	
	public abstract void addAgentNote(String value);

	public abstract List<ECT> getAgentExtension();

	public abstract void addAgentExtensionDepositante(String value);

	protected abstract void addAgentExtension(Serializable value);

	public abstract List<LRSICT> getLinkingRightsStatementIdentifier();

	public abstract void addLinkingRightsStatementIdentifier(String value);
}
