/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.geoReplica;

import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.Vector;

import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.archive.MDArchiveBusiness;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.entity.MDArchive;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.database.entity.MDNodi;
import it.bncf.magazziniDigitali.nodi.ENodi;
import it.bncf.magazziniDigitali.nodi.Nodi;
import it.bncf.magazziniDigitali.nodi.exception.NodiException;
import it.depositolegale.www.storage.Storage;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
class OggettoDigitaleGeoReplica_GeoReplica extends OggettoDigitaleGeoReplica_SendFile {

	/**
	 * 
	 */
	public OggettoDigitaleGeoReplica_GeoReplica() {
	}

	protected String[] geoReplica(
			Nodi nodoInput
			, Nodi nodoOutput
			, MDNodi mdNodi
//			, File[] files
			, MDFilesTmp mdFilesTmp
			, PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisElab
//			, String application
			, String objectIdentifierMaster
//			, Documenti documenti
//			, String objectIdentifierPremis
			, IMDConfiguration<?> configuration
			) throws HibernateException, HibernateUtilException, SQLException,
			MDConfigurationException, NodiException
	{
		Vector<String> ris = null;
		Storage storage = null;
		MDArchive mdArchive = null;
		MDArchiveBusiness mdArchiveBusiness = null;
		GregorianCalendar dataStart = null;
		GregorianCalendar dataEnd = null;
		GregorianCalendar dStart = null;
		
		try {
			dataStart = new GregorianCalendar();
			dStart = new GregorianCalendar();
			mdArchiveBusiness = new MDArchiveBusiness();
			mdArchive = mdArchiveBusiness.find(mdFilesTmp, mdNodi);
			if (mdArchive == null || !mdArchive.getEsito().booleanValue()){
				storage = nodoOutput.checkStorage(nodoInput.genDocumenti());
//				storage = checkStorage(mdNodi, documenti, objectIdentifierPremis);
				if (storage !=null && (storage.getEsito().equals("OK")
						||storage.getEsito().equals("DOCNOTFOUND"))){
					ris = sendFile(
							nodoInput
							, nodoOutput
							, ENodi.TAR
//							,mdNodi,files[x]
							,premisElab
//							, application
							, objectIdentifierMaster
							, ris
							, configuration);
					ris = sendFile(
							nodoInput
							, nodoOutput
							, ENodi.PREMIS
//							,mdNodi,files[x]
							,premisElab
//							, application
							, objectIdentifierMaster
							, ris
							, configuration);

					if (ris==null){
						storage = nodoOutput.checkStorage(nodoInput.genDocumenti());
//						storage = checkStorage(mdNodi, documenti, objectIdentifierPremis);
						ris = analizeRisp(storage, mdNodi, premisElab, dStart, objectIdentifierMaster,ris, true, configuration, nodoInput, nodoOutput);
//						ris = analizeRisp(storage, mdNodi, premisElab, dStart, files, application, objectIdentifierMaster, ris, true, configuration);
					}
				} else {
					ris = analizeRisp(storage, mdNodi, premisElab, dStart, objectIdentifierMaster,ris, false, configuration, nodoInput, nodoOutput);
//					ris = analizeRisp(storage, mdNodi, premisElab, dStart, files, application, objectIdentifierMaster, ris, false, configuration);
				}
				dataEnd = new GregorianCalendar();
				mdArchiveBusiness.insert((mdArchive==null?null:mdArchive.getId()), 
						mdFilesTmp, mdNodi, dataStart, dataEnd, (ris==null));
			} else {
				addGeoReplica(
						premisElab, 
						mdArchive.getDataStart(), 
						mdArchive.getDataEnd(), 
//						mdNodi, files[x].getAbsolutePath(), 
						null, 
//						application, 
						objectIdentifierMaster, 
						configuration,
						nodoOutput,
						ENodi.TAR
						);
				addGeoReplica(
						premisElab, 
						mdArchive.getDataStart(), 
						mdArchive.getDataEnd(), 
//							mdNodi, files[x].getAbsolutePath(), 
						null, 
//							application, 
						objectIdentifierMaster, 
						configuration,
						nodoOutput,
						ENodi.PREMIS
						);
			}
		} catch (HibernateException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (MDConfigurationException e) {
			throw e;
		} catch (NodiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return (ris==null?null:ris.toArray(new String[ris.size()]));
	}

	private Vector<String> analizeRisp(
			Storage storage, 
			MDNodi mdNodi, 
			PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisElab,
			GregorianCalendar dStart, 
			//File[] files, String application, 
			String objectIdentifierMaster,
			Vector<String> ris, boolean testRes, IMDConfiguration<?> configuration
			, Nodi nodoInput, Nodi nodoOutput) throws MDConfigurationException {
		String msg = null;
		GregorianCalendar dEnd = null;
		ENodi eNodi = null;

		try {
			if (storage!=null &&
					storage.getDocumenti() != null &&
					storage.getDocumenti().getDocumento() != null){
			dEnd = new GregorianCalendar();
			for (int x=0; x<storage.getDocumenti().getDocumento().length; x++){
				if(x==0) {
					eNodi =ENodi.TAR;
				} else {
					eNodi =ENodi.PREMIS;
				}
				if (storage.getDocumenti().getDocumento()[x].getEsito().equals("ERRORDIGEST")){
					if (ris ==null){
						ris = new Vector<String>();
					}
					msg = "Nel modo: ["+
							mdNodi.getNome()+
							" - "+
							mdNodi.getDescrizione()+
							"] lo sha1 del file ["+
							storage.getDocumenti().getDocumento()[x].getNomeFile()+
							"] non coincide";
					ris.add(msg);
					addGeoReplica(
							premisElab, dStart, dEnd
//							, mdNodi, files[0].getAbsolutePath()
							, msg
//							, application
							, objectIdentifierMaster, configuration
							, nodoOutput, eNodi);
				} else if (storage.getDocumenti().getDocumento()[x].getEsito().equals("ERRORCREATEFOLDER")){
					if (ris ==null){
						ris = new Vector<String>();
					}
					msg = "Nel modo: ["+
							mdNodi.getNome()+
							" - "+
							mdNodi.getDescrizione()+
							"] riscontrato un problema nella creazione della cartella per il file ["+
							storage.getDocumenti().getDocumento()[x].getNomeFile()+
							"]";
					ris.add(msg);
					addGeoReplica(
							premisElab, dStart, dEnd
//							, mdNodi, files[0].getAbsolutePath()
							,msg
//							, application
							, objectIdentifierMaster, configuration
							, nodoOutput, eNodi);
				} else if (storage.getDocumenti().getDocumento()[x].getEsito().equals("ERROR")){
					if (ris ==null){
						ris = new Vector<String>();
					}
					msg = "Nel modo: ["+
							mdNodi.getNome()+
							" - "+
							mdNodi.getDescrizione()+
							"] riscontrato un errore generio per il file ["+
							storage.getDocumenti().getDocumento()[x].getNomeFile()+
							"]";
					ris.add(msg);
					addGeoReplica(
							premisElab, dStart, dEnd
//							, mdNodi, files[0].getAbsolutePath()
							, msg
//							, application
							, objectIdentifierMaster, configuration
							, nodoOutput, eNodi);
				} else if (!testRes && 
						storage.getDocumenti().getDocumento()[x].getEsito().equals("FOUND")){
					addGeoReplica(premisElab, dStart, dEnd
							//, mdNodi, files[0].getAbsolutePath()
							, null
//							, application
							, objectIdentifierMaster, configuration
							, nodoOutput, eNodi);
				} else if (storage.getDocumenti().getDocumento()[x].getEsito().equals("NOTFOUND")){
					if (testRes){
						if (ris ==null){
							ris = new Vector<String>();
						}
						msg = "Nel modo: ["+
								mdNodi.getNome()+
								" - "+
								mdNodi.getDescrizione()+
								"] riscontrato un errore nella verifica per il file ["+
								storage.getDocumenti().getDocumento()[x].getNomeFile()+
								"]";
						ris.add(msg);
						addGeoReplica(premisElab, dStart, dEnd
								//, mdNodi, files[0].getAbsolutePath()
								, msg
								//, application
								, objectIdentifierMaster, configuration
								, nodoOutput, eNodi);
					} else {
						ris = sendFile(
								nodoInput
								, nodoOutput
								, eNodi
//								,mdNodi,files[x]
								,premisElab
//								, application
								, objectIdentifierMaster
								, ris
								, configuration);

//						ris = sendFile(
//								nodoI
//								mdNodi,files[x],premisElab, application, objectIdentifierMaster, ris, configuration);
					}
				}
			}
			} else {
				if (ris ==null){
					ris = new Vector<String>();
				}
				msg = "Nel modo: ["+
						mdNodi.getNome()+
						" - "+
						mdNodi.getDescrizione()+
						"] riscontrato un errore nella risposta del Servizio ckeckGeoReplica";
				ris.add(msg);
			}
		} catch (MDConfigurationException e) {
			throw e;
		}
		return ris;
	}
}
