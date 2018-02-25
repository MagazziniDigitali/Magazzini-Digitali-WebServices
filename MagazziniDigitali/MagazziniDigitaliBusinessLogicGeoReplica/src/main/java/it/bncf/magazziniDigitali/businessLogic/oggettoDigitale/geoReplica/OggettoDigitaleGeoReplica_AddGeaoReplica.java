/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.geoReplica;

import java.util.Date;
import java.util.GregorianCalendar;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.OggettoDigitale;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.nodi.ENodi;
import it.bncf.magazziniDigitali.nodi.Nodi;
import it.magazziniDigitali.xsd.premis.PremisXsd;

/**
 * @author massi
 *
 */
public class OggettoDigitaleGeoReplica_AddGeaoReplica extends OggettoDigitale {

	/**
	 * 
	 */
	public OggettoDigitaleGeoReplica_AddGeaoReplica() {
	}

	protected void addGeoReplica(
			PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisElab
			, Date dataStart
			, Date dataEnd
//			, MDNodi mdNodi
//			, String file
			, String msgError
//			, String application
			, String objectIdentifierMaster
			, IMDConfiguration<?> configuration
			, Nodi nodoOutput
			, ENodi eNodi
			) throws MDConfigurationException {
		GregorianCalendar dStart;
		GregorianCalendar dEnd;

		try {
			dStart = new GregorianCalendar();
			dStart.setTimeInMillis(dataStart.getTime());

			dEnd = new GregorianCalendar();
			dEnd.setTimeInMillis(dataEnd.getTime());

			addGeoReplica(
					premisElab
					, dStart
					, dEnd
//					, mdNodi
//					, file
					, msgError
//					, application
					, objectIdentifierMaster
					, configuration
					, nodoOutput
					, eNodi
					);
		} catch (MDConfigurationException e) {
			throw e;
		}
	}

	protected void addGeoReplica(
			PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisElab
			, GregorianCalendar dataStart
			, GregorianCalendar dataEnd
			
//			, MDNodi mdNodi
//			, String file
			, String msgError
//			, String application
			, String objectIdentifierMaster
			, IMDConfiguration<?> configuration
			, Nodi nodoOutput
			, ENodi eNodi
			) throws MDConfigurationException
	{
		try {
			premisElab.addEvent("geoReplica", dataStart, dataEnd, 
					"Nodo ID: "+nodoOutput.getMdNodi().getNome()
					+" File: "+nodoOutput.genFileDest(eNodi), 
					(msgError==null?"OK":"KO"), 
					(msgError==null?null:new String[]{msgError}), 
					null, 
					configuration.getMDSoftware(),
//				Configuration.getValue("demoni."
//						+ application + ".UUID"),
					objectIdentifierMaster);
		} catch (MDConfigurationException e) {
			throw e;
		}
	}

}
