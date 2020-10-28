/**
 * 
 */
package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.geoReplica;

import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.configuration.IMDConfiguration;
import it.bncf.magazziniDigitali.database.dao.MDStatoDAO;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import mx.randalf.hibernate.FactoryDAO;
import mx.randalf.hibernate.exception.HibernateUtilException;

/**
 * @author massi
 *
 */
class OggettoDigitaleGeoReplica_Check extends OggettoDigitaleGeoReplica_Init {

	/**
	 * 
	 */
	public OggettoDigitaleGeoReplica_Check(Logger logPublish, String name) {
		super(logPublish, name);
	}

	protected boolean checkStato(
			MDFilesTmp mdFilesTmp
			, String objectIdentifierPremis
			, IMDConfiguration<?> configuration
			, MDFilesTmpBusiness mdFileTmpBusiness
//			, String application
			) 
			throws HibernateException, HibernateUtilException {
		boolean esito = true;
		try {
			FactoryDAO.initialize(mdFilesTmp.getStato());
			if (mdFilesTmp.getStato().getId().equals(MDStatoDAO.FINEPUBLISH) ||
					mdFilesTmp.getStato().getId().equals(MDStatoDAO.INITARCHIVE)){
				
				esito = initGeoReplica(objectIdentifierPremis, configuration, mdFilesTmp, mdFileTmpBusiness);
//				, application);
			} else if (!mdFilesTmp.getStato().getId().equals(MDStatoDAO.FINEARCHIVE)){
				esito = false;
			}
		} catch (HibernateException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		}
		return esito;
	}

}
