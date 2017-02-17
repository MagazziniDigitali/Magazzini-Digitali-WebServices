/**
 * 
 */
package it.magazziniDigitali.xsd.rights;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBElement;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;
import it.magazziniDigitali.xsd.rights.v2_2.RightsV2_2Xsd;
import it.magazziniDigitali.xsd.rights.v3_0.RightsV3_0Xsd;
import mx.randalf.xsd.ReadXsd;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 *
 */
public abstract class RightsXsd<C, NPM extends NamespacePrefixMapper, LICT, LOICT, RSCT, RSICT, CICT, SICT, RGCT, ORICT>
		extends ReadXsd<C> {

	public static String COPYRIGHT = "copyright";
	public static String LICENSE = "license";
	public static String STATUTE = "statute";
	public static String OTHER = "other";

	public static String REPLICATE = "replicate";
	public static String MIGRATE = "migrate";
	public static String USE = "use";
	public static String DISSEMINATE = "disseminate";

	protected C agent = null;

	protected File file = null;

	protected String version = null;

	protected String schemaLocation = null;

	/**
	 * 
	 */
	public RightsXsd() {
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
	public RightsXsd(File file) throws XsdException {
		Object obj = null;

		obj = read(file);
		if (obj instanceof JAXBElement) {
			agent = ((JAXBElement<C>) obj).getValue();
		} else {
			agent = (C) obj;
		}
		this.file = file;
		init();
	}

	protected abstract void init();

	public void write(boolean isLocked) throws PremisXsdException, XsdException, IOException {
		write(file, isLocked);
	}

	public void write(java.io.File file, boolean isLocked) throws PremisXsdException, XsdException, IOException {
		java.io.File fLock = null;
		try {
			if (file == null) {
				throw new PremisXsdException("E' necessario indicare il nome del file con cui salvare i dati");
			}
			fLock = new java.io.File(file.getAbsolutePath() + ".lock");
			write(agent, file, getNamespacePrefixMapper(), null, null, schemaLocation);
			if (isLocked) {
				fLock.createNewFile();
			} else if (fLock.exists()) {
				if (!fLock.delete()) {
					throw new PremisXsdException(
							"Riscontrato un problema nella cancellazione del file [" + fLock.getAbsolutePath() + "]");
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

	public static RightsXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> initialize() {
		return new RightsV3_0Xsd();
	}

	public static RightsXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> open(File file) throws PremisXsdException {
		FileReader fr = null;
		BufferedReader br = null;
		String line = null;
		RightsXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisXsd = null;

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			line = br.readLine();
			if (line != null) {
				premisXsd = check(line, file);
			}
			if (premisXsd == null) {
				line = br.readLine();
				if (line != null) {
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
				if (br != null) {
					br.close();
				}
				if (fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				throw new PremisXsdException(e.getMessage(), e);
			}
		}
		return premisXsd;
	}

	private static RightsXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> check(String line, File file) throws XsdException {
		String[] st = null;
		RightsXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisXsd = null;

		try {
			st = line.split("<");

			for (int x = 0; x < st.length; x++) {
				if (st[x].startsWith("rights")) {
					if (st[x].indexOf("version=\"2.2\"") > -1) {
						premisXsd = new RightsV2_2Xsd(file);
					} else if (st[x].indexOf("version=\"3.0\"") > -1) {
						premisXsd = new RightsV3_0Xsd(file);
					} else if (st[x].indexOf("version=\"") == -1) {
						premisXsd = new RightsV3_0Xsd(file);
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
	public abstract List<Object> getRightsStatementOrRightsExtension();

	public abstract String getXmlID();

	public abstract void setXmlID(String value);

	public abstract void addRightsStatementComplexTypeAltaRisoluzione(String rightsStatementIdentifierValue,
			String linkingObjectIdentifierValue);

	public abstract void addRightsStatementComplexTypeAccessoAperto(String rightsStatementIdentifierValue);

	public abstract void addRightsStatementComplexTypeProtettoLicenza(String rightsStatementIdentifierValue,
			String linkingObjectIdentifierValue);

	protected abstract SICT genStatuteInformation(String statuteJurisdiction, String statuteCitation);

	protected abstract RGCT genRightsGranted(String act, String restriction);

	protected abstract ORICT genOtherRightsInformation(String otherRightsBasis);

	protected abstract LICT genLicenseInformationComplexType(String licenseNote, GregorianCalendar licenseDateStart,
			GregorianCalendar licenseDateEnd);

	protected abstract LOICT genLinkingObjectIdentifierComplexType(String linkingObjectIdentifierValue);

	protected abstract RSCT genRightsStatementComplexType(String rightsStatementIdentifierValue);

	protected abstract RSICT genRightsStatementIdentifierComplexType(String rightsStatementIdentifierValue);

	protected abstract CICT genCopyrightInformationComplexType(String copyrightStatus, String copyrightJurisdiction,
			GregorianCalendar copyrightDateStart, GregorianCalendar copyrightDateEnd);
}
