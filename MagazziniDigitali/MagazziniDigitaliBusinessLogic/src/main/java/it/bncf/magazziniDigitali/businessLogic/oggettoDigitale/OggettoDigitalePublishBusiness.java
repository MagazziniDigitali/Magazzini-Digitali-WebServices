package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale;

import it.bncf.magazziniDigitali.businessLogic.filesTmp.MDFilesTmpBusiness;
import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.implement.OggettoDigitalePublish;
import it.bncf.magazziniDigitali.database.dao.MDStatoDAO;
import it.bncf.magazziniDigitali.database.entity.MDFilesTmp;
import it.bncf.magazziniDigitali.database.entity.MDStato;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.naming.NamingException;

import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class OggettoDigitalePublishBusiness extends OggettoDigitaleBusiness{
	
	public Logger log = Logger.getLogger(OggettoDigitalePublishBusiness.class);

	public OggettoDigitalePublishBusiness(HibernateTemplate hibernateTemplate) {
		super(hibernateTemplate);
	}

	/**
	 * Questo metodo viene utilizzato per gestire la pubblicazione del materiale
	 * 
	 * @param application
	 */
	public void publish(String application, boolean testMode, Logger logPublish) {
		MDFilesTmpBusiness mdFileTmp = null;
		List<MDFilesTmp> rs = null;

		List<Future<Boolean>> futuresList = null;
		int nrOfProcessors = 1;
		ExecutorService eservice = null;
		int numberThread = 10;
		MDStatoDAO mdStatoDAO = new MDStatoDAO(hibernateTemplate);

		try {
			logPublish.debug("Ricerco oggetti da Pubblicare");
			mdFileTmp = new MDFilesTmpBusiness(hibernateTemplate);
			rs = mdFileTmp.find(null, null, null, new MDStato[] {
					mdStatoDAO.FINEVALID(), mdStatoDAO.INITPUBLISH() }, null,
					1, 100);

			if (rs != null && rs.size() > 0) {
				logPublish.info("Ci sono " + rs.size()
						+ " oggetti da Pubblicare");
				futuresList = new ArrayList<Future<Boolean>>();
				nrOfProcessors = Runtime.getRuntime().availableProcessors();
				eservice = Executors.newFixedThreadPool(nrOfProcessors);
				if (Configuration.getValue("demoni.Publish.numberThread") != null) {
					numberThread = Integer.valueOf(Configuration
							.getValue("demoni.Publish.numberThread"));
				}
				if (testMode) {
					numberThread = 1;
				}
				for (int x = 0; x < rs.size(); x++) {

					if (futuresList.size() >= numberThread) {
						checkThread(futuresList, numberThread);
						if (testMode) {
							break;
						}
					}
					futuresList.add(eservice.submit(new OggettoDigitalePublish(
							rs.get(x), logPublish, mdFileTmp, "Publish " + x
									+ "/" + rs.size(), application)));
					Thread.sleep(10000);
				}
				checkThread(futuresList, -1);
				eservice.shutdown();
			} else {
				logPublish.debug("Nessun oggetti da Pubblicare");
			}
		} catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
		} catch (NamingException e) {
			log.error(e.getMessage(), e);
		}
	}
}
