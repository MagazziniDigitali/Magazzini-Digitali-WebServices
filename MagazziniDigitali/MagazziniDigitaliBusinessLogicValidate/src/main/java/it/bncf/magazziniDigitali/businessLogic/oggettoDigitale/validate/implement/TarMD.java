/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.implement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.everit.json.schema.ValidationException;
import org.json.JSONException;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate.json.JsonValidator;
import it.magazziniDigitali.xsd.agent.AgentXsd;
import it.magazziniDigitali.xsd.event.EventXsd;
import it.magazziniDigitali.xsd.metadata.MetadataXsd;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;
import it.magazziniDigitali.xsd.registroIngressi.RegistroIngressiXsd;
import it.magazziniDigitali.xsd.rights.RightsXsd;
import mx.randalf.archive.Tar;
import mx.randalf.archive.TarIndexer;
import mx.randalf.archive.info.Xmltype;
import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.mag.MagXsd;
import mx.randalf.mets.MetsXsd;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 *
 */
public class TarMD extends Tar {

	private Logger log = LogManager.getLogger(TarMD.class);

	/**
	 * 
	 */
	public TarMD() {
	}

	/**
	 * @throws FileNotFoundException,
	 *             IOException, XsdException
	 * 
	 */
	@Override
	protected void checkXmlType(File fTmp, TarIndexer tarIndexer)
			throws FileNotFoundException, IOException, XsdException {
		try {
			if (fTmp.getName().toLowerCase().endsWith(".xml") || fTmp.getName().toLowerCase().endsWith(".premis")) {
				log.debug("\nFile: "+fTmp.getName());
				tarIndexer.setXmlType(checkXml(fTmp));
				log.debug("\nXmlType: "+tarIndexer.getXmlType());
			}
			if (fTmp.getName().toLowerCase().endsWith(".json") && 
					! fTmp.getName().toLowerCase().endsWith(".siegfried.json")) {
				validateJson(fTmp);
			}
			if (fTmp.getName().toLowerCase().equals("bag-info.txt")) {
				tarIndexer.setXmlType(Xmltype.BAGIT.value());
				tarIndexer.setIdDepositante(checkBagInfo(fTmp));
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (XsdException e) {
			throw e;
		} catch (JSONException e) {
			throw new XsdException(e.getMessage(), e);
		} catch (ValidationException e) {
			throw new XsdException(e.getMessage(), e);
		} catch (ConfigurationException e) {
			throw new XsdException(e.getMessage(), e);
		}
	}

	private void validateJson(File fJson)
			throws ConfigurationException, FileNotFoundException, JSONException, IOException, ValidationException {
		JsonValidator jsonValidator = null;

		jsonValidator = new JsonValidator();
		jsonValidator.validate(Configuration.getValue("demoni.Validate.json.schema"), fJson.getAbsolutePath());
	}

	private String checkBagInfo(File fTmp) throws FileNotFoundException, IOException {
		FileReader fr = null;
		BufferedReader br = null;
		String line = null;
		String idDepositante = null;

		try {
			fr = new FileReader(fTmp);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				if (line.trim().startsWith("BNCF-user-id:")) {
					idDepositante = line.replace("BNCF-user-id:", "").trim();
				}
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
		return idDepositante;
	}

	protected String checkXml(File fXml) throws FileNotFoundException, IOException, XsdException {
		FileReader fr = null;
		BufferedReader br = null;
		String ris = null;
		String line = null;
		String firstLine = null;
		int pos = 0;

		try {
			fr = new FileReader(fXml);
			br = new BufferedReader(fr);
			firstLine = br.readLine();

			line = br.readLine();
			if (line == null) {
				firstLine = firstLine.substring(1);
				pos = firstLine.indexOf("<");
				if (pos > -1) {
					line = firstLine.substring(pos);
				}
			}
			if (line.trim().toLowerCase().startsWith("<mets")) {
				ris = Xmltype.METS.value();
			} else if (line.trim().toLowerCase().startsWith("<metadigit")) {
				ris = Xmltype.MAG.value();
			} else if (line.trim().toLowerCase().startsWith("<premis")) {
				ris = Xmltype.PREMIS.value();
			} else if (line.trim().toLowerCase().startsWith("<agent")) {
				ris = Xmltype.AGENT.value();
			} else if (line.trim().toLowerCase().startsWith("<rights")) {
				ris = Xmltype.RIGHTS.value();
			} else if (line.trim().toLowerCase().startsWith("<event")) {
				ris = Xmltype.EVENT.value();
			} else if (line.trim().toLowerCase().startsWith("<mdregistroingressi")) {
				ris = Xmltype.REGISTRO.value();
			}
			// if (ris != null) {
			validateXsd(fXml, ris);
			// }
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (XsdException e) {
			throw e;
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
		return ris;
	}

	@SuppressWarnings("unused")
	protected void validateXsd(File fXml, String type) throws XsdException {
		MetsXsd metsXsd = null;
		MagXsd magXsd = null;
		PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisXsd = null;
		AgentXsd<?, ?, ?, ?, ?, ?> agentXsd = null;
		RightsXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> rightsXsd = null;
		EventXsd<?, ?, ?, ?, ?> eventXsd = null;
		RegistroIngressiXsd registroIngressiXsd = null;
		MetadataXsd metadataXsd = null;

		try {
			switch (type) {
			case "mets":
				metsXsd = new MetsXsd();
				metsXsd.check(fXml);
				break;

			case "mag":
				magXsd = new MagXsd();
				magXsd.check(fXml);
				break;

			case "premis":
				premisXsd = PremisXsd.open(fXml);
				break;

			case "agent":
				agentXsd = AgentXsd.open(fXml);
				break;

			case "rights":
				rightsXsd = RightsXsd.open(fXml);
				break;

			case "event":
				eventXsd = EventXsd.open(fXml);
				break;

			case "registro":
				registroIngressiXsd = new RegistroIngressiXsd();
				registroIngressiXsd.check(fXml);
				break;

			case "metadata":
				metadataXsd = new MetadataXsd();
				metadataXsd.check(fXml);
				break;

			default:
				throw new XsdException("Formato xml non supportqato");
			}
		} catch (XsdException e) {
			throw e;
		} catch (PremisXsdException e) {
			throw new XsdException(e.getMessage(), e);
		}

	}

}
