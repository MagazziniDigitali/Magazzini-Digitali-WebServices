package it.bncf.magazziniDigitali.businessLogic.oggettoDigitale;

import org.apache.log4j.Logger;

public class OggettoDigitaleValidateBusiness extends OggettoDigitaleBusiness{
	
	public Logger log = Logger.getLogger(getClass());

	public OggettoDigitaleValidateBusiness() {
		super();
	}

//	public void validate(String application, boolean testMode,
//			Logger logValidate) {
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
//			logValidate.debug("Ricerco oggetti da validare");
//			mdFileTmp = new MDFilesTmpBusiness(hibernateTemplate);
//
//			// Eseguo la ricerca di tutti i files che hanno finito il
//			// trasferimento oppure che risultano in fase di validazione
//			rs = mdFileTmp.find(null, null, null, new MDStato[] {
//					mdStatoDAO.FINETRASF(), mdStatoDAO.INITVALID() }, null,
//					1, 100);
//			if (rs != null && rs.size() > 0) {
//				logValidate.info("Numero oggetti da validare [" + rs.size()
//						+ "]");
//				// Risulta almeno 1 record da elaborare
//				futuresList = new ArrayList<Future<Boolean>>();
//				nrOfProcessors = Runtime.getRuntime().availableProcessors();
//				eservice = Executors.newFixedThreadPool(nrOfProcessors);
//				if (Configuration.getValue("demoni.Validate.numberThread") != null) {
//					numberThread = Integer.valueOf(Configuration
//							.getValue("demoni.Validate.numberThread"));
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
//					futuresList.add(eservice.submit(new OggettoDigitaleValidate(
//							rs.get(x), logValidate, mdFileTmp, "Validate " + x
//									+ "/" + rs.size(), application)));
//					Thread.sleep(10000);
//				}
//				checkThread(futuresList, -1);
//				eservice.shutdown();
//			} else {
//				logValidate.debug("Nessun oggetto da validare ");
//			}
//		} catch (ConfigurationException e) {
//			log.error(e.getMessage(), e);
//		} catch (HibernateException e) {
//			log.error(e.getMessage(), e);
//		} catch (NamingException e) {
//			log.error(e.getMessage(), e);
//		} catch (InterruptedException e) {
//			log.error(e.getMessage(), e);
//		}
//	}
}
