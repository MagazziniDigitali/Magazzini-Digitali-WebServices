/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.validate;

import java.io.File;

import it.magazziniDigitali.xsd.agent.AgentXsd;
import it.magazziniDigitali.xsd.event.EventXsd;
import it.magazziniDigitali.xsd.metadata.MetadataXsd;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import it.magazziniDigitali.xsd.premis.exception.PremisXsdException;
import it.magazziniDigitali.xsd.registroIngressi.RegistroIngressiXsd;
import it.magazziniDigitali.xsd.rights.RightsXsd;
import mx.randalf.archive.Tar;
import mx.randalf.mag.MagXsd;
import mx.randalf.mets.MetsXsd;
import mx.randalf.xsd.exception.XsdException;

/**
 * @author massi
 *
 */
public class TarMD extends Tar {

	/**
	 * 
	 */
	public TarMD() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.randalf.archive.Tar#validateXsd(java.io.File, java.lang.String)
	 */
	@SuppressWarnings("unused")
	@Override
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
			throw new XsdException(e.getMessage(),e);
		}

	}

}
