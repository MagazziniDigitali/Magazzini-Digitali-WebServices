/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.geoReplica;

import java.io.File;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.nodi.Nodi;
import it.bncf.magazziniDigitali.nodi.exception.NodiException;
import it.magazziniDigitali.xsd.premis.PremisXsd;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
class OggettoDigitaleGeoReplica_Verify extends OggettoDigitaleGeoReplica_SendNodes {

	protected Logger logPublish = null;
	
	protected String name = null;

	/**
	 * 
	 */
	public OggettoDigitaleGeoReplica_Verify(Logger logPublish, String name) {
		this.logPublish = logPublish;
		this.name = name;
	}

	protected boolean verifyFileElabPremis(
////			File fileElabPremis
			Nodi nodoInput
			, String objectIdentifierPremis
			, PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisElab
			, IMDConfiguration<?> configuration
			, MDFilesTmpBusiness mdFileTmpBusiness
			, MDFilesTmp mdFilesTmp
//			//, File fileElab
//			, String application
			, File filePremis
			) throws MDConfigurationException, SQLException, HibernateUtilException, NodiException 
	{
		boolean esito = true;

		try {
			if (nodoInput.isFilePremisExists()){
				esito = sendNodes(nodoInput
						//fileElab, fileElabPremis
						, configuration, mdFilesTmp, premisElab
						//, application
						, objectIdentifierPremis, 
						mdFileTmpBusiness, filePremis);
			} else {
				esito = false;
				logPublish.error("\n"+name+" ["+objectIdentifierPremis+"]"+" Il file ["+nodoInput.genFilePremisDest()+"] non è presente");
				premisElab.addEvent(
						"Error",
						null,
						null,
						null,
						"KO",
						new String[] { "Il file ["+nodoInput.genFilePremisDest()+"] non è presente" },
						null,
						configuration.getMDSoftware()
//					Configuration.getValue("demoni."
//							+ application + ".UUID")
						, null);
				mdFileTmpBusiness
				.updateStopArchive(
						mdFilesTmp.getId(),
						false,
						null,
						new String[] { "Il file ["+nodoInput.genFilePremisDest()+"] non è presente" },
						writeFilePremisDB(filePremis, configuration.getSoftwareConfigString("path.premis")));
			}
		} catch (MDConfigurationException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
//		} catch (NoSuchAlgorithmException e) {
//			throw e;
//		} catch (FileNotFoundException e) {
//			throw e;
		} catch (HibernateException e) {
			throw e;
//		} catch (IOException e) {
//			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		} catch (NodiException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return esito;
	}

}
