/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.geoReplica;

import java.io.File;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.log4j.Logger;

import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.configuration.exception.MDConfigurationException;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.magazziniDigitali.xsd.premis.PremisXsd;

/**
 * @author massi
 *
 */
class OggettoDigitaleGeoReplica_Init extends OggettoDigitaleGeoReplica_Elab {

	private Logger log = Logger.getLogger(OggettoDigitaleGeoReplica_Init.class);

	protected boolean trasferito = false;

	/**
	 * 
	 */
	public OggettoDigitaleGeoReplica_Init(Logger logPublish, String name) {
		super(logPublish, name);
	}

	protected boolean initGeoReplica(
			String objectIdentifierPremis
			, IMDConfiguration<?> configuration
			, MDFilesTmp mdFilesTmp
			, MDFilesTmpBusiness mdFileTmpBusiness
//			, String application
			) {
		File filePremis = null;
		PremisXsd<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> premisElab = null;
		boolean esito = true;

		trasferito = true;
		try {
			filePremis = new File(
					genFilePremis(configuration.getSoftwareConfigString("path.premis"), 
								"GeoReplica",UUID.randomUUID().toString(),".geoReplica.premis"));

			premisElab = PremisXsd.initialize();
			esito = elabGeoReplica(configuration, mdFilesTmp, objectIdentifierPremis, premisElab, mdFileTmpBusiness, filePremis);
//			, 
//					application);
		} catch (MDConfigurationException e) {
			esito = false;
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		} catch (SQLException e) {
			esito = false;
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		}
		return esito;
	}

}
