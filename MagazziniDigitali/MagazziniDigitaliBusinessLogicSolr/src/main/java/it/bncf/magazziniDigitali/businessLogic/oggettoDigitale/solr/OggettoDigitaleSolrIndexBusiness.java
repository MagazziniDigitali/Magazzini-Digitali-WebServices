package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.solr;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.bncf.magazziniDigitali.businessLogic.oggettoDigitale.OggettoDigitaleBusiness;

public class OggettoDigitaleSolrIndexBusiness extends OggettoDigitaleBusiness{
	
	public Logger log = LogManager.getLogger(OggettoDigitaleSolrIndexBusiness.class);

	public OggettoDigitaleSolrIndexBusiness() {
		super();
	}

	/**
	 * Questo metodo viene utilizzato per gestire la pubblicazione del materiale
	 * 
	 * @param application
	 */
//	public void solrIndex(String application, boolean testMode, Logger logSolr) {
//		MDFilesTmpBusiness mdFileTmp = null;
//		List<MDFilesTmp> rs = null;
//
//		List<Future<Boolean>> futuresList = null;
//		int nrOfProcessors = 1;
//		ExecutorService eservice = null;
//		int numberThread = 10;
//		MDStatoDAO mdStatoDAO = new MDStatoDAO(hibernateTemplate);
//
//		try {
//			logSolr.debug("Ricerco oggetti da Indicizzare");
//				logSolr.info("Ci sono " + rs.size()
//						+ " oggetti da Indicizzare");
//				futuresList = new ArrayList<Future<Boolean>>();
//				nrOfProcessors = Runtime.getRuntime().availableProcessors();
//				eservice = Executors.newFixedThreadPool(nrOfProcessors);
//				if (Configuration.getValue("demoni.SolrIndex.numberThread") != null) {
//					numberThread = Integer.valueOf(Configuration
//							.getValue("demoni.SolrIndex.numberThread"));
//				}
//				if (testMode) {
//					numberThread = 1;
//				}
//				for (int x = 0; x < rs.size(); x++) {
//
//					if (futuresList.size() >= numberThread) {
//						checkThread(futuresList, numberThread);
//						if (testMode) {
//							break;
//						}
//					}
//					futuresList.add(eservice.submit(new OggettoDigitaleSolrIndex(
//							rs.get(x), logSolr, mdFileTmp, "SolrIndex " + (x+1)
//									+ "/" + rs.size(), application)));
//					Thread.sleep(10000);
//				}
//				checkThread(futuresList, -1);
//				eservice.shutdown();
//			} else {
//				logSolr.debug("Nessun oggetti da Indicizzare");
//			}
//		} catch (ConfigurationException e) {
//			log.error(e.getMessage(), e);
//		} catch (InterruptedException e) {
//			log.error(e.getMessage(), e);
//		} catch (HibernateException e) {
//			log.error(e.getMessage(), e);
//		} catch (NamingException e) {
//			log.error(e.getMessage(), e);
//		}
//	}
}
