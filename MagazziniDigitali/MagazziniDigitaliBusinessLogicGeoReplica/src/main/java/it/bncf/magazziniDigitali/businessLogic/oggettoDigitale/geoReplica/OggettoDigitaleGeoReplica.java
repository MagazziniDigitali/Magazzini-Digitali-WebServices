/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.geoReplica;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import mx.randalf.hibernate.exception.HibernateUtilException;


/**
 * @author massi
 *
 */
public class OggettoDigitaleGeoReplica extends OggettoDigitaleGeoReplica_Check {

	private Logger log = LogManager.getLogger(OggettoDigitaleGeoReplica.class);

	/**
	 * 
	 */
	public OggettoDigitaleGeoReplica( 
			Logger logPublish, String name) {
		super(logPublish, name);
	}

	public boolean esegui(String objectIdentifierPremis, 
//			String application, 
			IMDConfiguration<?> configuration) throws HibernateUtilException {
		MDFilesTmp mdFilesTmp = null;
		boolean esito = true;
		MDFilesTmpBusiness mdFileTmpBusiness = null;

		logPublish.info("\n"+name+" ["+objectIdentifierPremis+"] Inizio la geo replica");
		trasferito = false;
		try {
			mdFileTmpBusiness = new MDFilesTmpBusiness();
			mdFilesTmp = mdFileTmpBusiness.findPremis(genPathPremis(objectIdentifierPremis));
		} catch (HibernateException e) {
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
		} catch (HibernateUtilException e) {
			log.error(name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
			throw e;
		}

		try {
			if (mdFilesTmp != null){
				esito = checkStato(mdFilesTmp
						, objectIdentifierPremis
						, configuration
						, mdFileTmpBusiness);
//						, application);
			} else {
				logPublish.error("\n"+name+" ["+objectIdentifierPremis+"]"+" Il file premis ["+objectIdentifierPremis+"] non è presente in archivio");
				esito = false;
			}
		} catch (HibernateUtilException e) {
			log.error("\n"+name+" ["+objectIdentifierPremis+"] "+e.getMessage(), e);
			esito = false;
			throw e;
		} finally{
			logPublish.info("\n"+name+" ["+objectIdentifierPremis+"] Fine della geo replica");
		}
		return esito;
	}



	
	/**
	 * @return the trasterito
	 */
	public boolean isTrasferito() {
		return trasferito;
	}

}
