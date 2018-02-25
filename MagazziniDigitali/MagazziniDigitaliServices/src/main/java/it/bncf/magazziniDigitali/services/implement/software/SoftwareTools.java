package it.bncf.magazziniDigitali.services.implement.software;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import it.bncf.magazziniDigitali.businessLogic.software.MDSoftwareBusiness;
import it.bncf.magazziniDigitali.database.entity.MDSoftware;
import it.bncf.magazziniDigitali.services.implement.tools.ToolsServices;
import it.depositolegale.www.software.Software;
import mx.randalf.hibernate.exception.HibernateUtilException;

public class SoftwareTools {

	private static Logger log = Logger.getLogger(SoftwareTools.class);

	public SoftwareTools() {
	}

	public static boolean checkSoftware(Software software, String login) throws HibernateException, HibernateUtilException{
		boolean result = false;
		MDSoftwareBusiness mdSoftwareBusiness = null;
		MDSoftware mdSoftware = null;
		String msgErr = null;
		
		try {
			if (software.getErrorMsg() != null &&
					software.getErrorMsg().length>0){
				msgErr = "Richiesta da authenticazione Utente dall'IP ["+
						ToolsServices.getRemoteIP()+
						"] per l'utente ["+
						login+
						"]\n"+
						"Riscontrati degli errori di identificazione Software ["+
						software.getId()+"]:\n";
				for (int x=0; x<software.getErrorMsg().length; x++){
					msgErr+=software.getErrorMsg()[x]+"\n";
				}
				log.error("\n"+msgErr);
			} else {
				mdSoftwareBusiness  = new MDSoftwareBusiness();
				mdSoftware = mdSoftwareBusiness .findById(software.getId());
				
				if (mdSoftware != null &&
						mdSoftware.getId().equals(software.getId())){
					if (ToolsServices.testIP(mdSoftware.getIpAutorizzati())){
						if (mdSoftware.getLogin().equals(software.getAuthentication().getLogin())){
							if (mdSoftware.getPassword().equals(software.getAuthentication().getPassword())){
								result = true;
							} else {
								msgErr = "Richiesta da authenticazione Utente dall'IP ["+
										ToolsServices.getRemoteIP()+
										"] per l'utente ["+
										login+
										"]\n"+
										"Il Software ["+
										software.getId()+
										"] la password Software ["+
										software.getAuthentication().getPassword()+
										"] non è valida";
								log.error("\n"+msgErr);
							}
						} else {
							msgErr = "Richiesta da authenticazione Utente dall'IP ["+
									ToolsServices.getRemoteIP()+
									"] per l'utente ["+
									login+
									"]\n"+
									"Il Software ["+
									software.getId()+
									"] l'identificativo Software ["+
									software.getAuthentication().getLogin()+
									"] non è valido";
							log.error("\n"+msgErr);
						}
					} else {
						msgErr = "Richiesta da authenticazione Utente dall'IP ["+
								ToolsServices.getRemoteIP()+
								"] per l'utente ["+
								login+
								"]\n"+
								"Il Software ["+
								software.getId()+
								"] non può essere utilizzato dalla stazione chiamante";
						log.error("\n"+msgErr);
					}
				} else {
					msgErr = "Richiesta da authenticazione Utente dall'IP ["+
							ToolsServices.getRemoteIP()+
							"] per l'utente ["+
							login+
							"]\n"+
							"Il Software ["+
							software.getId()+
							"] non risulta registrato in base dati";
					log.error("\n"+msgErr);
				}
			}
		} catch (HibernateException e) {
			throw e;
		} catch (HibernateUtilException e) {
			throw e;
		}
		return result;
	}

}
