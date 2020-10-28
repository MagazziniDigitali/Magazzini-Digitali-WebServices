/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.geoReplica;

import java.util.GregorianCalendar;
import java.util.Vector;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.exception.ClientMDException;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.nodi.ENodi;
import it.bncf.magazziniDigitali.nodi.Nodi;
import it.bncf.magazziniDigitali.nodi.exception.NodiException;
import it.bncf.magazziniDigitali.nodi.exception.NotImplementException;
import it.magazziniDigitali.xsd.premis.PremisXsd;

/**
 * @author massi
 *
 */
public class OggettoDigitaleGeoReplica_SendFile extends OggettoDigitaleGeoReplica_AddGeaoReplica {

	/**
	 * 
	 */
	public OggettoDigitaleGeoReplica_SendFile() {
	}
	
	protected Vector<String> sendFile(
			Nodi nodoInput
			, Nodi nodoOutput
			, ENodi eNodi
//			MDNodi mdNodi
//			, File file
			, PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisElab
//			, String application
			, String objectIdentifierMaster
			, Vector<String> ris
			, IMDConfiguration<?> configuration
			) throws MDConfigurationException {
		GregorianCalendar dStart = null;
		GregorianCalendar dEnd = null;

		try {
			try {
				dStart = new GregorianCalendar();
				if (nodoOutput.copyFile(nodoInput, eNodi)) {
					dEnd = new GregorianCalendar();
					addGeoReplica(premisElab, dStart, dEnd, 
							//mdNodi, file.getAbsolutePath(), 
							null, 
							//application, 
							objectIdentifierMaster, configuration
							, nodoOutput
							, eNodi);
				} else {
					throw new ClientMDException("Riscontrato un problema durante la GeoReplica");
				}
//				rsyncFile(mdNodi, file, configuration);
			} catch (ClientMDException e) {
				if (ris ==null){
					ris = new Vector<String>();
				}
				ris.add(e.getMessage());
				dEnd = new GregorianCalendar();
				addGeoReplica(premisElab, dStart, dEnd, 
						//mdNodi, file.getAbsolutePath(), 
						e.getMessage(), 
						//application, 
						objectIdentifierMaster, configuration
						, nodoOutput
						, eNodi);
			} catch (MDConfigurationException e) {
				if (ris ==null){
					ris = new Vector<String>();
				}
				ris.add(e.getMessage());
				dEnd = new GregorianCalendar();
				addGeoReplica(premisElab, dStart, dEnd
						//, mdNodi, file.getAbsolutePath()
						,e.getMessage()
						//, application
						, objectIdentifierMaster, configuration
						, nodoOutput
						, eNodi);
			} catch (NodiException e) {
				if (ris ==null){
					ris = new Vector<String>();
				}
				ris.add(e.getMessage());
				dEnd = new GregorianCalendar();
				addGeoReplica(premisElab, dStart, dEnd
						//, mdNodi, file.getAbsolutePath()
						,e.getMessage()
						//, application
						, objectIdentifierMaster, configuration
						, nodoOutput
						, eNodi);
			} catch (NotImplementException e) {
				if (ris ==null){
					ris = new Vector<String>();
				}
				ris.add(e.getMessage());
				dEnd = new GregorianCalendar();
				addGeoReplica(premisElab, dStart, dEnd
						//, mdNodi, file.getAbsolutePath()
						,e.getMessage()
						//, application
						, objectIdentifierMaster, configuration
						, nodoOutput
						, eNodi);
			}
		} catch (MDConfigurationException e) {
			throw e;
		}
		return ris;
	}

}
