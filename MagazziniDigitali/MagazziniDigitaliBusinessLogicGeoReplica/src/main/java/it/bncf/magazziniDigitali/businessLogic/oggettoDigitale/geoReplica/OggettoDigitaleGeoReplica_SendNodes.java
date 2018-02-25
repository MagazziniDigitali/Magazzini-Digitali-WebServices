/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.geoReplica;

import java.io.File;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.dao.MDNodiDAO;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.database.entity.MDNodi;
import it.bncf.magazziniDigitali.nodi.Nodi;
import it.bncf.magazziniDigitali.nodi.exception.NodiException;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
class OggettoDigitaleGeoReplica_SendNodes extends OggettoDigitaleGeoReplica_GeoReplica {

	/**
	 * 
	 */
	public OggettoDigitaleGeoReplica_SendNodes() {
	}

	@SuppressWarnings("unused")
	protected boolean sendNodes(
			Nodi nodoInput
////			File fileElab, File fileElabPremis
			, IMDConfiguration<?> configuration
			, MDFilesTmp mdFilesTmp
			, PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisElab
//			, String application
			, String objectIdentifierPremis
			, MDFilesTmpBusiness mdFileTmpBusiness
			, File filePremis
			) throws MDConfigurationException, HibernateUtilException, SQLException, NodiException 
	{
		MDNodiDAO mdNodiDAO = null;
		List<MDNodi> mdNodis = null;
		boolean elabNodi = true;
//		File[] files = null;
//		Documenti documenti = null;
		String[] msgs = null;
		Vector<String> msgErr = null;
		GregorianCalendar gcEnd = null;
		boolean esito = true;
		Nodi nodoOutput = null;

		try {
			mdNodiDAO = new MDNodiDAO();
			mdNodis = mdNodiDAO.findActive();
			elabNodi = true;
//			files = new File[2];
//			files[0] = fileElab;
//			files[1] = fileElabPremis;
//			documenti = genDocumenti(files, configuration);
			for (MDNodi mdNodiOutput : mdNodis){
				if (!mdNodiOutput.getId().equals(
						configuration.getSoftwareConfigMDNodi("nodo").getId()
//					Configuration.getValue("nodo")
						)){
					
					nodoOutput = new Nodi(mdNodiOutput, nodoInput.getNomeFileTar(), nodoInput.getNomeFilePremis());
					msgs =geoReplica(
							nodoInput
							, nodoOutput
							, mdNodiOutput
//							, files
							, mdFilesTmp
							, premisElab
//							, application
							, objectIdentifierPremis
//							, documenti
//							, objectIdentifierPremis
							, configuration
							);
					if (msgs!=null){
						if (msgErr==null){
							msgErr = new Vector<String>();
						}
						for (int x=0; x<msgs.length; x++){
							msgErr.add(msgs[x]);
						}
						elabNodi = false;
					}
				}
			}
			if (elabNodi){
				gcEnd = mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(), true, null, null,
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			} else {
				esito = false;
				gcEnd = mdFileTmpBusiness.updateStopArchive(mdFilesTmp.getId(), false, null,
						msgErr.toArray(new String[msgErr.size()]),
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			}
//		} catch (NoSuchAlgorithmException e) {
//			throw e;
//		} catch (FileNotFoundException e) {
//			throw e;
//		} catch (HibernateException e) {
//			throw e;
//		} catch (IOException e) {
//			throw e;
		} catch (MDConfigurationException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (NodiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return esito;
	}

}
